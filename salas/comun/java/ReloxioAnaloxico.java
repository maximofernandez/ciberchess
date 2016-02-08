import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.applet.*;


public class ReloxioAnaloxico extends Applet implements Runnable
{

  int centreX = 180;
  int centreY = 180;
  int radius = 180;

  private Thread t = null;
  private Hand sec,min,hour;
  private boolean shouldRun = true;
  private Graphics offGraphics;
  private Image imagen;
  
  ClienteReloxioAnaloxico clienteReloxioAnaloxico;
  
  Salon salon;
  
  int ancho,alto;


  public ReloxioAnaloxico(Salon salon, ClienteReloxioAnaloxico clienteReloxioAnaloxico, int hora, int minuto, int segundo, int ancho, int alto) 
  {
    this.salon=salon;
    this.clienteReloxioAnaloxico=clienteReloxioAnaloxico;

    fijarAnchoAlto(ancho,alto);
    fijarHora(hora,minuto,segundo);
  }
    
    
  public void fijarAnchoAlto(int ancho,int alto)
  {
    this.ancho=ancho;
    this.alto=alto;  	
    centreY=alto/2;
    centreX=ancho/2;
          
    if(ancho<alto)
      radius=ancho/2;
    else
      radius=alto/2;
      
    if(hour!=null && min!=null && sec!=null) //sustituir manetas por outras ca mesma hora pero distintas dimensións e centro   	
      fijarHora(hour.val, min.val, sec.val);
  }
    
  
  public void fijarHora(double hora, double minuto, double segundo)
  {
    sec = new Hand(segundo,radius-15,15,0);    //  value,length,neglen,Color,thick,
    min = new Hand(minuto,radius*5/7,9);
    hour = new Hand(hora,radius/2,14);
    pintar();
  }
  
  public double consultarHora()
  {
    return hour.val;
  }
  
  
  public double consultarMinuto()
  {
    return min.val;
  }
  
  
  public double consultarSegundo()
  {
    return sec.val;
  }    

    
  public Image imaxeReloxio()
  {
    return imagen;
  }


  public void start() 
  {
    shouldRun = true;
    t = new Thread(this);    
    t.start();
  }


  public void stop() 
  {
    t = null;
    shouldRun = false;
  }


  public void run() 
  {
    while(shouldRun) 
    {
      try
      {
	Thread.sleep(1000);
      
        sec.val++;
        if(sec.val == 60) 
        {
	  sec.val = 0;
	  min.val++;
        }
        if(min.val == 60) 
        {
	  min.val = 0;
	  hour.val++;
        }
        if(hour.val == 13)
          hour.val=1;
        pintar();
        }catch(Exception e){}
    }
  }

  private void setAllAngles() 
  {
    sec.angle = (sec.val)*Math.PI/30;
    min.angle = (min.val)*Math.PI/30 + (sec.val)*Math.PI/1800;
    hour.angle =(hour.val)*Math.PI/6 + (min.val)*Math.PI/360;
  }

  public void pintar() 
  {
    int x,y,z,t,mr;
    
    //crear la imagen oculta si es que no existe
    if (offGraphics == null) 
    {
      try
      {
        imagen = salon.createImage(ancho, alto);
        offGraphics = imagen.getGraphics(); 
      }
     catch(Exception e)
      {
      	return;
      }
    }

    //Borrar la imagen previa 
    offGraphics.setColor(salon.colFondoBotosClaro);
    offGraphics.fillRect(0, 0, ancho, alto);

    offGraphics.setColor(salon.colFondoBotos);
    for(int i=0;i<60;i+=5) 
    {
      if(i%15==0)
        offGraphics.drawLine(centreX+(int)((radius*14/15)*Math.sin(Math.toRadians(i*6))),centreY-(int)((radius*14/15)*Math.cos(Math.toRadians(i*6))),centreX+(int)(radius*Math.sin(Math.toRadians(i*6))),centreY-(int)(radius*Math.cos(Math.toRadians(i*6))));
      else if(i%5==0) 
        offGraphics.drawLine(centreX+(int)((radius*29/30)*Math.sin(Math.toRadians(i*6))),centreY-(int)((radius*29/30)*Math.cos(Math.toRadians(i*6))),centreX+(int)(radius*Math.sin(Math.toRadians(i*6))),centreY-(int)(radius*Math.cos(Math.toRadians(i*6))));


    }
    setAllAngles();


    hour.display(offGraphics);
    min.display(offGraphics);
    sec.display(offGraphics);

    offGraphics.setColor(salon.colFondoBotos);
    offGraphics.fillOval(centreX-3,centreY-3,6,6);
    offGraphics.setColor(salon.colFondoBotosClaro);
    offGraphics.fillOval(centreX-1,centreY-1,2,2);
 
    //Mostrar la imagen en pantalla
    //g.drawImage(imagen, 0, 0, this);
    
    clienteReloxioAnaloxico.eventoReloxioAnaloxico();
 
  }








class Hand
{
	 private int len;
	 private int negLen;
	 private Shape hand;
	 private double rectifier;
	 double angle;
	 double val;
	 AffineTransform af;

Hand(double value,int rec) 
{
	  len = 100;
	  val = value;
	  rectifier = Math.PI*rec/180;
	  initialize();
}

Hand(double value, int len,int rec) {
 this.len = len;
 this.val = value;
 negLen = 0;
 rectifier = Math.PI*rec/180;
 initialize();
}

Hand(double value, int len,int neg,int rec) {
 this.len = len;
 this.val = value;
 negLen = neg;
 rectifier = Math.PI*rec/180;
 initialize();
}

void initialize() {
 int x[] = new int[4];
 int y[] = new int[4];

 x[0] = centreX - negLen;
 y[0] = centreY;

 x[1] = centreX + (int)((len/2)*Math.cos(rectifier));
 y[1] = centreY - (int)((len/2)*Math.sin(rectifier));

 x[2] = centreX + len;
 y[2] = centreY;

 x[3] = centreX + (int)((len/2)*Math.cos(rectifier));
 y[3] = centreY + (int)((len/2)*Math.sin(rectifier));

 hand = new Polygon(x,y,4);
}

void display(Graphics g) {
Graphics2D g1 = (Graphics2D)g;
g.setColor(salon.colFondoBotos);
af = AffineTransform.getRotateInstance(angle-Math.PI/2,centreX,centreY);
g1.fill(af.createTransformedShape(hand));
g1.draw(af.createTransformedShape(hand));
}

}





}