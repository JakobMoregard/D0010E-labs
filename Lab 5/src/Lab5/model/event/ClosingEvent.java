package Lab5.model.event;

import Lab5.model.StoreState;
import Lab5.simulator.*;
public class ClosingEvent extends Event{

	private StoreState storeState;
	/**
	 * 
	 * @param storeState
	 * @param queue
	 * @param time
	 */
	public ClosingEvent(StoreState storeState, EventQueue queue, double time) {
		super(queue, time);
		this.storeState = storeState;

	}
/**
 * Closing down the store.
 */
	public void eventTriggered() {
		// Set all pre update variables.
		storeState.setEventName("Closing ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.setCurrentID("---");		
		
		// The time registers have been free since last timeStamp.
		double freeRegTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasRegFreeTime(freeRegTime);
		// The time people have been in line since last timeStamp.
		double peopleInLineTime = super.getTimeStamp() - storeState.getTime();
		storeState.increasInLineTime(peopleInLineTime);
		storeState.update();
		//-------------------------------------------------
		// change the rest of the variables
		storeState.setTime(getTimeStamp());
		storeState.closeStore();
		
	}
}
