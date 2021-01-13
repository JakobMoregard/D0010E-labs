import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue {
	
	private int maxLenght = 0;
	private int currentLenght = 0;
	private ArrayList<Object> list = new ArrayList<Object>();
	
//	public FIFO() {
//		this.maxLenght = 0;
//		this.currentLenght = 0;
//		this.list = new ArrayList<Object>();
//		
//	}
	
	public void add(Object item) {
		list.add(item);
		currentLenght = list.size();
		if(currentLenght > maxLenght) {
			maxLenght = currentLenght;
		}
	}
	
	public void removeFirst() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			list.remove(0);
			//for(int i = 0; i < list.size(); i++) {
			//	list.add(i, list.get(i+1));
			//}
			//currentLenght = list.size();
		}
		
	}
	
	public Object first() throws NoSuchElementException {
		if(!isEmpty()) {
			return list.get(0);
		}
		else{
			throw new NoSuchElementException();
		}
	}
	
	public int maxSize() {
		if(currentLenght > maxLenght) {
			maxLenght = currentLenght;
		}
		return maxLenght;
	}
	
	public boolean isEmpty() {
		if(list.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int size() {
		return list.size();
	}
	
	public String toString() {
		String toStr = "Queue: ";
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) == null) {
				toStr = toStr + "(null) ";
			}
			else{
				toStr =  toStr + "(" + String.valueOf(list.get(i)) + ") ";
			}
		}
		return toStr;
	}
	
	public boolean equals(Object f) throws ClassCastException {
		boolean equal = true;
		if(f instanceof FIFO) {
			FIFO F = (FIFO) f;
			if(this.list.size() == F.size()) {
				for(int i = 0; i < this.list.size(); i++) {
					if(this.list.get(i) == null && F.list.get(i) != null ||
						this.list.get(i) != null && F.list.get(i) == null) {
						equal = false;
						break;
					}
					else if(this.list.get(i) == null && F.list.get(i) == null) {
						equal = true;
						continue;
					}
					else if(this.list.get(i).equals(F.list.get(i))) {
						equal = true;
						continue;
					}
				}
				return equal;
			}
			else {
				return false;
			}
		}
		else {
			throw new ClassCastException();
		}
	}	
}
