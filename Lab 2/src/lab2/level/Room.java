
package lab2.level;

import java.awt.Color;


public class Room { 
	
	int pixelX;
	int pixelY;
	Color color;
	
	Room[] portals = {null,null,null,null};
	
	int roomPX;
	int roomPY;
	
	
	public Room(int dx, int dy, Color color) {
		
		this.pixelX = dx;
		this.pixelY = dy;
		this.color = color;

		
		System.out.println("color: " + this.color);
		System.out.println("X length: " + this.pixelX);
		System.out.println("Y length: " + this.pixelY);
		
	}

	public void connectNorthTo(Room r) {
		this.portals[0] = r;
	}
	public void connectEastTo(Room r) {
		this.portals[1] = r;
	}
	public void connectSouthTo(Room r) {
		this.portals[2] = r;
	}
	public void connectWestTo(Room r) {
		this.portals[3] = r;
	}
}