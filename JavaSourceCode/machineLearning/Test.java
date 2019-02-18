package machinelearning;

public abstract class Test extends OperationMode {

	private static final long serialVersionUID = 1L;
	@Override
	public void initialize() {
		classifier.load();
		test();		
	}

	@Override
	public double classify(double[] features) {
		return -1;
	}

	@Override
	public boolean train(int featuresSize) {
		return false;
	}
	
	public abstract double test();

}