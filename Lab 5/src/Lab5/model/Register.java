package Lab5.model;

/**
 * 
 * @author wesjon-5
 *
 */
class Registers {
	private int amountOfRegisterInUse;
	private int totalAmountOfRegisters;
	private double freeCashierTime = 0.0;
	
/**
 * 
 * @param registers
 */
	public Registers(int registers) {
		this.totalAmountOfRegisters = registers;
		this.amountOfRegisterInUse = 0;
	}
	
/**
 * 
 * @return The amount of registers in use. 
 */
	public int getUsed(){
		return this.amountOfRegisterInUse;
	}
	
/**
 * 
 * @return If there is a free register.
 */
	public boolean getFreeRegs(){
		return !(amountOfRegisterInUse==totalAmountOfRegisters);
	}
	
/**
 * 
 * @return The total amount of registers.
 */
	public int getTotalAmountOfRegisters() {
		return totalAmountOfRegisters;
	}
	
/**
 * 
 * @param time
 */
	public void increseFreeCashierTime(double time) {
		freeCashierTime += (time * (totalAmountOfRegisters - amountOfRegisterInUse));
	}
	
/**
 * Increases the amount of registers in use.
 */
	public void increasRegsInUse() {
		amountOfRegisterInUse++;
	}
/**
 * Decreases the amount of registers in use. 
 */
	public void decreasRegsInUse() {
		amountOfRegisterInUse--;
	}
	
/**
 * 
 * @return The time the registers have been free.
 */
	public double getFreeTime() {
		return freeCashierTime;
	}
}