import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.Runtime;
import java.lang.reflect.Method;

public class ServidorComunicacionSalon 
{
  Vector comunicaciones = new Vector();     /* vector donde almacenar los TalkServerThread */
  static String puerto;
  static String carpetaPHP;
  static int nivelLog;
  Socket xogadorSocket = null;
  ServidorComunicacionSalonThread sct = null;
  ThreadGroup grupoEscritores=new ThreadGroup("grupoEscritores");
  ThreadGroup grupoEnviarRecibirBD=new ThreadGroup("grupoEnviarRecibirBD");
  ThreadGroup grupoSCTs=new ThreadGroup("grupoSCTs");
  Vector vectorEscritores = new Vector();
  LiberarMemoria liberarMemoria;

  FileOutputStream out=null;
  File f;
  String nombreFicheiro;
  BufferedWriter bos;
  
  boolean torneoCreadoNosUltimos25Minutos=false;
  Vector vectorTorneos = new Vector();
  
  
  public static void main(String[] args) 
  {
    try
    {
      carpetaPHP = new String(args[0]);
      puerto = new String(args[1]);
      nivelLog = Integer.valueOf(new String(args[2])).intValue();
    }
    catch(Exception e)
    {
      System.out.println(e);
      System.out.println("java ServidorComunicacionSalon carpetaPHP puerto nivelLog");
      System.out.println("nivelLog: 0-solo erros 1-erros+chat 2-erros+entradas+retornosBD 3-erros+entradas+retornosBD+saidas");
      return;
    }
    new ServidorComunicacionSalon().start();
  }

  public void start() 
  {
    int diaUltimoLog,diaActual;
    Calendar calendario = new GregorianCalendar();
    ServerSocket serverRSocket = (ServerSocket)null;
    
    crearAbrirFicheiroLog();

    try 
    {
      serverRSocket = new ServerSocket(Integer.valueOf(puerto).intValue());
      System.out.println("Servidor de comunicaciones SALON activo  en el puerto: " + serverRSocket.getLocalPort());
    }catch (IOException e) 
    {
      System.err.println("El servidor de comunicaciones SALON no se puede activar en el puerto " + puerto);
      return;
    }

    liberarMemoria= new LiberarMemoria(this);
    liberarMemoria.start();
    
    (new TemporizadorConectados(this,comunicaciones,carpetaPHP,puerto)).start();
    
    (new TemporizadorHora(this,1000*60*1)).start();
    
    diaUltimoLog=calendario.get(Calendar.DAY_OF_MONTH);
    
    (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                  null,
                                                  carpetaPHP+"TorneosCrear.php?sala=ciberchess&a="+((int)(Math.random()*100000)),
                                                  "",
                                                  "")).start();    

    while (true) 
    {
      try
      {            
        //Escuchar si entra algún rival para conectar
        xogadorSocket = serverRSocket.accept();

        if(Runtime.getRuntime().freeMemory()>10000000)
        {
          //crear un thread para controlar esta comunicacion
          sct = new ServidorComunicacionSalonThread(xogadorSocket, this);
          sct.start();
        }

        sct=null; 
        xogadorSocket=null;
        calendario = new GregorianCalendar();
        diaActual=calendario.get(Calendar.DAY_OF_MONTH);
        if(diaActual != diaUltimoLog) //detecta cambio de dia para crear novo Log
        {
          crearAbrirFicheiroLog();
          diaUltimoLog=diaActual;
        }
      } catch (Exception e) 
      {
        //System.out.println(e.getMessage());
      }

    }


  }


  public void crearAbrirFicheiroLog()
  {
    
    try  //cerramos o anterior se é que está aberto
    {
	if (out!=null) 
	{
		out.close();
	}
    } catch (IOException e1) 
    {
	System.out.println(e1);
    }    

    nombreFicheiro = "./sc"+instante()+".log";

    try 
    {
    	f = new File(nombreFicheiro);
        out=new FileOutputStream(f);
        DataOutputStream os = new DataOutputStream(out);
        bos=new BufferedWriter(new OutputStreamWriter(os));			
    } 
    catch (IOException e2) 
    {
	System.err.println(e2);
	return;
    }
  }
  
  
  public void escribirLog(String strlog, int nivel)
  {
     if(nivelLog>=nivel)
        try  //Escritura no ficheiro de log
        {
          bos.write(strlog,0,strlog.length());
          bos.newLine();
          bos.flush();
        } 
        catch(Exception e1){}  
  }
  
  
  public void eliminarPartidasAbandonada(String login, String salaXogador)  
  {
    (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                  null,
                                                  carpetaPHP+"partidasXogador.php?login="+login+"&sala="+salaXogador+"&a="+((int)(Math.random()*100000)),
                                                  "",
                                                  "postEliminarPartidasAbandonada")).start();
  	
  }


  //recorrer tódalas partidas que ten para ver en cada unha se o rival tamén
  //se desconectou e eliminala  
  public void postEliminarPartidasAbandonada(String entrada)
  {
    String login;
    String salaXogador;
    String loginRival;
    
    login=entrada.substring(0,entrada.indexOf("#"));
    entrada = entrada.substring(entrada.indexOf('#')+1);
    salaXogador=entrada.substring(0,entrada.indexOf("#"));
    entrada = entrada.substring(entrada.indexOf('#')+1);
  	
    while(entrada.length()>0)
    {
      loginRival=entrada.substring(0,entrada.indexOf("#"));
      entrada = entrada.substring(entrada.indexOf('#')+1);
      if(!existeXogador(loginRival,salaXogador)) //el desconectouse e o rival tamén
        (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                      null,
                                                      carpetaPHP+"partidaAbandonada.php?login="+login+"&sala="+salaXogador+"&loginRival="+loginRival+"&a="+((int)(Math.random()*100000)),
                                                      "",
                                                      "")).start();
    }     
  }
  

  public void consultarHora()  
  {
    //if (comunicaciones.size()>0) 
     (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                   null,
                                                   carpetaPHP+"consultarHora.php"+"?a="+((int)(Math.random()*100000)),
                                                   "",
                                                   "postConsultarHora")).start();
  	
  }

 
  public void postConsultarHora(String entrada)
  {
    TemporizadorTorneo tt;
    int hora=0;
    int minuto=0;
    int segundo=0;
    int ano;
    int mes;
    int dia;
    
    try
    {
      hora = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      minuto = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      segundo = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);    	
      enviarCadena("C1#23#"+hora+"#"+minuto+"#"+segundo,null,"",true); //enviar a todos, incluso a si mesmo
    }
    catch (Exception e) {}

    //crear torneo se estamos dentro dos dez minutos antes da hora en punto, controlando que non se creara ningún
    //torneo nos últimos 25 minutos para evitar crear dous, polas inexactitudes de reló
    try
    {
      ano = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      mes = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      dia = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();            
      if(minuto>=25 && minuto<=35)
        torneoCreadoNosUltimos25Minutos=false;
      else if(minuto>=50 && !torneoCreadoNosUltimos25Minutos)
      {
      	torneoCreadoNosUltimos25Minutos=true;
      	hora+=1;
      	if (hora==24)
      	  hora = 0;
      	tt=new TemporizadorTorneo(this,(((59-minuto)*60+(60-segundo))*1000),35000,hora,ano,mes,dia);
      	vectorTorneos.add(tt);
      	tt.start();
        (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                      null,
                                                      carpetaPHP+"TorneosCrear.php?sala=ciberchess&a="+((int)(Math.random()*100000)),
                                                      "",
                                                      "")).start();      	
      }
    }
    catch (Exception e) {}   
    
    
    //chamar a programa php que elimina partidas que se saben abandonadas por superar o tempo máximo de xogo posible
    //tamén o mesmo cos torneos
    (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                  null,
                                                  carpetaPHP+"partidasAbandonadas.php?&a="+((int)(Math.random()*100000)),
                                                  "",
                                                  "")).start();     
    
  }
  
  
  public void torneoCrearRondaPaso1(int hora,int ano,int mes,int dia)  
  {
   (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                 null,
                                                 carpetaPHP+"TorneosListaApuntados.php?sala=ciberchess&torneohora="+hora+"&ano="+ano+"&mes="+mes+"&dia="+dia+"&a="+((int)(Math.random()*100000)),
                                                 "",
                                                 "postTorneosListaApuntados")).start();  
  }
  
  
  public void postTorneosListaApuntados(String entrada)
  {
    String login;
    int i;
    ServidorComunicacionSalonThread sct;
    boolean encontrado;
    int hora=0,ano=0,mes=0,dia=0;
    int xogadoresApuntados = 0;
    String crearaseRonda="";
    
    try
    {
      crearaseRonda = entrada.substring(0,entrada.indexOf("#"));
      entrada = entrada.substring(entrada.indexOf("#")+1);     	
    }catch(Exception e){}
      
    if(crearaseRonda.equals("SI"))
    {	
      try
      {
        hora = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1); 
        ano = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1);
        mes = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1);
        dia = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1);                     
        while(entrada.length()>0)
        {
          login = entrada.substring(0,entrada.indexOf("#"));
          entrada = entrada.substring(entrada.indexOf("#")+1);

  	  encontrado = false;
          for (i=0; i < comunicaciones.size() && encontrado==false; i++) 
          {
            sct = (ServidorComunicacionSalonThread)comunicaciones.elementAt(i);
            if ((sct.loginXogador).compareTo(login)==0)
              encontrado = true;
          }
          if(encontrado)
            xogadoresApuntados++;
          else
          {
            (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                          null,
                                                          carpetaPHP+"TorneosEliminarApuntado.php?sala=ciberchess&torneohora="+hora+"&login="+login+"&ano="+ano+"&mes="+mes+"&dia="+dia+"&a="+((int)(Math.random()*100000)),
                                                          "",
                                                          "")).start();           
          }
        }
        if(xogadoresApuntados>1)
        {
          try
          {
            esperar(2000); //Esperamos para que dea tempo ó mysql a eliminar os xogadores, senón execútase concurrente
          } catch(Exception e){}
          torneoCrearRondaPaso2(hora,ano,mes,dia);
        }
        else
          buscarTemporizadorTorneo(hora).pararCreacionRondas();        
      }
      catch (Exception e) {}
    }    
  }    
  

  public void torneoCrearRondaPaso2(int hora,int ano,int mes,int dia)  
  {
   (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                 null,
                                                 carpetaPHP+"TorneosCrearRonda.php?sala=ciberchess&torneohora="+hora+"&ano="+ano+"&mes="+mes+"&dia="+dia+"&a="+((int)(Math.random()*100000)),
                                                 "",
                                                 "postTorneoCrearRondaPaso2")).start();
  }
  
  
  public void postTorneoCrearRondaPaso2(String entrada)
  {
    String blancas,negras;
    int hora,numRonda,ritmo,incrementos;
    boolean realmenteCreouseRonda=false;
    
    try
    {
      hora = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      numRonda = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();    	
      entrada = entrada.substring(entrada.indexOf("#")+1);
      ritmo = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
      entrada = entrada.substring(entrada.indexOf("#")+1);
      incrementos = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();    	
      entrada = entrada.substring(entrada.indexOf("#")+1);      
      if(numRonda>=5)
        buscarTemporizadorTorneo(hora).pararCreacionRondas();
      while(entrada.length()>0)
      {
      	realmenteCreouseRonda=true;
        blancas = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        negras = entrada.substring(0,entrada.indexOf("#"));
        entrada = entrada.substring(entrada.indexOf("#")+1);
        if(negras.equals("")) //o xogador de blancas descansa, son impares
        {
          enviarCadena("C1#1#<SISTEMA>Ronda|"+numRonda+"|del|torneo|de|las|"+hora+":00,|el|número|de|jugadores|es|impar|y|le|toca|descansar,|se|da|la|partida|por|ganada]",null,blancas,true,"ciberchess");
        }
        else
        {
          enviarCadena("C1#6#"+blancas+"]"+negras+"]"+ritmo+"]"+incrementos+"]",null,blancas,true,"ciberchess"); 
          enviarCadena("C1#6#"+blancas+"]"+negras+"]"+ritmo+"]"+incrementos+"]",null,negras,true,"ciberchess");
        }  
      }
      if(realmenteCreouseRonda) //mensaje ó chat publico da sala ciberchess
        enviarCadena("C1#1#<SISTEMA>Se|inician|las|partidas|de|la|ronda|"+numRonda+"|del|torneo|de|las|"+hora+":00]",null,"",true,"ciberchess");
    }
    catch (Exception e) {}
  }  


  public void comprobarFinalizacionTorneo(int hora,int ano,int mes,int dia)  
  {
    //System.out.println("comprobarFinalizacionTorneo: "+carpetaPHP+"TorneosComprobarFinalizacion.php?sala=ciberchess&torneohora="+hora+"&ano="+ano+"&mes="+mes+"&dia="+dia+"&a="+((int)(Math.random()*100000)));
   (new ServidorComunicacionSalonEnviarRecibirBD(this,
                                                 null,
                                                 carpetaPHP+"TorneosComprobarFinalizacion.php?sala=ciberchess&torneohora="+hora+"&ano="+ano+"&mes="+mes+"&dia="+dia+"&a="+((int)(Math.random()*100000)),
                                                 "",
                                                 "postComprobarFinalizacionTorneo")).start();
  	
  }
  
  
  public void postComprobarFinalizacionTorneo(String entrada)
  {
    int hora;
    
    try
    { 
      if(entrada.length()>0)  //sabemos que finalizou
      {
      	hora = Integer.valueOf(entrada.substring(0,entrada.indexOf("#"))).intValue();
        entrada = entrada.substring(entrada.indexOf("#")+1);
        buscarTemporizadorTorneo(hora).destruir();
        enviarCadena("C1#1#<SISTEMA>"+entrada,null,"",true,"ciberchess");
      }
    }
    catch (Exception e) {}
   
    
  }  
  
  
  TemporizadorTorneo buscarTemporizadorTorneo(int hora)
  {
    int i;
    TemporizadorTorneo tt;
  	
    for (i=0; i < vectorTorneos.size(); i++) 
    {
      tt = (TemporizadorTorneo)vectorTorneos.elementAt(i);
      if (tt.hora==hora)
        return tt;
    } 
    
    return null;    
  }
  

  public boolean existeXogador(String xogador,String salaXogador)
  {
    int i;
    ServidorComunicacionSalonThread sct;
  	
    for (i=0; i < comunicaciones.size(); i++) 
    {
      sct = (ServidorComunicacionSalonThread)comunicaciones.elementAt(i);
      if ((sct.loginXogador).compareTo(xogador)==0 &&
        (sct.salaXogador).compareTo(salaXogador)==0)
        return true;
    } 
    
    return false;
  }  
  
  
  public String instante()
  {
    String cadea;
  	
    Calendar calendario = new GregorianCalendar();
       
    cadea = String.valueOf(calendario.get(Calendar.YEAR));
    cadea = cadea+dousDixitos(calendario.get(Calendar.MONTH)+1);
    cadea = cadea+dousDixitos(calendario.get(Calendar.DAY_OF_MONTH));
    cadea = cadea+dousDixitos(calendario.get(Calendar.HOUR_OF_DAY));
    cadea = cadea+dousDixitos(calendario.get(Calendar.MINUTE));
    cadea = cadea+dousDixitos(calendario.get(Calendar.SECOND));
    
    return cadea;
  }
  
  
  private String dousDixitos(int num)
  {
    String cadea;
    
    cadea=String.valueOf(num);
    if(cadea.length()==1)
      cadea = "0"+cadea;
    return cadea;  
  }
  
  


  public void enviarCadena(String string, ServidorComunicacionSalonThread origen, String loginDestino) 
  {
    enviarCadena(string, origen, loginDestino, false);
  }
  
  public void enviarCadena(String string, ServidorComunicacionSalonThread origen, String loginDestino, boolean permiteAutoenviar) 
  {
    enviarCadena(string, origen, loginDestino, permiteAutoenviar,null);
  } 

  public void enviarCadena(String string, ServidorComunicacionSalonThread origen, String loginDestino, boolean permiteAutoenviar, String salaEspecifica) 
  {
    enviarCadena(string, origen, loginDestino, permiteAutoenviar,salaEspecifica,false);
  }   

  public void enviarCadena(String string, ServidorComunicacionSalonThread origen, String loginDestino, boolean permiteAutoenviar, String salaEspecifica, boolean soloAutoenviar) 
  {
    int i;
    ServidorComunicacionSalonThread sct;
    boolean enviar;
    EscribirAStream escritor;

    //System.out.println("enviarCadena: "+String);

    if(Runtime.getRuntime().freeMemory()<10000000)  //se andamos xustos de memoria cancelamos envio
      return;

    for (i=0; i < comunicaciones.size(); i++) 
    {
      try
      {
        sct = (ServidorComunicacionSalonThread)comunicaciones.elementAt(i);
        
        //facer previamente os envios especiales de VER PARTIDAS
        //System.out.println("verXogador1: "+sct.verXogador1);
        //System.out.println("verXogador2: "+sct.verXogador2);
        
        if (origen!=null &&
            string.substring(0,4).compareTo("C1#7")==0 &&
            (string.indexOf("]11")>0 || string.indexOf("]16")>0 ) &&
            sct.salaXogador.compareTo(origen.salaXogador)==0 &&   //so se envia na mesma sala
            (sct.verXogador1.compareTo(origen.loginXogador)==0 &&
             sct.verXogador2.compareTo(loginDestino)==0 ||
             sct.verXogador2.compareTo(origen.loginXogador)==0 &&
             sct.verXogador1.compareTo(loginDestino)==0) )
          if (sct.sct_correcto())
          {
            if (sct.numeroEscritores>10) //se o sct chegou os 10 escritores non lle poñemos mais cancelase o envio para el
              continue;
            
            if(string.indexOf("]11")>0)
            {
              String movemento=string.substring(string.indexOf("]11")+2);
              escritor=new EscribirAStream(sct,"C1#12#1]"+loginDestino+"]"+origen.loginXogador+"]"+movemento);
            }
            else //so pode ser un ]16 de mensaxe fin de partida para AjedrezVerPartidas
            {
              String texto=string.substring(string.indexOf("]16")+2);
              escritor=new EscribirAStream(sct,"C1#12#2]"+loginDestino+"]"+origen.loginXogador+"]"+texto);
            }            
            vectorEscritores.add(escritor);
            escritor.start();
            sct.numeroEscritores++;
            escritor=null;
          }

        //facer os envios normais(non de ver partidas)
        enviar = true;
		if (!permiteAutoenviar && sct == origen) //Non enviar por donde veu
		  enviar = false;
		if (soloAutoenviar && sct != origen) //Solo enviar por donde veu, usarase para os accesos a base de datos
		  enviar = false;  
		if(origen!=null)  
		  if (sct.salaXogador.compareTo(origen.salaXogador)!=0) //so se envia na mesma sala, se o orixe está a null pode ser o envio da hora que se fai a todos
			enviar = false;
		if(salaEspecifica!=null)
		  if (sct.salaXogador.compareTo(salaEspecifica)!=0)
			enviar = false;
		if (loginDestino.length()>0 && (sct.loginXogador).compareTo(loginDestino)!=0)
		  enviar = false;  //Enviar a todos ou personalizado
		if (string.substring(0,4).compareTo("C1#7")==0 && string.indexOf("]16")>0 )
		  enviar = false; //Non enviar ó rival cando é unha mensaxe de fin de partida para AjedrezVerPartidas
		if (string.length()>5 && string.substring(0,6).compareTo("C1#20#")==0 && sct != origen) //Cando se pide autenticación enviar só polo sct orixe que fixo conexión e ahora vai facer autenticación
		  enviar = false;
		if (enviar) 
		{
			  if (sct.sct_correcto())
			  {
				if (sct.numeroEscritores>50)
				  continue;
				escritor=new EscribirAStream(sct,string);
				vectorEscritores.add(escritor);
				escritor.start();
				sct.numeroEscritores++;
				escritor=null;
			  }
		  else 
		  {
			if (sct != null) 
			{
			  comunicaciones.removeElement(sct);
			  sct.finalize();
				  i--;
			}
		  }
        }
      } catch (Exception e){}
    }
  }  


    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        } 

}




class ServidorComunicacionSalonThread implements Runnable
{
  Thread thread;
  public Socket socket;
  public DataInputStream is;
  public BufferedReader bis;
  public DataOutputStream os;
  public BufferedWriter bos;
  ServidorComunicacionSalon server;
  String loginXogador="#",salaXogador="ciberchess",eloXogador,numeroBandera,tempoIncCol="A|10]1]Bla";
  boolean nonMarcarDesconectado=false;
  boolean ocupado=false;
  int numeroEscritores=0;
  String verXogador1="",verXogador2="";
  Vector mensaxesPendientes=new Vector();
  String ip;
  Receptor receptor1,receptor2;
  boolean conectado=false;
  boolean autenticado=false;
  boolean validado=false; //variable para evitar envios mentras non estea validado, eso usabano hackers para falsear autodesconexión do rival
  long numeroSecreto=(long) (Math.random()*31991);
  boolean invitado=false;    
  boolean VIP; 

  ServidorComunicacionSalonThread(Socket socket, ServidorComunicacionSalon server) throws IOException 
  {
    is = new DataInputStream(socket.getInputStream());
    bis=new BufferedReader(new InputStreamReader(is));
    os = new DataOutputStream(socket.getOutputStream());
    bos=new BufferedWriter(new OutputStreamWriter(os));

    if (is == null) 
    {
      throw new IOException();
    }

    if (os == null) {
      throw new IOException();
    }

    this.socket = socket;
    this.server = server;
    ip=socket.getInetAddress().getHostAddress();
  }

  public void start() 
  {
    thread = new Thread( server.grupoSCTs,this );
    thread.start();
  }

  public void run() 
  {

    escribirAStream("Conexion establecida.",bos);

    if (socket != null) 
    {
    	
      receptor1 = new Receptor(this);
      receptor2 = new Receptor(this);
      receptor1.start();

    }
    else
      finalize();

  }


  public void procesarMensaxe(String str)
  {
        int i,posicion,posicion2,posicion3,posicion4,posicion5;
        ServidorComunicacionSalonThread sct;
        String loginDestino;
	
  	
        if (str.length()>6 && str.substring(0,7).compareTo("C1##30#")==0)
          server.escribirLog(server.instante()+" ERRO "+ip+" "+str,0);  //Escritura no ficheiro de log ERROS
        else if (str.length()>5 && str.substring(0,6).compareTo("C1##1#")==0)
          server.escribirLog(server.instante()+" E "+ip+" "+str,1);  //Escritura no ficheiro de log CHAT PUBLICO
        else if (str.indexOf("#7#")>0 && str.indexOf("]12")>0)
          server.escribirLog(server.instante()+" E "+ip+" "+str,1);  //Escritura no ficheiro de log CHAT PRIVADO
        else
          server.escribirLog(server.instante()+" E "+ip+" "+str,2);  //Escritura no ficheiro de log
          
        if (str.length()>6 && str.substring(0,7).compareTo("C1##24#")==0) //xogador modifica tempoIncCol
        {
          tempoIncCol=str.substring(str.indexOf("]")+1); 
        }  
        
        if (str.length()>6 && str.substring(0,7).compareTo("C1##17#")==0) //xogador modifica ELO
        {
          eloXogador=str.substring(str.indexOf("]")+1); 
        }        

        //System.out.println("procesarMensaxe: "+str);
        if (str != null) 
        {
          if(!conectado)
          {
            posicion=-1;
            if (str.length()>18)
              posicion=str.substring(0,18).indexOf("MAXIMOS1");
            if(posicion>-1) 
            {
              String listaConectados="";
              posicion2 = str.indexOf("MAXIMOS2");
              posicion3 = str.indexOf("MAXIMOS3");
              posicion4 = str.indexOf("MAXIMOS4");
              posicion5 = str.indexOf("MAXIMOS5");
              loginXogador = str.substring(posicion+8,posicion2);
              if(loginXogador.equals(""))
              {
                cleanup();
                return;
              }
              eloXogador =  str.substring(posicion2+8,posicion3);
              salaXogador =  str.substring(posicion3+8,posicion4);
              // =  str.substring(posicion4+8,posicion5);  //Posicion libre para futuros usos

              if(loginXogador.compareTo("INVITADO")==0 ||
                 loginXogador.compareTo("GUEST")==0)
              {
                //ver que usuario de invitado lle toca
                loginXogador = invitadoLibre(loginXogador);
                invitado=true;
              }
              else
                invitado=false; //cando se reconecta un invitado xa non se considera invitado pq non hai que buscar nome de invitado libre

              //Añade al vector todas las conexiones          
              server.comunicaciones.addElement((ServidorComunicacionSalonThread)this);              
              
              for (i=0; i < server.comunicaciones.size(); i++) 
              {
                sct = (ServidorComunicacionSalonThread)server.comunicaciones.elementAt(i);
                if ((sct.salaXogador).compareTo(salaXogador)==0 &&
                    (sct.loginXogador).compareTo(loginXogador)!=0) //confeccionar lista de conectados
                {
                  if(sct.loginXogador.compareTo("ADMINISTRADOR")!=0)
                    listaConectados=listaConectados+"C1#10#"+sct.loginXogador+"]"+sct.eloXogador+"]"+sct.numeroBandera+"]"+sct.tempoIncCol;
                }
              } 
                
              if(loginXogador.length()>0) //asegurome de que enviou nome xogador, pq senon envio a todos
              {                
                //autoenviarse lista de conectados
                server.enviarCadena(listaConectados,this,loginXogador,true);

                conectado=true;
                server.enviarCadena("C1#20#"+String.valueOf(numeroSecreto)+"]"+ip,this,loginXogador,true); //Pedirlle que se autentique e enviarlle ip (a sua propia ip)
              }
            }
          }
          else if(!autenticado)
          {
            if(str.length()>6 && str.substring(0,7).compareTo("C1##20#")==0) // Comprobar que o que chega é mensaxe de autenticacion
            {
              //System.out.println("Número enviado: "+numeroSecreto);
              if((str.substring(str.indexOf("C1##20#")+7)).equals(String.valueOf((numeroSecreto*59999)%31991)))
              {
              	
                autenticado=true;
                
                server.enviarCadena("C1#21#",this,loginXogador,true); //Confirmarlle autenticado
                
                if(invitado)
                {
                  //autoenviarse que usuario de invitado lle toca
                  escribirAStream("C1#11#"+loginXogador,bos);
                }                
                
              }
              else
                server.enviarCadena("C1#20#"+String.valueOf(numeroSecreto)+"]"+ip,this,loginXogador,true); //Pedirlle que se autentique e enviarlle ip (a sua propia ip)
            }
            else
              { 
              	//Se a mensaxe non e de autenticación descartámola
              }
          }
          else if(str.length()>1 && (str.substring(0,2)).compareTo(new String("C1")) ==0) 
          {
            //System.out.println(str);
            str = str.substring(str.indexOf("#")+1);
            loginDestino = str.substring(0,str.indexOf("#"));
            str = str.substring(str.indexOf("#")+1);
            str = "C1#" + str;

            if(str.substring(0,5).compareTo("C1#20")==0)
            {//chega unha autenticacion cando xa está autenticado, polo tanto non facer nada
            }//porque se se envia considerano unha petición os demais
            else if(str.substring(0,5).compareTo("C1#30")==0) // Mensaxe de depuración para que quede no log, non facer nada
            {
            }
            else if(str.substring(0,5).compareTo("C1#22")==0) // Avisa de que xa está validado, eliminaremos as conexións anteriores
            {                                                 // Avisamos de que se conectou e xa temos tamen o num. de bandera
              validado=true;
              str = str.substring(6);
              str = str.substring(str.indexOf("]")+1);
              eloXogador = str.substring(0,str.indexOf("]"));
              str = str.substring(str.indexOf("]")+1);
              numeroBandera = str.substring(0,str.indexOf("]"));
              if (str.substring(str.indexOf("]")+1).equals("VIPSI"))
                VIP = true;
              else
                VIP = false;              
              
              //avisar de que se conectou
              if(loginXogador.compareTo("ADMINISTRADOR")!=0)
                 server.enviarCadena("C1#2#"+loginXogador+"]"+eloXogador+"]"+numeroBandera+"]"+tempoIncCol,this,"");              
              
              //si existe una comunicación anterior con este jugador se elimina
              for (i=0; i < server.comunicaciones.size(); i++) 
              {
                sct = (ServidorComunicacionSalonThread)server.comunicaciones.elementAt(i);
                if ((sct.loginXogador).compareTo(loginXogador)==0 &&
                    (sct.salaXogador).compareTo(salaXogador)==0 &&
                     sct != (ServidorComunicacionSalonThread)this) 
                {
                  server.comunicaciones.removeElement(sct);
                  sct.sairSinMarcarDesconectado();
                  i--;
                }
              } 
            }                           
            else if(validado && str.substring(0,5).compareTo("C1#13")==0) // Anotarse para VER PARTIDA
            {
              verXogador1 = loginDestino;
              verXogador2 = str.substring(str.indexOf("C1#13#")+6);
            }
            else if(validado && str.substring(0,5).compareTo("C1#14")==0) // Borrarse para VER PARTIDA
            {
              verXogador1 = "";
              verXogador2 = "";
            }
            else if( (validado  || str.indexOf("loginValido.php")>=0) && //mentras non esté validado só pode chamar a loginValido.php
			         str.substring(0,5).compareTo("C1#15")==0) // Chamada a programa PHP
            {
              String cadeaPHP;
              String hashCodigo;
              String nombreFuncion;
              
              //System.out.println("SCT: "+str);
              
              str = str.substring(6);
              cadeaPHP = str.substring(0,str.indexOf("]"));
              str = str.substring(str.indexOf("]")+1);
              hashCodigo = str.substring(0,str.indexOf("]"));
              nombreFuncion = str.substring(str.indexOf("]")+1);
              (new ServidorComunicacionSalonEnviarRecibirBD(server,
                                                            this,
                                                            server.carpetaPHP+cadeaPHP,
                                                            hashCodigo,
                                                            nombreFuncion)).start();

            }            

            //DEPURACION
            else if(str.compareTo("C1#1#<ADMINISTRADOR>AYUDA]")==0)
            {
              str="C1#1#<SISTEMA>AYUDA: información sobre los comandos disponibles]";
              str=str+"<SISTEMA>INFORMAME: informa sobre uso de memoria y objetos]";
              str=str+"<SISTEMA>SCTS: lista de jugadores conectados a objetos ServidorComunicacionSalonThread]";
              str=str+"<SISTEMA>EXPULSAR JUGADOR: expulsa a un jugador de la sala]";
              server.enviarCadena(str,this,loginXogador,true);
            }
            //else if (str.length()>26 && str.substring(0,27).compareTo("C1#1#<ADMINISTRADOR>MENSAJE")==0)
            //enviarCadena(C1#8#,this,str.substring(28),true);
            else if (str.length()>27 && str.substring(0,28).compareTo("C1#1#<ADMINISTRADOR>EXPULSAR")==0)
              server.enviarCadena("C1#9#",this,str.substring(29,str.length()-1),true);
            else if (str.compareTo("C1#1#<ADMINISTRADOR>INFORMAME]")==0)
            {
              str="C1#1#<SISTEMA>Memoria libre:"+Runtime.getRuntime().freeMemory()+"]";
              str=str+"<SISTEMA>Memoria total:"+Runtime.getRuntime().totalMemory()+"]";
              str=str+"<SISTEMA>Memoria usada:"+(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())+"]";
              str=str+"<SISTEMA>SCT's Vector:"+server.comunicaciones.size()+"]";
              str=str+"<SISTEMA>SCT's Grupo:"+server.grupoSCTs.activeCount()+"]";
              str=str+"<SISTEMA>Escritores Vector:"+server.vectorEscritores.size()+"]";
              str=str+"<SISTEMA>Escritores Grupo:"+server.grupoEscritores.activeCount()+"]";
              str=str+"<SISTEMA>EnviarRecibirBD:"+server.grupoEnviarRecibirBD.activeCount()+"]";
              server.enviarCadena(str,this,loginXogador,true);
            }
            else if (str.compareTo("C1#1#<ADMINISTRADOR>SCTS]")==0)
            {
              str="C1#1#";
              for (i=0; i < server.comunicaciones.size(); i++) 
              {
                sct = (ServidorComunicacionSalonThread)server.comunicaciones.elementAt(i);
                str=str+"<SISTEMA>"+sct.loginXogador+"]";
              } 
              server.enviarCadena(str,this,loginXogador,true);
            }
            else
            {
              if(validado)
                server.enviarCadena(str,this,loginDestino);
            }
	  }
        }
  }


  public synchronized boolean escribirAStream(String tira, BufferedWriter bos) 
  {
    String cadea;
    int descontarEscritores;
    
    try 
    { 
      if(ocupado)
      {
        mensaxesPendientes.addElement(tira);  
        return true;
      }
      ocupado=true;

      server.escribirLog(server.instante()+" S "+ip+" "+tira,3);  //Escritura no ficheiro de log

      //System.out.println("MENSAXE ENVIADO: "+tira);
      bos.write(tira,0,tira.length());
      tira=null;
      bos.newLine();
      bos.flush();
      wait(400);
      numeroEscritores--;
      while(mensaxesPendientes.size()>0)
      {
      	cadea="";
      	descontarEscritores=0;
      	if(((String)mensaxesPendientes.elementAt(0)).substring(0,2).compareTo("C1")!=0)
      	{
      	  cadea=(String)mensaxesPendientes.elementAt(0);
      	  mensaxesPendientes.removeElementAt(0);
      	  descontarEscritores++;
      	}
      	else
      	{
      	  while(mensaxesPendientes.size()>0 &&
      	        ((String)mensaxesPendientes.elementAt(0)).substring(0,2).compareTo("C1")==0)
      	  {
      	    cadea = cadea + (String)mensaxesPendientes.elementAt(0);
      	    mensaxesPendientes.removeElementAt(0);
      	    descontarEscritores++;
      	  }
      	}
        server.escribirLog(server.instante()+" S "+ip+" "+cadea,3);  //Escritura no ficheiro de log
    	
        bos.write(cadea,0,cadea.length());
        bos.newLine();
        bos.flush();
        wait(400);
        numeroEscritores -= descontarEscritores;
      	  
      }
    } catch (Exception e) 
    {
      //System.out.println(e.getMessage());
      ocupado=false;
      return false;
    } 
    ocupado=false;
    return true;
  }


  public boolean sct_correcto() 
  {

    if (this.bos == null)
      return false;
    if (this.bis == null) 
      return false;
    if (this.socket == null)
      return false;

    return true;
  }


  public void sairSinMarcarDesconectado()
  {
    nonMarcarDesconectado=true;
    finalize();
  }

  public void finalize() 
  {
    try
    {
      escribirAStream("S1Problemas con el socket",this.bos);
    }
    catch(Exception e){}
    cleanup();
    thread=null;
  }

  synchronized void cleanup() 
  {
    int i;

    try 
    {
      if (is != null) 
      {
        is.close();
        is = null;
      }
    } catch (Exception e) {}

    try 
    {
      if (bis != null) 
      {
        bis.close();
        bis = null;
      }
    } catch (Exception e) {}

    try 
    {
      if (os != null) 
      {
        os.close();
        os = null;
      }
    } catch (Exception e) {}

    try 
    {
      if (bos != null) 
      {
        bos.close();
        bos = null;
      }
    } catch (Exception e) {}

    try 
    {
      if (socket != null) 
      {
        socket.close();
        socket = null;
      }
    } catch (Exception e) {}

/*
    try 
    {
      receptor1 = null;
      receptor2 = null;
    } catch (Exception e) {}    
*/

    try
    {
      for (i=0;i<52;i++)  //Pode haber ata 50 escritores dormidos que despertar
        notifyAll();
    } catch (Exception e) 
    {
      //System.out.println(e.getMessage());
    }
  }
  
  
  private synchronized String invitadoLibre(String tipoInvitado)
  {
    int primeiro,i;
    
    primeiro = (int) (Math.random()*100)+1;
    for(i=primeiro;i<=100;i++)
      if(!server.existeXogador(tipoInvitado + i,salaXogador))
        return tipoInvitado + i;
    for(i=1;i<primeiro;i++)
      if(!server.existeXogador(tipoInvitado + i,salaXogador))
        return tipoInvitado + i;
    
    return tipoInvitado;
  }

  
  public void recibirBD(String entrada,String hashCodigo,String nombreFuncion)
  {
    server.enviarCadena("C1#16#"+entrada+"]"+hashCodigo+"]"+nombreFuncion,this,loginXogador,true,null,true);  //só se envia polo sct que o pediu
  }

}


class EscribirAStream implements Runnable
{
  ServidorComunicacionSalonThread sct;
  String cadea;
  Thread thread;
  long tempoInicial=System.currentTimeMillis();
  ServidorComunicacionSalon server;

  EscribirAStream(ServidorComunicacionSalonThread sct,String cadea)
  {
    this.sct=sct;
    this.cadea=cadea;
    server=sct.server;
  }

  public void start() 
  {
    thread = new Thread( sct.server.grupoEscritores,this );
    thread.start();
  }

  public void stop()
  {
  }

  public void run()  
  {
   try
   {
    if(sct.escribirAStream(cadea, sct.bos)==false)
      if (!sct.sct_correcto())
        if (sct != null) 
        {
          sct.server.comunicaciones.removeElement(sct);
          sct.finalize();
          sct=null;
	}
   } catch(Exception e){}
   server.vectorEscritores.removeElement(this);
   sct=null;
   cadea=null;
   thread=null;
   server=null;
  }
}


class Receptor implements Runnable 
{
  ServidorComunicacionSalonThread sct;
  String received;
  BufferedReader bis;
  private Thread thread = null;


  Receptor(ServidorComunicacionSalonThread sct)
  {
    this.sct = sct;
    this.bis=sct.bis;
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
      //Esperando para leer lo que llegue del jugador
                
      received = bis.readLine();

    }
    catch (Exception e) 
    { 
        sct.server.enviarCadena("S1Problemas con el socket",(ServidorComunicacionSalonThread)null,sct.loginXogador,true);
                
        sct.cleanup(); 
    }

    if (received != null ) 
    {
      //System.out.println("RECEPTOR: "+received);
      //CREA UN RECEPTOR PARA A SEGUINTE FRASE
			
      sct.receptor1 = sct.receptor2;
      sct.receptor1.start();
      sct.receptor2 = new Receptor(sct);

      sct.procesarMensaxe(received);
    }
    else if (sct.socket!=null)
      sct.cleanup();
    
    if(sct.socket==null)
    {
      if(sct.loginXogador.compareTo("ADMINISTRADOR")!=0 &&
         !sct.nonMarcarDesconectado &&
         sct.loginXogador.compareTo("#")!=0 &&
         sct.validado) //se ainda non validara non avisamos da desconexión porque esto o aproveitan para simular desconexións de outros PENSADOR E CARLITOS22
        sct.server.enviarCadena("C1#3#"+sct.loginXogador,sct,"");
    
      sct.server.comunicaciones.removeElement(sct);
    
      sct.server.eliminarPartidasAbandonada(sct.loginXogador,sct.salaXogador);
    
      sct.finalize();    	
    }
  }
}



class LiberarMemoria implements Runnable
{

  Thread thread;
  ServidorComunicacionSalon server;

  LiberarMemoria(ServidorComunicacionSalon server)
  {
    this.server=server;
  }

  public void start() 
  {
    thread = new Thread( this );
    thread.start();
  }

  public void stop()
  {
  }

  public void run()  
  {
    int i;
    EscribirAStream escritor;

    while (true)
    {
      try
      {
        thread.sleep(5000);
        Runtime.getRuntime().gc();
        /*
        for (i=0; i < server.vectorEscritores.size(); i++) 
        {
          try
          {
            escritor = (EscribirAStream)server.vectorEscritores.elementAt(i);
            if(System.currentTimeMillis()-escritor.tempoInicial>10000)
            {
              server.vectorEscritores.removeElement(escritor);
              i--;
              escritor.sct.finalize();
              escritor.sct=null;
              
            }
          } catch (Exception e) {}
        }
        */
      } catch(Exception e)
      {
        //System.out.println(e.getMessage());
      }
      escritor=null;

    }
  }
}



/*******************************************************************************************/
/* TEMPORIZADOR QUE ANOTA PERIODICAMENTE O NÚMERO DE CONECTADOS EN CONFIGURACIÓN     */
/***********************************************************************************/
class TemporizadorConectados implements Runnable 
{
    Thread thread = null;
    Vector comunicaciones = null;
    String carpetaPHP;
    String portoTCP;
    ServidorComunicacionSalon server;
    
    public TemporizadorConectados(ServidorComunicacionSalon server, Vector comunicaciones, String carpetaPHP, String portoTCP ) 
    { 
    	this.server=server;
        this.comunicaciones = comunicaciones;
        this.carpetaPHP=carpetaPHP;
        this.portoTCP=portoTCP;
    }
    

    // Aqui creamos un nuevo thread para correr el lector. Lo inicializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
    }


    public void run() 
    {
      URL url;
      String entrada=new String("");
      int c,intervalo=300000,i,conectados,conectadosNoVIP;
      ServidorComunicacionSalonThread sct;

      do
      {
        try {

          conectados=0;
          conectadosNoVIP=0;
          for (i=0; i < comunicaciones.size(); i++) 
          {
            sct = (ServidorComunicacionSalonThread)comunicaciones.elementAt(i);
            if(sct.salaXogador.equals("ciberchess"))
            {
              conectados++;
              if (sct.VIP==false) //sumar conectados non VIP
                conectadosNoVIP++;
            }    
          } 
        	
          (new ServidorComunicacionSalonEnviarRecibirBD(server,null,
             carpetaPHP+"conectados.php?conectados="+conectados+"&conectadosNoVIP="+conectadosNoVIP+"&sala=ciberchess&portoTCP="+portoTCP+
                "&a="+((int)(Math.random()*100000))
             ,"","")).start();	


          conectados=0;
          conectadosNoVIP=0;
          for (i=0; i < comunicaciones.size(); i++) 
          {
            sct = (ServidorComunicacionSalonThread)comunicaciones.elementAt(i);
            if(sct.salaXogador.equals("english"))
            {
              conectados++;
              if (sct.VIP==false) //sumar conectados non VIP
                conectadosNoVIP++;
            }    
          } 
        	
          (new ServidorComunicacionSalonEnviarRecibirBD(server,null,
             carpetaPHP+"conectados.php?conectados="+conectados+"&conectadosNoVIP="+conectadosNoVIP+"&sala=english&portoTCP="+portoTCP+
                "&a="+((int)(Math.random()*100000))
             ,"","")).start();


            esperar(intervalo);

        }
        catch(Exception e) 
        {}

      } while (true);
  
    }    

    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        } 

}



/*************************************/
/* TEMPORIZADOR QUE SIRVE A HORA     */
/*************************************/
class TemporizadorHora implements Runnable 
{
    Thread thread = null;
    int intervalo;
    ServidorComunicacionSalon server;
    
    public TemporizadorHora(ServidorComunicacionSalon server,int intervalo) 
    { 
    	this.server=server;
    	this.intervalo=intervalo;
    }
    

    // Aqui creamos un nuevo thread para correr el lector. Lo inicializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
    }


    public void run() 
    {


      do
      {
        try {

            server.consultarHora();

            esperar(intervalo);

        }
        catch(Exception e) 
        {}

      } while (true);
  
    }    

    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        } 

}



/********************************************************/
/* TEMPORIZADOR QUE CONTROLA A EXECUCIÓN DUN TORNEO     */
/********************************************************/
class TemporizadorTorneo implements Runnable 
{
    Thread thread = null;
    int intervalo;
    ServidorComunicacionSalon server;
    long tempoInicial=System.currentTimeMillis();
    int hora,ano,mes,dia;
    int retardoInicial;
    boolean noMasRondas = false;
    
    public TemporizadorTorneo(ServidorComunicacionSalon server,int retardoInicial,int intervalo,int hora,int ano,int mes,int dia) 
    { 
    	this.server=server;
    	this.intervalo=intervalo;
    	this.hora=hora;
    	this.ano=ano;
    	this.mes=mes;
    	this.dia=dia;
    	this.retardoInicial=retardoInicial;
    }
    

    // Aqui creamos un nuevo thread para correr el lector. Lo inicializamos
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
 
    public void destruir() 
    {
       server.vectorTorneos.removeElement(this);
       server=null;
       stop();
    }


    public void run() 
    {

      try
      {

        esperar(retardoInicial);      	
        
        server.torneoCrearRondaPaso1(hora,ano,mes,dia);
        
      } catch(Exception e) 
      {}

      do
      {
        try 
        {
        	
            //System.out.println("TemporizadorTorneo "+ano+" "+mes+" "+dia+" "+hora+": BEEP");

            esperar(intervalo);
            
            if(!noMasRondas)
              server.torneoCrearRondaPaso1(hora,ano,mes,dia);            
            else
              server.comprobarFinalizacionTorneo(hora,ano,mes,dia);

        }
        catch(Exception e) 
        {}

      } while (Thread.currentThread() == thread);
  
    }

    
    public long tempoDeVida()
    {
      return System.currentTimeMillis()-tempoInicial;
    }
    
    
    public void pararCreacionRondas()
    {
      noMasRondas=true;
    }
    

    private synchronized void esperar( int lapso ) 
        throws InterruptedException {
        this.wait( lapso );
        } 

}






/*******************************************************************************************/
/* NOVO OBXETO PARA ENVIAR/RECIBIR DA BASE DE DATOS SEN BLOQUEOS, PORQUE VAI NUN THREAD DIFERENTE     */
/***********************************************************************************/
class ServidorComunicacionSalonEnviarRecibirBD implements Runnable {
    Thread thread = null;
    String cadeaPHP,hashCodigo,nombreFuncion;
    int maxIntentos=6,numIntentos=0;
    boolean activo = true;
    ServidorComunicacionSalonThread sct;
    ServidorComunicacionSalon sc;
    
    public ServidorComunicacionSalonEnviarRecibirBD( ServidorComunicacionSalon sc, ServidorComunicacionSalonThread sct, String cadeaPHP, String hashCodigo, String nombreFuncion ) 
    { 
    	this.sc = sc;
        this.sct = sct;
        this.cadeaPHP=cadeaPHP;
        this.hashCodigo=hashCodigo;
        this.nombreFuncion = nombreFuncion;
    }
    

    // Aqui creamos un nuevo thread para correr el lector. Lo inicializamos
    // con "this" de forma que el metodo run() se llame inmediatamente
    // como comience la ejecucion del thread
    public void start() 
    {
        thread = new Thread( this );
        thread.start();
    }
 
    public void stop() 
    {
    }


    public void run() 
    {
      boolean valido=false;
      URL url;
      String entrada=new String("");

      do
      {
        if(numIntentos<999)
          numIntentos++;
        try 
        {
          url = new URL(cadeaPHP);
          
          //System.out.println("ServidorComunicacionSalonEnviarRecibirBD cadeaPHP: "+cadeaPHP);

          BufferedReader server = new BufferedReader(new InputStreamReader(url.openStream()));
          String line;
          entrada=new String("");
          while((line = server.readLine()) != null)
            entrada = entrada+line;
          server.close();

          //System.out.println("ServidorComunicacionSalonEnviarRecibirBD entrada: "+entrada);

          if(entrada.length()>=6)
            if(entrada.substring(0,6).compareTo("MAXIMO")==0)
            {
              valido = true;
              entrada = entrada.substring(6);
            }
        }
        catch(Exception e) 
        {
          sc.escribirLog(sc.instante()+" ERRO ServidorComunicacionSalonEnviarRecibirBD "+" "+e+" "+cadeaPHP,0);  //Escritura no ficheiro de log 
        }

      } while (!valido && numIntentos<maxIntentos);

      if (valido)
      {
        if(nombreFuncion.length()>0) //senon é unha chamada sen retorno
        {
          sc.escribirLog(sc.instante()+" RETORNOBD "+" "+entrada+" "+hashCodigo+" "+nombreFuncion,2);  //Escritura no ficheiro de log
          if(nombreFuncion=="postEliminarPartidasAbandonada") // é unha chamada local
            sc.postEliminarPartidasAbandonada(entrada);
          if(nombreFuncion=="postConsultarHora") // é unha chamada local
            sc.postConsultarHora(entrada);
          if(nombreFuncion=="postTorneoCrearRondaPaso2") // é unha chamada local
            sc.postTorneoCrearRondaPaso2(entrada);
          if(nombreFuncion=="postComprobarFinalizacionTorneo") // é unha chamada local
            sc.postComprobarFinalizacionTorneo(entrada); 
          if(nombreFuncion=="postTorneosListaApuntados") // é unha chamada local
            sc.postTorneosListaApuntados(entrada);                                       
          if(sct!=null) //chamouno un sct
            sct.recibirBD(entrada,hashCodigo,nombreFuncion);
        }
      }

      thread = null;
      activo = false;
  
    }    

}
