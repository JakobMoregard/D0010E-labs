package Lab5;

import java.util.Random;


public class UniformedRandomStream {

    private Random rand;
    private double lower, width;
/**
 * 
 * @param lower
 * @param upper
 * @param seed
 */
    public UniformedRandomStream(double lower, double upper, long seed) {
    	// Sets random to use this seed.
    	rand = new Random(seed);
        this.lower = lower;
        this.width = upper-lower;
    }
/**
 * 
 * @param lower
 * @param upper
 */
    public UniformedRandomStream(double lower, double upper) {
    	// Sets random run without seed.
        rand = new Random();
        this.lower = lower;
        this.width = upper-lower;
    }
/**
 * 
 * @return A random double between lower and upper.
 */
    public double next() {
    	double test = lower+rand.nextDouble()*width;
    	//System.out.println("Test = " + test);
        return test;//lower+rand.nextDouble()*width;
    }
}