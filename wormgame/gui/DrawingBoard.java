package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.game.Updatable;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;


public class DrawingBoard extends JPanel implements Updatable {

    private WormGame wormGame;
    private int pieceWidth;


    public DrawingBoard(WormGame wormGame) {
        super.setBackground(Color.LIGHT_GRAY);
        this.wormGame = wormGame;
        pieceWidth = wormGame.getPieceWidth();
    }

    //draws the worm and an apple
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (Piece aPiece : wormGame.getWorm().getPieces()) {
            graphics.setColor(Color.BLACK);
            graphics.fill3DRect(aPiece.getX(), aPiece.getY(), pieceWidth, pieceWidth, true);
        }

        graphics.setColor(Color.RED);
        graphics.fillOval(wormGame.getApple().getX(), wormGame.getApple().getY(), pieceWidth, pieceWidth);
    }

    @Override
    public void update() {
        repaint();
    }
}
