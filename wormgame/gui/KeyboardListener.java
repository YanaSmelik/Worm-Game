package wormgame.gui;

import wormgame.domain.Direction;
import wormgame.domain.Worm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private Worm worm;

    public KeyboardListener(Worm worm) {
        this.worm = worm;
    }

    //worm is assigned direction corresponding to the arrow button pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            worm.setDirection(Direction.UP);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            worm.setDirection(Direction.DOWN);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            worm.setDirection(Direction.LEFT);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            worm.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
