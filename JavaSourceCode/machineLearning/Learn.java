package machinelearning;

public abstract class Learn extends OperationMode {
	
	private static final long serialVersionUID = 1L;
	public void initialize() {
		addAlgorithms();
	}
	
	public double classify(double[] features) {
		return -1;
	}
	
	public double test() {
		return -1;
	}

	public boolean train(int featuresSize) {
		return classifier.train(featuresSize);
	}

	protected abstract void addAlgorithms();
}