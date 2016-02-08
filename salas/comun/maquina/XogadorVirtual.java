

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class XogadorVirtual extends Applet
    implements Runnable, MouseListener
{

   Maquina maquina;
   int nivel;



    public XogadorVirtual(Maquina maq, int nivel)
    {
	  maquina = maq;
	  this.nivel = nivel;
    }

    public void init()
    {

        idioma = 0;
        archError = "../imagenes/error.jpg";
        archDerrota = "../imagenes/derrota.jpg";
        archTriunfo = "../imagenes/triunfo.jpg";
        congifs = false;
        conTiempo = true;
        tiempoTranscurrido = new long[2];
        cuantoFijo = 0L;
        tiempoTotal = tiempos[3] * 60000L;
        indiceCuantosMin = 3;
        elTiempoAntes = -1L;
        ultimaActR = 0L;
        murioTiempoU = false;
        murioTiempoC = false;
        mostrarultimacompu = false;
        condibujos = true;
        queCorona = 0;
        posini = 0;
        posfin = 0;
        fase_mouse = 0;
        dondex = 0;
        dondey = 0;
        InicioDeTodo = true;
        bandoU = 0;
        orientacion = 0;
        eval_est = 0;
        cualBan = 0;
        concualcolor = 0;
        Marcomp = false;
        ev = false;
        finDelJuego = false;
        ganoCom = false;
        ganoUsu = false;
        empate = false;
        ocupado = false;
        errorDelUsuario = false;
        clickNecesario = false;
        dibShock = false;
        dibFelicidad = false;
        yaSeMostroUI = false;
        renovarFlechaNivel = false;
        repasarNomas = false;
        cargarImagenes = true;
        animacionMarco = false;
        dospersonas = false;
        juegoDeColores = 0;
        cuantosJDC = 4;
        paraEnmarcar = Color.red;
        unasolavez = true;
        piezas = new Image[2][7];



        addMouseListener(this);
        enmarcarBlancas = true;
        String s = "0";
        if(s == null || !s.equals("0") && !s.equals("1"))
            idioma = 0;
        else
            idioma = Integer.parseInt(s);
	  s="1";
        if(s == null || s.equals("0"))
        {
            congifs = false;
        } else
        {
            congifs = true;
            condibujos = false;
        }
        setBackground(new Color(150,102,79));
        //altura = 320;
        altura=maquina.tamanhoPieza*8;
        //largo = 320;
        largo = maquina.tamanhoPieza*8;
        porteCas = altura / 8;
        porteTablero = porteCas * 8;
        dondeminx = largo / 2 - 25;
        dondeminy = altura / 2;
        dondemasminx = dondeminx - 15;
        dondemasminy = dondeminy;
        dondemenosminx = dondeminx - 30;
        dondemenosminy = dondeminy;
        dondebandox = dondeminx - 7;
        dondebandoy = dondeminy + 30;
        dondeempezarx = dondeminx - 2;
        dondeempezary = dondebandoy + 55 + porteCas;
        dondeevalx = porteTablero + 10;
        dondeevaly = 80;
        donderetrox = porteTablero + 10;
        donderetroy = 150;
        dondeespx = porteTablero + 5;
        dondeespy = 170;
        dondedospersonasx = porteTablero + 23;
        dondedospersonasy = 190;
        dondeconevalx = porteTablero + 15;
        dondeconevaly = dondeespy + 70;
        dondeconlibrox = porteTablero + 15;
        dondeconlibroy = dondeespy + 85;
        dondecambiacolorx = porteTablero + 10;
        dondecambiacolory = dondeespy + 125;
        dondefotosx = largo - 38;
        dondefotosy = dondecambiacolory;
        dondetdnx = porteTablero + 4;
        dondetdny = altura - 6;
        dondemenfx = dondeespx;
        dondemenfy = dondeespy;
        compu = new maleitor();
        compu.invencible.idioma = idioma;
        xdc = porteTablero + 20;
        ydc = porteTablero / 2 + 20;
        if(congifs)
        {
            piezas[0][5] = maquina.pb;
		piezas[0][2] = maquina.tb;
		piezas[0][1] = maquina.cb;
		piezas[0][3] = maquina.ab;
		piezas[0][4] = maquina.db;
		piezas[0][6] = maquina.rb;
		piezas[1][5] = maquina.pn;
		piezas[1][2] = maquina.tn;
		piezas[1][1] = maquina.cn;
		piezas[1][3] = maquina.an;
		piezas[1][4] = maquina.dn;
		piezas[1][6] = maquina.rn;
        }
        //ImError = getImage(maquina.getCodeBase(), archError);
        //ImDerrota = getImage(maquina.getCodeBase(), archDerrota);
        //ImTriunfo = getImage(maquina.getCodeBase(), archTriunfo);

	inicio();
    }

    public void inicio() {
            
	    bandoU = 0;  //blancas para el usuario

            tiempoTotal = nivel * 100000L; //trabajamos con este tiempo



 //mostrar tablero
                conTiempo = true;
                if(bandoU == 1)
                {
                    ev = true;
                    orientacion = 1;
                } else
                {
                    orientacion = 0;
                }
                InicioDeTodo = false;
                repaint();
                return;

	
    }

    public void start()
    {
        if(corriente == null)
        {
            corriente = new Thread(this);
            corriente.start();
            corriente.setPriority(10);
        }
    }

    public void run()
    {
        do
        {
            if(ocupado)
            {
                    if(elTiempoAntes != -1L)
                        tiempoTranscurrido[bandoU] += System.currentTimeMillis() - elTiempoAntes;
                    elTiempoAntes = System.currentTimeMillis();
                    int ai[] = compu.juega(1 - bandoU, tiempoTotal - tiempoTranscurrido[1 - bandoU]);
                    tiempoTranscurrido[1 - bandoU] += System.currentTimeMillis() - elTiempoAntes;
                    eval_est = evaluar();
                    compu.invencible.soloActualizarReloj = false;
                    elTiempoAntes = System.currentTimeMillis();
                    Marcomp = false;
                    if(ai[2] != 1 && ai[2] != -1 && ai[2] != 2 && ai[2] != -2)
                    {
                        ocupado = false;
                        mostrarultimacompu = true;
                        orientacion = bandoU;
                    } else
                    {
                        finDelJuego = true;
                        if(ai[2] == 1)
                        {
                            ganoCom = true;
                            ocupado = false;
                            repaint();
                        } else
                        if(ai[2] == -1)
                        {
                            ganoUsu = true;
                            ocupado = false;
                            repaint();
                        } else
                        {
                            empate = true;
                            ocupado = false;
                            repaint();
                        }
                    }
                ocupado = false;
                repaint();
            }
            try
            {
                Thread.sleep(40L);
            }
            catch(Exception exception) { }
            if(conTiempo && System.currentTimeMillis() > ultimaActR + 990L && !InicioDeTodo && !clickNecesario && !finDelJuego && !ev)
            {
                ultimaActR = System.currentTimeMillis();
                compu.invencible.soloActualizarReloj = true;
                repaint();
            } else
            if(fase_mouse == 1 && !clickNecesario)
            {
                //animacionMarco = true;
                //repaint();
            }
        } while(true);
    }

    public void paint(Graphics g)
    {
	  

        if(unasolavez)
        {
            unasolavez = false;
            try
            {
                //g.drawImage(ImError, (largo - ImError.getWidth(this)) / 2, (altura - ImError.getHeight(this)) / 2, this);
            }
            catch(Exception exception) { }
            try
            {
                //g.drawImage(ImDerrota, (largo - ImDerrota.getWidth(this)) / 2, (altura - ImDerrota.getHeight(this)) / 2, this);
            }
            catch(Exception exception1) { }
            try
            {
                //g.drawImage(ImTriunfo, (largo - ImTriunfo.getWidth(this)) / 2, (altura - ImTriunfo.getHeight(this)) / 2, this);
            }
            catch(Exception exception2) { }
        }
        if(InicioDeTodo)
        {
            g.setColor(getBackground());
            g.fillRect(0, 0, largo, altura);
            g.setColor(new Color(200, 20, 40));
            //g.drawString("A M Y A N", largo / 2 - 40, 80);
            g.setColor(Color.white);
            //g.drawString(autor[idioma], largo / 2 - 80, 115);
            //g.drawString(minutos[idioma] + " : " + tiempos[indiceCuantosMin], dondeminx, dondeminy);
            //g.drawString("-", dondemenosminx, dondemenosminy);
            //g.drawString("+", dondemasminx, dondemasminy);
            g.setColor(Colores[juegoDeColores][bandoU]);
            rellPieza(g, dondebandox, dondebandoy, rx, ry);
            g.setColor(Color.green);
            //g.drawString(empezar[idioma], dondeempezarx, dondeempezary);
            return;
        }
        if(dibFelicidad)
            try
            {
                //g.drawImage(ImTriunfo, (largo - ImTriunfo.getWidth(this)) / 2, (altura - ImTriunfo.getHeight(this)) / 2, this);
                //clickNecesario = true;
                dibFelicidad = false;
                //return;
            }
            catch(Exception exception3)
            {
                dibFelicidad = false;
            }
        if(dibShock)
            try
            {
                //g.drawImage(ImDerrota, (largo - ImDerrota.getWidth(this)) / 2, (altura - ImDerrota.getHeight(this)) / 2, this);
                //clickNecesario = true;
                dibShock = false;
                //return;
            }
            catch(Exception exception4)
            {
                dibShock = false;
            }
        if(errorDelUsuario)
            try
            {
                //g.drawImage(ImError, (largo - ImError.getWidth(this)) / 2, (altura - ImError.getHeight(this)) / 2, this);
                //clickNecesario = true;
                errorDelUsuario = false;
                //return;
            }
            catch(Exception exception5)
            {
                errorDelUsuario = false;
            }
        Color color = casClara[juegoDeColores];
        for(int i = 0; i <= 7; i++)
        {
            for(int j = 0; j <= 7; j++)
            {
                g.setColor(color);
                g.fillRect(i * porteCas, j * porteCas, porteCas, porteCas);
                color = cambiaColor(color);
            }

            color = cambiaColor(color);
        }

        g.setColor(Color.white);
        g.drawRect(0, 0, largo - 1, altura - 1);

            if(mostrarultimacompu)
            {
                g.setColor(Color.red);
                g.drawRect((compu.ultimapos % 10 - 1) * porteCas, (8 - compu.ultimapos / 10) * porteCas, porteCas - 1, porteCas - 1);
            }

        if(orientacion == 0)
        {
            for(int k = 1; k <= 8; k++)
            {
                for(int i1 = 1; i1 <= 8; i1++)
                    dibPieza(g, (i1 - 1) * porteCas, (k - 1) * porteCas, compu.jueguito.pos[(9 - k) * 10 + i1] / 10, compu.jueguito.pos[(9 - k) * 10 + i1] % 10);

            }

            if(mostrarultimacompu)
            {
                g.setColor(Color.red);
                g.drawRect((compu.ultimapos % 10 - 1) * porteCas, (8 - compu.ultimapos / 10) * porteCas, porteCas - 1, porteCas - 1);
            }
        } else
        {
            for(int l = 1; l <= 8; l++)
            {
                for(int j1 = 1; j1 <= 8; j1++)
                    dibPieza(g, (8 - j1) * porteCas, (8 - l) * porteCas, compu.jueguito.pos[(9 - l) * 10 + j1] / 10, compu.jueguito.pos[(9 - l) * 10 + j1] % 10);

            }
            if(mostrarultimacompu)
            {
                g.setColor(Color.red);
                g.drawRect((8 - compu.ultimapos % 10) * porteCas, (compu.ultimapos / 10 - 1) * porteCas, porteCas - 1, porteCas - 1);
            }
        }
        g.setColor(Color.white);
        g.drawRect(0, 0, largo - 1, altura - 1);
        //if(!ocupado)
            //maquina.getAppletContext().showStatus(compu.invencible.mensajeFinal);
        if(ev)
        {
            ocupado = true;
            fase_mouse = 0;
            ev = false;
        }
        if(repasarNomas)
        {
            repasarNomas = false;
            return;
        }
        if(ganoCom)
        {
            //ganoCompu(g);
            //return;
        }
        if(ganoUsu)
        {
            //ganoUsuario(g);
            //return;
        }
        if(empate)
        {
            //empatamos(g);
            //return;
        }
        g.setColor(Color.white);
        //g.drawString(ee[idioma], dondeevalx, dondeevaly);
        //g.drawString("" + eval_est + " / (250 = 1 p.)", dondeevalx + 15, dondeevaly + 15);
        //g.drawString(colores[idioma], dondecambiacolorx, dondecambiacolory);
        if(!ocupado)
        {
            if(dospersonas)
                g.setColor(Color.red);
            else
                g.setColor(Color.black);
            //g.drawString(forzar[idioma], dondedospersonasx, dondedospersonasy);
            g.setColor(Color.white);
            //g.drawString(retroceder[idioma], donderetrox, donderetroy);
        }
        if(compu.invencible.conEvaluaciones)
            g.setColor(Color.green);
        else
            g.setColor(Color.black);
        //g.drawString("*  " + evaluaciones[idioma], dondeconevalx, dondeconevaly);
        if(compu.conLibro)
            g.setColor(Color.green);
        else
            g.setColor(Color.black);
        //g.drawString("*  " + librito[idioma], dondeconlibrox, dondeconlibroy);
        if(congifs)
        {
            if(condibujos)
                g.setColor(Color.black);
            else
                g.setColor(Color.green);
            //g.drawString(fotos[idioma], dondefotosx, dondefotosy);
        }
        g.setColor(Color.white);
        if(ocupado)
        {
            g.setColor(Color.white);
            //g.drawString(esperaporfavor[idioma], dondeespx, dondeespy);
            if(Marcomp)
            {
                g.setColor(Color.white);
                g.drawRect(dondex, dondey, porteCas - 1, porteCas - 1);
            }
        } else
        {
            //g.drawString("<-<- " + tododenuevo[idioma], dondetdnx, dondetdny);
        }
        if(fase_mouse == 1)
        {
            g.setColor(paraEnmarcar);
            g.drawRect(dondex, dondey, porteCas - 1, porteCas - 1);
        }
    }

    public void update(Graphics g)
    {
        if(conTiempo && !InicioDeTodo && !clickNecesario && !finDelJuego)
        {
            int i = 0;
            if(ocupado)
                i = 1 - bandoU;
            else
                i = bandoU;
            long al[] = new long[2];
            if(elTiempoAntes != -1L)
                al[i] = tiempoTranscurrido[i] + (System.currentTimeMillis() - elTiempoAntes);
            else
                al[i] = 0L;
            al[1 - i] = tiempoTranscurrido[1 - i];
            int ai[] = new int[2];
            int ai1[] = new int[2];
            int ai2[] = new int[2];
            g.setColor(Color.blue);
            g.fillRect(porteTablero + 13, 15, 41, 17);
            g.fillRect(porteTablero + 13, altura - 37, 41, 17);
            for(int j = 0; j <= 1; j++)
            {
                int k = altura - 28;
                if(j != orientacion)
                    k = 25;
                if(al[j] <= tiempoTotal)
                {
                    g.setColor(Color.white);
                    ai[j] = (int)((tiempoTotal - al[j]) / 0x36ee80L);
                    ai1[j] = (int)(((tiempoTotal - al[j]) / 60000L) % 60L);
                    ai2[j] = (int)(((tiempoTotal - al[j]) / 1000L) % 60L);
                    String s = "";
                    String s1 = "";
                    if(ai1[j] < 10)
                        s = "0";
                    if(ai2[j] < 10)
                        s1 = "0";
                    //g.drawString(ai[j] + ":" + s + ai1[j] + ":" + s1 + ai2[j], porteTablero + 14, k);
                } else
                {
                    g.setColor(Color.black);
                    //g.drawString("-0:00:00", porteTablero + 14, k);
                }
            }

        }

        if(compu.invencible.soloActualizarReloj)
        {
            compu.invencible.soloActualizarReloj = false;
            if(!ev)
                return;
        }
       if(animacionMarco)
        {
            g.setColor(new Color((int)(Math.random() * 255D), (int)(Math.random() * 255D), (int)(Math.random() * 255D)));
            g.drawRect(dondex, dondey, porteCas - 1, porteCas - 1);
            animacionMarco = false;
            return;
        } else
        {
            g.setColor(getBackground());
            g.fillRect(0, 0, largo, altura);
            paint(g);
            return;
        }

    }

    public void mouseReleased(MouseEvent mouseevent)
    {

        int i = mouseevent.getX();
        int j = mouseevent.getY();





        if(!ocupado && i >= dondeconlibrox && i <= dondeconlibrox + 75 && j >= dondeconlibroy - 7 && j <= dondeconlibroy)
        {
            return;
        }
        if(ocupado)
        {
            return;
        }
        if(finDelJuego)
            return;





        if(true)
        {
            posfin = (9 - (j / porteCas + 1)) * 10 + i / porteCas + 1;
            if(orientacion == 1)
                posfin = (9 - posfin / 10) * 10 + (9 - posfin % 10);
            if(compu.jueguito.pos[posfin] != 0 && compu.jueguito.pos[posfin] / 10 == bandoU)
            {
                posini = posfin;
                dondex = (i / porteCas) * porteCas;
                dondey = (j / porteCas) * porteCas;
                repaint();
                return;
            }
            fase_mouse = 0;
            ev = compu.esValida(bandoU, posini, posfin);
            if(compu.vaACoronar)
            {
                compu.vaACoronar = false;
                compu.corona(bandoU, 4);
            }
            if(!ev)
            {
                //fase_mouse = 1;
                errorDelUsuario = true;
            }
            if(ev)
            {
                eval_est = evaluar();
                animacionMarco = false;
            }
            repaint();
            return;
	  } else
        {
            return;
        }

    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        mostrarultimacompu = false;
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if(ev)
            return;
        if(clickNecesario)
        {
            clickNecesario = false;
            if(yaSeMostroUI)
                todoDeNuevo();
            repaint();
            return;
        }
        if(finDelJuego)
        {
            fase_mouse = 0;
            todoDeNuevo();
            repaint();
            return;
        }
        if(ocupado)
        {
            posini = (9 - (j / porteCas + 1)) * 10 + i / porteCas + 1;
            if(orientacion == 1)
                posini = (9 - posini / 10) * 10 + (9 - posini % 10);
            if(compu.jueguito.pos[posini] == 0 || compu.jueguito.pos[posini] / 10 != bandoU)
            {
                return;
            } else
            {
                dondex = (i / porteCas) * porteCas;
                dondey = (j / porteCas) * porteCas;
                //fase_mouse = 1;
                Marcomp = true;
                repaint();
                return;
            }
        }
        if(i >= porteTablero && j >= dondetdny - 10)
        {
            todoDeNuevo();
            repaint();
            return;
        }
        if(finDelJuego)
            return;
        if(true)
        {
            dondex = (i / porteCas) * porteCas;
            dondey = (j / porteCas) * porteCas;
            posini = (9 - (j / porteCas + 1)) * 10 + i / porteCas + 1;
            if(orientacion == 1)
                posini = (9 - posini / 10) * 10 + (9 - posini % 10);
            if(compu.jueguito.pos[posini] == 0 || compu.jueguito.pos[posini] / 10 != bandoU)
            {
                return;
            } else
            {
                //fase_mouse++;
                return;
            }
        } else
        {
            return;
        }
    }

    public void retroceder()
    {
        if(compu.numJug > 1 || compu.numJug == 1 && bandoU == 0)
        {
            int i = compu.numJug;
            compu.tdn();
            for(int j = 1; j < i; j++)
            {
                compu.jueguito.mover(compu.jugadas[j][0][0], compu.jugadas[j][0][1], 0);
                if(compu.libro.dentroDelLibro)
                    compu.libro.actualizarLlave(compu.jugadas[j][0][0], compu.jugadas[j][0][1]);
                if(j != i - 1 || bandoU == 0)
                {
                    if(compu.libro.dentroDelLibro)
                        compu.libro.actualizarLlave(compu.jugadas[j][1][0], compu.jugadas[j][1][1]);
                    compu.jueguito.mover(compu.jugadas[j][1][0], compu.jugadas[j][1][1], 1);
                }
            }

            compu.numJug = i - 1;
        }
        animacionMarco = false;
        fase_mouse = 0;
        eval_est = evaluar();
        repaint();
    }

    public void empatamos(Graphics g)
    {
        g.setColor(Color.white);
        //g.drawString(empatoj[idioma], dondemenfx, dondemenfy);
    }

    public void ganoUsuario(Graphics g)
    {
        g.setColor(Color.white);
        //g.drawString(perdioj[idioma], dondemenfx, dondemenfy);
    }

    public void ganoCompu(Graphics g)
    {
        g.setColor(Color.white);
        //g.drawString(ganoj[idioma], dondemenfx, dondemenfy);
    }

    public void todoDeNuevo()
    {
/*
        if(!yaSeMostroUI)
        {
            if(ganoCom)
            {
                yaSeMostroUI = true;
                dibShock = true;
                return;
            }
            if(ganoUsu)
            {
                yaSeMostroUI = true;
                dibFelicidad = true;
                return;
            }
        }
        yaSeMostroUI = false;
        queCorona = 0;
        posini = 0;
        posfin = 0;
        fase_mouse = 0;
        dondex = 0;
        dondey = 0;
        InicioDeTodo = true;
        ganoCom = false;
        ganoUsu = false;
        empate = false;
        ocupado = false;
        bandoU = 0;
        ev = false;
        finDelJuego = false;
        conTiempo = true;
        tiempoTranscurrido[0] = 0L;
        tiempoTranscurrido[1] = 0L;
        tiempoTotal = 0x927c0L;
        ultimaActR = 0L;
        elTiempoAntes = -1L;
        murioTiempoU = false;
        murioTiempoC = false;
        eval_est = 0;
        compu.tdn();
*/
    }

    public void destroy()
    {
        if(corriente != null)
            corriente.stop();
    }

    public void dibPieza(Graphics g, int i, int j, int k, int l)
    {
        if(l != 0)
        {
            Image image = piezas[k][l];
            g.drawImage(image, i, j, porteCas, porteCas, this);
        }
    }

    public void rellPieza(Graphics g, int i, int j, int ai[], int ai1[])
    {
        int k = ai.length;
        int ai2[] = new int[k];
        int ai3[] = new int[k];
        for(int l = 0; l < k; l++)
        {
            ai2[l] = i + (porteCas * ai[l]) / 100;
            ai3[l] = j + (porteCas * ai1[l]) / 100;
        }

        g.fillPolygon(ai2, ai3, k);
        if(enmarcarBlancas)
        {
            g.setColor(Colores[juegoDeColores][1]);
            g.drawPolygon(ai2, ai3, k);
        }
    }

    public int evaluar()
    {
        return ((compu.jueguito.material[0] - compu.jueguito.material[1]) + compu.invencible.todoLoBuenoDe(compu.jueguito, 0)) - compu.invencible.todoLoBuenoDe(compu.jueguito, 1);
    }

    public Color cambiaColor(Color color)
    {
        if(color == casClara[juegoDeColores])
            return casOscura[juegoDeColores];
        else
            return casClara[juegoDeColores];
    }

    int idioma;
    String minutos[] = {
        "minutos", "minutes"
    };
    String empezar[] = {
        "empezar", "*begin*"
    };
    String tododenuevo[] = {
        "Todo de nuevo!", "Start again!"
    };
    String empatoj[] = {
        "\241empate! \277estas feliz?", "DRAW! are you happy?"
    };
    String perdioj[] = {
        "Ganaste! que bien...", "You win! lucky game!"
    };
    String ganoj[] = {
        "Has sido derrotado!!", "You are checkmated :("
    };
    String autor[] = {
        "hecho por Antonio Dieguez (zodiamoon@yahoo.com)", "made by Antonio Dieguez (zodiamoon@yahoo.com)"
    };
    String blancas[] = {
        "Blancas", "White"
    };
    String negras[] = {
        "Negras", "Black"
    };
    String esperaporfavor[] = {
        "Espera por favor", "Let me think..."
    };
    String facil[] = {
        "Facil!", "Easy!"
    };
    String ee[] = {
        "Evaluacion (b-n)", "Evaluation (w-b)"
    };
    String colores[] = {
        "Colores", "Colours"
    };
    String retroceder[] = {
        "<-Uups!", "<-Oops!"
    };
    String fotos[] = {
        "fotos", "pics"
    };
    String evaluaciones[] = {
        "Evaluaciones.", "Evaluations."
    };
    String librito[] = {
        "Libro aperturas.", "Opening book."
    };
    String forzar[] = {
        "-FORZAR-", "-FORCE-"
    };
    String archError;
    String archDerrota;
    String archTriunfo;
    boolean congifs;
    boolean conTiempo;
    long tiempoTranscurrido[];
    long cuantoFijo;
    long tiempos[] = {
        1L, 3L, 5L, 10L, 15L, 30L, 45L, 60L, 90L, 120L
    };
    long tiempoTotal;
    int indiceCuantosMin;
    long elTiempoAntes;
    long ultimaActR;
    boolean murioTiempoU;
    boolean murioTiempoC;
    boolean mostrarultimacompu;
    boolean condibujos;
    int queCorona;
    int posini;
    int posfin;
    int fase_mouse;
    int dondex;
    int dondey;
    int xdc;
    int ydc;
    maleitor compu;
    boolean InicioDeTodo;
    int porteCas;
    int altura;
    int largo;
    int bandoU;
    int orientacion;
    int eval_est;
    int porteTablero;
    int dondeminx;
    int dondeminy;
    int cualBan;
    int dondebandox;
    int dondebandoy;
    int dondeempezarx;
    int dondeempezary;
    int dondemasminx;
    int dondemasminy;
    int dondemenosminx;
    int dondemenosminy;
    int dondecambiacolorx;
    int dondecambiacolory;
    int dondeconevalx;
    int dondeconevaly;
    int dondeconlibrox;
    int dondeconlibroy;
    int dondeevalx;
    int dondeevaly;
    int dondeespx;
    int dondeespy;
    int dondetdnx;
    int dondetdny;
    int dondemenfx;
    int dondemenfy;
    int dondedospersonasx;
    int dondedospersonasy;
    int donderetrox;
    int donderetroy;
    int dondejuegatux;
    int dondejuegatuy;
    int dondecambiaopx;
    int dondecambiaopy;
    int dondecposx;
    int dondecposy;
    int dondefotosx;
    int dondefotosy;
    int dondecasillax;
    int dondecasillay;
    int anchocasillaBN;
    int concualcolor;
    int dondePrimPiezax;
    int dondePrimPiezay;
    int anchoCuadCP;
    boolean Marcomp;
    boolean ev;
    boolean finDelJuego;
    boolean ganoCom;
    boolean ganoUsu;
    boolean empate;
    boolean ocupado;
    boolean errorDelUsuario;
    boolean clickNecesario;
    boolean dibShock;
    boolean dibFelicidad;
    boolean yaSeMostroUI;
    boolean renovarFlechaNivel;
    boolean repasarNomas;
    boolean cargarImagenes;
    boolean animacionMarco;
    boolean dospersonas;
    int juegoDeColores;
    int cuantosJDC;
    Color casOscura[] = {
        new Color(205, 186, 158), new Color(205, 186, 158), new Color(205, 186, 158), new Color(205, 186, 158)
    };
    Color casClara[] = {
        Color.white, Color.white, Color.white, Color.white
    };
    Color Colores[][] = {
        {
            new Color(255, 255, 200), new Color(20, 20, 20)
        }, {
            new Color(255, 255, 200), new Color(20, 20, 20)
        }, {
            new Color(255, 255, 200), new Color(20, 20, 20)
        }, {
            new Color(255, 255, 255), new Color(29, 29, 29)
        }
    };
    Color paraEnmarcar;
    int px[] = {
        25, 75, 75, 60, 60, 55, 55, 62, 69, 69, 
        62, 38, 31, 31, 38, 45, 45, 40, 40, 25, 
        25
    };
    int py[] = {
        89, 89, 79, 70, 60, 60, 51, 51, 44, 34, 
        26, 26, 34, 44, 51, 51, 60, 60, 70, 79, 
        89
    };
    int tx[] = {
        21, 79, 79, 72, 72, 65, 65, 75, 75, 65, 
        65, 55, 55, 45, 45, 35, 35, 25, 25, 35, 
        35, 28, 28, 21, 21
    };
    int ty[] = {
        89, 89, 73, 73, 65, 65, 50, 39, 20, 20, 
        30, 30, 20, 20, 30, 30, 20, 20, 39, 50, 
        65, 65, 73, 73, 89
    };
    int cx[] = {
        26, 78, 78, 71, 77, 77, 72, 78, 72, 63, 
        56, 51, 47, 28, 24, 17, 18, 28, 36, 40, 
        46, 30, 30, 37, 37, 21, 26
    };
    int cy[] = {
        89, 89, 83, 73, 68, 59, 53, 43, 21, 15, 
        19, 17, 21, 22, 18, 22, 25, 42, 34, 38, 
        38, 54, 66, 71, 75, 83, 89
    };
    int rx[] = {
        24, 76, 76, 71, 73, 68, 68, 58, 58, 63, 
        63, 60, 58, 66, 56, 56, 61, 61, 56, 56, 
        44, 44, 39, 39, 44, 44, 34, 42, 40, 37, 
        37, 42, 42, 32, 32, 27, 29, 24, 24
    };
    int ry[] = {
        89, 89, 86, 82, 78, 76, 69, 65, 43, 43, 
        41, 41, 37, 23, 23, 18, 18, 13, 13, 7, 
        7, 13, 13, 18, 18, 23, 23, 37, 41, 41, 
        43, 43, 65, 69, 76, 78, 82, 86, 89
    };
    int ax[] = {
        27, 73, 74, 63, 63, 57, 57, 66, 57, 57, 
        62, 63, 50, 37, 38, 43, 43, 34, 43, 43, 
        37, 37, 26, 27
    };
    int ay[] = {
        89, 89, 82, 76, 73, 68, 54, 51, 49, 45, 
        41, 33, 12, 33, 41, 45, 49, 51, 54, 68, 
        73, 76, 82, 89
    };
    int dx[] = {
        21, 79, 79, 64, 60, 60, 54, 54, 57, 57, 
        69, 69, 63, 57, 50, 43, 37, 31, 31, 43, 
        43, 46, 46, 40, 40, 36, 21, 21
    };
    int dy[] = {
        89, 89, 79, 65, 65, 59, 59, 48, 48, 43, 
        36, 13, 20, 13, 20, 13, 20, 13, 36, 43, 
        48, 48, 59, 59, 65, 65, 79, 89
    };
    //Image ImError;
    //Image ImDerrota;
    //Image ImTriunfo;
    boolean unasolavez;
    Image piezas[][];
    boolean enmarcarBlancas;
    Thread corriente;
}
