package machinelearning;

import java.io.Serializable;

public abstract class OperationMode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Classifier classifier;
	
	public OperationMode(Classifier classifier) {
		this.classifier = classifier;
	}

	public OperationMode() {
		this.classifier = new Classifier();
	}
	
	public abstract void initialize();

	public abstract double classify(double[] features);

	public abstract double test();

	public abstract boolean train(int featuresSize);

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	public void close() {
		classifier.close();
	}

}