package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

public class Albergo {
	   private String CF;
	   private String nome;
	   private String cognome;
	   private String hotel;
	   private String location;
	   private String camp;
	   private int room;
	   public Albergo(String CF, String nome, String cognome, String hotel, String location, String camp, int room) {
	       this.CF = CF;
	       this.nome = nome;
	       this.cognome = cognome;
	       this.hotel = hotel;
	       this.location = location;
	       this.camp = camp;
	       this.room = room;
	   }
	   public String getCf() {
	       return CF;
	   }
	   public String getNome() {
	       return nome;
	   }
	   public String getCognome() {
	       return cognome;
	   }
	   public String getHotel() {
	       return hotel;
	   }
	   public String getLocation() {
	       return location;
	   }
	   public String getCamp() {
	       return camp;
	   }
	   public int getRoom() {
	       return room;
	   }
	}
