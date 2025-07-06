package it.unipv.ingsw.syzygy.excamp.modelController;

import javax.swing.*;
import it.unipv.ingsw.syzygy.excamp.database.dao.StaffProfileDAO;
import it.unipv.ingsw.syzygy.excamp.exceptions.InvalidImageFormatException;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;
import it.unipv.ingsw.syzygy.excamp.modelView.StaffProfileView;

public class StaffProfileController {
    private StaffProfileView profileView;
    private StaffProfileModel staffProfile;
    private StaffProfileDAO staffProfileDAO;

    public StaffProfileController(StaffProfileModel staffProfile, StaffProfileView profileView, StaffProfileDAO staffProfileDAO) {
        this.staffProfile = staffProfile;
        this.profileView = profileView;
        this.staffProfileDAO = staffProfileDAO;

        populateProfileView();

        profileView.getChangeProfileImageButton().addActionListener(e -> changeProfileImage());
        profileView.getChangeExperienceButton().addActionListener(e -> saveExperience());
    }

    private void populateProfileView() {
    	profileView.getNameLabel().setText("Nome: " + staffProfile.getName());
    	profileView.getSurnameLabel().setText("Cognome: " + staffProfile.getSurname());
    	profileView.getAgeLabel().setText("Età: " + staffProfile.getAge());
    	profileView.getPhoneNumberLabel().setText("Telefono: " + staffProfile.getPhoneNumber());
    	profileView.getAccommodationLabel().setText("Alloggio: " + staffProfile.getAccommodation());
    	profileView.getResidenceLabel().setText("Residenza: " + staffProfile.getResidence());
    	profileView.getTransportationLabel().setText("Trasporto: " + staffProfile.getTransportation());
    	profileView.getAllergiesLabel().setText("Allergie: " + staffProfile.getAllergies());
    	profileView.getMedicalIssuesLabel().setText("Problemi medici: " + staffProfile.getMedicalIssues());
    	profileView.getCampLabel().setText("Camp: " + staffProfile.getCamp());
    	profileView.getExperienceTextArea().setText(staffProfile.getExperience());

   
        if (staffProfile.getProfilePicturePath() != null && !staffProfile.getProfilePicturePath().isEmpty()) {
            profileView.getProfileImageLabel().setIcon(new ImageIcon(staffProfile.getProfilePicturePath()));
        } else {
            profileView.getProfileImageLabel().setIcon(new ImageIcon("resources/defaultImage.png"));
        }
    }

    public void saveExperience() {
        String experience = profileView.getExperience();
        staffProfile.setExperience(experience);
        boolean success = staffProfileDAO.updateExperience(staffProfile);
        if (!success) {
            JOptionPane.showMessageDialog(null, "Errore durante l'aggiornamento dell'esperienza.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changeProfileImage() {
        try {
            profileView.changeProfileImage();
            String newImagePath = profileView.getProfileImagePath();
            staffProfile.setProfilePicturePath(newImagePath);
            boolean success = staffProfileDAO.updateProfilePicture(staffProfile);
            if (!success) {
                JOptionPane.showMessageDialog(null, "Errore durante l'aggiornamento dell'immagine del profilo.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        } catch (InvalidImageFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Formato immagine non valido", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void visualizzaProfilo() {
        StaffProfileModel profiloAggiornato = staffProfileDAO.getStaffProfileById(staffProfile.getId());

        if (profiloAggiornato != null) {
            this.staffProfile = profiloAggiornato;
            populateProfileView();
        } else {
            JOptionPane.showMessageDialog(null, "Impossibile caricare il profilo.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
