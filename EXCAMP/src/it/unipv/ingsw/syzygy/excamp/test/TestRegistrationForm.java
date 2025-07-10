package it.unipv.ingsw.syzygy.excamp.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import it.unipv.ingsw.syzygy.excamp.modelDomain.RegistrationFormModel;
import it.unipv.ingsw.syzygy.excamp.modelView.RegistrationFormView;
import it.unipv.ingsw.syzygy.excamp.modelController.RegistrationFormController;
import it.unipv.ingsw.syzygy.excamp.database.dao.RegistrationFormDAO;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.*;

import java.util.Date;

public class TestRegistrationForm {

    private static RegistrationFormModel model;
    private static RegistrationFormView view;
    private static RegistrationFormController controller;
    private static RegistrationFormDAO dao;

    @BeforeClass
    public static void setUp() throws Exception {
    	model = new RegistrationFormModel();
    	controller = new RegistrationFormController(null, model);
    	view = new RegistrationFormView(controller,false);
    	controller.setView(view);
        dao = new RegistrationFormDAO();
    }

    @Test
    public void testValidateFase1() {
        controller.validateFase1(
            "Mario", "Rossi",
            "mario.rossi@example.com", "mario.rossi@example.com",
            "password123", "password123",
            "3331234567"
        );

        assertEquals("Mario", model.getNameGE());
        assertEquals("Rossi", model.getSurnameGE());
        assertEquals("mario.rossi@example.com", model.getUsername());
    }

    @Test
    public void testValidateFase2() {
        controller.validateFase2(
            "Luca", "Verdi",
            new Date(), "Milano", 13,
            "VRDLUC12A01H501Z", "Nessuna", "Asma", "Nulla",
            "M", "Italia", "Via Roma", "10", "20100", "MI"
        );

        assertEquals("Luca", model.getNamePA());
        assertEquals("Verdi", model.getSurnamePA());
        assertEquals("VRDLUC12A01H501Z", model.getCFPA());
    }

    @Test
    public void testSubmitForm() {
        try {
            controller.submitForm(
                Camp.MUSICAL,
                Transportation.BUS,
                Departure.MILANO,
                new Date(),
                "Nessuna nota",
                true, true, true
            );
            assertEquals(Camp.MUSICAL, model.getCamp());
            assertEquals(Transportation.BUS, model.getTransportation());
            assertTrue(model.isAcceptedPrivacy());
        } catch (Exception e) {
            fail("submitForm threw exception: " + e.getMessage());
        }
    }

    @AfterClass
    public static void tearDown() {
        dao.closeConnection();
    }
}
