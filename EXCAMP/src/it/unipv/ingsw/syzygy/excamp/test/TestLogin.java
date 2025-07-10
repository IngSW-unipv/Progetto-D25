package it.unipv.ingsw.syzygy.excamp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unipv.ingsw.syzygy.excamp.modelView.LoginView;

import org.junit.BeforeClass;

import javax.swing.*;

public class TestLogin {

    private LoginView loginView;

    @BeforeClass
    public void setUp() {
        loginView = new LoginView();
    }

    @Test
    public void testComponentsAreNotNull() {
        assertNotNull(loginView.getLoginButton());
        assertNotNull(loginView.getRegButton());
        assertNotNull(loginView.getUsername());
        assertNotNull(loginView.getPassword());
        assertNotNull(loginView.getUtenteRadioButton());
        assertNotNull(loginView.getStaffRadioButton());
        assertNotNull(loginView.getAmministratoreRadioButton());
    }

    @Test
    public void testDefaultSelectedRadioButton() {
        assertTrue("Genitore dovrebbe essere selezionato di default", loginView.getUtenteRadioButton().isSelected());
        assertFalse(loginView.getStaffRadioButton().isSelected());
        assertFalse(loginView.getAmministratoreRadioButton().isSelected());
    }

    @Test
    public void testStaffRadioButtonBehavior() {
        loginView.getStaffRadioButton().doClick();
        assertEquals("staff.experience", loginView.getUsername().getText());
        assertEquals("", new String(loginView.getPassword().getPassword()));
    }

    @Test
    public void testAmministratoreRadioButtonBehavior() {
        loginView.getAmministratoreRadioButton().doClick();
        assertEquals("amministratore.experience", loginView.getUsername().getText());
        assertEquals("", new String(loginView.getPassword().getPassword()));
    }

    @Test
    public void testUtenteRadioButtonClearsFields() {
        loginView.getUsername().setText("testUser");
        loginView.getPassword().setText("testPass");
        loginView.getUtenteRadioButton().doClick();
        assertEquals("", loginView.getUsername().getText());
        assertEquals("", new String(loginView.getPassword().getPassword()));
    }

    @Test
    public void testCheckEmptyFields() {
        loginView.getUsername().setText("");
        loginView.getPassword().setText("");
        assertTrue("Dovrebbe essere true se entrambi i campi sono vuoti", loginView.checkEmptyFields());

        loginView.getUsername().setText("user");
        loginView.getPassword().setText("");
        assertTrue("Dovrebbe essere true se la password è vuota", loginView.checkEmptyFields());

        loginView.getUsername().setText("");
        loginView.getPassword().setText("pass");
        assertTrue("Dovrebbe essere true se lo username è vuoto", loginView.checkEmptyFields());

        loginView.getUsername().setText("user");
        loginView.getPassword().setText("pass");
        assertFalse("Dovrebbe essere false se entrambi i campi sono riempiti", loginView.checkEmptyFields());
    }

    @Test
    public void testSetErrorLabel() {
        loginView.setErrorLabel("Errore di test");
        JLabel errorLabel = (JLabel) loginView.getComponent(0).getComponentAt(0, 0); 
        assertTrue(errorLabel.isVisible());
    }
}


