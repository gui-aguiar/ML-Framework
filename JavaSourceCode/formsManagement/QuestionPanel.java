package formsmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;

public abstract class QuestionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	static final String PREVIOUS_STRING = "Previous";
	static final String NEXT_STRING = "Next";
	
	private CardLayout cardLayout;
	private JButton buttonPrevious;
	private JButton buttonNext;
	
	private MainForm mainForm;
	private int numberOfQuestions;
	private boolean isFirstPanel;
	private boolean isLastPanel;
	private double[] data;
	private int index;
	
	public QuestionPanel(MainForm mainForm, int index, int numberOfQuestions, boolean isFirstPanel, boolean isLastPanel) {
		this.mainForm = mainForm;
		this.index = index;
		setIsFirstPanel(isFirstPanel);
		this.isLastPanel = isLastPanel;
		this.numberOfQuestions = numberOfQuestions;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	public boolean getIsFirstPanel() {
		return isFirstPanel;
	}

	public void setIsFirstPanel(boolean isFirstPanel) {
		this.isFirstPanel = isFirstPanel;
		buttonPrevious.setVisible(!isFirstPanel);
	}

	public boolean getIsLastPanel() {
		return isLastPanel;
	}

	public void setIsLastPanel(boolean isLastPanel) {
		this.isLastPanel = isLastPanel;
	}
	
	public QuestionPanel() { 
		initializeButtons();
	}

	protected void initializeButtons() {		
		setLayout(null);
		
		class ControlButtonsActionListenter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                cardLayout = (CardLayout) (getParent().getLayout());
                String cmd = e.getActionCommand();
                if (cmd.equals(PREVIOUS_STRING)) {
                	previousPage();
                }
                else if (cmd.equals(NEXT_STRING)) {
                	nextPage();
                }
            }
        }
        ControlButtonsActionListenter controlButtons = new ControlButtonsActionListenter();		
		
		buttonPrevious = new JButton(PREVIOUS_STRING);
		buttonPrevious.setBounds(10, 470, 70, 22);
		buttonPrevious.addActionListener(controlButtons);
		buttonPrevious.setActionCommand(PREVIOUS_STRING);
		add(buttonPrevious);
		buttonPrevious.setVisible(!isFirstPanel);
		
		buttonNext = new JButton(NEXT_STRING);
		buttonNext.setBounds(560, 470, 70, 22);
		buttonNext.addActionListener(controlButtons);
		buttonNext.setActionCommand(NEXT_STRING);
		add(buttonNext);
	}

	protected void previousPage() {
		cardLayout.previous(getParent());
	}
	
	protected void nextPage() {
		if (!checkAnswerRules()) {
			JOptionPane.showMessageDialog(this, "The form was not filled properly", 
				    "Atenção", JOptionPane.WARNING_MESSAGE);
		} else{
			collectData();
		    if (!this.isLastPanel) {
		    	cardLayout.next(getParent());
		    } else {
		    	mainForm.dataReady();
		    }
	    }	     
	}
	
	private void collectData() {
		collectFormData();
		mainForm.collectData(this);
	}
	
	protected abstract void collectFormData();

	protected abstract boolean checkAnswerRules();
	
	
	
}