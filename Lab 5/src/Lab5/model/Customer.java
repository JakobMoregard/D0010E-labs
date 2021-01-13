package Lab5.model;
/**
 * 
 * @author wesjon-5
 *
 */
public class Customer {

	private int ID; 
	private double pickTime;
	private double payTime;
/**
 * 
 * @param ID
 * @param P
 * @param K
 * @param seed
 */
	public Customer(int ID, StoreState storeState, boolean check) {
		//Makes references to the uniformed class to be able to generate streams.
		
		
		this.ID = ID;
		if(check) {
			this.pickTime = storeState.getNextPickTime();
			this.payTime = storeState.getNextPayTime();
		}
	}
/**
 * 
 * @return The ID
 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * @return The pickTime.
	 */
	public double getPickTime() {
		return pickTime;
	}
	
	/**
	 * 
	 * @return The payTime.
	 */
	public double getPayTime() {
		return payTime;
	}
}
