package machinelearning;

public abstract class DataNormalizer {
	
	public double[][] normalizeData(double[][] data){
		double[][] normalizedData = new double[data.length][data[0].length];
		for (int i = 0; i < data.length; i++) {		
				normalizedData[i] = normalizeSample(data[i]);
		}
		return normalizedData;
	}

	public abstract double[] normalizeSample(double[] sample);

}


