package lab4.data;

import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;

/**
 * Represents the state of a game
 * @author Robin Olofsson Jakob Moregård
 */

public class GomokuGameState extends Observable implements Observer{

    // Game variables
    private final int DEFAULT_SIZE = 15;
    private GameGrid gameGrid;

    //Possible game states
    private final int NOT_STARTED = 0;
    private final int MY_TURN = 1;
    private final int OTHER_TURN = 2;
    private final int FINISHED = 3;
    private int currentState;

    //client object that controlls the client connections.
    private GomokuClient client;

    // message variable for JLabel
    private String message;

    /**
     * The constructor
     *
     * @param gc The client used to communicate with the other player
     */
    public GomokuGameState(GomokuClient gc){
        client = gc;
        client.addObserver(this);
        gc.setGameState(this);
        currentState = NOT_STARTED;
        gameGrid = new GameGrid(DEFAULT_SIZE);
        message = "Welcome to Gomoku!";
    }


    /**
     * Returns the message string
     *
     * @return the message string
     */
    public String getMessageString(){
        return message;
    }

    /**
     * Returns the game grid
     *
     * @return the game grid
     */
    public GameGrid getGameGrid(){
        return gameGrid;
    }

    /**
     * This player makes a move at a specified location
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void move(int x, int y){
        System.out.println(currentState);

        //Checks if it's my turn.
        if (currentState == MY_TURN) {

            //If it is my turn, control if the move is ok to make/make it.
            if(gameGrid.move(x,y,MY_TURN)){

                //If the move was able to be made then change the label and call sendMoveMessage()
                client.sendMoveMessage(x,y);
                message = "Very clever move!";

                //Checks in case the move was a winnable move. If it is notify and return the method so the constant is set to finished.
                if(gameGrid.isWinner(MY_TURN)){
                    currentState = FINISHED;
                    message = "Congrats you have won the game!";
                    setChanged();
                    notifyObservers();
                    return;
                }
                //if it wasn't a winnable move then set it to the other players turn and notify it.
                currentState = OTHER_TURN;
                setChanged();
                notifyObservers();
            }else{
                message = "You can't make the move since it's taken";
            }
        }
        else if(currentState == NOT_STARTED){
            message = "The game has not even started yet chill bro..";
            setChanged();
            notifyObservers();
            return;
        }
        else if(currentState == FINISHED){
            message = "The game is over, winner is declared!";
            setChanged();
            notifyObservers();
            return;
        }
        else{
            message = "It's not your turn bro...";
            setChanged();
            notifyObservers();
            return;
        }
    }

    /**
     * Starts a new game with the current client
     */
    public void newGame(){
        gameGrid.clearGrid();
        currentState = OTHER_TURN;
        message = "New game started! it's your opponents turn!";
        client.sendNewGameMessage();
        setChanged();
        notifyObservers();
    }

    /**
     * Other player has requested a new game, so the
     * game state is changed accordingly
     */
    public void receivedNewGame(){
        gameGrid.clearGrid();
        message = "Game has started, it's your turn!";
        currentState = MY_TURN;
        setChanged();
        notifyObservers();
    }

    /**
     * The connection to the other player is lost,
     * so the game is interrupted
     */
    public void otherGuyLeft(){
        gameGrid.clearGrid();
        message = "Your opponent has disconnected, no ongoing games!";
        setChanged();
        notifyObservers();

    }

    /**
     * The player disconnects from the client
     */
    public void disconnect(){
        gameGrid.clearGrid();
        message = "Disconnected, no on going Game!";
        setChanged();
        notifyObservers();
        client.disconnect();
    }

    /**
     * The player receives a move from the other player
     *
     * @param x The x coordinate of the move
     * @param y The y coordinate of the move
     */
    public void receivedMove(int x, int y){

        //Makes the move and changes
        gameGrid.move(x,y,OTHER_TURN);

        //Checks in case the move that was made by the opponent was a finishing blow.
        if(gameGrid.isWinner(OTHER_TURN)){
            message = "The opponent has won the game, better luck next time!";
            currentState = FINISHED;
            setChanged();
            notifyObservers();
            return;
        }
        currentState = MY_TURN;
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param o the observable object, the client
     * @param arg the observer object
     */

    public void update(Observable o, Object arg) {

        switch(client.getConnectionStatus()){
            case GomokuClient.CLIENT:
                message = "Game started, it is your turn!";
                currentState = MY_TURN;
                break;
            case GomokuClient.SERVER:
                message = "Game started, waiting for other player...";
                currentState = OTHER_TURN;
                break;
        }
        setChanged();
        notifyObservers();
    }

}