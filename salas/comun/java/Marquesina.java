

import java.applet.Applet;
import java.awt.*;
import java.util.StringTokenizer;

public class Marquesina extends Applet
    implements Runnable
{
    Graphics offGraphics;
    Image imagenOculta;
    String s;
    
    public void init()
    {
        int i;

        copiamsg=new String("");
        myFont = new Font("Verdana", 0, 12);
        myFontM = getFontMetrics(myFont);
        copiamsg=new String(msg);

        setBackground(Color.black);
        
        s = msg.substring(0, 1);
        pixelsToGo = myFontM.stringWidth(s)-1;           
        msg = msg.substring(1);        

    }

    public void prepararImagenOculta()
    {
        if (offGraphics == null) 
        {
            imagenOculta = createImage(getWidth(), getHeight());
            offGraphics = imagenOculta.getGraphics();
        }          

        offGraphics.copyArea(1, 0, getWidth() - 1, getHeight(), -1, 0);
        offGraphics.setColor(salon.colFondoBotos);
        offGraphics.setFont(myFont);
        //pixelsToGo -=2;
        if(--pixelsToGo < 0)
        {
            offGraphics.drawString(s,getWidth()-myFontM.stringWidth(s)-(-pixelsToGo),getHeight()-3);
            s = msg.substring(0, 1);
            pixelsToGo = myFontM.stringWidth(s)-1;           
            msg = msg.substring(1);
            if(msg.length()==1)
              msg=msg+copiamsg;
        }
        offGraphics.setColor(Color.black);
        offGraphics.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());
    }        

    public void paint(Graphics g)
    {
          

        //g.drawLine(getWidth() - 2, 0, getWidth() - 2, getHeight());
        //g.drawLine(getWidth() - 3, 0, getWidth() - 3, getHeight());
        //g.drawLine(getWidth() - 4, 0, getWidth() - 4, getHeight());   
        //Mostrar la imagen en pantalla
        
        if(imagenOculta==null)
          prepararImagenOculta();
        g.drawImage(imagenOculta, 0, 0, this);        
        

    }

    public void update(Graphics g)
    {
      paint(g);
      
    }


    public synchronized void start()
    {
        if(daemon == null)
        {
            daemon = new Thread(this);
            daemon.start();
        }
    }

    public synchronized void stop()
    {
        //daemon.stop();
        daemon = null;
        Thread.currentThread().interrupt();
    }

    public void run()
    {
        long i;
        //guardar el tiempo al iniciar
        long momentoPintar = System.currentTimeMillis();

        //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        while(Thread.currentThread()==daemon) 
        {          

            //Retardo entre fotograma y fotograma
            try {
                momentoPintar += delay;
                i=momentoPintar - System.currentTimeMillis();
                if(i>0)
                {
                  Thread.sleep(i);
                  prepararImagenOculta();
                  repaint();
                }
            } catch(Exception e){}
            
        }
    }

    public Marquesina(String frase,Salon salon)
    {
        this.salon=salon;
        msg = frase;
        delay = 20;
        threadSuspended = false;
    }
    
    Salon salon;
    String msg;
    String copiamsg;
    Font myFont;
    FontMetrics myFontM;
    int delay;
    protected Thread daemon;
    protected boolean threadSuspended;
    protected int pixelsToGo;
}