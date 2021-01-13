package lab2;

import java.awt.Color;

import lab2.level.Level;
import lab2.level.LevelGUI;
import lab2.level.Room;

public class Driver {

	public void run() {
		
//		Room room1 = new Room(50, 50 , Color.cyan);
//		Room room2 = new Room(20, 70 , Color.cyan);
		
		Room room1 = new Room(50, 50 , Color.cyan);
		Room room2 = new Room(120, 40 , Color.white);
		Room room3 = new Room(20, 50 , Color.MAGENTA);
		Room room4 = new Room(80, 40 , Color.red);
		Room room5 = new Room(90, 80 , Color.BLUE);
		
//		Room room1 = new Room(120, 120 , Color.cyan);
//		Room room2 = new Room(100, 100 , Color.BLUE);

		Level levelOne = new Level();
		
//		System.out.println(levelOne.place(room1,50,50));
//		System.out.println(levelOne.place(room2,65,40));
		
//		System.out.println(levelOne.place(room1,10,10));
//		System.out.println(levelOne.place(room2,0,0));
		
		
		System.out.println(levelOne.place(room1,20,20));
		System.out.println(levelOne.place(room2,0,90));
		System.out.println(levelOne.place(room3,40,60));
		System.out.println(levelOne.place(room4,100,0));
		System.out.println(levelOne.place(room5,130,60));
		

		
		levelOne.firstLocation(room1);
		
		LevelGUI levelGUI1 = new LevelGUI(levelOne, "First Level");
		
		room1.connectSouthTo(room2);
		room2.connectNorthTo(room1);
		room1.connectEastTo(room4);
		room4.connectWestTo(room1);
		room4.connectSouthTo(room5);
		room5.connectNorthTo(room4);
		room2.connectEastTo(room5);
		
		
		
		
	}

}