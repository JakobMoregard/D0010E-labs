package Lab5.view;

import java.util.Observable;
import java.util.Observer;

import Lab5.model.StoreState;
import Lab5.simulator.SimView;

public class SuperMarketView extends SimView {
	private StoreState currentState;
	private String Pstr;
	private String Kstr;

	public SuperMarketView(StoreState state) {
		this.currentState = state;
		Pstr = "[" + String.valueOf(currentState.getP()[0]) + ".." + String.valueOf(currentState.getP()[1] + "]");
		Kstr = "[" + String.valueOf(currentState.getK()[0]) + ".." + String.valueOf(currentState.getK()[1] + "]");
	}
	/**
	 * Printar ut "huvudet" av simuleringen, dvs parametrarna
	 */
	public void printStartup() {

		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println("Antal kassor, N..........:" + currentState.getAmOfRegs());
		System.out.println("Max som ryms, M..........:" + currentState.getMaxPeople());
		System.out.println("Ankomshastighet, lambda..:" + currentState.getLambda());
		System.out.println("Plocktider, [P_min..Pmax]: " + Pstr);
		System.out.println("Betaltider, [K_min..Kmax]: " + Kstr);
		System.out.println("Frö, f...................:" + currentState.getSeed());

		System.out.println("FÖRLOPP");
		System.out.println("=======");
		System.out.println(
				"  Tid    Händelse    Kund    ?    led    ledT    I    $    :-(    köat    köT    köar    [Kassakö..]");

	}
	/**
	 * Printar ut resultatet av simuleringen
	 */
	public void printResult() {
		System.out.println("RESULTAT");
		System.out.println("========");
		System.out.println("1) av " + currentState.getTotalOfCustormers() + " kunder handlade "
				+ String.valueOf(currentState.getTotalOfCustormers() - currentState.getMissed()) + " medan "
				+ currentState.getMissed() + " missades.");
		System.out.println("2) Totalt tid " + currentState.getAmOfRegs() + " kassor har varit" + " lediga: "
				+ String.format("%.2f",currentState.getFreeTimeRegs()) + " te.");
		double snittTid = currentState.getFreeTimeRegs() / currentState.getAmOfRegs();
		double percentTid = 100*snittTid / currentState.getTime();
		System.out.println("Genomsnittlig ledig kassatid: " + String.format("%.2f",snittTid) + "(dvs " + String.format("%.2f",percentTid)
				+ "% av tiden från öppning till sista kunden betalat).");

		int totAmQueuedPeeps = currentState.getPeopleInLineTotal();
		double snittKöTid = currentState.getInLineTime() / totAmQueuedPeeps;
		System.out.println("3) Total tid " + totAmQueuedPeeps + " kunder" + " tvingats köa: "
				+ String.format("%.2f",currentState.getInLineTime()) + " te.");
		System.out.print("Genomsnittlig kötid: " + String.format("%.2f",snittKöTid) + " te.");
	}

	@Override
	public void update(Observable arg0, Object f) {
		String time = String.valueOf(String.format("%.2f", currentState.getCurrentTime())) + "    ";

		String event;

		if (currentState.getEventName() == "Arrival ") {
			event = "Arrival     ";
		} else if (currentState.getEventName() == "Shopping ") {
			event = "Shopping    ";
		} else if (currentState.getEventName() == "Payment ") {
			event = "Payment     ";
		} else if (currentState.getEventName() == "Closing ") {
			event = "Closing   ";
		} else {
			event = currentState.getEventName();
		}

		String custNum = String.valueOf(currentState.getCurrentID() + "      ");
		String open = (currentState.getIsOpen()) ? "Ö     " : "S     ";
		String amOfFreeRegs = String.valueOf(currentState.getAmOfRegs() - currentState.getRegsInUse()) + "     ";
		String sumTimeFreeRegs = String.valueOf(String.format("%.2f", currentState.getFreeTimeRegs())) + "    ";
		String amOfCusts = String.valueOf(currentState.getPeopleInStore()) + "    ";
		String doneCusts = String.valueOf(currentState.getTotalOfCustormers() - currentState.getPeopleInStore()) + "     ";
		String missedCusts = String.valueOf(currentState.getMissed()) + "      ";
		String totAmOfQueuedPeeps = String.valueOf(currentState.getPeopleInLineTotal()) + "      ";
		String timeQueued = String.valueOf(String.format("%.2f", currentState.getInLineTime())) + "     ";
		String inLine = String.valueOf(currentState.getQueueSize()) + "        ";
		String wholeQueue = currentState.getQueue() + " ";

		String infoRow;
		if (event == "Start " || event == "Stop ") {
			infoRow = time + event;
		} else {
			infoRow = time + event + custNum + open + amOfFreeRegs + sumTimeFreeRegs + amOfCusts + doneCusts
					+ missedCusts + totAmOfQueuedPeeps + timeQueued + inLine + wholeQueue;
		}

		System.out.println("  " + infoRow);
	}

}
