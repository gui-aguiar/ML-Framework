package machinelearning;

public class Classify extends OperationMode {

	private static final long serialVersionUID = 1L;
	public void initialize() {
		classifier.load();		
	}
	
	public double classify(double[] features) {
		return classifier.classify(features);
	}

	public double test() {
		return -1;
	}

	public boolean train(int featuresSize) {
		return false;
	}

}