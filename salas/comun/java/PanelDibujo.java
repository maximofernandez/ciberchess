import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;



/******************************************************************************************/
/******************************************************************************************/
/******************************* PANEL DIBUJO *********************************************/
/******************************************************************************************/
/******************************************************************************************/

class PanelDibujo extends Canvas implements MouseListener, MouseMotionListener 
{
    Vector figuras;
    String movimiento_salida="99999999",login,login_arriba,login_abajo;
    int x1,y1;
    Figura fpulsada=null;
    int retardo;
    Ajedrez ajedrez;
    int segundosMovemento;
    int rojox=0,rojoy=0,rojoz=0,rojot=0;    

    Graphics offGraphics;
    Image imagenOculta;
    boolean peon_arriba = false;
    TemporizadorPintar temporizadorPintar;
    Figura pa = null;

    boolean pintando = false;

    int tamanho=0;
    float tamanhoCelda=0;
    
    boolean axustarTamanhoFigura=false;
    
    String texto;
    
    boolean habilitado=true;


    public PanelDibujo(Vector figs,String log,String texto_arriba,String texto_abajo, Ajedrez ajed, int x, int y, int tamX, int tamY) 
    {
	ajedrez = ajed;
	setBackground(ajedrez.salon.colFondoBotos);
	addMouseMotionListener(this);
	addMouseListener(this);
	figuras = figs;
	login = log;
	login_arriba = texto_arriba;
	login_abajo  = texto_abajo;
	texto=ajedrez.ritmo+" min";

	retardo = 20;

        setBounds(x,y,tamX,tamY);
        tamanho=tamX;
        tamanhoCelda = (float)tamanho/10;
        if(Math.abs(tamanhoCelda-ajedrez.pb.getWidth(null))/tamanhoCelda>0.1)
          axustarTamanhoFigura=true;
    }


    public void mousePressed(MouseEvent e) 
    {
        int i;
        int nf = figuras.size();
        String letra= new String("");
        int coordenada;
        URL url=null;


        if(coordenadasPublicidade(e.getX(),e.getY()))
        {
	  try 
	  { 
	    url = new URL(ajedrez.salon.webPublicidade);
	    (ajedrez.salon.getAppletContext()).showDocument (url, "_blank");
            ajedrez.salon.enviarBD("../php/publicidade.php?sala="+ajedrez.salon.sala+"&login="+ajedrez.salon.login+"&a="+((int)(Math.random()*100000)),
	       String.valueOf(hashCode()),"");	    
	  } 
	  catch(IOException ee) 
	  {
	    System.out.println( ajedrez.salon.idioma.traducir("Error al abrir URL:")+" " + url.toString()); 
	  }          
	}        

        if(!habilitado)
          return;

        ajedrez.inicio=false;
	  fpulsada = null;
        e.consume();
        if (peon_arriba) 
        {
            if (pa.nombre.substring(0,1).compareTo(new String("p")) == 0) 
              return;
		coordenada = (Integer.valueOf(movimiento_salida.substring(7))).intValue();
		temporizadorPintar.stop();
	  	temporizadorPintar = null;
		if (coordenada == 1 && pa.nombre.compareTo(new String("db")) == 0) letra = "a";
		if (coordenada == 1 && pa.nombre.compareTo(new String("tb")) == 0) letra = "b";
		if (coordenada == 1 && pa.nombre.compareTo(new String("ab")) == 0) letra = "c";
		if (coordenada == 1 && pa.nombre.compareTo(new String("cb")) == 0) letra = "d";
		if (coordenada == 1 && pa.nombre.compareTo(new String("dn")) == 0) letra = "e";
		if (coordenada == 1 && pa.nombre.compareTo(new String("tn")) == 0) letra = "f";
		if (coordenada == 1 && pa.nombre.compareTo(new String("an")) == 0) letra = "g";
		if (coordenada == 1 && pa.nombre.compareTo(new String("cn")) == 0) letra = "h";
		if (coordenada == 8 && pa.nombre.compareTo(new String("db")) == 0) letra = "i";
		if (coordenada == 8 && pa.nombre.compareTo(new String("tb")) == 0) letra = "j";
		if (coordenada == 8 && pa.nombre.compareTo(new String("ab")) == 0) letra = "k";
		if (coordenada == 8 && pa.nombre.compareTo(new String("cb")) == 0) letra = "l";
		if (coordenada == 8 && pa.nombre.compareTo(new String("dn")) == 0) letra = "m";
		if (coordenada == 8 && pa.nombre.compareTo(new String("tn")) == 0) letra = "n";
		if (coordenada == 8 && pa.nombre.compareTo(new String("an")) == 0) letra = "o";
		if (coordenada == 8 && pa.nombre.compareTo(new String("cn")) == 0) letra = "p";
		movimiento_salida = movimiento_salida.substring(0,movimiento_salida.length()-1)+letra;
		ajedrez.partida = ajedrez.partida.substring(0,ajedrez.partida.length()-1)+letra;
		peon_arriba = false;
		pa = null;
		ajedrez.panel2.setEnabled(true);
            pintarEenviar(true,true);
	}
	else 
	{
		 x1 = ((int)(e.getX()/tamanhoCelda));
		 y1 = ((int)(e.getY()/tamanhoCelda));
		 if (x1!=0 && x1!=9 && y1!=0 && y1!=9)
		 {

		  	for (i=0; i < nf; i++) 
		  	{
		      	  Figura f = (Figura)figuras.elementAt(i);
	      	          if (f.x == x1 &&
	            	        f.y == y1 &&
	            	        (f.nombre.substring(1,2).compareTo(login) == 0 ||
	            	         ajedrez instanceof AjedrezTutor))
			 	   	fpulsada = f;
		  	}
		}
	}
        if (fpulsada instanceof Figura)
        {
          setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
          rojox=x1;
          rojoy=y1;
          rojoz=0;
          rojot=0;
          pintarEenviar(false,false);
        }


    }


    public void setHabilitado(boolean sino)
    {
      habilitado=sino;
    }


    public void mouseDragged(MouseEvent e) {
      
    }

    public void mouseMoved(MouseEvent e) 
    {
      int x,y;
      
      if(!ajedrez.salon.publicidade)	
    	return;
    	
      x = e.getX();
      y = e.getY();
      
      //cambiar o cursor para publicidade
      if (coordenadasPublicidade(x,y))
        setCursor(new Cursor(Cursor.HAND_CURSOR));
      else    	
    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      
    }
    
    public boolean coordenadasPublicidade(int x, int y)
    {
	if(!ajedrez.salon.publicidade)  
	  return false;
 
	if(!ajedrez.chkComidas.isSelected())
	{
	  int axusteY=(int)(tamanho-ajedrez.salon.imaxePublicidade.getHeight(null)-tamanhoCelda);
	  int axusteX=(int)((tamanhoCelda-ajedrez.salon.imaxePublicidade.getWidth(null))/5);
	  
	  if(x>=axusteX && x<axusteX+ajedrez.salon.imaxePublicidade.getWidth(null) &&
	     y>=axusteY && y<axusteY+ajedrez.salon.imaxePublicidade.getHeight(null))
	    return true;
	
	  if(x>=(int)(tamanho-tamanhoCelda+axusteX*4)+2 && x<(int)(tamanho-tamanhoCelda+axusteX*4)+2+ajedrez.salon.imaxePublicidade.getWidth(null) &&
	     y>=axusteY && y<axusteY+ajedrez.salon.imaxePublicidade.getHeight(null))
	    return true;	  
        }  
        
        int axusteX=tamanho/2-ajedrez.salon.imaxePublicidadeArriba1.getWidth(null)/2-2;        
          
	if(x>=axusteX && x<axusteX+ajedrez.salon.imaxePublicidadeArriba1.getWidth(null) &&
	   y<ajedrez.salon.imaxePublicidadeArriba1.getHeight(null))
	  return true;          
          
        return false;
    }    

    public void mouseReleased(MouseEvent e) 
    {
      int x,y,i;
      int nf = figuras.size();
      Figura f2;

      if (fpulsada==null)
        return; 
      if (!habilitado)
        return;  
      movimiento_salida="99999999";

      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      e.consume();
      x = e.getX();
      y = e.getY();
      if (x > tamanho-1)
        x = tamanho-1;
      if (y > tamanho-1)
        y = tamanho-1;

      x1 = ((int)(x/tamanhoCelda));
      y1 = ((int)(y/tamanhoCelda));

      if (ajedrez.reloj.tempoRestante() > 0 &&
          //ajedrez.metocamover &&
          x1!=0 && x1!=9 && y1!=0 && y1!=9 &&
          fpulsada != null && ((fpulsada.x != x1) ||(fpulsada.y != y1)) &&
          ((Integer.valueOf(movimiento_salida.substring(4,8))).intValue() == 9999) &&
          (ajedrez.posicion == ajedrez.partida.length())&&
          ajedrez.valido(fpulsada.x,fpulsada.y,x1,y1,fpulsada.nombre) ) 
      {
        f2 = null;
	for (i=0; i < nf; i++) 
	{
	  Figura f = (Figura)figuras.elementAt(i);
          if (f.x == x1 && f.y == y1 )
            f2 = f;
        }
        if (f2 != null)
          mueve_fuera(f2);

         /* comer al paso de blancas(peon movendose en diagonal sen comer) */
         if(fpulsada.nombre.compareTo("pb")==0 &&   
         (fpulsada.x == x1+1 || fpulsada.x == x1-1 ) &&
         f2 == null) 
         {
           for (i=0; i < nf; i++)  //buscamos peon comido o paso para quitalo do taboleiro
           { 
	     Figura f = (Figura)figuras.elementAt(i);
             if (f.x == x1 && f.y == y1+1 )
               f2 = f;
           }
           if (f2 != null)
             mueve_fuera(f2);
         }

         /* comer al paso de negras(peon movendose en diagonal sen comer) */
         if(fpulsada.nombre.compareTo("pn")==0 &&   
           (fpulsada.x == x1+1 || fpulsada.x == x1-1 ) &&
            f2 == null) 
         {
           for (i=0; i < nf; i++) //buscamos peon comido o paso para quitalo do taboleiro
           { 
             Figura f = (Figura)figuras.elementAt(i);
             if (f.x == x1 && f.y == y1+1 )
               f2 = f;
           }
           if (f2 != null)
             mueve_fuera(f2);
         }



         if (login.compareTo(new String("n")) == 0) 
           movimiento_salida = "9999" + String.valueOf(9-fpulsada.x)+String.valueOf(9-fpulsada.y)+String.valueOf(9-x1)+String.valueOf(9-y1);
         else 
           movimiento_salida = "9999" + String.valueOf(fpulsada.x)+String.valueOf(fpulsada.y)+String.valueOf(x1)+String.valueOf(y1);
           
         if (f2 != null)
         {
           if (login.compareTo(new String("n")) == 0) 
             movimiento_salida = String.valueOf(9-f2.z)+String.valueOf(9-f2.t)+String.valueOf(9-f2.x)+String.valueOf(9-f2.y)+movimiento_salida.substring(4,8);
           else
             movimiento_salida = String.valueOf(f2.z)+String.valueOf(f2.t)+String.valueOf(f2.x)+String.valueOf(f2.y)+movimiento_salida.substring(4,8);
         }
	
         /* enroque corto de negras */
         if(fpulsada.nombre.compareTo("rn")==0 && fpulsada.x == x1+2 ) 
         {
           for (i=0; i < nf; i++) 
           {
             Figura f = (Figura)figuras.elementAt(i);
             if (f.x == 1 && f.y == 8 ) 
             {
               f.x = 3;
               f.y = 8;
             }
           }
           movimiento_salida = "81615171";
         }
         
         /* enroque largo de negras */
         if(fpulsada.nombre.compareTo("rn")==0 && fpulsada.x == x1-2 ) 
         {
           for (i=0; i < nf; i++) 
           {
             Figura f = (Figura)figuras.elementAt(i);
             if (f.x == 8 && f.y == 8 ) 
             {
               f.x = 5;
               f.y = 8;
             }
           }
           movimiento_salida = "11415131";
         }
         
	/* enroque corto de blancas */
        if(fpulsada.nombre.compareTo("rb")==0 && fpulsada.x == x1-2 ) 
        {
          for (i=0; i < nf; i++) 
          {
            Figura f = (Figura)figuras.elementAt(i);
            if (f.x == 8 && f.y == 8 ) 
            {
              f.x = 6;
              f.y = 8;
            }
          }
          movimiento_salida = "88685878";
        }
        
        /* enroque largo de blancas */
        if(fpulsada.nombre.compareTo("rb")==0 && fpulsada.x == x1+2 ) 
        {
          for (i=0; i < nf; i++) 
          {
            Figura f = (Figura)figuras.elementAt(i);
            if (f.x == 1 && f.y == 8 ) 
            {
              f.x = 4;
              f.y = 8;
            }
          }
          movimiento_salida = "18485838";
        }

        if(movimiento_salida.length()>8) //validación para intentar eliminar extraño error que aparece un díxito demais no movemento
          return;

        ajedrez.partida = ajedrez.partida+movimiento_salida;
        ajedrez.posicion += 8;
        fpulsada.x = x1;
        fpulsada.y = y1;
        rojox=0;
        rojoy=0;
        rojoz=0;
        rojot=0;

        /* si no es peon arriba enviamos */
	if ((fpulsada.nombre).substring(0,1).compareTo(new String("p"))==0 &&(fpulsada.y==1 || fpulsada.y==8)&& fpulsada.x!=0 && fpulsada.x!=9);
	else 
          pintarEenviar(true,true);
      }
      pintarEenviar(false,false);             
        
    }

    public void mouseEntered(MouseEvent e) 
    {
    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseExited(MouseEvent e) 
    {
    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseClicked(MouseEvent e) 
    {
    }


    public void paint(Graphics g) 
    {
      pintarEenviar(false,false);
    }

    public synchronized void pintarEenviar(boolean enviar,boolean obligatorio) 
    {
      Graphics g;

      int nf = figuras.size();
      int i;
      float trayecto=tamanho;
      long tiempoInicio;

      if (pintando)
      {
      	if(obligatorio)
      	  while(pintando)
      	  {
      	    try
      	    {
      	      ajedrez.esperar(100);
      	    } catch(Exception e) {}  
      	  }  
      	else
          return;
      }
        
      pintando=true;

      try
      {
        g=getGraphics();

        //guardar el tiempo al iniciar
        tiempoInicio = System.currentTimeMillis();

        if (enviar)
        {
          
          if (ajedrez.incrementos>0 && ((ajedrez.partida.length()+8)/16) < 40) //Hai incrementos ata o movemento 40
            if (ajedrez.reloj.gastados<ajedrez.incrementos)  // sistema bronstein
            {
              segundosMovemento=0;
              ajedrez.reloj.para(ajedrez.reloj.segundos);
            }  
            else  
            {
              segundosMovemento=ajedrez.reloj.gastados - ajedrez.incrementos;
              ajedrez.reloj.para(ajedrez.reloj.segundos - segundosMovemento);          
            }  
          else
            segundosMovemento=ajedrez.reloj.para();
        
          if(ajedrez.reloj.tempoRestante() <= 0)
          {
            pintando=false;
            return;
          }

          ajedrez.GardarMovemento(); 
        }

        ajedrez.quienMueve();

        for(i=0; i < nf; i++) 
	{
	  Figura f = (Figura)figuras.elementAt(i);
          if(f.x != f.z || f.y != f.t)
            trayecto = 0;
 	}
 
        pintarFotograma(g,trayecto);
        trayecto+=tamanho/16.0;
        for( ; trayecto<tamanho ; trayecto+=tamanho/16.0) 
        {
	    
            //Retardo entre fotograma y fotograma
	    tiempoInicio += retardo;
          if(trayecto < tamanho)
          {
            int w;
            w = (int) (tiempoInicio - System.currentTimeMillis());
            if (w>0) 
	    {
	        ajedrez.esperar(w);
	        pintarFotograma(g,trayecto);
            }
          }
      	}
      	pintarFotograma(g,tamanho);


	for(i=0; i < nf; i++) 
	{
	  Figura f = (Figura)figuras.elementAt(i);
          f.z = f.x;
          f.t = f.y;
	  if (!(temporizadorPintar instanceof TemporizadorPintar) && 
            (f.nombre).substring(0,1).compareTo(new String("p"))==0 &&(f.y==1 || f.y==8)&& f.x!=0 && f.x!=9) 
          {
            pa = f;
            temporizadorPintar = new TemporizadorPintar(this);
            temporizadorPintar.start();
	    peon_arriba = true;
            setHabilitado(true);
            ajedrez.panel2.setEnabled(false);
	  }	
	}
      } catch(Exception e){}
      pintando=false;

    }

    public synchronized void pintarFotograma(Graphics g,float trayecto) 
    {
	int i,j;
	int nf = figuras.size();

        //crear la imagen oculta si es que no existe
        if (offGraphics == null) 
        {
//          try
  //        {
            imagenOculta = createImage(tamanho, tamanho);
            offGraphics = imagenOculta.getGraphics();
    //      }
      //    catch (Exception e) {}  
        }

        //Borrar la imagen previa 
        offGraphics.setColor(ajedrez.salon.colFondoBotos);
        offGraphics.fillRect(0, 0, tamanho, tamanho);

       
	/* pintar casillas */
	offGraphics.setColor(ajedrez.salon.colFondoBotos.darker());
	for (i=0; i < 8;i++) {
  	   for (j = (i + 1) % 2; j < 8; j += 2) {
		offGraphics.fillRect((int)(tamanhoCelda+i*tamanhoCelda),(int)(tamanhoCelda+j*tamanhoCelda),(int)tamanhoCelda,(int)tamanhoCelda);
	   }
	}

	offGraphics.setColor(Color.white);
	for (i=0; i < 8;i++) {
  	   for (j = i % 2; j < 8; j += 2) {
		offGraphics.fillRect((int)(tamanhoCelda+i*tamanhoCelda),(int)(tamanhoCelda+j*tamanhoCelda),(int)tamanhoCelda,(int)tamanhoCelda);
	   }
	} 

	offGraphics.setFont(new Font("Arial", 0, 18));
	offGraphics.setColor(ajedrez.salon.colTextoBotos);
	offGraphics.drawString(login_arriba,tamanho/2-(login_arriba.length()*10/2),(int)tamanhoCelda/2+13);
	offGraphics.drawString(login_abajo,tamanho/2-(login_abajo.length()*10/2),tamanho+7-(int)tamanhoCelda/2);

	offGraphics.setFont(new Font("Courier", 0,11));
	offGraphics.setColor(ajedrez.salon.colTextoBotos);	  
        offGraphics.drawString(texto, tamanho/2-(texto.length()*7/2), tamanho-2);

        offGraphics.drawString(numMovs()+" movs", 10, tamanho-2);
        offGraphics.drawString(numMovsRival()+" movs", 10, 9);
        
	/* pintar publicidade se hai */  
	if(ajedrez.salon.publicidade)  
	{ 
	  if(!ajedrez.chkComidas.isSelected())
	  {
	    int axusteY=(int)tamanhoCelda;
	    int axusteX=(int)((tamanhoCelda-ajedrez.salon.imaxePublicidade.getWidth(null))/5);
            offGraphics.drawImage(ajedrez.salon.imaxePublicidade,axusteX,axusteY,ajedrez.salon.imaxePublicidade.getWidth(null),(int)(tamanho*8/10),null);
            offGraphics.drawImage(ajedrez.salon.imaxePublicidade,(int)(tamanho-tamanhoCelda+axusteX*4)+2,axusteY,ajedrez.salon.imaxePublicidade.getWidth(null),(int)(tamanho*8/10),null);
          }  
          
          int axusteX=tamanho/2-ajedrez.salon.imaxePublicidadeArriba1.getWidth(null)/2-2;
          if(trayecto>=tamanho)
            offGraphics.drawImage(ajedrez.salon.imaxePublicidadeArriba1,axusteX,0,null);
          else
            offGraphics.drawImage(ajedrez.salon.imaxePublicidadeArriba2,axusteX,0,null);
        }

	/* pintar figuras */
	for (i=0; i < nf; i++) {
	    Figura f = (Figura)figuras.elementAt(i);
          if (ajedrez.chkComidas.isSelected() ||
             (f.x>0 && f.x<9 && f.y>0 && f.y<9) ||
             ((f.x!=f.z) || f.y!=f.t)&&trayecto<tamanho)
          {
            if(axustarTamanhoFigura)
	      offGraphics.drawImage(f.imagen,(int) (f.z*tamanhoCelda+trayecto/10*(f.x-f.z)),(int) (f.t*tamanhoCelda+trayecto/10*(f.y-f.t)),(int)tamanhoCelda+2,(int)tamanhoCelda+2,null);
	    else
	      offGraphics.drawImage(f.imagen,(int) (f.z*tamanhoCelda+trayecto/10*(f.x-f.z)),(int) (f.t*tamanhoCelda+trayecto/10*(f.y-f.t)),null);
            if (((f.x!=f.z) || f.y!=f.t)&&trayecto==tamanho)
            { 
              if(rojox==0)
              {
                rojox=f.x;
                rojoy=f.y;
              }
              else
              {
                rojoz=f.x;
                rojot=f.y;
              }
            }
          }
	}

      //rectángulos rojos
      if (trayecto==tamanho)
      {
        if(!ajedrez.inicio)
        {
	    offGraphics.setColor(Color.red);
          if(rojox>0 && rojox<9 && rojoy>0 && rojoy<9)
            offGraphics.drawRect((int)(tamanhoCelda*rojox),(int)(tamanhoCelda*rojoy),(int)(tamanhoCelda-1),(int)(tamanhoCelda-1));
          if(rojoz>0 && rojoz<9 && rojot>0 && rojot<9)
            offGraphics.drawRect((int)(tamanhoCelda*rojoz),(int)(tamanhoCelda*rojot),(int)(tamanhoCelda-1),(int)(tamanhoCelda-1));
        }
      }

      /* números e letras coordenadas */
	offGraphics.setFont(new Font("Courier", 0, 11));
	offGraphics.setColor(ajedrez.salon.colTextoBotos);
      if(login.compareTo(new String("b"))==0)
      {
        for (i=1;i<9;i++)
        {
	  offGraphics.drawString(String.valueOf(i),(int)(tamanhoCelda-7),(int)(tamanho-tamanhoCelda*(i+0.5))+6);
          offGraphics.drawString(String.valueOf((char)('a'+i-1)),(int)(tamanhoCelda*(i+0.5))-3,(int)(tamanho-tamanhoCelda+8));
        }
      }
      else
      {
        for (i=8;i>0;i--)
        {
	  offGraphics.drawString(String.valueOf(i),(int)(tamanhoCelda-7),(int)(tamanho-tamanhoCelda*(9.5-i))+6);
          offGraphics.drawString(String.valueOf((char)('a'+8-i)),(int)(tamanhoCelda*(i+0.5))-3,(int)(tamanho-tamanhoCelda+8));
        }
      }

      //Mostrar la imagen en pantalla
      g.drawImage(imagenOculta, 0, 0, this);

    }

    public void update(Graphics g)
    {
      paint(g);
    }

    private String numMovs()
    {  
	if (ajedrez.login.compareTo("b") == 0)
	  return String.valueOf(ajedrez.partida.length()/8/2+ajedrez.partida.length()/8%2);
	else
	  return String.valueOf(ajedrez.partida.length()/8/2);
	  
    }

    private String numMovsRival()
    {
	if (ajedrez.login.compareTo("b") == 0)
	  return String.valueOf(ajedrez.partida.length()/8/2);
	else
	  return String.valueOf(ajedrez.partida.length()/8/2+ajedrez.partida.length()/8%2);	  
    }

    public void mueve_fuera(Figura f) {
	int pos[][] = new int[16][2];
	int i,j = 0;
      int nf = figuras.size();
	int libre = -1;
	
	pos[0][0]=0;
	pos[0][1]=4;
	pos[1][0]=0;
	pos[1][1]=3;
	pos[2][0]=0;
	pos[2][1]=2;
	pos[3][0]=0;
	pos[3][1]=1;
	pos[4][0]=0;
	pos[4][1]=0;
	pos[5][0]=9;
	pos[5][1]=4;
	pos[6][0]=9;
	pos[6][1]=3;
	pos[7][0]=9;
	pos[7][1]=2;
	pos[8][0]=9;
	pos[8][1]=1;
	pos[9][0]=9;
	pos[9][1]=0;
	pos[10][0]=1;
	pos[10][1]=0;
	pos[11][0]=8;
	pos[11][1]=0;
	pos[12][0]=2;
	pos[12][1]=0;
	pos[13][0]=7;
	pos[13][1]=0;
	pos[14][0]=3;
	pos[14][1]=0;
	pos[15][0]=6;
	pos[15][1]=0;

        if(f.nombre.substring(1,2).compareTo(ajedrez.login) != 0)
        {
	  for(i=0;libre < 0;i++) 
	  {
	    libre = i;
	    for (j=0; j < nf; j++) 
	    {
	       Figura h = (Figura)figuras.elementAt(j);
             if (h.x == pos[i][0] && h.y == pos[i][1] ) 
			libre = -1;
	    }	
          }
	  f.x = pos[libre][0];
	  f.y = pos[libre][1];
	}
        else  //movo fora unha das miñas, só en AjedrezTutor
        {
	  for(i=0;libre < 0;i++) 
	  {
	    libre = i;
	    for (j=0; j < nf; j++) 
	    {
	       Figura h = (Figura)figuras.elementAt(j);
               if (h.x == 9-pos[i][0] && h.y == 9-pos[i][1] ) 
			libre = -1;
	    }	
          }
	  f.x = 9-pos[libre][0];
	  f.y = 9-pos[libre][1];
	}	
    }




}


