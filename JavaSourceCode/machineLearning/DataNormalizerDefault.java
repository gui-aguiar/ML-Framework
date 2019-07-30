package machinelearning;

public class DataNormalizerDefault extends DataNormalizer {

	@Override
	public double[] normalizeSample(double[] sample) {
		return sample;
	}
	
}
