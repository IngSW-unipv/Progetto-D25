package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import it.unipv.ingsw.syzygy.excamp.modelDomain.lista.Location;

public class Autista {
	
  private String nameAU;
  private String surnameAU;
  private int phoneNumberAU;
  private Location location;
  
  public Autista(String nameAU, String surnameAU, int phoneNumberAU, Location location) {
      this.nameAU = nameAU;
      this.surnameAU = surnameAU;
      this.phoneNumberAU = phoneNumberAU;
      this.location = location;
  }
  
  public String getNameAU() {
      return nameAU;
  }
  
  public String getSurnameAU() {
      return surnameAU;
  }
  
  public int getPhoneNumberAU() {
      return phoneNumberAU;
  }
  
  public Location getLocation() {
      return location;
  }

}
