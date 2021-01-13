package lab4.gui;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;

import javax.swing.*;

/**
 * The GUI class.
 * Creates a Jframe in the constructor aswell as a GamePanel object that's a child object to a Jpanel.
 * @author Robin Olofsson Jakob Moregård
 */

public class GomokuGUI implements Observer{

    private GomokuClient client;
    private GomokuGameState gamestate;
    private GamePanel gameGridPanel;
    private JButton connectButton;
    private JButton newGameButton;
    private JButton disconnectButton;
    private JLabel messageLabel;


    /**
     * The constructor
     *
     * @param g   The game state that the GUI will visualize
     * @param c   The client that is responsible for the communication
     */
    public GomokuGUI(GomokuGameState g, GomokuClient c){
        this.client = c;
        this.gamestate = g;
        client.addObserver(this);
        gamestate.addObserver(this);
        SpringLayout layout = new SpringLayout();


        //Jframe setup (Exit on close and set prefered frame location)
        //Creates a frame with a exit constant on the exit button.
        // It also sets a prefered location of the frame on the screen.
        JFrame frame = new JFrame("Gomoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(150,150);

        //Jpanel
        //Creates a panel and sets the panels layout methods.
        gameGridPanel = new GamePanel(gamestate.getGameGrid());
        //gameGridPanel.setLayout(layout);
        Container container = frame.getContentPane();
        container.setLayout(layout);
        Dimension d = new Dimension(gameGridPanel.d.width + 100, gameGridPanel.d.height + 100);
        container.setPreferredSize(d);
        container.setBackground(Color.WHITE);


        //Creates components
        connectButton = new JButton("Connect");
        newGameButton = new JButton("New game");
        disconnectButton = new JButton("Disconnect");
        messageLabel = new JLabel(gamestate.getMessageString());


        //Adds the components to the panel that was created.
        container.add(gameGridPanel);
        container.add(connectButton);
        container.add(newGameButton);
        container.add(disconnectButton);
        container.add(messageLabel);


        //Sets the gameGridPanel in the center of the container panel, and then puts it 5 pixels from the top of the container.
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameGridPanel, 0, SpringLayout.HORIZONTAL_CENTER, container);
        layout.putConstraint(SpringLayout.NORTH, gameGridPanel, 5, SpringLayout.NORTH, container);

        //Sets the location of connectButton to 20 pixels under the GridPanel, and 10 px away from newGameButton.
        layout.putConstraint(SpringLayout.NORTH, connectButton, 20, SpringLayout.SOUTH, gameGridPanel);
        layout.putConstraint(SpringLayout.EAST, connectButton, -10, SpringLayout.WEST, newGameButton);

        //Sets the location of disconnectButton to 20 px under the Grid and 10px left of NewGameButton.
        layout.putConstraint(SpringLayout.NORTH, disconnectButton, 20, SpringLayout.SOUTH, gameGridPanel);
        layout.putConstraint(SpringLayout.WEST, disconnectButton, 10, SpringLayout.EAST, newGameButton);

        //Sets the NewGameButton centered to the GridPannel, and sets the distance between the button to be 20px under Grid.
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER, gameGridPanel);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, 20, SpringLayout.SOUTH, gameGridPanel);

        //Sets the messageLabel to be 10px under newGameButton and sets it centered to the grid.
        layout.putConstraint(SpringLayout.NORTH, messageLabel, 10, SpringLayout.SOUTH, newGameButton);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageLabel, 0,SpringLayout.HORIZONTAL_CENTER,gameGridPanel);

        //Connects the panel with the frame and sets the pack and makes the frame visible.
        frame.setContentPane(container);
        frame.pack();
        frame.setVisible(true);


        // ----- Start of the anonymous classes
        gameGridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Calls the method gridpos, and sends in the x/y cordinate of the panel board that is printed.
                int[] square = gameGridPanel.getGridPosition(e.getX(),e.getY());
                gamestate.move(square[1],square[0]);

            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionWindow connectionWindow = new ConnectionWindow(client);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamestate.newGame();
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamestate.disconnect();
            }
        });

    }

    /**
     *
     * @param arg0 the object that is being observed
     * @param arg1 the object that observes the observer.
     *
     * Gets called when game states or client changes.
     */

    public void update(Observable arg0, Object arg1) {

        // Update the buttons if the connection status has changed
        if(arg0 == client){
            if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
                connectButton.setEnabled(true);
                newGameButton.setEnabled(false);
                disconnectButton.setEnabled(false);
            }else{
                connectButton.setEnabled(false);
                newGameButton.setEnabled(true);
                disconnectButton.setEnabled(true);
            }
        }

        // Update the status text if the game state has changed
        if(arg0 == gamestate){
            messageLabel.setText(gamestate.getMessageString());
        }

    }

}