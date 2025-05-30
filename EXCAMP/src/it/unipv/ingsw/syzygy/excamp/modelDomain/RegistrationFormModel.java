package it.unipv.ingsw.syzygy.excamp.modelDomain;

import java.util.Date;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Camp;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Departure;
import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Transportation;

public class RegistrationFormModel {
  private String username;
  private String password;
  private String nameGE;
  private String surnameGE;
  private String parentsInfoPA;
  private String namePA;
  private String surnamePA;
  private int agePA;
  private String CFPA;
  private String allergiesPA;
  private String medicalIssuesPA;
  private String sensitiveInfoPA;
  private String genderPA;
  private String paese;
  private String indirizzo;
  private String civico;
  private String CAP;
  private String provincia;
  private Date dateOfBirthPA;
  private String placeOfBirthPA;
  private Camp camp;
  private Transportation transportation;
  private Departure pointOfDeparture;
  private Date participationWeek;
  private String notes;
  private boolean acceptedRegulation;
  private boolean acceptedConditions;
  private boolean acceptedPrivacy;
  private String prezzo = "€ 600"; // Aggiunta variabile prezzo
  // Costruttore
  public RegistrationFormModel(String username, String password, String nameGE, String surnameGE, String parentsInfoPA, String namePA, String surnamePA,
  		int agePA, String CFPA, String allergiesPA, String medicalIssuesPA, String sensitiveInfoPA, String genderPA,
  		String paese, String indirizzo, String civico, String CAP, String provincia, Date dateOfBirthPA, String placeOfBirthPA,
  		Camp camp, Transportation transportation, Departure pointOfDeparture, Date participationWeek, String notes,
  		boolean acceptedRegulation, boolean acceptedConditions, boolean acceptedPrivacy) {
  	this.username = username;
  	this.password = password;
  	this.nameGE = nameGE;
  	this.surnameGE = surnameGE;
  	this.parentsInfoPA = parentsInfoPA;
  	
  	this.namePA = namePA;
  	this.surnamePA = surnamePA;
  	this.agePA = agePA;
  	this.CFPA = CFPA;
  	this.allergiesPA = allergiesPA;
  	this.medicalIssuesPA = medicalIssuesPA;
  	this.sensitiveInfoPA = sensitiveInfoPA;
  	this.genderPA = genderPA;
  	this.paese = paese;
  	this.indirizzo = indirizzo;
  	this.civico = civico;
  	this.CAP = CAP;
  	this.provincia = provincia;
  	this.dateOfBirthPA = dateOfBirthPA;
  	this.placeOfBirthPA = placeOfBirthPA;
  	
  	this.camp = camp;
  	this.transportation = transportation;
  	this.pointOfDeparture = pointOfDeparture;
  	this.participationWeek = participationWeek;
  	this.notes = notes;
  	this.acceptedRegulation = acceptedRegulation;
  	this.acceptedConditions = acceptedConditions;
  	this.acceptedPrivacy = acceptedPrivacy;
  }
   public RegistrationFormModel() {
}
// Getters e Setters
  public String getUsername() {
  	return username;
  }
   public void setUsername(String username) {
  	this.username = username;
  }
   public String getPassword() {
  	return password;
  }
   public void setPassword(String password) {
  	this.password = password;
  }
   public String getNameGE() {
      return nameGE;
  }
  public void setNameGE(String nameGE) {
      this.nameGE = nameGE;
  }
  public String getSurnameGE() {
      return surnameGE;
  }
  public void setSurnameGE(String surnameGE) {
      this.surnameGE = surnameGE;
  }
   public String getParentsInfoPA() {
      return parentsInfoPA;
  }
  public void setParentsInfoPA(String parentsInfoPA) {
      this.parentsInfoPA = parentsInfoPA;
  }
   public String getNamePA() {
      return namePA;
  }
  public void setNamePA(String namePA) {
      this.namePA = namePA;
  }
  public String getSurnamePA() {
      return surnamePA;
  }
  public void setSurnamePA(String surnamePA) {
      this.surnamePA = surnamePA;
  }
   public int getAgePA() {
      return agePA;
  }
  public void setAgePA(int agePA) {
      this.agePA = agePA;
  }
  public String getCFPA() {
      return CFPA;
  }
  public void setCFPA(String CFPA) {
      this.CFPA = CFPA;
  }
  public String getAllergiesPA() {
      return allergiesPA;
  }
  public void setAllergiesPA(String allergiesPA) {
      this.allergiesPA = allergiesPA;
  }
  public String getMedicalIssuesPA() {
      return medicalIssuesPA;
  }
  public void setMedicalIssuesPA(String medicalIssuesPA) {
      this.medicalIssuesPA = medicalIssuesPA;
  }
   public String getSensitiveInfoPA() {
      return sensitiveInfoPA;
  }
  public void setSensitiveInfoPA(String sensitiveInfoPA) {
      this.sensitiveInfoPA = sensitiveInfoPA;
  }
   public String getGenderPA() {
      return genderPA;
  }
  public void setGenderPA(String genderPA) {
      this.genderPA = genderPA;
  }
   public String getPaese() {
      return paese;
  }
  public void setPaese(String paese) {
      this.paese = paese;
  }
   public String getIndirizzo() {
      return indirizzo;
  }
  public void setIndirizzo(String indirizzo) {
      this.indirizzo = indirizzo;
  }
   public String getCivico() {
      return civico;
  }
  public void setCivico(String civico) {
      this.civico = civico;
  }
   public String getCAP() {
      return CAP;
  }
  public void setCAP(String CAP) {
      this.CAP = CAP;
  }
   public String getProvincia() {
      return provincia;
  }
  public void setProvincia(String provincia) {
      this.provincia = provincia;
  }
   public Date getDateOfBirthPA() {
      return dateOfBirthPA;
  }
  public void setDateOfBirthPA(Date dateOfBirthPA) {
      this.dateOfBirthPA = dateOfBirthPA;
  }
   public String getPlaceOfBirthPA() {
      return placeOfBirthPA;
  }
  public void setPlaceOfBirthPA(String placeOfBirthPA) {
      this.placeOfBirthPA = placeOfBirthPA;
  }
   public Camp getCamp() {
  	return camp;
  }
   public void setCamp(Camp camp) {
  	this.camp = camp;
  }
   public Transportation getTransportation() {
  	return transportation;
  }
   public void setTransportation(Transportation transportation) {
  	this.transportation = transportation;
  }
   public Departure getPointOfDeparture() {
  	return pointOfDeparture;
  }
   public void setPointOfDeparture(Departure pointOfDeparture) {
  	this.pointOfDeparture = pointOfDeparture;
  }
  public Date getParticipationWeek() {
  	return participationWeek;
  }
   public void setParticipationWeek(Date participationWeek) {
  	 this.participationWeek = participationWeek;
   }
 
   public String getNotes() {
  	 return notes;
   }
 
   public void setNotes(String notes) {
  	 this.notes = notes;
   }
 
   public boolean isAcceptedRegulation() {
  	 return acceptedRegulation;
   }
 
   public void setAcceptedRegulation(boolean acceptedRegulation) {
  	 this.acceptedRegulation = acceptedRegulation;
   }
 
   public boolean isAcceptedConditions() {
  	 return acceptedConditions;
   }
 
   public void setAcceptedConditions(boolean acceptedConditions) {
  	 this.acceptedConditions = acceptedConditions;
   }
 
   public boolean isAcceptedPrivacy() {
  	 return acceptedPrivacy;
   }
 
   public void setAcceptedPrivacy(boolean acceptedPrivacy) {
  	 this.acceptedPrivacy = acceptedPrivacy;
   }
  // Getter e Setter per prezzo
  public String getPrezzo() {
      return prezzo;
  }
  public void setPrezzo(String prezzo) {
      this.prezzo = prezzo;
  }
}
