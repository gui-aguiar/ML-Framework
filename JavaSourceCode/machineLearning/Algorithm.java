package machinelearning;

import java.io.Serializable;

public abstract class Algorithm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	PerformanceData performanceData;
	DataSplitter dataSplitter;
	
	public abstract void save();

	public abstract void read();	
	
	public abstract double predict(double[] features);

	public boolean fit(double[][] features, double[] labels) {
		dataSplitter = new DataSplitter();
		dataSplitter.SetData(features, labels);
		return fitAlgorithm(features, labels);
	};
	
	protected abstract boolean fitAlgorithm(double[][] features, double[] labels);
	
}