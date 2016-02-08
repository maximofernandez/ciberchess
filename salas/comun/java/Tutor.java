import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.lang.Math;
import java.net.*;

import javax.swing.JButton;

public class Tutor extends Applet implements ActionListener
{
    Salon salon;
    Label lblFondo;
    JButton btnNovaClase;
    Checkbox chkAleatorio;

    public void init() 
    {
      int i;
      
      setLayout(null);
      setBackground(salon.colorSalon);

//      chkAleatorio = new Checkbox();
//      chkAleatorio.setBounds(58,150,23,23);
//      chkAleatorio.setBackground(salon.colorSalon);
//      add(chkAleatorio);
//      chkAleatorio.addItemListener(this);
      	
//      lblFondo=new Label("Fondo");
//      lblFondo.setFont(new Font("",0,11));
//      lblFondo.setForeground(salon.colFondoBotos);
//      lblFondo.setBackground(salon.colorSalon);      
//      lblFondo.setBounds(10,10,35,23);
//      add(lblFondo);        
       
      btnNovaClase=new JButton(salon.idioma.traducir("Nueva Clase"));
      btnNovaClase.setFont(new Font("",1,11));
      btnNovaClase.setBackground(salon.colFondoBotos);
      btnNovaClase.setForeground(salon.colTextoBotos);
      btnNovaClase.setBounds(192,150,55,23);
      add(btnNovaClase);      
      btnNovaClase.addActionListener(this);
      
    }


    public Tutor(Salon salon) 
    {
      this.salon=salon;
    }


    public void paint(Graphics g) 
    {
    }
    
    public void actionPerformed(ActionEvent actionevent)
    {
    	
        Object obj = actionevent.getSource();
        if(obj == btnNovaClase)
        {
           crearPartida();
        }
    }
    
    public void crearPartida() 
    {
      URL url;

      salon.enviarBD("../php/altaPartidaTutor.php?sala="+salon.sala+
			   "&a="+((int)(Math.random()*100000)),
	             String.valueOf(hashCode()),"despoisDeCrearPartida");
    }

    public void despoisDeCrearPartida(String entrada)
    {
    	String t;
    	
        t=entrada.substring(0,entrada.indexOf("#"));
          
  	//salon.pestanhas.ajedrezTutor = new AjedrezTutor(salon,(byte)7,"",t);
        //salon.pestanhas.tabbed.add("Tutor",salon.pestanhas.ajedrezTutor);      
    }
    
    
   
}


 