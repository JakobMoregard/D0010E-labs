package Lab5.model.event;

import Lab5.model.Customer;
import Lab5.model.StoreState;
import Lab5.simulator.Event;
import Lab5.simulator.EventQueue;

public class ArrivalEvent extends Event{
	private StoreState storeState;
	/**
	 * 
	 * @param storeState
	 * @param queue
	 * @param time
	 */
	public ArrivalEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;
	}
/**
 * Creates a new arrival. Checks if the store is open and if there is room for the new customer.
 */
	public void eventTriggered(){
		//System.out.println("Arrival Time: " + super.getTimeStamp() );
		// Set all pre update variables.
		storeState.setEventName("Arrival ");
		storeState.setCurrentTime(super.getTimeStamp());
		Customer customer = new Customer(storeState.getCustomerIDSize(), storeState, storeState.getPeopleInStore() < storeState.getMaxPeople());
		storeState.getCustomerID().add(customer);
		storeState.setCurrentID(Integer.toString(customer.getID()));
		if(!super.getQueue().isNextLast()) {
			double freeRegTime = super.getTimeStamp() - storeState.getTime();
			storeState.increasRegFreeTime(freeRegTime);
		}
		
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		
		storeState.update();
		//-------------------------------------------------------------------
		// change the rest of the variables.
		if(storeState.getIsOpen()) {
			
			storeState.setTime(getTimeStamp());
			storeState.increaseTotalAmountOfCustomers();
			
			// Is there still room for a new customer in the store?
			if (storeState.getPeopleInStore() < storeState.getMaxPeople()) {
				storeState.increasPeopleInStore();
				// Make a timeStamp for a new shoppingEvent.
				double timeStamp = storeState.getTime() + customer.getPickTime();
				// Make a new ShoppingEvent
			//	System.out.println("send from arrival "+customer.getID()+ " with timeStamp " + timeStamp);
				super.getQueue().addEvent(new ShoppingEvent(storeState, super.getQueue(), timeStamp, customer.getID()), customer.getID());
			}
			else {
				storeState.increaseMissed();
			}
			// Make a new ArrivalEvent and add it to the EventQueue.
			double arrTime = storeState.getTime() + storeState.getNextExponetialTime();
			super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
		}
	}
}