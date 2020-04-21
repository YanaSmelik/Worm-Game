
import wormgame.game.WormGame;
import wormgame.gui.UserInterface;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        WormGame game = new WormGame(500);
        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);

        while(ui.getUpdatable() == null){
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }

        game.setUpdatable(ui.getUpdatable());
        game.start();
    }
}
