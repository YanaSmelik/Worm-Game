package wormgame.gui;

import wormgame.game.Updatable;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame wormGame;
    private DrawingBoard drawingBoard;

    public UserInterface(WormGame wormGame) {
        this.wormGame = wormGame;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        frame.setPreferredSize(new Dimension(wormGame.getFieldWidth(), wormGame.getFieldWidth()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }


    private void createComponents(Container container) {
        drawingBoard = new DrawingBoard(wormGame);
        container.add(drawingBoard);


        frame.addKeyListener(new KeyboardListener(wormGame.getWorm()));
    }

    public Updatable getUpdatable(){
        return drawingBoard;
    }



}
