import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.lang.*;

import java.util.*;
import java.text.NumberFormat;

import javax.swing.JButton;

public class AjedrezTutor extends Ajedrez
{
    JButton btnCerrar ;
    JButton btnAutomatico ;

    boolean automaticoAdiante=true;
    
    Panel relleno;
    
    JButton boton_tapiz_abajo;
    JButton boton_tapiz_arriba;
    
    String t;


    public AjedrezTutor(Salon sal,String partida,String t)
    {
      super(sal, partida, "TUTOR", "TUTOR", "b", "600", "600", "10", 0, "");
		              
      this.t=t;
      
      btnCerrar = new JButton("       "+salon.idioma.traducir("Cerrar")+"         ");
      btnAutomatico = new JButton("     "+salon.idioma.traducir("Detener")+"     ");      
   
      init();
   
      panel2.removeAll();
      panel2.setVisible(false);

      btnCerrar.setBackground(salon.colFondoBotos);
      btnCerrar.setForeground(salon.colTextoBotos);
      btnCerrar.setFont(fuenteBotones);
      panel2.add(btnCerrar);

      panel2.add(btnIni);
      panel2.add(btnAtras);
      panel2.add(btnAdelante);
      panel2.add(btnFin);
      
      btnAutomatico.setBackground(salon.colFondoBotos);
      btnAutomatico.setForeground(salon.colTextoBotos);
      btnAutomatico.setFont(fuenteBotones);
      panel2.add(btnAutomatico);
      
      panel2.add(chkComidas);
      panel2.validate();
      panel2.setVisible(true);
      panel2.repaint();

      reloj.para();
      remove(reloj);
      relojRival.para();
      remove(relojRival);
      
      mostrarPartida("");

      temporizador.intervalo=1000;
    }
    
    public void init()
    {
    	int i;
    	
    	super.init();
    	
    	//buscar canvas na lista de componentes e eliminalo
    	for(i=0;i<this.getComponentCount();i++)
    	{
    	  if(canvas == this.getComponent(i))
    	  {
    	    this.remove(i);
    	  }
    	}
    	
        canvas = new PanelDibujoTutor(figuras,login,texto_arriba,texto_abajo,this,TaboleiroX,TaboleiroY,TaboleiroTamanho,TaboleiroTamanho);
        add(canvas,0);
        canvas.setHabilitado(true); 
    }

/*

  public boolean action (Event evt, Object arg) 
  {

    
    if(evt.target.equals(btnAutomatico)) 
    {
      if(btnAutomatico.getText().compareTo("Automático >>")==0)
      {
        btnAutomatico.setText("     Detener     ");
        automaticoAdiante=true;
      }
      else if(btnAutomatico.getText().compareTo("     Detener     ")==0)
      {
        btnAutomatico.setText("Automático >>");
        automaticoAdiante=false;
      }
    }           
    else if(evt.target.equals(btnCerrar))  
      salon.quitarPestanha(this);
    else	
      return super.action(evt, arg);
	
 	
    return true;

  }

*/

    public void mostrarPartida(String color)
    {
        URL url;
        String cadeaPHP;

  
        inicio=false;
        btnAutomatico.setText(salon.idioma.traducir("Automático >>"));
        automaticoAdiante=false;          

        llamadas_pintar_tablero=0;

/*
            cadeaPHP="../php/partidaOnline.php?sala="+salon.sala+"&loginb="+login_b+"&loginn="+login_n+"&a="+((int)(Math.random()*100000));
            salon.enviarBD(cadeaPHP,
	                   String.valueOf(hashCode()),"visualizarPartida");
*/
      visualizarPartida("");
    }

    public void visualizarPartida(String entrada)
    {
       partida=entrada;

       posicion=partida.length();
       pintar_tablero();
    }


/******************************************************************************************/
/******************************* MENSAXE VER PARTIDAS *************************************/
/******************************************************************************************/
    public void mensajeVerPartidas(String entrada)
    {
    String mov="";
    int posicionMov=0;

    //temporizador.antibloqueo=0;
    
    //salon.chatPublico.engadirFrase("DEPURAR","mensajeVerPartidas, entrada: "+entrada);
 
    try
    {

      if (entrada.length() > 0)
      {

        mov = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        posicionMov = (Integer.valueOf(entrada.substring(0,entrada.indexOf("#")))).intValue();
        if (mov.length() > 0)
        {
          if( partida.length()<posicionMov) //chega movemento online e todavia non se lera todo da bd
          {
            try
            {
              esperar(6000);
            }catch(Exception e){}
            mostrarPartida(login);
          }
          else if( partida.length()==posicionMov) 
          {	
            partida = partida.substring(0,posicionMov)+mov;
            //if(!salon.pestanhas.tabbed.isShowing("Ver Partidas"))  //poñer pestaña verde
            //salon.pestanhas.tabbed.setTabColor("Ver Partidas",new Color(0,128,0));
      
            posicion_servidor_partida=partida.length();

            posicion = partida.length();

            direccion = "adelante";
            pintar_tablero();
          }
        }
      }
    } catch(Exception e) {}
      
    }


/***********************************************************************/
    public synchronized void enviar_comentario(String novaMensaxe) 
    {
      URL url;

      novaMensaxe = novaMensaxe.replace('#',' ');
      novaMensaxe = novaMensaxe.replace(' ','|');
	if (salon.conectado && novaMensaxe.length()>0)
      {
        chatPrivado.engadirFraseComposta(novaMensaxe.replace('|',' '));
	  //enviaOnline("C1#"+novaMensaxe);
        salon.enviar_mensaxe(loginRival,7,loginXogador+"]12"+novaMensaxe); 
      }

    }


/***********************************************************************/
  public synchronized void GardarMovemento() {
    URL url;
    String movimiento="",segundos="0",entrada="";
    int psp = 0;
    int longitudPartida;

    longitudPartida = partida.length();

    puedoProponerTablas = true;

    temporizador.antibloqueo=0;
    if (posicion_servidor_partida > 0) //gardaremos os dous últimos movementos
      psp = posicion_servidor_partida - 8;
    else
      psp = posicion_servidor_partida;

    movimiento = partida.substring(psp);

    if (movimiento.length() == 0)
      return;

    segundos = String.valueOf(canvas.segundosMovemento);
    relojRival.arranca();		  

    if (salon.conectado)
      salon.enviar_mensaxe(loginRival,7,loginXogador+"]11"+String.valueOf(reloj.segundos)+"#"+canvas.movimiento_salida+"#"+String.valueOf(longitudPartida-8)+"#");

    System.out.println("../php/GardarMovementoTutor.php?sala="+salon.sala+"&t="+t.substring(0,10)+"%20"+t.substring(11)+"&m="+movimiento+"&psp="+String.valueOf(psp)+"&a="+((int)(Math.random()*100000)));

    salon.enviarBD("../php/GardarMovementoTutor.php?sala="+salon.sala+"&t="+t.substring(0,10)+"%20"+t.substring(11)+"&m="+movimiento+"&psp="+String.valueOf(psp)+"&a="+((int)(Math.random()*100000)),
	           String.valueOf(hashCode()),"MovementoEnviadoCorrectamente");
  }


  public synchronized void MovementoEnviadoCorrectamente(String entrada) 
  {
        //se está pintando esperamos
        while(canvas.pintando)
	    try {
	      esperar(500);
          } catch( InterruptedException e ) {}


        posicion_servidor_partida=partida.length();

  }



  public void quienMueve()
  {
    super.quienMueve();
    canvas.setHabilitado(true);
  }


}







class PanelDibujoTutor extends PanelDibujo
{


  public PanelDibujoTutor(Vector figs,String log,String texto_arriba,String texto_abajo, Ajedrez ajed, int x, int y, int tamX, int tamY) 
  {
    super( figs, log, texto_arriba, texto_abajo, ajed, x, y, tamX, tamY);
  }


    public void mousePressed(MouseEvent e) {
	  int i;
        int nf = figuras.size();
	  String letra= new String("");
	  int coordenada;

        ajedrez.inicio=false;
	  fpulsada = null;
        e.consume();
        if (peon_arriba) {
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
	  else {
		 x1 = ((int)(e.getX()/tamanhoCelda));
		 y1 = ((int)(e.getY()/tamanhoCelda));
		 for (i=0; i < nf; i++) 
		 {
		   Figura f = (Figura)figuras.elementAt(i);
	      	   if (f.x == x1 &&
	               f.y == y1)
		     fpulsada = f;
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


    public void mouseReleased(MouseEvent e) 
    {
      int x,y,i;
      int nf = figuras.size();
      Figura f2;

      if (fpulsada==null)
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

      if (fpulsada != null && ((fpulsada.x != x1) ||(fpulsada.y != y1)) &&
          ((Integer.valueOf(movimiento_salida.substring(4,8))).intValue() == 9999) &&
          (ajedrez.posicion == ajedrez.partida.length())) 
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



}
