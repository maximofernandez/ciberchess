import java.awt.*;
import java.applet.Applet;
import java.net.*;
import java.io.*;

public class Xadrez extends Applet implements Runnable {
    int frameNumber = -1;
    Thread animatorThread;
    int tam = 0;
    float tamFloat=0;
    int retardo = 10;
    float incremento;
    int grados = 0;
    int numeroAnimacion = 1,avance;
    String blanco;
    Dimension offDimension;
    Image imagenOculta;
    Graphics offGraphics;
    int tamanhoApplet=0;

    public void init() {
	URL url;
	String entrar;

	// Obtener parametros para el applet
      setBackground(Color.black);
	paintComponents(getGraphics());
	blanco = getParameter("blanco");
	entrar = getParameter("entrar");
	retardo = (Integer.valueOf(getParameter("retardo"))).intValue();
	if ( entrar.compareTo(new String("si")) == 0 ) {
		try { 
			url = new URL ("http://cgi.tripod.com/brocochess/cgi-bin/entrar.pl");
				(getAppletContext()).showDocument (url, "_self");
		}catch(MalformedURLException e) {
			 System.out.println( "Error en URL" ); 
		}catch(IOException e) {
			 System.out.println( "Error al abrir partida" ); 
		} 
	}
	tamanhoApplet = getSize().width/20*20;
	incremento = (int)(tamanhoApplet/20);

    }

    public void start() {
        //Iniciar la animación
        if (animatorThread == null) {
            animatorThread = new Thread(this);
        }
        animatorThread.start();
    }

    public void stop() {
        //Stop the animating thread.
        animatorThread = null;

        //Get rid of the objects necessary for double buffering.
        offGraphics = null;
        imagenOculta = null;
    }

    public void run() {
	  int iteracion = 90;
	  int nuevoNumeroAnimacion;
        float incremento2 = 5+tamanhoApplet*5/900;

        //guardar el tiempo al iniciar
        long tiempoInicio = System.currentTimeMillis();

        //BUCLE DE ANIMACION
        while (Thread.currentThread() == animatorThread) {
		if (iteracion == 90) {
			nuevoNumeroAnimacion = ((int)(Math.random()*15));
			if (nuevoNumeroAnimacion >= 5 &&
			    nuevoNumeroAnimacion <= 8)
			  nuevoNumeroAnimacion = ((int)(Math.random()*15));
			if (nuevoNumeroAnimacion == numeroAnimacion)
				numeroAnimacion = (numeroAnimacion + 1) % 15;
			else numeroAnimacion = nuevoNumeroAnimacion;
			if (numeroAnimacion == 11 ||
			    numeroAnimacion == 12)
			  numeroAnimacion = 0;
			if (numeroAnimacion == 13 ||
			    numeroAnimacion == 14)
			  numeroAnimacion = 10;
			iteracion = 0;
			grados = 0;
			tamFloat = tamanhoApplet/10;
			incremento = -(float)tamanhoApplet/10/45;
			incremento2 = (float)tamanhoApplet/10/45*5;
		}
		
		iteracion++;

		if (numeroAnimacion == 0) {
              //modificar el tamaño de casilla del tablero segun sea zoom para acercar o no
              tamFloat += incremento;
              tam=(int)tamFloat;

		  if (tam > ((int)(tamanhoApplet/10))) tam = (int)(tamanhoApplet/10);
		  if (tam < 0) incremento = (float)tamanhoApplet/10/45;
		}

		if (numeroAnimacion == 1) {
			grados+=4;
			if (grados == 360) grados = 0;
		}

		if (numeroAnimacion == 2) {
			grados+=4;
			if (grados == 360) grados = 0;
		}

		if (numeroAnimacion == 3) {
			grados+=4;
			if (grados == 360) grados = 0;
		}

		if (numeroAnimacion == 4) {
			grados+=4;
			if (grados == 360) grados = 0;
		}

		if (numeroAnimacion == 5) {
			avance = iteracion*tamanhoApplet/40;
			if (iteracion > 45) avance = 90*tamanhoApplet/40 - avance;
		}

		if (numeroAnimacion == 6) {
			avance = iteracion*tamanhoApplet/40;
			if (iteracion > 45) avance = 90*tamanhoApplet/40 - avance;
		}

		if (numeroAnimacion == 7) {
			avance = iteracion*tamanhoApplet/40;
			if (iteracion > 45) avance = 90*tamanhoApplet/40 - avance;
		}

		if (numeroAnimacion == 8) {
			avance = iteracion*tamanhoApplet/40;
			if (iteracion > 45) avance = 90*tamanhoApplet/40 - avance;
		}
		if (numeroAnimacion == 9) {
              //modificar el tamaño de casilla del tablero segun sea zoom para acercar o no
              tamFloat += incremento;
              tam=(int) tamFloat;              

		  if (tam > ((int)(tamanhoApplet/10))) tam = (int)(tamanhoApplet/10);
		  if (tam < 0) incremento = (float)tamanhoApplet/10/45;
		}

		if (numeroAnimacion == 10) {
              //modificar el tamaño de casilla del tablero segun sea zoom para acercar o no
              tamFloat += incremento2;
              tam= (int)tamFloat;

		  if (tam > ((int)(tamanhoApplet/2))) incremento2 = -(float)tamanhoApplet/10/45*5;
		  if (tam < ((int)(tamanhoApplet/10))) tam = (int)(tamanhoApplet/10);
		}


              //mostrar el tablero con estos nuevos parámetros
            repaint();

            //Retardo entre fotograma y fotograma
            try {
                tiempoInicio += retardo;
                Thread.sleep(Math.max(0, tiempoInicio - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        boolean fillSquare;
        boolean fillNextFrame;
        int rowWidth = 0;
        int x , y , z, t;
        int w, h, i, j;
        int tmp;
	  int ancho;
	  int inicio;

        //crear la imagen oculta si es que no existe
        if ( (offGraphics == null)
          || (d.width != offDimension.width)
          || (d.height != offDimension.height) ) {
            offDimension = d;
            imagenOculta = createImage(d.width, d.height);
            offGraphics = imagenOculta.getGraphics();
        }

        //Borrar la imagen previa a blanco o negro segun parámetro
        offGraphics.setColor(Color.black);
	  if (blanco.compareTo(new String("si")) == 0 ) {
		 offGraphics.setColor(Color.white);
	  }
        offGraphics.fillRect(0, 0, d.width, d.height);

	if (numeroAnimacion == 0) {

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i < 8;i++) {
	  	   for (j = (i + 1) % 2; j < 8; j += 2) {
			offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam+i*tam,
	                                 ((tamanhoApplet-tam*10)/2)+ tam+j*tam, tam, tam);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=0; i < 8;i++) {
	  	   for (j = i % 2; j < 8; j += 2) {
			offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam+i*tam,
	                                 ((tamanhoApplet-tam*10)/2)+ tam+j*tam, tam, tam);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2), tam*10, tam);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2), tam, tam*10);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2)+tam*9, tam*10, tam);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam*9,
					   ((tamanhoApplet-tam*10)/2), tam, tam*10);
	  }

	  if (numeroAnimacion == 1) {
		ancho = Math.abs((int)(tamanhoApplet*Math.cos(grados*3.1416/180)));
		inicio = (tamanhoApplet-ancho)/2;
		tam = tamanhoApplet/10;

		offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i <= 9;i++) {
	  	   for (j = 0; j<=9; j++) {
			switch( i ) {
		        case 0:
            		offGraphics.setColor(new Color(235, 218, 184));
            		break;
        		  case 9:
            		offGraphics.setColor(new Color(235, 218, 184));
            		break;
        		  default:
				switch( j ) {
		        		case 0:
            				offGraphics.setColor(new Color(235, 218, 184));
            				break;
        		  		case 9:
            				offGraphics.setColor(new Color(235, 218, 184));
            				break;
        		  		default:
					   if (Math.cos(grados*3.1416/180) >= 0) {
						if ((i+j)%2 == 0)
			            		offGraphics.setColor(Color.white);
						else
							offGraphics.setColor(new Color(205, 186, 158));
            			   }
					   else {
						if ((i+j)%2 == 1)
			            		offGraphics.setColor(Color.white);
						else
							offGraphics.setColor(new Color(205, 186, 158));
            			   }
					   break;
       			}
            		break;
       		}
			offGraphics.fillRect(inicio+ancho*i/10,
	                                 j*tam,(ancho*(i+1)/10)-ancho*i/10, tam);
		   }
		}
		
	  }

	  if (numeroAnimacion == 2) {
		ancho = Math.abs((int)(tamanhoApplet*Math.cos(grados*3.1416/180)));
		inicio = (tamanhoApplet-ancho)/2;
		tam = tamanhoApplet/10;

		offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i <= 9;i++) {
	  	   for (j = 0; j<=9; j++) {
			switch( i ) {
		        case 0:
            		offGraphics.setColor(new Color(235, 218, 184));
     				break;
        		  case 9:
            		offGraphics.setColor(new Color(235, 218, 184));
     				break;
        		  default:
				switch( j ) {
		        		case 0:
            				offGraphics.setColor(new Color(235, 218, 184));
     						break;
        		  		case 9:
            				offGraphics.setColor(new Color(235, 218, 184));  
     						break;
        		  		default:
					   if (Math.cos(grados*3.1416/180) >= 0) {
						if ((i+j)%2 == 0)
			            		offGraphics.setColor(Color.white);
						else
							offGraphics.setColor(new Color(205, 186, 158));
            			   }
					   else {
						if ((i+j)%2 == 1)
			            		offGraphics.setColor(Color.white);
						else
							offGraphics.setColor(new Color(205, 186, 158));
            			   }
					   break;
       			}
            		break;
       		}
			offGraphics.fillRect(j*tam,inicio+ancho*i/10,
	                                 tam,(ancho*(i+1)/10)-ancho*i/10);
		   }
		}
		
	  }

	  if (numeroAnimacion == 3) {
		ancho = Math.abs((int)(tamanhoApplet/10*Math.cos(grados*3.1416/180)));
		inicio = (tamanhoApplet/10-ancho)/2;
		tam = tamanhoApplet/10;

		offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i <= 9;i++) {
	  	   for (j = 0; j<=9; j++) {
			switch( i ) {
		        case 0:
            		offGraphics.setColor(new Color(235, 218, 184));
				offGraphics.fillRect(j*tam ,i*tam + inicio,
		                                 tam, ancho);
            		break;
        		  case 9:
            		offGraphics.setColor(new Color(235, 218, 184));
				offGraphics.fillRect(j*tam ,i*tam + inicio,
		                                 tam, ancho);
            		break;
        		  default:
				switch( j ) {
		        		case 0:
            				offGraphics.setColor(new Color(235, 218, 184));
						offGraphics.fillRect(j*tam + inicio,i*tam,
		      		                           ancho, tam);
	            			break;
        		  		case 9:
            				offGraphics.setColor(new Color(235, 218, 184));
						offGraphics.fillRect(j*tam + inicio,i*tam,
		      		                           ancho, tam);
     						break;
        		  		default:
						if ((i+j)%2 == 1) {
							offGraphics.setColor(new Color(205, 186, 158));
							offGraphics.fillRect(j*tam + inicio,i*tam,
	      				                           ancho, tam);
						}
						else {

							offGraphics.setColor(Color.white);
							offGraphics.fillRect(j*tam ,i*tam + inicio,
	                  				               tam, ancho);
						}
					   break;
       			}
            		break;
       		}
		   }
		}		
	  }


	  if (numeroAnimacion == 4) {
		ancho = Math.abs((int)(tamanhoApplet/10*Math.cos(grados*3.1416/180)));
		inicio = (tamanhoApplet/10-ancho)/2;
		tam = tamanhoApplet/10;

		offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i <= 9;i++) {
	  	   for (j = 0; j<=9; j++) {
			switch( i ) {
		        case 0:
            		offGraphics.setColor(new Color(235, 218, 184));
				offGraphics.fillRect(j*tam ,i*tam + inicio,
		                                 tam, ancho);
            		break;
        		  case 9:
            		offGraphics.setColor(new Color(235, 218, 184));
				offGraphics.fillRect(j*tam ,i*tam + inicio,
		                                 tam, ancho);
            		break;
        		  default:
				switch( j ) {
		        		case 0:
            				offGraphics.setColor(new Color(235, 218, 184));
						offGraphics.fillRect(j*tam + inicio,i*tam,
		      		                           ancho, tam);
	            			break;
        		  		case 9:
            				offGraphics.setColor(new Color(235, 218, 184));
						offGraphics.fillRect(j*tam + inicio,i*tam,
		      		                           ancho, tam);
     						break;
        		  		default:
						if ((i+j)%2 == 1) {
			         		offGraphics.setColor(new Color(205, 186, 158));
						offGraphics.fillRect(j*tam ,i*tam + inicio,
	                  			               tam, ancho);
						}
						else {
						offGraphics.setColor(Color.white);
						offGraphics.fillRect(j*tam + inicio,i*tam,
	                  			               ancho, tam);
						}
					   break;
       			}
            		break;
       		}
		   }
		}		
	  }

	if (numeroAnimacion == 5) {
		tam = tamanhoApplet/10;
		ancho = tamanhoApplet;

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=1; i < 9;i++) {
	  	   for (j = (i + 1) % 2; j < 9; j += 2) {
				x = i * tam + avance;
				y = j * tam;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=1; i < 9;i++) {
	  	   for (j = i % 2; j < 9; j += 2) {
				x = i * tam + avance;
				y = j * tam;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));

			x = avance;
			y = 0;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);

			x = avance;
			y = tam;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = ancho-tam + avance;
			y = tam;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = avance;
			y = ancho-tam;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);	
	  }

	if (numeroAnimacion == 6) {
		tam = tamanhoApplet/10;
		ancho = tamanhoApplet;

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=1; i < 9;i++) {
	  	   for (j = (i + 1) % 2; j < 9; j += 2) {
				x = i * tam - avance;
				y = j * tam;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=1; i < 9;i++) {
	  	   for (j = i % 2; j < 9; j += 2) {
				x = i * tam - avance;
				y = j * tam;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));

			x = 0 - avance;
			y = 0;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0 - avance;
			y = tam;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = ancho-tam - avance;
			y = tam;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0 - avance;
			y = ancho-tam;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);	
	  }

	if (numeroAnimacion == 7) {
		tam = tamanhoApplet/10;
		ancho = tamanhoApplet;

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=1; i < 9;i++) {
	  	   for (j = (i + 1) % 2; j < 9; j += 2) {
				x = i * tam;
				y = j * tam- avance;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=1; i < 9;i++) {
	  	   for (j = i % 2; j < 9; j += 2) {
				x = i * tam;
				y = j * tam - avance;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));

			x = 0;
			y = 0 - avance;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0;
			y = tam - avance;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = ancho-tam;
			y = tam - avance;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0;
			y = ancho-tam - avance;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);	
	  }

	if (numeroAnimacion == 8) {
		tam = tamanhoApplet/10;
		ancho = tamanhoApplet;

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=1; i < 9;i++) {
	  	   for (j = (i + 1) % 2; j < 9; j += 2) {
				x = i * tam;
				y = j * tam+ avance;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=1; i < 9;i++) {
	  	   for (j = i % 2; j < 9; j += 2) {
				x = i * tam;
				y = j * tam + avance;
				z = tam;
				t = tam;
				offGraphics.fillRect(x,y,z,t);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));

			x = 0;
			y = 0 + avance;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0;
			y = tam + avance;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = ancho-tam;
			y = tam + avance;
			z = tam;
			t = ancho-tam-tam;
			offGraphics.fillRect(x,y,z,t);

			x = 0;
			y = ancho-tam + avance;
			z = ancho;
			t = tam;
			offGraphics.fillRect(x,y,z,t);	
	  }

	if (numeroAnimacion == 9) {

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i < 8;i++) {
	  	   for (j = (i + 1) % 2; j < 8; j += 2) {
			offGraphics.fillRect((tamanhoApplet/10-tam)/2+(i+1)*tamanhoApplet/10,
	                                 (tamanhoApplet/10-tam)/2+(j+1)*tamanhoApplet/10,
                                        tam, tam);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=0; i < 8;i++) {
	  	   for (j = i % 2; j < 8; j += 2) {
			offGraphics.fillRect((tamanhoApplet/10-tam)/2+(i+1)*tamanhoApplet/10,
	                                 (tamanhoApplet/10-tam)/2+(j+1)*tamanhoApplet/10,
                                        tam, tam);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   (tamanhoApplet-tam*10)/20, tam*10, tam);
		offGraphics.fillRect((tamanhoApplet-tam*10)/20,
					   ((tamanhoApplet-tam*10)/2)+tamanhoApplet/10,
                                 tam, tam*8);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   (tamanhoApplet-tam*10)/20+9*tamanhoApplet/10,
                                 tam*10, tam);
		offGraphics.fillRect((tamanhoApplet-tam*10)/20+9*tamanhoApplet/10,
					   ((tamanhoApplet-tam*10)/2)+tamanhoApplet/10, tam, tam*8);

	  }

	if (numeroAnimacion == 10) {

		/* pintar casillas de color */
	       offGraphics.setColor(new Color(205, 186, 158));
		 for (i=0; i < 8;i++) {
	  	   for (j = (i + 1) % 2; j < 8; j += 2) {
			offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam+i*tam,
	                                 ((tamanhoApplet-tam*10)/2)+ tam+j*tam, tam, tam);
		   }
		}
		/* pintar casilla blancas */
	      offGraphics.setColor(Color.white);
		for (i=0; i < 8;i++) {
	  	   for (j = i % 2; j < 8; j += 2) {
			offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam+i*tam,
	                                 ((tamanhoApplet-tam*10)/2)+ tam+j*tam, tam, tam);
		   }
		}

		/* pintar los bordes del tablero */
		offGraphics.setColor(new Color(235, 218, 184));
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2), tam*10, tam);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2), tam, tam*10);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2),
					   ((tamanhoApplet-tam*10)/2)+tam*9, tam*10, tam);
		offGraphics.fillRect(((tamanhoApplet-tam*10)/2)+tam*9,
					   ((tamanhoApplet-tam*10)/2), tam, tam*10);
	  }


        //Mostrar la imagen en pantalla
        g.drawImage(imagenOculta, 0, 0, this);
    }
}
