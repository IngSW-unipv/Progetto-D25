package it.unipv.ingsw.syzygy.excamp.modelView.organizzazione;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import it.unipv.ingsw.syzygy.excamp.modelDomain.organizzazione.ProgrammaGiornaliero;

public class ProgrammaView {
	
   private JFrame frame;
   private JTextArea textArea;
   private JSpinner dateSpinner;  

   public ProgrammaView() {
       frame = new JFrame("Programmi");
       textArea = new JTextArea(20, 40);
       
       dateSpinner = new JSpinner(new SpinnerDateModel());
       JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
       dateSpinner.setEditor(editor);
       
       // Imposta la finestra
       textArea.setEditable(false);
       JScrollPane scrollPane = new JScrollPane(textArea);
       frame.setLayout(new BorderLayout());
       
       frame.add(dateSpinner, BorderLayout.NORTH);
       frame.add(scrollPane, BorderLayout.CENTER);
       frame.pack();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
   }

   // Metodo per visualizzare i programmi
   public void visualizzaProgrammi(List<ProgrammaGiornaliero> programmi) {
       StringBuilder sb = new StringBuilder();
       for (ProgrammaGiornaliero p : programmi) {
           sb.append(p.toString()).append("\n");
       }
       textArea.setText(sb.toString());
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               ProgrammaView programmaView = new ProgrammaView();
               programmaView.visualizzaProgrammi(null);  
           }
       });
   }
   
}
