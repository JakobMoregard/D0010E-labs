package Lab5.simulator;

import java.util.Observable;

public class State extends Observable {

	//instance variables
	private boolean emergencyStop = false;
	private double time;


	public State(){
		this.time = 0;
	}

	// -------------  Getters ----------

	/**
	 *
	 * @return a boolean that tells if it's time to stop or not.
	 */
	public boolean getEmergencyStop(){
		return emergencyStop;
	}

	/**
	 *
	 * @return Returns the time
	 */
	public double getTime() {
		return time;
	}

	// --------- end Getters --------


	// --------- Setters --------

	/**
	 *
	 * @param newTime takes the current time of the simulation and sets it to time variable.
	 */
	public void setTime(double newTime) {
		this.time = newTime;
	}

	// --------- end Setters -------
	/**
	 * Stops the simulation by changing the flag "emergencyStop" to true
	 */
	public void raiseEmergencyStop() {
		emergencyStop = true;
	}
	
	public void deactivateEmergencyStop() {
		emergencyStop = false;
	}
}
