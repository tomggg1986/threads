package projekt;

import java.util.concurrent.locks.*;
import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.*;

public class XSwitch extends JFrame {

private ReentrantLock[] switchLocktab = new ReentrantLock[4];
private Condition con;
private static final  int SZEROKOSC = 1000;
private static final int WYSOKOSC = 500;
private Image obraz;
private Graphics d;
//private JLabel label;
private Container zawartosc;
JPanel tlo;
Box dane;
Box dane2;
JTextField[] textFields;
boolean colorb;



XSwitch()
{
     setTitle("Switch");
     setSize(SZEROKOSC, WYSOKOSC);
     zawartosc = getContentPane();
     tlo = new JPanel();
     dane = Box.createVerticalBox();
     dane2 = Box.createHorizontalBox();
     textFields = new JTextField[4];
     switchLocktab[0] = new ReentrantLock();
     switchLocktab[1] = new ReentrantLock();
     switchLocktab[2] = new ReentrantLock();
     switchLocktab[3] = new ReentrantLock();    
     this.colorb = true;

}
public void wyslij(char znak, int od, int dla)
        throws InterruptedException
{
    if(dla == 0)
    {
        port(od,0);
    }
    if(dla == 1)
    {
        port(od,1);  
    }
    if(dla ==2)
    {
        port(od,2);
    }
    if(dla == 3)
    {
        port(od,3);
    }
}
public void rysujKomp(int ip)
{
    try
    {
        Box dane3 = Box.createVerticalBox();
        File f = new File("images.jpg");
        BufferedImage image = ImageIO.read(f);
        JLabel label = new JLabel(new ImageIcon(image));
        Dimension wyniar = new Dimension();
        wyniar.setSize(120, 120);
        label.setMaximumSize(wyniar);
        textFields[ip] = new JTextField(22);
        textFields[ip].setMaximumSize(textFields[ip].getPreferredSize());
        JLabel numer = new JLabel();
        numer.setText("Komputer nr: "+Integer.toString(ip));
        numer.setVisible(true);
        dane3.add(textFields[ip]);
        dane3.add(numer);
        dane3.add(label);
        dane2.add(dane3);
        this.add(dane2);
        this.setVisible(true);
        
    }
    catch(IOException  e)
    {
     e.printStackTrace();
    }
}
private void pisz(int od, int dla)
{

    String ods ="", dlas ="";
    ods = Integer.toString(od);
    dlas = Integer.toString(dla);
    if(this.colorb == true)
    {
    textFields[od].setForeground(Color.red);
    textFields[dla].setForeground(Color.red);
    this.colorb = false;
    }
    else
    {
        textFields[od].setForeground(Color.green);
        textFields[dla].setForeground(Color.green);
        this.colorb = true;
    }
    textFields[od].setText("Wysyła do "+dlas);
    textFields[dla].setText("Odbiera od "+ods);
    System.out.println("PISZ START");
    
}
private void czysc(int od, int dla)
{
     textFields[od].setForeground(Color.black);
     textFields[dla].setForeground(Color.black);
     textFields[od].setText("");// show(false);
     textFields[dla].setText("");//.show(false);  
     System.out.println("CZYSC START");
}
private void czekaj(int ip,int na)
{
    textFields[ip].setForeground(Color.black);
    textFields[ip].setText("Czeka na "+Integer.toString(na));
}
private void port(int od, int dla)
{
    int wait = 0;
    //int warunek = 1;
    while(true)
        {
            if(switchLocktab[od].tryLock() )
            {
                try
                {                
                     if(switchLocktab[dla].tryLock() )
                     {
                           try
                           {
                                 System.out.println("Watek:"+od+"wysyła do wątku: " +dla);                              
                                 pisz(od,dla);
//                                 for(int i = 0; i<1000000;i++)
//                                    for(int j = 0; j<7000;j++){}
                                 Thread.sleep(800); 
                                 czysc(od,dla);
                                // warunek = 0;
                                break;
                             }catch(InterruptedException e){
                                 e.printStackTrace();
                             }
                             finally
                             {
                                  switchLocktab[dla].unlock();
                                  switchLocktab[od].unlock();             
                             }
                   }
                   else
                   {
//                       wait++;
//                       if(wait == 100){
//                           wait =0;
                           switchLocktab[od].unlock();
//                           System.out.println("UNLOCK: "+ od+" ----------------");
//                           Thread.sleep(1000);
//                       }
                       czekaj(od,dla);
//                       System.err.println("Czeka: "+od+" z transmisją do: "+dla);
                   }
               }catch(Exception e){
                   e.printStackTrace();
               }
//               finally
//               {
//                    if(switchLocktab[od].isLocked()){
//                        switchLocktab[od].unlock();
//                    }
//               }
                        
            }
        }//while
}
}