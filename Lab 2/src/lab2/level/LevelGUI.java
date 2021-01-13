
package lab2.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	
	public LevelGUI(Level level, String name) {
		
		this.lv = level;
		
		lv.addObserver(this);
		
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		d = new Display(lv,500,500);
		
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
	}
	
	
	public void update(Observable arg0, Object arg1) {
		d.repaint(); // BDE
	}
	
	private class Display extends JPanel {
		
		
		public Display(Level fp, int x, int y) {
		
			
			addKeyListener(new Listener());
			setBackground(Color.LIGHT_GRAY);
			setPreferredSize(new Dimension(x+20,y+20));
			setFocusable(true);
		}
	
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			roomMaker(g, lv);
			playerPrint(g);
			corridorMaker(g);

		}
		
		private void roomMaker(Graphics g, Level lv) {
			Room[] rooms = lv.getRoom();
			for(int i = 0; lv.getRoomNumber() > i; i++) {
				g.setColor(rooms[i].color);
				g.fillRect(rooms[i].roomPX, rooms[i].roomPY, rooms[i].pixelX , rooms[i].pixelY);
				g.setColor(Color.black);
				g.drawRect(rooms[i].roomPX, rooms[i].roomPY, rooms[i].pixelX , rooms[i].pixelY);
			}
		}
		
		private void playerPrint(Graphics g) {
			
			// Hittar mitten av första rummet.
			
			int middelOfRoomX = lv.getPlayerLocation().roomPX + (lv.getPlayerLocation().pixelX/2) -5;
			int middelOfRoomY = lv.getPlayerLocation().roomPY + (lv.getPlayerLocation().pixelY/2) - 5;
			
			
			g.fillOval(middelOfRoomX, middelOfRoomY, 10,10);
		}
		
		private void corridorMaker(Graphics g) {
			for(int i = 0; lv.getRoomNumber() > i; i++) {
				Room tempRoom = lv.getRoom()[i];
				if(tempRoom.portals[0] != null) {
					
					int x1 = tempRoom.roomPX + (tempRoom.pixelX / 2);
					int y1 = tempRoom.roomPY;
					int x2 = tempRoom.portals[0].roomPX + (tempRoom.portals[0].pixelX / 2);
					int y2 = tempRoom.portals[0].roomPY + tempRoom.portals[0].pixelY;
					
					g.drawLine(x1, y1, x2, y2);
					g.drawOval(x1-5, y1-5, 10, 10);
				}
				
				if(tempRoom.portals[1] != null) {
					
					int x1 = tempRoom.roomPX + tempRoom.pixelX;
					int y1 = tempRoom.roomPY + (tempRoom.pixelY /2);
					int x2 = tempRoom.portals[1].roomPX;
					int y2 = tempRoom.portals[1].roomPY + (tempRoom.portals[1].pixelY / 2);
					
					g.drawLine(x1, y1, x2, y2);
					g.drawOval(x1-5, y1-5, 10, 10);
					
				}
				
				if(tempRoom.portals[2] != null) {
					
					int x1 = tempRoom.roomPX + (tempRoom.pixelX /2);
					int y1 = tempRoom.roomPY + tempRoom.pixelY;
					int x2 = tempRoom.portals[2].roomPX + (tempRoom.portals[2].pixelX / 2);
					int y2 = tempRoom.portals[2].roomPY;
					
					g.drawLine(x1, y1, x2, y2);
					g.drawOval(x1-5, y1-5, 10, 10);
					
				}
				
				if(tempRoom.portals[3] != null) {
					
					int x1 = tempRoom.roomPX;
					int y1 = tempRoom.roomPY + (tempRoom.pixelY / 2);
					int x2 = tempRoom.portals[3].roomPX + tempRoom.portals[3].pixelX;
					int y2 = tempRoom.portals[3].roomPY + (tempRoom.portals[3].pixelY/2);
					
					g.drawLine(x1, y1, x2, y2);
					g.drawOval(x1-5, y1-5, 10, 10);
					
				}
				
			}
			
		}
		

	 	private class Listener implements KeyListener {
	 		
	 		
	 		
	 		public void keyPressed(KeyEvent arg0) {
	 			
	 			char key = arg0.getKeyChar();
	 			
	 			switch(key) {
	 			
	 			
	 			case 'w':
	 				lv.moveNorht();
	 				break;
	 				
	 			case 'd':
	 				lv.moveEast();
	 				break;
	 				
	 			case 's':
	 				lv.moveSouth();
	 				break;
	 			case 'a':
	 				lv.moveWest();
	 				break;
	 			default:
	 				break;
	 			}
	 			
	 		}

	 		public void keyReleased(KeyEvent arg0) {
	 		}

	 		public void keyTyped(KeyEvent event) {
	 		}
	 	}

	}
	
}