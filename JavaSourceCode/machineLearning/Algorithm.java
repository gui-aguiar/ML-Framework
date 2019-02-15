package machinelearning;

import java.io.Serializable;

public abstract class Algorithm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	PerformanceData performanceData;
	
	public abstract double predict(double[] features);

	public abstract boolean fit(double[][] features, double[] labels);
	
	public abstract void save();

	public abstract void read();	
}