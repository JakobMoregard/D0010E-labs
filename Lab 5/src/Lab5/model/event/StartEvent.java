package Lab5.model.event;

import Lab5.model.StoreState;
import Lab5.simulator.*;
public class StartEvent extends Event{

	/*
	 * Lägg ur nödbromsen
	 * öppna butiken
	 * skapa en ankomsthändelse.
	 * */
	/*private CashRegisterState item;
	StartEvent(CashRegisterState item){
		this.item = item;
	}*/
	private StoreState storeState;
	private SimView view;
/**
 * 
 * @param storeState
 * @param queue
 * @param time
 * @param view
 */
	public StartEvent(StoreState storeState, EventQueue queue, double time, SimView view) {
		super(queue, time);
		this.storeState = storeState;
		this.view = view;
	}
/**
 * Deactivate the break, open the store, create the first ArrivalEvent. 
 */
	public void eventTriggered() {
		// Pre update changes.
		view.printStartup();
		storeState.setEventName("Start ");
		storeState.setCurrentTime(super.getTimeStamp());
		storeState.update();
		//After update changes.
		storeState.deactivateEmergencyStop();
		storeState.openStore();
		// The timeStamp for when the first arrival happens.
		
		double arrTime = storeState.getTime() + storeState.getNextExponetialTime();
		super.getQueue().addEvent(new ArrivalEvent(storeState, super.getQueue(), arrTime));
	}

}
