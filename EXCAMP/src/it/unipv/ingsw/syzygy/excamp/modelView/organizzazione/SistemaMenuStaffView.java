package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import it.unipv.ingsw.syzygy.excamp.modelController.organizzazione.MenuController;
import it.unipv.ingsw.syzygy.excamp.database.MySQLConnection;
import it.unipv.ingsw.syzygy.excamp.database.dao.UtenteDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.PastiDAO;
import it.unipv.ingsw.syzygy.excamp.database.dao.organizzazione.StaffDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.MissingMenuChoiceException;  // Importa l'eccezione
public class SistemaMenuStaffView {
   protected List<String> pranzoSelezionato;
   protected List<String> cenaSelezionata;
   private MenuController menuController;
   public SistemaMenuStaffView() {
	    try {
	        Connection conn = MySQLConnection.getConnection();
	        menuController = new MenuController(
	            new UtenteDAO(conn),
	            new PastiDAO(conn),
	            new StaffDAO(conn)
	        );
	        pranzoSelezionato = menuController.getPranzoOpzioniStaff();
	        cenaSelezionata = menuController.getCenaOpzioniStaff();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

   public void createAndShowGUI() {
       // Creazione della finestra principale
	   
	   JTextField usernameField = new JTextField(20);
	   usernameField.setMaximumSize(new Dimension(200, 25));
       JTextField cfField = new JTextField(20);
       cfField.setMaximumSize(new Dimension(200, 25));
       
       
       JFrame frame = new JFrame("Scelta Pasti Staff");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(2000, 1000);
 
       JPanel panel = new JPanel();
       panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       
       panel.add(new JLabel("Username Staff:"));
       panel.add(usernameField);
       panel.add(new JLabel("Codice Fiscale Staff:"));
       panel.add(cfField);
      
       panel.add(Box.createRigidArea(new Dimension(0, 5)));
       
       // ComboBox per la scelta del pranzo
       JComboBox<String> pranzoComboBox = new JComboBox<>();
       for (String pranzo : pranzoSelezionato) {
           pranzoComboBox.addItem(pranzo);
       }
       
       pranzoComboBox.setSelectedItem(pranzoSelezionato.get(0));
       pranzoComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
       pranzoComboBox.setMaximumSize(new Dimension(200, 40)); 
       panel.add(pranzoComboBox);
       
       panel.add(Box.createRigidArea(new Dimension(0, 15))); // spazio tra i due combo

       // ComboBox per la scelta della cena
       JComboBox<String> cenaComboBox = new JComboBox<>();
       for (String cena : cenaSelezionata) {
           cenaComboBox.addItem(cena);
       }
       cenaComboBox.setSelectedItem(cenaSelezionata.get(0));
       cenaComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
       
          cenaComboBox.setSelectedItem(cenaSelezionata.get(0));
       cenaComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
       cenaComboBox.setMaximumSize(new Dimension(200, 40));
       panel.add(cenaComboBox);
       
       // Area di testo per mostrare il menu finale
       JTextArea menuText = new JTextArea(5, 20);
       menuText.setEditable(false);
       menuText.setFont(new Font("Courier New", Font.PLAIN, 16));
       JScrollPane scrollPane = new JScrollPane(menuText);
       
       panel.add(Box.createRigidArea(new Dimension(0, 15)));
       
       // Pulsante per confermare la selezione
       JButton confermaButton = new JButton("Conferma Selezione");
       confermaButton.setFont(new Font("Courier New", Font.PLAIN, 20));
       confermaButton.setBackground(new Color(163, 3, 121));
       confermaButton.setForeground(Color.WHITE);
       // Tale azione permette di confermare la selezione e visualizzare il menu
       confermaButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
        	   
        	   String usernameST = usernameField.getText();
        	   String CFST = cfField.getText();
        	   
               String pastoPranzo = (String) pranzoComboBox.getSelectedItem();
               String pastoCena = (String) cenaComboBox.getSelectedItem();
               try {
                   
                   menuController.salvaScelteStaff(usernameST, CFST, pastoPranzo, pastoCena);
                   
                   menuText.setText("MENU SELEZIONATO:\nPranzo: " + pastoPranzo + "\nCena: " + pastoCena);
               } catch (MissingMenuChoiceException ex) {
                   
                   JOptionPane.showMessageDialog(frame, "Errore: Devi selezionare un pranzo e una cena!",
                                                 "Errore", JOptionPane.ERROR_MESSAGE);
               }
           }
       });
       // Aggiunta dei componenti al layout
       panel.add(new JLabel("Seleziona il pranzo:"));
       panel.add(pranzoComboBox);
       panel.add(new JLabel("Seleziona la cena:"));
       panel.add(cenaComboBox);
       panel.add(Box.createRigidArea(new Dimension(0, 15)));
       panel.add(confermaButton);
       panel.add(scrollPane);
  
       frame.getContentPane().add(panel);
       
       frame.setVisible(true);
   }
   public static void main(String[] args) {
       
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               SistemaMenuStaffView menuStaff = new SistemaMenuStaffView();
               menuStaff.createAndShowGUI();
           }
       });
   }
}
