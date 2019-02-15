package formsmanagement;

import java.util.ArrayList;
import java.util.Dictionary;

public class MessageHandler {

	MainForm mainForm;
	//private ArrayList<QuestionPanel> questionForms;
	private Dictionary<Integer, QuestionPanel> questionPanels;

	public MessageHandler(MainForm mainForm) {
		// questionPanels = new Dictionary<Integer, QuestionPanel>;
		// cant create because is abstract
		this.mainForm = mainForm;
	}
	
	public int getNumberOfForms(){
		return questionPanels.size();
	}
	
	public void addQuestionForm(QuestionPanel questionPanel) {
		this.questionPanels.put(questionPanel.getIndex(), questionPanel);
	}
	
	public void collectData(QuestionPanel questionPanel) {
		int startDataIndex = 0;
		for (int i = 0; i < questionPanel.getIndex(); i++ ) {
			startDataIndex += questionPanels.get(i).getNumberOfQuestions();
		}
		for (int j = 0; j < questionPanel.getData().length; j++ ) {
			mainForm.getDataToPredict()[startDataIndex + j] = questionPanel.getData()[j];
		}
	}

	public void dataReady() {
		this.mainForm.finish();
	}
	
}
