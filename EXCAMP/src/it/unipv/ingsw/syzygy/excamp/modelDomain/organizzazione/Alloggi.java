package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

import java.util.ArrayList;
import java.util.List;

public class Alloggi {
   private List<Albergo> alloggiPartecipanti;
   private List<Albergo> alloggiStaff;
   public Alloggi() {
       this.alloggiPartecipanti = new ArrayList<>();
       this.alloggiStaff = new ArrayList<>();
   }
   public void aggiungiAlloggioPartecipante(Albergo alloggio) {
       alloggiPartecipanti.add(alloggio);
   }
   public void aggiungiAlloggioStaff(Albergo alloggio) {
       alloggiStaff.add(alloggio);
   }
   public void visualizzaAlloggiPartecipanti() {
       StringBuilder sb = new StringBuilder();
       for (Albergo alloggio : alloggiPartecipanti) {
           sb.append("Nome: ").append(alloggio.getNome())
               .append(" Cognome: ").append(alloggio.getCognome())
               .append(" Hotel: ").append(alloggio.getHotel())
               .append(" Stanza: ").append(alloggio.getRoom())
               .append("\n");
       }
       System.out.println(sb.toString());
   }
   public void visualizzaAlloggiStaff() {
       StringBuilder sb = new StringBuilder();
       for (Albergo alloggio : alloggiStaff) {
           sb.append("Nome: ").append(alloggio.getNome())
               .append(" Cognome: ").append(alloggio.getCognome())
               .append(" Hotel: ").append(alloggio.getHotel())
               .append(" Stanza: ").append(alloggio.getRoom())
               .append("\n");
       }
       System.out.println(sb.toString());
   }
   public void visualizzaAlloggioUtente(String CFPA) {
       StringBuilder sb = new StringBuilder();
       for (Albergo alloggio : alloggiPartecipanti) {
           if (alloggio.getCf().equals(CFPA)) {
               sb.append("Alloggio per il partecipante: ")
                   .append("Nome: ").append(alloggio.getNome())
                   .append(" Cognome: ").append(alloggio.getCognome())
                   .append(" Hotel: ").append(alloggio.getHotel())
                   .append(" Stanza: ").append(alloggio.getRoom())
                   .append("\n");
               System.out.println(sb.toString());
               return;
           }
       }
       System.out.println("Alloggio non trovato per il CF: " + CFPA);
   }
   public List<Albergo> getAlloggiPartecipanti() {
       return alloggiPartecipanti;
   }
   public List<Albergo> getAlloggiStaff() {
       return alloggiStaff;
   }
}
