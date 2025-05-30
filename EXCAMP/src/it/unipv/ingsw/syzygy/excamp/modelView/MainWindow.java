package it.unipv.ingsw.syzygy.excamp.modelView;

import javax.swing.*;

import it.unipv.ingsw.syzygy.excamp.modelController.RegistrationFormController;
import it.unipv.ingsw.syzygy.excamp.modelDomain.RegistrationFormModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;
import it.unipv.ingsw.syzygy.excamp.modelView.payment.PaymentSelectionView;

import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private RegistrationFormController registrationFormController;
    private PaymentSelectionView paymentSelectionView;
    private ProfileView profileView;
    
    public MainWindow() {
    	
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        RegistrationFormModel registrationModel = new RegistrationFormModel();

        registrationFormController = new RegistrationFormController(null, registrationModel);

        RegistrationFormView registrationView = new RegistrationFormView(registrationFormController);

        registrationView.setMainWindow(this);
        registrationFormController.setView(registrationView); 

        paymentSelectionView = new PaymentSelectionView();

        mainPanel.add("registrationForm", registrationView);
        mainPanel.add("paymentSelection", paymentSelectionView);

        this.add(mainPanel);

        setTitle("Applicazione");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void showPaymentSelection() {
        System.out.println("Cambio a schermata pagamento");
        System.out.println("PaymentSelectionView: " + paymentSelectionView);
        cardLayout.show(mainPanel, "paymentSelection");
    }

    public void showRegistrationForm() {
        cardLayout.show(mainPanel, "registrationForm");
    }  

    public static void main(String[] args) {
        // Crea e mostra la finestra principale
        MainWindow window = new MainWindow();
        window.setVisible(true);
        
    }

	public RegistrationFormController getRegistrationFormController() {
		return registrationFormController;
	}

	public void setRegistrationFormController(RegistrationFormController registrationFormController) {
		this.registrationFormController = registrationFormController;
	}
	
}
