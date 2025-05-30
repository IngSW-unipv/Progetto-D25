package it.unipv.ingsw.syzygy.excamp.modelView.bars;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LowerBar extends JPanel {
   private static LowerBar istance;
   
   private static final long serialVersionUID = 1L;
   
   private LowerBar() {
 
       setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
       setMinimumSize(new Dimension(getWidth(), 30)); 
       setBackground(new Color(148, 6, 79));  
   }
   // Singleton method to return the unique instance of LowerBar
   public static LowerBar getInstance() {
       if (istance == null) {
           istance = new LowerBar();
       }
       return istance;
   }
}

