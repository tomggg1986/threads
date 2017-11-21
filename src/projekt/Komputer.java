package projekt;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Komputer implements Runnable {
    
    private int ip;
    private String nazwa;
    private Image obraz;
    private Graphics d;
    private XSwitch xswitch;
    private char znak;
    private int adresat;
    private static Random rn = new Random();
    Komputer(int ip,String nazwa, XSwitch xswitch)
    {

        this.ip = ip;
        this.nazwa = nazwa;
        this.xswitch = xswitch;
     
    }
    public  int rand(int lo, int hi)
    {
        int i;
        do{
           int n = hi - lo + 1;
            i = rn.nextInt() % n;
           if (i < 0)
           i = -i;
        }
        while(lo+i == ip);
           return lo + i;
     }
    public void run()
    {
        try
        {
            xswitch.rysujKomp(ip);
            for(int i = 0; i<100; i++)
            {
                 if((adresat = rand(0,4)) != ip);
                  xswitch.wyslij(znak, ip, adresat);
                // for( int iq = 0; iq<5000;iq++){
                 //    for(int iw = 0; iw<5000;iw++){}}
                 System.out.println("RYSUJ KOMPY " +i+" "+ip);
                Thread.sleep(adresat*1000);
           }         
        }
        catch(InterruptedException e)
        {
            System.out.println("Błąd wątku");
        }
   }        

}


