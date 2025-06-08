package it.unipv.ingsw.syzygy.excamp.modelView;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.File;
import it.unipv.ingsw.syzygy.excamp.exceptions.InvalidImageFormatException;
import it.unipv.ingsw.syzygy.excamp.modelDomain.StaffProfileModel;

public class StaffProfileView extends JPanel {

    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel ageLabel;
    private JLabel phoneLabel;
    private JLabel accommodationLabel;
    private JLabel residenceLabel;
    private JLabel transportationLabel;
    private JLabel allergiesLabel;
    private JLabel medicalIssuesLabel;
    private JLabel campLabel; 

    private StaffProfileModel staffProfile;  
    private JLabel profileImageLabel;  
    private JTextArea experienceTextArea;  
    private JButton changeProfileImageButton;  
    private JButton changeExperienceButton;  

    public StaffProfileView(StaffProfileModel staffProfile) {
        this.staffProfile = staffProfile;
        this.profileImageLabel = new JLabel();
        this.experienceTextArea = new JTextArea(10, 30);  
        this.changeProfileImageButton = new JButton("Cambia immagine profilo"); 
        this.changeExperienceButton = new JButton("Salva esperienza"); 
        setupProfileView();
    }

    private void setupProfileView() {
        setLayout(new BorderLayout());

        nameLabel = new JLabel("Nome: " + staffProfile.getName());
        surnameLabel = new JLabel("Cognome: " + staffProfile.getSurname());
        ageLabel = new JLabel("Età: " + staffProfile.getAge());
        phoneLabel = new JLabel("Telefono: " + staffProfile.getPhoneNumber());
        accommodationLabel = new JLabel("Alloggio: " + staffProfile.getAccommodation());
        residenceLabel = new JLabel("Residenza: " + staffProfile.getResidence());
        transportationLabel = new JLabel("Trasporto: " + staffProfile.getTransportation());
        allergiesLabel = new JLabel("Allergie: " + staffProfile.getAllergies());
        medicalIssuesLabel = new JLabel("Problemi Medici: " + staffProfile.getMedicalIssues());
        campLabel = new JLabel("Camp attuale: " + staffProfile.getCamp()); 

        experienceTextArea.setText(staffProfile.getExperience());  
        experienceTextArea.setLineWrap(true);
        experienceTextArea.setWrapStyleWord(true);

        // Immagine del profilo
        if (staffProfile.getProfilePicturePath() != null && !staffProfile.getProfilePicturePath().isEmpty()) {
            ImageIcon profileImage = new ImageIcon(staffProfile.getProfilePicturePath());
            profileImageLabel.setIcon(profileImage);
        }

        // Pannello info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(nameLabel);
        infoPanel.add(surnameLabel);
        infoPanel.add(ageLabel);
        infoPanel.add(phoneLabel);
        infoPanel.add(accommodationLabel);
        infoPanel.add(residenceLabel);
        infoPanel.add(transportationLabel);
        infoPanel.add(allergiesLabel);
        infoPanel.add(medicalIssuesLabel);
        infoPanel.add(campLabel); 
        infoPanel.add(new JLabel("Esperienza:"));
        infoPanel.add(new JScrollPane(experienceTextArea));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(changeProfileImageButton);
        buttonsPanel.add(changeExperienceButton);

        add(profileImageLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH); 
    }

    public void changeProfileImage() throws InvalidImageFormatException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Immagini JPEG", "jpg", "jpeg"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            if (!filePath.endsWith(".jpg") && !filePath.endsWith(".jpeg")) {
                throw new InvalidImageFormatException();  
            }
            staffProfile.setProfilePicturePath(filePath);
            ImageIcon profileImage = new ImageIcon(filePath);
            profileImageLabel.setIcon(profileImage);
        }
    }

    public String getExperience() {
        return experienceTextArea.getText();
    }

    public void setExperience(String experience) {
        staffProfile.setExperience(experience);
    }

    public JLabel getProfileImageLabel() {
        return profileImageLabel;
    }

    public JTextArea getExperienceTextArea() {
        return experienceTextArea;
    }

    public JButton getChangeProfileImageButton() {
        return changeProfileImageButton;
    }

    public JButton getChangeExperienceButton() {
        return changeExperienceButton;
    }


    public JTextComponent getNameLabel() {
        return new JTextArea(staffProfile.getName());
    }

    public JTextComponent getSurnameLabel() {
        return new JTextArea(staffProfile.getSurname());
    }

    public JTextComponent getAgeLabel() {
        return new JTextArea(String.valueOf(staffProfile.getAge()));
    }

    public JTextComponent getPhoneNumberLabel() {
        return new JTextArea(staffProfile.getPhoneNumber());
    }

    public JTextComponent getAccommodationLabel() {
        return new JTextArea(staffProfile.getAccommodation());
    }

    public JTextComponent getResidenceLabel() {
        return new JTextArea(staffProfile.getResidence());
    }

    public JTextComponent getTransportationLabel() {
        return new JTextArea(staffProfile.getTransportation());
    }

    public JTextComponent getAllergiesLabel() {
        return new JTextArea(staffProfile.getAllergies());
    }

    public JTextComponent getMedicalIssuesLabel() {
        return new JTextArea(staffProfile.getMedicalIssues());
    }

    public String getProfileImagePath() {
        return staffProfile.getProfilePicturePath();
    }

    public JTextComponent getCampLabel() {
        return new JTextArea(staffProfile.getCamp());
    }
}
