package machinelearning;

import java.io.Serializable;

public class Inductor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private OperationMode operationMode;
	private int featuresSize;

	public Inductor(int feturesSize) {
		this.featuresSize = feturesSize;
	}

	public OperationMode getOperationMode() {
		return this.operationMode;
	}

	public void setOperationMode(OperationMode operationMode) {
		this.operationMode = operationMode;
	}

	public void run() {
		operationMode.initialize();
	}

	public double classify(double[] features) {
		return operationMode.classify(features);
	}

	public double test() {
		return operationMode.test();
	}

	public boolean train() {
		return operationMode.train(featuresSize);
	}

	public void close() {
		operationMode.close();
	}

}