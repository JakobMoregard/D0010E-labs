package lab4.gui;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 * @author Robin Olofsson Jakob Moregård
 */

public class GamePanel extends JPanel implements Observer{

    private final int UNIT_SIZE = 20;
    private GameGrid grid;
    Dimension d;

    /**
     * The constructor
     *
     * @param grid The grid that is to be displayed
     */
    public GamePanel(GameGrid grid){
        this.grid = grid;
        grid.addObserver(this);
        d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setBackground(Color.WHITE);
    }

    /**
     * Returns a grid position given pixel coordinates
     * of the panel
     *
     * @param x the x coordinates
     * @param y the y coordinates
     * @return an integer array containing the [x, y] grid position
     */
    public int[] getGridPosition(int x, int y){
        int colum = x / UNIT_SIZE;
        int row = y / UNIT_SIZE;
        int[] returnArray = {row,colum};
        return returnArray;
    }


    /**
     *
     * @param arg0 the observable object that this class observes. In this case the Grid.
     * @param arg1 a objec that this update method get calls to (.this)
     */
    public void update(Observable arg0, Object arg1) {
        this.repaint();
    }

    /**
     *
     * @param g takes a graphic object as parameter
     * paintComponents get's called every time update method is called (repaint() method is called).
     */
    public void paintComponent(Graphics g){
        int row;
        int colum;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.BLACK);
        Stroke standardStroke = g2.getStroke();


        for(int i = 0; i < grid.getSize(); i++){
            for(int j = 0; j < grid.getSize(); j++){
                row = i * UNIT_SIZE;
                colum = j * UNIT_SIZE;
                g.drawRect(row,colum,UNIT_SIZE,UNIT_SIZE);
                if(grid.getLocation(j,i) == grid.ME){
                    g.fillOval(colum+ 1,row+1,UNIT_SIZE-2,UNIT_SIZE-2);
                }
                if(grid.getLocation(j,i) == grid.OTHER){
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(colum+2,row+2,colum + UNIT_SIZE - 2,row + UNIT_SIZE - 2);
                    g2.drawLine(colum + UNIT_SIZE - 2,row + 2, colum + 2,row + UNIT_SIZE - 2);
                    g2.setStroke(standardStroke);
                }
            }
        }

    }

}