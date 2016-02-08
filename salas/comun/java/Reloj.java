
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Reloj extends java.applet.Applet implements Runnable 
{

    Thread relojThread = null;
    int segundos = 600,gastados=0;
    boolean parado=true;
    long tiempoInicial;
    Ajedrez ajedrez;
    boolean desconto=false;
    int tempoAnteriorRelojRival;
    boolean activo = true;

    public Reloj(int sg,Ajedrez ajedrez)
    {
		segundos=sg;
        this.ajedrez = ajedrez;
    }

    public void init()
    {
		setBackground(ajedrez.salon.colFondoBotos);
    }
    
    public int tempoRestante()
    {
      return segundos-gastados;
    }

    public int para()
    {
		int gas;

        if (gastados == 0)
          gastados = 1; //obligase a gastar polo menos un segundo no movemento
		parado = true;
		segundos = segundos - gastados ;
		gas = gastados;
		gastados = 0;
        return gas;
    }

    public void para(int sg)
    {
      desconto = false;
      segundos = sg;
      parado = true;
      gastados = 0;
    }

    public void arranca()
    {
		parado = false;
		tiempoInicial=ajedrez.salon.tempoRelativo.tempo();
    }

    public void arranca(int sg)
    {
        desconto = false;
		segundos = sg;
		parado = false;
		tiempoInicial=ajedrez.salon.tempoRelativo.tempo();
    }
    
    public void arrancaPostDesconto()
    {
      if(desconto)
        arranca(tempoAnteriorRelojRival);
    }

    public void arrancaDesconto()
    {
      tempoAnteriorRelojRival = segundos-gastados;
      //if(tempoAnteriorRelojRival>30)
      //  tempoAnteriorRelojRival -= 30; //de momento non penalizo cos 30 segundos
      desconto = true;
      segundos = Math.min(60,tempoAnteriorRelojRival); //si se penaliza os que se desconectan o final
      gastados = 0;
      parado = false;
      tiempoInicial=ajedrez.salon.tempoRelativo.tempo();
    }
    
    public void paraPostDesconto()
    {
    	if(desconto)
          para(tempoAnteriorRelojRival);
    }        

    public void start() 
    {
        if (relojThread == null) 
        {
            relojThread = new Thread(this, "Reloj");
            relojThread.start();
        }
    }
    
    public void run() 
    {   	
        tiempoInicial=ajedrez.salon.tempoRelativo.tempo();
        while (activo && Thread.currentThread() == relojThread) 
        {
            repaint();
            try 
            {
                Thread.sleep(1000);
	            if(!parado)
			      gastados = (int)(ajedrez.salon.tempoRelativo.tempo()-tiempoInicial)/1000;
            } catch (InterruptedException e){return;}
            if(gastados >= segundos && !parado) 
            {
				gastados = segundos;
						repaint();
				if(this == ajedrez.reloj)
				{ 
						  ajedrez.finReloxio();
						  activo=false;
				}  
				if(this == ajedrez.relojRival && desconto) 
				{
                  ajedrez.finDesconto();
                  activo=false;
                }  
            }
        }
    }
    
    
    public void paint(Graphics g) 
    {
		String mins,segs;
	  
		mins=("   "+String.valueOf((segundos-gastados)/60));
        mins=mins.substring(mins.length()-3);
		segs=("00"+String.valueOf((segundos-gastados)%60));
		segs=segs.substring(segs.length()-2);
		g.setFont(new Font("Courier", Font.BOLD,20));
        if (desconto)
        {
          if(gastados%2 == 0)
          {
            setBackground(Color.red);
            g.setColor(new Color(255,255,255));
          }
          else
          {
            setBackground(new Color(255,255,255));
            g.setColor(Color.red);
          }
        }
        else
        {
          setBackground(ajedrez.salon.colFondoBotos);	  
          g.setColor(ajedrez.salon.colTextoBotos);
        }
        g.drawString(mins+":"+segs, 1, 14);
    }
    
    public void stop() 
    {
        relojThread = null;
        activo=false;
        Thread.currentThread().interrupt();
    }
}

