package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.MenuController;
import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import it.unipv.ingsw.syzygy.excamp.database.dao.UtenteDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.PastiDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.StaffDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.SafeMenu;  
public class SistemaMenuView {
   
   private List<String> opzioniCiboPranzo = new ArrayList<>();
   private List<String> opzioniCiboCena = new ArrayList<>();
   private List<String> pranzoSelezionato = new ArrayList<>();
   private List<String> cenaSelezionata = new ArrayList<>();
  
   
   private MenuController menuController;
   protected int personaId;
   public SistemaMenuView() {
	    try {
	        Connection conn = MySQLConnection.getConnection();
	        menuController = new MenuController(
	            new UtenteDAO(conn),
	            new PastiDAO(conn),
	            new StaffDAO(conn)
	        );

	        List<String> pasti = menuController.pastiDAO.getAllPasti(); 
	        if (!pasti.isEmpty()) {
	        	opzioniCiboPranzo.addAll(pasti);  // Aggiunge dal DB tutte le opzioni per il pranzo
	            opzioniCiboCena.addAll(pasti); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
   
   public void createAndShowGUI() {
       // Creare la finestra principale
       JFrame frame = new JFrame("Sistema Menu");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(2000, 1000);
       
       JPanel mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
     
       JLabel label = new JLabel("Sistema di Creazione Menu");
       label.setFont(new Font("Georgia", Font.PLAIN, 50));
       label.setForeground(new Color(207, 2, 152));
       label.setAlignmentX(Component.CENTER_ALIGNMENT);
       // ComboBox per la selezione del tipo di persona
       JComboBox<String> personaComboBox = new JComboBox<>();
       personaComboBox.addItem("Utente");
       personaComboBox.addItem("Staff");
       personaComboBox.setSelectedItem("Utente");
       personaComboBox.setFont(new Font("Verdana", Font.PLAIN, 20));
       
       // Layout per il pranzo e cena
       JPanel pranzoPanel = new JPanel();
       pranzoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       JLabel pranzoLabel = new JLabel("Scelta Pranzo:");
       pranzoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
       pranzoLabel.setForeground(new Color(177, 33, 159));
       pranzoPanel.add(pranzoLabel);
       pranzoPanel.add(createPranzoButtons(personaComboBox));
       JPanel cenaPanel = new JPanel();
       cenaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
       JLabel cenaLabel = new JLabel("Scelta Cena:");
       cenaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
       cenaLabel.setForeground(new Color(177, 33, 159));
       cenaPanel.add(cenaLabel);
       cenaPanel.add(createCenaButtons(personaComboBox));
       
       // Area di testo per visualizzare il menu
       JTextArea menuArea = new JTextArea(10, 30);
       menuArea.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(menuArea);
      
       JButton creaMenuButton = new JButton("Crea Menu");
       creaMenuButton.setFont(new Font("Courier New", Font.PLAIN, 20));
       creaMenuButton.setForeground(Color.WHITE);
       creaMenuButton.setBackground(new Color(163, 3, 121));
       creaMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
       
       creaMenuButton.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        String personaSelezionata = (String) personaComboBox.getSelectedItem();
    	        String pranzo = String.join(", ", pranzoSelezionato);
    	        String cena = String.join(", ", cenaSelezionata);
    	        String menu = "Menu creato:\n";
    	        
    	        try {
    	            if (personaSelezionata.equals("Utente")) {
    	            	 menuController.creaMenuIniziale(personaId, "user", "password", "CF");
    	            } else {
    	                menuController.creaMenuIniziale(personaId, "staff", "password", "CF");
    	            }
    	            menu += "Pranzo: " + pranzo + "\n";
    	            menu += "Cena: " + cena + "\n";
    	        } catch (SafeMenu ex) {
    	            menu = "Menu alternativo selezionato a causa di allergie!";
    	        } catch (Exception ex) {
    	            menu = "Errore nella creazione del menu: " + ex.getMessage();
    	        }
    	        
    	     
    	        menuArea.setFont(new Font("Courier New", Font.PLAIN, 24));  
    	        menuArea.setText(menu);  // Visualizza il menu aggiornato
    	    }
    	});

       // Aggiunta dei componenti al mainPanel
       mainPanel.add(label);
       mainPanel.add(personaComboBox);
       mainPanel.add(pranzoPanel);
       mainPanel.add(cenaPanel);
       mainPanel.add(creaMenuButton);
       mainPanel.add(scrollPane);
       
       frame.getContentPane().add(mainPanel);
       
       frame.setVisible(true);
   }
   // Questo metodo crea i bottoni per il pranzo (e ne crea fino a 3 per lo staff)
   private JPanel createPranzoButtons(JComboBox<String> personaComboBox) {
       JPanel pranzoButtons = new JPanel();
       pranzoButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
       for (String cibo : opzioniCiboPranzo) {
           JButton button = new JButton(cibo);
           button.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if (personaComboBox.getSelectedItem().equals("Utente")) {
                       pranzoSelezionato.clear();  // Resetta la selezione se è un "Utente"
                       pranzoSelezionato.add(cibo);
                   } else {
                       if (pranzoSelezionato.size() < 3) {
                           pranzoSelezionato.add(cibo);
                       }
                   }
               }
           });
           pranzoButtons.add(button);
       }
       return pranzoButtons;
   }
   // Invece questo metodo crea i bottoni per la cena (e ne crea fino a 3 per lo staff)
   private JPanel createCenaButtons(JComboBox<String> personaComboBox) {
       JPanel cenaButtons = new JPanel();
       cenaButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
       for (String cibo : opzioniCiboCena) {
           JButton button = new JButton(cibo);
           button.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   if (personaComboBox.getSelectedItem().equals("Utente")) {
                       cenaSelezionata.clear();  // Resetta la selezione se è un "Utente"
                       cenaSelezionata.add(cibo);
                   } else {
                       if (cenaSelezionata.size() < 3) {
                           cenaSelezionata.add(cibo);
                       }
                   }
               }
           });
           cenaButtons.add(button);
       }
       return cenaButtons;
   }
   public static void main(String[] args) {
       
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               SistemaMenuView menu = new SistemaMenuView();
               menu.createAndShowGUI();
           }
       });
   }
}
