package machinelearning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classifier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double[][] normalizedFeatures;
	
	private ArrayList<Algorithm> algorithms;
	private Algorithm currentAlgorithm;     
	private DataDAO dataDAO;
	private DataNormalizer normalizer;	

	public Classifier() {
		algorithms = new  ArrayList<>();
		createDataDAO();
		createNormalizer();
	}

	private void createDataDAO() {
		throw new UnsupportedOperationException(); 	// TODO: Edit to create the proper DataDAO 
	}

	private void createNormalizer() {
		normalizer = new DataNormalizerDefault(); // TODO: Edit to create the proper DataNormalizer; 		
	}		
	
	public List<Algorithm> getAlgorithms() {
		return algorithms;
	}
	
	public void setCurrentAlgorithm(int currentAlgorithm) {
		this.currentAlgorithm = this.algorithms.get(currentAlgorithm);
	}

	public Algorithm getCurrentAlgorithm() {
		return this.currentAlgorithm;
	}

	private void saveAlgorithms() {
		for(Algorithm algorithm: algorithms){
    		algorithm.save();
    	}
	}

    public boolean train(int featuresSize) {
    	boolean fitResult = true;
    	loadData(featuresSize);
    	for(Algorithm algorithm: algorithms){
    		fitResult = algorithm.fit(normalizedFeatures, dataDAO.getLabels()) && fitResult;
    	}
    	setCurrentAlgorithm(0);
    	return fitResult;
	}
    
	private void loadData(int featuresSize) {		
		if (dataDAO.load()[0].length != featuresSize) {
			throw new Error("Provided data doesn match with the appications configuratiosns.");
		}
		normalizedFeatures = normalizer.normalizeData(dataDAO.getFeatures());
	}
	
	public void load() {
		for(Algorithm algorithm: algorithms){
    		algorithm.read();
    	}	
	}

	public double classify(double[] features) {
	  return currentAlgorithm.predict(features);
	}
	
	public void close() {
		this.saveAlgorithms();
	}

}