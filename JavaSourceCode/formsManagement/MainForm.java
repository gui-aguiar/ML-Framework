package formsmanagement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

import machinelearning.Application;

public class MainForm extends JFrame{

	private Panel mainPanel;
	private Panel questionPanelsPane;
	private JTextArea intructions;
	private Label intructionsTitle;
	private Button button;
	
	private int numberOfQuestionForms;
	private int numberOfQuestions;
	private double[] dataToPredict;
	
	private Application application;
	
	private MessageHandler formsManager;
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new MainForm();
	}
	
	public MainForm () {
		createFormsManager();
		configureCloseAction();
		defineDataInfo();
		setInstrucionsPanelComponents();
		setQuestionPanelsComponents();
		createQuestionPanels();	
		checkNumberOfPages();			
		runApplication();		
	}

	private void createFormsManager() {
		formsManager = new MessageHandler(this);		
	}

	private void configureCloseAction() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		       application.close();		         // TODO: This can be edited to perform another actions 
		    }
		});
	}
		
	private void defineDataInfo() {
		this.setNumberOfQuestionForms(10);
		this.setNumberOfQuestions(60);
		dataToPredict = new double[this.getNumberOfQuestions()];
	}

	private void setFormInfo() {
		this.setInstructionsTitle("Aplication");
		this.setInstructions("Set your instrucions  here");
	}

	private void runApplication() {
		this.application = new Application(this.getNumberOfQuestions());		
		this.application.setNumberOfQuestionForms(this.getNumberOfQuestionForms());
	}

	public double[] getDataToPredict() {
		return dataToPredict;
	}

	public void setDataToPredict(double[] dataToPredict) {
		this.dataToPredict = dataToPredict;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public int getNumberOfQuestionForms() {
		return numberOfQuestionForms;
	}

	public void setNumberOfQuestionForms(int numberOfQuestionForms) {
		this.numberOfQuestionForms = numberOfQuestionForms;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	protected void setInstrucionsPanelComponents() {  // TODO - User can create another instruction panel
		this.setVisible(true);
		this.setBounds(100, 100, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		createInstructionsPanelComponents();		
		configureInstructionsPanelComponents();		
		setFormInfo();
	}

	private void configureInstructionsPanelComponents() {
		mainPanel.setBounds(10, 10, 680, 590);
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);		
		
		intructionsTitle.setAlignment(Label.CENTER);
		intructionsTitle.setFont(new Font("Arial", Font.BOLD, 22));
		intructionsTitle.setBounds(182, 35, 251, 66);
		mainPanel.add(intructionsTitle);		
		
		intructions.setLineWrap(true);
		intructions.setFont(new Font("Tahoma", Font.PLAIN, 11));
		intructions.setEditable(false);
		intructions.setBounds(10, 153, 594, 206);
		intructions.setOpaque(false);  
	    mainPanel.add(intructions);		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTest();
			}
		});
		
		button.setBounds(273, 500, 70, 22);
		mainPanel.add(button);
		this.repaint();
	}

	private void createInstructionsPanelComponents() {
		mainPanel = new Panel();
		intructionsTitle = new Label();
		intructions = new JTextArea();
		button = new Button("Iniciar");
	}
	
	private void setQuestionPanelsComponents() { // TODO: This can be edited
		questionPanelsPane = new Panel();
		questionPanelsPane.setVisible(false);
		questionPanelsPane.setBounds(10, 10, 640, 590);
		getContentPane().add(questionPanelsPane);
		questionPanelsPane.setLayout(new CardLayout(0, 0));
	}	

	private void createQuestionPanels() {
		for (int i = 0; i < this.numberOfQuestionForms; i++ ) {
			QuestionPanel questionPanel = createQuestionPanel(i, i==0, i==(this.numberOfQuestionForms-1));
			questionPanelsPane.add(questionPanel);
			formsManager.addQuestionForm(questionPanel);
		}
	}

	private void checkNumberOfPages() {
		if (this.numberOfQuestionForms != formsManager.getNumberOfForms()) {
			JOptionPane.showMessageDialog(this, "The number of pages configured doesnt match the configured",
				    "Atenção", JOptionPane.ERROR_MESSAGE);
			throw new UnsupportedOperationException("The number of pages configured doesnt match the configured");
		}
	}
	
	protected void startTest() {
		if (application.getOperationMode() instanceof  machinelearning.Learn ) {
			application.train();
		} 

	    mainPanel.setVisible(false);
	    questionPanelsPane.setVisible(true);				
	}
		
	public void setInstructions(String text) {
		intructions.setText(text);		
	}
	
	public void setInstructionsTitle(String text) {
		intructionsTitle.setText(text);		
	}
	
	public void finish() {
		double prediction = application.classify(dataToPredict);
		setFinishMessage(prediction);
	}

	private void setFinishMessage(double prediction) {
		// TODO: Create the message report for the users;
	}
	
	private QuestionPanel createQuestionPanel(int index, boolean isFirstPanel, boolean isLastPanel) {
		// TODO createAndConfigure a QuestionPanel subclass
		return null;
	}
		
}