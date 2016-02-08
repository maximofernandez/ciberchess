import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.text.NumberFormat;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class Maquina extends Applet{
    Image pb,tb,cb,ab,db,rb,pn,tn,cn,an,dn,rn;/* imagenes de figuras y logotipo */
    String login; /* parametros de entrada */
    Button tablero_boton_tapiz = new Button("");
    Button xogar;
	
    XogadorVirtual jugadorVirtual;

    Checkbox nivel1 = new Checkbox("1");
    Checkbox nivel2 = new Checkbox("2");
    Checkbox nivel3 = new Checkbox("3");
    Checkbox nivel4 = new Checkbox("4");
    Checkbox nivel5 = new Checkbox("5");
    Checkbox nivel6 = new Checkbox("6");
    Checkbox nivel7 = new Checkbox("7");
    Checkbox nivel8 = new Checkbox("8");
    Checkbox nivel9 = new Checkbox("9");
    Checkbox nivel10 = new Checkbox("10");
    CheckboxGroup grupo;
    
    Label etqNivel,texto1,texto2;

    MediaTracker tracker;                            /* para manejar las imagenes */
    Image logo;/* imagenes de logotipo */
    int tamanhoPieza;
    
    String idiomaUsado="spanish";


    public Maquina()
    {

    }


    public void init() {

      int centrar=0;
      Image piezas;
      ImageFilter cropfilter;
      ImageProducer prod;

	setBackground(new Color(150,102,79));

	setLayout( new BorderLayout() );
	
	try
	{
	  idiomaUsado=getParameter("idioma");
	} 
	catch(Exception e){}
	
	if(idiomaUsado==null)
	  idiomaUsado="spanish";

	tracker = new MediaTracker( this );

      if (getBounds().height<=600-120)
      {
        piezas = getImage(getCodeBase(), "../imagenes/piezas800.gif");
        tamanhoPieza=48;
      }
      else if (getBounds().height<=768-120)
      {
        piezas = getImage(getCodeBase(), "../imagenes/piezas1024.gif");
        tamanhoPieza=64;
      } 
      else if (getBounds().height<=800-120)
      {
        piezas = getImage(getCodeBase(), "../imagenes/piezas1152.gif");
        tamanhoPieza=74;
      }       
      else
      {
        piezas = getImage(getCodeBase(), "../imagenes/piezas1280.gif");
        tamanhoPieza=90;
      }
	tracker.addImage( piezas,0 );
	logo = getImage(getCodeBase(), "../imagenes/netchess.jpg");
	tracker.addImage( logo,0 );
 
      cropfilter = new CropImageFilter(0, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      pb=createImage(prod);
	tracker.addImage(pb ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      cb=createImage(prod);
	tracker.addImage(cb ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*2, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      ab=createImage(prod);
	tracker.addImage(ab ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*3, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      tb=createImage(prod);
	tracker.addImage(tb ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*4, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      db=createImage(prod);
	tracker.addImage(db ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*5, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      rb=createImage(prod);
	tracker.addImage(rb ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*6, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      pn=createImage(prod);
	tracker.addImage(pn ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*7, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      cn=createImage(prod);
	tracker.addImage(cn ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*8, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      an=createImage(prod);
	tracker.addImage(an ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*9, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      tn=createImage(prod);
	tracker.addImage(tn ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*10, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      dn=createImage(prod);
	tracker.addImage(dn ,0 );

      cropfilter = new CropImageFilter(tamanhoPieza*11, 0, tamanhoPieza, tamanhoPieza);
	prod = new FilteredImageSource(piezas.getSource(),
						     cropfilter);
      rn=createImage(prod);
	tracker.addImage(rn ,0 );


      try { 
		tracker.waitForAll(); 
	} catch( InterruptedException e ) {
		 System.out.println( "Error cargando imagenes" ); 
	}

      centrar = (getBounds().width - tamanhoPieza*8-100)/2;

        if(idiomaUsado.equals("english"))
          texto1=new Label("Level");
        else
	  texto1=new Label("Nivel");
	texto1.setBackground(new Color(150,102,79));
	texto1.setForeground(new Color(235+20, 218+20, 184+20));
      texto1.setFont(new Font("",0,12));
	texto1.setBounds(30,30,80,30);
	add(texto1);

	grupo= new CheckboxGroup();
	nivel1.setForeground(new Color(235+20,218+20,184+20));
	nivel1.setCheckboxGroup(grupo);
	nivel1.setFont(new Font("",0,10));
	nivel1.setBounds(30,60,25,20);
	nivel1.setState(true);
	add(nivel1);

	nivel2.setForeground(new Color(235+20,218+20,184+20));
	nivel2.setCheckboxGroup(grupo);
	nivel2.setFont(new Font("",0,10));
	nivel2.setBounds(30,80,25,20);
	add(nivel2);

	nivel3.setForeground(new Color(235+20,218+20,184+20));
	nivel3.setCheckboxGroup(grupo);
	nivel3.setFont(new Font("",0,10));
	nivel3.setBounds(30,100,25,20);
	add(nivel3);

	nivel4.setForeground(new Color(235+20,218+20,184+20));
	nivel4.setCheckboxGroup(grupo);
	nivel4.setFont(new Font("",0,10));
	nivel4.setBounds(30,120,25,20);
	add(nivel4);

	nivel5.setForeground(new Color(235+20,218+20,184+20));
	nivel5.setCheckboxGroup(grupo);
	nivel5.setFont(new Font("",0,10));
	nivel5.setBounds(30,140,25,20);
	add(nivel5);

	nivel6.setForeground(new Color(235+20,218+20,184+20));
	nivel6.setCheckboxGroup(grupo);
	nivel6.setFont(new Font("",0,10));
	nivel6.setBounds(30,160,25,20);
	add(nivel6);

	nivel7.setForeground(new Color(235+20,218+20,184+20));
	nivel7.setCheckboxGroup(grupo);
	nivel7.setFont(new Font("",0,10));
	nivel7.setBounds(30,180,25,20);
	add(nivel7);

	nivel8.setForeground(new Color(235+20,218+20,184+20));
	nivel8.setCheckboxGroup(grupo);
	nivel8.setFont(new Font("",0,10));
	nivel8.setBounds(30,200,25,20);
	add(nivel8);

	nivel9.setForeground(new Color(235+20,218+20,184+20));
	nivel9.setCheckboxGroup(grupo);
	nivel9.setFont(new Font("",0,10));
	nivel9.setBounds(30,220,25,20);
	add(nivel9);

	nivel10.setForeground(new Color(235+20,218+20,184+20));
	nivel10.setCheckboxGroup(grupo);
	nivel10.setFont(new Font("",0,10));
	nivel10.setBounds(30,240,35,20);
	add(nivel10);

        if(idiomaUsado.equals("english"))
          xogar = new Button("New");
        else
          xogar = new Button("Nueva");
	xogar.setFont(new Font("",1,11));
	xogar.setBackground(new Color(235+20, 218+20, 184+20));
	xogar.setForeground(new Color(150-40,102-40,79-40));
	xogar.setBounds(30,270,40,20);
	add(xogar);

	etqNivel=new Label("");
	etqNivel.setBackground(new Color(235, 218, 184));
	etqNivel.setForeground(new Color(150-40,102-40,79-40));
        etqNivel.setFont(new Font("",0,24));
        etqNivel.setSize(200,40);
	etqNivel.setBounds(centrar + 50+tamanhoPieza*4-7*12/2,15,200,35);
	add(etqNivel);

	  jugadorVirtual = new XogadorVirtual(this,1);
	  //jugadorVirtual.resize(320,320);
	  jugadorVirtual.resize(tamanhoPieza*8,tamanhoPieza*8);
	  jugadorVirtual.init();
	  jugadorVirtual.start();
	  jugadorVirtual.repaint();
	  //jugadorVirtual.setBounds(centrar +  50,50,320,320 );
        jugadorVirtual.setBounds(centrar +  50,50,tamanhoPieza*8,tamanhoPieza*8 );
	  add(jugadorVirtual);

         if(idiomaUsado.equals("english"))
           etqNivel.setText("LEVEL 1");
         else
	   etqNivel.setText("NIVEL 1");

        remove(tablero_boton_tapiz);
	  tablero_boton_tapiz.setBackground(new Color(235, 218, 184));
	  tablero_boton_tapiz.setEnabled(false);
        //tablero_boton_tapiz.setSize(404,404);
	  //tablero_boton_tapiz.setBounds(centrar + 8,8,404,404);
        tablero_boton_tapiz.setSize(tamanhoPieza*8+88,tamanhoPieza*8+88);
        tablero_boton_tapiz.setBounds(centrar + 8,8,tamanhoPieza*8+88,tamanhoPieza*8+88);
	  add(tablero_boton_tapiz);

	  Panel fondo=new Panel();
        fondo.setBackground(new Color(150,102,79));
        add(fondo);             
    }



    public boolean action (Event evt, Object arg) {

       	if(evt.target.equals(xogar)) 
	{ 
	  nuevaPartida();

	}

	return true;

    }

    private void nuevaPartida()
    {
	int nivel=0;

	  if (nivel1.getState())
	    nivel = 1;
	  if (nivel2.getState())
	    nivel = 2;
	  if (nivel3.getState())
	    nivel = 3;
	  if (nivel4.getState())
	    nivel = 4;
	  if (nivel5.getState())
	    nivel = 5;
	  if (nivel6.getState())
	    nivel = 6;
	  if (nivel7.getState())
	    nivel = 7;
	  if (nivel8.getState())
	    nivel = 8;
	  if (nivel9.getState())
	    nivel = 9;
	  if (nivel10.getState())
	    nivel = 10;

          if(idiomaUsado.equals("english"))
            etqNivel.setText("LEVEL "+String.valueOf(nivel));
          else
	    etqNivel.setText("NIVEL "+String.valueOf(nivel));
        jugadorVirtual.nivel=nivel;
	  jugadorVirtual.init();

        repaint();      

    }

    public void destroy() {
        remove(jugadorVirtual);
    }

    public void paint(Graphics g) {

    } 

}