package Lab5.model;

import java.util.ArrayList;

import Lab5.ExponentialRandomStream;
import Lab5.UniformedRandomStream;
import Lab5.simulator.State;

/**
 *
 * @author wesjon-5, 
 *
 */
public class StoreState extends State{
	private int maxPeople;
	private double lambda;
	private double[] P;
	private double[] K;
	private long seed;
	private FIFO inLine = new FIFO();
	private int peopleInStore = 0;
	private int totalAmountOfCustomers = 0;
	private int missedCustomers = 0;
	private String eventName = "";
	private ArrayList<Customer> CustomerID = new ArrayList<Customer>();
	private boolean isOpen = false;
	private Registers cashier;
	private String currentID = "";
	private double currentTime = 0.0;
	private int totAmOfRegs;
	private ExponentialRandomStream ArrivalTime;
	private UniformedRandomStream PickTime;
	private UniformedRandomStream PayTime;
	private int customersDone = 0;
	/**
	 * @param cashiers How many cashiers is there in the store.
	 * @param maxPeople Max amount of people in the store.
	 * @param lambda Time divider.
	 * @param P Between what time variables dose it take for a customer to pick products.
	 * @param K Between what time variables dose it take for a customer to pay.
	 * @param seed The seed for how all the randomizes operate on.
	 */

	public StoreState(int cashiers, int maxPeople, double lambda, double[] P, double[] K, long seed) {
		this.totAmOfRegs = cashiers;
		this.cashier = new Registers(cashiers);
		this.maxPeople = maxPeople;
		this.lambda = lambda;
		this.P = P;
		this.K = K;
		this.seed = seed;
		this.ArrivalTime = new ExponentialRandomStream(lambda,seed);
		this.PickTime = new UniformedRandomStream(P[0],P[1],seed);
		this.PayTime = new UniformedRandomStream(K[0], K[1], seed);

	}

	//All the get methods 
	/**
	 * @return The max numbers of people allowed in the store.
	 */
	public int getMaxPeople() {
		return maxPeople;
	}
	
	/**
	 * 
	 * @return The total amount of customers.
	 */
	public int getTotalAmountOfCustomers(){
	    return getTotalOfCustormers();
    }
	
	 /**
     * 
     * @return The size of customers in line.
     */
	public int getQueueSize() {
		return inLine.size();
	}
	
	/**
	 * 
	 * @return The amount of registers in use.
	 */
	public int getRegsInUse() {
		return cashier.getUsed();
	}

	/**
	 * 
	 * @return The total amount of registers
	 */
	public int getAmOfRegs() {
		return totAmOfRegs;
	}
	
	/**
	 * 
	 * @return Who is in line.
	 */
	public String getQueue() {
		return inLine.toString();
	}

	/**
	 * 
	 * @return Lambda.
	 */
	public double getLambda() {
		return lambda;
	}

	/**
	 * 
	 * @return The Array P.
	 */
	public double[] getP(){
		return P;
	}

	/**
	 * 
	 * @return The Array K.
	 */
	public double[] getK(){
		return K;
	}
	/**
	 * @return The programs seed.
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @return The next person in line to pay.
	 */
	public int getNextInLine() {
		return inLine.first();
	}

	/**
	 * @return The amount of people in the store right now.
	 */
	public int getPeopleInStore() {
		return peopleInStore;
	}
	
	/**
	 * @return The total amount of customers that have arrived to the store.
	 */
	public int getTotalOfCustormers() {
		return totalAmountOfCustomers;
	}

	/**
	 * @return The amount of missed customers.
	 */
	public int getMissed() {
		return missedCustomers;
	}
	
	/**
	 * @return The name of the current event.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 *
	 * @param i
	 * @return The last customer.
	 */
	public int getCustomerID(int i){
		return CustomerID.get(i).getID();
	}

	/**
	 * @param i
	 * @return The time it takes for this customer to pick products.
	 */
	public double getCustomerBuyTime(int i) {
		return CustomerID.get(i).getPickTime();
	}

	/**
	 *
	 * @param i
	 * @return The time it takes for this customer to pay for the products.
	 */
	public double getCustomerPayTime(int i) {
		return CustomerID.get(i).getPayTime();
	}
	/**
	 * @return Is the store open.
	 */
	public boolean getIsOpen() {
		return isOpen;
	}
	
	/**
	 * 
	 * @return How many customers exists. 
	 */
	public int getCustomerIDSize(){
		return CustomerID.size();
	}
	
	/**
	 * 
	 * @return The ArrayList with the IDs.
	 */
	public ArrayList<Customer> getCustomerID(){
		return CustomerID;
	}
	
	/**
	 * 
	 * @return The ID of the active customer.
	 */
	public String getCurrentID() {
		return currentID;
	}
	
	/**
	 * 
	 * @return The time people have been in line.
	 */
	public double getInLineTime() {
		return inLine.getInLineTime();
	}
	
	/**
	 * 
	 * @return The time the registers have been free
	 */
	public double getFreeTimeRegs(){
		return cashier.getFreeTime();
	}
	
	/**
	 * 
	 * @return The total of people that have been in line.
	 */
	public int getPeopleInLineTotal() {
		return inLine.getPeopleInLineTotal();
	}
	
	/**
	 * 
	 * @return The time addition to next ArrivalEvent.
	 */
	public double getNextExponetialTime() {
		return ArrivalTime.next();
	}
	
	/**
	 * 
	 * @return The time addition to next ShoppingEvent.
	 */
	protected double getNextPickTime() {
		return PickTime.next();
	}
	
	/**
	 * 
	 * @return The time addition to next PayEvent.
	 */
	protected double getNextPayTime() {
		return PayTime.next();
	}
	
	/**
	 * 
	 * @return get the current time.
	 */
	public double getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * 
	 * @return The amount of customers that have payed and left.
	 */
	public int getCustomersDone() {
		return customersDone;
	}
	
//------------------------------------------------------------------------
	// All the set methods
	/**
	 * Sets the name of the current event.
	 * @param name
	 */
	public void setEventName(String name){
	    this.eventName = name;
    }
	
	/**
	 * Set the ID of the active customer.
	 * @param ID
	 */
	public void setCurrentID(String ID) {
		this.currentID = ID;
	}

	/**
	 * Change current time.
	 * @param time
	 */
	public void setCurrentTime(double time) {
		currentTime = time;
	}
	
//------------------------------------------------------------------------	
	// All the increase methods.
	/**
	 * Increases the amount of total customers. 
	 */
    public void increaseTotalAmountOfCustomers(){
	    this.totalAmountOfCustomers +=1;
    }
    
	/**
	 * increases the amount of people in the store.
	 */
    public void increasPeopleInStore() {
		peopleInStore++;
	}
    
	/**
	 * Increase the amount of missed customers.
	 */
    public void increaseMissed(){
	    this.missedCustomers ++;
    }

	/**
	 * Increases the time registers have been free.
	 * @param time
	 */
	public void increasRegFreeTime(double time) {
		cashier.increseFreeCashierTime(time);
	}
	
	/**
	 * Increases the time there have been people in line.
	 * @param time
	 */
	public void increasInLineTime(double time) {
		inLine.increasInLineTime(time);
	}
	
	/**
	 * Increases the amount of registers in use.
	 */
	public void increasRegsInUse() {
		cashier.increasRegsInUse();
	}
	
//------------------------------------------------------------------------
    //Decrease methods
    
    /**
     * Decrease the amount of people in the store.
     */
	public void decreasPeopleInStore() {
		peopleInStore--;
		customersDone++;
	}
	
	/**
	 * Decreases the amount of registers in use. 
	 */
	public void decreasRegsInUse() {
		cashier.decreasRegsInUse();
	}
    
//------------------------------------------------------------------------
    // Other methods
    /**
	 * Removes the first person in the line.
	 */
	public void removeInLine() {
		inLine.removeFirst();
	}

	/**
	 * @return True if the line is empty else false.
	 */
	public boolean isLineEmpty() {
		return inLine.isEmpty();
	}

	/**
	 * Opens the store.
	 */
	public void openStore() {
		isOpen = true;
	}
	
	/**
	 * Close the store. 
	 */
	public void closeStore() {
		isOpen = false;
	}
	
	/**
	 * Notify the observer that changes have been made.
	 */
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return If there is a free register.
	 */
	public boolean FreeRegs() {
		return cashier.getFreeRegs();
	}
	
	/**
	 * Adds a customer in line.
	 * @param ID
	 */
	public void addInLine(int ID) {
		inLine.add(ID);
	}
}