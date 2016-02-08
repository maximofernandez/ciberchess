import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.lang.Math;

import javax.swing.JButton;
import javax.swing.*;

public class XestionColor extends Applet implements ActionListener,ItemListener,AdjustmentListener
{
    Salon salon;
    Image imaxe;
    Label lblFondo,lblFrente,lblAleatorio,lblFondo2,lblFrente2;
    JButton btnAceptar,btnCancelar;
    Checkbox chkFondo[];
    Checkbox chkFrente[];
    Checkbox chkAleatorio;
    CheckboxGroup grupoFondo,grupoFrente;
    Color coloresFondo[];
    Color coloresFrente[];
    Scrollbar brR1,brG1,brB1,brR2,brG2,brB2;
  

    public void init() 
    {
      int i;
      
      setLayout(null);
      setBackground(salon.colorSalon);

      try
      {
      	coloresFondo=new Color[10];
      	coloresFrente=new Color[10];
        for(i=0;i<10;i++)
        {
          coloresFondo[i]=new Color(Integer.valueOf(salon.getParameter("ColorFondoRojo"+i)).intValue(),
                                    Integer.valueOf(salon.getParameter("ColorFondoVerde"+i)).intValue(),
                                    Integer.valueOf(salon.getParameter("ColorFondoAzul"+i)).intValue());
          coloresFrente[i]=new Color(Integer.valueOf(salon.getParameter("ColorFrenteRojo"+i)).intValue(),
                                     Integer.valueOf(salon.getParameter("ColorFrenteVerde"+i)).intValue(),
                                     Integer.valueOf(salon.getParameter("ColorFrenteAzul"+i)).intValue());
        }
      }
      catch (Exception e) {}
      
      chkFondo=new Checkbox[10];
      chkFrente=new Checkbox[10];
      grupoFondo= new CheckboxGroup();
      grupoFrente= new CheckboxGroup();
      for(i=0;i<10;i++)
      {
      	chkFondo[i]=new Checkbox();
      	chkFondo[i].setBackground(coloresFondo[i]);
      	chkFondo[i].setCheckboxGroup(grupoFondo);
      	chkFondo[i].setBounds(58+40*i,10,23,23);
      	add(chkFondo[i]);
      	chkFondo[i].addItemListener(this);
      	chkFrente[i]=new Checkbox();
      	chkFrente[i].setBackground(coloresFrente[i]);
      	chkFrente[i].setCheckboxGroup(grupoFrente);
      	chkFrente[i].setBounds(58+40*i,50,23,23);
      	add(chkFrente[i]);
      	chkFrente[i].addItemListener(this);
      }
      
      chkAleatorio = new Checkbox();
      chkAleatorio.setBounds(58,150,23,23);
      chkAleatorio.setBackground(salon.colorSalon);
      if(salon.getParameter("ColorAleatorio").compareTo("1")==0)
        chkAleatorio.setState(true);
      add(chkAleatorio);
      chkAleatorio.addItemListener(this);
      	
      lblFondo=new Label(salon.idioma.traducir("Fondo"));
      lblFondo.setFont(new Font("",0,11));
      lblFondo.setForeground(salon.colFondoBotos);
      lblFondo.setBackground(salon.colorSalon);      
      lblFondo.setBounds(10,10,35,23);
      add(lblFondo);        
      
      lblFrente=new Label(salon.idioma.traducir("Frente"));
      lblFrente.setFont(new Font("",0,11));
      lblFrente.setForeground(salon.colFondoBotos);
      lblFrente.setBackground(salon.colorSalon);      
      lblFrente.setBounds(10,50,35,23);
      add(lblFrente);     
      
      	
      lblFondo2=new Label(salon.idioma.traducir("Fondo"));
      lblFondo2.setFont(new Font("",0,11));
      lblFondo2.setForeground(salon.colFondoBotos);
      lblFondo2.setBackground(salon.colorSalon);      
      lblFondo2.setBounds(10,89,35,23);
      add(lblFondo2);        
      
      lblFrente2=new Label(salon.idioma.traducir("Frente"));
      lblFrente2.setFont(new Font("",0,11));
      lblFrente2.setForeground(salon.colFondoBotos);
      lblFrente2.setBackground(salon.colorSalon);      
      lblFrente2.setBounds(10,110,35,23);
      add(lblFrente2);      
         
      lblAleatorio=new Label(salon.idioma.traducir("Aleatorio"));
      lblAleatorio.setFont(new Font("",0,11));
      lblAleatorio.setForeground(salon.colFondoBotos);
      lblAleatorio.setBackground(salon.colorSalon);      
      lblAleatorio.setBounds(10,150,48,23);
      add(lblAleatorio);          

      brR1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brR1.setBounds(47,96,132,10);
      brR1.setBackground(new Color(128,0,0));
      add(brR1);
      brR1.addAdjustmentListener(this);      
      
      brG1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brG1.setBounds(180,96,132,10);
      brG1.setBackground(new Color(0,128,0));
      add(brG1);
      brG1.addAdjustmentListener(this);      
      
      brB1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brB1.setBounds(313,96,132,10);
      brB1.setBackground(new Color(0,0,128));
      add(brB1);
      brB1.addAdjustmentListener(this);      
      
      brR2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brR2.setBounds(47,117,132,10);
      brR2.setBackground(new Color(128,0,0));
      add(brR2);
      brR2.addAdjustmentListener(this);      
      
      brG2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brG2.setBounds(180,117,132,10);
      brG2.setBackground(new Color(0,128,0));
      add(brG2);
      brG2.addAdjustmentListener(this);      
      
      brB2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 255);
      brB2.setBounds(313,117,132,10);
      brB2.setBackground(new Color(0,0,128));
      add(brB2);
      brB2.addAdjustmentListener(this);      
       
      btnAceptar=new JButton(salon.idioma.traducir("Aceptar"));
      btnAceptar.setFont(new Font("",0,11));
      btnAceptar.setBackground(salon.colFondoBotos);
      btnAceptar.setForeground(salon.colTextoBotos);
      btnAceptar.setBounds(192,150,55,23);
      add(btnAceptar);      
      btnAceptar.addActionListener(this);
      
      btnCancelar=new JButton(salon.idioma.traducir("Cancelar"));
      btnCancelar.setFont(new Font("",0,11));
      btnCancelar.setBackground(salon.colFondoBotos);
      btnCancelar.setForeground(salon.colTextoBotos);
      btnCancelar.setBounds(248,150,55,23);
      add(btnCancelar);      
      btnCancelar.addActionListener(this);      
      
      fijarBarrasColor();
      
    }


    public XestionColor(Salon salon) 
    {
      this.salon=salon;
    }


    public void paint(Graphics g) 
    {
      int i;
      
      for(i=0;i<10;i++)
      {
      	g.setColor(coloresFondo[i]);
        g.fillRect(47+40*i,2,38,38);
        g.setColor(new Color(110,110,110));
        g.drawRect(47+40*i-1,2-1,38+1,38+1);
      	g.setColor(coloresFrente[i]);
        g.fillRect(47+40*i,42,38,38);     
        g.setColor(new Color(110,110,110));
        g.drawRect(47+40*i-1,42-1,38+1,38+1);           
      }
      super.paint(g);
    }
    
     
    public void actionPerformed(ActionEvent actionevent)
    {
    	String colorAleatorio;
    	
        Object obj = actionevent.getSource();
        if(obj == btnAceptar)
        {
              if(chkAleatorio.getState())
                colorAleatorio="1";
              else
                colorAleatorio="0";
                
              salon.enviarBD("../php/gardarColor.php?sala="+salon.sala+"&login="+salon.login+
	                           "&colRojoFondo="+salon.colorSalon.getRed()+
	                           "&colVerdeFondo="+salon.colorSalon.getGreen()+
	                           "&colAzulFondo="+salon.colorSalon.getBlue()+
	                           "&colRojoFrente="+String.valueOf(salon.colFondoBotos.getRed())+
	                           "&colVerdeFrente="+String.valueOf(salon.colFondoBotos.getGreen())+
	                           "&colAzulFrente="+String.valueOf(salon.colFondoBotos.getBlue())+	                           
	                           "&colAleatorio="+colorAleatorio+
				   "&a="+((int)(Math.random()*100000)),
	                     String.valueOf(hashCode()),"");       
	     salon.dlgXestionColor.hide();         
        	       
        }
        if(obj == btnCancelar)
        {
          salon.enviarBD("../php/lerColor.php?sala="+salon.sala+"&login="+salon.login+
		           "&a="+((int)(Math.random()*100000)),
	                 String.valueOf(hashCode()),"fijarColores");
	  salon.dlgXestionColor.hide();
        }
    }
    
    
    public void fijarColores(String entrada)
    {
      int r,g,b;

      r=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      g=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      b=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      cambiarColorFondo(new Color(r,g,b));

      r=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      g=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      b=Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      cambiarColorFrente(new Color(r,g,b));
      
      salon.ponherMenu();
      
      if(Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue()==1)
        chkAleatorio.setState(true);
      else
        chkAleatorio.setState(false);
            
      fijarBarrasColor();
    }


    public void adjustmentValueChanged(AdjustmentEvent event)
    {
      Object obj = event.getSource();
      
      if(obj==brR1 || obj==brG1 || obj==brB1)
      {
        cambiarColorFondo(new Color(brR1.getValue(),
                                    brG1.getValue(),
                                    brB1.getValue()));                                   
      }
      if(obj==brR2 || obj==brG2 || obj==brB2)
      {
        cambiarColorFrente(new Color(brR2.getValue(),
                                     brG2.getValue(),
                                     brB2.getValue()));                                   
      }       
      salon.ponherMenu();
    }
    
    
     public void itemStateChanged(ItemEvent actionevent)
     {
       int i,j;
       Checkbox chk;
       
       Object obj = actionevent.getSource();
       if(obj instanceof Checkbox)
       {
         chk=(Checkbox) obj;
         if(chk.getCheckboxGroup()==grupoFondo)
         {
           for(i=0;i<10;i++)
           {
             if(chkFondo[i].getState())
             	cambiarColorFondo(coloresFondo[i]);
           }
           fijarBarrasColor();
         }
         if(chk.getCheckboxGroup()==grupoFrente)
         {
           for(i=0;i<10;i++)
           {
             if(chkFrente[i].getState())
               cambiarColorFrente(coloresFrente[i]);
           }
           fijarBarrasColor();
         }        
         salon.ponherMenu(); 
       }       	
     }
     
     
     void cambiarColorFondo(Color colorSeleccionado)
     {
       Color novoColTextoBotos;
       int i;
     	
       if(!salon.colorSalon.equals(colorSeleccionado))
       {
       	 colorSeleccionado = new Color(Math.min(Math.max(colorSeleccionado.getRed(),51),225),Math.min(Math.max(colorSeleccionado.getGreen(),51),215),Math.min(Math.max(colorSeleccionado.getBlue(),51),215));
       	 novoColTextoBotos = new Color(colorSeleccionado.getRed()-50,colorSeleccionado.getGreen()-50,colorSeleccionado.getBlue()-50);
       	 if(colorSeleccionado.equals(salon.colFondoBotos)||
       	    novoColTextoBotos.equals(salon.colFondoBotos))
       	   return; //comprobación para non mezclar cores de fondo e primeiro plano
       	 if(colorSeleccionado.equals(new Color(salon.colFondoBotos.getRed()+30,salon.colFondoBotos.getGreen()+40,salon.colFondoBotos.getBlue()+40))||
       	    novoColTextoBotos.equals(new Color(salon.colFondoBotos.getRed()+30,salon.colFondoBotos.getGreen()+40,salon.colFondoBotos.getBlue()+40)))
       	   return; //comprobación para non mezclar cores de fondo e primeiro plano       	 
       	 try
       	 {
       	   if((new Color(colorSeleccionado.getRed()+30,colorSeleccionado.getGreen()+40,colorSeleccionado.getBlue()+40)).equals(salon.colFondoBotos)||
       	      (new Color(novoColTextoBotos.getRed()+30,novoColTextoBotos.getGreen()+40,novoColTextoBotos.getBlue()+40)).equals(salon.colFondoBotos))
       	     return; //comprobación para non mezclar cores de fondo e primeiro plano       	   
       	 }
       	 catch (Exception e){}
         cambiarColor(salon,salon.colorSalon,colorSeleccionado);
         cambiarColor(salon,salon.colTextoBotos,novoColTextoBotos);
         //salon.pestanhasVerticales.setBackground(colorSeleccionado);
         //salon.pestanhasVerticales.paint(salon.pestanhasVerticales.getGraphics());
         
         try 
         {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
         } catch (Exception exc) {}  
         UIManager.put("TabbedPane.selected", colorSeleccionado);
         //SwingUtilities.updateComponentTreeUI(salon.pestanhasVerticales);
         salon.pestanhasVerticales.updateUI();
         try 
         {
           UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
         } catch (Exception exc) {}   
             
         cambiarColor(salon.dlgXestionColor,salon.colorSalon,colorSeleccionado);
         cambiarColor(salon.dlgXestionColor,salon.colTextoBotos,novoColTextoBotos);
         if(salon.frmVerPartidas != null)
         {
           cambiarColor(salon.frmVerPartidas,salon.colorSalon,colorSeleccionado);
           cambiarColor(salon.frmVerPartidas,salon.colTextoBotos,novoColTextoBotos);         	
         }
         salon.colorSalon=colorSeleccionado;
         salon.colTextoBotos=novoColTextoBotos;
       }
     } 


     void cambiarColorFrente(Color colorSeleccionado)
     {
       Color novoColFondoBotos;
       Ajedrez aj;
       int i;
     	
       novoColFondoBotos = new Color(Math.min(colorSeleccionado.getRed(),225),Math.min(colorSeleccionado.getGreen(),215),Math.min(colorSeleccionado.getBlue(),215));
       if(!novoColFondoBotos.equals(salon.colFondoBotos))
       {
       	 if(novoColFondoBotos.equals(salon.colorSalon)||
       	    novoColFondoBotos.equals(salon.colTextoBotos)||
       	    novoColFondoBotos.equals(salon.colorFondo))
       	   return; //comprobación para non mezclar cores de fondo e primeiro plano
       	 if(novoColFondoBotos.equals(new Color(salon.colorSalon.getRed()+30,salon.colorSalon.getGreen()+40,salon.colorSalon.getBlue()+40))||
       	    novoColFondoBotos.equals(new Color(salon.colTextoBotos.getRed()+30,salon.colTextoBotos.getGreen()+40,salon.colTextoBotos.getBlue()+40)))
       	   return; //comprobación para non mezclar cores de fondo e primeiro plano
         cambiarColor(salon,salon.colFondoBotos,novoColFondoBotos);
         cambiarColor(salon.dlgXestionColor,salon.colFondoBotos,novoColFondoBotos);
         if(salon.frmVerPartidas != null)
           cambiarColor(salon.frmVerPartidas,salon.colFondoBotos,novoColFondoBotos);
         salon.colFondoBotos=novoColFondoBotos;
         salon.colFondoBotosClaro=new Color(salon.colFondoBotos.getRed()+30,salon.colFondoBotos.getGreen()+40,salon.colFondoBotos.getBlue()+40);
         if(salon.chatPublico!=null)
           if(salon.chatPublico.reloxioAnaloxico!=null)
           {
             salon.chatPublico.reloxioAnaloxico.pintar();
             salon.chatPublico.repaint();
           }
       }     
     }
     
   
     void cambiarColor(Component componente,Color color, Color novoColor)
     {
       int i;

       if(componente instanceof Checkbox)
         if(((Checkbox)componente).getCheckboxGroup()==grupoFondo ||
            ((Checkbox)componente).getCheckboxGroup()==grupoFrente)
           return;

       if(componente.getBackground().equals(new Color(color.getRed()+30,color.getGreen()+40,color.getBlue()+40)))
         componente.setBackground(new Color(novoColor.getRed()+30,novoColor.getGreen()+40,novoColor.getBlue()+40));
       if(componente.getBackground().equals(color))
         componente.setBackground(novoColor);
       if(componente.getForeground().equals(color))
         componente.setForeground(novoColor);
                   
       if(componente instanceof Container)
         for(i=0;i<((Container)componente).getComponentCount();i++)       
           cambiarColor(((Container)componente).getComponent(i),color,novoColor);
       componente.repaint();
     }


    void fijarBarrasColor()
    {
      brR1.setValue(salon.colorSalon.getRed());
      brG1.setValue(salon.colorSalon.getGreen());
      brB1.setValue(salon.colorSalon.getBlue());
      brR2.setValue(salon.colFondoBotos.getRed());
      brG2.setValue(salon.colFondoBotos.getGreen());
      brB2.setValue(salon.colFondoBotos.getBlue());      
    }
    
    
}
