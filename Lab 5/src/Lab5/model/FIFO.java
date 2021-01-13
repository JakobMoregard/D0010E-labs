package Lab5.model;


import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * 
 * @author wesjon-5
 *
 *	This program gives the basis for an "First in first out" program.
 *
 */

class FIFO{
	private int maxSize = 0;
	private double inLineTime = 0.0;
	ArrayList<Integer> queue = new ArrayList<Integer>();
	private int peopleInLineTotal = 0;
	
	
	/**
	 * 
	 * @return The size of queue.
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * @return The max size queue have ever had.
	 */
	public int maxSize() {
		return maxSize;
	}
	
	
	/**
	 * 
	 * @return Checks if queue is empty.
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}
	
	
	/**
	 * 
	 * @return The first object in queue.
	 */
	public Integer first() {
		if(queue.size()== 0 ) {
			throw new NoSuchElementException("There are no elements in queue(First)");
		}
		return queue.get(0);
	}

	/*Returns a String that says what is in a queue.*/

	/**
	 * @return Element to string.
	 */
	public String toString() {
		String string = "[";
		
		for (int elem= 0; elem < queue.size(); elem++) {
			string += String.valueOf(queue.get(elem));
			if(elem < queue.size()-1) {
				string +=", ";
			}
		}	
		string += "]";
		return string;
	}
	
	/**
	 * Adds objects at the last position in a queue.
	 * @param item
	 */
	public void add(Integer item) {
		queue.add(item);
		peopleInLineTotal++;
		if (queue.size() > maxSize) {
			maxSize = queue.size();
		}
	}
	
	/**
	 * Removes the first object in a queue. 
	 */
	public void removeFirst(){
		if(queue.size()== 0 ) {
			throw new NoSuchElementException("There are no elements in queue(Remove First)");
		}
		queue.remove(0);
	}
	
	/**
	 * Increases the time a register have been free.
	 * @param newTime
	 */
	public void increasInLineTime(double newTime) {
		inLineTime += (newTime * queue.size());
	}
	
	/**
	 * 
	 * @return The time registers have been free.
	 */
	public double getInLineTime() {
		return inLineTime;
	}
	
	/**
	 * 
	 * @return The amount of people that's in line.
	 */
	public int getPeopleInLineTotal() {
		return peopleInLineTotal;
	}
}
