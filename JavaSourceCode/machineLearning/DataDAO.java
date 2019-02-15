package machinelearning;

import java.io.Serializable;

public abstract class DataDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected double[][] features;
	protected double[] labels;

	public abstract double[][] load();

	public void addLog(int[] features, int label) {
		throw new UnsupportedOperationException();
	}

	public void delLog(int index) {		
		throw new UnsupportedOperationException();
	}

	public double[][] getFeatures() {
		return features;
	}

	public void setFeatures(double[][] features) {
		this.features = features;
	}

	public double[] getLabels() {
		return labels;
	}

	public void setLabels(double[] labels) {
		this.labels = labels;
	}

}