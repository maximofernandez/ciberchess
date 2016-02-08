import java.awt.*;
import java.applet.Applet;
import java.net.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.KeyListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.AdjustmentEvent;

public class Chat extends JApplet implements ClienteReloxioAnaloxico
{
    JTextField nuevoTextoChat = new JTextField(90);
    int posicionServidor = 0;
    String login;
    Vector frases=new Vector();
    Vector coloreado=new Vector();
    Vector color=new Vector();
    Vector color1=new Vector();
    JScrollBar barra;
    int numeroLineas;
    boolean publico;
    int longitudMaxima;
    Applet padre;
    int hora,minuto,segundo;

    //String mensaxeAnterior="";
    Chat chat;
    
    ReloxioAnaloxico reloxioAnaloxico;
    
    Image imagenOculta;
    Graphics offGraphics;
    
    CanvasReloxio canvas;
    
    public Chat(String log,int numLineas,boolean publico,int longMaxima,Applet padre)
    {
      this(log, numLineas, publico, longMaxima, padre, 0, 0, 0);
    }


    public Chat(String log,int numLineas,boolean publico,int longMaxima,Applet padre,int hora,int minuto,int segundo)
    {
      login=log;
      numeroLineas=numLineas;
      this.publico = publico;
      longitudMaxima=longMaxima;
      this.padre=padre;
      chat = this;
      this.hora=hora;
      this.minuto=minuto;
      this.segundo=segundo;
    }


    public void init() 
    {
        getContentPane().setLayout( null );

        try 
        {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception exc) {}  
          barra=new JScrollBar();
        try 
        {
          UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception exc) {}


	canvas=new CanvasReloxio(this);
        colocarComponentes();
        getContentPane().add(canvas);

        nuevoTextoChat.setFont(new Font("Courier", Font.PLAIN, 11));
        nuevoTextoChat.setBackground(Color.white);
        getContentPane().add(nuevoTextoChat);   

        
        if(publico)
        {
          getContentPane().setBackground(((Salon)padre).colFondoBotosClaro);
          barra.setBackground(((Salon)padre).colFondoBotos);
          barra.setForeground(((Salon)padre).colFondoBotos);
          reloxioAnaloxico = new ReloxioAnaloxico((Salon)padre,this, hora, minuto, segundo, getBounds().width-18, getBounds().height-22);
          reloxioAnaloxico.start();
        }
        else
        {
          getContentPane().setBackground(((Ajedrez)padre).salon.colFondoBotosClaro);
          barra.setBackground(((Ajedrez)padre).salon.colFondoBotos);
          barra.setForeground(((Ajedrez)padre).salon.colFondoBotos);
        }        
        barra.setBlockIncrement(numeroLineas*11);
        barra.setUnitIncrement(11);
        barra.setBorder(new EmptyBorder(0,0,0,0));
	getContentPane().add(barra);

        color1.addElement(new Color(255,0,0));
        color1.addElement(new Color(130,65,0));
        color1.addElement(new Color(0,128,255));
        color1.addElement(new Color(128,128,255));
        color1.addElement(new Color(255,0,128));
        color1.addElement(new Color(0,64,128));
        color1.addElement(new Color(0,87,130));
        color1.addElement(new Color(0,128,192));
        color1.addElement(new Color(128,128,64));
        color1.addElement(new Color(255,128,0));
        
        axustarParametros();
        
        barra.setValues(0, 5, 0,5);

        nuevoTextoChat.addKeyListener(
          new KeyListener()
          {
	    public void keyReleased( KeyEvent evt ) 
	    {
	      String textoTecleado;
	      
	      textoTecleado=nuevoTextoChat.getText();
	      if(textoTecleado.length()>200)
	        textoTecleado=textoTecleado.substring(0,200);
	      if (evt.getKeyCode()=='\n') 
	      {
		  if (textoTecleado.length()>0)
		  {
			if(publico)
			{
			  //if(mensaxeAnterior.compareTo(nuevoTextoChat.getText())!=0)
			  //{
			    
			    ((Salon)padre).enviar_comentario("<"+login+">"+textoTecleado.replace(']',' ')+"]");
			    if(login.compareTo("ADMINISTRADOR")!=0)
			    {
			      nuevoTextoChat.setEnabled(false);
			      nuevoTextoChat.setBackground(new Color(192,192,192));
			      (new TemporizadorChat(chat)).start();
			    }
			  //}
			}
			else
			{
			  ((Ajedrez)padre).enviar_comentario("<"+login+">"+textoTecleado.replace(']',' ')+"]");		  
			}
			nuevoTextoChat.setText("");
	
			
	         }
		}
	      }
	      
              public void keyPressed(KeyEvent e) 
              {
              
              }

              public void keyTyped(KeyEvent e) 
              {
                
              }	      
            }
          );
          
      barra.addAdjustmentListener(
        new AdjustmentListener()
        {	
          public void adjustmentValueChanged(AdjustmentEvent e) 
          {
            repaint();	
          }
        }
      );
          
          
    }
    
    
    public void colocarComponentes()
    {
        canvas.setBounds(0,0,getBounds().width-18,getBounds().height-22);
        nuevoTextoChat.setBounds(0,getBounds().height-22,getBounds().width,22);
        barra.setBounds(getBounds().width-18,0,18,getBounds().height-22);       	
    }
    
    
    public void redimensionarReloxio()
    {
      if (reloxioAnaloxico!=null)
        reloxioAnaloxico.fijarAnchoAlto(getBounds().width-18, getBounds().height-22); 
    }
    
    
    public void eventoReloxioAnaloxico()
    {
      crearImaxeCanvas();
      canvas.repaint();
    }

    
    public void axustarParametros()
    {
    
        numeroLineas = (getBounds().height-20)/11;
        longitudMaxima = (getBounds().width-15)/7-1;
    }

   public void update(Graphics g) 
   {
     paint(g);	
   }

    public void paint(Graphics g) 
    {
      
      //super.paint(g);

     //crear la imagen oculta si es que no existe
      crearImaxeCanvas();

  
  /*    
      Graphics ntcg=nuevoTextoChat.getGraphics();
      if (ntcg != null)
        nuevoTextoChat.paint(ntcg);
      else
        nuevoTextoChat.repaint();
      Graphics barrag=barra.getGraphics();
      if (barrag != null)
        barra.paint(barrag);
      else
        barra.repaint();   
*/           
      canvas.repaint();
      nuevoTextoChat.repaint();
      barra.repaint();
    } 


    private void crearImaxeCanvas()
    {
      int i,largo;
          	
      if (offGraphics == null) 
      {
        try
        {
          imagenOculta = padre.createImage(getBounds().width, getBounds().height);
          offGraphics = imagenOculta.getGraphics(); 
        }
       catch(Exception e)
        {
          return;
        }
      }
           

      if(publico)
      {
        offGraphics.setColor(((Salon)padre).colFondoBotos);
        try
        {
          offGraphics.drawImage(reloxioAnaloxico.imaxeReloxio(),0,0,null);
        }
        catch (Exception e) {}
      }
      else
      {
      	offGraphics.setColor(Color.gray);
      	offGraphics.drawLine(0,0,getBounds().width,0);
        offGraphics.setColor(((Ajedrez)padre).salon.colFondoBotosClaro);
        offGraphics.fillRect(0, 0, getBounds().width, getBounds().height);
        offGraphics.setColor(((Ajedrez)padre).salon.colFondoBotos);
        offGraphics.setFont(new Font("Arial", Font.BOLD, 42));
        offGraphics.drawString("Chat Privado",1,55);
      }

      offGraphics.setFont(new Font("Courier", Font.PLAIN, 11));
      for(i=0;i<frases.size();i++) 
      {
        largo=((Integer)coloreado.elementAt(i)).intValue();
        if(largo>0) 
        {
          offGraphics.setColor((Color)color1.elementAt((((Integer)color.elementAt(i)).intValue()+1)%10));
          offGraphics.drawString(((String)frases.elementAt(i)).substring(0,largo),1,-barra.getValue()+(i+1)*11);	   
        }
        offGraphics.setColor((Color)color1.elementAt(((Integer)color.elementAt(i)).intValue()));
        offGraphics.drawString(((String)frases.elementAt(i)).substring(largo),1+largo*7,-barra.getValue()+(i+1)*11);
      }
  	
    }

    private Integer sumaAscii(String cadea)
    {
      int i;
      int suma=0;

      for(i=0;i<cadea.length();i++)
        suma=suma+cadea.charAt(i);
      return new Integer(suma%10);
    }


    public void engadirFrase(String cadea1,String cadea2)
    {
      String cadea;
      int indice = 0;

      
      if(publico)
        if(((Salon)(padre)).frmVerPartidas!=null)
          if(this != ((Salon)(padre)).ajedrezVer.chatPublico)
            ((Salon)(padre)).ajedrezVer.chatPublico.engadirFrase(cadea1,cadea2);

      try
      {
	cadea1 = "<" + cadea1 + ">"; 
	cadea = cadea1 + cadea2;
	if(cadea.length()>longitudMaxima)
	{
          indice = cadea.lastIndexOf(' ',longitudMaxima) + 1;
          if (indice <=0)
            indice = longitudMaxima;
	  frases.addElement(cadea.substring(0,indice));
	  coloreado.addElement(new Integer(cadea1.length()));
	  color.addElement(sumaAscii(cadea1));
	  cadea=cadea.substring(indice);
	  do
	  {
		if(cadea.length()>longitudMaxima)
		{
                  indice = cadea.lastIndexOf(' ',longitudMaxima) + 1;
                  if (indice <= 0)
                    indice = longitudMaxima;
	          frases.addElement(cadea.substring(0,indice));
		  coloreado.addElement(new Integer(0));
		  color.addElement(sumaAscii(cadea1));
		  cadea=cadea.substring(indice);
		}
		else
		{
		  frases.addElement(cadea);
		  coloreado.addElement(new Integer(0));
		  color.addElement(sumaAscii(cadea1));
		  cadea="";
		}
	  } while(cadea.length()>0);
	}
	else
	{
	  frases.addElement(cadea);
	  coloreado.addElement(new Integer(cadea1.length()));
	  color.addElement(sumaAscii(cadea1));
	}
	
      while(frases.size()>200)
      {
      	frases.removeElementAt(0);
      	coloreado.removeElementAt(0);
      	color.removeElementAt(0);
      }	
	
      axustarValoresBarra();
       
      }catch(Exception e) {}
      
    }
    
    
    public void axustarValoresBarra()
    {
      int zonaVisible;
    	
      zonaVisible=Math.min(numeroLineas*11,frases.size()*11+5);	
      if(barra.getValue()==barra.getMaximum()-barra.getVisibleAmount())
      {
	  barra.setValues(frases.size()*11+5-zonaVisible, zonaVisible, 0,frases.size()*11+5);  
	  //System.out.println("primeiro: "+String.valueOf(frases.size()*11+5-zonaVisible)+" "+String.valueOf(zonaVisible)+" "+String.valueOf(0)+" "+String.valueOf(frases.size()*11+5));
        repaint();
      }
      else
      {
      	  //System.out.println("segundo: "+String.valueOf(barra.getValue())+" "+String.valueOf(zonaVisible)+" "+String.valueOf(0)+" "+String.valueOf(frases.size()*11+5));
	  barra.setValues(barra.getValue(), zonaVisible, 0,frases.size()*11+5);
      }    	
    }


    public void engadirFraseComposta(String cadea)
    {
      try
      {
	while (cadea.length()>0)
	{
	  engadirFrase(cadea.substring(1,cadea.indexOf(">")),
	               cadea.substring(cadea.indexOf(">")+1,cadea.indexOf("]")));
	  cadea = cadea.substring(cadea.indexOf("]")+1);
	}
      }catch(Exception e) {}
    }



}








/*************************************************************************************/
/******************************TEMPORIZADOR  CHAT ************************************/
/*************************************************************************************/
class TemporizadorChat implements Runnable 
{
    private Thread thread = null;
    Chat chat = null;
    
    TemporizadorChat( Chat chat ) 
    {
      this.chat = chat;
    }    

    // Aqui creamos un nuevo thread para correr el TemporizadorChat. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
      thread = new Thread( this );
      thread.start();
    }
 
    public void stop() 
    {
    }
    
    public void run() 
    {


          try {
              esperar(5000);       
          } catch (InterruptedException e) 
          {
          }
          catch (Exception e) 
          {
          }
          chat.nuevoTextoChat.setEnabled(true);
          chat.nuevoTextoChat.setBackground(Color.white);

             
    }    


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}




/*************************************************************************************/
/******************************CANVAS RELOXIO ************************************/
/*************************************************************************************/
class CanvasReloxio extends Canvas
{
  
  Chat chat;
  
  
  public CanvasReloxio(Chat chat)
  {
    this.chat=chat;
  }
  
  public void update(Graphics g)
  {
    paint(g);
  }  
  
  public void paint(Graphics g)
  {
    if(chat.imagenOculta != null)
      g.drawImage(chat.imagenOculta,0,0,null);
  }	
	
} 