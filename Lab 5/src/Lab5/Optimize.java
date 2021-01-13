package Lab5;

import Lab5.model.StoreState;
import Lab5.model.event.ClosingEvent;
import Lab5.model.event.StartEvent;
import Lab5.model.event.StopEvent;
import Lab5.simulator.*;

/**
 * @authors roblof-8, johlax-8, wesjon-5, jakmor-8
 */

import java.util.Random;

public class Optimize implements K {

	public static void main(String[] args) {

		method3(SEED);

	}

	private static StoreState method1(int cashier, long seed) {

		// variables
		final double[] PICK_TIME = { LOW_COLLECTION_TIME, HIGH_COLLECTION_TIME };

		final double[] PAY_TIME = { LOW_PAYMENT_TIME, HIGH_PAYMENT_TIME };

		// Creates a specific state reference.
		StoreState specificState = new StoreState(cashier, M, L, PICK_TIME, PAY_TIME, seed);

		// Creates a queue where Events are stored, and a state and sends it to the
		// simulator.
		EventQueue queue = new EventQueue();
		// Creates a general state.
		State state = new State();
		// Creates simulator with the state and queue;
		Simulator sim = new Simulator(queue, state);

		StartEvent startEvent = new StartEvent(specificState, queue, 0);
		ClosingEvent closeEvent = new ClosingEvent(specificState, queue, END_TIME);
		StopEvent stopEvent = new StopEvent(specificState, queue, STOP_TIME);

		queue.addEvent(startEvent);
		queue.addEvent(closeEvent);
		queue.addEvent(stopEvent);

		sim.run();
		return specificState;
	}

	private static int method2(long seed) {
		StoreState state;
		int missed = 199999;
		int bestRegisterAmount = 0;

		// Loops through
		for (int cashiers = M; cashiers >= 1; cashiers--) {

			state = method1(cashiers, seed);

			// If the amount of missed people increase, then break.
			if (state.getMissed() > missed) {
				break;
			}
			missed = state.getMissed();
			bestRegisterAmount = cashiers;
		}

		// printStart();
		// System.out.println(" (" + missed + "): " + bestRegisterAmount);
		return bestRegisterAmount;
	}

	private static void method3(int seed) {

		// Variables
		Random random = new Random(seed);
		int counter = 0;
		int bestRegisterAmount = 1;
		int varv = 1;

		while (true) {

			int temp = method2(random.nextInt());

			if (temp > bestRegisterAmount) {
				counter = 0;
				bestRegisterAmount = temp;

			} else {
				counter++;
			}

			if (counter == 100) {
				break;
			}
//			System.out.println("varv: " + varv + " counter :" + counter);
//			System.out.println("BestKassaNu :" + bestRegisterAmount + " Temp KAssa:" + temp);
			varv++;
		}
		System.out.print("Bäst kassaantal: " + bestRegisterAmount);
	}

	private static void printStart() {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Max som ryms, M..........:" + M);
		System.out.println("Ankomshastighet, lambda..:" + L);
		System.out.println("Plocktider, [P_min..Pmax]: [" + LOW_COLLECTION_TIME + " .. " + HIGH_COLLECTION_TIME + "]");
		System.out.println("Betaltider, [K_min..Kmax]: [" + LOW_PAYMENT_TIME + " .. " + HIGH_PAYMENT_TIME + "]");
		System.out.println("FrÃ¶, f...................:" + SEED + "\n");

		System.out.println("StÃ¤ngning sker tiden " + END_TIME + " och stophÃ¤ndelsen sker tiden " + STOP_TIME + "\n");
		System.out.print("Minsta antal kassor som ger minimalt antal missade:");
	}
}
