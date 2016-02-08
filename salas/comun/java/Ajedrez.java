import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.lang.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.*;
import java.text.NumberFormat;

public class Ajedrez extends Applet
{
    Image pb,tb,cb,ab,db,rb,pn,tn,cn,an,dn,rn;       /* imagenes de figuras y logotipo */
    PanelDibujo canvas;                              /* tablero de ajedrez */
    Vector figuras = new Vector();                   /* vector donde almacenar las figuras */
    String partida;                                  /* cadena con los movimientos */
    String login_b,login_n,login,texto_arriba,texto_abajo,servidor,portoTableiro,loginXogador,loginRival,ritmo,t; /* parametros de entrada */
    JButton btnRendir;
    JButton btnAdelante;
    JButton btnAtras;
    JButton btnFin;
    JButton btnIni;
    JButton btnTablas;
    JCheckBox chkComidas;

    int posicion;
    String direccion = "adelante";
    int posicion_servidor_partida;
    
    boolean metocamover = false;
    TemporizadorAjedrez temporizador;
    Panel panel2 = new Panel();

    boolean rey_movido = false;
    boolean torre_derecha_movida = false;
    boolean torre_izquierda_movida = false;
	
    Salon salon;

    Font fuenteBotones = new Font("Arial",3,10);

    Chat chatPrivado;

    Reloj reloj,relojRival;
    int tempo,tempoRival;
   
    boolean inicio = true;
    
    Panel fondo=new Panel();

    long llamadas_pintar_tablero=0;

    int TaboleiroTamanho;
    int TaboleiroX=2;
    int TaboleiroY=2;

    boolean puedoProponerTablas=true;
    boolean mostrandoConfirmacionRendirse=false;
    
    Ajedrez ajedrez;
    
    int incrementos;
    
    int reconexionsRival=0;

    public Ajedrez(Salon sal,String partida,String login_b,String login_n,
		       String login,String tb,String tn,String ritmo,int inc,String t)
    {
	  salon=sal;

	  this.partida = partida;
	  this.login_b = login_b;
	  this.login_n = login_n;
	  this.login = login;
	  this.ritmo = ritmo;
	  this.t=t;
	  incrementos=inc;
	  if(login.compareTo("b")==0)
          {
	    tempo = (Integer.valueOf(tb)).intValue();
	    tempoRival = (Integer.valueOf(tn)).intValue();
          }
	  else
	  {
	    tempo = (Integer.valueOf(tn)).intValue();
	    tempoRival = (Integer.valueOf(tb)).intValue();
	  }

        TaboleiroTamanho=salon.getBounds().height-45;

        ajedrez=this;

    }


    public void init() 
    {

        String cadea="";

        btnRendir = new JButton("       "+salon.idioma.traducir("Rendirse")+"       ");
        btnAdelante = new JButton(" > ");
        btnAtras = new JButton(" < " );
        btnFin = new JButton(" >| ");
        btnIni = new JButton(" |< ");
        btnTablas = new JButton(salon.idioma.traducir("Proponer tablas"));
        chkComidas = new JCheckBox(salon.idioma.traducir("Figs. comidas"));

	InputStream entrada = null;
	URLConnection conexion = null;
	
	setBackground(salon.colorSalon);

	pb = salon.pb;
	tb = salon.tb;
	cb = salon.cb;
	ab = salon.ab;
	db = salon.db;
	rb = salon.rb;
	pn = salon.pn;
	tn = salon.tn;
	cn = salon.cn;
	an = salon.an;
	dn = salon.dn;
	rn = salon.rn;

	posicion = partida.length();
	if(login.compareTo("b")==0) 
	{
	  loginXogador=login_b;
	  loginRival=login_n;
	}
	else
	{
	  loginXogador=login_n;
	  loginRival=login_b;
	}
	servidor = salon.getParameter("s");
	portoTableiro = salon.getParameter("pt");

	posicion_servidor_partida=partida.length();
	setLayout( new BorderLayout() );


	if (login.compareTo(new String("b")) == 0) 
	{
		texto_arriba = login_n; 
		texto_abajo = login_b;
		if (partida.length()%16 == 0) 
                {
			texto_abajo = "» "+texto_abajo;
			metocamover = true;
                }
		else 
		{
		  texto_arriba = "» "+texto_arriba;
		  metocamover = false;
		}
        }

	if (login.compareTo(new String("n")) == 0) 
        {
		texto_arriba = login_b; 
		texto_abajo = login_n; 
		if (partida.length()%16 == 0) 
		{
		  texto_arriba = "» "+texto_arriba;
		  metocamover = false;
		}
		else 
		{
		  texto_abajo = "» "+texto_abajo;
		  metocamover = true;
		}
        }


	chatPrivado=new Chat(loginXogador,4,false,54,this);
	chatPrivado.setBounds(3,46+4*(salon.contenedorOeste.getBounds().height-43)/5,salon.contenedorOeste.getBounds().width-3,(salon.contenedorOeste.getBounds().height-43)/5);
	chatPrivado.init();
	chatPrivado.repaint();

	panel2.setBounds(44+TaboleiroX+TaboleiroTamanho/2-202,TaboleiroTamanho+TaboleiroY,404,20);
	panel2.setLayout(new FlowLayout(1,2,0));
	panel2.setBackground(salon.colorSalon);
	add(panel2);

        btnRendir.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if(btnRendir.getText().compareTo(salon.idioma.traducir("Cerrar"))==0)
	      {
	        salon.quitarPestanha(ajedrez);
	      }
	      else
	        salon.confirmacionRendirse(ajedrez);
            }
          });
        btnRendir.setBackground(salon.colFondoBotos);
        btnRendir.setForeground(salon.colTextoBotos);
        btnRendir.setFont(fuenteBotones);
        panel2.add(btnRendir);
        
        btnIni.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if (posicion > 0) 
	      {
	        direccion = "atras";
	        posicion = 0;
	        pintar_tablero();
	      }
            }
          });
	btnIni.setBackground(salon.colFondoBotos);
	btnIni.setForeground(salon.colTextoBotos);
	btnIni.setFont(fuenteBotones);
	panel2.add(btnIni);
	
	btnAtras.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if (posicion > 0) 
	      {
	        direccion = "atras";
	        posicion -= 8;
	        pintar_tablero();
	      }
            }
          });
	btnAtras.setBackground(salon.colFondoBotos);
	btnAtras.setForeground(salon.colTextoBotos);
	btnAtras.setFont(fuenteBotones);
	panel2.add(btnAtras);
	
	btnAdelante.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if (posicion < partida.length()) 
	      {
	        direccion = "adelante";
	        posicion += 8;
	        pintar_tablero();
	      }            	
            }
          });
	btnAdelante.setBackground(salon.colFondoBotos);
	btnAdelante.setForeground(salon.colTextoBotos);
	btnAdelante.setFont(fuenteBotones);
	panel2.add(btnAdelante);
	
	btnFin.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if (posicion != partida.length()) 
	      {
	        direccion = "adelante";
	        posicion = partida.length();
	        pintar_tablero();
	      }
            }
          });
	btnFin.setBackground(salon.colFondoBotos);
	btnFin.setForeground(salon.colTextoBotos);
	btnFin.setFont(fuenteBotones);
	panel2.add(btnFin);
	
	btnTablas.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
	      if(btnTablas.getText().compareTo(salon.idioma.traducir("Cerrar"))==0)
	        salon.quitarPestanha(ajedrez);
	      else
	        proponerTablas();
            }
          });
        btnTablas.setBackground(salon.colFondoBotos);
        btnTablas.setForeground(salon.colTextoBotos);
        btnTablas.setFont(fuenteBotones);
        panel2.add(btnTablas);

        chkComidas.addActionListener(
          new ActionListener() 
          {
            public void actionPerformed(ActionEvent e) 
            {
      	      canvas.repaint();
            }
          });
	chkComidas.setFont(new Font("",2,10));
	chkComidas.setForeground(salon.colFondoBotos);
	chkComidas.setBackground(salon.colorSalon);
	panel2.add(chkComidas);

        int rt = 60*(Integer.valueOf(ritmo)).intValue();
        if(tempo<rt)
          tempo=tempo - (int)(rt/40); // penalización por desconexión
	reloj=new Reloj(tempo,this);
	reloj.init();
        reloj.start();
        reloj.setBounds(TaboleiroX+TaboleiroTamanho-77,TaboleiroY+TaboleiroTamanho-16,75,14);
        add(reloj);
        if(metocamover)
          reloj.arranca();

        relojRival=new Reloj(tempoRival,this);
	relojRival.init();
        relojRival.start();
        relojRival.setBounds(TaboleiroX+TaboleiroTamanho-77,TaboleiroY+2,75,14);
        add(relojRival);
        if(!metocamover)
          relojRival.arranca();

        canvas = new PanelDibujo(figuras,login,texto_arriba,texto_abajo,this,TaboleiroX,TaboleiroY,TaboleiroTamanho,TaboleiroTamanho);
        add(canvas);
        canvas.setHabilitado(metocamover);

        pintar_tablero();

        temporizador = new TemporizadorAjedrez(7000,this);
      
	temporizador.start(); 
	
	fondo.setBackground(salon.colorSalon);
        add(fondo);
  }
  
  
  public void paint(Graphics g)
  {
    super.paint(g) ;
    panel2.paint(panel2.getGraphics());
  }

    public void destroy()
    {
    	eliminarReferencias();
    } 

    public void eliminarReferencias()
    {
    	
      //System.out.println("Ajedrez.eliminarReferencias.Principio");

      chatPrivado.padre=null;
      
      if (temporizador!=null)
        temporizador.stop();
      try
      {
        canvas.temporizadorPintar.stop();
      }
      catch(Exception e){}
      canvas.ajedrez=null;
      chatPrivado=null;
      temporizador.ajedrez=null;
      temporizador=null;
      canvas.temporizadorPintar=null;
      canvas=null;
      reloj.stop();
      reloj.ajedrez=null;
      reloj=null;
      relojRival.stop();
      relojRival.ajedrez=null;
      relojRival=null;
      if (figuras != null)
        figuras.removeAllElements();
      figuras=null;

      pb=null;
      tb=null;
      cb=null;
      ab=null;
      db=null;
      rb=null;
      pn=null;
      tn=null;
      cn=null;
      an=null;
      dn=null;
      rn=null;
      partida=null;
      login_b=null;
      login_n=null;
      login=null;
      texto_arriba=null;
      texto_abajo=null;
      servidor=null;
      portoTableiro=null;
      loginXogador=null;
      loginRival=null;
      ritmo=null;
      btnRendir = null;
      btnAdelante = null;
      btnAtras = null;
      btnFin = null;
      btnIni = null;
      btnTablas =  null;
      direccion = null;
      panel2 = null;
	
      //salon = null;

      fuenteBotones = null;
      
      //salon.chatPublico.engadirFrase("DEPURAR","Ajedrez.eliminarReferencias.Final");    	      

    }

    public void finalize()
    {
      eliminarReferencias();
    }


    public synchronized void pintar_tablero() 
    {
	int mox,moy,mdx,mdy,i,p,nf;
	Figura figura_cambiada;
	
	if (canvas!=null)
	  while(canvas.pintando)
      	  {
      	    try
      	    {
      	      esperar(100);
      	    } catch(Exception e) {}  
      	  } 
	
      llamadas_pintar_tablero++;
	figuras.removeAllElements();

      if(llamadas_pintar_tablero>=2)
      {
        inicio=false;
      }
      canvas.rojox=0;
      canvas.rojoy=0;
      canvas.rojoz=0;
      canvas.rojot=0;

	if (inicio) 
      {
		figuras.addElement(new Figura(pn, 1,2,8,7, "pn"));
		figuras.addElement(new Figura(pn, 2,2,7,7, "pn"));
		figuras.addElement(new Figura(pn, 3,2,6,7, "pn"));
		figuras.addElement(new Figura(pn, 4,2,5,7, "pn"));
		figuras.addElement(new Figura(pn, 5,2,4,7, "pn"));
		figuras.addElement(new Figura(pn, 6,2,3,7, "pn"));
		figuras.addElement(new Figura(pn, 7,2,2,7, "pn"));
		figuras.addElement(new Figura(pn, 8,2,1,7, "pn"));
		figuras.addElement(new Figura(tn, 1,1,8,8, "tn"));
		figuras.addElement(new Figura(cn, 2,1,7,8, "cn"));
		figuras.addElement(new Figura(an, 3,1,6,8, "an"));
		figuras.addElement(new Figura(rn, 5,1,5,8, "rn"));
		figuras.addElement(new Figura(dn, 4,1,4,8, "dn"));
		figuras.addElement(new Figura(an, 6,1,3,8, "an"));
		figuras.addElement(new Figura(cn, 7,1,2,8, "cn"));
		figuras.addElement(new Figura(tn, 8,1,1,8, "tn"));
		figuras.addElement(new Figura(pb, 1,7,8,2, "pb"));
		figuras.addElement(new Figura(pb, 2,7,7,2, "pb"));
		figuras.addElement(new Figura(pb, 3,7,6,2, "pb"));
		figuras.addElement(new Figura(pb, 4,7,5,2, "pb"));
		figuras.addElement(new Figura(pb, 5,7,4,2, "pb"));
		figuras.addElement(new Figura(pb, 6,7,3,2, "pb"));
		figuras.addElement(new Figura(pb, 7,7,2,2, "pb"));
		figuras.addElement(new Figura(pb, 8,7,1,2, "pb"));
		figuras.addElement(new Figura(tb, 1,8,8,1, "tb"));
		figuras.addElement(new Figura(cb, 2,8,7,1, "cb"));
		figuras.addElement(new Figura(ab, 3,8,6,1, "ab"));
		figuras.addElement(new Figura(rb, 5,8,4,1, "rb"));
		figuras.addElement(new Figura(db, 4,8,5,1, "db"));
		figuras.addElement(new Figura(ab, 6,8,3,1, "ab"));
		figuras.addElement(new Figura(cb, 7,8,2,1, "cb"));
		figuras.addElement(new Figura(tb, 8,8,1,1, "tb"));
	
	}
	else {
		figuras.addElement(new Figura(pn, 1,2,1,2, "pn"));
		figuras.addElement(new Figura(pn, 2,2,2,2, "pn"));
		figuras.addElement(new Figura(pn, 3,2,3,2, "pn"));
		figuras.addElement(new Figura(pn, 4,2,4,2, "pn"));
		figuras.addElement(new Figura(pn, 5,2,5,2, "pn"));
		figuras.addElement(new Figura(pn, 6,2,6,2, "pn"));
		figuras.addElement(new Figura(pn, 7,2,7,2, "pn"));
		figuras.addElement(new Figura(pn, 8,2,8,2, "pn"));
		figuras.addElement(new Figura(tn, 1,1,1,1, "tn"));
		figuras.addElement(new Figura(cn, 2,1,2,1, "cn"));
		figuras.addElement(new Figura(an, 3,1,3,1, "an"));
		figuras.addElement(new Figura(rn, 5,1,5,1, "rn"));
		figuras.addElement(new Figura(dn, 4,1,4,1, "dn"));
		figuras.addElement(new Figura(an, 6,1,6,1, "an"));
		figuras.addElement(new Figura(cn, 7,1,7,1, "cn"));
		figuras.addElement(new Figura(tn, 8,1,8,1, "tn"));
		figuras.addElement(new Figura(pb, 1,7,1,7, "pb"));
		figuras.addElement(new Figura(pb, 2,7,2,7, "pb"));
		figuras.addElement(new Figura(pb, 3,7,3,7, "pb"));
		figuras.addElement(new Figura(pb, 4,7,4,7, "pb"));
		figuras.addElement(new Figura(pb, 5,7,5,7, "pb"));
		figuras.addElement(new Figura(pb, 6,7,6,7, "pb"));
		figuras.addElement(new Figura(pb, 7,7,7,7, "pb"));
		figuras.addElement(new Figura(pb, 8,7,8,7, "pb"));
		figuras.addElement(new Figura(tb, 1,8,1,8, "tb"));
		figuras.addElement(new Figura(cb, 2,8,2,8, "cb"));
		figuras.addElement(new Figura(ab, 3,8,3,8, "ab"));
		figuras.addElement(new Figura(rb, 5,8,5,8, "rb"));
		figuras.addElement(new Figura(db, 4,8,4,8, "db"));
		figuras.addElement(new Figura(ab, 6,8,6,8, "ab"));
		figuras.addElement(new Figura(cb, 7,8,7,8, "cb"));
		figuras.addElement(new Figura(tb, 8,8,8,8, "tb"));
	}
      nf = figuras.size();
	if (login.compareTo(new String("n")) == 0) {
		for (i=0; i < nf; i++) {
			Figura f = (Figura)figuras.elementAt(i);
				f.x = 9-f.x;
				f.y = 9-f.y;
				f.z = 9-f.z;
				f.t = 9-f.t;
		}
		for (p=0; p < posicion ; p += 4) {

			mox = 9 - (Integer.valueOf(partida.substring(p,p+1))).intValue();
			moy = 9 - (Integer.valueOf(partida.substring(p+1,p+2))).intValue();
			mdx = 9 - (Integer.valueOf(partida.substring(p+2,p+3))).intValue();
			figura_cambiada = null;
			if (partida.substring(p+3,p+4).compareTo("9") > 0 ) {
				figura_cambiada = decodifica(partida.substring(p+3,p+4));
				mdy = 9 - figura_cambiada.y;
			}
			else mdy = 9 - (Integer.valueOf(partida.substring(p+3,p+4))).intValue();
			for (i=0; i < nf; i++) {
				Figura f = (Figura)figuras.elementAt(i);
				if (f.x==mox && f.y==moy) {
					if(f.x == 1 && f.y ==8 && f.nombre.compareTo("tn")==0)
						torre_izquierda_movida = true;
					if(f.x == 8 && f.y ==8 && f.nombre.compareTo("tn")==0)
						torre_derecha_movida = true;
					if(f.x == 4 && f.y ==8 && f.nombre.compareTo("rn")==0)
						rey_movido = true;
					f.x = mdx;
					f.y = mdy;
					if (figura_cambiada != null) {
						f.imagen = figura_cambiada.imagen;
						f.nombre = figura_cambiada.nombre;
					}
  					if (direccion.compareTo("adelante") != 0 ||
                                  posicion - p > 8) {
					      f.z = mdx;
					      f.t = mdy;
					}
				}
			}
		}
		if(direccion.compareTo("atras") == 0) {
		   for (p=posicion;p<=posicion+4;p+=4) { 
			mox = 9 - (Integer.valueOf(partida.substring(p,p+1))).intValue();
			moy = 9 - (Integer.valueOf(partida.substring(p+1,p+2))).intValue();
			mdx = 9 - (Integer.valueOf(partida.substring(p+2,p+3))).intValue();
			figura_cambiada = null;
			if (partida.substring(p+3,p+4).compareTo("9") > 0) {
				figura_cambiada = decodifica(partida.substring(p+3,p+4));
				mdy = 9 - figura_cambiada.y;
			}
			else mdy = 9 - (Integer.valueOf(partida.substring(p+3,p+4))).intValue();
			for (i=0; i < nf; i++) {
				Figura f = (Figura)figuras.elementAt(i);
				if (f.x==mox && f.y==moy) {
					f.z = mdx;
					f.t = mdy;
				}
			}
               }
	      }
	}
	else {
		for (p=0; p < posicion ; p += 4) {
			mox = (Integer.valueOf(partida.substring(p,p+1))).intValue();
			moy = (Integer.valueOf(partida.substring(p+1,p+2))).intValue();
			mdx = (Integer.valueOf(partida.substring(p+2,p+3))).intValue();
			figura_cambiada = null;
			if (partida.substring(p+3,p+4).compareTo("9") > 0) {
				figura_cambiada = decodifica(partida.substring(p+3,p+4));
				mdy = figura_cambiada.y;
			}
			else mdy = (Integer.valueOf(partida.substring(p+3,p+4))).intValue();
			for (i=0; i < nf; i++) {
				Figura f = (Figura)figuras.elementAt(i);
				if (f.x==mox && f.y==moy) {
					if(f.x == 1 && f.y ==8 && f.nombre.compareTo("tb")==0)
						torre_izquierda_movida = true;
					if(f.x == 8 && f.y ==8 && f.nombre.compareTo("tb")==0)
						torre_derecha_movida = true;
					if(f.x == 5 && f.y ==8 && f.nombre.compareTo("rb")==0)
						rey_movido = true;
					f.x = mdx;
					f.y = mdy;
					if (figura_cambiada != null) {
						f.imagen = figura_cambiada.imagen;
						f.nombre = figura_cambiada.nombre;
					}
  					if (direccion.compareTo("adelante") != 0 ||
                                  posicion - p > 8) {
					      f.z = mdx;
					      f.t = mdy;
					}
				}
			}
		}
		if(direccion.compareTo("atras") == 0) {
		   for (p=posicion;p<=posicion+4;p+=4) { 
			mox = (Integer.valueOf(partida.substring(p,p+1))).intValue();
			moy = (Integer.valueOf(partida.substring(p+1,p+2))).intValue();
			mdx = (Integer.valueOf(partida.substring(p+2,p+3))).intValue();
			figura_cambiada = null;
			if (partida.substring(p+3,p+4).compareTo("9") > 0) {
				figura_cambiada = decodifica(partida.substring(p+3,p+4));
				mdy = figura_cambiada.y;
			}
			else mdy = (Integer.valueOf(partida.substring(p+3,p+4))).intValue();
			for (i=0; i < nf; i++) {
				Figura f = (Figura)figuras.elementAt(i);
				if (f.x==mox && f.y==moy) {
					f.z = mdx;
					f.t = mdy;
				}
			}
               }
	      } 
	}

	if (canvas != null)
          canvas.pintarEenviar(false,true);
        
    }

    
    public Figura decodifica(String letra) {
	Figura figura = new Figura(db, 0,0,0,0, "db");

        if (letra.compareTo(new String("a")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = db;
	    figura.nombre = "db";
        }
        else if (letra.compareTo(new String("b")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = tb;
	    figura.nombre = "tb";
        }
        else if (letra.compareTo(new String("c")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = ab;
	    figura.nombre = "ab";
        }
        else if (letra.compareTo(new String("d")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = cb;
	    figura.nombre = "cb";
        }
        else if (letra.compareTo(new String("e")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = dn;
	    figura.nombre = "dn";
        }
        else if (letra.compareTo(new String("f")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = tn;
	    figura.nombre = "tn";
        }
        else if (letra.compareTo(new String("g")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = an;
	    figura.nombre = "an";
        }
        else if (letra.compareTo(new String("h")) == 0 ) 
        {
	    figura.y = 1;
	    figura.imagen = cn;
	    figura.nombre = "cn";
        }
        else if (letra.compareTo(new String("i")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = db;
	    figura.nombre = "db";
        }
        else if (letra.compareTo(new String("j")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = tb;
	    figura.nombre = "tb";
        }
        else if (letra.compareTo(new String("k")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = ab;
	    figura.nombre = "ab";
        }
        else if (letra.compareTo(new String("l")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = cb;
	    figura.nombre = "cb";
        }
        else if (letra.compareTo(new String("m")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = dn;
	    figura.nombre = "dn";
        }
        else if (letra.compareTo(new String("n")) == 0 ) 
        {
	    figura.y =8;
	    figura.imagen = tn;
	    figura.nombre = "tn";
        }
        else if (letra.compareTo(new String("o")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = an;
	    figura.nombre = "an";
        }
        else if (letra.compareTo(new String("p")) == 0 ) 
        {
	    figura.y = 8;
	    figura.imagen = cn;
	    figura.nombre = "cn";
        } 

	return figura;
    }


/*****************************************************************************/
/*****COMPROBACION DE MATE OU AFOGADO ****************************************/
// devolve 0: nin mate nin afogado
//         1: mate
//         2: afogado
/*****************************************************************************/

    public int estaMortoAfogadoDousreiesTresmovementosiguais()
     {
	int i,j,k,nf,reyx=0,reyy=0,contador=0,lp;
	Figura f;
	boolean jaque=false;
	
	//Comprobar se se fixeron tres movementos iguais
	lp=partida.length();
	if(lp>=10*8)
	{
	  for(i=lp;i>lp-6*8;i-=8)
	    if(!partida.substring(i-8,i).equals(partida.substring(i-40,i-32)))
	      break;
	  if(i<=lp-6*8)
	    return 4;
	}
	
	//Comprobar se só quedan dous reies no tableiro
	nf = figuras.size();
	for (i=0; i < nf; i++) 
	{
	  f = (Figura)figuras.elementAt(i);
          if (f.x!=0 && f.y!=0 && f.x!=9 && f.y!=9)
            contador++;
        }
        if (contador<=2)
          return 3;  //só quedan os dous reies

	girarTableiro();//giramos tableiro para comprobar esto é logo deixámolo como estaba
	//comprobarei se algunha das pezas do rival ten algún movemento válido.
	nf = figuras.size();
	for (i=0; i < nf; i++) 
        {   
	    f = (Figura)figuras.elementAt(i);
	    if (f.nombre.substring(1,2).compareTo(login)!=0 && f.x!=0 && f.y!=0 && f.x!=9 && f.y!=9) 
	      //localizada figura del rival
	      //ver si o movela a cq das outras 63 casillas é válido o movemento
	      for(j=1;j<=8;j++)
		for(k=1;k<=8;k++)
                  if(f.x!=j || f.y!=k) 
                  {
                    if(valido(f.x,f.y,j,k,f.nombre,false))
                    {
                      girarTableiro();
                      return 0; //se hai algún movemento válido devolvemos cero 
                    }
                  }
	}


	girarTableiro();

        //localizo o seu rei
	for (i=0; i < nf; i++) 
        {   
	    f = (Figura)figuras.elementAt(i);
	    if(f.nombre.substring(0,1).compareTo("r") == 0 
              && f.nombre.substring(1,2).compareTo(login)!=0)
            {
              reyx = f.x;
              reyy = f.y;
              break;
            }
	}

	//comprobo se o seu rei está amenazado por algunha peza miña(jaque)
	for (i=0; i < nf; i++) 
        {   
	    f = (Figura)figuras.elementAt(i);
	    if (f.nombre.substring(1,2).compareTo(login)==0 && f.x!=0 && f.y!=0 && f.x!=9 && f.y!=9) 
	      //localizada figura miña
	      //ver si o movela a posición do seu rei e un movemento válido

	      
              if(valido(f.x,f.y,reyx,reyy,f.nombre,false))
		jaque = true;
	}


	if (jaque) 
          return 1; //Está en jaque mate

	return 2; //Está afogado
     }

     public void girarTableiro()
     {
	Figura f;
	int i,nf;

	nf = figuras.size();
	for (i=0; i < nf; i++) 
        {   
	    f = (Figura)figuras.elementAt(i);
	    f.x = 9-f.x;
	    f.y = 9-f.y;
	}

     }

/*****************************************************************************/
/*****COMPROBACIONES DE MOVIMIENTOS VALIDOS***********************************/
/*****************************************************************************/
    public boolean valido(int x,int y,int z, int t,String figura) 
    {
	return valido(x,y,z, t,figura,true);
    }

    public boolean valido(int x,int y,int z, int t,String figura,boolean salida) 
    {
	int nf;
	int i,reyx,reyy,x1,y1,z1,t1;
	Figura f;
	nf = figuras.size();
        String movAnterior;

	/* Comer o paso con peón branco */
	if (figura.compareTo("pb") == 0 &&
            y == t+1 && 
            (x == z+1 || x == z-1) &&
            y == 4  &&
            z > 0 &&
            z < 9 ) 
        {
          for (i=0; i < nf; i++) {   /*buscar peza en (z,t), se a hai non é comer o paso*/
	      f = (Figura)figuras.elementAt(i);
	      if (z==f.x && t==f.y) 
            {
              break;
	      }
          }
          if (i == nf)  //non se atopou 
          {
            for (i=0; i < nf; i++) {   /*buscar peza en (z,t+1) para ver se é peón negro*/
	        f = (Figura)figuras.elementAt(i);
	        if (f.nombre.compareTo("pn")==0 &&
		    z==f.x && t+1==f.y) 
                {
                break;
	        }
	      }
            if (i == nf) //no se ha encontrado peón negro en esa posición
            {
              if (salida)
                chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
              return false;
            }
            movAnterior = partida.substring(partida.length()-8);
            x1=Integer.valueOf(movAnterior.substring(4,5)).intValue();
            y1=Integer.valueOf(movAnterior.substring(5,6)).intValue();
            z1=Integer.valueOf(movAnterior.substring(6,7)).intValue();
            t1=Integer.valueOf(movAnterior.substring(7,8)).intValue();
            if(x1 == z && y1 == y-2 && z1 == z && t1 == y)
              return true;
            else
            {
              if (salida) 
                chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
              return false;
            }
          }
          //se se atopa figura en z,t non é comer o paso          
        }

	/* Comer o paso con peón negro */

	if (figura.compareTo("pn") == 0 &&
            y == t+1 && 
            (x == z+1 || x == z-1) &&
            y == 4  &&
            z > 0 &&
            z < 9 ) 
      {
        for (i=0; i < nf; i++) {   /*buscar peza en (z,t), se a hai non é comer o paso*/
	    f = (Figura)figuras.elementAt(i);
	    if (z==f.x && t==f.y) 
          {
            break;
	    }
        }
        if (i == nf)  //non se atopou 
        {
          for (i=0; i < nf; i++) {   /*buscar peza en (z,t+1) para ver se é peón branco*/
	      f = (Figura)figuras.elementAt(i);
	      if (f.nombre.compareTo("pb")==0 &&
	        z==f.x && t+1==f.y) 
            {
              break;
	      }
	    }
          if (i == nf) //no se ha encontrado peón blanco en esa posición
          {
            if (salida)
              chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
            return false;
          }
          movAnterior = partida.substring(partida.length()-8);
          x1=9-Integer.valueOf(movAnterior.substring(4,5)).intValue();
          y1=9-Integer.valueOf(movAnterior.substring(5,6)).intValue();
          z1=9-Integer.valueOf(movAnterior.substring(6,7)).intValue();
          t1=9-Integer.valueOf(movAnterior.substring(7,8)).intValue();
          if(x1 == z && y1 == y-2 && z1 == z && t1 == y)
            return true;
          else
          {
            if (salida)
              chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
            return false;
          }
        } // non se atopou, non é comer o paso
      }

	/* Enroque largo de blancas */
	if (figura.compareTo("rb") == 0 && x==5 && y==8 && z==3 && t==8) {

		if (rey_movido) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido el rey."));
		      return false;
		}
		if (torre_izquierda_movida) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido la torre."));
		      return false;
		}

		/* Ver si estorba alguna figura enmedio */
		for (i=0; i < nf; i++) {   
		    f = (Figura)figuras.elementAt(i);
		    if ((f.x==2 && f.y==8) ||
			  (f.x==3 && f.y==8) ||
			  (f.x==4 && f.y==8)) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar con figuras en medio."));
		      return false;
		    }
		}

		/* Ver si el rey esta en jaque o pasa por posición amenazada */
		for (i=0; i < nf; i++) {
		    f = (Figura)figuras.elementAt(i);
		    if ((f.nombre.substring(1,2).compareTo("n")==0) &&
			   f.x>0 && f.x<9 && f.y>0 && f.y<9) {
			if ((f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,3,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,4,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,5,8,f.nombre,false)) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==3)||(f.y+1==8 && f.x-1==3))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==4)||(f.y+1==8 && f.x-1==4))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==5)||(f.y+1==8 && f.x-1==5))) ) {
				if (salida)
                                  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No puede enrocar con el rey en jaque o pasando por una posición amenazada."));
			      return false;
			}
		    }
		}
	return true;
	}

	/* Enroque corto de blancas */
	if (figura.compareTo("rb") == 0 && x==5 && y==8 && z==7 && t==8) {

		if (rey_movido) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido el rey."));
		      return false;
		}
		if (torre_derecha_movida) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido la torre."));
		      return false;
		}

		/* Ver si estorba alguna figura enmedio */
		for (i=0; i < nf; i++) {   
		    f = (Figura)figuras.elementAt(i);
		    if ((f.x==6 && f.y==8) ||
			  (f.x==7 && f.y==8)) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar con figuras en medio."));
		      return false;
		    }
		}

		/* Ver si el rey esta en jaque o pasa por posición amenazada */
		for (i=0; i < nf; i++) {
		    f = (Figura)figuras.elementAt(i);
		    if ((f.nombre.substring(1,2).compareTo("n")==0) &&
			   f.x>0 && f.x<9 && f.y>0 && f.y<9) {
			if ((f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,5,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,6,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,7,8,f.nombre,false)) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==5)||(f.y+1==8 && f.x-1==5))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==6)||(f.y+1==8 && f.x-1==6))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==7)||(f.y+1==8 && f.x-1==7))) ) {
				if (salida)
                                  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No puede enrocar con el rey en jaque o pasando por una posición amenazada."));
			      return false;
			}
		    }
		}
	return true;
	}

	/* Enroque largo de negras */
	if (figura.compareTo("rn") == 0 && x==4 && y==8 && z==6 && t==8) {

		if (rey_movido) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido el rey."));
		      return false;
		}
		if (torre_derecha_movida) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido la torre."));
		      return false;
		}

		/* Ver si estorba alguna figura enmedio */
		for (i=0; i < nf; i++) {   
		    f = (Figura)figuras.elementAt(i);
		    if ((f.x==5 && f.y==8) ||
			  (f.x==6 && f.y==8) ||
			  (f.x==7 && f.y==8)) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar con figuras en medio."));
		      return false;
		    }
		}

		/* Ver si el rey esta en jaque o pasa por posición amenazada */
		for (i=0; i < nf; i++) {
		    f = (Figura)figuras.elementAt(i);
		    if ((f.nombre.substring(1,2).compareTo("b")==0) &&
			   f.x>0 && f.x<9 && f.y>0 && f.y<9) {
			if ((f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,4,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,5,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,6,8,f.nombre,false)) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==4)||(f.y+1==8 && f.x-1==4))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==5)||(f.y+1==8 && f.x-1==5))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==6)||(f.y+1==8 && f.x-1==6))) ) {
				if (salida)
                                  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No puede enrocar con el rey en jaque o pasando por una posición amenazada."));
			      return false;
			}
		    }
		}
	return true;
	}

	/* Enroque corto de negras */
	if (figura.compareTo("rn") == 0 && x==4 && y==8 && z==2 && t==8) {

		if (rey_movido) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido el rey."));
		      return false;
		}
		if (torre_izquierda_movida) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar, ya ha movido la torre."));
		      return false;
		}

		/* Ver si estorba alguna figura enmedio */
		for (i=0; i < nf; i++) {   
		    f = (Figura)figuras.elementAt(i);
		    if ((f.x==2 && f.y==8) ||
			  (f.x==3 && f.y==8)) {
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No se puede enrocar con figuras en medio."));
		      return false;
		    }
		}

		/* Ver si el rey esta en jaque o pasa por posición amenazada */
		for (i=0; i < nf; i++) {
		    f = (Figura)figuras.elementAt(i);
		    if ((f.nombre.substring(1,2).compareTo("b")==0) &&
			   f.x>0 && f.x<9 && f.y>0 && f.y<9) {
			if ((f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,2,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,3,8,f.nombre,false)) ||
			    (f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,4,8,f.nombre,false)) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==2)||(f.y+1==8 && f.x-1==2))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==3)||(f.y+1==8 && f.x-1==3))) ||
		          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==8 && f.x+1==4)||(f.y+1==8 && f.x-1==4))) ) {
				if (salida)
                                  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("No puede enrocar con el rey en jaque o pasando por una posición amenazada."));
			      return false;
			}
		    }
		}
	return true;
	}



	reyx = 0;
	reyy = 0;

	if (!correcto(x,y,z,t,figura,salida))
	return false;

	for (i=0; i < nf; i++) {   /*mover la ficha comida fuera del tablero, si es que la hay*/
	    f = (Figura)figuras.elementAt(i);
	    if (z==f.x && t==f.y) {
		  f.x = 4;
		  f.y = 0;
	    }
	}
	for (i=0; i < nf; i++) {   /*mover para hacer un supuesto del movimiento*/
	    f = (Figura)figuras.elementAt(i);
	    if (f.nombre.compareTo(figura)==0 &&
		  x==f.x && y==f.y) {
		  f.x = z;
		  f.y = t;
	    }
	}

	/* comprobar si una vez hecho el  movimiento, el rey queda en jaque */
	for (i=0; i < nf; i++) { /*busco as coordenadas do meu rey */
	    f = (Figura)figuras.elementAt(i);
	    if (f.nombre.substring(1,2).compareTo(figura.substring(1,2))==0 &&
		  f.nombre.substring(0,1).compareTo(new String("r"))==0) {
		  reyx = f.x;
		  reyy = f.y;
	    }
	}

	for (i=0; i < nf; i++) {
	    f = (Figura)figuras.elementAt(i);
	    if ((f.nombre.substring(1,2).compareTo(figura.substring(1,2))!=0) &&
		   f.x>0 && f.x<9 && f.y>0 && f.y<9) {
		if ((f.nombre.substring(0,1).compareTo("p") != 0 && correcto(f.x,f.y,reyx,reyy,f.nombre,false)) ||
	          (f.nombre.substring(0,1).compareTo("p") == 0 && ((f.y+1==reyy && f.x+1==reyx)||(f.y+1==reyy && f.x-1==reyx)) )) {
			for (i=0; i < nf; i++) {   /*restaurar la ficha comida desde fuera del tablero, si es que la hay*/
		    	  f = (Figura)figuras.elementAt(i);
	    		  if (4==f.x && 0==f.y) {
		  		f.x = z;
		  		f.y = t;
	    	 	  }
			}
			for (i=0; i < nf; i++) {   /*deshacer el movimiento supuesto*/
		  	  f = (Figura)figuras.elementAt(i);
		   	 if (f.nombre.compareTo(figura)==0 &&
				  z==f.x && t==f.y) {
				  f.x = x;
				  f.y = y;
	 		   }
			}
			if (salida)
                          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Queda en jaque después de mover."));
		      return false;
		}
	    }
	}

	for (i=0; i < nf; i++) {   /*restaurar la ficha comida desde fuera del tablero, si es que la hay*/
    	  f = (Figura)figuras.elementAt(i);
   	  if (4==f.x && 0==f.y) {
  		f.x = z;
  		f.y = t;
   	  }
	}

	for (i=0; i < nf; i++) {   /*deshacer el movimiento supuesto*/
 		  f = (Figura)figuras.elementAt(i);
      	  if (f.nombre.compareTo(figura)==0 &&
		  	z==f.x && t==f.y) {
		  	f.x = x;
		  	f.y = y;
		   }
	}

	return true;
    }


    public boolean correcto(int x,int y,int z, int t,String figura,boolean salida) {
	int nf;
	int i;
	Figura f;

	if (!posible(x,y,z,t,figura.substring(0,1),figura.substring(1,2),salida))
	return false;
	
	/* comprobar si no hay ninguna ficha en medio para hacer el movimiento a no ser
	   que se trate de un movimiento de caballo */
	nf = figuras.size();
	for (i=0; i < nf; i++) {
	    f = (Figura)figuras.elementAt(i);
	    if (figura.compareTo(new String("cb"))!=0) {
		if (((x<f.x && f.x<z) || (x>f.x && f.x>z)) && /* comproba segmentos diagonales */
		    ((y<f.y && f.y<t) || (y>f.y && f.y>t)) &&
		    ( (100*(y-t))/(x-z) == (100*(f.y-t))/(f.x-z) ) ) {
			if (salida) {
			  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Solo el caballo puede saltar otras figuras."));
			}
			return false;
		}
		if (((x==z)&&(x==f.x)&&((y<f.y && f.y<t) || (y>f.y && f.y>t)))||
		    ((y==t)&&(y==f.y)&&((x<f.x && f.x<z) || (x>f.x && f.x>z)))){ /* comproba segmentos horizontales ou verticales */
			if (salida){
				chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Solo el caballo puede saltar otras figuras."));
			}
			return false;
		}
	    }

	    if ((f.x==z && f.y==t) &&
		  (figura.substring(1,2).compareTo(f.nombre.substring(1,2))==0))
	    return false;
	} 
	return true;
    }


    /* comprobar que el movimiento sería valido sobre un tablero vacio*/
    public boolean posible(int x,int y,int z, int t,String figura,String color,boolean salida) {
	int nf;
	int i;
	Figura f;

          nf = figuras.size();

	    if (figura.compareTo(new String("p"))==0) {
		if ((y==7 && t==5 && x==z) ||
		    (t==y-1) && (x==z)){
			for (i=0; i < nf; i++) {  
			    f = (Figura)figuras.elementAt(i);
			    if (f.nombre.substring(1,2).compareTo(color)!=0 &&
				  z==f.x && t==f.y) {
				  if (salida){
					chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
				  }
				  return false;
		    	    }
			} 
		return true;
		}
	    }
	    if (figura.compareTo(new String("p"))==0) {
		if ((t==y-1) && (x==z+1)) {
			for (i=0; i < nf; i++) {  
			    f = (Figura)figuras.elementAt(i);
			    if (f.nombre.substring(1,2).compareTo(color)!=0 &&
				  z==f.x && t==f.y) {
				  return true;
		    	    }
			} 	
		}
	    }
	    if (figura.compareTo(new String("p"))==0) {
		if ((t==y-1) && (x==z-1)) {
			for (i=0; i < nf; i++) {  
			    f = (Figura)figuras.elementAt(i);
			    if (f.nombre.substring(1,2).compareTo(color)!=0 &&
				  z==f.x && t==f.y) {
				  return true;
		    	    }
			}
		}
	    }


	    if (figura.compareTo(new String("c"))==0) {
		if ((Math.abs(x-z)==2 && Math.abs(y-t)==1) ||
		    (Math.abs(x-z)==1 && Math.abs(y-t)==2))
		return true;
	    }

	    if (figura.compareTo(new String("a"))==0 || figura.compareTo(new String("d"))==0) {
		if (Math.abs(x-z)==Math.abs(y-t))
		return true;
	    }

	    if (figura.compareTo(new String("t"))==0 || figura.compareTo(new String("d"))==0) {
		if ((x==z) || (y==t))
		return true;
	    }

	    if (figura.compareTo(new String("r"))==0) {
		if (Math.abs(x-z)<2 && Math.abs(y-t)<2)
		return true;
	    }
	    
	    if (salida){
		chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Movimiento no válido."));
	    }
          return false;	
    }


	
/***********************************************************************/
    public synchronized void enviar_comentario(String novaMensaxe) 
    {
      URL url;

      novaMensaxe = novaMensaxe.replace('#',' ');
      novaMensaxe = novaMensaxe.replace(' ','|');
	if (novaMensaxe.length()>0)
      {
        chatPrivado.engadirFraseComposta(novaMensaxe.replace('|',' '));
	  //enviaOnline("C1#"+novaMensaxe);
        salon.enviar_mensaxe(loginRival,7,loginXogador+"]12"+novaMensaxe); 
      }

    }


/***********************************************************************/

  public synchronized void GardarMovemento() 
  {
    GardarMovemento(false);
  }

  public synchronized void GardarMovemento(boolean repeticion) 
  {
    URL url;
    String movimiento="",ultimoMovimento="",segundos="0",entrada="";
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
    
    try
    {
      if(!repeticion)
        relojRival.arranca();
        
      segundos = String.valueOf(canvas.segundosMovemento);  
        
      ultimoMovimento=partida.substring(longitudPartida-8);
    }catch (Exception e) {}	  

    //ó rival só lle envío o último movemento
    salon.enviar_mensaxe(loginRival,7,loginXogador+"]11"+String.valueOf(reloj.segundos)+"#"+ultimoMovimento+"#"+String.valueOf(longitudPartida-8)+"#");

    //á base de datos enviolle os dous ultimos movementos, excepto no primeiro
    salon.enviarBD("../php/GardarMovemento.php?sala="+salon.sala+"&login="+loginXogador+"&m="+movimiento+"&psp="+String.valueOf(psp)+"&b="+login_b+"&n="+login_n+"&segundos="+segundos+"&l="+login+"&a="+((int)(Math.random()*100000)),
	           String.valueOf(hashCode()),"MovementoEnviadoCorrectamente");
  }


  public synchronized void MovementoEnviadoCorrectamente(String entrada) 
  {
        //se está pintando esperamos
        while(canvas.pintando)
	    try {
	      esperar(500);
          } catch( InterruptedException e ) {}

        /* comproba se foi mate ou afogado */ 
        switch(estaMortoAfogadoDousreiesTresmovementosiguais())
        {
          case 1:
            mate();
            break;
          case 2:
            afogado();
            break;
          case 3:
            dousreies();
            break;
          case 4:
            tresmovementosiguais();
            break;            
        }

        posicion_servidor_partida=partida.length();

  }

/***********************************************************************/
  public synchronized void procesarMovemento(String entrada) 
  {
    String mov="";
    int tempo=0,posicionMov=0;
    Ajedrez aj;
    int i;

    temporizador.antibloqueo=0;
 
    try
    {
      if (entrada.length() > 0)
        quienMueve();      

      if (entrada.length() > 0 && !metocamover)
      {

        puedoProponerTablas = true;

        tempo = (Integer.valueOf(entrada.substring(0,entrada.indexOf("#")))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1);
        mov = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        posicionMov = (Integer.valueOf(entrada.substring(0,entrada.indexOf("#")))).intValue();
        if (mov.length() > 0 && partida.length()<posicionMov+mov.length()) 
        {	
          partida = partida.substring(0,posicionMov)+mov;
          relojRival.para(tempo);
          reloj.arranca();
          aj = (Ajedrez)(salon.pestanhasVerticales.getComponentAt(salon.pestanhasVerticales.getSelectedIndex()));
          if(aj!=this)  //poñer pestaña verde
            for (i=0;i<salon.pestanhasVerticales.getTabCount();i++)
            {
              aj=(Ajedrez)(salon.pestanhasVerticales.getComponentAt(i));
              if(aj==this)
              {
                salon.pestanhasVerticales.setBackgroundAt(i,new Color(0,128,0));
                salon.pestanhasVerticales.setIconAt(i,new VTextIcon(salon.pestanhasVerticales, loginRival, VTextIcon.ROTATE_LEFT,Color.red));
              }
            }
      
          posicion_servidor_partida=partida.length();

          posicion = partida.length();

          direccion = "adelante";
          pintar_tablero();
        }
        else  //movemento que enviei eu mesmo
        {
          posicion_servidor_partida=partida.length();
        }

      }
    } catch(Exception e) {}
  }

  public void quienMueve()
  {
  	
          if (login.compareTo(new String("b")) == 0) 
          {
            canvas.login_arriba = login_n; 
            canvas.login_abajo = login_b;
            if (partida.length()%16 == 0) 
            {
              canvas.login_abajo = "» "+canvas.login_abajo;
              metocamover = true;
            }
            else 
            {
              canvas.login_arriba = "» "+canvas.login_arriba;
              metocamover = false;
            }
          }
	
          if (login.compareTo(new String("n")) == 0) 
          {
            canvas.login_arriba = login_b; 
            canvas.login_abajo = login_n; 
            if (partida.length()%16 == 0) 
            {
            canvas.login_arriba = "» "+canvas.login_arriba;
            metocamover = false;
            }
            else 
            {
              canvas.login_abajo = "» "+canvas.login_abajo;
              metocamover = true;
            }
          }
          
  	  if (this instanceof AjedrezVerPartidas)
  	    return;          
         
          if (btnRendir.getText().compareTo(salon.idioma.traducir("Cerrar"))==0)
            canvas.setHabilitado(false);
          else
            canvas.setHabilitado(metocamover || canvas.peon_arriba);

  }

    public synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   


/******************************************************************************************/
/******************************* FIN TEMPO RELOXIO DESCONTO RIVAL *************************/
/******************************************************************************************/
    public void finDesconto()
    {
        int i;
        i = temporizador.contador;  /* CHAPUZA PARA SABER SE ESTÁ VIVO */
        while(i==temporizador.contador)
	    try {
	      esperar(500);
            } catch( InterruptedException e ) {}
      
      if(btnTablas.getText().compareTo(salon.idioma.traducir("Cerrar"))!=0) //se non está xa rematada
      {
        salon.enviarBD("../php/GanarPartida.php?sala="+salon.sala+"&login="+loginXogador+"&b="+login_b+"&n="+login_n+"&a="+((int)(Math.random()*100000)),
	               String.valueOf(hashCode()),"postFinDesconto");      	
      }
    }


    public void postFinDesconto(String entrada)
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Enhorabuena, has ganado porque el rival se ha desconectado."));
	modificarELO(2,1);
        salon.enviar_mensaxe(loginRival,7,loginXogador+"]10");  //indicarlle o rival que perdeu por desconexión, no caso de que se conectase no último momento
        salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Gana")+" "+loginXogador+" "+salon.idioma.traducir("por desconexión de")+" "+loginRival); //Mensaxe para Ver Partidas
    }



/******************************************************************************************/
/******************************* FIN TEMPO RELOXIO ****************************************/
/******************************************************************************************/
    public void finReloxio()
    {
        int i;
        int nf;
        int contador=0;
        Figura f;
        
        i = temporizador.contador;  /* CHAPUZA PARA SABER SE ESTÁ VIVO */
        while(i==temporizador.contador)
	    try {
	      esperar(500);
            } catch( InterruptedException e ) {}


	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));

	//Comprobar se só lle queda o rei a él co cal serán tablas, contarei cantas pezas lle quedan
	nf = figuras.size();
	for (i=0; i < nf; i++) 
	{
	  f = (Figura)figuras.elementAt(i);
          if (f.x!=0 && f.y!=0 && f.x!=9 && f.y!=9
              && f.nombre.substring(1,2).compareTo(login)!=0)
            contador++;
        }
        if (contador==1) //so lle queda o rei
        {
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("La partida termina en tablas por finalización del tiempo y al rival solo le queda el rey."));
	  modificarELO(1,1);
	  salon.enviar_mensaxe(loginRival,7,loginXogador+"]17");  //aceptar tablas
	  salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Pactan TABLAS")); //Mensaxe para Ver Partidas
	  facerTablas();
        }
        else
        {
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Lo siento, has perdido por finalización del tiempo."));
	  modificarELO(0,1);
	  salon.enviar_mensaxe(loginRival,7,loginXogador+"]1");  //indicarlle o rival que gañou
	  salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Gana")+" "+loginRival+" "+salon.idioma.traducir("por finalizar el tiempo")+" "+loginXogador); //Mensaxe para Ver Partidas
	  perder();
	}

    }

/******************************************************************************************/
/******************************* R E N D I R S E ******************************************/
/******************************************************************************************/
    public void rendirse()
    {

	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Lo siento, tu pierdes."));
	modificarELO(0,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]2");  //indicarlle o rival que gañou
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Gana")+" "+loginRival+" "+salon.idioma.traducir("por rendirse")+" "+loginXogador); //Mensaxe para Ver Partidas
	perder();

    }

/******************************************************************************************/
/******************************* PROPONER TABLAS ******************************************/
/******************************************************************************************/
    public void proponerTablas()
    {
      if (puedoProponerTablas==false)
      {
        chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Ya has propuesto tablas antes."));
        return;
      }
      puedoProponerTablas=false;
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Enviada proposición de tablas."));
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]3");  //propoñer tablas
    }

    public void aceptarTablas()
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("La partida termina en tablas."));
	modificarELO(1,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]4");  //aceptar tablas
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Pactan TABLAS")); //Mensaxe para Ver Partidas
	facerTablas();
    }

    public void denegarTablas()
    {
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Enviada notificación al rival de DENEGACIÓN de tablas."));
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]5");  //denegar tablas
    }

/******************************************************************************************/
/******************************* MENSAXE DO RIVAL ****************************************/
/******************************************************************************************/
    public void mensajeDelRival(String mensaje)
    {
	if(mensaje.compareTo("1") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tu ganas, al rival se le acabó el tiempo."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(2,0);
	}
	else if(mensaje.compareTo("2") == 0)
        {
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tu ganas, el rival se rinde."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(2,0);
        }
	else if(mensaje.compareTo("3") == 0)
	  salon.proposicionTablas(this);
	else if(mensaje.compareTo("4") == 0)
        {
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("El rival ACEPTA tablas."));
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Por tanto, la partida termina en tablas."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(1,0);
        }
	else if(mensaje.compareTo("5") == 0)
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("El rival NO acepta tablas."));
	else if(mensaje.compareTo("6") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("JAQUE MATE, lo siento has perdido."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(0,0);
	}
	else if(mensaje.compareTo("7") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas por AHOGADO."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(1,0);
	  while(!metocamover)
          {
            try {
	      esperar(500);
            } catch( InterruptedException e ) {}
          }
	  facerTablas();
	}
        else if(mensaje.compareTo("8") == 0)  //DESCONECTADO
        {
          if (reconexionsRival>0) //se e a segunda desconexión xa perde a partida
          {
            chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Solo se permite una reconexión en cada partida."));
            finDesconto();
          }
          else
            if(btnRendir.getText().compareTo(salon.idioma.traducir("Cerrar"))!=0)
              relojRival.arrancaDesconto();
        }
	else if(mensaje.compareTo("9") == 0)  //RECONECTADO
	{
	  reconexionsRival++;
	  if (!metocamover)
            relojRival.arrancaPostDesconto();
          else
            relojRival.paraPostDesconto();
        }    
	else if(mensaje.compareTo("10") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Lo siento, has perdido por superar los 2 minutos de desconexión."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(0,0);
	}
	else if(mensaje.substring(0,2).compareTo("11") == 0)
	  procesarMovemento(mensaje.substring(2));
	else if(mensaje.substring(0,2).compareTo("12") == 0)
          chatPrivado.engadirFraseComposta(mensaje.substring(2).replace('|',' '));
	else if(mensaje.compareTo("13") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, solo quedan los dos reyes."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(1,0);
	  while(!metocamover)
          {
            try {
	      esperar(500);
            } catch( InterruptedException e ) {}
          }
	  facerTablas();
	}
	else if(mensaje.compareTo("14") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, tres movimientos iguales."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(1,0);
	  while(!metocamover)
          {
            try {
	      esperar(500);
            } catch( InterruptedException e ) {}
          }
	  facerTablas();
	}       
	else if(mensaje.compareTo("15") == 0)  //Salon avisa de que se está desconectando a drede, cerrando ventana ou menu salir
        {
          chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("El rival se ha autodesconectado con lo cual ganas la partida."));
          finDesconto();
        }
	else if(mensaje.compareTo("17") == 0)
	{
	  chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, al rival se le acabó el tiempo quedándole solo el rey."));
	  btnRendir.setText(salon.idioma.traducir("Cerrar"));
	  btnTablas.setText(salon.idioma.traducir("Cerrar"));
	  reloj.para();
	  relojRival.para();
	  modificarELO(1,0);
	}        
         
    }
    

    
/******************************************************************************************/
/******************************* P E R D E R **********************************************/
/******************************************************************************************/
    public void perder()
    {
      salon.enviarBD("../php/PerderPartida.php?sala="+salon.sala+"&login="+loginXogador+"&loginRival="+loginRival+"&b="+login_b+"&n="+login_n+"&a="+((int)(Math.random()*100000)),
	             String.valueOf(hashCode()),"");
    }

/******************************************************************************************/
/******************************* FACER TABLAS **********************************************/
/******************************************************************************************/
    public void facerTablas()
    {
      salon.enviarBD("../php/FacerTablas.php?sala="+salon.sala+"&login="+loginXogador+"&loginRival="+loginRival+"&b="+login_b+"&n="+login_n+"&a="+((int)(Math.random()*100000)),
	             String.valueOf(hashCode()),"");
    }


/******************************************************************************************/
/******************************* M A T E **************************************************/
/******************************************************************************************/
    public void mate()
    {
      salon.enviarBD("../php/GanarPartida.php?sala="+salon.sala+"&login="+loginXogador+"&b="+login_b+"&n="+login_n+"&a="+((int)(Math.random()*100000)),
	             String.valueOf(hashCode()),"postMate");
    }

    public void postMate(String entrada)
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Enhorabuena, JAQUE MATE."));
	modificarELO(2,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]6");  //indicarlle o rival que perdeu por mate
        salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("Gana")+" "+loginXogador+" "+salon.idioma.traducir("por MATE")); //Mensaxe para Ver Partidas
    }


/******************************************************************************************/
/******************************* AFOGADO **************************************************/
/******************************************************************************************/
    public void afogado()
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, has AHOGADO al rival."));
	modificarELO(1,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]7");  //indicarlle o rival que está afogado
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("TABLAS")+", "+loginXogador+" "+salon.idioma.traducir("AHOGA a")+" "+loginRival); //Mensaxe para Ver Partidas
	facerTablas();
    }


/******************************************************************************************/
/************************ SÓ DOUS REIES NO TABLEIRO ***************************************/
/******************************************************************************************/
    public void dousreies()
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, solo quedan dos reyes."));
	modificarELO(1,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]13");  //indicarlle o rival que só quedan dous reies
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("TABLAS por falta de material.")); //Mensaxe para Ver Partidas
	facerTablas();
    }



/******************************************************************************************/
/************************ TRES MOVEMENTOS IGUAIS ******************************************/
/******************************************************************************************/
    public void tresmovementosiguais()
    {
	reloj.para();
	relojRival.para();
	btnRendir.setText(salon.idioma.traducir("Cerrar"));
	btnTablas.setText(salon.idioma.traducir("Cerrar"));
	chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Tablas, se han hecho tres movimientos iguales."));
	modificarELO(1,1);
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]14");  //indicarlle o rival que se fixeron tres movementos iguais
	salon.enviar_mensaxe(loginRival,7,loginXogador+"]16"+salon.idioma.traducir("TABLAS por 3 movimientos iguales")); //Mensaxe para Ver Partidas
	facerTablas();
    }




/******************************************************************************************/
/******************************* MODIFICAR ELO **********************************************/
// resultado: 0 perdín
//            1 tablas
//            2 ganei
// modificar: 0 solo consulta
//            1 modificar ELO na base de datos
/******************************************************************************************/
  public void modificarELO(int resultado,int modificar)
  {
    String ganador,fecha="",hora="";

    if(loginXogador.indexOf(salon.idioma.traducir("INVITADO"))>=0 ||
       loginRival.indexOf(salon.idioma.traducir("INVITADO"))>=0)
    {
      chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Jugar con invitados no modifica la puntuación."));
      return;
    }
    if(partida.length()<33)
    {
      chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Partidas con cuatro movimientos o menos no puntuan."));
      return;
    }    

    ganador = "";
    if(resultado == 0)
      ganador = loginRival;
    if(resultado == 2)
      ganador = loginXogador;
      
    try
    {
       fecha=t.substring(0,10);
       hora=t.substring(11);
    }catch(Exception e){}  

    salon.enviarBD("../php/ModificacionELO.php?sala="+salon.sala+"&modificar="+modificar+"&ganador="+ganador+"&login="+loginXogador+"&loginRival="+loginRival+"&fecha="+fecha+"&hora="+hora+"&a="+((int)(Math.random()*100000)),
	           String.valueOf(hashCode()),"postModificarELO");
  }

    public void postModificarELO(String entrada)
    {
        String modificoPuntos;

        modificoPuntos = entrada;
        chatPrivado.engadirFrase(salon.idioma.traducir("SISTEMA"),"ELO "+modificoPuntos+" "+salon.idioma.traducir("puntos."));
        salon.elo = String.valueOf(Integer.valueOf(salon.elo).intValue()+Integer.valueOf(modificoPuntos).intValue());
        salon.enviar_mensaxe("",17,loginXogador+"]"+salon.elo); //Indicar a todos o novo ELO
        salon.recibe_mensaxe(17,loginXogador+"]"+salon.elo);
	salon.repaint();
    }


}



/***********************************************************************************/
class TemporizadorAjedrez implements Runnable {
    Thread thread = null;
    int intervalo;
    private boolean inicio = true;
    public Ajedrez ajedrez = null;
    public int contador=0;
    //int ultimos3intentos;
    int antibloqueo = 0;    

    TemporizadorAjedrez( int tiempo, Ajedrez aj ) { 
        intervalo = tiempo;
	  ajedrez = aj;
        }
    

    // Aqui creamos un nuevo thread para correr el TemporizadorAjedrez. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() {
        thread = new Thread( this );
        thread.start();
        }
 
    public void stop() {
      thread=null;
      Thread.currentThread().interrupt();
        }


    public void run() {
	contador=0;

      while( Thread.currentThread() == thread )  {
        try {
	  if (inicio && !(ajedrez instanceof AjedrezVerPartidas))
          {
	      inicio = false;
	      esperar( 2000 );
	      ajedrez.repaint();
          }
          else
            esperar( intervalo );
        } catch( InterruptedException e ) {return;}

        if(ajedrez instanceof AjedrezVerPartidas)
        {
          try
          {
	  	if (((AjedrezVerPartidas)ajedrez).automaticoAdiante && ajedrez.posicion < ajedrez.partida.length()) 
            {
			ajedrez.direccion = "adelante";
			ajedrez.posicion += 8;
			ajedrez.pintar_tablero();
		}
           }
           catch (Exception e){}
         }
         else
         {    
          try
          {
/*Esto ahora non ten sentido porque desconectado non se fai nada          	
            if (!ajedrez.salon.conectado)
              ultimos3intentos = 3; //cando pasa de desconectado e conecta ainda lemos 3 veces máis da base de datos

		if ((!ajedrez.salon.conectado || ultimos3intentos>0) && !ajedrez.canvas.peon_arriba && !ajedrez.metocamover)
            {
                ajedrez.salon.enviarBD("../php/LerMovemento.php?sala="+ajedrez.salon.sala+"&login="+ajedrez.loginXogador+"&psp="+String.valueOf(ajedrez.posicion_servidor_partida)+"&b="+ajedrez.login_b+"&n="+ajedrez.login_n+"&l="+ajedrez.login+"&a="+((int)(Math.random()*100000)),
	                              String.valueOf(ajedrez.hashCode()),"procesarMovemento");              	
            }

            if (ajedrez.salon.conectado && contador%2 == 0)
              ultimos3intentos--;
*/
            if (ajedrez.salon.conectado)
              if (ajedrez.metocamover)
                antibloqueo=0;
              else
                antibloqueo++;
            else
              antibloqueo=7;

            if (antibloqueo >= 8 )
                 //se estando conectado non move en oito temporizadores lemos da base de datos 
                 //por si acaso
            {
              antibloqueo = 0;
              if(ajedrez.salon.conectado && ajedrez.btnTablas.getText().compareTo(ajedrez.salon.idioma.traducir("Cerrar"))!=0) //se non está xa rematada) //ahora se non se está conectado non se pode facer nada
              {
              	ajedrez.GardarMovemento(true); //antes só volvia a ler pero ahora tamén envio de novo por se fallou un envio anterior e non o sei
                ajedrez.salon.enviarBD("../php/LerMovemento.php?sala="+ajedrez.salon.sala+"&login="+ajedrez.loginXogador+"&psp="+String.valueOf(ajedrez.posicion_servidor_partida)+"&b="+ajedrez.login_b+"&n="+ajedrez.login_n+"&l="+ajedrez.login+"&a="+((int)(Math.random()*100000)),
	                               String.valueOf(ajedrez.hashCode()),"procesarMovemento");             
	      }
	    }

        } catch(Exception e) {}
	     contador++;  
        }
      }   
             
    }    

    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}


