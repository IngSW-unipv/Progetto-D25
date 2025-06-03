package it.unipv.ingsw.syzygy.excamp.modelView;

import it.unipv.ingsw.syzygy.excamp.modelView.bars.LowerBar;
import it.unipv.ingsw.syzygy.excamp.modelView.bars.UpperBar;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LoginView extends JPanel {

   private JButton loginButton;
   private JButton regButton;
   private JPanel layout;
   private JPanel grid;
   private JRadioButton utenteRadioButton;
   private JRadioButton staffRadioButton;
   private JRadioButton amministratoreRadioButton;
   private LowerBar lowerBar;
   private UpperBar upperBar;
   private JLabel usernameLabel;
   private JTextField usernameField;
   private JLabel passwordLabel;
   private JPasswordField passwordField;
   private JLabel signupLabel;
   private JLabel errorLabel;
   private ButtonGroup accountTypeToggleGroup;
   public LoginView() {
       super();
       initComponents();
   }
   public void initComponents() {
       layout = new JPanel();
       layout.setLayout(new BorderLayout());
       layout.setBackground(new Color(145, 186, 214));  // Colore simile a #91bad6
       grid = new JPanel();
       grid.setLayout(new GridBagLayout());
       grid.setBorder(new EmptyBorder(20, 20, 20, 20));
       lowerBar = LowerBar.getInstance();
       upperBar = UpperBar.getInstance();
       upperBar.setForOspite();
       layout.add(lowerBar, BorderLayout.SOUTH);
       layout.add(upperBar, BorderLayout.NORTH);
       layout.add(grid, BorderLayout.CENTER);
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10); 
       // Imposto campo username
       usernameLabel = new JLabel("Username:");
       usernameLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
       gbc.gridx = 0;
       gbc.gridy = 1;
       grid.add(usernameLabel, gbc);
       usernameField = new JTextField();
       gbc.gridx = 1;
       grid.add(usernameField, gbc);
       // Imposto campo password
       passwordLabel = new JLabel("Password:");
       passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
       gbc.gridx = 0;
       gbc.gridy = 2;
       grid.add(passwordLabel, gbc);
       passwordField = new JPasswordField();
       gbc.gridx = 1;
       grid.add(passwordField, gbc);
       // SignUp label
       signupLabel = new JLabel("Non sei ancora iscritto? ");
       signupLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
       gbc.gridx = 0;
       gbc.gridy = 6;
       grid.add(signupLabel, gbc);
       accountTypeToggleGroup = new ButtonGroup();
       // RadioButton per l'utente
       utenteRadioButton = new JRadioButton("Genitore");
       utenteRadioButton.setFont(new Font("Helvetica", Font.BOLD, 14));
       accountTypeToggleGroup.add(utenteRadioButton);
       utenteRadioButton.setSelected(true);   // Utente selezionato come di default
       gbc.gridx = 0;
       gbc.gridy = 0;
       grid.add(utenteRadioButton, gbc);
       // RadioButton per lo staff
       staffRadioButton = new JRadioButton("Staff");
       staffRadioButton.setFont(new Font("Helvetica", Font.BOLD, 14));
       accountTypeToggleGroup.add(staffRadioButton);
       gbc.gridx = 1;
       grid.add(staffRadioButton, gbc);
       // RadioButton per l'amministratore
       amministratoreRadioButton = new JRadioButton("Amministratore");
       amministratoreRadioButton.setFont(new Font("Helvetica", Font.BOLD, 14));
       accountTypeToggleGroup.add(amministratoreRadioButton);
       gbc.gridx = 2;
       grid.add(amministratoreRadioButton, gbc);
       loginButton = new JButton("Login");
       gbc.gridx = 1;
       gbc.gridy = 5;
       grid.add(loginButton, gbc);
       regButton = new JButton("Registrati");
       gbc.gridy = 6;
       grid.add(regButton, gbc);
       errorLabel = new JLabel();
       errorLabel.setForeground(Color.RED);
       errorLabel.setFont(new Font("Helvetica", Font.BOLD, 14));
       gbc.gridx = 0;
       gbc.gridy = 9;
       grid.add(errorLabel, gbc);
       errorLabel.setVisible(false);
      utenteRadioButton.addActionListener(e -> {
		    if (utenteRadioButton.isSelected()) {
		        errorLabel.setVisible(false);
		        usernameField.setText("");
		        passwordField.setText("");
		    }
		});
		staffRadioButton.addActionListener(e -> {
		    if (staffRadioButton.isSelected()) {
		        errorLabel.setVisible(false);
		        usernameField.setText("staff.experience");
		        passwordField.setText("");
		    }
		});
	
		amministratoreRadioButton.addActionListener(e -> {
		    if (amministratoreRadioButton.isSelected()) {
		        errorLabel.setVisible(false);
		        usernameField.setText("amministratore.experience");
		        passwordField.setText("");
		    }
		});
   }
   public JRadioButton getUtenteRadioButton() {
       return utenteRadioButton;
   }
   public JRadioButton getStaffRadioButton() {
       return staffRadioButton;
   }
   public JRadioButton getAmministratoreRadioButton() {
       return amministratoreRadioButton;
   }
   public JButton getLoginButton() {
       return loginButton;
   }
   public JButton getRegButton() {
       return regButton;
   }
   public JTextField getUsername() {
       return usernameField;
   }
   public JPasswordField getPassword() {
       return passwordField;
   }
   public void setErrorLabel(String message) {
       errorLabel.setText(message);
       errorLabel.setVisible(true);
   }
   public boolean checkEmptyFields() {
       return usernameField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty();
   }
   public LayoutManager getLayout() {
       return (LayoutManager) layout;
   }
}

