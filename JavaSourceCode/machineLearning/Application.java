package machinelearning;

import java.io.Serializable;

public class Application implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int numberOfQuestions;
	private int numberOfPages;
	private OperationMode operationMode;

	private Inductor inductor;

    public Application(int numberOfQuestions) {
    	this.numberOfQuestions = numberOfQuestions;
    	inductor = new Inductor(getNumberOfQuestions());
		createOperationMode();
		inductor.setOperationMode(operationMode);
		
    	inductor.run();
	}

	private void createOperationMode() {
		throw new UnsupportedOperationException();
		// TODO: Edit to create the proper operationMode. Example:  operationMode =  new Classify();  		
	}

    public OperationMode getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(OperationMode operationMode) {
		this.operationMode = operationMode; 
		this.inductor.setOperationMode(operationMode);
	}
	
	public int getNumberOfQuestionForms() {
		return this.numberOfPages;
	}
	public void setNumberOfQuestionForms(int numberOfForms) {
		this.numberOfPages = numberOfForms;		
	}

	public int getNumberOfQuestions() {
		return this.numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;		
	}
	
	public double classify(double[] features) {
		return inductor.classify(features);
	}

	public double test() {
		return inductor.test();
	}

	public boolean train() {
		return inductor.train();
	}
	
	public void close() {
		inductor.close();
	}
}