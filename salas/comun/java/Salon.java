import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.text.NumberFormat;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.lang.reflect.Method;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.event.CellEditorListener;

import javax.swing.plaf.ScrollPaneUI;

import javax.swing.table.TableCellRenderer;

import fractal.*;
import processing.core.PApplet;

public class Salon extends JApplet
{
    MediaTracker tracker;                            /* para manejar las imagenes */
    Image pb,tb,cb,ab,db,rb,pn,tn,cn,an,dn,rn,logov,icono,bannerup,btrapido[];/* imagenes de figuras y logotipo */
    Vector figuras = new Vector();                   /* vector donde almacenar las figuras */
    String partida;                                  /* cadena con los movimientos */
    TaboaXogadores tabla;
    JScrollPane panelXogadores;
    JTabbedPane pestanhasVerticales;
    
    String inicio;

    boolean sw_cargar_tableros=false;

    Panel mensaje_panel   = new Panel();
    Panel mensaje_panel2  = new Panel();
    Panel mensaje_panelb  = new Panel();
    Panel mensaje_panelc  = new Panel();
    Panel mensaje_paneld  = new Panel();
    Label mensaje = new Label("");	
    Label mensaje2 = new Label("");
    JButton mensaje_boton;
    JButton mensaje_boton2;
    Button mensaje_boton_tapiz = new Button("");
    int accionDespuesMensaje = 0; // 1-retar 2-Aceptar reto 3-Proposición tablas 4-Confirmar Rendirse 
    JRadioButton blancas;
    JRadioButton negras;
    JRadioButton min1;
    JRadioButton min2;
    JRadioButton min3;
    JRadioButton min5;
    JRadioButton min10;
    JRadioButton min20;
    JRadioButton min40;
    JRadioButton min60;
    JRadioButton min120;
    JCheckBox incrementos;
    ButtonGroup grupo;

    int colorFondoVermello=0;
    int colorFondoVerde=0;
    int colorFondoAzul=0;
    int colorSalonVermello=150;
    int colorSalonVerde=102;
    int colorSalonAzul=79;
    int colorObxetosVermello=235;
    int colorObxetosVerde=218;
    int colorObxetosAzul=184;

    Font fonteBotos = new Font("Courier", Font.PLAIN, 11);
    Color colFondoBotos;
    Color colFondoBotosClaro;    
    Color colTextoBotos;
    Color colorSalon;
    Color colorFondo;

    Chat chatPublico;

    String sala,login,clave,servidor,elo,portoSalon;
    ComunicaOnlineSalon envioOnlineSalon;

    TemporizadorSalon temporizador;

    boolean conectado=false,autenticado=false,validado=false;

    int posicion_mensaxes;
    String novaPartidaLoginBlancas="",novaPartidaLoginNegras="",novaPartidaLoginRival="",novaPartidaTempo="";
    int novaPartidaIncrementos=0;

    Ajedrez ajedrezRecibeProposicionTablas;
    Ajedrez ajedrezConfirmaRendirse;

    boolean mostrandoMensaje = false;
 
    TemporizadorReto temporizadorReto;

    IniciarSalon iniciarSalon;

    Marquesina marquesina;
    
    XestionColor xestionColor;
    JDialog dlgXestionColor=null;
    
    Applet contenedor;
    Applet contenedorOeste;
    
    Tutor tutor;
    
    boolean existeContribucion = false; //existe na base de datos campo contribucion
    String fraseMarquesinaContribucion = "";
    String fraseChatPublicoContribucion = "";
    
    String[] xogadorARetar;
    
    VentanaHtml ventanaHtml;
    
    AjedrezVerPartidas ajedrezVer=null;
    JFrame frmVerPartidas=null;
    
    JMenuBar menuBarra;
    
    Salon salon;
    
    JEditorPane htmlArriba=null;
    JEditorPane htmlAbajo=null;
    
    Container con;
    
    Presentacion presentacion;
    
    TempoRelativo tempoRelativo;
    
    long numero; //numero recibido para autenticación
    
    ImageIcon bandera[];
    
    EnviaOnlineOrdenado enviaOnlineOrdenado;
    
    String numeroBandera="0";
    
    String ip="";
    
    boolean VIP = false;
    
    JProgressBar progressBar;
    
    boolean cargando=true;
    
    Idioma idioma;
    String idiomaUsado="spanish";
    
    Vector palabrasProhibidasGraves = new Vector();    
    
    Vector palabrasProhibidasLeves = new Vector();
    
    boolean publicidade=false;
    Image imaxePublicidade,imaxePublicidadeArriba1,imaxePublicidadeArriba2;
    String webPublicidade;
    
    JPanel panelBotosRapidos;
    
    boolean admitirRetos=true;

    public void eliminarEtiquetaInferior(Component comp)
    {

      if(comp instanceof JLabel)
        comp.getParent().remove(comp);
      
      if(comp instanceof Container)
       {
        Component[] comps = ((Container)comp).getComponents();
        for(int x = 0, y = comps.length; x < y; x++)
          eliminarEtiquetaInferior(comps[x]);
      }
      
    }   

    public void init() 
    {

      int tamanhoPieza;
      Image piezas;
      Image banderas;
      ImageFilter cropfilter;
      ImageProducer prod;
      int i;
      int totalBanderas;
      URL urljar;
      URL urlPublicidade=null;
      Toolkit tk=getToolkit();      
      Color c;
      String palabra;

      salon=this;
      
      tempoRelativo = new TempoRelativo(this);
      tempoRelativo.start();
      
      enviaOnlineOrdenado = new EnviaOnlineOrdenado(this);

      con = this;
      while( con.getParent() != null ) 
        con = con.getParent();

      if( con instanceof JFrame ) //Situar frame na posición 0,0
      {
        JFrame f = (JFrame) con;
        f.setLocation( 0, 0 );
        f.setDefaultCloseOperation(0);
        try
        {
          eliminarEtiquetaInferior(f);
        }catch(Exception e){}
        f.setBackground(Color.black);
        f.getContentPane().setBackground(Color.black);
        f.validate();
        f.repaint();
        f.addWindowListener( new WindowAdapter() 
        {
          public void windowClosing(WindowEvent e) 
          {
            enviarMensajesAutodesconexion();
            System.exit(0);
          }
       });  
      }

      //Toolkit.getDefaultToolkit().beep();
      setBackground(Color.black);
      getContentPane().setBackground(Color.black);
      getContentPane().setLayout( null );
      
      presentacion=new Presentacion(this);
      presentacion.start(); //lanzar presentación
      
      progressBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
      
      progressBar.setValue(0);
      progressBar.setBounds(8,7,150,8);
      progressBar.setBackground(Color.black);
      progressBar.setForeground(Color.white);
      
      getContentPane().add(progressBar);
      
      try
      {
        idiomaUsado=getParameter("idioma");
      } catch (Exception e) {}
      idioma = new Idioma(idiomaUsado);

      mostrarMensajeCargando(idioma.traducir("Cargando configuración."));

      try
      {
      	  sala = getParameter("sala");
	  login = getParameter("login");
     	  clave = getParameter("clave");
	  elo = getParameter("elo");
	  servidor = getParameter("s");
	  portoSalon = getParameter("ps");
      }catch(Exception e){}    
      
      try
      {
      	i=1;
      	while((palabra=getParameter("ppg"+i))!=null)
      	{
      	  palabrasProhibidasGraves.addElement((String)palabra);
      	  //System.out.println(palabra);
      	  i++;
      	}
      }catch(Exception e){}      
      

      try
      {
      	i=1;
      	while((palabra=getParameter("ppl"+i))!=null)
      	{
      	  palabrasProhibidasLeves.addElement((String)palabra);
      	  i++;
      	}
      }catch(Exception e){} 
      
      try
      {
        if(getParameter("publicidade").equals("si") || login.equals("PUBLICIDAD"))
          publicidade=true;
      } catch (Exception e) {}      

      progressBar.setValue(7);
      mostrarMensajeCargando(idioma.traducir("Cargando web inicial."));

      try
      {
        URL url = new URL(getCodeBase(),"../../"+sala+"/php/presentacionArriba.php?login="+login+"&sala="+sala+"&a="+((int)(Math.random()*100000)));
        htmlArriba = new JEditorPane(url);
      } catch(Exception e){System.out.println(e);}
      
      progressBar.setValue(14);
      mostrarMensajeCargando(idioma.traducir("Cargando imagen arte."));

      try
      {
        URL url = new URL(getCodeBase(),"../../"+sala+"/php/presentacionAbajo.php?login="+login+"&sala="+sala+"&a="+((int)(Math.random()*100000)));
        htmlAbajo = new JEditorPane(url);
      } catch(Exception e){System.out.println(e);}

      try
      {
      	if(getParameter("existeContribucion").compareTo("si")==0)
      	{
      	  existeContribucion=true;
      	  fraseMarquesinaContribucion = getParameter("fraseMarquesinaContribucion");
      	  fraseChatPublicoContribucion = getParameter("fraseChatPublicoContribucion");
      	}
      } 
      catch(Exception e) {}    
      
      progressBar.setValue(21);
      mostrarMensajeCargando(idioma.traducir("Cargando colores."));
      
      try
      {
        c=createColor(getParameter("colorFondo"));
	colorFondoVermello=c.getRed();
        colorFondoVerde=c.getGreen();
        colorFondoAzul=c.getBlue();
      } 
      catch(Exception e) {}

      try
      {
        c=createColor(getParameter("colorSalon"));
        colorSalonVermello=c.getRed();
        colorSalonVerde=c.getGreen();
        colorSalonAzul=c.getBlue();
      } 
      catch(Exception e) {}

      try
      {
        c=createColor(getParameter("colorObxetos"));
        colorObxetosVermello=c.getRed();
        colorObxetosVerde=c.getGreen();
        colorObxetosAzul=c.getBlue();
        c=null;
      } 
      catch(Exception e) {}

      colFondoBotos = new Color(colorObxetosVermello,colorObxetosVerde,colorObxetosAzul);
      colFondoBotosClaro = new Color(colorObxetosVermello+30,colorObxetosVerde+40,colorObxetosAzul+40);
      colTextoBotos = new Color(colorSalonVermello-50,colorSalonVerde-50,colorSalonAzul-50);
      colorSalon = new Color(colorSalonVermello,colorSalonVerde,colorSalonAzul);
      colorFondo = new Color(colorFondoVermello,colorFondoVerde,colorFondoAzul);
      	
     progressBar.setValue(28);
     mostrarMensajeCargando(idioma.traducir("Cargando piezas."));
      	
      tracker = new MediaTracker( this );  
      
      if (getBounds().height<=600)
      {
        //piezas = getImage(getCodeBase(), "../imagenes/piezas800.gif");
        urljar = this.getClass().getResource( "imagenes/piezas800.gif" );
        tamanhoPieza=48;
    
        if(publicidade)
        {
          try
          {
            urlPublicidade=new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidade800.png");
          }catch(Exception e){}  
        }
      }
      else if (getBounds().height<=768)
      {
        //piezas = getImage(getCodeBase(), "../imagenes/piezas1024.gif");
        urljar = this.getClass().getResource( "imagenes/piezas1024.gif" );
        tamanhoPieza=64;
               
        if(publicidade)
        {
          try
          {
            urlPublicidade=new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidade1024.png");
          }catch(Exception e){}  
        }               
      } 
      else if (getBounds().height<=864)
      {
        //piezas = getImage(getCodeBase(), "../imagenes/piezas1152.gif");
        urljar = this.getClass().getResource( "imagenes/piezas1152.gif" );
        tamanhoPieza=74;
               
        if(publicidade)
        {
          try
          {
            urlPublicidade=new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidade1152.png");
          }catch(Exception e){}  
        }               
      }
      else
      {
        //piezas = getImage(getCodeBase(), "../imagenes/piezas1280.gif");
        urljar = this.getClass().getResource( "imagenes/piezas1280.gif" );
        tamanhoPieza=90;
        
        if(publicidade)
        {
          try
          {
            urlPublicidade=new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidade1280.png");
          }catch(Exception e){}  
        }        
       
      }
      piezas = tk.getImage(urljar);
      tracker.addImage( piezas,0 );
      
      if(publicidade)
      {
      	try
      	{
          imaxePublicidade = tk.getImage(urlPublicidade);
          tracker.addImage( imaxePublicidade,0 ); 
          imaxePublicidadeArriba1 = tk.getImage(new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidadeArriba1.png"));
          tracker.addImage( imaxePublicidadeArriba1,0 );            
          imaxePublicidadeArriba2 = tk.getImage(new URL(getCodeBase(), "../../"+sala+"/imagenes/publicidadeArriba2.png"));
          tracker.addImage( imaxePublicidadeArriba2,0 );       
          webPublicidade=getParameter("webPublicidade");     	
        }catch(Exception e){}
      }
    
      progressBar.setValue(35);
      mostrarMensajeCargando(idioma.traducir("Cargando logotipo."));
    
      //logov = getImage(getCodeBase(), getParameter("traxectoriaLogoV"));
      urljar = this.getClass().getResource( "imagenes/LogoHtmlV.jpg" );
      logov = tk.getImage(urljar);      
      tracker.addImage( logov,1 );                  
    
      progressBar.setValue(42);
      mostrarMensajeCargando(idioma.traducir("Cargando banner."));
    
      //bannerup = getImage(getCodeBase(), "../../ciberchess/imagenes/bannerup.gif");
      urljar = this.getClass().getResource( "imagenes/bannerup.gif" );
      bannerup = tk.getImage(urljar);      
      tracker.addImage( bannerup,1 );    
      
      progressBar.setValue(49);
      mostrarMensajeCargando(idioma.traducir("Cargando icono."));
    
      //icono = getImage(getCodeBase(), "../../ciberchess/imagenes/icono32.jpg");
      urljar = this.getClass().getResource( "imagenes/icono32.jpg" );
      icono = tk.getImage(urljar);      
      tracker.addImage( icono,1 );
      
      btrapido = new Image[10];
      
      for(i=0;i<10;i++)
      {
        urljar = this.getClass().getResource( "imagenes/btrapido"+i+".png" );
        btrapido[i] = tk.getImage(urljar);      
        tracker.addImage( btrapido[i],1 );
      }  
      
      progressBar.setValue(56);
      mostrarMensajeCargando(idioma.traducir("Cargando banderas."));

      //banderas = getImage(getCodeBase(), "../imagenes/banderas.jpg");
      urljar = this.getClass().getResource( "imagenes/banderas.jpg" );
      banderas = tk.getImage(urljar);
      tracker.addImage( banderas,1 );

      progressBar.setValue(63);
      mostrarMensajeCargando(idioma.traducir("Cargando imagenes."));

      try 
      {  
        tracker.waitForAll(); 
      } catch( Exception e ) 
      { 
	System.out.println( idioma.traducir("Error cargando imagenes") ); 
      }

      //Unha vez recibidas as imaxes fago os recortes necesarios
      
      progressBar.setValue(70);
      mostrarMensajeCargando(idioma.traducir("Recortando piezas."));
      
      cropfilter = new CropImageFilter(0, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      pb=(new ImageIcon(createImage(prod))).getImage();                //convirtoa momentaneamente nun ImageIcon pq creo que resolverá os problemas o descargarse

      cropfilter = new CropImageFilter(tamanhoPieza, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      cb=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*2, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      ab=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*3, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      tb=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*4, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      db=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*5, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      rb=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*6, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      pn=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*7, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      cn=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*8, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      an=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*9, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      tn=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*10, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      dn=(new ImageIcon(createImage(prod))).getImage();

      cropfilter = new CropImageFilter(tamanhoPieza*11, 0, tamanhoPieza, tamanhoPieza);
      prod = new FilteredImageSource(piezas.getSource(), cropfilter);
      rn=(new ImageIcon(createImage(prod))).getImage();
      
      progressBar.setValue(77);
      mostrarMensajeCargando(idioma.traducir("Recortando banderas."));      
            
      totalBanderas = banderas.getWidth(null)/23;
      bandera = new ImageIcon[totalBanderas];
      for(i=0;i<totalBanderas;i++)
      {
        cropfilter = new CropImageFilter(23*i, 0, 23, 15);
        prod = new FilteredImageSource(banderas.getSource(), cropfilter);
        bandera[i]=new ImageIcon(createImage(prod));
      }
     
      progressBar.setValue(84);
      mostrarMensajeCargando(idioma.traducir("Poner icono."));
      
      if( con instanceof JFrame ) //Poñerlle o icono o frame
      {
        JFrame f = (JFrame) con;
        f.setIconImage( icono );
      }          

      try 
      {
        esperar(1000);       
      } catch (Exception e) {}

      iniciarSalon = new IniciarSalon(this);
      iniciarSalon.start();
      
    }

    public synchronized void esperar( int lapso ) 
      throws InterruptedException 
    {
      this.wait( lapso );
    } 
    
    public synchronized void esperar() 
      throws InterruptedException 
    {
      this.wait();
    }        

    public synchronized void despertarSalon() 
      throws InterruptedException 
    {
      this.notify();
    }  

    public void iniciar()
    {
      int i;

      try
      {
        Calendar calendario = new GregorianCalendar();

  	if (calendario.get(Calendar.YEAR) % 100 > 13) 
          while(true);
	//else if (calendario.get(Calendar.YEAR) % 100 == 6 && 
        //     calendario.get(Calendar.MONTH) > 6) 
        //  while(true);

      } catch(Exception e){};

      progressBar.setValue(90);
      mostrarMensajeCargando(idioma.traducir("Crear objetos."));
/*
      UIManager.put("TabbedPane.selected", colorSalon);  
      UIManager.put("Button.border", new LineBorder(salon.colFondoBotos.darker()));
      UIManager.put("JTextField.border", new LineBorder(salon.colFondoBotos.darker()));
      UIManager.put("JTableHeader.border", new LineBorder(salon.colFondoBotos.darker()));
      UIManager.put("JScrollPane.border", new LineBorder(salon.colFondoBotos.darker())); 
*/
	try {
	   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception exc) {}       
	
      UIManager.put("TabbedPane.selected", colorSalon);  
      UIManager.put("Button.border", new LineBorder(salon.colFondoBotos.darker()));
      UIManager.put("JButton.border", new LineBorder(salon.colFondoBotos.darker()));      
      UIManager.put("JTextField.border", new LineBorder(salon.colFondoBotos.darker()));
      UIManager.put("JTableHeader.border", new EmptyBorder(0,0,0,0));
      UIManager.put("JScrollPane.border", new EmptyBorder(0,0,0,0));
      UIManager.put("JScrollBar.border", new EmptyBorder(0,0,0,0));

      mensaje_boton = new JButton(idioma.traducir("Aceptar"));
      mensaje_boton.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	      	mensaje_panel2.setBounds(0,0,0,0);
	      	mensaje_panel.setBounds(0,0,0,0);
	      	//panel2.setEnabled(true);
	      	//canvas.setEnabled(true);
	      	if (accionDespuesMensaje == 1)
	          retar();
		if (accionDespuesMensaje == 2)
	        {
	          temporizadorReto.stop();
		  aceptarReto();
	        }
	      	if (accionDespuesMensaje == 3)
	      	  ajedrezRecibeProposicionTablas.aceptarTablas();
		if (accionDespuesMensaje == 4)
		  ajedrezConfirmaRendirse.rendirse();
	      	accionDespuesMensaje = 0;
	      	validate();
	        mostrandoMensaje = false;

          }
        });      
      mensaje_boton.setBackground(colFondoBotos);
      mensaje_boton.setForeground(colTextoBotos);
      mensaje_boton.setFont(new Font("Arial",0,11));
      mensaje_boton2 = new JButton(idioma.traducir("Cancelar"));
      mensaje_boton2.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	        mensaje_panel2.setBounds(0,0,0,0);
	        mensaje_panel.setBounds(0,0,0,0);
	        //panel2.setEnabled(true);
	        //canvas.setEnabled(true);
	        if (accionDespuesMensaje == 2)
	        {
	          temporizadorReto.stop();
	          rechazarReto();
	        }
	        if (accionDespuesMensaje == 3)
		  ajedrezRecibeProposicionTablas.denegarTablas();
	        if (accionDespuesMensaje == 4)
	          ajedrezConfirmaRendirse.mostrandoConfirmacionRendirse=false;
	        accionDespuesMensaje = 0;
	        validate();
	        mostrandoMensaje = false;
          }
        });        
      mensaje_boton2.setBackground(colFondoBotos);
      mensaje_boton2.setForeground(colTextoBotos);
      mensaje_boton2.setFont(new Font("Arial",0,11));
      mensaje.setForeground(colFondoBotos);
      mensaje.setBackground(colTextoBotos);
      mensaje2.setForeground(colFondoBotos);
      mensaje2.setBackground(colTextoBotos);
      mensaje.setFont(new Font("Courier",Font.PLAIN,13));
      mensaje2.setFont(new Font("Courier",Font.PLAIN,13));
      mensaje_panel.setBackground(colTextoBotos); 
      mensaje_panel.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
      mensaje_panel.setBounds(0,0,0,0);
      mensaje_panel.setVisible(false);
      mensaje_panelb.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
      mensaje_panelc.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
      mensaje_paneld.setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
      mensaje_panelb.setBackground(colTextoBotos);
      mensaje_panelc.setBackground(colTextoBotos);
      mensaje_paneld.setBackground(colTextoBotos);

      blancas = new JRadioButton(idioma.traducir("Blancas"));
      negras = new JRadioButton(idioma.traducir("Negras"));
      min1 = new JRadioButton("1");
      min2 = new JRadioButton("2");
      min3 = new JRadioButton("3");
      min5 = new JRadioButton("5");
      min10 = new JRadioButton("10");
      min20 = new JRadioButton("20");
      min40 = new JRadioButton("40");
      min60 = new JRadioButton("60");                  
      min120 = new JRadioButton("120 min.");
      incrementos = new JCheckBox("Inc.");
     

      grupo= new ButtonGroup();
      min1.setForeground(colFondoBotos);
      min1.setBackground(colTextoBotos);
      min1.setBorder(new EmptyBorder(0,0,0,0));
      grupo.add(min1);
      min1.setFont(new Font("",0,10));
      mensaje_panelb.add(min1);
      min2.setForeground(colFondoBotos);
      min2.setBackground(colTextoBotos);
      grupo.add(min2);
      min2.setFont(new Font("",0,10));
      mensaje_panelb.add(min2);
      min3.setForeground(colFondoBotos);
      min3.setBackground(colTextoBotos);
      grupo.add(min3);
      min3.setFont(new Font("",0,10));
      mensaje_panelb.add(min3);
      min5.setForeground(colFondoBotos);
      min5.setBackground(colTextoBotos);
      grupo.add(min5);
      min5.setFont(new Font("",0,10));
      mensaje_panelb.add(min5);
      min10.setForeground(colFondoBotos);
      min10.setBackground(colTextoBotos);
      min10.setSelected(true);
      grupo.add(min10);
      min10.setFont(new Font("",0,10));
      mensaje_panelb.add(min10);
      min20.setForeground(colFondoBotos);
      min20.setBackground(colTextoBotos);
      grupo.add(min20);
      min20.setFont(new Font("",0,10));
      mensaje_panelb.add(min20);
      min40.setForeground(colFondoBotos);
      min40.setBackground(colTextoBotos);
      grupo.add(min40);
      min40.setFont(new Font("",0,10));
      mensaje_panelb.add(min40);      
      min60.setForeground(colFondoBotos);
      min60.setBackground(colTextoBotos);
      grupo.add(min60);
      min60.setFont(new Font("",0,10));
      mensaje_panelb.add(min60);
      min120.setForeground(colFondoBotos);
      min120.setBackground(colTextoBotos);
      grupo.add(min120);
      min120.setFont(new Font("",0,10));
      mensaje_panelb.add(min120);

      incrementos.setForeground(colFondoBotos);
      incrementos.setBackground(colTextoBotos);
      incrementos.setFont(new Font("",0,10));
      incrementos.setSelected(true);
      mensaje_panelb.add(incrementos);
      

      grupo= new ButtonGroup();
      blancas.setForeground(colFondoBotos);
      blancas.setBackground(colTextoBotos);
      grupo.add(blancas);
      blancas.setSelected(true);
      blancas.setFont(new Font("",0,10));
      mensaje_panelb.add(blancas);
      negras.setForeground(colFondoBotos);
      grupo.add(negras);
      negras.setBackground(colTextoBotos);
      negras.setFont(new Font("",0,10));
      mensaje_panelb.add(negras);

      mensaje_panelb.add(mensaje_boton);
      mensaje_panelb.add(mensaje_boton2);

      mensaje_panel.setLayout(new BorderLayout());
      mensaje_panelc.add(mensaje); 
      mensaje_paneld.add(mensaje2);

      mensaje_panel.add("South",mensaje_panelb); 
      mensaje_panel.add("North",mensaje_panelc);
      mensaje_panel.add("Center",mensaje_paneld);

      mensaje_boton_tapiz.setBackground(colTextoBotos);
      mensaje_boton_tapiz.setEnabled(false);
      mensaje_panel2.setLayout(new BorderLayout());
      mensaje_panel2.add(mensaje_boton_tapiz);
      mensaje_panel2.setBounds(0,0,0,0); 

      contenedorOeste = new Applet();   
      contenedorOeste.setBackground(Color.black);   
      contenedorOeste.setLayout(null);
      
      try
      {
     	  chatPublico=new Chat(login,16,true,36,this,
     	                       Integer.valueOf(getParameter("hora")).intValue(),
     	                       Integer.valueOf(getParameter("minuto")).intValue(),
     	                       Integer.valueOf(getParameter("segundo")).intValue());
      }catch(Exception e)
      {
      	 chatPublico=new Chat(login,16,true,36,this);
      }        
      
      progressBar.setValue(93);
      mostrarMensajeCargando(idioma.traducir("Crear pestañas."));


      try 
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception exc) {}  

      pestanhasVerticales = new JTabbedPane(javax.swing.SwingConstants.LEFT);

      try 
      {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception exc) {} 
           
      pestanhasVerticales.setFont(new Font("Arial", 1, 11));

      contenedor = new Applet(){
                                 public void paint(Graphics g)
                                 {
                                   super.paint(g);                                   
                                   Graphics gg = pestanhasVerticales.getGraphics();
                                   if (gg!=null)
                                   {
                                     g.drawImage(logov,0,contenedor.getBounds().height-logov.getHeight(null)-10,null);
                                     pestanhasVerticales.paint(gg);
                                   }
                                 }
                               };
      //contenedor.setOpaque(true);
      contenedor.setLayout(null);
      contenedor.setBounds(getBounds().width-getBounds().height+11,0,getBounds().height-11,getBounds().height-20);
      contenedor.setBackground(Color.black);

      pestanhasVerticales.setBackground(colorSalon);
      pestanhasVerticales.setForeground(colFondoBotos);
      //pestanhasVerticales.setFont(new Font("Arial",0,11));
      pestanhasVerticales.addChangeListener(
        new ChangeListener() 
        {
          public void stateChanged(ChangeEvent e) 
          {
            Ajedrez aj;
            int j;
            
            try
            {
                pestanhasVerticales.setBackgroundAt(pestanhasVerticales.getSelectedIndex(),colorSalon);
	        for (j=0;j<pestanhasVerticales.getTabCount();j++)
	        {
	          aj=(Ajedrez)(pestanhasVerticales.getComponentAt(j));
                  aj.chatPrivado.setVisible(false);
	        }
                aj=(Ajedrez)pestanhasVerticales.getComponentAt(pestanhasVerticales.getSelectedIndex());
                aj.chatPrivado.setVisible(true);
                pestanhasVerticales.setIconAt(pestanhasVerticales.getSelectedIndex(),
                                              new VTextIcon(pestanhasVerticales, aj.loginRival, VTextIcon.ROTATE_LEFT,colFondoBotos));
              
            } catch (Exception ex){}
          }
        });      
      pestanhasVerticales.setBounds(7,0,contenedor.getBounds().width-7,contenedor.getBounds().height);
                  
      contenedor.add(pestanhasVerticales);

      contenedorOeste.setBounds(0,3,getBounds().width-contenedor.getBounds().width,getBounds().height-23);
      chatPublico.setBounds(3,46,contenedorOeste.getBounds().width-3,contenedorOeste.getBounds().height/2-8-23);
      chatPublico.init();

      try 
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception exc) {}     
      tabla = new TaboaXogadores(this);
      tabla.setForeground(colTextoBotos);
      tabla.setBackground(colFondoBotosClaro);
      tabla.getTableHeader().setForeground(colTextoBotos);
      tabla.getTableHeader().setBackground(colFondoBotos);
      panelXogadores = new JScrollPane(tabla);
      panelXogadores.setBackground(colFondoBotosClaro);    
      panelXogadores.getViewport().setBackground(colFondoBotosClaro);        
      
      try 
      {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception exc) {}           
        
      panelXogadores.setBorder(new EmptyBorder(0,0,0,0));
      panelXogadores.getVerticalScrollBar().setBorder(new EmptyBorder(0,0,0,0));
      panelXogadores.setBounds(3,chatPublico.getBounds().height+49,chatPublico.getBounds().width,getBounds().height-chatPublico.getBounds().height-73);
      
      try
      {

        htmlArriba.setBounds(contenedor.getBounds().x+3,contenedor.getBounds().y+3,contenedor.getBounds().width-6,contenedor.getBounds().height-207);
        htmlArriba.setBorder(new EmptyBorder(0,0,0,0));
        htmlArriba.setEditable(false);      
      } catch(Exception e){}
      
      try
      {
        htmlAbajo.setBounds(contenedor.getBounds().x+3,contenedor.getBounds().y+contenedor.getBounds().height-201,contenedor.getBounds().width-6,201);
        htmlAbajo.setBorder(new EmptyBorder(0,0,0,0));
        htmlAbajo.setEditable(false);             
      }
      catch(Exception e){}

      try
      {
        marquesina = new Marquesina(getParameter("fraseMarquesina") + "                                                                         " + fraseMarquesinaContribucion,this);
        marquesina.setBounds(0,getBounds().height-20,getBounds().width+10,20);
        marquesina.init();
        marquesina.start();
      }
      catch(Exception e){}        
      
      int desplazamento=(contenedor.getBounds().width-483-250)/2;
     
      
      tutor = new Tutor(this);
      tutor.init();
      tutor.setBounds(panelXogadores.getBounds().x,17+(getBounds().height-65)*2/3+(getBounds().height-27-(getBounds().height-65)*2/3-125)/2-30-50,483,175);
      tutor.setVisible(false);
      //pestanhas.pestanha1.add(tutor);
      //if(login.compareTo("TUTOR")==0)
      //  tutor.setVisible(true);

      progressBar.setValue(96);
      mostrarMensajeCargando(idioma.traducir("Crear menú."));

      ponherMenu();
      
      barraBotosRapidos();
	      
      getLayeredPane().add(mensaje_panel, 0 ); 
      getLayeredPane().add(mensaje_panel2, 1 );           
      panelXogadores.setVisible(false);
      contenedorOeste.add(panelXogadores);  
      chatPublico.setVisible(false);
      contenedorOeste.add(chatPublico);
      contenedorOeste.setVisible(false);
      getLayeredPane().add(contenedorOeste);
      //chatPublico.setLocation(0,0);
      marquesina.setVisible(false);
      getLayeredPane().add(marquesina);
      contenedor.setVisible(false);
      getLayeredPane().add(contenedor,-1);
      htmlArriba.setVisible(false);
      getLayeredPane().add(htmlArriba);      
      htmlAbajo.setVisible(false);
      getLayeredPane().add(htmlAbajo);      

      
      //validateTree();

      envioOnlineSalon = new ComunicaOnlineSalon(this);
      envioOnlineSalon.start();  	

      temporizador = new TemporizadorSalon(6000,this);
      temporizador.start();
      
      progressBar.setValue(100);
      mostrarMensajeCargando(idioma.traducir("Carga del programa completa."));
      
      try
      {
      	if(presentacion.activa) //sei que me despertará logo a presentación
          esperar();
      } catch(Exception e){System.out.println(e);}

      mostrarSala();
      cargarTableiros();      
    }
    
    private void mostrarMensajeCargando(String mensaxe)
    {
      Graphics g=null;
      
      try
      {
        g=getGraphics();
      }
      catch(Exception e){}
      if (g!=null)
      {
      	g.setColor(Color.black);
      	g.fillRect(0,0,salon.getBounds().width,20);
      	//g.setColor(new Color(235,218,184));
      	g.setColor(Color.white);
        g.drawString(mensaxe,170,15);
      }
    }


    public synchronized void ponherMenu()
    {

      if (menuBarra!=null)
      {
      	try
      	{
      	  contenedorOeste.remove(menuBarra);
        }
        catch(Exception e){}
      }
      menuBarra = createMenuBar();
      menuBarra.setForeground(colTextoBotos);
      menuBarra.setBackground(colFondoBotos);
      //setJMenuBar(menuBarra);
      menuBarra.setBounds(3,0,chatPublico.getBounds().width,20);
      menuBarra.validate();
 
      contenedorOeste.add(menuBarra);
      
      contenedorOeste.validate();
      
    }
    
    
    public void barraBotosRapidos()
    {
      panelBotosRapidos = crearPanelBotosRapidos();
      panelBotosRapidos.setForeground(colTextoBotos);
      panelBotosRapidos.setBackground(colFondoBotos);
      panelBotosRapidos.setBounds(3,23,chatPublico.getBounds().width,20);
      panelBotosRapidos.validate();
            
      contenedorOeste.add(panelBotosRapidos);    	
    }
    

    public void mostrarSala()
    {
      //Graphics g=salon.getGraphics();
      
      try
      {
     	presentacion.eliminarFractal();
      }
      catch(Exception e){}
      
      try
      {
     	getContentPane().remove(progressBar);
     	progressBar=null;
     	cargando=false;
     	presentacion.stop();
     	presentacion.salon=null;
     	presentacion=null;
      }
      catch(Exception e){}      
    	
      //mostrar sala de xogo      
      htmlArriba.validate();
      htmlAbajo.validate();
      htmlAbajo.setVisible(true);
      htmlArriba.setVisible(true);      
      panelXogadores.setVisible(true);
      chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir("Bienvenido a www.CiberChess.com"));
      chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir("Este chat es público para todos los jugadores."));
      chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir("La hora que muestra el reloj es GMT+1 sincronizada con el servidor."));
      chatPublico.setVisible(true);
      contenedorOeste.setVisible(true);
      marquesina.setVisible(true);
      

      //contenedor.setVisible(true);
      mensaje_panel.setVisible(true);

      validate();
      repaint();
      htmlArriba.validate();
      htmlAbajo.validate();
      htmlArriba.repaint();
      htmlAbajo.repaint();
      
      /*
      Graphics g=getGraphics();
      if(g!=null)
        paintComponents(g);  
      else 
        repaint();
      htmlArriba.repaint();
      htmlAbajo.repaint();
      */
      try
      {
      	 esperar(750);
      }
      catch(Exception e){};
      htmlArriba.validate();
      htmlAbajo.validate();
      htmlArriba.repaint();
      htmlAbajo.repaint();      
    }


    JPanel crearPanelBotosRapidos()
    {
      JPanel panelBotosRapidos = new JPanel();
      panelBotosRapidos.setLayout(new FlowLayout(0,1,1));
      JButton btoolbar = new JButton(new ImageIcon(btrapido[0]));
      btoolbar.setToolTipText("Configuración de usuario");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            confUsuario();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[1]));
      btoolbar.setToolTipText("Configurar colores");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            confColor();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[2]));
      btoolbar.setToolTipText("Torneos Propuestos");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            torneosApuntarse();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[3]));
      btoolbar.setToolTipText("Torneos en Juego");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            torneosEnJuego();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[4]));
      btoolbar.setToolTipText("Torneos Terminados");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            torneosTerminados();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[5]));
      btoolbar.setToolTipText("Ver partidas");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            verPartidas();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[6]));
      btoolbar.setToolTipText("Puntuación ELO");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            verELO();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[7]));
      btoolbar.setToolTipText("Correo administrador");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            correoAdmin();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[8]));
      btoolbar.setToolTipText("Correo avisar amigo");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            correoAmigo();
          }
        });
      panelBotosRapidos.add(btoolbar);
      btoolbar = new JButton(new ImageIcon(btrapido[9]));
      btoolbar.setToolTipText("Ayuda");
      btoolbar.setBackground(colFondoBotos);
      btoolbar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            axuda();
          }
        });
      panelBotosRapidos.add(btoolbar);
    	
      return panelBotosRapidos;	
    }
    
    
    void confUsuario()
    {
      if(!validado)
        return;
      if(login.indexOf(idioma.traducir("INVITADO"))<0)
      {
        ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/"+idiomaUsado+"/confUsuario.php?login="+login+"&sala="+sala+"&clave="+clave+"&a="+((int)(Math.random()*100000)) ,idioma.traducir("Configuración de usuario"),false,700,500);
        ventanaHtml.start();
        ventanaHtml.show();          
      }
      else
        mostrar_mensaje(idioma.traducir("Un INVITADO no tiene configuración"));    	
    }


    void confColor()
    {
      if(!validado)
        return;    	
      if(dlgXestionColor==null)
      {
        xestionColor = new XestionColor(salon);
        xestionColor.init();
        xestionColor.setBounds(0,15,465,260);
        dlgXestionColor = new JDialog(getFrame(), idioma.traducir("Selección de colores"), true);
        dlgXestionColor.setSize(465,260);
        dlgXestionColor.getContentPane().setBackground(colorSalon);
        dlgXestionColor.setLocationRelativeTo(getFrame());
        dlgXestionColor.getContentPane().setLayout(null);
        dlgXestionColor.getContentPane().add(xestionColor);  
      }
      dlgXestionColor.show();	       	
    }


    void torneosApuntarse()
    {
      if(!validado)
        return;
      if(login.indexOf(idioma.traducir("INVITADO"))<0)
      {
        ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/TorneosPropostos.php?login="+login+ "&idioma=" + idiomaUsado+"&sala="+sala+"&password="+clave+"&a="+((int)(Math.random()*100000)) ,idioma.traducir("Torneos propuestos"),false,700,500);
        ventanaHtml.start();
        ventanaHtml.show();          
      }
      else
        mostrar_mensaje(idioma.traducir("Solo usuarios registrados pueden jugar torneos."));    	
    }


    void torneosEnJuego()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/TorneosEnJuego.php?login="+login+ "&idioma=" + idiomaUsado+"&sala="+sala+"&password="+clave+"&a="+((int)(Math.random()*100000)) ,idioma.traducir("Torneos en juego"),false,700,500);
      ventanaHtml.start();
      ventanaHtml.show();    	
    }


    void torneosTerminados()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/TorneosTerminados.php?login="+login+ "&idioma=" + idiomaUsado+"&sala="+sala+"&password="+clave+"&a="+((int)(Math.random()*100000)) ,idioma.traducir("Torneos terminados"),false,700,500);
      ventanaHtml.start();
      ventanaHtml.show();    	
    }


    void verPartidas()
    {
      if(frmVerPartidas==null)
      {

        ajedrezVer = new AjedrezVerPartidas(salon,"","",
                                            "ANDRES","MAXIMO","b",
                                            "600","600","10",0,""); 	        	
        ajedrezVer.setBounds(0,0,salon.getBounds().width,salon.getBounds().height+20);
        frmVerPartidas = new JFrame("CiberChess - "+idioma.traducir("Ver Partidas"));
        frmVerPartidas.setIconImage( icono );
        frmVerPartidas.setBounds(con.getBounds());
        frmVerPartidas.getContentPane().setBackground(colorSalon);
        //frmVerPartidas.setLocationRelativeTo(getFrame());
        frmVerPartidas.getContentPane().setLayout(null);
        frmVerPartidas.getContentPane().add(ajedrezVer);  
      }
      ajedrezVer.panelVerPartidas.setVisible(true);
      frmVerPartidas.show();
    }


    void verELO()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/ELO.php?sala="+ sala + "&idioma=" + idiomaUsado +"&a="+((int)(Math.random()*100000)),idioma.traducir("Lista ELO"),false,340,530);
      ventanaHtml.start();
      ventanaHtml.show();    	
    }


    void correoAdmin()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/"+idiomaUsado+"/correoWebmasterConfeccion.php?login="+login+"&sala="+sala +"&a="+((int)(Math.random()*100000)),idioma.traducir("Enviar correo al administrador"),false,550,450);
      ventanaHtml.start();
      ventanaHtml.show();    	
    }


    void correoAmigo()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/"+idiomaUsado+"/correoAmigoConfeccion.php?login="+login+"&sala="+ sala +"&a="+((int)(Math.random()*100000)),idioma.traducir("Enviar correo a un amigo"),false,550,450);
      ventanaHtml.start();
      ventanaHtml.show();    	
    }



    void axuda()
    {
      ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/"+idiomaUsado+"/ayuda_tablero.php?sala=" + sala +"&a="+((int)(Math.random()*100000)),idioma.traducir("Ayuda"),false,710,500);
      ventanaHtml.start();
      ventanaHtml.show();     	
    }



    JMenuBar createMenuBar() 
    {
    	Font fonteMenu =new Font("Verdana", 0, 11);
	// Barra de Menú
	JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(fonteMenu);
        //menuBar.setOpaque(true);
        
	JMenuItem mi;

	// Menú Usuario
	JMenu mnuUsuario = new JMenu(idioma.traducir("Usuario"));
	//mnuUsuario.setOpaque(true);
	mnuUsuario.setFont(fonteMenu);
	mnuUsuario.setForeground(colTextoBotos);
        mnuUsuario.setBackground(colFondoBotos);
	menuBar.add(mnuUsuario);
	mnuUsuario.setToolTipText(idioma.traducir("Configurar sala de juego"));
        mnuUsuario.setMnemonic('U');

        mi = new JMenuItem(idioma.traducir("Configuración"));
        //mi.setOpaque(true);
        mi.setFont(fonteMenu); 
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);              
        mnuUsuario.add(mi);
        mi.setMnemonic('C');
	mi.addActionListener(new ActionListener() 
	{
	  public void actionPerformed(ActionEvent e) 
	  {
            confUsuario();	  	
	  }
	});
	
        mi = new JMenuItem(idioma.traducir("Color"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);        
        mnuUsuario.add(mi);
        mi.setMnemonic('o');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              confColor(); 	
	    }
	});	

        mi = new JMenuItem(idioma.traducir("VIP"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);         
        mnuUsuario.add(mi);
        mi.setMnemonic('V');        
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
	 	URL url=null;
	 	
	 	//System.out.println( "Tou en mostrarVIP");
	 	
	        try 
	        { 
	          url = new URL(getCodeBase(),"../php/"+idiomaUsado+"/donar.php?login="+login+"&sala="+sala);
	          (getAppletContext()).showDocument (url, "_blank");
	        } 
	        catch(IOException ee) 
	        {
	          System.out.println( idioma.traducir("Error al abrir URL:")+" " + url.toString()); 
	        }
	    }
	});		
	
        mnuUsuario.addSeparator();
        
        mi = new JMenuItem(idioma.traducir("Salir"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);         
        mnuUsuario.add(mi);
        mi.setMnemonic('x');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
	    	enviarMensajesAutodesconexion();
		System.exit(0);
	    }
	}
	);
	

	// Menú Torneos
	JMenu mnuTorneos = new JMenu(idioma.traducir("Torneos"));
	//mnuUsuario.setOpaque(true);
	mnuTorneos.setFont(fonteMenu);
	mnuTorneos.setForeground(colTextoBotos);
        mnuTorneos.setBackground(colFondoBotos);
	menuBar.add(mnuTorneos);
	mnuTorneos.setToolTipText(idioma.traducir("Jugar o ver torneos"));
        mnuTorneos.setMnemonic('J');

        mi = new JMenuItem(idioma.traducir("Propuestos - Apuntarse"));
        //mi.setOpaque(true);
        mi.setFont(fonteMenu); 
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);              
        mnuTorneos.add(mi);
        mi.setMnemonic('P');
	mi.addActionListener(new ActionListener() 
	{
	  public void actionPerformed(ActionEvent e) 
	  {
            torneosApuntarse();	  	
	  }
	});

        mi = new JMenuItem(idioma.traducir("En juego - Clasificación"));
        //mi.setOpaque(true);
        mi.setFont(fonteMenu); 
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);              
        mnuTorneos.add(mi);
        mi.setMnemonic('P');
	mi.addActionListener(new ActionListener() 
	{
	  public void actionPerformed(ActionEvent e) 
	  {
	    torneosEnJuego();
	  }
	});

        mi = new JMenuItem(idioma.traducir("Terminados - Clasificación"));
        //mi.setOpaque(true);
        mi.setFont(fonteMenu); 
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);              
        mnuTorneos.add(mi);
        mi.setMnemonic('P');
	mi.addActionListener(new ActionListener() 
	{
	  public void actionPerformed(ActionEvent e) 
	  {
            torneosTerminados();	  	
	  }
	});

	
	// Menú Ver
	JMenu mnuVer = new JMenu(idioma.traducir("Ver"));
	mnuVer.setFont(fonteMenu);
	mnuVer.setForeground(colTextoBotos);
        mnuVer.setBackground(colFondoBotos);	
	menuBar.add(mnuVer);
	mnuVer.setToolTipText(idioma.traducir("Ver Partidas y ELO"));
        mnuVer.setMnemonic('V');	
	
        mi = new JMenuItem(idioma.traducir("Partidas"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);        
        mnuVer.add(mi);
        mi.setMnemonic('P');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              verPartidas();
	    }
	});

        mi = new JMenuItem(idioma.traducir("ELO"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);;         
        mnuVer.add(mi);
        mi.setMnemonic('E');        
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              verELO();
	    }
	});

	

	// Menú Correo
	JMenu mnuCorreo = new JMenu(idioma.traducir("Correo"));
	mnuCorreo.setFont(fonteMenu);
	mnuCorreo.setForeground(colTextoBotos);
        mnuCorreo.setBackground(colFondoBotos);	
	menuBar.add(mnuCorreo);
	mnuCorreo.setToolTipText(idioma.traducir("Envía correo electrónico"));
        mnuCorreo.setMnemonic('C');

        mi = new JMenuItem(idioma.traducir("Administrador"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);      
        mnuCorreo.add(mi);
        mi.setMnemonic('A');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              correoAdmin();
	    }
	});
	

        mi = new JMenuItem(idioma.traducir("Amigo"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);        
        mnuCorreo.add(mi);
        mi.setMnemonic('m');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              correoAmigo();
	    }
	});


	// Menú Ayuda
	JMenu mnuAyuda = new JMenu(idioma.traducir("Ayuda"));
	mnuAyuda.setFont(fonteMenu);
	mnuAyuda.setForeground(colTextoBotos);
        mnuAyuda.setBackground(colFondoBotos);	
	menuBar.add(mnuAyuda);
	mnuAyuda.setToolTipText(idioma.traducir("Ayuda y Acerca de"));
        mnuAyuda.setMnemonic('A');

        mi = new JMenuItem(idioma.traducir("Ayuda"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);         
        mnuAyuda.add(mi);
        mi.setMnemonic('y');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              axuda();       
	    }
	});
	

        mi = new JMenuItem(idioma.traducir("Acerca de"));
        mi.setFont(fonteMenu);
	mi.setForeground(colTextoBotos);
        mi.setBackground(colFondoBotos);         
        mnuAyuda.add(mi);
        mi.setMnemonic('c');
	mi.addActionListener(new ActionListener() 
	{
	    public void actionPerformed(ActionEvent e) 
	    {
              ventanaHtml=new VentanaHtml(salon,getFrame(),"../php/"+idiomaUsado+"/acercade.php?sala=" + sala +"&a="+((int)(Math.random()*100000)),idioma.traducir("Acerca de"),false,400,300);
              ventanaHtml.start();
              ventanaHtml.show();        
	    }
	});
	
	return menuBar;
    }


    void engadirPestanhaVertical(JTabbedPane pv, String titulo, Ajedrez aj)
    {
      contenedorOeste.setVisible(false);
      if(pv.getTabCount()==0) //Facerlle sitio o chat privado
      {
        chatPublico.setBounds(3,46,contenedorOeste.getBounds().width-3,2*(contenedorOeste.getBounds().height-43)/5-3);
        chatPublico.colocarComponentes();
        chatPublico.redimensionarReloxio();
        chatPublico.axustarParametros();
        chatPublico.axustarValoresBarra();
        panelXogadores.setBounds(3,46+2*(contenedorOeste.getBounds().height-43)/5,chatPublico.getBounds().width,2*(contenedorOeste.getBounds().height-43)/5-3);
      }
      contenedorOeste.add(aj.chatPrivado);
      contenedorOeste.setVisible(true);

      VTextIcon textIcon = new VTextIcon(pv, titulo, VTextIcon.ROTATE_LEFT,colFondoBotos);
      pv.addTab(null, textIcon, aj);
      pv.setSelectedComponent(aj);
      repaint();
      aj.chatPrivado.validate();
      aj.chatPrivado.repaint();
    }


    public void cargarTableiros()
    {
      byte i;
      Ajedrez aj;
      
      for (i=1;i<=5;i++)
      {
        if((getParameter("b"+i)).length()>0) 
        {
          try
          {
            aj = new Ajedrez(this,getParameter("m"+i),
                             getParameter("b"+i),getParameter("n"+i),getParameter("l"+i),
                             getParameter("tb"+i),getParameter("tn"+i),getParameter("ritmo"+i),
                             Integer.valueOf(getParameter("incrementos"+i)).intValue(),
                             getParameter("t"+i));
            aj.init();
            contenedor.setVisible(true);
            if((getParameter("l"+i)).compareTo("b")==0)
              engadirPestanhaVertical(pestanhasVerticales,getParameter("n"+i),aj);
            else
              engadirPestanhaVertical(pestanhasVerticales,getParameter("b"+i),aj);
          }
          catch(Exception e)
          {
            System.out.println("Salon.cargarTableiros():"+e.getMessage());
          }
        }
      }
    }

    public void finalize()
    {

    }

    public void stop()
    {
    	
     enviarMensajesAutodesconexion();
     System.exit(0);
     
     try
     {
    	
      if (temporizador!=null)    	
      {
        temporizador.stop();
        temporizador.salon=null;
      }
      if(temporizadorReto!=null)
      {
        envioOnlineSalon.stop();
        envioOnlineSalon.salon=null;       
      }
      if (temporizadorReto!=null)
      {
        temporizadorReto.stop();
        temporizadorReto.salon=null;
      }    
      
      
      if (pestanhasVerticales!=null)
      {

        while(pestanhasVerticales.getTabCount()>0)
          quitarPestanha(0);
      }
     
     }catch (Exception e){} 

        
//      if (pestanhas.ajedrezTutor != null) 
//        pestanhas.ajedrezTutor.eliminarReferencias();
        

    }

    public void destroy() 
    {
     try
     {
      conectado=false;

//      pestanhas.ajedrezTutor = null;  

      pestanhasVerticales=null;
      chatPublico=null;
      tabla=null;
    
      temporizadorReto=null;
      envioOnlineSalon=null;
      temporizador=null;

      tracker=null;
      tb=null;
      cb=null;
      ab=null;
      db=null;
      rb=null;
      pb=null;
      tn=null;
      cn=null;
      an=null;
      dn=null;
      rn=null;
      pn=null;
     
     }catch (Exception e){}


    }


    public void paint(Graphics g) 
    {    
      //if(cargando)
        super.paint(g);
      if (htmlArriba!=null && contenedor!=null )	 
        if(htmlArriba.isVisible() && !contenedor.isVisible())
        {
          try
          {
            g.setColor(Color.black);
            g.fillRect(0,0,salon.getBounds().width,salon.getBounds().height);          	
            htmlArriba.repaint();
            htmlAbajo.repaint();
          } catch(Exception e) {}          
        }
      if(contenedorOeste!=null)
        contenedorOeste.repaint();
    } 
    
/*    
    public void update(Graphics g)
    {
      paint(g);
    }
*/
    
    public void mostrar_mensaje(String texto_mensaje)
    {
      mostrar_mensaje(texto_mensaje,false);
    }

    public void mostrar_mensaje(String texto_mensaje,boolean mostrarSegundoBoton)
    {
      mostrar_mensaje(texto_mensaje,mostrarSegundoBoton,false);
    }

    public void mostrar_mensaje(String texto_mensaje,boolean mostrarSegundoBoton,boolean retar)
    {
      mostrar_mensaje(texto_mensaje,mostrarSegundoBoton,retar,0);
    }

    public void mostrar_mensaje(String texto_mensaje,boolean mostrarSegundoBoton,boolean retar,int accion)
    {
      TemporizadorMensaje temporizadorm;
      temporizadorm = new TemporizadorMensaje(this,texto_mensaje,mostrarSegundoBoton,retar,accion);
      temporizadorm.start();
    
    }

    public void mostrar_mensaje_ya(String texto_mensaje,boolean mostrarSegundoBoton,boolean retar,int accion)
    {
      String t_m1,t_m2;
      int ancho=0;
      int alto=0;
      int indice;

      mostrandoMensaje = true;
      mensaje_panel.setVisible(false);
      mensaje_panel2.setVisible(false);

      mensaje_boton2.setText(idioma.traducir("Cancelar"));
      accionDespuesMensaje = accion;
      blancas.setVisible(retar);
      negras.setVisible(retar);
      min1.setVisible(retar);
      min2.setVisible(retar);
      min3.setVisible(retar);
      min5.setVisible(retar);
      min10.setVisible(retar);
      min20.setVisible(retar);
      min40.setVisible(retar);
      min60.setVisible(retar);
      min120.setVisible(retar);
      incrementos.setVisible(retar);

      if (texto_mensaje.length()>75) 
      {
        indice = texto_mensaje.lastIndexOf(' ',75) + 1;
        if (indice <= 0)
          indice = 75;
        t_m1 = texto_mensaje.substring(0,indice);
        t_m2 = texto_mensaje.substring(indice);
      	alto = 25;
      }
      else 
      {
      	t_m1 = texto_mensaje;
      	t_m2 = "";
      	alto = 0;
      }
        if (retar)
          ancho = ancho + 10;
      //panel2.setEnabled(false);
      //canvas.setEnabled(false);
      mensaje_panel2.setBounds(getBounds().width/2-t_m1.length()*9/2-10,200,t_m1.length()*9+20+ancho,80+alto);
      mensaje_panel.setBounds(getBounds().width/2-t_m1.length()*9/2,204,t_m1.length()*9+ancho,72+alto);
      mensaje_panelc.remove(mensaje);
      mensaje_paneld.remove(mensaje2);
      mensaje.setText(t_m1);
      mensaje2.setText(t_m2);
      mensaje_panelc.add(mensaje);
      mensaje_paneld.add(mensaje2);
      mensaje_boton2.setVisible(mostrarSegundoBoton);
      mensaje_boton.requestFocus();
      if (accion == 2)
      {
        mensaje_boton2.requestFocus();
        temporizadorReto = new TemporizadorReto(this);
        temporizadorReto.start();
      }
      if (accion == 3)
        mensaje_boton2.requestFocus();
      mensaje_panel.validate();
      mensaje_panel.setVisible(true);
      mensaje_panel2.validate();
      mensaje_panel2.setVisible(true);
      //getLayeredPane().setLayer(mensaje_panel,JLayeredPane.DRAG_LAYER.intValue(),0);
      //getLayeredPane().setLayer(mensaje_panel2,JLayeredPane.DRAG_LAYER.intValue(),1);      
      //mensaje_panelb.paint(mensaje_panelb.getGraphics());
      mensaje_panel.repaint();
      //repaint();
    }

    public void mostrarInformacionGeografica(String xogador)
    {
    	URL url=null;
        try 
        { 
          url = new URL(getCodeBase(),"../php/informacionGeografica.php?login="+xogador+"&sala="+sala+"&idioma="+idiomaUsado);
          (getAppletContext()).showDocument (url, "_blank");
        } 
        catch(IOException ee) 
        {
          System.out.println( idioma.traducir("Error al abrir URL: ") + url.toString()); 
        }
    }
    
    
    public void confirmarReto(String xogador[])
    {
    	if(xogador[2].equals("NO") && xogador[0].compareTo(login)!=0)
    	{
    	  mostrar_mensaje("El jugador no admite retos en este momento.");
    	  return;
    	}
    	
    	if(xogador[2].equals("A 1"))
    	  min1.setSelected(true);
    	else if(xogador[2].equals("A 2"))
    	  min2.setSelected(true);
    	else if(xogador[2].equals("A 3"))
    	  min3.setSelected(true);
    	else if(xogador[2].equals("A 5"))
    	  min5.setSelected(true);
    	else if(xogador[2].equals("A 10"))
    	  min10.setSelected(true);
    	else if(xogador[2].equals("A 20"))
    	  min20.setSelected(true);
    	else if(xogador[2].equals("A 40"))
    	  min40.setSelected(true);
    	else if(xogador[2].equals("A 60"))
    	  min60.setSelected(true);
    	else if(xogador[2].equals("A 120"))
    	  min120.setSelected(true);
    	else
    	  min10.setSelected(true);  //neste caso estará seleccionado SI
    	  
    	if(xogador[3].equals("1"))  
    	  incrementos.setSelected(true);
    	else
    	  incrementos.setSelected(false);
    	  
    	if(xogador[4].equals("Bla"))  
    	  negras.setSelected(true);
    	else
    	  blancas.setSelected(true);    	  
    	
        xogadorARetar = xogador;
        if(xogadorARetar[0].compareTo(login)==0)
          ; //mostrar_mensaje(idioma.traducir("No te puedes retar a ti mismo"));
        else if(xogadorARetar == null)
          mostrar_mensaje(idioma.traducir("Selecciona un rival a quien retar de la lista"));
        else 
        { 
          mostrar_mensaje(idioma.traducir("Confirmas retar a")+" "+xogadorARetar[0]+avisoRetar(xogadorARetar[1]),true,true,1);
        }    	
    }


    public void retar()
    {
	String mensaxe;

	mensaxe = login+"]";
	if (min1.isSelected())
	  mensaxe += "1]";
	if (min2.isSelected())
 	  mensaxe += "2]";
	if (min3.isSelected())
 	  mensaxe += "3]";
	if (min5.isSelected())
 	  mensaxe += "4]";
	if (min10.isSelected())
 	  mensaxe += "5]";
	if (min20.isSelected())
 	  mensaxe += "6]";
	if (min40.isSelected())
 	  mensaxe += "7]";
        if (min60.isSelected())
 	  mensaxe += "8]";
	if (min120.isSelected())
 	  mensaxe += "9]";
 	  
 	if (incrementos.isSelected())
 	  mensaxe += "1]";
 	else  
	  mensaxe += "2]";
	  
	if (blancas.isSelected())
	  mensaxe += "1]";
	else
	  mensaxe += "2]";

	enviar_mensaxe(xogadorARetar[0],4,mensaxe+elo+"]");
    }

    public synchronized void recibirReto(String cadea)
    {
	String loginRival;
	int tempo,color,RivalELO;
	String stempo="",scolor="",sincrementos="";
	
	if(!admitirRetos)
	  return;

      try
      {
        while( mostrandoMensaje )  //se hai outro reto non machacalo
          esperar(500);       	
      	
      	
        loginRival = cadea.substring(0,cadea.indexOf("]"));
	novaPartidaLoginRival = loginRival;
   	cadea = cadea.substring(cadea.indexOf("]")+1);
	tempo = Integer.valueOf(cadea.substring(0,cadea.indexOf("]"))).intValue();
	cadea = cadea.substring(cadea.indexOf("]")+1);
	novaPartidaIncrementos=Integer.valueOf(cadea.substring(0,cadea.indexOf("]"))).intValue();
	cadea = cadea.substring(cadea.indexOf("]")+1);
	color = Integer.valueOf(cadea.substring(0,cadea.indexOf("]"))).intValue();
        cadea = cadea.substring(cadea.indexOf("]")+1);
        RivalELO = Integer.valueOf(cadea.substring(0,cadea.indexOf("]"))).intValue();


	switch (tempo)
	{
	  case 1: stempo = "1 min";
		    novaPartidaTempo = "1";
		    break;
	  case 2: stempo = "2 min";
		    novaPartidaTempo = "2";
		    break;
	  case 3: stempo = "3 min";
		    novaPartidaTempo = "3";
		    break;
	  case 4: stempo = "5 min";
		    novaPartidaTempo = "5";
		    break;
	  case 5: stempo = "10 min";
		    novaPartidaTempo = "10";
		    break;
	  case 6: stempo = "20 min";
		    novaPartidaTempo = "20";
		    break;
	  case 7: stempo = "40 min";
		    novaPartidaTempo = "40";
		    break;
	  case 8: stempo = "60 min";
		    novaPartidaTempo = "60";
		    break;
	  case 9: stempo = "120 min";
		    novaPartidaTempo = "120";
		    break;		    		    		    
	}
	
	if(novaPartidaIncrementos==1)
	{
	  novaPartidaIncrementos=(int)Math.round(Integer.valueOf(novaPartidaTempo).intValue()*0.6);
	  sincrementos = idioma.traducir("con incremento")+" "+novaPartidaIncrementos+" "+idioma.traducir("seg");
	}  
	else
	{
	  sincrementos = idioma.traducir("sin incremento");
	  novaPartidaIncrementos=0; 
	}
	
	if (color == 2)
	{
	  scolor = idioma.traducir("blancas");
	  novaPartidaLoginBlancas = login;
	  novaPartidaLoginNegras = loginRival;
	}
	else
	{
	  scolor = idioma.traducir("negras");
	  novaPartidaLoginBlancas = loginRival;
	  novaPartidaLoginNegras = login;
	}
	

       mostrar_mensaje(loginRival+" "+idioma.traducir("te reta a una partida de")+" "+stempo+" "+sincrementos+" "+idioma.traducir("jugando tu con")+" "+scolor+avisoRetar(RivalELO),true,false,2);
       
      }catch(Exception e) {} 
    }

    public void aceptarReto()
    {
    	try
    	{
    	  tabla.cambiarTempo(login,"NO");
    	  tabla.enviarTempoIncCol();
        }
        catch (Exception e){}
		crearPartida(novaPartidaLoginBlancas,novaPartidaLoginNegras,novaPartidaTempo,novaPartidaIncrementos);
    }

    public void rechazarReto()
    {
	enviar_mensaxe(novaPartidaLoginRival,5,login);
    }

    public void rechazouReto(String mensaxe)
    {
	mostrar_mensaje(mensaxe+" "+idioma.traducir("no acepta el reto"));
    }

    public void crearPartida(String blancas,String negras,String tempo,int inc) 
    {	

      enviarBD("../php/altaPartida.php?sala="+sala+"&blancas="+blancas+"&negras="+
			         negras+"&login="+login+
			         "&tempo="+tempo+"&incrementos="+inc+"&a="+((int)(Math.random()*100000)),
	       String.valueOf(hashCode()),"despoisDeCrearPartida");
                          
    }

    public void despoisDeCrearPartida(String entrada)
    {
        String blancas,negras,tempo,inc;

        blancas=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        negras=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        tempo=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        inc=entrada.substring(0,entrada.indexOf("#"));        
        if(login.equals(blancas))
          enviar_mensaxe(negras,6,blancas+"]"+negras+"]"+tempo+"]"+inc+"]");
        else
          enviar_mensaxe(blancas,6,blancas+"]"+negras+"]"+tempo+"]"+inc+"]");
        mostrarPartida(blancas,negras,tempo,inc);

    }

    public void abrirPartida(String mensaxe)
    {
      String blancas,negras,tempo,inc;

      try
      {
		blancas=mensaxe.substring(0,mensaxe.indexOf("]"));
		mensaxe = mensaxe.substring(mensaxe.indexOf("]")+1);
		negras=mensaxe.substring(0,mensaxe.indexOf("]"));
		mensaxe = mensaxe.substring(mensaxe.indexOf("]")+1);
		tempo=mensaxe.substring(0,mensaxe.indexOf("]"));
		mensaxe = mensaxe.substring(mensaxe.indexOf("]")+1);
		inc=mensaxe.substring(0,mensaxe.indexOf("]"));	
		mostrarPartida(blancas,negras,tempo,inc);
      }catch(Exception e) {}
    }

    public void mostrarPartida(String blancas,String negras,String tempo,String inc)
    {
      
      //comprobar primeiro que existe
      
      enviarBD("../php/existePartida.php?sala="+sala+"&blancas="+blancas+"&negras="+
               negras+"&tempo="+tempo+"&incrementos="+inc+"&a="+((int)(Math.random()*100000)),
	       String.valueOf(hashCode()),"despoisDeMostrarPartida");      
	       
    }  


    public void despoisDeMostrarPartida(String entrada)  
    {
		String l,lRival,tempoSegs;
		byte i;
		Ajedrez aj;

        String blancas,negras,tempo;
        int inc;
        String t;
        
        //chatPublico.engadirFrase("DEPURAR",entrada);

        if (entrada.substring(0,6).compareTo("MAXIMO")!=0)
        {
          chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir("No se puede crear partida"));
          return;
        }
        entrada=entrada.substring(6);  

        blancas=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        negras=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        tempo=entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        inc=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();   
        entrada = entrada.substring(entrada.indexOf("#")+1);
        t=entrada.substring(0,entrada.indexOf("#"));                    


	tempoSegs=String.valueOf(60*(Integer.valueOf(tempo)).intValue());
	
	if(login.compareTo(blancas)==0)
	{
	  l="b";
	  lRival = negras;
	}
	else 
	{
	  l="n";
	  lRival = blancas;
	}

        for (i=0;i<pestanhasVerticales.getTabCount();i++)
        {
          aj=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
          if(aj.loginRival.compareTo(lRival)==0) 
            quitarPestanha(i);
        }
        
        contenedor.setVisible(true);

        aj = new Ajedrez(this,"",blancas,negras,l,tempoSegs,tempoSegs,tempo,inc,t);
	aj.init();
	engadirPestanhaVertical(pestanhasVerticales,lRival,aj);         
    }

    public void quitarPestanha(Ajedrez aj)
    {
      int i;
      Ajedrez aj2;
      
      for (i=0;i<pestanhasVerticales.getTabCount();i++)
      {
        aj2=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
        if(aj==aj2)
        {
          quitarPestanha(i);
          break;
        }
      }
      if(pestanhasVerticales.getTabCount()==0)
      {
        contenedor.setVisible(false);
        chatPublico.setBounds(3,46,contenedorOeste.getBounds().width-3,contenedorOeste.getBounds().height/2-8-23);
        chatPublico.colocarComponentes();
        chatPublico.redimensionarReloxio();
        chatPublico.axustarParametros();
        chatPublico.axustarValoresBarra();
        panelXogadores.setBounds(3,chatPublico.getBounds().height+49,chatPublico.getBounds().width,getBounds().height-chatPublico.getBounds().height-73);
      }
      else
      {
        for (i=0;i<pestanhasVerticales.getTabCount();i++)
        {
          aj=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
          aj.chatPrivado.setVisible(false);
        }
        aj=(Ajedrez)pestanhasVerticales.getComponentAt(pestanhasVerticales.getSelectedIndex());
        aj.chatPrivado.setVisible(true);	      	
      }
    }

    public void quitarPestanha(int i)
    {
      Ajedrez aj;
      aj=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
      pestanhasVerticales.removeTabAt(i);
      
      //pestanhas.ajedrez[ajedrez.numeroPestanha-1] = null;
      //if(ajedrez.numeroPestanha == 7)
      //	pestanhas.tabbed.remove("Tutor");
      contenedorOeste.remove(aj.chatPrivado);
      aj.eliminarReferencias();
      ajedrezRecibeProposicionTablas=null;
      ajedrezConfirmaRendirse=null;

    }


    public void enviarTempoIncCol(String tempo, String inc, String col)
    {
	String mensaxe;

        if(tempo.equals("NO"))
          admitirRetos=false;
        else
          admitirRetos=true;
	enviar_mensaxe("",24,login+"]"+tempo+"]"+inc+"]"+col);
	enviarBD("../php/gardarTempoIncCol.php?sala="+sala+"&login="+login+"&tempoDesexado="+tempo+"&incrementoDesexado="+inc+"&colorDesexado="+col+"&a="+((int)(Math.random()*100000)),
	         String.valueOf(hashCode()),""); 
    }


/***********************************************************************/
    public void enviar_comentario(String novaMensaxe) 
    {
      enviar_mensaxe("",1,novaMensaxe);
    }


/***********************************************************************/
    public synchronized void enviar_mensaxe(String destinatario,int tipoMensaxe,String novaMensaxe)
    {
    	enviar_mensaxe(destinatario,tipoMensaxe,novaMensaxe,true);
    }
 
    public synchronized void enviar_mensaxe(String destinatario,int tipoMensaxe,String novaMensaxe,boolean esixeAutenticado) 
    {
      int r=0;	
    
      
      novaMensaxe = novaMensaxe.replace(' ','|');

      if (novaMensaxe.length()>0)
      {
      	r=comprobarPalabrasProhibidas(novaMensaxe.substring(novaMensaxe.indexOf('>')+1));
      	if(r==0)
          (new ThreadEnvioOnline(this,"C1#"+destinatario+"#"+tipoMensaxe+"#"+novaMensaxe,esixeAutenticado)).start();
        else if(r==1); //non facer nada, non e moi grave sigue na sala pero non envia
        else if(r==2)  //é moi grave expúlsase
        {
          envioOnlineSalon.stop();
          expulsar(); 
          return;
        }
        if (tipoMensaxe == 1)
        {
          chatPublico.engadirFraseComposta(novaMensaxe.replace('|',' '));
          //if(frmVerPartidas!=null)
          //  ajedrezVer.chatPublico.engadirFraseComposta(novaMensaxe.replace('|',' '));
        }
      }
    }


    public int comprobarPalabrasProhibidas(String cadea)
    {
      int resultado=0,r=0; //0 frase correcta 1 conten palabras prohibidas leves 2 conten palabras prohibidas graves
      
      //System.out.println(cadea);
      cadea=cadea.replace(']','|');
      StringTokenizer st = new StringTokenizer(cadea,"|");
      while (st.hasMoreTokens()) 
      {
         r=comprobarPalabraProhibida(st.nextToken());
         if(r==2)
           return 2;
         else if (r==1)
           resultado=1;
      }
      return resultado;
    }
    

    public int comprobarPalabraProhibida(String palabra)
    {
      String pp;
      int i;
      
      //System.out.println(palabra);
      for (i=0; i < palabrasProhibidasGraves.size() ; i++) 
      {
        pp = (String)palabrasProhibidasGraves.elementAt(i);
        if ((pp).compareToIgnoreCase(palabra)==0)
          return 2;
      }    
      for (i=0; i < palabrasProhibidasLeves.size() ; i++) 
      {
        pp = (String)palabrasProhibidasLeves.elementAt(i);
        if ((pp).compareToIgnoreCase(palabra)==0)
          return 1;
      }     
      return 0;   
    }

    

/***********************************************************************/

  public void recibe_mensaxe(int tipomensaxe,String mensaxe)
  {
    //chatPublico.engadirFrase("RECIBEMENSAXE",mensaxe);
    if (tipomensaxe==1) //CHAT
      if (mensaxe.length()>0)
        chatPublico.engadirFraseComposta(mensaxe.replace('|',' '));
    if (tipomensaxe==2) //novo usuario conectado
    {
      String l,e,bandera,tempoDesexado="A 10",colorDesexado="Bla",tic;
      boolean incrementoDesexado=true;
      
      l=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      e=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      bandera=mensaxe.substring(0,mensaxe.indexOf("]"));
      try
      {
      	tic=mensaxe.substring(mensaxe.indexOf("]")+1);
        tempoDesexado=tic.substring(0,tic.indexOf("]"));
        tempoDesexado=tempoDesexado.replace('|',' ');
        tic=tic.substring(tic.indexOf("]")+1);
        incrementoDesexado=tic.substring(0,tic.indexOf("]")).equals("1");
        colorDesexado=tic.substring(tic.indexOf("]")+1);
      }
      catch (Exception ex){}

      tabla.engadirRival(l,e,bandera,tempoDesexado,incrementoDesexado,colorDesexado);
      recibirMensajeAjedrez(l+"]9");
    }
    if (tipomensaxe==3) //usuario desconectouse
    {
      if (mensaxe.compareTo(login)!=0)
      {
        //chatPublico.engadirFrase("SISTEMA",mensaxe+" se ha DESCONECTADO");
        tabla.quitarRival(mensaxe);
        recibirMensajeAjedrez(mensaxe+"]8");
      }
    }
    if (tipomensaxe==4) //Solicitude de reto para partida
      recibirReto(mensaxe);
    if (tipomensaxe==5) //Contestación rechazando reto para partida
      rechazouReto(mensaxe);
    if (tipomensaxe==6) //Aceptou reto e creou partida
    {
      try
      {
    	tabla.cambiarTempo(login,"NO");
    	tabla.enviarTempoIncCol();
      }
      catch (Exception e){}
      abrirPartida(mensaxe);
    }  
    if (tipomensaxe==7) //Mensaxes da clase Ajedrez
      recibirMensajeAjedrez(mensaxe);
    if (tipomensaxe==8) //Mensaxe do ADMINISTRADOR
      mostrar_mensaje(idioma.traducir("ADMINISTRADOR")+"  "+mensaxe);
    if (tipomensaxe==9) //Expulsar xogador
    {
      envioOnlineSalon.stop();
      expulsar(); 
    }
    if (tipomensaxe==10) //lista de usuarios conectados
    {
      String l,e,bandera,tempo,inc,color;
      boolean incLoxico;
      
      l=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      e=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      bandera=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      tempo=mensaxe.substring(0,mensaxe.indexOf("]")); 
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      inc=mensaxe.substring(0,mensaxe.indexOf("]"));  
      color=mensaxe.substring(mensaxe.indexOf("]")+1);             

      if(inc.equals("1"))
        incLoxico=true;
      else
        incLoxico=false;  

      if (l.compareTo(login)!=0)
        tabla.engadirRival(l,e,bandera,tempo.replace('|',' '),incLoxico,color);
    }    
    if (tipomensaxe==11) //usuario invitado que lle toca
    {
      if(mensaxe.compareTo(idioma.traducir("INVITADO"))==0)
        expulsar();
      login=mensaxe;      

      chatPublico.login=login;
      enviarBD("../php/inicializarInvitado.php?sala="+sala+"&login="+login+"&a="+((int)(Math.random()*100000)),
	       String.valueOf(hashCode()),"");  
      
    }    
    if (tipomensaxe==12) //Mensaxes para Ver Partidas Online
      recibirMensajeVerPartidas(mensaxe);   
    if (tipomensaxe==16) //Retorno dunha petició á base de datos
      recibirBD(mensaxe);        
    if (tipomensaxe==17) //Xogador modificou ELO
      tabla.xogadorModificaELO(mensaxe);               
    if (tipomensaxe==20) //Pide autenticación e chega a ip
    {
      numero=Integer.valueOf(mensaxe.substring(0,mensaxe.indexOf("]"))).longValue();
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      ip=mensaxe;
      enviar_mensaxe("",20,String.valueOf((numero*59999)%31991),false);
      try
      {
        enviar_mensaxe("",24,login+"]"+getParameter("tempoIncCol"));
      }
      catch (Exception e) {}  
      //chatPublico.engadirFrase("DEPURAR ","NUMERO QUE PIDE "+numero);
    }
    if (tipomensaxe==21) //Confirmaselle autenticación
    {
      autenticado=true;
      salon.loginValido();
    }    
    if (tipomensaxe==23) //Mensaxe coa hora para sincronizar
      recibirHora(mensaxe);     
    if (tipomensaxe==24) //Recibese a preferencia dun xogador para os retos de tempo, incrementos e color
    {
      String l,t,i,c;
      
      l=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      t=mensaxe.substring(0,mensaxe.indexOf("]"));
      mensaxe=mensaxe.substring(mensaxe.indexOf("]")+1);
      i=mensaxe.substring(0,mensaxe.indexOf("]"));      
      c=mensaxe.substring(mensaxe.indexOf("]")+1);

      t=t.replace('|',' ');

      if (l.compareTo(login)!=0)
        tabla.cambiarTempoIncCol(l,t,i,c);
    }       
  }

   
    public void recibe_mensaxe_online(String mensaxe)
    {
      int tipomensaxe;

      //chatPublico.engadirFrase("RECIBEMENSAXE",mensaxe);

      try 
      {
        if (mensaxe.substring(0,2).compareTo(new String("C1"))==0)
        {
          mensaxe = mensaxe.substring(mensaxe.indexOf('#')+1);
          tipomensaxe = Integer.valueOf(mensaxe.substring(0,mensaxe.indexOf('#'))).intValue();
          mensaxe = mensaxe.substring(mensaxe.indexOf('#')+1);
          recibe_mensaxe(tipomensaxe,mensaxe);
        }
        else if(mensaxe.substring(0,7).compareTo(new String("SISTEMA"))==0)
          chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir(mensaxe.substring(mensaxe.indexOf('#')+1)));
        else if(mensaxe.substring(0,9).compareTo(new String("CONECTADO"))==0)
		{
          conectado = true;
	      if(validado)
		  {
	        enviar_mensaxe("",22,login+"]"+elo+"]"+numeroBandera+"]"+"VIPSI"); //avisar de que xa está validado, envío VIPSI porque sexa vip ou non xa estaba dentro		  
		  }	
		}
        else if(mensaxe.substring(0,12).compareTo(new String("DESCONECTADO"))==0)
        {
          if (conectado == true)
            chatPublico.engadirFrase(idioma.traducir("SISTEMA"),idioma.traducir("Se ha cortado la conexión."));
          {  
            conectado = false;
            autenticado = false;
          } 
        } 
      }catch(Exception e) {}
    }


    public synchronized void conectarOnline()
    {
        envioOnlineSalon.conectar();
    }


    public void recibirMensajeAjedrez(String mensaje)
    {
      String loginRemitente;
      byte i;
      Ajedrez aj;
      
      //chatPublico.engadirFrase("DEPURAR","recibirMensajeAjedrez():"+mensaje);

      try
      {        
        loginRemitente = mensaje.substring(0,mensaje.indexOf(']'));
        mensaje = mensaje.substring(mensaje.indexOf(']')+1);

        for (i=0;i<pestanhasVerticales.getTabCount();i++)
        {
          aj=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
          if(aj.loginRival.compareTo(loginRemitente)==0) 
            aj.mensajeDelRival(mensaje);
        }

      }catch(Exception e) {}
    }


    public void recibirMensajeVerPartidas(String mensaje)
    {
      String loginDestino, loginOrigen, tipoMensaxe;

      try
      {
      	tipoMensaxe = mensaje.substring(0,mensaje.indexOf(']'));
      	mensaje = mensaje.substring(mensaje.indexOf(']')+1);
        loginDestino = mensaje.substring(0,mensaje.indexOf(']'));
        mensaje = mensaje.substring(mensaje.indexOf(']')+1);
        loginOrigen = mensaje.substring(0,mensaje.indexOf(']'));
        mensaje = mensaje.substring(mensaje.indexOf(']')+2);
        if((ajedrezVer.login_b.compareTo(loginDestino) == 0 &&
            ajedrezVer.login_n.compareTo(loginOrigen) == 0) ||
            (ajedrezVer.login_n.compareTo(loginDestino) == 0 &&
            ajedrezVer.login_b.compareTo(loginOrigen) == 0))
          if(tipoMensaxe.equals("1")) //Movemento    
            ajedrezVer.mensajeVerPartidas(mensaje);
          else //Mensaxe de fin de partida
            ajedrezVer.recibirComentarioFinPartida(mensaje);
        
      }catch(Exception e) {}
    }
    
    
    public void recibirHora(String entrada)
    {
      String hora;
      String minuto;
      String segundo;
    
      hora=entrada.substring(0,entrada.indexOf("#"));
      entrada = entrada.substring(entrada.indexOf('#')+1);
      minuto=entrada.substring(0,entrada.indexOf("#"));
      segundo = entrada.substring(entrada.indexOf('#')+1);    

      chatPublico.reloxioAnaloxico.fijarHora(Integer.valueOf(hora).intValue(),
                                             Integer.valueOf(minuto).intValue(),
                                             Integer.valueOf(segundo).intValue());
      if(ajedrezVer!=null)
        ajedrezVer.chatPublico.reloxioAnaloxico.fijarHora(Integer.valueOf(hora).intValue(),
                                                          Integer.valueOf(minuto).intValue(),
                                                          Integer.valueOf(segundo).intValue());      
    }
    
    
    public void enviarMensajesAutodesconexion()
    {
      byte i;
      Ajedrez aj;

      try
      {        

        for (i=0;i<pestanhasVerticales.getTabCount();i++)
        {
          aj=(Ajedrez)(pestanhasVerticales.getComponentAt(i));
            if(aj.btnRendir.getText().compareTo(idioma.traducir("Cerrar"))!=0) //saber se non está terminada
              enviaOnlineOrdenado.enviaOnline("C1#"+aj.loginRival+"#"+"7"+"#"+aj.loginXogador+"]15");  //indicarlle o rival que gañou porque eu autodesconectome
        }

      }catch(Exception e) {}
    }



/******************************************************************************************/
/******************************* CONFIRMACIÓN RENDIRSE ************************************/
/******************************************************************************************/
    public void confirmacionRendirse(Ajedrez aj)
    {
      ajedrezConfirmaRendirse = aj;
      
      if (aj.mostrandoConfirmacionRendirse==false)
      {
        aj.mostrandoConfirmacionRendirse=true;
        mostrar_mensaje(idioma.traducir("Confirmas rendirte contra")+" "+aj.loginRival,true,false,4);
      }
      

    }



/******************************************************************************************/
/******************************* PROPOSICION TABLAS ***************************************/
/******************************************************************************************/
    public void proposicionTablas(Ajedrez aj)
    {
      ajedrezRecibeProposicionTablas = aj;
      mostrar_mensaje(aj.loginRival+" "+idioma.traducir("te propone tablas. ¿Aceptas?"),true,false,3);
    }


/******************************************************************************************/
/******************************* AVISO RETAR ***************************************/
/******************************************************************************************/

  public String avisoRetar(String RivalELO)
  {
    return avisoRetar(Integer.valueOf(RivalELO).intValue());
  }

  public String avisoRetar(int RivalELO)
  {
    String cadea;
    double p;

    cadea = "";

    p = obterProporcionPuntos(RivalELO);
    
    cadea = cadea + ", "+idioma.traducir("modificas puntuación ELO: ganando")+" ";
    cadea = cadea + String.valueOf((int)Math.round(p*25));
    cadea = cadea + ", "+idioma.traducir("perdiendo")+" ";
    cadea = cadea + String.valueOf((int)Math.round((p-1)*25));
    cadea = cadea + ", "+idioma.traducir("tablas")+" ";
    cadea = cadea + String.valueOf((int)Math.round((p-0.5)*25));
    cadea = cadea + " "+idioma.traducir("puntos");

    return cadea;
  }

    public double obterProporcionPuntos(int suELO)
    {
      String entrada;
 
      double p=0;
      int miELO,diferencia;

      miELO = Integer.valueOf(elo).intValue();

      if(suELO > miELO)
        diferencia = suELO - miELO;
      else
        diferencia = miELO - suELO;

      if(diferencia <= 3) p = 50;
      else if(diferencia <= 10) p = 51;
      else if(diferencia <= 17) p = 52;
      else if(diferencia <= 25) p = 53;
      else if(diferencia <= 32) p = 54;
      else if(diferencia <= 39) p = 55;
      else if(diferencia <= 46) p = 56;
      else if(diferencia <= 53) p = 57;
      else if(diferencia <= 61) p = 58;
      else if(diferencia <= 68) p = 59;
      else if(diferencia <= 76) p = 60;
      else if(diferencia <= 83) p = 61;
      else if(diferencia <= 91) p = 62;
      else if(diferencia <= 98) p = 63;
      else if(diferencia <= 106) p = 64;
      else if(diferencia <= 113) p = 65;
      else if(diferencia <= 121) p = 66;
      else if(diferencia <= 129) p = 67;
      else if(diferencia <= 137) p = 68;
      else if(diferencia <= 145) p = 69;
      else if(diferencia <= 153) p = 70;
      else if(diferencia <= 162) p = 71;
      else if(diferencia <= 170) p = 72;
      else if(diferencia <= 179) p = 73;
      else if(diferencia <= 188) p = 74;
      else if(diferencia <= 197) p = 75;
      else if(diferencia <= 206) p = 76;
      else if(diferencia <= 215) p = 77;
      else if(diferencia <= 225) p = 78;
      else if(diferencia <= 235) p = 79;
      else if(diferencia <= 245) p = 80;
      else if(diferencia <= 256) p = 81;
      else if(diferencia <= 267) p = 82;
      else if(diferencia <= 278) p = 83;
      else if(diferencia <= 290) p = 84;
      else if(diferencia <= 302) p = 85;
      else if(diferencia <= 315) p = 86;
      else if(diferencia <= 328) p = 87;
      else if(diferencia <= 344) p = 88;
      else if(diferencia <= 357) p = 89;
      else if(diferencia <= 374) p = 90;
      else if(diferencia <= 391) p = 91;
      else if(diferencia <= 411) p = 92;
      else if(diferencia <= 432) p = 93;
      else if(diferencia <= 456) p = 94;
      else if(diferencia <= 484) p = 95;
      else if(diferencia <= 517) p = 96;
      else if(diferencia <= 559) p = 96;
      else if(diferencia <= 619) p = 96;
      else if(diferencia <= 735) p = 96;
      else p = 96;

      if(miELO > suELO)
        return (100-p)/100;
      else
        return p/100;
    }


/*************************************************************************************/
/*************************COMPROBA PIRATILLAS*******************************************/
/*************************************************************************************/

    public void loginValido() 
    {
      enviarBD("../php/loginValido.php?sala="+sala+"&login="+login+"&password="+clave+"&ip="+ip+"&a="+((int)(Math.random()*100000)),
	       String.valueOf(hashCode()),"despoisDeLoginValido");
    }


    public void despoisDeLoginValido(String entrada)
    {
       String strVIP,tempoDesexado="A 10",colorDesexado="Bla",tic;
       boolean incrementoDesexado=true;
       
       
       if (validado) //control para que se xa foi validado non reciba estos mensaxes de novo, pode ser un hacker que intenta facerse pasar por el o que causa este mensaxe
         return;
       
       try
       {
       	
         if (entrada.compareTo("PIRATA")==0)
           expulsar();
         else
         {
           validado=true;
           if(entrada.length()>0) // se non é pirata, devolve o ELO e publicamolo de novo por se fan a chorrada de modificar o ELO como parámetro do applet
           {                      // Ahora tamén devolve a bandera
             elo = entrada.substring(0,entrada.indexOf("#"));
             entrada=entrada.substring(entrada.indexOf("#")+1);
             numeroBandera = entrada.substring(0,entrada.indexOf("#"));
             strVIP = entrada.substring(entrada.indexOf("#")+1);
             if (strVIP.equals("VIPSI"))
               VIP = true;
             else
               VIP = false;
               
             try
             {
               tic=getParameter("tempoIncCol");
               tempoDesexado=tic.substring(0,tic.indexOf("]"));
               tempoDesexado=tempoDesexado.replace('|',' ');
               tic=tic.substring(tic.indexOf("]")+1);
               incrementoDesexado=tic.substring(0,tic.indexOf("]")).equals("1");
               colorDesexado=tic.substring(tic.indexOf("]")+1);
             }
             catch (Exception ex){}               
               
             tabla.engadirRival(login,elo,numeroBandera,tempoDesexado,incrementoDesexado,colorDesexado);           
             enviar_mensaxe("",22,login+"]"+elo+"]"+numeroBandera+"]"+strVIP);        //Avisar de que está validado e enviar elo e bandera e vip
             //enviar_mensaxe("",17,login+"]"+elo); //Indicar a todos o novo ELO //Ahora innecesario porque xa avisa de que se conectou despois de comprobar o ELO na base de datos
             recibe_mensaxe(17,login+"]"+elo); //Indicarme a min mismo o ELO
           }  
         }
       }
       catch (Exception e)
       {
       }  

    }


    public void expulsar()
    {
      URL url;

      try 
      { 
      	enviarMensajesAutodesconexion();
        url= new URL ("http://www.ciberchess.com");
        (getAppletContext()).showDocument (url, "_self");
        System.exit(0);
      }
      catch(MalformedURLException e) 
      {
        System.out.println( idioma.traducir("Error en URL") ); 
      }catch(IOException e) 
      {
        System.out.println( idioma.traducir("Error URL") ); 
      }
    }


    private static Color createColor(String s)
    {
        if(s == null || s.length() == 0)
            return null;
        s = s.trim().toLowerCase();
        if(s.indexOf(",") >= 0)
        {
            StringTokenizer stringtokenizer = new StringTokenizer(s, ", ");
            try
            {
                return new Color(Integer.parseInt(stringtokenizer.nextToken()), Integer.parseInt(stringtokenizer.nextToken()), Integer.parseInt(stringtokenizer.nextToken()));
            }
            catch(Exception _ex)
            {
                System.out.println("Invalid RGB color: " + s);
            }
        } else
        if(s.startsWith("#"))
        {
            try
            {
                return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
            }
            catch(Exception _ex)
            {
                System.out.println("Invalid hex color: " + s);
            }
        } else
        {
            if(s.equals("black"))
                return Color.black;
            if(s.equals("blue"))
                return Color.blue;
            if(s.equals("cyan"))
                return Color.cyan;
            if(s.equals("darkgray"))
                return Color.darkGray;
            if(s.equals("gray"))
                return Color.gray;
            if(s.equals("green"))
                return Color.green;
            if(s.equals("lightgray"))
                return Color.lightGray;
            if(s.equals("magenta"))
                return Color.magenta;
            if(s.equals("orange"))
                return Color.orange;
            if(s.equals("pink"))
                return Color.pink;
            if(s.equals("red"))
                return Color.red;
            if(s.equals("white"))
                return Color.white;
            if(s.equals("yellow"))
                return Color.yellow;
            System.out.println("Invalid color name: " + s);
        }
        return null;
    }

    public Frame getFrame() 
    {

      Container parent;
      for(parent = this; parent != null && !(parent instanceof Frame) ; parent = parent.getParent());
      if(parent != null)
	return (Frame)parent;
      else
	return null;

    }  

/*************************************************************************************/
/*****ENVIAR MENSAXE O SERVIDOR DE COMUNICACIONS PARA QUE CHAME A BASE DE DATOS*******/
/*************************************************************************************/
    public void enviarBD(String cadeaPHP, String hashCodigo, String metodoRetorno)
    {
      enviar_mensaxe("",15,cadeaPHP+"]"+hashCodigo+"]"+metodoRetorno);
    }

/*************************************************************************************/
/*****ENVIAR MENSAXE O SERVIDOR DE COMUNICACIONS PARA QUE CHAME A BASE DE DATOS*******/
/*************************************************************************************/
    public void recibirBD(String str)
    {
      String entrada;
      String hashCodigo;
      String nombreFuncion;
      Object cliente = null;
      Method funcion;
      Object parametrosObxetos[] = new Object[1];   
      
      //System.out.println("CHEGA ESTO DA BD1: "+str);   
      
      entrada = str.substring(0,str.indexOf("]"));
      str = str.substring(str.indexOf("]")+1);
      hashCodigo = str.substring(0,str.indexOf("]"));
      nombreFuncion = str.substring(str.indexOf("]")+1);
      
      cliente = buscarReceptorBD(Integer.valueOf(hashCodigo).intValue());

      Class parametrosClases[] = new Class[1];
      parametrosClases[0]=(new String()).getClass();

      try
      {
      	//System.out.println("CHEGA ESTO DA BD2: "+entrada);
        funcion = (cliente.getClass()).getMethod(nombreFuncion,parametrosClases);
        parametrosObxetos[0]=entrada;
        funcion.invoke(cliente,parametrosObxetos);        
      }
      catch (Exception e)
      {System.out.println(e);}      
      
      
  
    }

    private Object buscarReceptorBD(int hashCodigo)
    {
      Object retorno;
      
      if (xestionColor!=null)
      {
        retorno=buscarReceptorBDRecursivo(xestionColor,hashCodigo);
        if(retorno != null)
          return retorno;
      }
      
      if (ajedrezVer!=null)
      {
        retorno=buscarReceptorBDRecursivo(ajedrezVer,hashCodigo);
        if(retorno != null)
          return retorno;
      }      

      retorno=buscarReceptorBDRecursivo(this,hashCodigo);
      if(retorno != null)
        return retorno;
     
      return null;
    }
    
    private Object buscarReceptorBDRecursivo(Component componente,int hashCodigo)
    {
      int i;    	
      Object retorno;
    	
      if(componente.hashCode()==hashCodigo)
        return componente;
      
      if(componente instanceof Container)
        for(i=0;i<((Container)componente).getComponentCount();i++)
        {
          retorno = buscarReceptorBDRecursivo(((Container)componente).getComponent(i),hashCodigo);
          if( retorno != null)
            return retorno;
        }
        
      return null;
    }


}


/*************************************************************************************/
/******************************TEMPORIZADOR*******************************************/
/*************************************************************************************/
class TemporizadorSalon implements Runnable 
{
    private Thread thread = null;
    private int intervalo;
    private boolean inicio = true;
    Salon salon = null;
    
    TemporizadorSalon( int tiempo, Salon aj ) 
    { 
        intervalo = tiempo;
        salon = aj;
    }    

    // Aqui creamos un nuevo thread para correr el TemporizadorSalon. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
        thread=null;
        Thread.currentThread().interrupt();
    }
    
    public void run() 
    {
        int contador=0;
        long tempoMensaxeContribucion;
        
        tempoMensaxeContribucion = salon.tempoRelativo.tempo() - 400000;
 
        while( Thread.currentThread() == thread )  
        {

            try 
            {
             if (inicio)
             {
               inicio = false;
               esperar(2000);
             }
             else
             {
               esperar( intervalo );
               //salon.mostrarSala(); //por se en algunha mvj falla a presentación e se queda en negro
             }
            } catch( Exception e ) {
            }
            
            try
            { //se se desconectou intenta volver a conectar
              if (!salon.conectado && (contador%3 == 0 || contador<5)) 
                salon.conectarOnline();
              if (salon.conectado && !salon.autenticado && salon.numero!=0) //para prevenir que se quede incomunicado pola autenticación
              {
              	salon.enviar_mensaxe("",20,String.valueOf((salon.numero*59999)%31991),false);
              }  
            } catch(Exception e) {};

            contador++;
            
            if(salon.existeContribucion && salon.tempoRelativo.tempo()-tempoMensaxeContribucion > 600000)
            {
              salon.chatPublico.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.fraseChatPublicoContribucion);
              tempoMensaxeContribucion = salon.tempoRelativo.tempo();
            }
            

        }    
    }


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}


/*************************************************************************************/
/******************************TEMPORIZADOR  RETO ************************************/
/*************************************************************************************/
class TemporizadorReto implements Runnable {
    private Thread thread = null;
    Salon salon = null;
    boolean parado = false;
    
    TemporizadorReto( Salon aj ) 
    { 
      salon = aj;
    }    

    // Aqui creamos un nuevo thread para correr el TemporizadorReto. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() {
        thread = new Thread( this );
        thread.start();
        }
 
    public void stop() {
      thread=null;
      parado = true;
        }
    
    public void run() {
        int contador=9;
 
        while( true )  
        {
            salon.mensaje_boton2.setText(salon.idioma.traducir("Cancelar")+" "+String.valueOf(contador));

            try {

                 esperar( 1000 );
            } catch( InterruptedException e ) {
                return;
            }

            contador--;
            
            if (parado)
              break;

            if (contador == 0)
            {
              salon.mensaje_panel2.setBounds(0,0,0,0);
              salon.mensaje_panel.setBounds(0,0,0,0);
              salon.rechazarReto();
              salon.accionDespuesMensaje = 0;
              salon.validate();
              salon.mostrandoMensaje = false;
              break;
            }
        }   
             
    }    


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}


/*************************************************************************************/
/******************************TEMPORIZADOR  MOSTRAR MENSAJE ************************************/
/*************************************************************************************/
class TemporizadorMensaje implements Runnable {
    private Thread thread = null;
    Salon salon = null;
    String texto_mensaje;
    boolean mostrarSegundoBoton;
    boolean retar;
    int accion;
    
    TemporizadorMensaje( Salon aj, String texto_mensaje,boolean mostrarSegundoBoton,boolean retar,int accion ) 
    {
      salon = aj;
      this.texto_mensaje=texto_mensaje;
      this.mostrarSegundoBoton=mostrarSegundoBoton;
      this.retar=retar;
      this.accion=accion;
    }    

    // Aqui creamos un nuevo thread para correr el TemporizadorMensaje. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
      thread = new Thread( this );
      thread.start();
    }
 
    public void stop() 
    {
    	thread=null;
    	Thread.currentThread().interrupt();
    }
    
    public void run() 
    {
        while( Thread.currentThread() == thread )  
        {
          if (!salon.mostrandoMensaje)
          {
            salon.mostrar_mensaje_ya(texto_mensaje,mostrarSegundoBoton,retar,accion );
            break;
          }


          try {
              esperar(500);       
          } catch (InterruptedException e) 
          {
             break;
          }
          catch (Exception e) 
          {
            break;
          }



        }   
             
    }    


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}


/*************************************************************************************/
/******************************INICIAR SALON ************************************/
/*************************************************************************************/
class IniciarSalon implements Runnable {
    private Thread thread = null;
    Salon salon = null;
    
    IniciarSalon( Salon aj) { 
        salon = aj;
        }    

    // Aqui creamos un nuevo thread . Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() {
        thread = new Thread( this );
        //thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        }
 
    public void stop() {
      thread=null;
        }
    
    public void run() {
      salon.iniciar();
             
    }    



}







/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/
/***************************C O M U N I C A C I O N S*********************************/
/***********************************S A L O N*****************************************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/

class ComunicaOnlineSalon implements Runnable 
{
    Socket socket;
    DataOutputStream os;
    BufferedWriter bos;
    DataInputStream is;
    BufferedReader bis;
    boolean trysted;
    Thread receiveThread;
    Salon salon;
    EscoitaSalon receptor1,receptor2;
//    boolean depurar = false ;

    ComunicaOnlineSalon(Salon salon){
      this.salon = salon;
    }


    public synchronized void start() {
        if (receiveThread == null) 
        {
            trysted = false;
            os = null;
            is = null;
            socket = null;
            receiveThread = new Thread(this);
            receiveThread.start();   
            try 
            {
              wait(500);
            } catch( InterruptedException e ) {;}
        }
    }


    public synchronized void stop() {
        receiveThread = null;
        trysted = false;
       
        salon.recibe_mensaxe_online("DESCONECTADO");
        notify();

        try { //Close input stream.
            if (bis != null) {
                bis.close();
                bis = null;
            }
        } catch (Exception e) {} //Ignore exceptions.

        try { //Close input stream.
            if (is != null) {
                is.close();
                is = null;
            }
        } catch (Exception e) {} //Ignore exceptions.

        try { //Close output stream.
            if (bos != null) {
                bos.close();
                bos = null;
            }
        } catch (Exception e) {} //Ignore exceptions.

        try { //Close output stream.
            if (os != null) {
                os.close();
                os = null;
            }
        } catch (Exception e) {} //Ignore exceptions.

        try { //Close socket.
            if (socket != null) 
            {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {} //Ignore exceptions.
        
        try { //Parar receptores
            if (receptor1 != null) 
            {
                receptor1.stop();
                receptor1 = null;                
            }
            if (receptor2 != null) 
            {
                receptor2.stop();
                receptor2 = null;                
            }            
        } catch (Exception e) {} //Ignore exceptions.        
    }
            


    public synchronized void conectar() {

        boolean conecta = false;
        if (receiveThread == null) 
        {
            start();
        } 

        if (!trysted) 
        {
          conecta = rendezvous();
          if (conecta) 
          {
                  salon.recibe_mensaxe_online("CONECTADO");
                  enviar("GET /MAXIMOS1"+salon.login+"MAXIMOS2"+salon.elo+"MAXIMOS3"+salon.sala+"MAXIMOS4"+" "+"MAXIMOS5");
          }
          return;
        }
    }

    public synchronized boolean enviar(String str) 
    {
    	return enviar(str,false);
    }

    public synchronized boolean enviar(String str,boolean exigeAutenticacion) 
    {
    	if(exigeAutenticacion)
    	  if(!salon.autenticado)
    	    return false;
    	    
        if (trysted) 
        {
            try 
            {
                bos.write(str,0,str.length());
                bos.newLine();
                bos.flush();
            } 
            catch (Exception e) 
            {
                stop();
                return false;
            }
            return true; 
        }
        else
          return false;
    }


    public void run() {
        String received = null;

        waitForTryst();


        //Despues de esperar por la conexión con el servidor ya se puede recibir
        if (Thread.currentThread() == receiveThread) 
        {
	  receptor1 = new EscoitaSalon(salon,this);
          receptor2 = new EscoitaSalon(salon,this);
	  receptor1.start();
	}
    }


    synchronized void waitForTryst() {
        //esperando notificacion de la conexión para poder enviar y recibir
        try {
            wait();        
        } catch (InterruptedException e) {}

	}	


    private boolean rendezvous() {
        //intentando abrir un socket al servidor en el puerto
        try 
        {
              socket = new Socket(salon.getCodeBase().getHost(), (Integer.valueOf(salon.portoSalon)).intValue());
        } catch (Exception e) 
        {
          //System.out.println(e.getMessage());
//            if (depurar) salon.recibe_mensaxe_online("SISTEMA#DEBUG,No se puede abrir el puerto 9001.");
            stop();
            return false;
        }

        //Try to open streams to read and write from the socket.
        try {
            os = new DataOutputStream(socket.getOutputStream());
            bos=new BufferedWriter(new OutputStreamWriter(os));
            is = new DataInputStream(socket.getInputStream());
            bis = new BufferedReader(new InputStreamReader(is));
        } catch (IOException e) {
//            if (depurar) salon.recibe_mensaxe_online("SISTEMA#Socket correcto pero output stream erroneo.");
            stop();
            return false;
        }   

        if ((os != null) && (is != null)) {
            //Conexión establecida
            trysted = true;
            notify();
		return true;
        } else {
//            if (depurar) salon.recibe_mensaxe_online("SISTEMA#Puerto válido pero falla la comunicación.");
                stop();
		return false;
        }
    }

}



class EscoitaSalon implements Runnable 
{
  Salon	salon;
  String received;
  BufferedReader bis;
  ComunicaOnlineSalon envioOnlineSalon; 
//      boolean depurar = false;
  private Thread thread = null;


  EscoitaSalon(Salon salon, ComunicaOnlineSalon envioOnlineSalon)
  {
    this.salon = salon;
    this.bis=envioOnlineSalon.bis;
    this.envioOnlineSalon = envioOnlineSalon;
  }   


  // Aqui creamos un nuevo thread . Lo incializamos
  // con "this" de forma que el metodo run() se llame inmediatamente
  // como comience la ejecucion del thread
  public void start() 
  {
    thread = new Thread( this );
    thread.start();
  }

 
  public void stop() 
  {
  	thread=null;
  	Thread.currentThread().interrupt();
  }


  public void run() 
  {
    try 
    { 
      //Esperando para leer lo que llegue del servidor
                
      received = bis.readLine();

    }
    catch (Exception e) 
    { 
      envioOnlineSalon.stop();
      return; 
    }


    if (received != null ) 
    {
      //salon.chatPublico.engadirFrase("DEPURAR",received);
      //CREA UN RECEPTOR PARA A SEGUINTE FRASE
			
      envioOnlineSalon.receptor1 = envioOnlineSalon.receptor2;
      envioOnlineSalon.receptor1.start();
      envioOnlineSalon.receptor2 = new EscoitaSalon(salon,envioOnlineSalon);

      if (received.substring(0,2).compareTo(new String("C1"))==0)
      { 
      	String mensaxe;
      	
      	while(received.substring(1).indexOf("C1#")>0)
      	{
          mensaxe = received.substring(0,received.substring(1).indexOf("C1#")+1);
      	  received = received.substring(received.substring(1).indexOf("C1#")+1);
      	  salon.recibe_mensaxe_online(mensaxe);
        }     	
	salon.recibe_mensaxe_online(received);
	//Actualización recibida	
      }
      else if (received.substring(0,11).compareTo(new String("S1Problemas"))==0) 
      {
        
      }
      else if(received.substring(0,8).compareTo(new String("Conexion"))==0) 
      {
        //salon.recibe_mensaxe_online("CONECTADO");
        salon.recibe_mensaxe_online("SISTEMA#Conexión establecida.");
      }

    }
    else
    {
      envioOnlineSalon.stop();
      return;
    }      
  }
}



/*************************************************************************************/
/*****************************THREAD ENVIO ONLINE ************************************/
/*************************************************************************************/
class ThreadEnvioOnline implements Runnable 
{
    private Thread thread = null;
    Salon salon = null;
    String mensaxe;
    boolean esixeAutenticado;
    
    ThreadEnvioOnline( Salon aj, String mensaxe, boolean esixeAutenticado) 
    { 
       salon = aj;
       this.mensaxe = mensaxe;
       this.esixeAutenticado = esixeAutenticado;
    }    

    public void start() 
    {
        thread = new Thread( this );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
 
    public void stop() 
    {
      thread=null;
    }
    
    public void run() 
    {
      if (esixeAutenticado)
        salon.enviaOnlineOrdenado.enviaOnline(mensaxe);
      else
        salon.envioOnlineSalon.enviar(mensaxe,false);
    }
}



/*************************************************************************************/
/******************************TempoRelativo******************************************/
/*************************************************************************************/
class EnviaOnlineOrdenado
{
    Salon salon = null;
        
    boolean enviarOcupado=false;
    
    EnviaOnlineOrdenado(Salon salon) 
    { 
      this.salon = salon;
    }    

    public synchronized void enviaOnline(String mensaxe) //coloco esta función nunha clase porque estorba en Salon co wait e notify
    {
    	
        while(enviarOcupado)
    	{
    	  try 
    	  {
              wait();
          } catch( Exception e ) 
          {
              return;
          }
        }        

        enviarOcupado=true;

    	while(!salon.envioOnlineSalon.enviar(mensaxe,true))
    	{
    	  try 
    	  {
              esperar( 300 );
          } catch( InterruptedException e ) 
          {
          }
        }

    	try 
    	{
            esperar( 100 );
        } catch( InterruptedException e ) 
        {}
        
        enviarOcupado=false;
        notify();
    }
    
    
    public synchronized void esperar( int lapso ) 
      throws InterruptedException 
    {
      this.wait( lapso );
    }
    
}



/*************************************************************************************/
/****************************CLASE PRESENTACION   ************************************/
/*************************************************************************************/
class Presentacion implements Runnable 
{
    private Thread thread = null;
    Graphics g;
    Salon salon;
    boolean activa=false; 
    Object fract=null;
    int ancho,alto;
    
    Presentacion(Salon salon) 
    { 
       this.salon = salon;
    }    

    public void start() 
    {
        thread = new Thread( this );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
 
    public void stop() 
    {
      thread=null;
    }
    
    public void run() 
    {
      double alea=Math.random();
      
      ancho = (int)salon.getSize().getWidth();
      alto = (int)salon.getSize().getHeight()-20;      
      
      activa=true;
      g=salon.getGraphics();
      
      while(g==null)
      {
      	try
      	{
      	  esperar(100);
      	} catch (Exception e){System.out.println(e);}
      	g=salon.getGraphics();
      }
      
      if (salon.idiomaUsado.equals("english"))
        alea=alea*13+1;  //en ingles nunca mostro a primeira presentación
      else
        alea=alea*14;

      //for(int i=0;i<14;i++)
      //{
      //alea=i;
      
      if(alea<1)
        presentacion1();   
      else if(alea<2)
        presentacion2();
      else if(alea<3)
        presentacion3();
      else if(alea<4)
        presentacion4();  
      else if(alea<5)
        presentacion5();     
      else if(alea<6)
        presentacion6();  
      else if(alea<7)
        presentacion7(); 
      else if(alea<8)
        presentacion8();   
      else if(alea<9)
        presentacion9();    
      else if(alea<10)
        presentacion10(); 
      else if(alea<11)
        presentacion11();   
      else if(alea<12)
        presentacion12();          
      else if(alea<13)
        presentacion13();      
      else
        presentacion14();                           
        
      //try
      //{  
      //eliminarFractal();                                     
      //}catch(Exception e){}
      //}
        
      try
      {
          esperar(300);
          activa=false;
          g=null;
          salon.despertarSalon();
      } catch (Exception e){System.out.println(e);}

    }

    public void presentacion1()
    {
      int c;
      int coorX;
      Font fonteGrande,fontePequena;
      Image imagenOculta;
      Graphics offGraphics;      
      
      coorX = ancho/2-234;
      
      imagenOculta = salon.createImage(ancho, alto);
      offGraphics = imagenOculta.getGraphics();      
     
      fonteGrande = new Font("Arial",1,55);
      fontePequena = new Font("Arial",0,25);
      for(c=0;c<255;c+=15)
      {
      	offGraphics.setColor(new Color(c,c,c));
      	offGraphics.setFont(fontePequena);
      	offGraphics.drawString("Una idea de",coorX-100,200-60);
      	offGraphics.setFont(fonteGrande);
        offGraphics.drawString("Máximo Fernández López",coorX-100,200);
        g.drawImage(imagenOculta,0,20,null);
        try
        {
          esperar(50);
        }
        catch(Exception e){System.out.println(e);}
      }
      for(c=255;c>100;c-=15)
      {
      	offGraphics.setColor(new Color(c,c,c));
      	offGraphics.setFont(fontePequena);
      	offGraphics.drawString("Una idea de",coorX-100,200-60);
      	offGraphics.setFont(fonteGrande);      	
        offGraphics.drawString("Máximo Fernández López",coorX-100,200);
        g.drawImage(imagenOculta,0,20,null);
        try
        {
          esperar(50);
        }
        catch(Exception e){System.out.println(e);};
      }   
         
      
      for(c=0;c<255;c+=15)
      {
      	offGraphics.setColor(new Color(c,c,c));
      	offGraphics.setFont(fontePequena);
      	offGraphics.drawString("Posible gracias a",coorX-100,alto/2+100-60);
      	offGraphics.setFont(fonteGrande);
        offGraphics.drawString("Manolo Fernández Prado",coorX-100,alto/2+100);
        g.drawImage(imagenOculta,0,20,null);
        try
        {
          esperar(50);
        }
        catch(Exception e){System.out.println(e);};
      }
      for(c=255;c>100;c-=15)
      {
      	offGraphics.setColor(new Color(c,c,c));
      	offGraphics.setFont(fontePequena);
      	offGraphics.drawString("Posible gracias a",coorX-100,alto/2+100-60);
      	offGraphics.setFont(fonteGrande);
        offGraphics.drawString("Manolo Fernández Prado",coorX-100,alto/2+100);
        g.drawImage(imagenOculta,0,20,null);
        try
        {
          esperar(50);
        }
        catch(Exception e){System.out.println(e);};
      }      
      
      if (salon.bannerup!=null)
      {
        offGraphics.drawImage(salon.bannerup,coorX,alto-60,null);
        g.drawImage(imagenOculta,0,20,null);
      }      
      try
      {
        esperar(1000);
      }
      catch(Exception e){System.out.println(e);};      
      
    }
    
    public void presentacion2()
    {
      int i,j,x,y;
      long t=System.currentTimeMillis(),contador=0;
      Image imagenOculta;
      Graphics offGraphics;      
      
      imagenOculta = salon.createImage(ancho, alto);
      offGraphics = imagenOculta.getGraphics(); 

      do
      {
      	    i=(int)(Math.random()*ancho);
      	    j=(int)(Math.random()*alto);
            x=i*8/ancho;
            y=j*8/alto;
            if(x+y==(int)((x+y)/2)*2) //par
              if(Math.random()<0.3)
                offGraphics.setColor(Color.black);
              else
                offGraphics.setColor(Color.white);
            else
              if(Math.random()<0.3)
                offGraphics.setColor(Color.white);
              else
                offGraphics.setColor(Color.black);
            offGraphics.drawLine(i,j,i,j); 
            if(contador==1000)
            try
            {
               g.drawImage(imagenOculta,0,20,null);
               contador=0;
               esperar(10);
            }
            catch(Exception e){System.out.println(e);}       
            contador++;              
      } while(System.currentTimeMillis() < t+4000);
      g.drawImage(imagenOculta,0,20,null);   
    }    
    

    public void presentacion3()
    {
      int i;
      TextoCorrendo textos[];
      long tempoInicio=System.currentTimeMillis();
      Image imagenOculta;
      Graphics offGraphics;    
      int totalTextos;  
      
      totalTextos=30;    
      
      imagenOculta = salon.createImage(ancho, alto);
      offGraphics = imagenOculta.getGraphics();      
      
      textos=new TextoCorrendo[totalTextos];
      for(i=0;i<totalTextos;i++)
        textos[i]=new TextoCorrendo(this,offGraphics,"CiberChess.com",new Font("Verdana",0,(int)(Math.random()*30+20)),new Color(235,218,184), (int)(Math.random()*(ancho+100))-200, (int)(Math.random()*(alto+100))-50, (int)(Math.random()*(ancho+100))-200, (int)(Math.random()*(alto+100))-50, 6000);
      for(i=0;i<totalTextos;i++)
        textos[i].start();
      
      do
      {
      	try
        {
          esperar(40);
        }
        catch(Exception e){System.out.println(e);}
        g.drawImage(imagenOculta,0,20,null);

      } while(System.currentTimeMillis() < tempoInicio+5000);  
    }
    
    public void presentacion4()
    {
      int i;
      long tempoInicio=System.currentTimeMillis();
      Image imagenOculta;
      TextoCorrendo textos[];      
      Graphics offGraphics;
      int totalTextos;  
      
      totalTextos=14;    
      
      imagenOculta = salon.createImage(ancho, alto);
      offGraphics = imagenOculta.getGraphics();
      
      Font fuente=new Font("Verdana",1,90);
      Color color = new Color(235,218,184);
      FontMetrics medirFonte = salon.getFontMetrics(fuente);
      
      textos=new TextoCorrendo[totalTextos];
      
      textos[0]=new TextoCorrendo(this,offGraphics,"C",fuente,color, ancho/2-50, alto , (int)(ancho/2-400), (int)(alto/2-40), 4000);      
      textos[1]=new TextoCorrendo(this,offGraphics,"i",fuente,color, ancho-100, 100, (int)(ancho/2-400)+anchoTexto("C",medirFonte), (int)(alto/2-40), 4000);      
      textos[2]=new TextoCorrendo(this,offGraphics,"b",fuente,color, 0, alto, (int)(ancho/2-400)+anchoTexto("Ci",medirFonte), (int)(alto/2-40), 4000);  
      textos[3]=new TextoCorrendo(this,offGraphics,"e",fuente,color, ancho-100, alto, (int)(ancho/2-400)+anchoTexto("Cib",medirFonte), (int)(alto/2-40), 4000);          
      textos[4]=new TextoCorrendo(this,offGraphics,"r",fuente,color, ancho/2-50, 100, (int)(ancho/2-400)+anchoTexto("Cibe",medirFonte), (int)(alto/2-40), 4000);      
      textos[5]=new TextoCorrendo(this,offGraphics,"C",fuente,color, 0, alto/2-50, (int)(ancho/2-400)+anchoTexto("Ciber",medirFonte), (int)(alto/2-40), 4000);      
      textos[6]=new TextoCorrendo(this,offGraphics,"h",fuente,color, ancho-100, alto/2-50, (int)(ancho/2-400)+anchoTexto("CiberC",medirFonte), (int)(alto/2-40), 4000);  
      textos[7]=new TextoCorrendo(this,offGraphics,"e",fuente,color, 0, alto/40-50, (int)(ancho/2-400)+anchoTexto("CiberCh",medirFonte), (int)(alto/2-40), 4000);          
      textos[8]=new TextoCorrendo(this,offGraphics,"s",fuente,color, ancho/4-50, 100, (int)(ancho/2-400)+anchoTexto("CiberChe",medirFonte), (int)(alto/2-40), 4000);      
      textos[9]=new TextoCorrendo(this,offGraphics,"s",fuente,color, ancho/4*3-50, 100, (int)(ancho/2-400)+anchoTexto("CiberChes",medirFonte), (int)(alto/2-40), 4000);      
      textos[10]=new TextoCorrendo(this,offGraphics,".",fuente,color, ancho-100, alto/4*3-50, (int)(ancho/2-400)+anchoTexto("CiberChess",medirFonte), (int)(alto/2-40), 4000);  
      textos[11]=new TextoCorrendo(this,offGraphics,"c",fuente,color, ancho/4-50, alto, (int)(ancho/2-400)+anchoTexto("CiberChess.",medirFonte), (int)(alto/2-40), 4000);          
      textos[12]=new TextoCorrendo(this,offGraphics,"o",fuente,color, ancho/4*3-50, alto, (int)(ancho/2-400)+anchoTexto("CiberChess.c",medirFonte), (int)(alto/2-40), 4000);          
      textos[13]=new TextoCorrendo(this,offGraphics,"m",fuente,color, 0, 100 , (int)(ancho/2-400)+anchoTexto("CiberChess.co",medirFonte), (int)(alto/2-40), 4000);          
      
      for(i=0;i<totalTextos;i++)
        textos[i].start();
              
      do
      {
      	try
        {
          esperar(40); 
        }
        catch(Exception e){System.out.println(e);}
        g.drawImage(imagenOculta,0,20,null);

      } while(System.currentTimeMillis() < tempoInicio+6000);
    }      

    public void presentacion5()
    {
      int i;
      long tempoInicio=System.currentTimeMillis();
      Image imagenOculta;
      TextoCorrendo textos[];      
      Graphics offGraphics;
      int totalTextos;  
      
      totalTextos=14;   
      
      imagenOculta = salon.createImage(ancho, alto);
      offGraphics = imagenOculta.getGraphics();
      
      Font fuente=new Font("Verdana",1,90);
      Color color = new Color(235,218,184);
      FontMetrics medirFonte = salon.getFontMetrics(fuente);
      
      textos=new TextoCorrendo[totalTextos];
      
      textos[0]=new TextoCorrendo(this,offGraphics,"C",fuente,color,  (int)(ancho/2-400), (int)(alto/2-40),ancho/2-50, alto , 4000,2000);      
      textos[1]=new TextoCorrendo(this,offGraphics,"i",fuente,color, (int)(ancho/2-400)+anchoTexto("C",medirFonte), (int)(alto/2-40),ancho-100, 100,  4000,2000);      
      textos[2]=new TextoCorrendo(this,offGraphics,"b",fuente,color,(int)(ancho/2-400)+anchoTexto("Ci",medirFonte), (int)(alto/2-40), 0, alto,  4000,2000);  
      textos[3]=new TextoCorrendo(this,offGraphics,"e",fuente,color, (int)(ancho/2-400)+anchoTexto("Cib",medirFonte), (int)(alto/2-40),ancho-100, alto,  4000,2000);          
      textos[4]=new TextoCorrendo(this,offGraphics,"r",fuente,color, (int)(ancho/2-400)+anchoTexto("Cibe",medirFonte), (int)(alto/2-40),ancho/2-50, 100,  4000,2000);      
      textos[5]=new TextoCorrendo(this,offGraphics,"C",fuente,color, (int)(ancho/2-400)+anchoTexto("Ciber",medirFonte), (int)(alto/2-40),0, alto/2-50,  4000,2000);      
      textos[6]=new TextoCorrendo(this,offGraphics,"h",fuente,color,  (int)(ancho/2-400)+anchoTexto("CiberC",medirFonte), (int)(alto/2-40),ancho-100, alto/2-50, 4000,2000);  
      textos[7]=new TextoCorrendo(this,offGraphics,"e",fuente,color, (int)(ancho/2-400)+anchoTexto("CiberCh",medirFonte), (int)(alto/2-40),0, alto/40-50,4000,2000);          
      textos[8]=new TextoCorrendo(this,offGraphics,"s",fuente,color,(int)(ancho/2-400)+anchoTexto("CiberChe",medirFonte), (int)(alto/2-40), ancho/4-50, 100,  4000,2000);      
      textos[9]=new TextoCorrendo(this,offGraphics,"s",fuente,color, (int)(ancho/2-400)+anchoTexto("CiberChes",medirFonte), (int)(alto/2-40),ancho/4*3-50, 100,  4000,2000);      
      textos[10]=new TextoCorrendo(this,offGraphics,".",fuente,color,  (int)(ancho/2-400)+anchoTexto("CiberChess",medirFonte), (int)(alto/2-40),ancho-100, alto/4*3-50, 4000,2000);  
      textos[11]=new TextoCorrendo(this,offGraphics,"c",fuente,color,  (int)(ancho/2-400)+anchoTexto("CiberChess.",medirFonte), (int)(alto/2-40),ancho/4-50, alto, 4000,2000);          
      textos[12]=new TextoCorrendo(this,offGraphics,"o",fuente,color, (int)(ancho/2-400)+anchoTexto("CiberChess.c",medirFonte), (int)(alto/2-40),ancho/4*3-50, alto, 4000,2000);          
      textos[13]=new TextoCorrendo(this,offGraphics,"m",fuente,color,  (int)(ancho/2-400)+anchoTexto("CiberChess.co",medirFonte), (int)(alto/2-40),0, 100 , 4000,2000);          
      
      for(i=0;i<totalTextos;i++)
        textos[i].start();
              
      do
      {
      	try
        {
          esperar(40); 
        }
        catch(Exception e){System.out.println(e);}
        g.drawImage(imagenOculta,0,20,null);

      } while(System.currentTimeMillis() < tempoInicio+6000);
    }    
    
    
    public void presentacion6()
    {
      BinaryRing fractal = new BinaryRing(ancho,alto);
      fract = fractal;
      fractal.setBounds(0,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
      
    }
    
    
    public void presentacion7()
    {
      int dim;
      
      dim = alto;
      CityTraveler fractal = new CityTraveler(dim,dim);
      fract = fractal;
      fractal.setBounds((ancho-dim)/2,20,dim,dim);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }    
    

    public void presentacion8()
    {
      Bubble fractal = new Bubble(ancho,alto,2);
      fract = fractal;
      fractal.setBounds(0,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }
    
    
    public void presentacion9()
    {
      Bubble fractal = new Bubble(ancho,alto,1);
      fract = fractal;
      fractal.setBounds(0,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }        


    public void presentacion10()
    {
      fractal.Box fractal = new fractal.Box(ancho/2,alto/2);
      fract = fractal;
      fractal.setBounds(ancho/4,20+alto/4,ancho/2,alto/2);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }  
    

    public void presentacion11()
    {
      Orbs fractal = new Orbs(ancho,alto);
      fract = fractal;
      fractal.setBounds(0,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }     
    
    
    public void presentacion12()
    {
      Sub fractal = new Sub(ancho/2,alto/2);
      fract = fractal;
      fractal.setBounds(ancho/4,20+alto/4,ancho/2,alto/2);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(10000); 
      }
      catch(Exception e){System.out.println(e);}
    }       
    
    public void presentacion13()
    {
      St fractal = new St(ancho,alto);
      fract = fractal;
      fractal.setBounds(0,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(7000); 
      }
      catch(Exception e){System.out.println(e);}
    }     
    
    public void presentacion14()
    {
      Hap fractal = new Hap(alto,alto);
      fract = fractal;
      fractal.setBounds((ancho-alto)/2,20,ancho,alto);
      fractal.init();
      salon.getContentPane().add(fractal);
      fractal.start();
      
      try
      {
        esperar(8000); 
      }
      catch(Exception e){System.out.println(e);}
    }        
    
    
    public void eliminarFractal()
    {
      salon.getContentPane().remove((Component)fract);
      ((PApplet)fract).stop();
    }

    
    public synchronized void esperar( int lapso ) 
      throws InterruptedException 
    {
      this.wait( lapso );
    } 
        
    synchronized void pintaFotograma(Graphics g,int x,int y,int z,int t,Font fuente,String texto,Color color)
    {
      g.setColor(Color.black);
      g.setFont(fuente);
      g.drawString(texto,x,y);    	
      g.setColor(color);
      g.drawString(texto,z,t);    	    
    }
    
    int anchoTexto(String s,FontMetrics f)
    {
      int i,ancho=0;
      
      for(i=0;i<s.length();i++)
        ancho = ancho + f.charWidth(s.charAt(i));
        
      return ancho;
    }
    
}


class TextoCorrendo implements Runnable 
{
    private Thread thread = null;
    Graphics g;
    String texto;
    Font fuente;
    Color color;
    int orixeX, orixeY;
    int destinoX, destinoY;
    int tempo;
    long tempoInicio;
    Presentacion presentacion;
    int retardoInicial;
    
    TextoCorrendo(Presentacion presentacion, Graphics g, String texto, Font fuente, Color color, int orixeX, int orixeY, int destinoX, int destinoY, int tempo) 
    {
       this(presentacion, g, texto, fuente, color, orixeX, orixeY, destinoX, destinoY, tempo, 0);
    }
    
    TextoCorrendo(Presentacion presentacion, Graphics g, String texto, Font fuente, Color color, int orixeX, int orixeY, int destinoX, int destinoY, int tempo, int retardoInicial) 
    { 
      this.presentacion=presentacion;
      this.g=g;
      this.texto=texto;
      this.fuente=fuente;
      this.color=color;
      this.orixeX=orixeX;
      this.orixeY=orixeY;
      this.destinoX=destinoX;
      this.destinoY=destinoY;
      this.tempo=tempo;
      this.retardoInicial=retardoInicial;
    }    

    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
      thread=null;
    }
    
    public void run() 
    {
      double distanciaInicial;
      int x,y,xant,yant;  //posicion actual
      
      x=orixeX;
      y=orixeY;
      distanciaInicial=distancia(x,y,destinoX,destinoY);
      presentacion.pintaFotograma(g,x,y,x,y,fuente,texto,color);
      if (retardoInicial>0)
      {
          try
          {
            esperar(retardoInicial);
          }
          catch(Exception e){System.out.println(e);}      
      }
      tempoInicio=System.currentTimeMillis();
      do
      {
        if(distancia(x,y,destinoX,destinoY)/distanciaInicial > (tempoInicio+tempo-System.currentTimeMillis())/(double)tempo)
        {
          xant=x;
          yant=y;
          if(x!=destinoX && Math.random()>0.5)
            if(x>destinoX)
              x-=3;
            else
              x+=3;
          else
          {
            if(y>destinoY)
              y-=3;
            if(y<destinoY)
              y+=3;
          }
          presentacion.pintaFotograma(g,xant,yant,x,y,fuente,texto,color);
        }
        else
        {
          try
          {
            esperar(1);
          }
          catch(Exception e){System.out.println(e);}        	
        }
      }while(tempoInicio+tempo>System.currentTimeMillis());
    }

    
    double distancia(int x,int y,int z,int t)
    {
      int difX,difY;
      
      difX=Math.abs(x-z);
      difY=Math.abs(y-t);
      return Math.round(Math.sqrt(difX*difX+difY*difY));
    }
    
    public synchronized void esperar( int lapso ) 
      throws InterruptedException 
    {
      this.wait( lapso );
    }      
    
}



/*************************************************************************************/
/****************************CLASE TABOA XOGADORES************************************/
/*************************************************************************************/

class TaboaXogadores extends JTable implements MouseListener //CellEditorListener,
{
    DefaultTableModel datos;
    Salon salon;
    int sortCol = 1;
    int columnsCount=6;
    boolean isSortAsc = true;
    String retos;
    
    public TaboaXogadores(Salon sal) 
    {
    	
        super();        
        
        this.salon=sal;
        
        datos=new DefaultTableModel() 
                  {
                    public boolean isCellEditable(int rowIndex, int mColIndex) 
                    {
                      if(!((String)datos.getValueAt(rowIndex,1)).equals(getSalon().login))
                        return false;
                      if(mColIndex>2)
                        return true;
                      else
                        return false;  
                    }
                  };
        
        setModel(datos);
        
        setBorder(new EmptyBorder(0,0,0,0));
        
        //getTableHeader().setReorderingAllowed(false);
        //getTableHeader().setBorder(new EmptyBorder(0,0,0,0));
        
        addMouseListener(this);            
        
        //setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        //getTableHeader().setVisible(false);

        datos.addColumn("");
        datos.addColumn(salon.idioma.traducir("Jugador"));
        datos.addColumn("ELO");
        datos.addColumn("Retos");
        datos.addColumn("Inc");
        datos.addColumn("Col");
        
        getTableHeader().setUpdateTableInRealTime(true);
        getTableHeader().addMouseListener(new ColumnListener(this));
        getTableHeader().setReorderingAllowed(true);        
        
        getTableHeader().setBorder(new EmptyBorder(0,0,0,0));        
        
        setRowHeight(16);
        //setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        
        sizeColumnsToFit(1);        
              
        fijarAnchoColumnas();        

    }

    
    Salon getSalon()
    {
      return salon;
    }


    public Class getColumnClass(int column)
    {
      return getValueAt(0, column).getClass();
    }
 

    public void fijarAnchoColumnas()
    {
      try
      {
        getColumnModel().getColumn(0).setWidth(24);
        getColumnModel().getColumn(0).setPreferredWidth(24);
        getColumnModel().getColumn(0).setMaxWidth(24);
        getColumnModel().getColumn(0).setMinWidth(24);        
        getColumnModel().getColumn(1).setWidth(1000);
        getColumnModel().getColumn(1).setPreferredWidth(1000);
        getColumnModel().getColumn(2).setWidth(35);
        getColumnModel().getColumn(2).setPreferredWidth(35);         
        getColumnModel().getColumn(2).setMaxWidth(35);
        getColumnModel().getColumn(2).setMinWidth(35);  
        getColumnModel().getColumn(3).setWidth(50);
        getColumnModel().getColumn(3).setPreferredWidth(50);         
        getColumnModel().getColumn(3).setMaxWidth(50);
        getColumnModel().getColumn(3).setMinWidth(50); 
        getColumnModel().getColumn(4).setWidth(35);
        getColumnModel().getColumn(4).setPreferredWidth(35);         
        getColumnModel().getColumn(4).setMaxWidth(35);
        getColumnModel().getColumn(4).setMinWidth(35);        
        getColumnModel().getColumn(5).setWidth(45);
        getColumnModel().getColumn(5).setPreferredWidth(45);         
        getColumnModel().getColumn(5).setMaxWidth(45);
        getColumnModel().getColumn(5).setMinWidth(45);                     
      }catch(Exception e){} 
    }


    public String[] xogadorSeleccionado()
    {
      int fila;
      
      fila = getSelectedRow();
      if (fila == -1)
        return null;
      if(((Boolean)datos.getValueAt(fila,4)).booleanValue())  
        return new String[]{(String)datos.getValueAt(fila,1),(datos.getValueAt(fila,2)).toString(),(String)datos.getValueAt(fila,3),"1",(String)datos.getValueAt(fila,5)};
      else
        return new String[]{(String)datos.getValueAt(fila,1),(datos.getValueAt(fila,2)).toString(),(String)datos.getValueAt(fila,3),"0",(String)datos.getValueAt(fila,5)};  
    }   
    
    
    public void xogadorModificaELO(String mensaje)
    {
      String xogador,novoELO;
      int fila;
      
      try
      {        
        xogador = mensaje.substring(0,mensaje.indexOf(']'));
        mensaje = mensaje.substring(mensaje.indexOf(']')+1);
        novoELO = mensaje;

        for(fila=0;fila<datos.getRowCount();fila++)
          if(xogador.equals(datos.getValueAt(fila,1)))
          {
            datos.setValueAt(new Integer(novoELO),fila,2);
            break;
          } 
        
      }catch(Exception e) {}
    }    
    
    
    public void cambiarTempo(String loginXogador, String tempo)
    {
      int fila;
      
      try
      {        
        for(fila=0;fila<datos.getRowCount();fila++)
          if(loginXogador.equals(datos.getValueAt(fila,1)))
          {
            datos.setValueAt(tempo,fila,3);
            break;
          } 
        
      }catch(Exception e) {}    	
    	
    }    
    
    
    public void cambiarTempoIncCol(String loginXogador, String tempo, String incremento, String colorPezas)
    {
      int fila;
      
      //System.out.println("cambiarTempoIncCol: "+loginXogador+" "+tempo+" "+incremento+" "+colorPezas);
      try
      {        
        for(fila=0;fila<datos.getRowCount();fila++)
          if(loginXogador.equals(datos.getValueAt(fila,1)))
          {
            datos.setValueAt(tempo,fila,3);
            if(incremento.equals("0"))
              datos.setValueAt(new Boolean(false),fila,4);
            else
              datos.setValueAt(new Boolean(true),fila,4);
            datos.setValueAt(colorPezas,fila,5);
            break;
          } 
        
      }catch(Exception e) {}    	
    	
    }	    
    
    
    public void coloresRivales()
    {
    }


    public void engadirRival(String xogador,String ELO,String numeroBandera,String tempo,boolean incrementos,String colorpiezas)
    {
      int fila,numBandera=0;

      try
      {         
        for(fila=0;fila<datos.getRowCount();fila++) //comprobación por se xa está
          if(xogador.equals(datos.getValueAt(fila,1)))
            return;
      }
      catch (Exception e){}
                
      if(xogador.equals(salon.login))
      {
        retos=tempo;  //para quedarnos nunha variable global co último valor de retos i evitar que salte o evento duas veces
      }    
      
      try
      {
      	 numBandera = (Integer.valueOf(numeroBandera)).intValue();

        datos.addRow(new Object[]{salon.bandera[numBandera],xogador,new Integer(ELO),tempo,new Boolean(incrementos),colorpiezas});
        sortAllRowsBy(datos, sortCol, isSortAsc);
        fijarAnchoColumnas();    
      
        //String [] choices = {"10", "20", "40"};  // the possibilities
        //JComboBox comboBox = new JComboBox(choices);     // create the combo box (only once)
       
        // Inform the table of the fact that you want to use this combo box for editing the 4th column:
        //getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));         
        
        fixarRenderers();
      }
      catch (Exception e){}        
    }


    public void fixarRenderers()
    {
      int vColIndex;
      TableColumn col;    	
        
        vColIndex = 1;
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer() 
        {
	    public void setValue(Object value) {
	        if (value instanceof String) 
	        {
	            if(((String)value).equals(salon.login))
	            setFont(new Font("",1,12));
	            setText((String)value);
	        }
	    }

        };
  
        col = getColumnModel().getColumn(vColIndex);
        col.setCellRenderer(colorRenderer);     
        
        
        // These are the combobox values
        String[] values = new String[]{"SI","NO","A 1","A 2","A 3","A 5","A 10","A 20","A 40","A 60","A 120"};
    
        // Set the combobox editor on the 1st visible column
        vColIndex = 3;
        colorRenderer = new DefaultTableCellRenderer() 
        {
	    public void setValue(Object value) 
	    {
	        if (value instanceof String)
	        {
	            if(((String)value).equals("NO"))
	            {
	              setBackground(Color.red);
	            }  
	            else
	            {  
	              setBackground(Color.green);
	            }  
	        }
	        setText((String)value);
	    }

        }; 
        col = getColumnModel().getColumn(vColIndex);
        col.setCellRenderer(colorRenderer);
        MyComboBoxEditor cbe=new MyComboBoxEditor(values);
        col = getColumnModel().getColumn(vColIndex);
        col.setCellEditor(cbe);
        
        cbe.addCellEditorListener(
          new CellEditorListener() 
          {
    
            public void	editingCanceled(ChangeEvent e)
	    {
	    } 
	    
	    public void	editingStopped(ChangeEvent e)
	    {
	      try
	      {
	    	DefaultCellEditor dce=(DefaultCellEditor)e.getSource();
	    	if(!retos.equals((String)dce.getCellEditorValue()))
	     	{
	    	  retos=(String)dce.getCellEditorValue();
	    	  enviarTempoIncCol();
	    	}
	      }
	      catch(Exception ex){System.out.println(ex.toString());}	
	    } 
          });
 
    
          // If the cell should appear like a combobox in its
          // non-editing state, also set the combobox renderer
//          if(xogador.equals(salon.login))
//            col.setCellRenderer(new MyComboBoxRenderer(values));         
        
        
        // Set the checkbox editor on the 1st visible column
        vColIndex = 4;
        col = getColumnModel().getColumn(vColIndex);
        MyCheckBoxEditor chbe = new MyCheckBoxEditor();
        
        col.setCellEditor(chbe);
        chbe.addCellEditorListener(this);
        chbe.addCellEditorListener(
          new CellEditorListener() 
          {
    
	    public void	editingCanceled(ChangeEvent e)
	    {
	    } 
	    
	    public void	editingStopped(ChangeEvent e)
	    {
	      try
	      {
	    	//DefaultCellEditor dce=(DefaultCellEditor)e.getSource();
                enviarTempoIncCol();
	      }
	      catch(Exception ex){System.out.println(ex.toString());}	
	    } 
          });
    
        // If the cell should appear like a combobox in its
        // non-editing state, also set the combobox renderer
        //col.setCellRenderer(new MyCheckBoxRenderer());
        
        
        // These are the combobox values
        String[] valores = new String[]{"Bla","Neg"};
    
        // Set the combobox editor on the 1st visible column
        vColIndex = 5;
        colorRenderer = new DefaultTableCellRenderer() 
        {
	    public void setValue(Object value) 
	    {
	        if (value instanceof String)
	        {
	            if(((String)value).equals("Bla"))
	            {
	              setBackground(Color.white);
	              setForeground(Color.black);
	            }  
	            else
	            {  
	              setBackground(Color.black);
	              setForeground(Color.white);
	            }  
	        }
	        setText((String)value);
	    }

        };
        col = getColumnModel().getColumn(vColIndex);
        col.setCellRenderer(colorRenderer);         
        MyComboBoxEditor cbcol=new MyComboBoxEditor(valores);
        col = getColumnModel().getColumn(vColIndex);
        col.setCellEditor(cbcol);
        
        cbcol.addCellEditorListener(
          new CellEditorListener() 
          {
    
            public void	editingCanceled(ChangeEvent e)
	    {
	    } 
	    
	    public void	editingStopped(ChangeEvent e)
	    {
	      try
	      {
	    	enviarTempoIncCol();
	      }
	      catch(Exception ex){System.out.println(ex.toString());}	
	    } 
          });
 
    
          // If the cell should appear like a combobox in its
          // non-editing state, also set the combobox renderer
//          if(xogador.equals(salon.login))
//            col.setCellRenderer(new MyComboBoxRenderer(valores));        
	
    }

    
    public void quitarRival(String xogador)
    {
      int fila;
      for(fila=0;fila<datos.getRowCount();fila++)
        if(xogador.equals(datos.getValueAt(fila,1)))
        {
          datos.removeRow(fila);
          return;
        } 
    }
    
    
    public void enviarTempoIncCol()
    {
      String xogador;
      int fila;
      
      xogador=salon.login;
      for(fila=0;fila<datos.getRowCount();fila++)
        if(xogador.equals(datos.getValueAt(fila,1)))
        {
          if(((Boolean)datos.getValueAt(fila,4)).booleanValue())
            salon.enviarTempoIncCol((String)datos.getValueAt(fila,3),"1",(String)datos.getValueAt(fila,5));
          else
            salon.enviarTempoIncCol((String)datos.getValueAt(fila,3),"0",(String)datos.getValueAt(fila,5));
          return;
        }         
    }
    
    
    public void mouseClicked(MouseEvent e) 
    {
      if(xogadorSeleccionado()==null)
        return;
      if (e.getClickCount() == 1) 
        if (e.getX() > 23)
          salon.confirmarReto(xogadorSeleccionado());
        else
          salon.mostrarInformacionGeografica(xogadorSeleccionado()[0]);
    }     
    
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }  
           


    public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) 
    {
        Vector data = model.getDataVector();
        Collections.sort(data, new ColumnSorter(colIndex, ascending));
        model.fireTableStructureChanged();
    }


  class ColumnListener extends MouseAdapter 
  {
    protected TaboaXogadores table;

    public ColumnListener(TaboaXogadores t) 
    {
      table = t;
    }

    public void mouseClicked(MouseEvent e) 
    {
      TableColumnModel colModel = table.getColumnModel();
      int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
      int modelIndex = colModel.getColumn(columnModelIndex)
          .getModelIndex();

      //System.out.println("Evento mouseClicked");

      if (modelIndex < 0)
        return;
      if (sortCol == modelIndex)
        isSortAsc = !isSortAsc;
      else
        sortCol = modelIndex;

      for (int i = 0; i < columnsCount; i++) { 
        TableColumn column = colModel.getColumn(i);
        column.setHeaderValue(getColumnName(column.getModelIndex()));
      }
      table.getTableHeader().repaint();

      //System.out.println("Punto 1");

      ((TaboaXogadores)table).sortAllRowsBy(datos, sortCol, isSortAsc);
      table.fijarAnchoColumnas();
      table.fixarRenderers();
      //table.repaint();
    }
    

  }

    
    // This comparator is used to sort vectors of data
    public class ColumnSorter implements Comparator {
        int colIndex;
        boolean ascending;
        ColumnSorter(int colIndex, boolean ascending) {
            this.colIndex = colIndex;
            this.ascending = ascending;
        }
        public int compare(Object a, Object b) {
            Vector v1 = (Vector)a;
            Vector v2 = (Vector)b;
            Object o1 = v1.get(colIndex);
            Object o2 = v2.get(colIndex);
    
            // Treat empty strains like nulls
            if (o1 instanceof String && ((String)o1).length() == 0) {
                o1 = null;
            }
            if (o2 instanceof String && ((String)o2).length() == 0) {
                o2 = null;
            }
    
            // Sort nulls so they appear last, regardless
            // of sort order
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return 1;
            } else if (o2 == null) {
                return -1;
            } else if (o1 instanceof Comparable) {
                if (ascending) {
                    return ((Comparable)o1).compareTo(o2);
                } else {
                    return ((Comparable)o2).compareTo(o1);
                }
            } else {
                if (ascending) {
                    return o1.toString().compareTo(o2.toString());
                } else {
                    return o2.toString().compareTo(o1.toString());
                }
            }
        }
    }
    
    class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
        public MyComboBoxRenderer(String[] items) {
            super(items);
        }
    
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
           
    
            // Select the current value
            setSelectedItem(value);           
            return this;
        }
    }
    
    class MyComboBoxEditor extends DefaultCellEditor {
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }    
    
    class MyCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public MyCheckBoxRenderer() {
            super();
        }
    
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
    
            // Select the current value
            setSelected(isSelected);
            return this;
        }
    }
    
    class MyCheckBoxEditor extends DefaultCellEditor {
        public MyCheckBoxEditor() {
            super(new JCheckBox());
        }
    }    
    
    
}



/*************************************************************************************/
/******************************TempoRelativo******************************************/
/*************************************************************************************/
class TempoRelativo implements Runnable 
{
    private Thread thread = null;
    Salon salon = null;
    long t;
    
    TempoRelativo(Salon salon) 
    { 
      this.salon = salon;
    }    

    // Aqui creamos un nuevo thread para correr el TemporizadorSalon. Lo incializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
        thread = new Thread( this );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
 
    public void stop() 
    {
        thread=null;
        Thread.currentThread().interrupt();
    }
    
    public void run() 
    {
        t=1;
        
        while( Thread.currentThread() == thread )  
        {

            try 
            {
               esperar(500);
            } catch(Exception e ) 
            {
            }
            t+=500;
        }    
    }
    
    public long tempo()
    {
      return t;
    }


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}



/*************************************************************************************/
/************************Temporizador colores lista rivales **************************/
/*************************************************************************************/
class TemporizadorColoresRivales implements Runnable 
{
    private Thread thread = null;
    TaboaXogadores tx = null;
    
    TemporizadorColoresRivales(TaboaXogadores tx) 
    { 
      this.tx = tx;
    }

    public void start() 
    {
        thread = new Thread( this );
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
 
    public void stop() 
    {
        thread=null;
        Thread.currentThread().interrupt();
    }
    
    public void run() 
    {

        while( Thread.currentThread() == thread )  
        {

            try 
            {
               esperar(1000);
            } catch(Exception e ) 
            {
            }
            tx.coloresRivales();
        }    
    }


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}
