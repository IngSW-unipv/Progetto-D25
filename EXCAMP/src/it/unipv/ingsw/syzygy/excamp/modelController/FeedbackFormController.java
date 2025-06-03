package it.unipv.ingsw.syzygy.excamp.modelController;

import it.unipv.ingsw.syzygy.excamp.database.dao.FeedbackFormDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.FeedbackFormModel;
import it.unipv.ingsw.syzygy.excamp.modelView.FeedbackFormView;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FeedbackFormController {
	private FeedbackFormView view;
	private FeedbackFormDAO dao;
	private int userId; 
	
	public FeedbackFormController(int userId) throws SQLException {
		this.userId = userId;
		this.view = new FeedbackFormView(this, userId);
		this.dao = new FeedbackFormDAO();
	}
	
	public void processFeedback() {
		FeedbackFormModel feedback = new FeedbackFormModel(userId, view.getSelectedStars(), view.getActivityFromComboBox(view.getActivityLikedPanel()),
				view.getActivityFromComboBox(view.getActivityDislikedPanel()), view.getSliderValue(view.getMenuPanel()),
				view.getSliderValue(view.getAlloggioPanel()), view.getSliderValue(view.getTrasportiPanel()),
				view.getComments());
		
		dao.saveFeedback(feedback);
		JOptionPane.showMessageDialog(view, "Feedback inviato con successo!");
	}
	
	public int getCurrentUserId() {
		return userId;
	}
}
