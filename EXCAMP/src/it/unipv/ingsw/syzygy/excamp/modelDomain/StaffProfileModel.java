package it.unipv.ingsw.syzygy.excamp.modelDomain;

import it.unipv.ingsw.syzygy.excamp.exceptions.InvalidImageFormatException;

public class StaffProfileModel {
    private int id; 
    private String username;  
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;
    private String accommodation;
    private String residence;
    private String transportation;
    private String allergies;
    private String medicalIssues;
    private String profilePicturePath;
    private String experience;
    private String camp; 

    public StaffProfileModel(String name, String surname, int age, String phoneNumber,
                             String accommodation, String residence, String transportation,
                             String allergies, String medicalIssues, String profilePicturePath,
                             String experience) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.accommodation = accommodation;
        this.residence = residence;
        this.transportation = transportation;
        this.allergies = allergies;
        this.medicalIssues = medicalIssues;
        this.profilePicturePath = (profilePicturePath == null || profilePicturePath.isEmpty()) ? "" : profilePicturePath;
        this.experience = (experience == null || experience.isEmpty()) ? "" : experience;
    }

    public void visualizzaProfilo() {
        System.out.println("Metodo visualizzaProfilo chiamato. Utilizzare una classe controller per visualizzare la UI.");
    }

    public void updateProfilePicture(String path) throws InvalidImageFormatException {
        if (!path.endsWith(".jpg") && !path.endsWith(".jpeg")) {
            throw new InvalidImageFormatException();
        }
        this.profilePicturePath = path;
    }

    public void updateExperience(String newExperience) {
        if (newExperience == null || newExperience.isEmpty()) {
            throw new IllegalArgumentException("L'esperienza non può essere vuota.");
        }
        this.experience = newExperience;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccommodation() {
        return accommodation;
    }
    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getTransportation() {
        return transportation;
    }
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedicalIssues() {
        return medicalIssues;
    }
    public void setMedicalIssues(String medicalIssues) {
        this.medicalIssues = medicalIssues;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }
    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCamp() {
        return camp;
    }
    public void setCamp(String camp) {
        this.camp = camp;
    }
}
