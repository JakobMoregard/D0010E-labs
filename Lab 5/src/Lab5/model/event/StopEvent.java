package Lab5.model.event;

import Lab5.model.StoreState;
import Lab5.simulator.*;

public class StopEvent extends Event{

	SimView view;

	private StoreState storeState;
/**
 * 
 * @param storeState
 * @param queue
 * @param time
 * @param view
 */
	public StopEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}

	/**
	 *
	 * Lägg i nödbromsen
	 */
	public void eventTriggered() {
		storeState.setEventName("Stop ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.update();
		//storeState.setTime(getTimeStamp());

		//prints the result before shutting down.
		view.printResult();
		storeState.raiseEmergencyStop();
	}
}
