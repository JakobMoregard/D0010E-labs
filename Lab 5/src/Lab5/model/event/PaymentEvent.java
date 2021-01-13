package Lab5.model.event;

import Lab5.model.*;
import Lab5.simulator.*;

public class PaymentEvent extends Event{

	private StoreState storeState;
	private int ID;

	public PaymentEvent(StoreState storeState, EventQueue queue, double time, int ID) {
		super(queue, time);
		this.storeState = storeState;
		this.ID = ID;
	}


	public void eventTriggered() {
		storeState.setEventName("Payment ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID(Integer.toString(ID));
		
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasRegFreeTime(freeRegTime);
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		storeState.update();
		//----------------------------------------------- Before notifying

		//Removes costumer from store.
		storeState.decreasPeopleInStore();
		//Changes the time of the current time.
		storeState.setTime(getTimeStamp());

		//Checks if the queue is empty.
		if(!storeState.isLineEmpty()){

			//Creates a new payment event if there is people in queue.
			int nextInLine = storeState.getNextInLine();
			PaymentEvent EventPay = new PaymentEvent(storeState,super.getQueue(),super.getTimeStamp() +	storeState.getCustomerPayTime(nextInLine), nextInLine);

			//Adds the event to EventQueue.
			super.getQueue().addEvent(EventPay, storeState.getNextInLine());

			//Removes the one who's first in the queue.
			storeState.removeInLine();

		}else {
			storeState.decreasRegsInUse();
		}
		
	}

}
