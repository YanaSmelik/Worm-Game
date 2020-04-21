package wormgame.game;

import wormgame.domain.Apple;
import wormgame.domain.Direction;
import wormgame.domain.Piece;
import wormgame.domain.Worm;

import java.util.concurrent.*;


public class WormGame implements Updatable, Runnable {

    private int fieldWidth;
    private Worm currentWorm;
    private Apple currentApple;
    private int pieceWidth;
    private ScheduledExecutorService scheduleAtRate;
    private Updatable updatable;


    public WormGame(int fieldWidth) {
        this.fieldWidth = fieldWidth;
        pieceWidth = fieldWidth / 20;
        //game's worm is created in the constructor.
        currentWorm = new Worm(fieldWidth / 2, fieldWidth / 2, pieceWidth, Direction.DOWN);

        //apple is created inside of the constructor
        currentApple = new Apple(randomCoordinate(), randomCoordinate(), pieceWidth);
        scheduleAtRate = Executors.newSingleThreadScheduledExecutor();
    }

    public int getPieceWidth() {
        return pieceWidth;
    }

    public Worm getWorm() {
        return currentWorm;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int randomCoordinate() {
        int initialRandom = ThreadLocalRandom.current().nextInt(0 + pieceWidth * 2, fieldWidth - pieceWidth * 2);
        return initialRandom - (initialRandom % pieceWidth); //set apple's coordinate to the same range as Worm's.
    }

    //checks that apple won't appear on a worm
    public Apple newAppleOnField() {
        while (true) {
            Apple apple = new Apple(randomCoordinate(), randomCoordinate(), pieceWidth);
            if (!currentWorm.runsInto(apple)) {
                return apple;
            }
        }
    }

    //sets on the game the method parameter worm.
    //If the method getWorm is called after the worm has been set up, it has to return a reference to the same worm
    public void setWorm(Worm worm) {
        currentWorm = worm;
    }

    public Apple getApple() {
        return currentApple;
    }

    //sets the method parameter apple to the worm game.
    // If the method getApple is called after the apple has been set up, it has to return a reference to the same apple
    public void setApple(Apple apple) {
        currentApple = apple;
    }

    //This method implements the following tasks in the given order:
    // 1. Move the worm;
    // 2. If the worm runs into the apple, it eats the apple and calls the grow method. A new apple is randomly created.
    // 3. If the worm runs into itself, the variable continueGame is assigned the value false.
    public void action() {
        Piece head = currentWorm.getWormHead();
        currentWorm.move();

        if (currentWorm.runsInto(currentApple)) {
            currentWorm.grow();
            setApple(newAppleOnField());
        }

        if (currentWorm.runsIntoItself()) {
            System.exit(0);
        }

        if (head.getX() > fieldWidth || head.getX() < 0 || head.getY() > fieldWidth || head.getY() < 0) {
            System.exit(0);
        }
    }

    public int setDelay() {
        if (getWorm().getLength() >= 3) {
            return 500 / currentWorm.getLength();
        }
        return 500;
    }

    @Override
    public void update() {
        scheduleAtRate.scheduleWithFixedDelay(() -> {
            action();
            updatable.update();
        }, setDelay(), setDelay(), TimeUnit.MILLISECONDS);
    }

    public void setUpdatable(Updatable upd) {
        updatable = upd;
    }

    @Override
    public void run() {

    }

    //Starts the game and binds the game logic with ui
    public void start() {
        update();
    }
}
