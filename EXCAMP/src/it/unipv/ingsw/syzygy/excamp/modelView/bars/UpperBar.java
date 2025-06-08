package it.unipv.ingsw.syzygy.excamp.modelView.bars;

import javax.swing.*;
import it.unipv.ingsw.syzygy.excamp.database.dao.StaffProfileDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;
import it.unipv.ingsw.syzygy.excamp.modelView.ProfileView;
import it.unipv.ingsw.syzygy.excamp.modelView.StaffProfileView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class UpperBar extends JPanel{
   private StaffProfileDAO staffProfileDAO;
   // variabile che memorizza l'unica istanza
   private static UpperBar instance;
   private JTextField usernameField;
   private JButton profileButton;
   private JButton contattiSegreteriaButton;
   private JButton feedbackButton;
   private JButton organizzazioneButton;
   private JButton bachecaButton;
   // costruttore privato per singleton
   private UpperBar() {
       
	   try {
		    Connection conn = MySQLConnection.getConnection();
		    staffProfileDAO = new StaffProfileDAO(conn);
		} catch (SQLException e) {
		    e.printStackTrace(); 
		}
	   
       setLayout(new FlowLayout(FlowLayout.LEFT));
       setBackground(new Color(213, 0, 109));  
      
       JLabel title = new JLabel("Experience Summer Camp");
       title.setFont(new Font("Berlin Sans FB", Font.PLAIN, 40));
       title.setForeground(Color.WHITE);
       // Logo (icona)
       ImageIcon iconLogo = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/logo.png"));
       JLabel logoLabel = new JLabel(iconLogo);
       logoLabel.setPreferredSize(new Dimension(60, 60));
       // Pulsante profilo
       profileButton = new JButton();
       profileButton.setBackground(new Color(213, 0, 109));
       ImageIcon profileIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/user.png"));
       profileButton.setIcon(new ImageIcon(profileIcon.getImage().getScaledInstance(29, 25, Image.SCALE_SMOOTH)));
       // Pulsante contatti segreteria
       contattiSegreteriaButton = new JButton();
       contattiSegreteriaButton.setBackground(new Color(213, 0, 109));
       ImageIcon contattiSegreteriaIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/segreteria.png"));
       contattiSegreteriaButton.setIcon(new ImageIcon(contattiSegreteriaIcon.getImage().getScaledInstance(29, 25, Image.SCALE_SMOOTH)));
       // Pulsante feedback
       feedbackButton = new JButton();
       feedbackButton.setBackground(new Color(213, 0, 109));
       ImageIcon feedbackIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/feedback.png"));
       feedbackButton.setIcon(new ImageIcon(feedbackIcon.getImage().getScaledInstance(29, 25, Image.SCALE_SMOOTH)));
       // Pulsante organizzazione
       organizzazioneButton = new JButton();
       organizzazioneButton.setBackground(new Color(213, 0, 109));
       ImageIcon organizzazioneIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/organizzazione.png"));
       organizzazioneButton.setIcon(new ImageIcon(organizzazioneIcon.getImage().getScaledInstance(29, 25, Image.SCALE_SMOOTH)));
       // Pulsante bacheca
       bachecaButton = new JButton();
       bachecaButton.setBackground(new Color(213, 0, 109));
       ImageIcon bachecaIcon = new ImageIcon(getClass().getResource("/it/unipv/ingsw/syzygy/excamp/modelView/imagesResources/bacheca.png"));
       bachecaButton.setIcon(new ImageIcon(bachecaIcon.getImage().getScaledInstance(29, 25, Image.SCALE_SMOOTH)));

       JPanel spacer = new JPanel();
       spacer.setPreferredSize(new Dimension(200, 60));  
      
       add(title);
       add(logoLabel);
       add(spacer);
       add(organizzazioneButton);
       add(bachecaButton);
       add(feedbackButton);
       add(contattiSegreteriaButton);
       add(profileButton);

       contattiSegreteriaButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               mostraContattiSegreteria();
           }
       });
   
       profileButton.addActionListener(new ActionListener() {
       	@Override
       	public void actionPerformed(ActionEvent e) {
       		// Qui si gestisce l'apertura del profilo
       		openProfileDialog();
       	}
       });
   }

   public static UpperBar getInstance() {
       if (instance == null) {
           instance = new UpperBar();
       }
       return instance;
   }
   // Metodi per ottenere i pulsanti
   public JButton getProfileButton() {
       return profileButton;
   }
   public JButton getContattiSegreteriaButton() {
       return contattiSegreteriaButton;
   }
   public JButton getFeedbackButton() {
       return feedbackButton;
   }
   public JButton getOrganizzazioneButton() {
       return organizzazioneButton;
   }
   public JButton getBachecaButton() {
       return bachecaButton;
   }
   // Impostazioni per l'ospite
   public void setForOspite() {
       profileButton.setVisible(true);
       contattiSegreteriaButton.setVisible(true);
       feedbackButton.setVisible(true);
       organizzazioneButton.setVisible(false);
       bachecaButton.setVisible(false);
   }
   // Impostazioni per l'utente
   public void setForUtente() {
       profileButton.setVisible(true);
       contattiSegreteriaButton.setVisible(true);
       feedbackButton.setVisible(true);
       organizzazioneButton.setVisible(true);
       bachecaButton.setVisible(true);
   }
   // Impostazioni per lo staff
   public void setForStaff() {
       profileButton.setVisible(true);
       contattiSegreteriaButton.setVisible(true);
       feedbackButton.setVisible(true);
       organizzazioneButton.setVisible(true);
       bachecaButton.setVisible(true);
   }
   // Impostazioni per l'amministratore
   public void setForAmministratore() {
       profileButton.setVisible(true);
       contattiSegreteriaButton.setVisible(true);
       feedbackButton.setVisible(true);
       organizzazioneButton.setVisible(true);
       bachecaButton.setVisible(true);
   }
   // Funzione per mostrare i contatti segreteria
   private void mostraContattiSegreteria() {
       // Mostra una finestra di dialogo con i contatti
       JOptionPane.showMessageDialog(this,
           "Email: info@xpcamp.it\nTelefono: +39 0331 33 37 24",
           "Contatti Segreteria",
           JOptionPane.INFORMATION_MESSAGE);
   }
   private void openProfileDialog() {
	    // Recupera il nome utente (ad esempio, dalla sessione o dal contesto)
//       String username = usernameField.getText(); // 
      
		String idText = usernameField.getText().trim();
	    int id;
	    try {
	        id = Integer.parseInt(idText);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Inserisci un ID numerico valido.", "Errore", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Recupera i dati del profilo dal DAO
	    StaffProfileModel staffProfile = staffProfileDAO.getStaffProfileById(id); // 'dao' è il tuo oggetto DAO
	
	    // Crea la vista del profilo 
	    StaffProfileView profileView = new StaffProfileView(staffProfile);
	
	    // Crea il JDialog per visualizzare la vista del profilo
	    JDialog profileDialog = new JDialog();
	    profileDialog.setTitle("Profilo dello Staff");
	    profileDialog.setModal(true); 
	    profileDialog.setSize(500, 400); 
	    profileDialog.setLocationRelativeTo(null); 
	
	    // Aggiungi la vista del profilo al JDialog
	    profileDialog.add(profileView);
	   
	    // Mostra il JDialog
	    profileDialog.setVisible(true);
	}
	
	
	public StaffProfileModel getStaffProfile(int id) {
	   
	    return staffProfileDAO.getStaffProfileById(id); 
	}
	public void setForNoLogged() {
		
	}
}

