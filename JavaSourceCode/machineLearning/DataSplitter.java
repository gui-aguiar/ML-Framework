package machinelearning;

public class DataSplitter {

	private double[][] trainingData;
	private double[][] validationData;
	private double[][] testData;
	
	private double[][] joinedData;
	
	private double trainingDataShare;
	private double validationDataShare;
	private double testDataShare;
	
	public DataSplitter(){
		trainingDataShare = 0.7;
		validationDataShare = 0.15;
		testDataShare = 0.15;	
	}
	
	public DataSplitter(double[][] features, double[] labels) {
		JoinData(features, labels);
		
		trainingDataShare = 0.7;
		validationDataShare = 0.15;
		testDataShare = 0.15;		
		SplitData();
	}

	public DataSplitter(double[][] features, double[] labels, double trainingDataShare, double validationDataShare, double testDataShare) {
		JoinData(features, labels);
		
		if (!setDataShare(trainingDataShare, validationDataShare, testDataShare)) {
			this.trainingDataShare = 0.7;
			this.validationDataShare = 0.15;
			this.testDataShare = 0.15;
			
			SplitData();	
		};
	}
	
	private void JoinData(double[][] features, double[] labels) {
		double[][] joinedData = new double[features.length][features[0].length + 1];
		for (int i = 0; i < features.length; i++) {
			for (int j = 0; j < features[i].length; j++) {
				joinedData[i][j] = features[i][j];
			}		
			joinedData[i][joinedData[0].length - 1] = labels[i];
		}	
	}
	
	private void SplitData() {
		int totalDataSamples = joinedData.length;
		int trainingSamples = (int) Math.ceil(totalDataSamples * trainingDataShare);
		int validationSamples = (int) Math.floor(totalDataSamples * validationDataShare);
		int testSamples =  (int) Math.floor(totalDataSamples * testDataShare);
		int totalDefinedSamples = trainingSamples + validationSamples + testSamples;
		
		if (totalDefinedSamples != totalDataSamples) {
			trainingSamples = trainingSamples + (totalDataSamples - totalDefinedSamples);
		}
		
		trainingData = new double[trainingSamples][joinedData[0].length + 1];
		for (int i = 0; i < trainingSamples; i++) {
			trainingData[i] = joinedData[i];
		}
		
		validationData = new double[validationSamples][joinedData[0].length +1];
		for (int j = trainingSamples; j < trainingSamples + validationSamples; j++) {
			validationData[j] = joinedData[j];
		}
		
		testData = new double[testSamples][joinedData[0].length +1];
		for (int k = trainingSamples + validationSamples; k < trainingSamples + validationSamples + testSamples; k++) {
			testData[k] = joinedData[k];
		}
		
	}
	
	public boolean setDataShare(double trainingDataShare, double validationDataShare, double testDataShare) {
		if ((trainingDataShare < 0) || (validationDataShare < 0) || (testDataShare < 0))
			return false;		
		
		if ((trainingDataShare + validationDataShare + testDataShare) != 1)
			return false;
		
		if (trainingDataShare < 0.5)
			return false;
		
		
		this.trainingDataShare = trainingDataShare;
		this.validationDataShare = validationDataShare;
		this.testDataShare = testDataShare;
		SplitData();
		
		return true;
	}
	
	public void SetData(double[][] features, double[] labels) {
		JoinData(features, labels);
		SplitData();
	}
	
	public double[][] getJoinedData()
	{
		return this.joinedData;
	}

	public double[][] getTrainingData() {
		return trainingData;
	}
	
	public double[][] getValidationDataShare() {
		return validationData;
	}
	
	public double[][] getTestData() {
		return testData;
	}
	
}
