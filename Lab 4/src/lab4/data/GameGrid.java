package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 *
 * @author Robin Olofsson Jakob Moregård
 */

public class GameGrid extends Observable{

    public static final int EMPTY = 0;
    public static final int ME = 1;
    public static final int OTHER = 2;
    private static final int INROW = 5;
    private int[][] twoDim;


    /**
     * Constructor
     *
     * @param size The width/height of the game grid
     */
    public GameGrid(int size){
        twoDim = new int[size][size];
    }

    /**
     * Reads a location of the grid
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return the value of the specified location
     */
    public int getLocation(int x, int y){
        return twoDim[y][x];
    }

    /**
     * Returns the size of the grid
     *
     * @return the grid size
     */
    public int getSize(){
        return twoDim.length;
    }

    /**
     * Enters a move in the game grid
     *
     * @param x the x position
     * @param y the y position
     * @param player
     * @return true if the insertion worked, false otherwise
     */
    public boolean move(int x, int y, int player){
        int pos = twoDim[y][x];
        if(pos == EMPTY) {
            twoDim[y][x] = player;
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Clears the grid of pieces
     */
    public void clearGrid(){
        //goes throgh the 2d array and sets every element as EMPTY, then notifies the observers
        // that a change has been made.
        for(int i = 0; i < twoDim.length; i++){
            for(int j = 0; j < twoDim.length; j++){
                twoDim[i][j] = EMPTY;
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Check if a player has 5 in row
     *
     * @param player the player to check for
     * @return true if player has 5 in row, false otherwise
     */
    public boolean isWinner(int player) {
        int winner = 0;
        //Loopar igenom hela spelplanen
        for (int i = 0; i < twoDim.length; i++) {
            for (int j = 0; j < twoDim.length; j++) {


                // Kollar ifall den specifika positionen tillhör spelaren i sökes till vinst.
                if (twoDim[i][j] == player) {


                    //Kontrollerar ifall det finns spelplan för vinst.
                    boolean columsCheck = j + INROW - 1 < twoDim.length;
                    boolean rowCheck = i + INROW - 1 < twoDim.length;
                    boolean negativeColum = j - (INROW - 1) >= 0;


                    //kollar ifall man har vunnit i kolumner
                    if (columsCheck) {
                        if(winnerColum(i,j,player)){
                            return true;
                        }
                    }

                    //kollar ifall man har vunnit i rader
                    if (rowCheck) {
                        if(winnerRow(i,j,player)){
                            return true;
                        }

                    }

                    if (rowCheck && columsCheck) {
                        if(winnerDiag(i,j,player)){
                            return true;
                        }
                    }

                    if (negativeColum && rowCheck) {
                        if(winnerNegDiag(i,j,player)){
                            return true;
                        }
                    }
                } //Avslutning till if satsen som söker efter vinst spelaren
            }// for loopen
        }
        return false;
    }

    private boolean winnerRow(int i, int j, int player){
        int winner = 0;
        int rowLengths = i + INROW;
        for (int r = i; r < rowLengths; r++) {
            if (twoDim[r][j] == player) {
                winner++;
            }
        }
        if (winner == INROW) {
            return true;
        }
        return false;
    }

    public boolean winnerColum(int i, int j, int player){
        int winner = 0;
        int columLength = j + INROW;
        for (int c = j; c < columLength; c++) {
            if (twoDim[i][c] == player) {
                winner++;
            }
        }
        if (winner == INROW) {
            return true;
        }
        return false;

    }

    private boolean winnerDiag(int i, int j, int player){
        //används för att alltid vara 1 större, så man kör ett steg x led och sen köra om i Y led.
        int c = j; // c för columner
        int winner = 0;
        int rowLengths = i + INROW;

        for (int r = i; r < rowLengths; r++) {
            if (twoDim[r][c] == player) {
                ++winner;
            }
            c++;
        }

        if (winner == INROW) {
            return true;
        }
        return false;
    }

    private boolean winnerNegDiag(int i, int j, int player){

        int rowLengths = i + INROW;
        int c = j; // columner
        int winner = 0;

        for (int r = i; r < rowLengths; r++) {
            if (twoDim[r][c] == player) {
                ++winner;
            }
            c--;
        }
        if (winner == INROW) {
            return true;
        }
        return false;
    }
}