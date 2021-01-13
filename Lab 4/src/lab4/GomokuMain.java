package lab4;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

/**
 *
 * @author Robin Olofsson Jakob Moregård
 *
 * GomokuMain class job is to run/create the game. It creates the objects that are neccasary to start up the game.
 * It creates a client that keeps track of the different players connected
 * It creates a "game" a gamestate object that controlls the different state of the game.
 * It creates a gui that is an object is used to communicate with the player about how to game goes, the "view".
 */

public class GomokuMain {
    public static void main(String[] args) {

        GomokuClient client;
        GomokuClient client2 = new GomokuClient(4899);

        if(args.length == 0) {
            client = new GomokuClient(4989);
        }else{
            client = new GomokuClient(Integer.parseInt(args[0]));
        }

        //Creates a game object.
        GomokuGameState game = new GomokuGameState(client);
        GomokuGameState game2 = new GomokuGameState(client2);

        //Creates a gui object.
        GomokuGUI gui = new GomokuGUI(game,client);
        GomokuGUI gui1 = new GomokuGUI(game2,client2);
    }
}
