package wormgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Worm extends Piece {

    private Direction direction;
    private List<Piece> worm;
    private boolean canGrow;

    //worm is composed of a list of instances of the class Piece
    //when worm is created, it's length is 1, but it's "mature" length is 3.
    //Worm has to grow by one piece while it moves
    //when its length is 3, Worm grows only by eating.
    public Worm(int originalX, int originalY, int width, Direction direction) {
        super(originalX, originalY, width);
        this.direction = direction;
        worm = new ArrayList<>();
        worm.add(new Piece(originalX, originalY, width));
        canGrow = false;
    }


    public Direction getDirection() {
        return direction;
    }

    //The worm starts to move in the new direction when the method move() is called again
    public void setDirection(Direction dir) {
        direction = dir;
    }

    //returns the worm's length;
    // the worm's length has to be equal to the length of the list returned by the method getPieces()
    public int getLength() {
        return worm.size() - 1;
    }


    //returns a list of Piece objects which the worm is composed of.
    //the worm head is at the end of the list.
    public List<Piece> getPieces() {
        return worm;
    }


    // moves the worm one piece forward. While moving the worm is always given a new piece.
    // the position of the new piece depends on the moving direction
    // When the worm moves, a new piece is added to the list, and the first piece is deleted from the beginning of the list.
    public void move() {
        int newX = 0;
        int newY = 0;
        if (direction.equals(Direction.LEFT)) {
            newX = worm.get(getLength()).getX() - super.getWidth();
            newY = worm.get(getLength()).getY();
        }

        if (direction.equals(Direction.RIGHT)) {
            newX = worm.get(getLength()).getX() + super.getWidth();
            newY = worm.get(getLength()).getY();
        }

        if (direction.equals(Direction.UP)) {
            newX = worm.get(getLength()).getX();
            newY = worm.get(getLength()).getY() - super.getWidth();
        }

        if (direction.equals(Direction.DOWN)) {
            newX = worm.get(getLength()).getX();
            newY = worm.get(getLength()).getY() + super.getWidth();
        }

        //if worm is less than 3 Pieces long, it grows constantly
        if (worm.size() < 3) {
            worm.add(new Piece(newX, newY, super.getWidth()));
        } else {
            if (!canGrow) {  //worm cannot grow, thus its length doesn't change
                worm.add(new Piece(newX, newY, super.getWidth()));
                worm.remove(0);

            } else if (canGrow) {  //worm can grow, thus its length increases by one piece
                worm.add(new Piece(newX, newY, super.getWidth()));
                canGrow = false;
            }
        }
    }

    public void grow() {
        canGrow = true;
    }

    //checks whether the worm runs into the parameter piece.
    public boolean runsInto(Piece piece) {
        for (Piece thisPiece : worm) {
            if (pieceRunsIntoPiece(thisPiece, piece)) {
                return true;
            }
        }
        return false;
    }

    //helper method for runsInto(Piece piece)
    public boolean pieceRunsIntoPiece(Piece piece1, Piece piece2) {
        if (piece1.getX() == piece2.getX() && piece1.getY() == piece2.getY()) {
            return true;
        }
        return false;
    }

    //checks whether the worm runs into itself.
    // if so (if one piece of the worm runs into another piece of the worm) - the method returns the value true;
    // else, it returns false;
    public boolean runsIntoItself() {
        Piece head = getWormHead();
        for (int i = 0; i < getLength(); i++) {
            if (i == getLength()) {

            } else {
                if (head.getX() == worm.get(i).getX() && head.getY() == worm.get(i).getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Piece getWormHead() {
        return worm.get(getLength());
    }
}
