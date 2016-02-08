

class TemporizadorPintar implements Runnable 
{
    private Thread thread = null;
    private PanelDibujo panel = null;
    boolean seguir = true;
    
    TemporizadorPintar( PanelDibujo pan ) 
    { 
	  panel = pan;
    }
    


    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
    	thread.interrupt();
    	seguir = false;
    }
    

    public void run() 
    {
 
        while( seguir )  
        {
	   if((panel.pa.nombre).substring(1,2).compareTo(new String("b"))==0){
		if((panel.pa.nombre).compareTo(new String("pb"))==0) {
			panel.pa.imagen = panel.ajedrez.db;
			panel.pa.nombre = "db";
              
		}
		else if((panel.pa.nombre).compareTo(new String("db"))==0) {
			panel.pa.imagen = panel.ajedrez.tb;
			panel.pa.nombre = "tb";
		}
		else if((panel.pa.nombre).compareTo(new String("tb"))==0) {
			panel.pa.imagen = panel.ajedrez.ab;
			panel.pa.nombre = "ab";
		}
		else if((panel.pa.nombre).compareTo(new String("ab"))==0) {
			panel.pa.imagen = panel.ajedrez.cb;
			panel.pa.nombre = "cb";
		}
		else if((panel.pa.nombre).compareTo(new String("cb"))==0) {
			panel.pa.imagen = panel.ajedrez.db;
			panel.pa.nombre = "db";
		}
	   }
	   else 
	   {
		if((panel.pa.nombre).compareTo(new String("pn"))==0) {
			panel.pa.imagen = panel.ajedrez.dn;
			panel.pa.nombre = "dn";
             
		}
		else if((panel.pa.nombre).compareTo(new String("dn"))==0) {
			panel.pa.imagen = panel.ajedrez.tn;
			panel.pa.nombre = "tn";
		}
		else if((panel.pa.nombre).compareTo(new String("tn"))==0) {
			panel.pa.imagen = panel.ajedrez.an;
			panel.pa.nombre = "an";
		}
		else if((panel.pa.nombre).compareTo(new String("an"))==0) {
			panel.pa.imagen = panel.ajedrez.cn;
			panel.pa.nombre = "cn";
		}
		else if((panel.pa.nombre).compareTo(new String("cn"))==0) {
			panel.pa.imagen = panel.ajedrez.dn;
			panel.pa.nombre = "dn";
		}
	   }
	   panel.pintarEenviar(false,true);
         try 
         {
                esperar(1500);
         } catch( InterruptedException e2 ) {return;}

        }   
             
    }


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        }   
    
}

