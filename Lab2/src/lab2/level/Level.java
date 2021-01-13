package lab2.level;
import java.util.Observable;


public class Level extends Observable {
	
	private static Room[] rooms;
	private static int roomNumber;
	private Room playerlocation;
	
	public Level(){
		rooms = new Room[10];
		roomNumber = 0;
		playerlocation = null;

		
		
	}
		
	private static boolean roomCheck(Room rNew, Room rOld) {
		
		// Moregård Algoritm
		
		if (rNew.roomPX + rNew.pixelX < rOld.roomPX 
				
			|| //--------------- OR --------------//
			
			rNew.roomPX > rOld.roomPX + rOld.pixelX	
			
			|| //--------------- OR --------------//
			
			rNew.roomPY > rOld.roomPY + rOld.pixelY 
			
			|| //--------------- OR --------------//
			
			rNew.roomPY + rNew.pixelY < rOld.roomPY
			){
			
			return false;
		}
		
		return true;
	}
//		
		

		
	public boolean place(Room r, int x, int y)  {
		
		r.roomPX = x; // Behövs för att jämföra.
		r.roomPY = y;
		
		
		//Loopar igenom befintiliga rum.
		for(int i=0; roomNumber > i; i++) {
			if (roomCheck(r, rooms[i])) {
				return false;
			}
			
		}
		
		rooms[roomNumber] = r;
		roomNumber++;
		return true;
			
	}	
	
	Room[] getRoom() { // Gör så att andra classer kan få tag på rummen.
		return rooms;
	}
	
	int getRoomNumber() { // Så vi vet hur många rumm finns.
		return roomNumber;
	}
	
	public void firstLocation(Room r) {
		playerlocation = r;
	}
	
	Room getPlayerLocation() {
		return playerlocation;
	}
	
	void playerMove(int move){
		
		if (playerlocation.portals[move] == null) {
			
		}
		else {
			playerlocation = playerlocation.portals[move];
			setChanged();
			notifyObservers();
		}
				
	}
	
	void moveNorht() {
		playerMove(0);
	}
	
	void moveEast() {
		playerMove(1);
	}
	
	void moveSouth() {
		playerMove(2);
	}
	
	void moveWest() {
		playerMove(3);
	}
}














