package it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione;

public class Attività {
	
   private String nome;
   private String descrizione;
   private String orario;
   private String luogo;
   
   public Attività(String nome, String descrizione, String orario, String luogo) {
       this.nome = nome;
       this.descrizione = descrizione;
       this.orario = orario;
       this.luogo = luogo;
   }
   
   // Getters e Setters
   public String getNome() { return nome; }
   public String getDescrizione() { return descrizione; }
   public String getOrario() { return orario; }
   public String getLuogo() { return luogo; }
   
   @Override
   public String toString() {
       return "Attività: " + nome + " (" + orario + " @ " + luogo + ")\n" + descrizione;
   }
   
}
