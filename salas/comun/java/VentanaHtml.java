import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.awt.*;
import java.net.URL;
import java.io.*;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.swing.border.*;


public class VentanaHtml extends JDialog implements Runnable, HyperlinkListener
{

  JEditorPane html;
  Salon salon;
  String direccionURL;
  
  private Thread thread = null;  

  public VentanaHtml(Salon salon, Frame frame, String direccionURL, String titulo, boolean modal, int ancho, int alto)
  {
    super(frame,titulo,modal);
    this.salon=salon;
    this.direccionURL=direccionURL;

    setSize(ancho,alto);
    setLocationRelativeTo(frame);   

    setBackground(Color.black);
    getContentPane().setBackground(Color.black);
    validate();
    repaint();    
    
    
  }   






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
      URL url = null;

      try 
      {
        url = new URL(salon.getCodeBase(),direccionURL);
      } catch (Exception exc) 
        {
                   System.err.println("Error intentando abrir "
                                      + "dirección URL: " + url);
                   url = null;
        }
            
      if(url != null) 
      {
        html = new JEditorPane(url);
        html.setBorder(new EmptyBorder(0,0,0,0));
        html.setEditable(false);
        html.setBackground(Color.black);
        html.addHyperlinkListener(this);
        //html.addHyperlinkListener(this);
        JScrollPane scroller = new JScrollPane();
        //scroller.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        scroller.setBorder(new EmptyBorder(0,0,0,0));
        JViewport vp = scroller.getViewport();
        vp.add(html);
        vp.setBackingStoreEnabled(true);
        getContentPane().add(scroller, BorderLayout.CENTER);
      }
    } catch (MalformedURLException e) 
      {
        System.out.println("Malformed URL: " + e);
      } 
      catch (IOException e) 
      {
        System.out.println("IOException: " + e);
      }
      validate();
      repaint();

    }













    /**
     * Notification of a change relative to a 
     * hyperlink.
     */
    public void hyperlinkUpdate(HyperlinkEvent e) {    	
	if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) 
           linkActivated(e.getURL());
    }

    /**
     * Follows the reference in an
     * link.  The given url is the requested reference.
     * By default this calls <a href="#setPage">setPage</a>,
     * and if an exception is thrown the original previous
     * document is restored and a beep sounded.  If an 
     * attempt was made to follow a link, but it represented
     * a malformed url, this method will be called with a
     * null argument.
     *
     * @param u the URL to follow
     */
    protected void linkActivated(URL u) {
    	String datosPartida[];
    	String blancasNegras="";
    	String entrada="";
	Cursor c = html.getCursor();
	Cursor waitCursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	
	html.setCursor(waitCursor);
	if(u.toString().indexOf("CHAMADALOCALVERPARTIDAS")>0)
	{
	  datosPartida=new String[6];
	  entrada=u.toString().substring(u.toString().indexOf("CHAMADALOCALVERPARTIDAS")+23);
	  try
          {
  	    datosPartida[0]=entrada.substring(0,entrada.indexOf("#"));
	    entrada = entrada.substring(entrada.indexOf("#")+1);
	    datosPartida[1]=entrada.substring(0,entrada.indexOf("#"));
	    entrada = entrada.substring(entrada.indexOf("#")+1);
	    datosPartida[2]=entrada.substring(0,entrada.indexOf("#"));
	    entrada = entrada.substring(entrada.indexOf("#")+1);
	    datosPartida[5]=entrada.substring(0,entrada.indexOf("#"));	    
	    entrada = entrada.substring(entrada.indexOf("#")+1);
	    blancasNegras=entrada.substring(0,entrada.indexOf("#"));	    
          }catch(Exception e) {}
	  
          if(salon.frmVerPartidas==null)
          { 

            salon.ajedrezVer = new AjedrezVerPartidas(salon,"","",
                                                      "ANDRES","MAXIMO","b",
                                                      "600","600","10",0,""); 	        	
            salon.ajedrezVer.setBounds(0,0,salon.getBounds().width,salon.getBounds().height-20);
            salon.frmVerPartidas = new JFrame("CiberChess - "+salon.idioma.traducir("Ver Partidas"));
            salon.frmVerPartidas.setIconImage( salon.icono );
            salon.frmVerPartidas.setBounds(salon.con.getBounds());
            salon.frmVerPartidas.getContentPane().setBackground(salon.colorSalon);
            //frmVerPartidas.setLocationRelativeTo(getFrame());
            salon.frmVerPartidas.getContentPane().setLayout(null);
            salon.frmVerPartidas.getContentPane().add(salon.ajedrezVer);  
          }
          
          if(datosPartida[5].equals(""))
            salon.ajedrezVer.mostrarPartida(blancasNegras,datosPartida,true);
          else
            salon.ajedrezVer.mostrarPartida(blancasNegras,datosPartida,false);
          salon.frmVerPartidas.show();
	        
	  html.setCursor(c);
	}
	else
	  SwingUtilities.invokeLater(new PageLoader(u, c));
    }

    /**
     * temporary class that loads synchronously (although
     * later than the request so that a cursor change
     * can be done).
     */
    class PageLoader implements Runnable {
	
	PageLoader(URL u, Cursor c) {
	    url = u;
	    cursor = c;
	}

        public void run() {
	    if (url == null) {
		// restore the original cursor
		html.setCursor(cursor);

		// PENDING(prinz) remove this hack when 
		// automatic validation is activated.
		Container parent = html.getParent();
		parent.repaint();
	    } else {
		Document doc = html.getDocument();
		try {
		    html.setPage(url);
		} catch (IOException ioe) {
		    html.setDocument(doc);
		    getToolkit().beep();
		} finally {
		    // schedule the cursor to revert after
		    // the paint has happended.
		    url = null;
		    SwingUtilities.invokeLater(this);
		}
	    }
	}

	URL url;
	Cursor cursor;
    }





}