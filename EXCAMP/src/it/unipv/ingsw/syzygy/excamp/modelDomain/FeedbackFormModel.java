package it.unipv.ingsw.syzygy.excamp.modelDomain;

import it.unipv.ingsw.syzygy.excamp.modelController.FeedbackFormController;

public class FeedbackFormModel {
	private int userId;
	private int stelle;
	private String attivitaLiked;
	private String attivitaDisliked;
	private int votoMenu;
	private int votoAlloggio;
	private int votoTrasporti;
	private String commenti;
	
	
	public FeedbackFormModel(int userId, int stelle, String attivitaLiked, String attivitaDisliked,
							 int votoMenu, int votoAlloggio, int votoTrasporti, String commenti) {
		this.userId = userId;
		this.stelle = stelle;
		this.attivitaLiked = attivitaLiked;
		this.attivitaDisliked = attivitaDisliked;
		this.votoMenu = votoMenu;
		this.votoAlloggio = votoAlloggio;
		this.votoTrasporti = votoTrasporti;
		this.commenti = commenti;
	}
	public static void beginFeedbackComp(int userId) {
		try {
			new FeedbackFormController(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Getters e Setters
	public int getStelle() {
		return stelle;
	}
	
	public void setStelle(int stelle) {
		this.stelle = stelle;
	}
	
	public String getAttivitaLiked() {
		return attivitaLiked;
	}
	
	public void setAttivitaLiked(String attivitaLiked) {
		this.attivitaLiked = attivitaLiked;
	}
	
	public String getAttivitaDisliked() {
		return attivitaDisliked;
	}
	
	public void setAttivitaDisliked(String attivitaDisliked) {
		this.attivitaDisliked = attivitaDisliked;
	}
	
	public int getVotoMenu() {
		return votoMenu;
	}
	
	public void setVotoMenu(int votoMenu) {
		this.votoMenu = votoMenu;
	}
	
	public int getVotoAlloggio() {
		return votoAlloggio;
	}
	
	public void setVotoAlloggio(int votoAlloggio) {
		this.votoAlloggio = votoAlloggio;
	}
	
	public int getVotoTrasporti() {
		return votoTrasporti;
	}
	
	public void setVotoTrasporti(int votoTrasporti) {
		this.votoTrasporti = votoTrasporti;
	}
	
	public String getCommenti() {
		return commenti;
	}
	
	public void setCommenti(String commenti) {
		this.commenti = commenti;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
