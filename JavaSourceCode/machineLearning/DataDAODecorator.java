package machinelearning;

public abstract class DataDAODecorator extends DataDAO {

	private static final long serialVersionUID = 1L;

	private DataDAO dataDAO;
	
	public DataDAODecorator(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}

	@Override
	public double[][] load(){
		return loadDecoration(this.dataDAO.load());
	}
	
	public abstract double[][] loadDecoration(double[][] dataLoaded);
}

