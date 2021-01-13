package Lab5;

import Lab5.model.event.ClosingEvent;
import Lab5.model.event.StartEvent;
import Lab5.model.event.StopEvent;
import Lab5.simulator.*;
import Lab5.model.StoreState;


public class SimMain {

    public static void main(String[] args) {
        //Creates constants the defines the specific state.
        final int CASHIER = 2;
        final int MAX_PEOPLE = 5;
        final double lambda = 1.0;
        final double[] PICK_TIME = {0.5, 1.0};
        final double[] PAY_TIME = {2.0, 3.0};
        final long SEED = 1234;

       //Creates a specific state reference.
       StoreState specificState = new StoreState(CASHIER,MAX_PEOPLE,
                lambda,PICK_TIME,PAY_TIME,SEED);

        //Creates a queue where Events are stored, and a state and sends it to the simulator.
        EventQueue queue = new EventQueue();
        //Creates a general state.
        State state = new State();
        //Creates simulator with the state and queue;
        Simulator sim = new Simulator(queue,state);
        //Creates a view.
        SimView view = new SimView(specificState);

        

        StartEvent startEvent = new StartEvent(specificState,queue,0, view);
        ClosingEvent closeEvent = new ClosingEvent(specificState,queue,10);
        StopEvent stopEvent = new StopEvent(specificState,queue,999, view);
        
        queue.addEvent(startEvent);
        queue.addEvent(closeEvent);
        queue.addEvent(stopEvent);
        
        sim.run();
    }

}