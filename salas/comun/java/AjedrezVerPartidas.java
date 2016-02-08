import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.lang.*;

import java.util.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.*;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AjedrezVerPartidas extends Ajedrez
{
    
    JButton btnCerrar ;
    JButton btnAutomatico ;    

    Panel panelVerPartidas;
    Panel panelVerPartidasSur;
    TaboaPartidas tablaPartidas;
    JScrollPane panelPartidas;
    JButton partidasHistoricas;
    JButton partidasOnline;
    JButton btnAxuda;
    JButton partidaMostrarBlancas;
    JButton partidaMostrarNegras;
    JButton btnDescargarPartida;
    JTextField xogadorHistorico;
    Label etqVerPartidas;
    boolean automaticoAdiante=true;
    
    Label textoAviso;    
    Label textoXogador;
    
    JButton boton_tapiz_abajo;
    JButton boton_tapiz_arriba;
    
    Chat chatPublico;
    
    String tempos;                                   /* cadea cos tempos de cada movemento*/
    String ganador;
    
    boolean activa;
    long instanteUltimoMovemento;

    boolean mensajeVerPartidasOcupado=false;
    
    String[] partidaSeleccionada;
    boolean online;

    public AjedrezVerPartidas(Salon sal,String partida,String tempos,String login_b,String login_n,
		              String login,String tb,String tn,String ritmo,int inc,String t)
    {
      super(sal, partida, login_b, login_n, login, tb, tn, ritmo, inc, t);
      
      btnCerrar = new JButton("       "+salon.idioma.traducir("Cerrar")+"         ");
      btnAutomatico = new JButton("       "+salon.idioma.traducir("Automático")+"         ");      

      this.tempos=tempos;

      TaboleiroTamanho-=20;

      int centrar=(TaboleiroTamanho+TaboleiroX+10 - 496)/2-1;

      panelVerPartidas = new Panel();
      panelVerPartidas.setBackground(salon.colorSalon);
      panelVerPartidas.setBounds(1,1,TaboleiroTamanho+TaboleiroX+10,TaboleiroTamanho+TaboleiroY+40);
      panelVerPartidas.setLayout(null);
      
      partidasOnline=new JButton("Online");
      partidasOnline.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	      etqVerPartidas.setText("Online");
	      tablaPartidas.masDatosPartidas(xogadorHistorico.getText(),true);
          }
        });      
      partidasOnline.setFont(new Font("Verdana",1,12));
      partidasOnline.setForeground(salon.colTextoBotos);
      partidasOnline.setBackground(salon.colFondoBotos);
      partidasOnline.setBounds(centrar+5,10,124-10,25);
      //partidasOnline.setEnabled(false);
      panelVerPartidas.add(partidasOnline);

      xogadorHistorico = new JTextField(80);
      xogadorHistorico.setBackground(Color.white);
      xogadorHistorico.setForeground(salon.colTextoBotos);
      xogadorHistorico.setFont(new Font("Verdana",1,12));
      xogadorHistorico.setBounds(centrar+2*124+5,10,124-10,24);
      panelVerPartidas.add(xogadorHistorico);
      
      textoXogador=new Label(salon.idioma.traducir("Jugador"));
      textoXogador.setFont(new Font("Verdana",1,12));
      textoXogador.setBackground(salon.colorSalon);
      textoXogador.setForeground(salon.colFondoBotos);
      textoXogador.setBounds(centrar+2*124-50,11,50,25);
      panelVerPartidas.add(textoXogador);      
      
      textoAviso=new Label(salon.idioma.traducir("En blanco muestra todos"));
      textoAviso.setFont(new Font("Verdana",1,9));
      textoAviso.setBackground(salon.colorSalon);
      textoAviso.setForeground(salon.colFondoBotos);
      textoAviso.setBounds(centrar+2*124+3,34,115,10);
      panelVerPartidas.add(textoAviso);        
      
      partidasHistoricas=new JButton(salon.idioma.traducir("Histórico"));
      partidasHistoricas.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	      etqVerPartidas.setText(salon.idioma.traducir("Histórico"));
	      tablaPartidas.masDatosPartidas(xogadorHistorico.getText(),false); 
          }
        });
      partidasHistoricas.setFont(new Font("Verdana",1,12));
      partidasHistoricas.setForeground(salon.colTextoBotos);
      partidasHistoricas.setBackground(salon.colFondoBotos);
      partidasHistoricas.setBounds(centrar+3*124+5,10,124-10,25);
      panelVerPartidas.add(partidasHistoricas);
      
      btnAxuda=new JButton(salon.idioma.traducir("Ayuda"));
      btnAxuda.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              VentanaHtml ventanaHtml=new VentanaHtml(salon,salon.frmVerPartidas,"../php/"+salon.idiomaUsado+"/ayuda_ver_partidas.php?sala=" + salon.sala +"&a="+((int)(Math.random()*100000)),salon.idioma.traducir("Ayuda"),true,710,500);
              ventanaHtml.show();
          }
        });
      btnAxuda.setFont(new Font("Verdana",1,12));
      btnAxuda.setForeground(salon.colTextoBotos);
      btnAxuda.setBackground(salon.colFondoBotos);
      btnAxuda.setBounds(centrar+3*124+5,50,124-10,25);
      panelVerPartidas.add(btnAxuda);      

      etqVerPartidas = new Label("");
      etqVerPartidas.setBackground(salon.colorSalon);
      etqVerPartidas.setForeground(salon.colFondoBotos);     
      etqVerPartidas.setFont(new Font("Verdana",0,21));
      etqVerPartidas.setBounds(centrar+2*124-45,55,150,30);
      panelVerPartidas.add(etqVerPartidas);
      
      try 
      {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception exc) {}  
           
      tablaPartidas = new TaboaPartidas(salon);
      tablaPartidas.setForeground(salon.colTextoBotos);
      tablaPartidas.setBackground(salon.colFondoBotosClaro);
      tablaPartidas.getTableHeader().setForeground(salon.colTextoBotos);
      tablaPartidas.getTableHeader().setBackground(salon.colFondoBotos);      
      panelPartidas = new JScrollPane(tablaPartidas);
      panelPartidas.setBackground(salon.colFondoBotosClaro);    
      panelPartidas.getViewport().setBackground(salon.colFondoBotosClaro);       
      panelPartidas.setBorder(new EmptyBorder(0,0,0,0));
      panelPartidas.getVerticalScrollBar().setBorder(new EmptyBorder(0,0,0,0));
      panelPartidas.setBounds(centrar+5,90,496-10,352);
      panelVerPartidas.add(panelPartidas);
      
      try 
      {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception exc) {}      
      
      partidaMostrarBlancas=new JButton(salon.idioma.traducir("Ver Blancas"));
      partidaMostrarBlancas.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
      	    mostrarPartida("b");
          }
        });
      partidaMostrarBlancas.setFont(new Font("Verdana",1,12));
      partidaMostrarBlancas.setForeground(salon.colTextoBotos);
      partidaMostrarBlancas.setBackground(salon.colFondoBotos);
      partidaMostrarBlancas.setBounds(centrar+5,452,165-10,25);
      panelVerPartidas.add(partidaMostrarBlancas);
      
      partidaMostrarNegras=new JButton(salon.idioma.traducir("Ver Negras"));
      partidaMostrarNegras.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
      	    mostrarPartida("n");
          }
        });      
      partidaMostrarNegras.setFont(new Font("Verdana",1,12));
      partidaMostrarNegras.setForeground(salon.colTextoBotos);
      partidaMostrarNegras.setBackground(salon.colFondoBotos);
      partidaMostrarNegras.setBounds(centrar+2*165+5,452,165-10+2,25);
      panelVerPartidas.add(partidaMostrarNegras);
      
      btnDescargarPartida=new JButton(salon.idioma.traducir("Descargar Partidas"));
      btnDescargarPartida.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
            descargarPartida();
          }
        });
      btnDescargarPartida.setFont(new Font("Verdana",1,12));
      btnDescargarPartida.setForeground(salon.colTextoBotos);
      btnDescargarPartida.setBackground(salon.colFondoBotos);
      btnDescargarPartida.setBounds(centrar+165+5,452,165-10,25);
      panelVerPartidas.add(btnDescargarPartida);
      
      add(panelVerPartidas);
      
      chatPublico=new Chat(salon.login,16,true,36,salon);
      chatPublico.setBounds(panelVerPartidas.getBounds().width+1,2,salon.getBounds().width-panelVerPartidas.getBounds().width-5,panelVerPartidas.getBounds().height-42);
      chatPublico.init();
      chatPublico.reloxioAnaloxico.fijarHora(salon.chatPublico.reloxioAnaloxico.consultarHora(),
                                             salon.chatPublico.reloxioAnaloxico.consultarMinuto(),
                                             salon.chatPublico.reloxioAnaloxico.consultarSegundo());      

      add(chatPublico);
      
      btnAutomatico.setText("     "+salon.idioma.traducir("Detener")+"     ");
   
      init();
   
      chatPrivado.setVisible(false);

      panel2.removeAll();
      panel2.setVisible(false);

      btnCerrar.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	      salon.enviar_mensaxe("",14,"MAXIMO"); //quitar rexistro no servidor de comunicacion para ver partidas
	      panelVerPartidas.setVisible(true);  
	      repaint();
          }
        });
      btnCerrar.setBackground(salon.colFondoBotos);
      btnCerrar.setForeground(salon.colTextoBotos);
      btnCerrar.setFont(fuenteBotones);
      panel2.add(btnCerrar);

      panel2.add(btnIni);
      panel2.add(btnAtras);
      panel2.add(btnAdelante);
      panel2.add(btnFin);
      
      btnAutomatico.addActionListener(
        new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
	      if(btnAutomatico.getText().compareTo(salon.idioma.traducir("Automático")+" >>")==0)
	      {
	        btnAutomatico.setText("     "+salon.idioma.traducir("Detener")+"     ");
	        automaticoAdiante=true;
	      }
	      else if(btnAutomatico.getText().compareTo("     "+salon.idioma.traducir("Detener")+"     ")==0)
	      {
	        btnAutomatico.setText(salon.idioma.traducir("Automático")+" >>");
	        automaticoAdiante=false;
	      }
          }
        });
      btnAutomatico.setBackground(salon.colFondoBotos);
      btnAutomatico.setForeground(salon.colTextoBotos);
      btnAutomatico.setFont(fuenteBotones);
      panel2.add(btnAutomatico);
      
      panel2.add(chkComidas);
      panel2.validate();
      panel2.setVisible(true);
      panel2.repaint();

      reloj.para();
      //remove(reloj);
      relojRival.para();
      //remove(relojRival);

      temporizador.intervalo=1000;
      
      canvas.setHabilitado(false);
      
    }


    public void descargarPartida()
    {
        URL url;
        String cadeaPHP;
        String[][] partidasSeleccionadas;
        int i,j;
        
          partidasSeleccionadas = tablaPartidas.partidasSeleccionadas();
	  if(partidasSeleccionadas==null)
	    JOptionPane.showMessageDialog(this, salon.idioma.traducir("Selecciona una partida para descargar"));
	  else if(tablaPartidas.online)
	    JOptionPane.showMessageDialog(this, salon.idioma.traducir("Solo se pueden descargar partidas terminadas"));
	  else if(partidasSeleccionadas.length>100)
	    JOptionPane.showMessageDialog(this, salon.idioma.traducir("No se pueden seleccionar más de 100 partidas"));	    
	  else 
	  { 
	    cadeaPHP="../php/enviarPartidaFicheiro.php?sala="+salon.sala;
	    for(i=0;i<partidasSeleccionadas.length;i++)
	    {
              login_b=partidasSeleccionadas[i][1];
              login_n=partidasSeleccionadas[i][2];
              if(loginXogador.equals(login_n))
                login="n";
              else
                login="b";

              j=i+1;
              cadeaPHP=cadeaPHP+"&f"+j+"="+partidasSeleccionadas[i][0].substring(0,10)+"&h"+j+"="+partidasSeleccionadas[i][0].substring(11)+"&lb"+j+"="+login_b+"&ln"+j+"="+login_n+"&l"+j+"="+login;
            
            }
            cadeaPHP=cadeaPHP+"&a="+((int)(Math.random()*100000));
            salon.enviarBD(cadeaPHP,
	                   String.valueOf(hashCode()),"postDescargarPartida");            
         }
    }


    public void postDescargarPartida(String entrada)
    {
      URL url;
      
      try 
      { 
      	url = new URL(salon.getCodeBase(),entrada);
        (salon.getAppletContext()).showDocument (url, "_self");
       }catch(MalformedURLException e) 
       {
         salon.chatPublico.engadirFrase(salon.idioma.traducir("SISTEMA"),salon.idioma.traducir("Error al buscar fichero para descargar la partida."));
       }
    }


    public void mostrarPartida(String color)
    {
	if(tablaPartidas.partidaSeleccionada()==null)
	  JOptionPane.showMessageDialog(this, salon.idioma.traducir("Selecciona una partida para ver"));    	
        mostrarPartida(color,tablaPartidas.partidaSeleccionada(),tablaPartidas.online);    	
    }


    public void mostrarPartida(String color,String[] partidaSeleccionada,boolean online)
    {
        URL url;
        String cadeaPHP;
        
        this.online=online;
        this.partidaSeleccionada=partidaSeleccionada;
  
        if(online)
        {
          inicio=false;
          btnAutomatico.setText(salon.idioma.traducir("Automático")+" >>");
          automaticoAdiante=false;
          activa=true;
          instanteUltimoMovemento=salon.tempoRelativo.tempo();
        }
        else
        {
          inicio=true;
          btnAutomatico.setText("     "+salon.idioma.traducir("Detener")+"     ");
          automaticoAdiante=true;
          activa=false;
        }
        llamadas_pintar_tablero=0;

	  if(partidaSeleccionada!=null)
          { 
            login_b=partidaSeleccionada[1];
            login_n=partidaSeleccionada[2];
            login=color;
            ganador=partidaSeleccionada[5];
            if (login.equals("b"))
              loginXogador=login_b;
            else
              loginXogador=login_n;
           
            if(online)
            {
              salon.enviar_mensaxe(login_b,13,login_n); //rexistrarse no servidor de comunicacion para ver partidas 
              cadeaPHP="../php/partidaOnline.php?sala="+salon.sala+"&loginb="+login_b+"&loginn="+login_n+"&a="+((int)(Math.random()*100000));
            }
            else
              cadeaPHP="../php/partidaHistorico.php?sala="+salon.sala+"&fecha="+partidaSeleccionada[0].substring(0,10)+"&hora="+partidaSeleccionada[0].substring(11)+"&loginb="+login_b+"&loginn="+login_n+"&a="+((int)(Math.random()*100000));
              
            salon.enviarBD(cadeaPHP,
	       String.valueOf(hashCode()),"visualizarPartida");              
          }
         
    }

    public void visualizarPartida(String entrada)
    {
       //System.out.println("visualizarPartida entrada: "+entrada);
       partida = entrada.substring(0,entrada.indexOf("#"));
       entrada = entrada.substring(entrada.indexOf("#")+1); 
       tempos=entrada.substring(0,entrada.indexOf("#"));
       entrada = entrada.substring(entrada.indexOf("#")+1);
       ritmo=entrada;
       if(online)
       {
         posicion=partida.length();
         canvas.texto=ritmo+" min "+salon.idioma.traducir("ACTIVA");
       }
       else
       {
         posicion=0;
         if(ganador.equals(salon.idioma.traducir("TABLAS")))
           canvas.texto=ritmo+" min  "+salon.idioma.traducir("TABLAS");
         else if(ganador.equals(salon.idioma.traducir("ABANDONADA")))
           canvas.texto=ritmo+" min  "+salon.idioma.traducir("ABANDONADA");
         else
           canvas.texto=ritmo+" min  "+salon.idioma.traducir("GANADOR")+" "+ganador;
       }
       pintar_tablero();
       panelVerPartidas.setVisible(false);
       panelVerPartidas.paint(panelVerPartidas.getGraphics());   
       canvas.setHabilitado(false);   
    }


    public synchronized void pintar_tablero() //engádeselle que actualize os reloxios
    {
      Reloj relojBlancas,relojNegras;
      int p;
      
      p=posicion/8;
      //System.out.println("p: "+p);
      //System.out.println("login xogador: "+loginXogador);
      if(loginXogador.equals(login_n))
      {
        //System.out.println("login negras");
        //System.out.println("login xogador: "+loginXogador);
      	relojBlancas=relojRival;
      	relojNegras=reloj;
      }
      else
      {
      	//System.out.println("login blancas");
      	relojBlancas=reloj;
      	relojNegras=relojRival;
      }
      super.pintar_tablero();
      if(p==p/2*2) //par
      {
      	//System.out.println("PAR");
        relojNegras.para(tempoMov(p));
        if(posicion==partida.length() && activa)
          relojBlancas.arranca(tempoMov(p-1)-(int)((salon.tempoRelativo.tempo()-instanteUltimoMovemento)/1000));
        else
          relojBlancas.para(tempoMov(p-1));      	
      }
      else
      {
      	//System.out.println("IMPAR");
        relojBlancas.para(tempoMov(p));
        if(posicion==partida.length() && activa)
          relojNegras.arranca(tempoMov(p-1)-(int)((salon.tempoRelativo.tempo()-instanteUltimoMovemento)/1000));
        else
          relojNegras.para(tempoMov(p-1));   	
      }
      relojNegras.repaint();
      relojBlancas.repaint();
    }
    
    public int tempoMov(int pos)
    {
      //System.out.println("pos: "+pos);
      //System.out.println("Tempos: "+tempos);
      if(pos<1)
        return 60*(Integer.valueOf(ritmo)).intValue();
        
            //System.out.println("Tempos: "+tempos+" "+tempos.substring(pos*4-4,pos*4));
      return Integer.valueOf(tempos.substring(pos*4-4,pos*4)).intValue();
    }
    


/******************************************************************************************/
/******************************* MENSAXE VER PARTIDAS *************************************/
/******************************************************************************************/
    public synchronized void mensajeVerPartidas(String entrada)
    {
    String mov="",tempo="";
    int posicionMov=0;

    while(mensajeVerPartidasOcupado)
    {
      try 
      {
          wait();
      } catch( Exception e ) 
      {
          return;
      }
    }        

    mensajeVerPartidasOcupado=true;

    temporizador.antibloqueo=0;
    
    //salon.chatPublico.engadirFrase("DEPURAR","mensajeVerPartidas, entrada: "+entrada);
 
    try
    {

      if (entrada.length() > 0)
      {

        tempo = entrada.substring(0,entrada.indexOf("#"));
        tempo = "0000"+tempo;
        tempo = tempo.substring(tempo.length()-4);
        entrada = entrada.substring(entrada.indexOf("#")+1);
        mov = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        posicionMov = (Integer.valueOf(entrada.substring(0,entrada.indexOf("#")))).intValue();
        if (mov.length() > 0)
        {
          if( partida.length()<posicionMov) //chega movemento online e todavia non se lera todo da bd
          {
            //Antes ou despois aquí haberá que recargar a partida e esperar nun bucle a que se recargue
            mostrarPartida(login,partidaSeleccionada,online);
          }
          else if( partida.length()==posicionMov) 
          {
            instanteUltimoMovemento=salon.tempoRelativo.tempo();
            
            tempos = tempos.substring(0,posicionMov/2)+tempo;
          	
            partida = partida.substring(0,posicionMov)+mov;
      
            posicion_servidor_partida=partida.length();

            posicion = partida.length();

            direccion = "adelante";
            pintar_tablero();
          }
        }
      }
    } catch(Exception e) {}
    
    mensajeVerPartidasOcupado=false;
    notify();     
    }


    public synchronized void procesarMovemento(String entrada) 
    {
      mensajeVerPartidas(entrada);
    }
    
    
    public void recibirComentarioFinPartida(String cadea)
    {
      activa = false;
      reloj.para();
      relojRival.para();
      canvas.texto=cadea.replace('|',' ');
      canvas.repaint();
    }    
    
    
    public void finDesconto()
    {
      //En ver partidas non se fai nada, nunca se deberia chamar tampouco porque nunca comeza desconto
    }
    
    
    public void finReloxio()
    {
      //Nunca se fai nada ainda que termine un reloxio
    }    

}




/*************************************************************************************/
/****************************CLASE TABOA PARTIDAS ************************************/
/*************************************************************************************/

class TaboaPartidas extends JTable implements MouseListener
{
    DefaultTableModel datos;
    boolean online=false;
    String xogadorHist="";
    int posicionMasDatos=0;
    Salon salon;
    
    public TaboaPartidas(Salon salon) 
    {
    	
        super();
        datos=new DefaultTableModel() 
                  {
                    public boolean isCellEditable(int rowIndex, int mColIndex) 
                    {
                      return false;
                    }
                  };
        
        this.salon=salon;
        
        setModel(datos);
        
        setBorder(new EmptyBorder(0,0,0,0));
        
        addMouseListener(this); 
        
        getTableHeader().setReorderingAllowed(true);
        getTableHeader().setBorder(new EmptyBorder(0,0,0,0));

        datos.addColumn(salon.idioma.traducir("Fecha"));
        datos.addColumn(salon.idioma.traducir("Blancas"));
        datos.addColumn(salon.idioma.traducir("Negras"));
        datos.addColumn("Min.");
        datos.addColumn(salon.idioma.traducir("Movs"));
        datos.addColumn(salon.idioma.traducir("Ganador"));
        
        setRowHeight(15);
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(true);
        
        //setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getColumnModel().getColumn(0).setWidth(125);
        getColumnModel().getColumn(0).setPreferredWidth(125);  
        getColumnModel().getColumn(1).setWidth(100);
        getColumnModel().getColumn(1).setPreferredWidth(100); 
        getColumnModel().getColumn(2).setWidth(100);
        getColumnModel().getColumn(2).setPreferredWidth(100);                       
        getColumnModel().getColumn(3).setWidth(45);
        getColumnModel().getColumn(3).setPreferredWidth(45);  
        getColumnModel().getColumn(4).setWidth(55);
        getColumnModel().getColumn(4).setPreferredWidth(55);          
        getColumnModel().getColumn(5).setWidth(110);
        getColumnModel().getColumn(5).setPreferredWidth(110);               

    }
 

    public String[] partidaSeleccionada()
    {
      int fila;
      
      fila = getSelectedRow();
      if (fila == -1)
        return null;
      return new String[]{
      	                   (String)datos.getValueAt(fila,0),
      	                   (String)datos.getValueAt(fila,1),
      	                   (String)datos.getValueAt(fila,2),
                           (datos.getValueAt(fila,3)).toString(),
                           (datos.getValueAt(fila,4)).toString(),
                           (String)datos.getValueAt(fila,5),
                         };
    }
    
    public String[][] partidasSeleccionadas()
    {
      int filas[];
      String cadeas[][];
      int i;
      
      if (getSelectedRowCount() < 1)
        return null;
        
      cadeas=new String[getSelectedRowCount()][6];
      
      filas = getSelectedRows();
      
      for(i=0;i<getSelectedRowCount();i++)
      {
        cadeas[i][0]=(String)datos.getValueAt(filas[i],0);
      	cadeas[i][1]=(String)datos.getValueAt(filas[i],1);
      	cadeas[i][2]=(String)datos.getValueAt(filas[i],2);
        cadeas[i][3]=(datos.getValueAt(filas[i],3)).toString();
        cadeas[i][4]=(datos.getValueAt(filas[i],4)).toString();
        cadeas[i][5]=(String)datos.getValueAt(filas[i],5); 	
      }
      
      return cadeas;     
    }


    public void engadirPartida(String fecha,String blancas,String negras,Integer ritmo,Integer movs,String ganador)
    {
      int i;

      datos.addRow(new Object[]{
      	                         fecha,
      	                         blancas,
      	                         negras,
      	                         ritmo,
                                 movs,
                                 ganador
                               });
    }
    
    
    public void masDatosPartidas(String xogadorHistorico, boolean online)
    {
      posicionMasDatos=0;
      this.online=online;
      xogadorHist=xogadorHistorico;
      while(datos.getRowCount()>0)
        datos.removeRow(0);
      masDatosPartidas();
    }
    
    
    public void masDatosPartidas()
    {
      String url;	
      
      if (datos.getRowCount()>0)
	  datos.removeRow(datos.getRowCount()-1);

      if (!online)
        url = "../php/PartidasHistorico.php?sala="+salon.sala+"&login="+salon.login+"&xogadorHistorico="+xogadorHist+"&posicion="+posicionMasDatos+"&a="+((int)(Math.random()*100000));
      else
        url = "../php/PartidasOnline.php?sala="+salon.sala+"&login="+salon.login+"&a="+((int)(Math.random()*100000));

      salon.enviarBD(url,String.valueOf(hashCode()),"despoisDeMDP");	    
    }
    

    public void despoisDeMDP(String entrada)
    {
      int contador=0;
    	
      while(entrada.length()>0) 
      {
        String partida[]={"","","","","",""};
        partida[0] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        partida[1] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        partida[2] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        partida[3] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        partida[4] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        partida[5] = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);        
        posicionMasDatos++;
        engadirPartida(partida[0],
                       partida[1],
                       partida[2],
                       Integer.valueOf(partida[3]),
                       Integer.valueOf(partida[4]),
                       partida[5]
                      );
        contador ++;
      }
      if(!online)
      {
        if (contador == 20)
          engadirPartida(salon.idioma.traducir("Más datos"),null,null,null,null,null);
      
        try
        {
          salon.esperar(100);
        }catch(Exception e){}
        salon.ajedrezVer.panelPartidas.getVerticalScrollBar().setValue(salon.ajedrezVer.panelPartidas.getVerticalScrollBar().getMaximum());
      }
      repaint();
    }


    public void mouseClicked(MouseEvent e) 
    {
      int fila;
      
      fila = getSelectedRow();
      if (fila == -1)
        return;
    	
      //if (e.getClickCount() == 1) 
      if(((String)datos.getValueAt(fila,0)).equals(salon.idioma.traducir("Más datos")))
        masDatosPartidas();
      else
        if (e.getClickCount() == 2)
          salon.ajedrezVer.mostrarPartida("b");
    }     
    
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }    

    
}