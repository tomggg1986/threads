package projekt;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;



public class Main {




    public static void main(String[] args) {

        System.out.println("Symulator Switch'a");
        XSwitch xswitch = new XSwitch();
        xswitch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit zestaw = Toolkit.getDefaultToolkit();
        Dimension wymiary = zestaw.getScreenSize();
        int wysokosc = wymiary.height;
        int szerokosc = wymiary.width;
        xswitch.setLocation((szerokosc/2)-100, (wysokosc/2)-150);
        xswitch.setVisible(true);
       
       
              Komputer k1 = new Komputer( 0, "ptoku1",xswitch);
              Komputer k2 = new Komputer( 1, "ptoku2",xswitch);
              Komputer k3 = new Komputer( 2, "ptoku3",xswitch);
              Komputer k4 = new Komputer( 3, "ptoku4",xswitch);
          //    try
          //    {
              (new Thread(k1)).start();
              (new Thread(k2)).start();
              (new Thread(k3)).start();
              (new Thread(k4)).start();
          //    }
           //   catch(InterruptedException e)
           //   {

           //   }

//        while(true){
//        
//        }
    }
}
