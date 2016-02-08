
class cerebrin
{

    public cerebrin()
    {
        idioma = 0;
        cualturno = 0;
        conEvaluaciones = true;
        anteriormejorpuntaje = 0;
        debug = false;
        cabecera = "";
        profuMortal = 1;
        vaticinio = 0;
        orden = new int[222];
        agresividad = new int[2];
        anticipando = false;
        yaJugo = false;
        cualInc = new int[89][89];
        cualIncTorre = new int[89][89];
        cualIncAlfil = new int[89][89];
        atacapor = new boolean[17][12];
        septima = new boolean[2][89];
        noRetrocede = new boolean[2][89][89];
        pp = 0;
        patc = 0;
        pc = 0;
        pa = 0;
        pt = 0;
        pd = 0;
        pr = 0;
        primlin = 0;
        peligroRey = 0;
        septimafila = 0;
        choques = 0;
        bonus = 0;
        rinconesFeos = 0;
        unica = false;
        morimos = false;
        matearemos = false;
        soloActualizarReloj = false;
        conTiempo = false;
        tiempoFijo = false;
        compuAhogado = false;
        enemigoAhogado = false;
        lamejorini = 0;
        lamejorfin = 0;
        mejorpuntaje = "-";
        mateEn = 0;
        proximaInf = 0;
        tiempoAnt = 0L;
        tiempoAux = 0L;
        ultimaActR = 0L;
        mensajeFinal = "";
        atRey = new boolean[89][89];
        mismoCuadrante = new boolean[89][89];
        atCab = new boolean[89][89];
        mismoFlanco = new boolean[89][89];
        dentroTablero = new boolean[110];
        ultimaJugada = false;
        victoria = 0;
        capturaskiller = true;
        puntajesUltimos = new int[200];
        puntajesPrimeros = new int[200];
        movsCaballines = new int[89][8];
        movsReyuzcos = new int[89][8];
        nivel = 2;
        pvcompleta = true;
        cuentaSoloFinal = false;
        enCuadOCerca = new boolean[89][89];
        atacabilidadAlfil = new int[89][89];
        atacabilidadDama = new int[89][89];
        atacabilidadTorreNS = new int[89][89];
        atacabilidadTorreEO = new int[89][89];
        byte byte0 = 30;
        profundidadMaxima = 17;
        jugsAnteriores = new int[byte0 + 5][2];
        comida = new int[byte0 + 5];
        estabaJaque = new boolean[byte0 + 1];
        seextendiojaque = new boolean[byte0 + 1];
        jugnula = new boolean[byte0 + 1];
        cortamos = new boolean[byte0 + 1];
        comCortanteI = new int[byte0 + 1];
        comCortanteF = new int[byte0 + 1];
        esCaptura = new boolean[byte0 + 5];
        evals = new int[byte0 + 1];
        llenarDentroTablero();
        llenaratRey();
        llenarmismoCuadrante();
        llenarenCuadOCerca();
        llenarmismoFlanco();
        llenarAtaquesCaballezcos();
        llenarmovsCaballines();
        llenarmovsReyuzcos();
        llenarAtacabilidades();
        llenarseptima();
        llenarcualIncs();
        llenaratacapor();
        llenarPotenciaDe();
        llenarnoRetrocede();
        mejoresRespuestas = new int[200][2];
        juegos = new Juego[byte0];
        for(int k = 0; k <= byte0 - 1; k++)
        {
            juegos[k] = new Juego();
            juegos[k].atCab = atCab;
            juegos[k].atRey = atRey;
            juegos[k].dentroTablero = dentroTablero;
            juegos[k].vaPorLineas = vaPorLineas;
            juegos[k].vaPorDiagonales = vaPorDiagonales;
            juegos[k].septima = septima;
            juegos[k].cualInc = cualInc;
            juegos[k].cualIncTorre = cualIncTorre;
            juegos[k].cualIncAlfil = cualIncAlfil;
            juegos[k].atacapor = atacapor;
            juegos[k].potenciaDe = potenciaDe;
        }

        comCortanteI = new int[byte0];
        comCortanteF = new int[byte0];
        pinis = new int[200][byte0];
        pfins = new int[200][byte0];
        cinis = new int[35][byte0];
        cfins = new int[35][byte0];
        piezamov = new int[200][byte0];
        agresividades = new int[byte0][2];
        primCortante = new int[byte0][2];
        segCortante = new int[byte0][2];
        terCortante = new int[byte0][2];
    }

    public int[] jugar(Juego juego, int k, int l, int i1)
    {
        int ai[] = new int[3];
        bandoC = k;
        bandoCont = 1 - k;
        pvcompleta = true;
        if(juego.p[0][0] == 0 && juego.p[1][0] == 0 && juego.potencia[bandoC] + juego.potencia[bandoCont] <= 3)
        {
            mensajeFinal = pocomaterial[idioma];
            ai[2] = 2;
            return ai;
        }
        ultimaActR = 0L;
        jugsAnteriores[4][0] = l;
        jugsAnteriores[4][1] = i1;
        if(bandoC == 1 && numeroDeJugada > 4)
        {
            jugsAnteriores[4][0] = hjugadas[numeroDeJugada][0][0];
            jugsAnteriores[4][1] = hjugadas[numeroDeJugada][0][1];
            jugsAnteriores[3][0] = hjugadas[numeroDeJugada - 1][1][0];
            jugsAnteriores[3][1] = hjugadas[numeroDeJugada - 1][1][1];
            jugsAnteriores[2][0] = hjugadas[numeroDeJugada - 1][0][0];
            jugsAnteriores[2][1] = hjugadas[numeroDeJugada - 1][0][1];
            jugsAnteriores[1][0] = hjugadas[numeroDeJugada - 2][1][0];
            jugsAnteriores[1][1] = hjugadas[numeroDeJugada - 2][1][1];
            jugsAnteriores[0][0] = hjugadas[numeroDeJugada - 2][0][0];
            jugsAnteriores[0][1] = hjugadas[numeroDeJugada - 2][0][1];
            esCaptura[3] = huboComida[numeroDeJugada - 2][1];
            esCaptura[2] = huboComida[numeroDeJugada - 2][0];
            esCaptura[1] = huboComida[numeroDeJugada - 3][1];
            esCaptura[0] = huboComida[numeroDeJugada - 3][0];
        } else
        if(bandoC == 0 && numeroDeJugada > 4)
        {
            jugsAnteriores[3][0] = hjugadas[numeroDeJugada][0][0];
            jugsAnteriores[3][1] = hjugadas[numeroDeJugada][0][1];
            jugsAnteriores[2][0] = hjugadas[numeroDeJugada - 1][1][0];
            jugsAnteriores[2][1] = hjugadas[numeroDeJugada - 1][1][1];
            jugsAnteriores[1][0] = hjugadas[numeroDeJugada - 1][0][0];
            jugsAnteriores[1][1] = hjugadas[numeroDeJugada - 1][0][1];
            jugsAnteriores[0][0] = hjugadas[numeroDeJugada - 2][1][0];
            jugsAnteriores[0][1] = hjugadas[numeroDeJugada - 2][1][1];
            esCaptura[3] = huboComida[numeroDeJugada - 2][0];
            esCaptura[2] = huboComida[numeroDeJugada - 3][1];
            esCaptura[1] = huboComida[numeroDeJugada - 3][0];
            esCaptura[0] = huboComida[numeroDeJugada - 4][1];
        }
        posAnalizadas = 0;
        nodosNormales = 0;
        nodosExtension = 0;
        proximaInf = 100 + (int)(Math.random() * 300D);
        cuantoMas = proximaInf;
        cabecera = "[1] ?-? ; P: ";
        tiempoAnt = System.currentTimeMillis();
        profundidad = 1;
        if(juego.minimoatacante(juego.posRey[k], bandoCont) == 0)
            estabaJaque[0] = false;
        else
            estabaJaque[0] = true;
        tiempoPartida = System.currentTimeMillis();
        if(conTiempo)
        {
            if(tiempoFijo)
                tiempoPorJugada = cuantoTiempoFijo;
            else
            if(tiempoRestante < 0L)
                tiempoPorJugada = 7000L;
            else
            if(numeroDeJugada >= 40)
                tiempoPorJugada = tiempoRestante / 20L;
            else
                tiempoPorJugada = tiempoRestante / (long)(60 - numeroDeJugada);
            limite = tiempoPartida + tiempoPorJugada;
        }
        int ai1[] = respuestaConTiempo(juego, k, 0, 0, 0, 0, l, i1);
        if(compuAhogado)
        {
            compuAhogado = false;
            mensajeFinal = reyahogado[idioma];
            ai[0] = lamejorini;
            ai[1] = lamejorfin;
            ai[2] = 2;
            return ai;
        }
        if(morimos)
        {
            morimos = false;
            mensajeFinal = mate[idioma];
            ai[2] = -1;
            return ai;
        }
        if(unica)
        {
            unica = false;
            mensajeFinal = unicaj[idioma];
            ai[0] = lamejorini;
            ai[1] = lamejorfin;
            return ai;
        }
        for(i = 1; i < primCortante.length - 2; i++)
            if(primCortante[i + 2][0] != 0)
            {
                primCortante[i][0] = primCortante[i + 2][0];
                segCortante[i][0] = segCortante[i + 2][0];
                terCortante[i][0] = terCortante[i + 2][0];
                primCortante[i][1] = primCortante[i + 2][1];
                segCortante[i][1] = segCortante[i + 2][1];
                terCortante[i][1] = terCortante[i + 2][1];
            }

        mensajeFinal = "[" + profundidad + "] ";
        if(!pvcompleta)
            mensajeFinal = mensajeFinal + ">";
        mensajeFinal = mensajeFinal + mejorpuntaje + " P: " + posAnalizadas;
        int j1 = (int)((System.currentTimeMillis() - tiempoPartida) / 1000L);
        if(j1 > 0)
        {
            int k1 = posAnalizadas / j1;
            mensajeFinal += " " + k1 + " p/s";
        }
        if(ai1[2] != 0)
            mensajeFinal = mensajeFinal + " PC=";
        int l1 = ai1.length - 1;
        if(!pvcompleta)
            l1 = 2;
        for(int i2 = 2; i2 <= l1 && ai1[i2] != 0; i2 += 2)
            mensajeFinal = mensajeFinal + " " + nombresCas[ai1[i2]] + "-" + nombresCas[ai1[i2 + 1]];

        if(debug)
        {
            System.out.println("Total nodos = " + posAnalizadas);
            System.out.println("Nodos normales  : " + nodosNormales);
            System.out.println("Nodos extension : " + nodosExtension);
            System.out.println("");
        }
        if(victoria == 1)
        {
            victoria = 0;
            if(mateEn == 1)
            {
                mateEn = 0;
                mensajeFinal += mate[idioma];
                ai[2] = 1;
            } else
            {
                matearemos = true;
                profuMortal = 2 * mateEn - 1;
                mensajeFinal += mateenin[idioma] + mateEn;
                if(mateEn > 2)
                    mensajeFinal += omenos[idioma];
            }
            ai[0] = lamejorini;
            ai[1] = lamejorfin;
            return ai;
        }
        if(enemigoAhogado)
        {
            enemigoAhogado = false;
            ai[2] = -2;
            ai[0] = lamejorini;
            ai[1] = lamejorfin;
            return ai;
        } else
        {
            ai[0] = lamejorini;
            ai[1] = lamejorfin;
            return ai;
        }
    }

    public int[] respuestaConTiempo(Juego juego, int k, int l, int i1, int j1, int k1, int l1, 
            int i2)
    {
        int ai[] = new int[21];
        lamejorini = 0;
        lamejorfin = 0;
        int j2 = jugPos(juego, k, 0);
        if(j2 == 1)
        {
            unica = true;
            lamejorini = pinis[0][0];
            lamejorfin = pfins[0][0];
            return ai;
        }
        if(j2 == 0)
            if(estabaJaque[0])
            {
                morimos = true;
                return ai;
            } else
            {
                compuAhogado = true;
                return ai;
            }
        int k2 = 1 - k;
        int ai1[] = new int[3];
        ai[0] = -30000;
        char c = '\u9A70';
        mejorpuntaje = "??";
        for(int i3 = 0; i3 < j2; i3++)
        {
            JuegoConNuevaJugada(juegos[0], juego, pinis[i3][0], pfins[i3][0], k);
            int ai2[] = jugada(juegos[0], k2, 1, pinis[i3][0], pfins[i3][0], profundidad, 26000, -26000, 0);
            puntajesPrimeros[i3] = ai2[0];
            if(ai2[0] > ai[0])
            {
                ai = ai2;
                lamejorini = pinis[i3][0];
                lamejorfin = pfins[i3][0];
                mejorpuntaje = transformar(ai[0]);
                cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* " + mejorpuntaje + " P: ";
                if(ai[0] > 25000)
                {
                    victoria = 1;
                    mateEn = (ai[1] + 1) / 2;
                    return ai;
                }
            }
        }

        if(ai[0] < -25000 || j2 == 1)
            return ai;
        profundidad++;
        ordenar(j2, j2);
        byte byte0;
        if(juego.potencia[0] + juego.d[0][0] * 5 <= 19 && juego.potencia[1] + juego.d[1][0] * 5 <= 19)
        {
            byte0 = 65;
            byte byte1 = 75;
        } else
        {
            byte0 = 55;
            byte byte2 = 60;
        }
        do
        {
            anteriormejorpuntaje = ai[0];
            boolean flag = true;
            cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* " + mejorpuntaje + " P: ";
            boolean flag2 = false;
            boolean flag3 = false;
            int l2 = ai[0] - byte0;
            int j3 = ai[0] + byte0;
            agregarCorteALC(mejoresRespuestas[orden[0]][0], mejoresRespuestas[orden[0]][1], 1, juego);
            JuegoConNuevaJugada(juegos[0], juego, pinis[orden[0]][0], pfins[orden[0]][0], k);
            ai = jugada(juegos[0], k2, 1, pinis[orden[0]][0], pfins[orden[0]][0], profundidad, j3, l2, 0);
            if(ai[0] >= j3)
            {
                cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* >=" + transformar(j3) + " P: ";
                ai = jugada(juegos[0], k2, 1, pinis[orden[0]][0], pfins[orden[0]][0], profundidad, Math.max(300, j3 + 300), j3, 0);
                if(ai[0] >= Math.max(300, j3 + 300))
                {
                    cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* >=" + transformar(Math.max(300, j3 + 300)) + " P: ";
                    ai = jugada(juegos[0], k2, 1, pinis[orden[0]][0], pfins[orden[0]][0], profundidad, 26000, Math.max(300, j3 + 300), 0);
                }
            } else
            if(ai[0] <= l2)
            {
                agregarCorteALC(mejoresRespuestas[orden[0]][0], mejoresRespuestas[orden[0]][1], 1, juego);
                cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* <=" + transformar(l2) + " P: ";
                ai = jugada(juegos[0], k2, 1, pinis[orden[0]][0], pfins[orden[0]][0], profundidad, l2, Math.min(-300, l2 - 300), 0);
            }
            lamejorini = pinis[orden[0]][0];
            lamejorfin = pfins[orden[0]][0];
            mejorpuntaje = transformar(ai[0]);
            cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* " + mejorpuntaje + " P: ";
            if(ai[0] > 25000)
            {
                victoria = 1;
                mateEn = (ai[1] + 1) / 2;
                return ai;
            }
            for(int j4 = 1; j4 < j2; j4++)
            {
                agregarCorteALC(mejoresRespuestas[orden[j4]][0], mejoresRespuestas[orden[j4]][1], 1, juego);
                JuegoConNuevaJugada(juegos[0], juego, pinis[orden[j4]][0], pfins[orden[j4]][0], k);
                int ai3[] = jugada(juegos[0], k2, 1, pinis[orden[j4]][0], pfins[orden[j4]][0], profundidad, ai[0] + 1, ai[0], 0);
                if(ai3[0] < -25000)
                {
                    if(ai3[0] > ai[0])
                    {
                        ai = ai3;
                        lamejorini = pinis[orden[j4]][0];
                        lamejorfin = pfins[orden[j4]][0];
                        mejorpuntaje = transformar(ai[0]);
                        int k3 = j4;
                        cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* " + mejorpuntaje + " P: ";
                    }
                    for(int k4 = j4; k4 < j2 - 1; k4++)
                        orden[k4] = orden[k4 + 1];

                    j2--;
                    j4--;
                    boolean flag1 = true;
                } else
                {
                    if(ai3[0] >= ai[0] + 1)
                    {
                        lamejorini = pinis[orden[j4]][0];
                        lamejorfin = pfins[orden[j4]][0];
                        if(j4 >= 3 && System.currentTimeMillis() > limite && ai3[0] > -25000 && ai3[0] >= anteriormejorpuntaje - 185)
                        {
                            pvcompleta = false;
                            ai = ai3;
                            mejorpuntaje = transformar(ai[0]);
                            if(ai[0] > 25000)
                            {
                                victoria = 1;
                                mateEn = (ai[1] + 1) / 2;
                            }
                            return ai;
                        }
                        cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* >=" + transformar(ai[0] + 1) + " P: ";
                        ai3 = jugada(juegos[0], k2, 1, pinis[orden[j4]][0], pfins[orden[j4]][0], profundidad, Math.max(300, ai[0] + 300), ai[0], 0);
                        if(ai3[0] >= Math.max(300, ai[0] + 300))
                            ai3 = jugada(juegos[0], k2, 1, pinis[orden[j4]][0], pfins[orden[j4]][0], profundidad, 26000, Math.max(300, ai[0] + 300), 0);
                    }
                    if(ai3[0] > ai[0])
                    {
                        ai = ai3;
                        int l3 = j4;
                        mejorpuntaje = transformar(ai[0]);
                        cabecera = "[" + profundidad + "]*" + nombresCas[lamejorini] + "-" + nombresCas[lamejorfin] + "* " + mejorpuntaje + " P: ";
                        if(ai[0] > 25000)
                        {
                            victoria = 1;
                            mateEn = (ai[1] + 1) / 2;
                            return ai;
                        }
                        int i4 = orden[j4];
                        for(int l4 = j4; l4 > 0; l4--)
                            orden[l4] = orden[l4 - 1];

                        orden[0] = i4;
                    }
                    if(!debug && j4 >= 3 && System.currentTimeMillis() > limite && ai[0] >= anteriormejorpuntaje - 185)
                        return ai;
                }
            }

            if(j2 <= 1 || profundidad == profundidadMaxima || System.currentTimeMillis() > (28L * tiempoPartida + 72L * limite) / 100L || ai[0] < -25000)
                return ai;
            anteriormejorpuntaje = ai[0];
            profundidad++;
        } while(true);
    }

    private String continuacion(int ai[])
    {
        String s = "";
        for(int k = 2; k <= ai.length - 1 && ai[k] != 0; k += 2)
            s = s + " " + nombresCas[ai[k]] + "-" + nombresCas[ai[k + 1]];

        return s;
    }

    private final void corteALACOLA(int k, int l, int i1)
    {
        if(primCortante[i1][0] == 0)
        {
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
            return;
        }
        if(segCortante[i1][0] == 0)
        {
            segCortante[i1][0] = k;
            segCortante[i1][1] = l;
            return;
        } else
        {
            terCortante[i1][0] = k;
            terCortante[i1][1] = l;
            return;
        }
    }

    private final void corte2pasos(int k, int l, int i1)
    {
        if(primCortante[i1][0] == 0)
        {
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
            return;
        }
        if(segCortante[i1][0] == 0)
        {
            segCortante[i1][0] = k;
            segCortante[i1][1] = l;
            return;
        } else
        {
            terCortante[i1][0] = segCortante[i1][0];
            terCortante[i1][1] = segCortante[i1][1];
            segCortante[i1][0] = k;
            segCortante[i1][1] = l;
            return;
        }
    }

    private final void agregarCorteALC(int k, int l, int i1, Juego juego)
    {
        if(primCortante[i1][1] == l && primCortante[i1][0] == k)
            return;
        if(segCortante[i1][1] == l && segCortante[i1][0] == k)
        {
            segCortante[i1][0] = primCortante[i1][0];
            segCortante[i1][1] = primCortante[i1][1];
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
            return;
        }
        if(terCortante[i1][1] == l && terCortante[i1][0] == k)
        {
            terCortante[i1][0] = segCortante[i1][0];
            terCortante[i1][1] = segCortante[i1][1];
            segCortante[i1][0] = primCortante[i1][0];
            segCortante[i1][1] = primCortante[i1][1];
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
            return;
        }
        if(juego.pos[k] % 10 != 5)
        {
            if(!juego.casVacias[l])
            {
                terCortante[i1][0] = segCortante[i1][0];
                terCortante[i1][1] = segCortante[i1][1];
                segCortante[i1][0] = primCortante[i1][0];
                segCortante[i1][1] = primCortante[i1][1];
                primCortante[i1][0] = k;
                primCortante[i1][1] = l;
            }
            return;
        }
        if(Math.abs(k - l) == 10)
        {
            terCortante[i1][0] = segCortante[i1][0];
            terCortante[i1][1] = segCortante[i1][1];
            segCortante[i1][0] = primCortante[i1][0];
            segCortante[i1][1] = primCortante[i1][1];
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
        }
    }

    private final void agregarCorte(int k, int l, int i1)
    {
        if(primCortante[i1][0] == 0)
        {
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
        } else
        if(segCortante[i1][0] == 0)
        {
            segCortante[i1][0] = k;
            segCortante[i1][1] = l;
        } else
        if(segCortante[i1][1] == l && segCortante[i1][0] == k)
        {
            segCortante[i1][0] = primCortante[i1][0];
            segCortante[i1][1] = primCortante[i1][1];
            primCortante[i1][0] = k;
            primCortante[i1][1] = l;
        } else
        if(terCortante[i1][1] == l && terCortante[i1][0] == k)
        {
            terCortante[i1][0] = segCortante[i1][0];
            terCortante[i1][1] = segCortante[i1][1];
            segCortante[i1][0] = primCortante[i1][0];
            segCortante[i1][1] = primCortante[i1][1];
        } else
        if(primCortante[i1][1] != l || primCortante[i1][0] != k)
        {
            terCortante[i1][0] = k;
            terCortante[i1][1] = l;
        }
    }

    public int pseudoCapturasPos(Juego juego, int k, int l)
    {
        int i1 = 1 - k;
        casVacias = juego.casVacias;
        int j1 = -1;
        int k1 = inc[k];
        for(i = 1; i <= juego.p[k][0]; i++)
        {
            int l1 = juego.p[k][i] / 10;
            int j3 = juego.p[k][i] % 10;
            if(l1 != i1 * 5 + 2)
            {
                if(j3 >= 2)
                    if(juego.hayPieza[i1][(juego.p[k][i] + k1) - 1])
                    {
                        j1++;
                        cinis[j1][l] = juego.p[k][i];
                        cfins[j1][l] = (juego.p[k][i] + k1) - 1;
                        piezamov[j1][l] = 5;
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP + 1)
                    {
                        j1++;
                        cinis[j1][l] = juego.p[k][i];
                        cfins[j1][l] = (juego.p[k][i] + k1) - 1;
                        piezamov[j1][l] = 5;
                    }
                if(j3 <= 7)
                    if(juego.hayPieza[i1][juego.p[k][i] + k1 + 1])
                    {
                        j1++;
                        cinis[j1][l] = juego.p[k][i];
                        cfins[j1][l] = juego.p[k][i] + k1 + 1;
                        piezamov[j1][l] = 5;
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP - 1)
                    {
                        j1++;
                        cinis[j1][l] = juego.p[k][i];
                        cfins[j1][l] = juego.p[k][i] + k1 + 1;
                        piezamov[j1][l] = 5;
                    }
                if(l1 == i1 * 3 + 3 && casVacias[juego.p[k][i] + k1] && casVacias[juego.p[k][i] + 2 * k1] && juego.potencia[i1] <= 17)
                {
                    j1++;
                    cinis[j1][l] = juego.p[k][i];
                    cfins[j1][l] = juego.p[k][i] + k1;
                    piezamov[j1][l] = 5;
                }
            } else
            {
                if(casVacias[juego.p[k][i] + k1])
                {
                    for(int k3 = 1; k3 <= 4; k3++)
                    {
                        cinis[j1 + k3][l] = juego.p[k][i];
                        cfins[j1 + k3][l] = 25 - k3;
                        piezamov[j1 + k3][l] = 5;
                    }

                    j1 += 4;
                }
                if(j3 >= 2 && juego.hayPieza[i1][(juego.p[k][i] + k1) - 1])
                {
                    for(int l3 = 1; l3 <= 4; l3++)
                    {
                        cinis[j1 + l3][l] = juego.p[k][i];
                        cfins[j1 + l3][l] = 15 - l3;
                        piezamov[j1 + l3][l] = 5;
                    }

                    j1 += 4;
                }
                if(j3 <= 7 && juego.hayPieza[i1][juego.p[k][i] + k1 + 1])
                {
                    for(int i4 = 1; i4 <= 4; i4++)
                    {
                        cinis[j1 + i4][l] = juego.p[k][i];
                        cfins[j1 + i4][l] = 35 - i4;
                        piezamov[j1 + i4][l] = 5;
                    }

                    j1 += 4;
                }
            }
        }

        for(i = 1; i <= juego.c[k][0]; i++)
        {
            for(int i2 = 0; i2 <= 7; i2++)
                if(dentroTablero[movsCaballines[juego.c[k][i]][i2]] && juego.hayPieza[i1][movsCaballines[juego.c[k][i]][i2]])
                {
                    j1++;
                    cinis[j1][l] = juego.c[k][i];
                    cfins[j1][l] = movsCaballines[juego.c[k][i]][i2];
                    piezamov[j1][l] = 1;
                }

        }

        for(i = 1; i <= juego.a[k][0]; i++)
        {
            for(int j2 = 0; j2 <= 3; j2++)
                for(j = juego.a[k][i] + incAlfil[j2]; dentroTablero[j]; j += incAlfil[j2])
                {
                    if(casVacias[j])
                        continue;
                    if(juego.hayPieza[i1][j])
                    {
                        j1++;
                        cinis[j1][l] = juego.a[k][i];
                        cfins[j1][l] = j;
                        piezamov[j1][l] = 3;
                    }
                    break;
                }


        }

        for(i = 1; i <= juego.t[k][0]; i++)
        {
            for(int k2 = 0; k2 <= 3; k2++)
                for(j = juego.t[k][i] + incTorre[k2]; dentroTablero[j]; j += incTorre[k2])
                {
                    if(casVacias[j])
                        continue;
                    if(juego.hayPieza[i1][j])
                    {
                        j1++;
                        cinis[j1][l] = juego.t[k][i];
                        cfins[j1][l] = j;
                        piezamov[j1][l] = 2;
                    }
                    break;
                }


        }

        for(i = 1; i <= juego.d[k][0]; i++)
        {
            for(int l2 = 0; l2 <= 7; l2++)
            {
                auxil = incDama[l2];
                for(j = juego.d[k][i] + auxil; dentroTablero[j]; j += auxil)
                {
                    if(casVacias[j])
                        continue;
                    if(juego.hayPieza[i1][j])
                    {
                        j1++;
                        cinis[j1][l] = juego.d[k][i];
                        cfins[j1][l] = j;
                        piezamov[j1][l] = 4;
                    }
                    break;
                }

            }

        }

        for(int i3 = 0; i3 <= 7; i3++)
            if(dentroTablero[movsReyuzcos[juego.posRey[k]][i3]] && juego.hayPieza[i1][movsReyuzcos[juego.posRey[k]][i3]])
            {
                j1++;
                cinis[j1][l] = juego.posRey[k];
                cfins[j1][l] = movsReyuzcos[juego.posRey[k]][i3];
                piezamov[j1][l] = 6;
            }

        return j1 + 1;
    }

    public int pseudoNoCapturasPos(Juego juego, int k, int l)
    {
        int i1 = 1 - k;
        casVacias = juego.casVacias;
        int j1 = -1;
        int k1 = inc[k];
        if(juego.ped[k] && casVacias[juego.posRey[k] + 1] && casVacias[juego.posRey[k] + 2] && juego.minimoatacante(juego.posRey[k] + 1, i1) == 0)
        {
            j1++;
            pinis[j1][l] = juego.posRey[k];
            pfins[j1][l] = juego.posRey[k] + 2;
            piezamov[j1][l] = 7;
        }
        if(juego.pei[k] && casVacias[juego.posRey[k] - 1] && casVacias[juego.posRey[k] - 2] && casVacias[juego.posRey[k] - 3] && juego.minimoatacante(juego.posRey[k] - 1, i1) == 0)
        {
            j1++;
            pinis[j1][l] = juego.posRey[k];
            pfins[j1][l] = juego.posRey[k] - 2;
            piezamov[j1][l] = 7;
        }
        for(i = 1; i <= juego.c[k][0]; i++)
        {
            for(int l1 = 0; l1 <= 7; l1++)
                if(dentroTablero[movsCaballines[juego.c[k][i]][l1]] && juego.casVacias[movsCaballines[juego.c[k][i]][l1]])
                {
                    j1++;
                    pinis[j1][l] = juego.c[k][i];
                    pfins[j1][l] = movsCaballines[juego.c[k][i]][l1];
                    piezamov[j1][l] = 1;
                }

        }

        for(i = 1; i <= juego.a[k][0]; i++)
        {
            for(int i2 = 0; i2 <= 3; i2++)
                for(j = juego.a[k][i] + incAlfil[i2]; dentroTablero[j] && casVacias[j]; j += incAlfil[i2])
                {
                    j1++;
                    pinis[j1][l] = juego.a[k][i];
                    pfins[j1][l] = j;
                    piezamov[j1][l] = 3;
                }


        }

        for(i = 1; i <= juego.t[k][0]; i++)
        {
            for(int j2 = 0; j2 <= 3; j2++)
            {
                auxil = incTorre[j2];
                for(j = juego.t[k][i] + auxil; dentroTablero[j] && casVacias[j]; j += auxil)
                {
                    j1++;
                    pinis[j1][l] = juego.t[k][i];
                    pfins[j1][l] = j;
                    piezamov[j1][l] = 2;
                }

            }

        }

        for(i = 1; i <= juego.d[k][0]; i++)
        {
            for(int k2 = 0; k2 <= 7; k2++)
            {
                auxil = incDama[k2];
                for(j = juego.d[k][i] + auxil; dentroTablero[j] && casVacias[j]; j += auxil)
                {
                    j1++;
                    pinis[j1][l] = juego.d[k][i];
                    pfins[j1][l] = j;
                    piezamov[j1][l] = 4;
                }

            }

        }

        for(i = 1; i <= juego.p[k][0]; i++)
        {
            int l2 = juego.p[k][i] / 10;
            if(l2 != i1 * 5 + 2 && (l2 != i1 * 3 + 3 || !casVacias[juego.p[k][i] + 2 * k1] || juego.potencia[i1] > 17) && casVacias[juego.p[k][i] + k1])
            {
                j1++;
                pinis[j1][l] = juego.p[k][i];
                pfins[j1][l] = juego.p[k][i] + k1;
                piezamov[j1][l] = 5;
                if(l2 == k * 5 + 2 && casVacias[juego.p[k][i] + 2 * k1])
                {
                    j1++;
                    pinis[j1][l] = juego.p[k][i];
                    pfins[j1][l] = juego.p[k][i] + 2 * k1;
                    piezamov[j1][l] = 5;
                }
            }
        }

        for(int i3 = 0; i3 <= 7; i3++)
            if(dentroTablero[movsReyuzcos[juego.posRey[k]][i3]] && juego.casVacias[movsReyuzcos[juego.posRey[k]][i3]])
            {
                j1++;
                pinis[j1][l] = juego.posRey[k];
                pfins[j1][l] = movsReyuzcos[juego.posRey[k]][i3];
                piezamov[j1][l] = 6;
            }

        return j1 + 1;
    }

    private final int pseudoJaquesPos(Juego juego, int k, int l)
    {
        int i1 = 1 - k;
        casVacias = juego.casVacias;
        int j1 = 0;
        int k1 = inc[k];
        int l1 = juego.posRey[i1];
        for(int i2 = 1; i2 <= juego.c[k][0]; i2++)
        {
            for(int j2 = 0; j2 <= 7; j2++)
                if(dentroTablero[movsCaballines[juego.c[k][i2]][j2]] && juego.casVacias[movsCaballines[juego.c[k][i2]][j2]] && atCab[movsCaballines[juego.c[k][i2]][j2]][l1])
                {
                    pinis[j1][l] = juego.c[k][i2];
                    pfins[j1][l] = movsCaballines[juego.c[k][i2]][j2];
                    piezamov[j1][l] = 1;
                    j1++;
                }

        }

        for(int k2 = 1; k2 <= juego.a[k][0]; k2++)
        {
            for(int l2 = 0; l2 <= 3; l2++)
            {
                for(int j3 = juego.a[k][k2] + incAlfil[l2]; dentroTablero[j3] && casVacias[j3]; j3 += incAlfil[l2])
                    if(cualIncAlfil[l1][j3] != 0 && juego.vacioEntre(l1, j3))
                    {
                        pinis[j1][l] = juego.a[k][k2];
                        pfins[j1][l] = j3;
                        piezamov[j1][l] = 3;
                        j1++;
                    }

            }

        }

        for(int i3 = 1; i3 <= juego.t[k][0]; i3++)
        {
            for(int k3 = 0; k3 <= 3; k3++)
            {
                for(int i4 = juego.t[k][i3] + incTorre[k3]; dentroTablero[i4] && casVacias[i4]; i4 += incTorre[k3])
                    if(cualIncTorre[l1][i4] != 0 && juego.vacioEntre(l1, i4))
                    {
                        pinis[j1][l] = juego.t[k][i3];
                        pfins[j1][l] = i4;
                        piezamov[j1][l] = 2;
                        j1++;
                    }

            }

        }

        for(int l3 = 1; l3 <= juego.d[k][0]; l3++)
        {
            for(int j4 = 0; j4 <= 7; j4++)
            {
                for(int l4 = juego.d[k][l3] + incDama[j4]; dentroTablero[l4] && casVacias[l4]; l4 += incDama[j4])
                    if(cualInc[l1][l4] != 0 && juego.vacioEntre(l1, l4))
                    {
                        pinis[j1][l] = juego.d[k][l3];
                        pfins[j1][l] = l4;
                        piezamov[j1][l] = 4;
                        j1++;
                    }

            }

        }

        for(int k4 = 1; k4 <= juego.p[k][0]; k4++)
        {
            int i5 = juego.p[k][k4] / 10;
            if(i5 != i1 * 5 + 2 && casVacias[juego.p[k][k4] + k1])
            {
                int j5 = juego.p[k][k4] + k1;
                if(juego.casAtPorP[i1][j5] == 0)
                    if(septima[k][j5] || juego.hayPieza[i1][j5 + 1] || juego.hayPieza[i1][j5 - 1])
                    {
                        pinis[j1][l] = juego.p[k][k4];
                        pfins[j1][l] = juego.p[k][k4] + k1;
                        piezamov[j1][l] = 5;
                        j1++;
                    } else
                    if(i5 == k * 5 + 2 && casVacias[juego.p[k][k4] + 2 * k1] && (juego.hayPieza[i1][j5 + 19] || juego.hayPieza[i1][j5 + 21]))
                    {
                        pinis[j1][l] = juego.p[k][k4];
                        pfins[j1][l] = juego.p[k][k4] + 2 * k1;
                        piezamov[j1][l] = 5;
                        j1++;
                    }
            }
        }

        return j1;
    }

    public int jugPos(Juego juego, int k, int l)
    {
        int i1 = 1 - k;
        casVacias = juego.casVacias;
        int j1 = -1;
        rey = juego.posRey[k];
        int k1 = rey % 10;
        int l1 = rey / 10;
        int i2 = inc[k];
        for(i = 1; i <= juego.c[k][0]; i++)
        {
            for(int j2 = 0; j2 <= 7; j2++)
                if(dentroTablero[movsCaballines[juego.c[k][i]][j2]] && !juego.hayPieza[k][movsCaballines[juego.c[k][i]][j2]] && juego.noDejaJaque(juego.c[k][i], movsCaballines[juego.c[k][i]][j2], k1, l1, rey, k))
                {
                    j1++;
                    pinis[j1][l] = juego.c[k][i];
                    pfins[j1][l] = movsCaballines[juego.c[k][i]][j2];
                }

        }

        for(i = 1; i <= juego.a[k][0]; i++)
        {
            for(int k2 = 0; k2 <= 3; k2++)
                for(j = juego.a[k][i] + incAlfil[k2]; dentroTablero[j] && !juego.hayPieza[k][j]; j += incAlfil[k2])
                {
                    if(juego.noDejaJaque(juego.a[k][i], j, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.a[k][i];
                        pfins[j1][l] = j;
                    }
                    if(!casVacias[j])
                        break;
                }


        }

        for(i = 1; i <= juego.p[k][0]; i++)
        {
            int l2 = juego.p[k][i] / 10;
            int l3 = juego.p[k][i] % 10;
            if(l2 == i1 * 5 + 2)
            {
                if(casVacias[juego.p[k][i] + i2] && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + i2, k1, l1, rey, k))
                {
                    for(int i4 = 1; i4 <= 4; i4++)
                    {
                        pinis[j1 + i4][l] = juego.p[k][i];
                        pfins[j1 + i4][l] = 25 - i4;
                    }

                    j1 += 4;
                }
                if(l3 >= 2 && juego.hayPieza[i1][(juego.p[k][i] + i2) - 1] && juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + i2) - 1, k1, l1, rey, k))
                {
                    for(int j4 = 1; j4 <= 4; j4++)
                    {
                        pinis[j1 + j4][l] = juego.p[k][i];
                        pfins[j1 + j4][l] = 15 - j4;
                    }

                    j1 += 4;
                }
                if(l3 <= 7 && juego.hayPieza[i1][juego.p[k][i] + i2 + 1] && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + i2 + 1, k1, l1, rey, k))
                {
                    for(int k4 = 1; k4 <= 4; k4++)
                    {
                        pinis[j1 + k4][l] = juego.p[k][i];
                        pfins[j1 + k4][l] = 35 - k4;
                    }

                    j1 += 4;
                }
            } else
            {
                if(l3 >= 2)
                    if(juego.hayPieza[i1][(juego.p[k][i] + i2) - 1])
                    {
                        if(juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + i2) - 1, k1, l1, rey, k))
                        {
                            j1++;
                            pinis[j1][l] = juego.p[k][i];
                            pfins[j1][l] = (juego.p[k][i] + i2) - 1;
                        }
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP + 1 && juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + i2) - 1, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.p[k][i];
                        pfins[j1][l] = (juego.p[k][i] + i2) - 1;
                    }
                if(l3 <= 7)
                    if(juego.hayPieza[i1][juego.p[k][i] + i2 + 1])
                    {
                        if(juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + i2 + 1, k1, l1, rey, k))
                        {
                            j1++;
                            pinis[j1][l] = juego.p[k][i];
                            pfins[j1][l] = juego.p[k][i] + i2 + 1;
                        }
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP - 1 && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + i2 + 1, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.p[k][i];
                        pfins[j1][l] = juego.p[k][i] + i2 + 1;
                    }
                if(casVacias[juego.p[k][i] + i2])
                {
                    if(juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + i2, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.p[k][i];
                        pfins[j1][l] = juego.p[k][i] + i2;
                    }
                    if(l2 == k * 5 + 2 && casVacias[juego.p[k][i] + 2 * i2] && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + 2 * i2, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.p[k][i];
                        pfins[j1][l] = juego.p[k][i] + 2 * i2;
                    }
                }
            }
        }

        for(i = 1; i <= juego.t[k][0]; i++)
        {
            for(int i3 = 0; i3 <= 3; i3++)
                for(j = juego.t[k][i] + incTorre[i3]; dentroTablero[j] && !juego.hayPieza[k][j]; j += incTorre[i3])
                {
                    if(juego.noDejaJaque(juego.t[k][i], j, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.t[k][i];
                        pfins[j1][l] = j;
                    }
                    if(!casVacias[j])
                        break;
                }


        }

        for(i = 1; i <= juego.d[k][0]; i++)
        {
            for(int j3 = 0; j3 <= 7; j3++)
                for(j = juego.d[k][i] + incDama[j3]; dentroTablero[j] && !juego.hayPieza[k][j]; j += incDama[j3])
                {
                    if(juego.noDejaJaque(juego.d[k][i], j, k1, l1, rey, k))
                    {
                        j1++;
                        pinis[j1][l] = juego.d[k][i];
                        pfins[j1][l] = j;
                    }
                    if(!casVacias[j])
                        break;
                }


        }

        if(!estabaJaque[l])
        {
            if(juego.ped[k] && casVacias[rey + 1] && casVacias[rey + 2] && juego.minimoatacante(rey + 1, i1) == 0 && juego.minimoatacante(rey + 2, i1) == 0)
            {
                j1++;
                pinis[j1][l] = rey;
                pfins[j1][l] = rey + 2;
            }
            if(juego.pei[k] && casVacias[rey - 1] && casVacias[rey - 2] && casVacias[rey - 3] && juego.minimoatacante(rey - 1, i1) == 0 && juego.minimoatacante(rey - 2, i1) == 0)
            {
                j1++;
                pinis[j1][l] = rey;
                pfins[j1][l] = rey - 2;
            }
        }
        for(int k3 = 0; k3 <= 7; k3++)
            if(dentroTablero[movsReyuzcos[rey][k3]] && !juego.hayPieza[k][movsReyuzcos[rey][k3]] && juego.noDejaJaque(rey, movsReyuzcos[rey][k3], movsReyuzcos[rey][k3] % 10, movsReyuzcos[rey][k3] / 10, movsReyuzcos[rey][k3], k))
            {
                j1++;
                pinis[j1][l] = rey;
                pfins[j1][l] = movsReyuzcos[rey][k3];
            }

        return j1 + 1;
    }

    public int fuertesPos(Juego juego, int k, int l)
    {
        int i1 = 1 - k;
        casVacias = juego.casVacias;
        int j1 = -1;
        int k1 = juego.posRey[k] % 10;
        int l1 = juego.posRey[k] / 10;
        for(i = 1; i <= juego.c[k][0]; i++)
        {
            for(int i2 = 0; i2 <= 7; i2++)
                if(dentroTablero[movsCaballines[juego.c[k][i]][i2]] && juego.hayPieza[i1][movsCaballines[juego.c[k][i]][i2]] && juego.noDejaJaque(juego.c[k][i], movsCaballines[juego.c[k][i]][i2], k1, l1, juego.posRey[k], k))
                {
                    j1++;
                    puntajesUltimos[j1] = valorJC(juego, juego.pos, movsCaballines[juego.c[k][i]][i2], k);
                }

        }

        int j2 = 10 - 20 * k;
        for(i = 1; i <= juego.p[k][0]; i++)
        {
            int k2 = juego.p[k][i] / 10;
            int l3 = juego.p[k][i] % 10;
            if(k2 != i1 * 5 + 2)
            {
                if(l3 >= 2)
                    if(juego.hayPieza[i1][(juego.p[k][i] + j2) - 1])
                    {
                        if(juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + j2) - 1, k1, l1, juego.posRey[k], k))
                        {
                            j1++;
                            puntajesUltimos[j1] = valorJP(juego, juego.pos, juego.p[k][i], (juego.p[k][i] + j2) - 1, k);
                        }
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP + 1 && juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + j2) - 1, k1, l1, juego.posRey[k], k))
                    {
                        j1++;
                        puntajesUltimos[j1] = valorJP(juego, juego.pos, juego.p[k][i], (juego.p[k][i] + j2) - 1, k);
                    }
                if(l3 <= 7)
                    if(juego.hayPieza[i1][juego.p[k][i] + j2 + 1])
                    {
                        if(juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + j2 + 1, k1, l1, juego.posRey[k], k))
                        {
                            j1++;
                            puntajesUltimos[j1] = valorJP(juego, juego.pos, juego.p[k][i], juego.p[k][i] + j2 + 1, k);
                        }
                    } else
                    if(juego.hayEscaqueAP && juego.p[k][i] == juego.cualAP - 1 && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + j2 + 1, k1, l1, juego.posRey[k], k))
                    {
                        j1++;
                        puntajesUltimos[j1] = valorJP(juego, juego.pos, juego.p[k][i], juego.p[k][i] + j2 + 1, k);
                    }
            } else
            {
                if(casVacias[juego.p[k][i] + j2] && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + j2, k1, l1, juego.posRey[k], k))
                {
                    for(j = 1; j <= 4; j++)
                        puntajesUltimos[j1 + j] = valorJP(juego, juego.pos, juego.p[k][i], 25 - j, k);

                    j1 += 4;
                }
                if(l3 >= 2 && juego.hayPieza[i1][(juego.p[k][i] + j2) - 1] && juego.noDejaJaque(juego.p[k][i], (juego.p[k][i] + j2) - 1, k1, l1, juego.posRey[k], k))
                {
                    for(j = 1; j <= 4; j++)
                        puntajesUltimos[j1 + j] = valorJP(juego, juego.pos, juego.p[k][i], 15 - j, k);

                    j1 += 4;
                }
                if(l3 <= 7 && juego.hayPieza[i1][juego.p[k][i] + j2 + 1] && juego.noDejaJaque(juego.p[k][i], juego.p[k][i] + j2 + 1, k1, l1, juego.posRey[k], k))
                {
                    for(j = 1; j <= 4; j++)
                        puntajesUltimos[j1 + j] = valorJP(juego, juego.pos, juego.p[k][i], 35 - j, k);

                    j1 += 4;
                }
            }
        }

        for(i = 1; i <= juego.a[k][0]; i++)
        {
            for(int l2 = 0; l2 <= 3; l2++)
                for(j = juego.a[k][i] + incAlfil[l2]; dentroTablero[j]; j += incAlfil[l2])
                {
                    if(casVacias[j])
                        continue;
                    if(juego.noDejaJaque(juego.a[k][i], j, k1, l1, juego.posRey[k], k))
                    {
                        j1++;
                        puntajesUltimos[j1] = valorJA(juego, juego.pos, j, k);
                    }
                    break;
                }


        }

        for(i = 1; i <= juego.t[k][0]; i++)
        {
            for(int i3 = 0; i3 <= 3; i3++)
                for(j = juego.t[k][i] + incTorre[i3]; dentroTablero[j]; j += incTorre[i3])
                {
                    if(casVacias[j])
                        continue;
                    if(juego.hayPieza[i1][j] && juego.noDejaJaque(juego.t[k][i], j, k1, l1, juego.posRey[k], k))
                    {
                        j1++;
                        puntajesUltimos[j1] = valorJT(juego, juego.pos, j, k);
                    }
                    break;
                }


        }

        for(i = 1; i <= juego.d[k][0]; i++)
        {
            for(int j3 = 0; j3 <= 7; j3++)
                for(j = juego.d[k][i] + incDama[j3]; dentroTablero[j]; j += incDama[j3])
                {
                    if(casVacias[j])
                        continue;
                    if(juego.hayPieza[i1][j] && juego.noDejaJaque(juego.d[k][i], j, k1, l1, juego.posRey[k], k))
                    {
                        j1++;
                        puntajesUltimos[j1] = valorJD(juego, juego.pos, j, k);
                    }
                    break;
                }


        }

        for(int k3 = 0; k3 <= 7; k3++)
            if(dentroTablero[movsReyuzcos[juego.posRey[k]][k3]] && juego.hayPieza[i1][movsReyuzcos[juego.posRey[k]][k3]] && juego.noDejaJaque(juego.posRey[k], movsReyuzcos[juego.posRey[k]][k3], movsReyuzcos[juego.posRey[k]][k3] % 10, movsReyuzcos[juego.posRey[k]][k3] / 10, movsReyuzcos[juego.posRey[k]][k3], k))
            {
                j1++;
                puntajesUltimos[j1] = vDePiezas[juego.pos[movsReyuzcos[juego.posRey[k]][k3]]];
            }

        return j1 + 1;
    }

    private final int valorJC(Juego juego, int ai[], int k, int l)
    {
        int i1 = vDePiezas[ai[k]];
        if(juego.minimoatacante(k, 1 - l) != 0)
            return i1 - vDePiezas[1];
        else
            return i1;
    }

    private final int valorJT(Juego juego, int ai[], int k, int l)
    {
        int i1 = vDePiezas[ai[k]];
        if(juego.minimoatacante(k, 1 - l) != 0)
            return i1 - vDePiezas[2];
        else
            return i1;
    }

    private final int valorJA(Juego juego, int ai[], int k, int l)
    {
        int i1 = vDePiezas[ai[k]];
        if(juego.minimoatacante(k, 1 - l) != 0)
            return i1 - vDePiezas[3];
        else
            return i1;
    }

    private final int valorJD(Juego juego, int ai[], int k, int l)
    {
        int i1 = vDePiezas[ai[k]];
        if(juego.minimoatacante(k, 1 - l) != 0)
            return i1 - vDePiezas[4];
        else
            return i1;
    }

    private final int valorJP(Juego juego, int ai[], int k, int l, int i1)
    {
        if(septima[i1][k])
        {
            int j1 = ((k + 8) - 20 * i1) + l / 10;
            if(juego.minimoatacante(j1, 1 - i1) != 0)
                return -vDePiezas[5];
            else
                return (vDePiezas[ai[j1]] + vDePiezas[l % 10]) - vDePiezas[5];
        }
        if(k / 10 == 5 - i1 && ai[l] == 0)
            if(juego.minimoatacante(l, 1 - i1) != 0)
                return 0;
            else
                return vDePiezas[5];
        int k1 = vDePiezas[ai[l]];
        if(juego.minimoatacante(l, 1 - i1) != 0)
            return k1 - vDePiezas[5];
        else
            return k1;
    }

    private void JuegoConNuevaJugada(Juego juego, Juego juego1, int k, int l, int i1)
    {
        System.arraycopy(juego1.pos, 11, juego.pos, 11, 78);
        System.arraycopy(juego1.casVacias, 11, juego.casVacias, 11, 78);
        juego.posRey[0] = juego1.posRey[0];
        juego.posRey[1] = juego1.posRey[1];
        juego.ped[0] = juego1.ped[0];
        juego.ped[1] = juego1.ped[1];
        juego.pei[0] = juego1.pei[0];
        juego.pei[1] = juego1.pei[1];
        for(j = 0; j <= 1; j++)
        {
            for(i = 0; i <= juego1.p[j][0]; i++)
                juego.p[j][i] = juego1.p[j][i];

            for(i = 0; i <= juego1.t[j][0]; i++)
                juego.t[j][i] = juego1.t[j][i];

            for(i = 0; i <= juego1.c[j][0]; i++)

                juego.c[j][i] = juego1.c[j][i];

            for(i = 0; i <= juego1.a[j][0]; i++)
                juego.a[j][i] = juego1.a[j][i];

            for(i = 0; i <= juego1.d[j][0]; i++)
                juego.d[j][i] = juego1.d[j][i];

            for(i = 0; i <= 8; i++)
                juego.peonesEnLinea[j][i] = juego1.peonesEnLinea[j][i];

            for(i = 11; i <= 88; i++)
            {
                juego.casAtPorP[j][i] = juego1.casAtPorP[j][i];
                juego.hayPieza[j][i] = juego1.hayPieza[j][i];
            }

        }

        juego.potencia[0] = juego1.potencia[0];
        juego.potencia[1] = juego1.potencia[1];
        juego.material[0] = juego1.material[0];
        juego.material[1] = juego1.material[1];
        juego.hayEscaqueAP = juego1.hayEscaqueAP;
        juego.cualAP = juego1.cualAP;
        juego.contador50m = juego1.contador50m;
        juego.mover(k, l, i1);
    }

    private void ordenar(int k, int l)
    {
        int ai[] = new int[222];
        for(int i1 = 0; i1 <= k; i1++)
            ai[i1] = puntajesPrimeros[i1];

        for(int k1 = 0; k1 < l; k1++)
        {
            int j1 = -50000;
            int l1 = -1;
            for(int i2 = 0; i2 < k; i2++)
                if(ai[i2] > j1)
                {
                    j1 = ai[i2];
                    l1 = i2;
                }

            orden[k1] = l1;
            ai[l1] = -50000;
        }

    }

    private final int buscarempates(Juego juego, int k, int l)
    {
        if(juego.p[0][0] == 0 && juego.p[1][0] == 0)
        {
            if(!rinconTablero[juego.posRey[l]] && (juego.potencia[k] <= 3 && juego.potencia[l] <= 3 || juego.potencia[k] == 4 && juego.potencia[l] == 0))
                return 0;
            if(!bordeTablero[juego.posRey[l]] && juego.potencia[k] == 4 && juego.potencia[l] <= 3)
                return 0;
            if(!bordeTablero[juego.posRey[k]] && juego.potencia[k] == 5 && juego.potencia[l] == 5 && juego.t[k][0] == 1 && juego.t[l][0] == 1)
                return 1;
            if(!bordeTablero[juego.posRey[k]] && juego.potencia[l] == 5 && juego.t[l][0] == 1 && juego.potencia[k] == 3 && cualInc[juego.posRey[k]][juego.t[l][1]] == 0)
                return 0;
        }
        if(juego.potencia[l] <= 3 && juego.p[l][0] == 0)
            return 1;
        if(juego.p[l][0] == 1 && juego.p[k][0] == 0)
            if(juego.potencia[k] == 0 && juego.potencia[l] <= 3)
            {
                if(k == 0)
                    if(juego.potencia[1] == 0)
                    {
                        if(juego.posRey[0] == juego.p[1][1] - 10 && !enPrimeraLinea[0][juego.posRey[0]])
                            return 0;
                        if(juego.posRey[0] == juego.p[1][1] - 20)
                            return 0;
                        if(juego.posRey[0] == juego.posRey[1] - 20 && Math.abs(juego.posRey[1] - juego.p[l][1]) == 1 && !enPrimeraLinea[0][juego.posRey[0]])
                            return 0;
                        if(juego.p[1][1] % 10 == 8)
                        {
                            if(juego.posRey[0] % 10 >= 7 && juego.posRey[0] / 10 <= juego.p[1][1] / 10 + 2)
                                return 0;
                        } else
                        if(juego.p[1][1] % 10 == 1 && juego.posRey[0] % 10 <= 2 && juego.posRey[0] / 10 <= juego.p[1][1] / 10 + 2)
                            return 0;
                    } else
                    if(juego.potencia[1] == 3)
                    {
                        if(juego.p[1][1] % 10 == 8)
                        {
                            if((juego.posRey[0] == 18 || juego.posRey[0] == 27) && casNegra[juego.a[1][1]])
                                return 0;
                        } else
                        if(juego.p[1][1] % 10 == 1 && (juego.posRey[0] == 11 || juego.posRey[1] == 22) && casBlanca[juego.a[1][1]])
                            return 0;
                    } else
                    {

                        if(juego.posRey[0] == 18 && juego.p[l][1] == 28)
                            return 0;
                        if(juego.posRey[0] == 11 && juego.p[l][1] == 21)
                            return 0;
                    }
                if(k == 1)
                    if(juego.potencia[0] == 0)
                    {
                        if(juego.posRey[1] == juego.p[0][1] + 10 && !enPrimeraLinea[1][juego.posRey[1]])
                            return 0;
                        if(juego.posRey[1] == juego.p[0][1] + 20)
                            return 0;
                        if(juego.posRey[1] == juego.posRey[0] + 20 && Math.abs(juego.posRey[0] - juego.p[l][0]) == 1 && !enPrimeraLinea[1][juego.posRey[1]])
                            return 0;
                        if(juego.p[0][1] % 10 == 8)
                        {
                            if(juego.posRey[1] % 10 >= 7 && juego.posRey[1] / 10 >= juego.p[0][1] / 10 - 2)
                                return 0;
                        } else
                        if(juego.p[1][1] % 10 == 1 && juego.posRey[1] % 10 <= 2 && juego.posRey[1] / 10 >= juego.p[0][1] / 10 - 2)
                            return 0;
                    } else
                    if(juego.potencia[0] == 3)
                    {
                        if(juego.p[0][1] % 10 == 8)
                        {
                            if((juego.posRey[1] == 88 || juego.posRey[1] == 77) && casBlanca[juego.a[0][1]])
                                return 0;
                        } else
                        if(juego.p[0][1] % 10 == 1 && (juego.posRey[1] == 81 || juego.posRey[1] == 72) && casNegra[juego.a[0][1]])
                            return 0;
                    } else
                    {
                        if(juego.posRey[1] == 88 && juego.p[0][1] == 78)
                            return 0;
                        if(juego.posRey[1] == 81 && juego.p[0][1] == 71)
                            return 0;
                    }
            } else
            if(juego.potencia[k] == 5 && juego.potencia[l] == 5 && juego.t[k][0] == 1 && juego.t[l][0] == 1 && septima[l][juego.p[l][1]] && juego.t[l][1] % 10 == juego.t[k][1] % 10 && juego.t[l][1] == juego.p[l][1] + inc[l] && septima[l][juego.posRey[k]] && !atRey[juego.posRey[k]][juego.t[l][1]] && (Math.abs(juego.posRey[k] % 10 - juego.p[l][1] % 10) <= 2 || juego.posRey[k] % 10 <= 2 || juego.posRey[k] % 10 >= 7) && (!atRey[juego.posRey[l]][juego.t[k][1]] || !atRey[juego.posRey[l]][juego.p[l][1]]))
                return 0;
        return -1;
    }

    private final int[] escape(Juego juego, int k, int l, int i1, int j1, int k1, int l1, 
            int i2, int j2)
    {
        int ai[] = new int[l + l + 2];
        int k2 = 1 - k;
        for(int l2 = 1; l2 <= l; l2++)
        {
            ai[l2 + l2] = jugsAnteriores[l2 + 4][0];
            ai[l2 + l2 + 1] = jugsAnteriores[l2 + 4][1];
        }

        if(juego.contador50m >= 4 && jugsAnteriores[l + 2][0] == j1 && jugsAnteriores[l + 2][1] == i1 && jugsAnteriores[l + 1][0] == jugsAnteriores[l + 3][1] && jugsAnteriores[l + 1][1] == jugsAnteriores[l + 3][0])
        {
            ai[0] = 0;
            return ai;
        }
        int i3 = jugPos(juego, k, l);
        if(i3 == 0)
        {
            if(bandoC == k)
            {
                ai[0] = -25050 + l;
                ai[1] = l;
            } else
            {
                if(l == 1)
                {
                    victoria = 1;
                    ai[0] = 25100;
                    ai[1] = 1;
                    return ai;
                }
                ai[1] = l;
                if(!matearemos || matearemos && profuMortal > l)
                    ai[0] = 25050 - l;
                else
                    ai[0] = 19999;
            }
            return ai;
        }
        if(l < 29)
            if(i3 <= 3)
            {
                seextendiojaque[l] = true;
                k1++;
            } else
            if(l >= 3 || l >= k1 - 3)
            {
                seextendiojaque[l] = true;
                k1++;
            } else
            if(j2 < 1)
            {
                seextendiojaque[l] = true;
                j2++;
                k1++;
            }
        if(bandoC == k)
        {
            ai[0] = -30000;
            for(int j3 = 0; j3 < i3; j3++)
                if(juego.hayPieza[k2][pfins[j3][l]])
                {
                    JuegoConNuevaJugada(juegos[l], juego, pinis[j3][l], pfins[j3][l], k);
                    int ai1[] = jugada(juegos[l], k2, l + 1, pinis[j3][l], pfins[j3][l], k1, l1, i2, j2);
                    if(ai1[0] > ai[0])
                    {
                        ai = ai1;
                        if(ai[0] >= l1)
                            return ai;
                    }
                    if(i2 < ai1[0])
                        i2 = ai1[0];
                    pinis[j3][l] = 0;
                }

            for(int l3 = 0; l3 < i3; l3++)
                if(juego.casVacias[pfins[l3][l]] && pinis[l3][l] != 0 && juego.casAtPorP[k2][pfins[l3][l]] == 0 && (k * 2 - 1) * ((pinis[l3][l] - pfins[l3][l]) / 10) >= 0)
                {
                    JuegoConNuevaJugada(juegos[l], juego, pinis[l3][l], pfins[l3][l], k);
                    int ai2[] = jugada(juegos[l], k2, l + 1, pinis[l3][l], pfins[l3][l], k1, l1, i2, j2);
                    if(ai2[0] > ai[0])
                    {
                        ai = ai2;
                        if(ai[0] >= l1)
                        {
                            terCortante[l][0] = pinis[l3][l];
                            terCortante[l][1] = pfins[l3][l];
                            return ai;
                        }
                    }
                    if(i2 < ai2[0])
                        i2 = ai2[0];
                    pinis[l3][l] = 0;
                }

            for(int j4 = 0; j4 < i3; j4++)
                if(pinis[j4][l] != 0)

                {
                    JuegoConNuevaJugada(juegos[l], juego, pinis[j4][l], pfins[j4][l], k);
                    int ai3[] = jugada(juegos[l], k2, l + 1, pinis[j4][l], pfins[j4][l], k1, l1, i2, j2);
                    if(ai3[0] > ai[0])
                    {
                        ai = ai3;
                        if(ai[0] >= l1)
                        {
                            terCortante[l][0] = pinis[j4][l];
                            terCortante[l][1] = pfins[j4][l];
                            return ai;
                        }
                    }
                    if(i2 < ai3[0])
                        i2 = ai3[0];
                }

            return ai;
        }
        ai[0] = 30000;
        for(int k3 = 0; k3 < i3; k3++)
            if(juego.hayPieza[k2][pfins[k3][l]])
            {
                JuegoConNuevaJugada(juegos[l], juego, pinis[k3][l], pfins[k3][l], k);
                int ai4[] = jugada(juegos[l], k2, l + 1, pinis[k3][l], pfins[k3][l], k1, l1, i2, j2);
                if(ai4[0] < ai[0])
                {
                    ai = ai4;
                    if(ai[0] <= i2)
                        return ai;
                }
                if(l1 > ai4[0])
                    l1 = ai4[0];
                pinis[k3][l] = 0;
            }

        for(int i4 = 0; i4 < i3; i4++)
            if(juego.casVacias[pfins[i4][l]] && pinis[i4][l] != 0 && juego.casAtPorP[k2][pfins[i4][l]] == 0 && (k * 2 - 1) * ((pinis[i4][l] - pfins[i4][l]) / 10) >= 0)
            {
                JuegoConNuevaJugada(juegos[l], juego, pinis[i4][l], pfins[i4][l], k);
                int ai5[] = jugada(juegos[l], k2, l + 1, pinis[i4][l], pfins[i4][l], k1, l1, i2, j2);
                if(ai5[0] < ai[0])
                {
                    ai = ai5;
                    if(ai[0] <= i2)
                    {
                        terCortante[l][0] = pinis[i4][l];
                        terCortante[l][1] = pfins[i4][l];
                        return ai;
                    }
                }
                if(l1 > ai5[0])
                    l1 = ai5[0];
                pinis[i4][l] = 0;
            }

        for(int k4 = 0; k4 < i3; k4++)
            if(pinis[k4][l] != 0)
            {
                JuegoConNuevaJugada(juegos[l], juego, pinis[k4][l], pfins[k4][l], k);
                int ai6[] = jugada(juegos[l], k2, l + 1, pinis[k4][l], pfins[k4][l], k1, l1, i2, j2);
                if(ai6[0] < ai[0])
                {
                    ai = ai6;
                    if(ai[0] <= i2)
                    {
                        terCortante[l][0] = pinis[k4][l];
                        terCortante[l][1] = pfins[k4][l];
                        return ai;
                    }
                }
                if(l1 > ai6[0])
                    l1 = ai6[0];
            }

        return ai;
    }

    private final int[] jugada(Juego juego, int k, int l, int i1, int j1, int k1, int l1, 
            int i2, int j2)
    {
        seextendiojaque[l] = false;
        esCaptura[l + 3] = juego.seComio;
        comida[l + 3] = juego.quesecomio;
        int k2 = 1 - k;
        posAnalizadas++;
        if(jugnula[l])
        {
            jugsAnteriores[l + 4][0] = -l;
            jugsAnteriores[l + 4][1] = -l;
        } else
        {
            jugsAnteriores[l + 4][0] = i1;
            jugsAnteriores[l + 4][1] = j1;
        }
        if(!jugnula[l - 1] && !juego.noNosJaquearon(juego.posRey[k], i1, j1, k))
        {
            estabaJaque[l] = true;
            if(l < 29)
                return escape(juego, k, l, i1, j1, k1, l1, i2, j2);
        } else
        {
            estabaJaque[l] = false;
        }
        int ai[] = new int[l + l + 2];
        for(int l2 = 1; l2 <= l; l2++)
        {
            ai[l2 + l2] = jugsAnteriores[l2 + 4][0];
            ai[l2 + l2 + 1] = jugsAnteriores[l2 + 4][1];
        }

        if(juego.contador50m >= 100)
        {
            ai[0] = 0;
            return ai;
        }
        boolean flag = false;
        if(juego.contador50m >= 4 && jugsAnteriores[l + 2][0] == j1 && jugsAnteriores[l + 2][1] == i1 && jugsAnteriores[l + 1][0] == jugsAnteriores[l + 3][1] && jugsAnteriores[l + 1][1] == jugsAnteriores[l + 3][0])
        {
            ai[0] = 0;
            return ai;
        }
        if(juego.p[0][0] <= 1 && juego.p[1][0] <= 1 && juego.potencia[k] <= 10 && juego.potencia[k2] <= 10)
        {
            int i3 = buscarempates(juego, k, k2);
            if(i3 == 0)
            {
                ai[0] = 0;
                return ai;
            }
            if(i3 == 1)
                if(bandoC == k)
                {
                    if(0 >= l1)
                    {
                        ai[0] = l1;
                        return ai;
                    }
                } else
                if(0 <= i2)
                {
                    ai[0] = i2;
                    return ai;
                }
        }
        if(posAnalizadas > proximaInf)
        {
            if(conTiempo && System.currentTimeMillis() > ultimaActR + 990L)
            {
                ultimaActR = System.currentTimeMillis();
                soloActualizarReloj = true;
            }
            mensaje = "Cont. : ";
            for(int j3 = 1; j3 <= l; j3++)
            {
                if(jugsAnteriores[j3 + 4][0] <= 0)
                {
                    mensaje = mensaje + "n-n";
                } else
                {
                    mensaje = mensaje + nombresCas[jugsAnteriores[j3 + 4][0]];
                    if(esCaptura[j3 + 3])
                        mensaje = mensaje + "x";
                    else
                        mensaje = mensaje + "-";
                }
                mensaje = mensaje + nombresCas[jugsAnteriores[j3 + 4][1]] + " ";
            }

            cuantoMas = 1800 + (int)(Math.random() * 40D);
            proximaInf = posAnalizadas + cuantoMas;
        }
        if(j2 < 1)
        {
            if(juego.ultimaPieza == 5 && i1 / 10 == k * 3 + 3 && l >= k1 - 1 && juego.potencia[k] <= 17)
            {
                flag = true;
                j2++;
                k1++;
            }
            if(!flag && l >= k1)
            {
                int k3 = 0;
                for(int k4 = 1; k4 <= juego.d[k][0]; k4++)
                    if(juego.casAtPorP[k2][juego.d[k][k4]] > 0)
                        k3++;

                for(int i5 = 1; i5 <= juego.c[k][0]; i5++)
                    if(juego.casAtPorP[k2][juego.c[k][i5]] > 0)
                        k3++;

                for(int l5 = 1; l5 <= juego.a[k][0]; l5++)
                    if(juego.casAtPorP[k2][juego.a[k][l5]] > 0)
                        k3++;

                for(int j6 = 1; j6 <= juego.t[k][0]; j6++)
                    if(juego.casAtPorP[k2][juego.t[k][j6]] > 0)
                        k3++;

                if(k3 > 0)
                {
                    j2++;
                    flag = true;
                }
            }
            if(!flag && l >= k1 && juego.ultimaPieza == 2 && i1 % 10 != j1 % 10 && juego.peonesEnLinea[k][j1 % 10] == 0 && juego.peonesEnLinea[k2][j1 % 10] == 0 && juego.d[k][0] > 0 && juego.d[k][1] % 10 == j1 % 10 && juego.posRey[k] % 10 == j1 % 10)
            {
                j2++;
                flag = true;
            }
            if(l >= k1 && !flag)
                if(k == 0)
                {
                    if(juego.pos[11] == 2 && juego.hayPieza[k2][22] && vaPorDiagonales[juego.pos[22]] && juego.pos[21] == 5 && (juego.pos[12] == 1 || juego.pos[12] == 3) && !vaPorDiagonales[juego.pos[33]])
                    {
                        j2++;
                        flag = true;
                    } else
                    if(juego.pos[18] == 2 && juego.hayPieza[k2][27] && vaPorDiagonales[juego.pos[27]] && juego.pos[28] == 5 && (juego.pos[17] == 1 || juego.pos[17] == 3) && !vaPorDiagonales[juego.pos[36]])
                    {
                        j2++;
                        flag = true;
                    }
                } else
                if(juego.pos[81] == 12 && juego.hayPieza[k2][72] && vaPorDiagonales[juego.pos[72]] && juego.pos[71] == 15 && (juego.pos[82] == 11 || juego.pos[82] == 13) && !vaPorDiagonales[juego.pos[63]])
                {
                    j2++;
                    flag = true;
                } else
                if(juego.pos[88] == 12 && juego.hayPieza[k2][77] && vaPorDiagonales[juego.pos[77]] && juego.pos[78] == 15 && (juego.pos[87] == 11 || juego.pos[87] == 13) && !vaPorDiagonales[juego.pos[66]])
                {
                    j2++;
                    flag = true;
                }
            if(l >= k1 && !flag && juego.d[k][0] > 0)
                if(juego.ultimaPieza == 2)
                {
                    int l3 = juego.d[k][1] - j1;
                    if(l3 == 1 || l3 == -1 || l3 == 10 || l3 == -10)
                    {
                        flag = true;
                        j2++;
                    }
                } else
                if(juego.ultimaPieza == 1 && atCab[j1][juego.d[k][1]])
                {
                    flag = true;
                    j2++;
                }
            if(!flag && l >= 2 && l <= k1 && !seextendiojaque[l - 1] && esCaptura[l + 3] && esCaptura[l + 2] && calidad[comida[l + 3]] == calidad[comida[l + 2]] && (juego.ultimaPieza == 5 || juego.casAtPorP[k][j1] == 0) && j1 == jugsAnteriores[l + 3][1] && (!esCaptura[l + 1] || calidad[comida[l + 1]] != calidad[comida[l + 2]] || jugsAnteriores[l + 2][1] != j1))
            {
                k1++;
                j2++;
            }
        }
        int i4 = juego.material[bandoC] - juego.material[bandoCont];
        cualturno = k;
        int l4 = k1 - l;
        if(l4 < 0)
            l4 = 0;
        if(l >= k1 && l < k1 + 8 && l < 29 && !flag)
        {
            ai[0] = i4;
            if(bandoC == k)
            {
                if(ai[0] >= l1 + 1000)
                    return ai;
                ai[0] -= todoLoBuenoDe(juego, bandoCont);
                if(ai[0] >= l1)
                    return ai;
                ai[0] += todoLoBuenoDe(juego, bandoC);
                if(ai[0] >= l1)
                    return ai;
            } else
            {
                if(ai[0] <= i2 - 1000)
                    return ai;
                ai[0] += todoLoBuenoDe(juego, bandoC);
                if(ai[0] <= i2)
                    return ai;
                ai[0] -= todoLoBuenoDe(juego, bandoCont);
                if(ai[0] <= i2)
                    return ai;
            }
            int j5 = pseudoCapturasPos(juego, k, l);
            if(j5 == 0)
            {
                if(bandoC == k && i2 > ai[0])
                    ai[0] = i2;
                else
                if(bandoC != k && l1 < ai[0])
                    ai[0] = l1;
                return ai;
            }
            if(bandoC == k)
            {
                if(ai[0] > i2)
                {
                    i2 = ai[0];
                } else
                {
                    int k6 = i2 - ai[0];
                    ai[0] = i2;
                    boolean flag4 = true;
                    for(int j10 = 0; j10 < j5; j10++)
                    {
                        int i8;
                        if(piezamov[j10][l] != 5)
                            i8 = juego.pos[cfins[j10][l]];
                        else
                        if(septima[k][cinis[j10][l]])
                            i8 = cfins[j10][l] % 10;
                        else
                        if(juego.pos[cfins[j10][l]] == 0)
                            i8 = 5;
                        else
                            i8 = juego.pos[cfins[j10][l]];
                        int j11 = vDePiezas[i8];
                        if(juego.casAtPorP[k2][cfins[j10][l]] > 0)
                        {
                            if(calidad[piezamov[j10][l]] < calidad[i8])
                            {
                                cinis[j10][l] = cinis[j5 - 1][l];
                                cfins[j10][l] = cfins[j5 - 1][l];
                                j5--;
                                j10--;
                                continue;
                            }
                            j11 += vDePiezas[5] - vDePiezas[piezamov[j10][l]];
                        }
                        if(j11 + 220 < k6)
                        {
                            if(piezamov[j10][l] == 5)
                            {
                                if(juego.posRey[k2] == cfins[j10][l] + inc[k] + 1 || juego.posRey[k2] == (cfins[j10][l] + inc[k]) - 1)
                                    flag4 = false;
                            } else
                            if(piezamov[j10][l] == 1)
                            {
                                if(atCab[cfins[j10][l]][juego.posRey[k2]])
                                    flag4 = false;
                            } else
                            if(piezamov[j10][l] < 5)
                            {
                                int j12 = cualInc[juego.posRey[k2]][cfins[j10][l]];
                                if(j12 != 0 && atacapor[piezamov[j10][l]][Math.abs(j12)])
                                {
                                    flag4 = false;
                                    for(int i14 = juego.posRey[k2] + j12; i14 != cfins[j10][l]; i14 += j12)
                                    {
                                        if(juego.casVacias[i14])
                                            continue;
                                        flag4 = true;
                                        break;
                                    }

                                }
                            }
                            if(flag4)
                                cinis[j10][l] = 0;
                        }
                    }

                }
                for(int l6 = 0; l6 <= 3; l6++)
                {
                    for(int j8 = 0; j8 < j5; j8++)
                        if(cinis[j8][l] != 0 && calidad[juego.pos[cfins[j8][l]]] == l6 && (calidad[piezamov[j8][l]] >= l6 || juego.minimoatacante(cfins[j8][l], k2) == 0))
                        {
                            if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[j8][l], cfins[j8][l], k))
                            {
                                JuegoConNuevaJugada(juegos[l], juego, cinis[j8][l], cfins[j8][l], k);
                                int ai1[] = jugada(juegos[l], k2, l + 1, cinis[j8][l], cfins[j8][l], k1, l1, i2, j2);
                                if(ai1[0] > ai[0])
                                {
                                    ai = ai1;
                                    if(ai[0] >= l1)
                                        return ai;
                                }
                                if(i2 < ai1[0])
                                    i2 = ai1[0];
                            }
                            cinis[j8][l] = 0;
                        }

                }

                for(int k8 = 0; k8 < j5; k8++)
                    if(cinis[k8][l] != 0 && juego.casAtPorP[k2][cfins[k8][l]] == 0 && juego.noDejaJaqueHacia(juego.posRey[k], cinis[k8][l], cfins[k8][l], k))
                    {
                        JuegoConNuevaJugada(juegos[l], juego, cinis[k8][l], cfins[k8][l], k);
                        int ai2[] = jugada(juegos[l], k2, l + 1, cinis[k8][l], cfins[k8][l], k1, l1, i2, j2);
                        if(ai2[0] > ai[0])
                        {
                            ai = ai2;
                            if(ai[0] >= l1)
                                return ai;
                        }
                        if(i2 < ai2[0])
                            i2 = ai2[0];
                    }

            } else
            {
                if(ai[0] < l1)
                {
                    l1 = ai[0];
                } else
                {
                    int i7 = ai[0] - l1;
                    ai[0] = l1;
                    boolean flag5 = true;
                    for(int k10 = 0; k10 < j5; k10++)
                    {
                        int l8;
                        if(piezamov[k10][l] != 5)
                            l8 = juego.pos[cfins[k10][l]];
                        else
                        if(septima[k][cinis[k10][l]])
                            l8 = cfins[k10][l] % 10;
                        else
                        if(juego.pos[cfins[k10][l]] == 0)
                            l8 = 5;
                        else
                            l8 = juego.pos[cfins[k10][l]];
                        int k11 = vDePiezas[l8];
                        if(juego.casAtPorP[k2][cfins[k10][l]] > 0)
                        {
                            if(calidad[piezamov[k10][l]] < calidad[l8])
                            {
                                cinis[k10][l] = cinis[j5 - 1][l];
                                cfins[k10][l] = cfins[j5 - 1][l];
                                j5--;
                                k10--;
                                continue;
                            }
                            k11 += vDePiezas[5] - vDePiezas[piezamov[k10][l]];
                        }
                        if(k11 + 220 < i7)
                        {
                            if(piezamov[k10][l] == 5)
                            {
                                if(juego.posRey[k2] == cfins[k10][l] + inc[k] + 1 || juego.posRey[k2] == (cfins[k10][l] + inc[k]) - 1)
                                    flag5 = false;
                            } else
                            if(piezamov[k10][l] == 1)
                            {
                                if(atCab[cfins[k10][l]][juego.posRey[k2]])
                                    flag5 = false;
                            } else
                            if(piezamov[k10][l] < 5)
                            {
                                int k12 = cualInc[juego.posRey[k2]][cfins[k10][l]];
                                if(k12 != 0 && atacapor[piezamov[k10][l]][Math.abs(k12)])
                                {
                                    flag5 = false;
                                    for(int j14 = juego.posRey[k2] + k12; j14 != cfins[k10][l]; j14 += k12)
                                    {
                                        if(casVacias[j14])
                                            continue;
                                        flag5 = true;
                                        break;
                                    }

                                }
                            }
                            if(flag5)
                                cinis[k10][l] = 0;
                        }
                    }

                }
                for(int j7 = 0; j7 <= 3; j7++)
                {
                    for(int i9 = 0; i9 < j5; i9++)
                        if(cinis[i9][l] != 0 && calidad[juego.pos[cfins[i9][l]]] == j7 && (calidad[piezamov[i9][l]] >= j7 || juego.minimoatacante(cfins[i9][l], k2) == 0))
                        {
                            if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[i9][l], cfins[i9][l], k))
                            {
                                JuegoConNuevaJugada(juegos[l], juego, cinis[i9][l], cfins[i9][l], k);
                                int ai3[] = jugada(juegos[l], k2, l + 1, cinis[i9][l], cfins[i9][l], k1, l1, i2, j2);
                                if(ai3[0] < ai[0])
                                {
                                    ai = ai3;
                                    if(ai[0] <= i2)
                                        return ai;
                                }
                                if(l1 > ai3[0])
                                    l1 = ai3[0];
                            }
                            cinis[i9][l] = 0;
                        }

                }

                for(int j9 = 0; j9 < j5; j9++)
                    if(cinis[j9][l] != 0 && juego.casAtPorP[k2][cfins[j9][l]] == 0 && juego.noDejaJaqueHacia(juego.posRey[k], cinis[j9][l], cfins[j9][l], k))
                    {
                        JuegoConNuevaJugada(juegos[l], juego, cinis[j9][l], cfins[j9][l], k);
                        int ai4[] = jugada(juegos[l], k2, l + 1, cinis[j9][l], cfins[j9][l], k1, l1, i2, j2);
                        if(ai4[0] < ai[0])
                        {
                            ai = ai4;
                            if(ai[0] <= i2)
                                return ai;
                        }
                        if(l1 > ai4[0])
                            l1 = ai4[0];
                    }

            }
            return ai;
        }
        if(l >= 29 || !flag && l >= k1)
        {
            ai[0] = i4;
            if(bandoC == k && ai[0] >= l1 + 1000)
                return ai;
            if(bandoC != k && ai[0] <= i2 - 1000)
                return ai;
            ai[0] += todoLoBuenoDe(juego, bandoC) - todoLoBuenoDe(juego, bandoCont);
            if(bandoC == k && ai[0] >= l1)
                return ai;
            if(bandoC != k && ai[0] <= i2)
                return ai;
            int k5 = fuertesPos(juego, k, l);
            if(k5 == 0)
                return ai;
            int i6 = 0;
            for(int k7 = 0; k7 < k5; k7++)
                if(puntajesUltimos[k7] > i6)
                    i6 = puntajesUltimos[k7];

            if(bandoC != k)
                i6 = -i6;
            ai[0] += i6;
            return ai;
        }
        boolean flag1 = false;
        if(!jugnula[l - 1] && !flag && !matearemos && l < k1 && juego.potencia[k] >= 10 && l >= 2)
        {
            boolean flag2 = false;
            boolean flag3 = false;
            cualturno = k;
            i4 += todoLoBuenoDe(juego, bandoC) - todoLoBuenoDe(juego, bandoCont);
            if(l4 <= 3)
            {
                flag1 = true;
                if(bandoC == k && i4 >= l1 + margenDescarteSC[l4] || bandoC != k && i4 <= i2 - margenDescarteSC[l4])
                    flag2 = true;
            }
            if(l4 >= 2)
                if(bandoC == k)
                {
                    if(l4 > 3 && i4 >= l1 || l4 <= 3 && i4 >= l1 + 30)
                        flag3 = true;
                } else
                if(l4 > 3 && i4 <= i2 || l4 <= 3 && i4 <= i2 - 30)
                    flag3 = true;
            if(flag2)
            {
                int l9 = pseudoCapturasPos(juego, k2, l);
                int l10 = 0;
                int l11 = 0;
                for(i = 0; i < l9; i++)
                {
                    int k14 = juego.pos[cfins[i][l]];
                    int l12;
                    if(piezamov[i][l] == 5 && septima[k2][cinis[i][l]])
                    {
                        l12 = vDePiezas[cfins[i][l] % 10];
                    } else
                    {
                        l12 = vDePiezas[k14];
                        int k16 = juego.minimoatacante(cfins[i][l], k);
                        if(k16 != 0)
                        {
                            boolean flag10 = false;
                            for(int k18 = 0; k18 < l9; k18++)
                            {
                                if(i == k18 || cfins[i][l] != cfins[k18][l])
                                    continue;
                                flag10 = true;
                                break;
                            }

                            if(!flag10 && juego.topamoscondeslizante(cinis[i][l], k2, cualInc[cfins[i][l]][cinis[i][l]]))
                                flag10 = true;
                            if(calidad[k14] > calidad[piezamov[i][l]] && (!flag10 || l12 + vDePiezas[k16] < vDePiezas[piezamov[i][l]]))
                                l10++;
                            if(!flag10)
                                l12 -= vDePiezas[piezamov[i][l]];
                            else
                            if(vDePiezas[k16] < vDePiezas[piezamov[i][l]])
                                l12 += vDePiezas[k16] - vDePiezas[piezamov[i][l]];
                        }
                    }
                    if(l12 > l11)
                        l11 = l12;
                }

                if(bandoC == k)
                {
                    if(l9 == 0 || l9 == l10)
                    {
                        ai[0] = l1;
                        return ai;
                    }
                    if(i4 >= l1 + margenDescarteCC[l4] + l11)
                    {
                        ai[0] = l1;
                        return ai;
                    }
                } else
                {
                    if(l9 == 0 || l9 == l10)
                    {
                        ai[0] = i2;
                        return ai;
                    }
                    if(i4 <= i2 - margenDescarteCC[l4] - l11)
                    {
                        ai[0] = i2;
                        return ai;
                    }
                }
            }
            if(flag3)
                if(bandoC == k)
                {
                    jugnula[l] = true;
                    int ai5[] = jugada(juego, k2, l + 1, (k1 - 3) + 1, 0, 0, l1, l1 - 1, j2);
                    jugnula[l] = false;
                    if(ai5[0] >= l1)
                        return ai5;
                } else
                {
                    jugnula[l] = true;
                    int ai6[] = jugada(juego, k2, l + 1, (k1 - 3) + 1, 0, 0, i2 + 1, i2, j2);
                    jugnula[l] = false;
                    if(ai6[0] <= i2)
                        return ai6;
                }
        }
        if(l4 == 0)
            l4 = 1;
        int l7 = 0;
        int k9 = i2;
        int i10 = l1;
        int i11 = pseudoCapturasPos(juego, k, l);
        if(bandoC == k)
        {
            ai[0] = -30000;
            if(flag1 && i4 <= i2 - 230 - 1000 * (l4 - 1) - vDePiezas[5])
            {
                l7++;
                int i12 = i2 - i4;
                boolean flag8 = true;
                for(int l16 = 0; l16 < i11; l16++)
                {
                    int i13;
                    if(piezamov[l16][l] != 5)
                        i13 = juego.pos[cfins[l16][l]];
                    else
                    if(septima[k][cinis[l16][l]])
                        i13 = cfins[l16][l] % 10;
                    else
                    if(juego.pos[cfins[l16][l]] == 0)
                        i13 = 5;
                    else
                        i13 = juego.pos[cfins[l16][l]];
                    if(vDePiezas[i13] + 230 + 1000 * (l4 - 1) < i12)
                    {
                        if(piezamov[l16][l] == 5)
                        {
                            if(juego.posRey[k2] == cfins[l16][l] + inc[k] + 1 || juego.posRey[k2] == (cfins[l16][l] + inc[k]) - 1)
                                flag8 = false;
                        } else
                        if(piezamov[l16][l] == 1)
                        {
                            if(atCab[cfins[l16][l]][juego.posRey[k2]])
                                flag8 = false;
                        } else
                        if(piezamov[l16][l] < 5)
                        {
                            int k17 = cualInc[juego.posRey[k2]][cfins[l16][l]];
                            if(k17 != 0 && atacapor[piezamov[l16][l]][Math.abs(k17)])
                            {
                                flag8 = false;
                                for(int l18 = juego.posRey[k2] + k17; l18 != cfins[l16][l]; l18 += k17)
                                {
                                    if(juego.casVacias[l18])
                                        continue;
                                    flag8 = true;
                                    break;
                                }

                            }
                        }
                        if(flag8)
                            cinis[l16][l] = 0;
                    }
                }

            }
            boolean flag6 = false;
            for(int j13 = 0; j13 <= 3; j13++)
            {
                for(int l14 = 0; l14 < i11; l14++)
                    if(calidad[juego.pos[cfins[l14][l]]] == j13)
                        if(calidad[piezamov[l14][l]] >= j13 || juego.minimoatacante(cfins[l14][l], k2) == 0)
                        {
                            if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[l14][l], cfins[l14][l], k))
                            {
                                l7++;
                                JuegoConNuevaJugada(juegos[l], juego, cinis[l14][l], cfins[l14][l], k);
                                int ai7[] = jugada(juegos[l], k2, l + 1, cinis[l14][l], cfins[l14][l], k1, l1, i2, j2);
                                if(ai7[0] > ai[0])
                                {
                                    ai = ai7;
                                    if(ai[0] >= l1)
                                        return ai;
                                }
                                if(i2 < ai7[0])
                                    i2 = ai7[0];
                            }
                            cinis[l14][l] = 0;
                        } else
                        {
                            flag6 = true;
                        }

            }

            if(capturaskiller && flag6 && comCortanteI[l] != 0)
            {
                for(int i15 = 0; i15 < i11; i15++)
                    if(cinis[i15][l] == comCortanteI[l] && cfins[i15][l] == comCortanteF[l])
                    {
                        if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[i15][l], cfins[i15][l], k))
                        {
                            l7++;
                            JuegoConNuevaJugada(juegos[l], juego, cinis[i15][l], cfins[i15][l], k);
                            int ai8[] = jugada(juegos[l], k2, l + 1, cinis[i15][l], cfins[i15][l], k1, l1, i2, j2);
                            if(ai8[0] > ai[0])
                            {
                                ai = ai8;
                                if(ai[0] >= l1)
                                    return ai;
                            }
                            if(i2 < ai8[0])
                                i2 = ai8[0];
                            else
                                comCortanteI[l] = 0;
                        }
                        cinis[i15][l] = 0;
                    }

            }
            int j15;
            if(flag1 && i4 <= i2 - 200 - 1000 * (l4 - 1))
            {
                j15 = pseudoJaquesPos(juego, k, l);
                l7++;
            } else
            {
                j15 = pseudoNoCapturasPos(juego, k, l);
            }
            for(int i17 = 0; i17 < j15; i17++)
                if(piezamov[i17][l] != 5 && juego.casAtPorP[k2][pinis[i17][l]] > 0 && juego.casAtPorP[k2][pfins[i17][l]] == 0)
                {
                    if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[i17][l], pfins[i17][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, pinis[i17][l], pfins[i17][l], k);
                        int ai9[] = jugada(juegos[l], k2, l + 1, pinis[i17][l], pfins[i17][l], k1, l1, i2, j2);
                        if(ai9[0] > ai[0])
                        {
                            ai = ai9;
                            if(ai[0] >= l1)
                                return ai;
                        }
                        if(i2 < ai9[0])
                            i2 = ai9[0];
                    }
                    pinis[i17][l] = 0;
                }

            if(flag && l >= k1)
            {
                cualturno = k;
                i4 = ((juego.material[bandoC] - juego.material[bandoCont]) + todoLoBuenoDe(juego, bandoC)) - todoLoBuenoDe(juego, bandoCont);
                if(ai[0] >= i4)
                    return ai;
                if(i4 < l1)
                    l1 = i4;
            }
            for(int l17 = 0; l17 < j15; l17++)
            {
                if(pinis[l17][l] == 0 || primCortante[l][1] != pfins[l17][l] || primCortante[l][0] != pinis[l17][l])
                    continue;
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[l17][l], pfins[l17][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[l17][l], pfins[l17][l], k);
                    int ai10[] = jugada(juegos[l], k2, l + 1, pinis[l17][l], pfins[l17][l], k1, l1, i2, j2);
                    if(ai10[0] > ai[0])
                    {
                        ai = ai10;
                        if(ai[0] >= l1)
                            return ai;
                    }
                    if(i2 < ai10[0])
                        i2 = ai10[0];
                }
                pinis[l17][l] = 0;
                break;
            }

            for(int i19 = 0; i19 < j15; i19++)
            {
                if(pinis[i19][l] == 0 || segCortante[l][1] != pfins[i19][l] || segCortante[l][0] != pinis[i19][l])
                    continue;
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[i19][l], pfins[i19][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[i19][l], pfins[i19][l], k);
                    int ai11[] = jugada(juegos[l], k2, l + 1, pinis[i19][l], pfins[i19][l], k1, l1, i2, j2);
                    if(ai11[0] > ai[0])
                    {
                        ai = ai11;
                        if(ai[0] >= l1)
                        {
                            segCortante[l][0] = primCortante[l][0];
                            segCortante[l][1] = primCortante[l][1];
                            primCortante[l][0] = pinis[i19][l];
                            primCortante[l][1] = pfins[i19][l];
                            return ai;
                        }
                    }
                    if(i2 < ai11[0])
                        i2 = ai11[0];
                }
                pinis[i19][l] = 0;
                break;
            }

            for(int l19 = 0; l19 < j15; l19++)
            {
                if(pinis[l19][l] == 0 || terCortante[l][1] != pfins[l19][l] || terCortante[l][0] != pinis[l19][l])
                    continue;
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[l19][l], pfins[l19][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[l19][l], pfins[l19][l], k);
                    int ai12[] = jugada(juegos[l], k2, l + 1, pinis[l19][l], pfins[l19][l], k1, l1, i2, j2);
                    if(ai12[0] > ai[0])
                    {
                        ai = ai12;
                        if(ai[0] >= l1)
                        {
                            terCortante[l][0] = segCortante[l][0];
                            terCortante[l][1] = segCortante[l][1];
                            segCortante[l][0] = primCortante[l][0];
                            segCortante[l][1] = primCortante[l][1];
                            primCortante[l][0] = pinis[l19][l];
                            primCortante[l][1] = pfins[l19][l];
                            return ai;
                        }
                    }
                    if(i2 < ai12[0])
                        i2 = ai12[0];
                }
                pinis[l19][l] = 0;
                break;
            }

            for(int k20 = 0; k20 < j15; k20++)
                if(pinis[k20][l] != 0 && enPrimeraLinea[k][pinis[k20][l]] && piezamov[k20][l] != 6 && (piezamov[k20][l] != 1 || !bordeTablero[pfins[k20][l]]) && juego.minimoatacante(pfins[k20][l], k2) == 0)
                {
                    if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[k20][l], pfins[k20][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, pinis[k20][l], pfins[k20][l], k);
                        int ai13[] = jugada(juegos[l], k2, l + 1, pinis[k20][l], pfins[k20][l], k1, l1, i2, j2);
                        if(ai13[0] > ai[0])
                        {
                            ai = ai13;
                            if(ai[0] >= l1)
                            {
                                corteALACOLA(pinis[k20][l], pfins[k20][l], l);
                                return ai;
                            }
                        }
                        if(i2 < ai13[0])
                            i2 = ai13[0];
                    }
                    pinis[k20][l] = 0;
                }

            for(int i21 = 0; i21 < j15; i21++)
                if(pinis[i21][l] != 0 && noRetrocede[k][pinis[i21][l]][pfins[i21][l]] && piezamov[i21][l] != 6 && (piezamov[i21][l] != 1 || !bordeTablero[pfins[i21][l]]) && juego.minimoatacante(pfins[i21][l], k2) == 0)
                {
                    if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[i21][l], pfins[i21][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, pinis[i21][l], pfins[i21][l], k);
                        int ai14[] = jugada(juegos[l], k2, l + 1, pinis[i21][l], pfins[i21][l], k1, l1, i2, j2);
                        if(ai14[0] > ai[0])
                        {
                            ai = ai14;
                            if(ai[0] >= l1)
                            {
                                corteALACOLA(pinis[i21][l], pfins[i21][l], l);
                                return ai;
                            }
                        }
                        if(i2 < ai14[0])
                            i2 = ai14[0];
                    }
                    pinis[i21][l] = 0;
                }

            for(int k21 = 0; k21 < j15; k21++)
                if(pinis[k21][l] != 0 && (juego.casAtPorP[k2][pfins[k21][l]] == 0 || piezamov[k21][l] == 5))
                {
                    if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[k21][l], pfins[k21][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, pinis[k21][l], pfins[k21][l], k);
                        int ai15[] = jugada(juegos[l], k2, l + 1, pinis[k21][l], pfins[k21][l], k1, l1, i2, j2);
                        if(ai15[0] > ai[0])
                        {
                            ai = ai15;
                            if(ai[0] >= l1)
                            {
                                corteALACOLA(pinis[k21][l], pfins[k21][l], l);
                                return ai;
                            }
                        }
                        if(i2 < ai15[0])
                            i2 = ai15[0];
                    }
                    pinis[k21][l] = 0;
                }

            if(flag6)
            {
                for(int i22 = 0; i22 < i11; i22++)
                    if(cinis[i22][l] != 0 && juego.noDejaJaqueHacia(juego.posRey[k], cinis[i22][l], cfins[i22][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, cinis[i22][l], cfins[i22][l], k);
                        int ai16[] = jugada(juegos[l], k2, l + 1, cinis[i22][l], cfins[i22][l], k1, l1, i2, j2);
                        if(ai16[0] > ai[0])
                        {
                            ai = ai16;
                            if(ai[0] >= l1)
                            {
                                comCortanteI[l] = cinis[i22][l];
                                comCortanteF[l] = cfins[i22][l];
                                return ai;
                            }
                        }
                        if(i2 < ai16[0])
                            i2 = ai16[0];
                    }

            }
            for(int j22 = 0; j22 < j15; j22++)
                if(pinis[j22][l] != 0 && juego.noDejaJaqueHacia(juego.posRey[k], pinis[j22][l], pfins[j22][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[j22][l], pfins[j22][l], k);
                    int ai17[] = jugada(juegos[l], k2, l + 1, pinis[j22][l], pfins[j22][l], k1, i2 + 1, i2, j2);
                    if(ai17[0] > ai[0] && i2 + 1 != l1)
                        ai17 = jugada(juegos[l], k2, l + 1, pinis[j22][l], pfins[j22][l], k1, l1, i2, j2);
                    if(ai17[0] > ai[0])
                    {
                        ai = ai17;
                        if(ai[0] >= l1)
                        {
                            corteALACOLA(pinis[j22][l], pfins[j22][l], l);
                            return ai;
                        }
                    }
                    if(i2 < ai17[0])
                        i2 = ai17[0];
                }

            if(l7 == 0)
            {
                ai[0] = 0;
                return ai;
            }
            if(ai[0] > k9 && juego.casVacias[l + l + 1])
                agregarCorte(ai[l + l], ai[l + l + 1], l);
            return ai;
        }
        ai[0] = 30000;
        boolean flag7 = false;
        if(flag1 && i4 > l1 + 230 + 1000 * (l4 - 1) + vDePiezas[5])
        {
            l7++;
            int k13 = i4 - l1;
            boolean flag9 = true;
            for(int i18 = 0; i18 < i11; i18++)
            {
                int k15;
                if(piezamov[i18][l] != 5)
                    k15 = juego.pos[cfins[i18][l]];
                else
                if(septima[k][cinis[i18][l]])
                    k15 = cfins[i18][l] % 10;
                else
                if(juego.pos[cfins[i18][l]] == 0)
                    k15 = 5;
                else
                    k15 = juego.pos[cfins[i18][l]];
                if(vDePiezas[k15] + 230 + 1000 * (l4 - 1) < k13)
                {
                    if(piezamov[i18][l] == 5)
                    {
                        if(juego.posRey[k2] == cfins[i18][l] + inc[k] + 1 || juego.posRey[k2] == (cfins[i18][l] + inc[k]) - 1)
                            flag9 = false;
                    } else
                    if(piezamov[i18][l] == 1)
                    {
                        if(atCab[cfins[i18][l]][juego.posRey[k2]])
                            flag9 = false;
                    } else
                    if(piezamov[i18][l] < 5)
                    {
                        int j19 = cualInc[juego.posRey[k2]][cfins[i18][l]];
                        if(j19 != 0 && atacapor[piezamov[i18][l]][Math.abs(j19)])
                        {
                            flag9 = false;
                            for(int i20 = juego.posRey[k2] + j19; i20 != cfins[i18][l]; i20 += j19)
                            {
                                if(juego.casVacias[i20])
                                    continue;
                                flag9 = true;
                                break;
                            }

                        }
                    }
                    if(flag9)
                        cinis[i18][l] = 0;
                }
            }

        }
        for(int l13 = 0; l13 <= 3; l13++)
        {
            for(int l15 = 0; l15 < i11; l15++)
                if(calidad[juego.pos[cfins[l15][l]]] == l13)
                    if(calidad[piezamov[l15][l]] >= l13 || juego.minimoatacante(cfins[l15][l], k2) == 0)
                    {
                        if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[l15][l], cfins[l15][l], k))
                        {
                            l7++;
                            JuegoConNuevaJugada(juegos[l], juego, cinis[l15][l], cfins[l15][l], k);
                            int ai18[] = jugada(juegos[l], k2, l + 1, cinis[l15][l], cfins[l15][l], k1, l1, i2, j2);
                            if(ai18[0] < ai[0])
                            {
                                ai = ai18;
                                if(ai[0] <= i2)
                                    return ai;
                            }
                            if(l1 > ai18[0])
                                l1 = ai18[0];
                        }
                        cinis[l15][l] = 0;
                    } else
                    {
                        flag7 = true;
                    }

        }

        if(capturaskiller && flag7 && comCortanteI[l] != 0)
        {
            for(int i16 = 0; i16 < i11; i16++)
                if(cinis[i16][l] == comCortanteI[l] && cfins[i16][l] == comCortanteF[l])
                {
                    if(juego.noDejaJaqueHacia(juego.posRey[k], cinis[i16][l], cfins[i16][l], k))
                    {
                        l7++;
                        JuegoConNuevaJugada(juegos[l], juego, cinis[i16][l], cfins[i16][l], k);
                        int ai19[] = jugada(juegos[l], k2, l + 1, cinis[i16][l], cfins[i16][l], k1, l1, i2, j2);
                        if(ai19[0] < ai[0])
                        {
                            ai = ai19;
                            if(ai[0] <= i2)
                                return ai;
                        }
                        if(l1 > ai19[0])
                            l1 = ai19[0];
                        else
                            comCortanteI[l] = 0;
                    }
                    cinis[i16][l] = 0;
                }

        }
        int j16;
        if(flag1 && i4 >= l1 + 200 + 1000 * (l4 - 1))
        {
            j16 = pseudoJaquesPos(juego, k, l);
            l7++;
        } else
        {
            j16 = pseudoNoCapturasPos(juego, k, l);
        }
        for(int j17 = 0; j17 < j16; j17++)
            if(piezamov[j17][l] != 5 && juego.casAtPorP[k2][pinis[j17][l]] > 0 && juego.casAtPorP[k2][pfins[j17][l]] == 0)
            {
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[j17][l], pfins[j17][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[j17][l], pfins[j17][l], k);
                    int ai20[] = jugada(juegos[l], k2, l + 1, pinis[j17][l], pfins[j17][l], k1, l1, i2, j2);
                    if(ai20[0] < ai[0])
                    {
                        ai = ai20;
                        if(ai[0] <= i2)
                            return ai;
                    }
                    if(l1 > ai20[0])
                        l1 = ai20[0];
                }
                pinis[j17][l] = 0;
            }

        if(flag && l >= k1)
        {
            cualturno = k;
            int j4 = ((juego.material[bandoC] - juego.material[bandoCont]) + todoLoBuenoDe(juego, bandoC)) - todoLoBuenoDe(juego, bandoCont);
            if(ai[0] <= j4)
                return ai;
            if(j4 > i2)
                i2 = j4;
        }
        for(int j18 = 0; j18 < j16; j18++)
        {
            if(pinis[j18][l] == 0 || primCortante[l][1] != pfins[j18][l] || primCortante[l][0] != pinis[j18][l])
                continue;
            if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[j18][l], pfins[j18][l], k))
            {
                l7++;
                JuegoConNuevaJugada(juegos[l], juego, pinis[j18][l], pfins[j18][l], k);
                int ai21[] = jugada(juegos[l], k2, l + 1, pinis[j18][l], pfins[j18][l], k1, l1, i2, j2);
                if(ai21[0] < ai[0])
                {
                    ai = ai21;
                    if(ai[0] <= i2)
                        return ai;
                }
                if(l1 > ai21[0])
                    l1 = ai21[0];
            }
            pinis[j18][l] = 0;
            break;
        }

        for(int k19 = 0; k19 < j16; k19++)
        {
            if(pinis[k19][l] == 0 || segCortante[l][1] != pfins[k19][l] || segCortante[l][0] != pinis[k19][l])
                continue;
            if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[k19][l], pfins[k19][l], k))
            {
                l7++;
                JuegoConNuevaJugada(juegos[l], juego, pinis[k19][l], pfins[k19][l], k);
                int ai22[] = jugada(juegos[l], k2, l + 1, pinis[k19][l], pfins[k19][l], k1, l1, i2, j2);
                if(ai22[0] < ai[0])
                {
                    ai = ai22;
                    if(ai[0] <= i2)
                    {
                        segCortante[l][0] = primCortante[l][0];
                        segCortante[l][1] = primCortante[l][1];
                        primCortante[l][0] = pinis[k19][l];
                        primCortante[l][1] = pfins[k19][l];
                        return ai;
                    }
                }
                if(l1 > ai22[0])
                    l1 = ai22[0];
            }
            pinis[k19][l] = 0;
            break;
        }

        for(int j20 = 0; j20 < j16; j20++)
        {
            if(pinis[j20][l] == 0 || terCortante[l][0] != pinis[j20][l] || terCortante[l][1] != pfins[j20][l])
                continue;
            if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[j20][l], pfins[j20][l], k))
            {
                l7++;
                JuegoConNuevaJugada(juegos[l], juego, pinis[j20][l], pfins[j20][l], k);
                int ai23[] = jugada(juegos[l], k2, l + 1, pinis[j20][l], pfins[j20][l], k1, l1, i2, j2);
                if(ai23[0] < ai[0])
                {
                    ai = ai23;
                    if(ai[0] <= i2)
                    {
                        terCortante[l][0] = segCortante[l][0];
                        terCortante[l][1] = segCortante[l][1];
                        segCortante[l][0] = primCortante[l][0];
                        segCortante[l][1] = primCortante[l][1];
                        primCortante[l][0] = pinis[j20][l];
                        primCortante[l][1] = pfins[j20][l];
                        return ai;
                    }
                }
                if(l1 > ai23[0])
                    l1 = ai23[0];
            }
            pinis[j20][l] = 0;
            break;
        }

        for(int l20 = 0; l20 < j16; l20++)
            if(pinis[l20][l] != 0 && enPrimeraLinea[k][pinis[l20][l]] && piezamov[l20][l] != 6 && (piezamov[l20][l] != 1 || !bordeTablero[pfins[l20][l]]) && juego.minimoatacante(pfins[l20][l], k2) == 0)
            {
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[l20][l], pfins[l20][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[l20][l], pfins[l20][l], k);
                    int ai24[] = jugada(juegos[l], k2, l + 1, pinis[l20][l], pfins[l20][l], k1, l1, i2, j2);
                    if(ai24[0] < ai[0])
                    {
                        ai = ai24;
                        if(ai[0] <= i2)
                        {
                            corteALACOLA(pinis[l20][l], pfins[l20][l], l);
                            return ai;
                        }
                    }
                    if(l1 > ai24[0])
                        l1 = ai24[0];
                }
                pinis[l20][l] = 0;
            }

        for(int j21 = 0; j21 < j16; j21++)
            if(pinis[j21][l] != 0 && noRetrocede[k][pinis[j21][l]][pfins[j21][l]] && piezamov[j21][l] != 6 && pinis[j21][l] / 10 != k * 7 + 1 && (piezamov[j21][l] != 1 || !bordeTablero[pfins[j21][l]]) && juego.minimoatacante(pfins[j21][l], k2) == 0)
            {
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[j21][l], pfins[j21][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[j21][l], pfins[j21][l], k);
                    int ai25[] = jugada(juegos[l], k2, l + 1, pinis[j21][l], pfins[j21][l], k1, l1, i2, j2);
                    if(ai25[0] < ai[0])
                    {
                        ai = ai25;
                        if(ai[0] <= i2)
                        {
                            corteALACOLA(pinis[j21][l], pfins[j21][l], l);
                            return ai;
                        }
                    }
                    if(l1 > ai25[0])
                        l1 = ai25[0];
                }
                pinis[j21][l] = 0;
            }

        for(int l21 = 0; l21 < j16; l21++)
            if(pinis[l21][l] != 0 && (juego.casAtPorP[k2][pfins[l21][l]] == 0 || piezamov[l21][l] == 5))
            {
                if(juego.noDejaJaqueHacia(juego.posRey[k], pinis[l21][l], pfins[l21][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, pinis[l21][l], pfins[l21][l], k);
                    int ai26[] = jugada(juegos[l], k2, l + 1, pinis[l21][l], pfins[l21][l], k1, l1, i2, j2);
                    if(ai26[0] < ai[0])
                    {
                        ai = ai26;
                        if(ai[0] <= i2)
                        {
                            corteALACOLA(pinis[l21][l], pfins[l21][l], l);
                            return ai;
                        }
                    }
                    if(l1 > ai26[0])
                        l1 = ai26[0];
                }
                pinis[l21][l] = 0;
            }

        if(flag7)
        {
            for(int k22 = 0; k22 < i11; k22++)
                if(cinis[k22][l] != 0 && juego.noDejaJaqueHacia(juego.posRey[k], cinis[k22][l], cfins[k22][l], k))
                {
                    l7++;
                    JuegoConNuevaJugada(juegos[l], juego, cinis[k22][l], cfins[k22][l], k);
                    int ai27[] = jugada(juegos[l], k2, l + 1, cinis[k22][l], cfins[k22][l], k1, l1, i2, j2);
                    if(ai27[0] < ai[0])
                    {
                        ai = ai27;
                        if(ai[0] <= i2)
                        {
                            comCortanteI[l] = cinis[k22][l];
                            comCortanteF[l] = cfins[k22][l];
                            return ai;
                        }
                    }
                    if(l1 > ai27[0])
                        l1 = ai27[0];
                }

        }
        for(int l22 = 0; l22 < j16; l22++)
            if(pinis[l22][l] != 0 && juego.noDejaJaqueHacia(juego.posRey[k], pinis[l22][l], pfins[l22][l], k))
            {
                l7++;
                JuegoConNuevaJugada(juegos[l], juego, pinis[l22][l], pfins[l22][l], k);
                int ai28[] = jugada(juegos[l], k2, l + 1, pinis[l22][l], pfins[l22][l], k1, l1, l1 - 1, j2);
                if(ai28[0] < ai[0] && l1 - 1 != i2)
                    ai28 = jugada(juegos[l], k2, l + 1, pinis[l22][l], pfins[l22][l], k1, l1, i2, j2);
                if(ai28[0] < ai[0])
                {
                    ai = ai28;
                    if(ai[0] <= i2)
                    {
                        corteALACOLA(pinis[l22][l], pfins[l22][l], l);
                        return ai;
                    }
                }
                if(l1 > ai28[0])
                    l1 = ai28[0];
            }

        if(l7 == 0)
        {
            ai[0] = 0;
            return ai;
        }
        if(ai[0] < i10 && juego.casVacias[ai[l + l + 1]])
            agregarCorte(ai[l + l], ai[l + l + 1], l);
        return ai;
    }

    public final int todoLoBuenoDe(Juego juego, int k)
    {
        if(!conEvaluaciones)
            return 0;
        pp = 0;
        patc = 0;
        pc = 0;
        pa = 0;
        pt = 0;
        pd = 0;
        pr = 0;
        primlin = 0;
        peligroRey = 0;
        septimafila = 0;
        choques = 0;
        bonus = 0;
        rinconesFeos = 0;
        boolean flag = false;
        int l = 1 - k;
        int i1 = juego.posRey[l];
        int j1 = juego.posRey[k];
        int k1 = i1 % 10;
        int l1 = inc[k];
        int i2 = juego.potencia[k] + juego.c[k][0];
        int j2 = juego.potencia[l] + juego.c[l][0];
        if(i2 > 30)
            i2 = 30;
        if(j2 > 30)
            j2 = 30;
        if(juego.d[k][0] > 0)
            flag = true;
        patc += (juego.casAtPorP[k][44 + 10 * k] + juego.casAtPorP[k][45 + 10 * k] + juego.casAtPorP[k][64 - 30 * k] + juego.casAtPorP[k][65 - 30 * k]) * 8 + (juego.casAtPorP[k][54 - 10 * k] + juego.casAtPorP[k][55 - 10 * k]) * 16;
        if(juego.peonesEnLinea[l][4] == 0 && juego.peonesEnLinea[l][5] == 0 && juego.peonesEnLinea[k][4] > 0 && juego.peonesEnLinea[k][5] > 0)
        {
            patc += 40;
        } else
        {
            if(juego.peonesEnLinea[k][5] != 0)
                patc += 14;
            if(juego.peonesEnLinea[k][4] != 0)
                patc += 5;
        }
        if(juego.potencia[l] >= 20 && juego.ped[k] && juego.casVacias[16 + 70 * k] && juego.casVacias[17 + 70 * k] && (juego.pos[28 + 50 * k] == 5 + 10 * k || juego.pos[38 + 30 * k] == 5 + 10 * k) && juego.pos[27 + 50 * k] == 5 + 10 * k)
            if(cualturno == k)
                pr += 30;
            else
                pr += 10;
        if(i2 >= 19 && k1 == 5 && juego.t[k][0] > 0 && juego.peonesEnLinea[l][5] == 0 && juego.peonesEnLinea[k][5] == 0)
        {
            boolean flag1 = false;
            boolean flag2 = false;
            for(int i3 = 35 + 30 * k; i3 != 75 - 50 * k; i3 += l1)
            {
                if(juego.pos[i3] == 3 + 10 * l || juego.pos[i3] == 1 + 10 * l)
                {
                    flag1 = true;
                    continue;
                }
                if(juego.pos[i3] == 4 + 10 * l)
                {
                    flag2 = true;
                    continue;
                }
                if(!juego.casVacias[i3])
                    break;
            }

            if(flag1)
            {
                if(flag2)
                    bonus += 75;
                else
                    bonus += 35;
            } else
            if(flag2)
                bonus += 35;
            if(cualturno == k && bonus != 0)
                if(!flag2 && juego.pos[25] == 4 + 10 * k || juego.pos[15 + 70 * k] == 2 + 10 * k && juego.casVacias[25 + 50 * k] && juego.casVacias[35 + 30 * k])
                    bonus *= 5;
                else
                if(juego.casVacias[15 + 70 * k] && juego.pos[16 + 70 * k] == 2 + 10 * k && juego.casVacias[25 + 50 * k])
                    bonus *= 4;
        }
        for(int k2 = 1; k2 <= juego.c[l][0]; k2++)
        {
            if(juego.casAtPorP[k][juego.c[l][k2]] > 0)
                pc += 17;
            if(bordeTablero[juego.c[l][k2]])
            {
                rinconesFeos += 63;
                if(rinconTablero[juego.c[l][k2]])
                    rinconesFeos += 16;
                if(enPrimeraLinea[l][juego.c[l][k2]])
                    primlin++;
            }
            if(l == 0 && juego.c[0][k2] / 10 <= 4)
            {
                if(juego.casAtPorP[k][juego.c[l][k2] + 21] > 0 && juego.casAtPorP[k][juego.c[l][k2] + 19] > 0)
                    pc += 5;
            } else
            if(l == 1 && juego.c[1][k2] / 10 >= 5 && juego.casAtPorP[k][juego.c[l][k2] - 21] > 0 && juego.casAtPorP[k][juego.c[l][k2] - 19] > 0)
                pc += 5;
        }

        for(int l2 = 1; l2 <= juego.c[k][0]; l2++)
        {
            if(i2 >= 13 && mismoFlanco[i1][juego.c[k][l2]])
                pc += 7;
            pc += puntosCab[juego.c[k][l2]];
            if(esC[juego.c[k][l2]])
                pc += juego.casAtPorP[k][juego.c[k][l2]] * 3;
            if(i2 >= 8 && (caballitobacan[k][juego.c[k][l2]] || enCuadOCerca[i1][juego.c[k][l2]]) && !enPrimeraLinea[l][juego.c[k][l2]])
            {
                boolean flag3 = true;
                for(int l3 = juego.c[k][l2]; dentroTablero[l3]; l3 += l1)
                {
                    if(juego.casAtPorP[l][l3] <= 0)
                        continue;
                    flag3 = false;
                    break;
                }

                if(flag3)
                    if(juego.casAtPorP[k][juego.c[k][l2]] > 0)
                        pc += i2;
                    else
                        pc += i2 / 2;
            }
            for(int j3 = 0; j3 <= 7; j3++)
            {
                int i4 = movsCaballines[juego.c[k][l2]][j3];
                if(dentroTablero[i4])
                    if(juego.hayPieza[l][i4])
                    {
                        int i6 = juego.pos[i4] % 10;
                        if(i6 == 4 || i6 == 2)
                            choques += 24;
                        else
                        if(i6 == 3)
                        {
                            if(juego.casAtPorP[l][i4] == 0)
                                choques += 24;
                            else
                                choques += 15;
                        } else
                        if(i6 == 5 && juego.casAtPorP[l][i4] == 0 && juego.casAtPorP[l][i4 - l1] == 0)
                            if(juego.casAtPorP[l][i4 + l1] == 0)
                                choques += 20;
                            else
                                choques += 10;
                    } else
                    if(juego.pos[i4] == 1 + 10 * k && esC[i4])
                        pc += 6;
            }

        }

        int k3 = 0;
        for(i = 1; i <= juego.t[k][0]; i++)
        {
            if(juego.potencia[k] >= 19 && mismoFlanco[juego.t[k][i]][i1])
                pt += 6;
            if(juego.casAtPorP[l][juego.t[k][i]] > 0)
                pc -= 19;
            if(septima[k][juego.t[k][i]])
                k3++;
            if(juego.peonesEnLinea[k][juego.t[k][i] % 10] == 0)
            {
                if(juego.peonesEnLinea[l][juego.t[k][i] % 10] == 0)
                {
                    pt += 34;
                    if(i2 >= 15)
                        if(Math.abs(juego.t[k][i] % 10 - k1) <= 1)
                            peligroRey += i2 + 5;
                        else
                        if(mismoFlanco[juego.t[k][i]][i1] && juego.t[k][i] % 10 != 4 && juego.t[k][i] % 10 != 5)
                            peligroRey += i2 - 6;
                        else
                        if(juego.d[l][0] > 0 && juego.d[l][1] % 10 == juego.t[k][i] % 10)
                            pt += 10;
                } else
                {
                    if(i2 >= 15 && mismoFlanco[juego.t[k][i]][i1])
                        peligroRey += i2 - 6;
                    if(juego.peonesEnLinea[k][juego.t[k][i] % 10 + 1] == 0 && juego.peonesEnLinea[k][juego.t[k][i] % 10 - 1] == 0)
                        pt += 25;
                    else
                        pt += 17;
                }
            } else
            if(juego.peonesEnLinea[l][juego.t[k][i] % 10] == 0 && juego.peonesEnLinea[k][juego.t[k][i] % 10] <= 1)
                pt += 6;
            for(j = juego.t[k][i] + 1; dentroTablero[j]; j++)
            {
                if(!juego.casVacias[j])
                {
                    if(juego.hayPieza[l][j] && juego.casAtPorP[l][j] == 0 && (juego.pos[j] % 10 != 5 || juego.casAtPorP[l][j - l1] == 0))
                    {
                        choques += 6;
                        peligroRey += atacabilidadTorreEO[i1][j];
                    }
                    break;
                }
                if(juego.casAtPorP[l][j] == 0)
                {
                    pt += movilidadTorreEO[j];
                    peligroRey += atacabilidadTorreEO[i1][j];
                } else
                {
                    pt += movilidadTorreEO[j] / 2;
                    peligroRey += atacabilidadTorreEO[i1][j] / 2;
                }
            }

            for(j = juego.t[k][i] - 1; dentroTablero[j]; j--)
            {
                if(!juego.casVacias[j])
                {
                    if(juego.hayPieza[l][j] && juego.casAtPorP[l][j] == 0 && (juego.pos[j] % 10 != 5 || juego.casAtPorP[l][j - l1] == 0))
                    {
                        choques += 6;
                        peligroRey += atacabilidadTorreEO[i1][j];
                    }
                    break;
                }
                if(juego.casAtPorP[l][j] == 0)
                {
                    pt += movilidadTorreEO[j];
                    peligroRey += atacabilidadTorreEO[i1][j];
                } else
                {
                    pt += movilidadTorreEO[j] / 2;
                    peligroRey += atacabilidadTorreEO[i1][j] / 2;
                }
            }

            for(j = juego.t[k][i] + 10; j < 89; j += 10)
            {
                if(!juego.casVacias[j])
                {
                    if(juego.hayPieza[l][j])
                    {
                        if(piezamenor[juego.pos[j]] || juego.pos[j] == 4 + 10 * l)
                        {
                            for(int j4 = j + 10; dentroTablero[j4]; j4 += 10)
                            {
                                if(juego.casVacias[j4])
                                    continue;
                                if(juego.hayPieza[l][j4] && vDePiezas[juego.pos[j4]] > vDePiezas[2])
                                    if(juego.casAtPorP[k][j] > 0 || juego.casAtPorP[l][j] == 0)
                                        bonus += 32;
                                    else
                                        bonus += 21;
                                break;
                            }

                        }
                        if(juego.casAtPorP[l][j] == 0 && (juego.pos[j] % 10 != 5 || juego.casAtPorP[l][j - l1] == 0) || juego.pos[j] == 4 + 10 * l)
                        {
                            choques += 18;
                            peligroRey += atacabilidadTorreNS[i1][j];
                        }
                    } else
                    if(juego.pos[j] == 2 + 10 * k && juego.peonesEnLinea[k][j % 10] == 0)
                    {
                        if(juego.peonesEnLinea[l][j % 10] == 0)
                            pt += 56;
                        else
                            pt += 32;
                    } else
                    if(juego.pos[j] == 5 + 10 * k && juego.casAtPorP[l][j] > 0)
                    {
                        pt += 15;
                        if(mismoFlanco[j][i1])
                            pt += 5;
                    }
                    break;
                }
                if(juego.casAtPorP[l][j] == 0)
                {
                    pt += movilidadTorreNS[j];
                    peligroRey += atacabilidadTorreNS[i1][j];
                } else
                {
                    pt += movilidadTorreNS[j] / 2;
                    peligroRey += atacabilidadTorreNS[i1][j] / 2;
                }
            }

            for(j = juego.t[k][i] - 10; j > 10; j -= 10)
            {
                if(!juego.casVacias[j])
                {
                    if(juego.hayPieza[l][j])
                    {
                        if(piezamenor[juego.pos[j]] || juego.pos[j] == 4 + 10 * l)
                        {
                            for(int k4 = j + 10; dentroTablero[k4]; k4 += 10)
                            {
                                if(juego.casVacias[k4])
                                    continue;
                                if(juego.hayPieza[l][k4] && vDePiezas[juego.pos[k4]] > vDePiezas[2])
                                    if(juego.casAtPorP[k][j] > 0 || juego.casAtPorP[l][j] == 0)
                                        bonus += 32;
                                    else
                                        bonus += 21;
                                break;
                            }

                        }
                        if(juego.casAtPorP[l][j] == 0 && (juego.pos[j] % 10 != 5 || juego.casAtPorP[l][j - l1] == 0) || juego.pos[j] == 4 + 10 * l)
                        {
                            choques += 18;
                            peligroRey += atacabilidadTorreNS[i1][j];
                        }
                    } else
                    if(juego.pos[j] == 5 + 10 * k && juego.casAtPorP[l][j] > 0)
                    {
                        pt += 15;
                        if(mismoFlanco[j][i1])
                            pt += 5;
                    }
                    break;
                }
                if(juego.casAtPorP[l][j] == 0)
                {
                    pt += movilidadTorreNS[j];
                    peligroRey += atacabilidadTorreNS[i1][j];
                } else
                {
                    pt += movilidadTorreNS[j] / 2;
                    peligroRey += atacabilidadTorreNS[i1][j] / 2;
                }
            }

        }

        if((k != bandoC || k3 != 0) && flag && septima[k][juego.d[k][1]])
            k3++;
        if(k3 != 0)
        {
            boolean flag4 = false;
            if(k3 > 2)
                k3 = 2;
            for(int j7 = 22 + 50 * l; j7 != 28 + 50 * l; j7++)
            {
                if(juego.pos[j7] != 5 + 10 * l)
                    continue;
                flag4 = true;
                break;
            }

            boolean flag5;
            if(i1 / 10 == l * 7 + 1)
                flag5 = true;
            else
                flag5 = false;
            if(flag5)
            {
                if(flag4)
                    septimafila += bonusSeptimaReyConP[k3];
                else
                    septimafila += bonusSeptimaRey[k3];
            } else
            if(flag4)
                septimafila += bonusSeptimaP[k3];
        }
        for(i = 1; i <= juego.a[l][0]; i++)
        {
            if(juego.casAtPorP[k][juego.a[l][i]] > 0)
                pa += 18;
            if(enPrimeraLinea[l][juego.a[l][i]])
            {
                primlin++;
                rinconesFeos += 2 * j2;
            }
            if(l == 0)
            {
                if(juego.a[l][i] == 71)
                {
                    if(juego.pos[62] == 15 && juego.pos[73] == 15)
                        pa += 450;
                    else
                    if(juego.pos[81] == 12 && juego.casAtPorP[l][71] == 0 && juego.d[l][0] > 0 && juego.d[l][1] % 10 == 8)
                    {
                        for(int l4 = 61; l4 >= 11; l4 -= 10)
                        {
                            if(juego.casVacias[l4])
                                continue;
                            if(juego.pos[l4] == 4 + 10 * l)
                                pa += 450;
                            break;
                        }

                    }
                } else
                if(juego.a[l][i] == 78)
                    if(juego.pos[67] == 15 && juego.pos[76] == 15)
                        pa += 450;
                    else
                    if(juego.pos[88] == 12 && juego.casAtPorP[l][78] == 0 && juego.d[l][0] > 0 && juego.d[l][1] % 10 == 8)
                    {
                        for(int i5 = 68; i5 >= 18; i5 -= 10)
                        {
                            if(juego.casVacias[i5])
                                continue;
                            if(juego.pos[i5] == 4 + 10 * l)
                                pa += 450;
                            break;
                        }

                    }
            } else
            if(juego.a[l][i] == 21)
            {
                if(juego.pos[32] == 5 && juego.pos[23] == 5)
                    pa += 450;
                else
                if(juego.pos[11] == 2 && juego.casAtPorP[l][21] == 0 && juego.d[l][0] > 0 && juego.d[l][1] % 10 == 1)
                {
                    for(int j5 = 31; j5 <= 81; j5 += 10)
                    {
                        if(juego.casVacias[j5])
                            continue;
                        if(juego.pos[j5] == 4 + 10 * l)
                            pa += 450;
                        break;
                    }

                }
            } else
            if(juego.a[l][i] == 28)
                if(juego.pos[37] == 5 && juego.pos[26] == 5)
                    pa += 450;
                else
                if(juego.pos[18] == 2 && juego.casAtPorP[l][28] == 0 && juego.d[l][0] > 0 && juego.d[l][1] % 10 == 1)
                {
                    for(int k5 = 38; k5 <= 88; k5 += 10)
                    {
                        if(juego.casVacias[k5])
                            continue;
                        if(juego.pos[k5] == 4 + 10 * l)
                            pa += 450;
                        break;
                    }

                }
        }

        for(int l5 = 1; l5 <= juego.a[k][0]; l5++)
        {
            pa += movilidadAlfil[juego.a[k][l5]];
            for(int j6 = 0; j6 <= 3; j6++)
            {
                for(int k7 = juego.a[k][l5] + incAlfil[j6]; dentroTablero[k7]; k7 += incAlfil[j6])
                {
                    if(!juego.casVacias[k7])
                    {
                        if(juego.hayPieza[l][k7])
                        {
                            if(alfilClavaEsto[juego.pos[k7]])
                            {
                                for(int l8 = k7 + incAlfil[j6]; dentroTablero[l8]; l8 += incAlfil[j6])
                                {
                                    if(juego.casVacias[l8])
                                        continue;
                                    if(juego.hayPieza[l][l8] && vDePiezas[juego.pos[l8]] > vDePiezas[3])
                                        if(juego.casAtPorP[k][k7] > 0 || juego.casAtPorP[l][k7] == 0)
                                            bonus += 36;
                                        else
                                            bonus += 25;
                                    break;
                                }

                            }
                            if(juego.pos[k7] % 10 != 5 || juego.casAtPorP[l][k7] == 0)
                                peligroRey += atacabilidadAlfil[i1][k7];
                            if(juego.pos[k7] % 10 != 5 || juego.casAtPorP[l][k7] == 0 && juego.casAtPorP[l][k7 - l1] == 0)
                                if(juego.casAtPorP[l][k7] == 0)
                                    choques += 28;
                                else
                                    choques += 13;
                        } else
                        if(cualturno == k && alfilClavaEsto[juego.pos[k7]])
                        {
                            for(int i9 = k7 + incAlfil[j6]; dentroTablero[i9]; i9 += incAlfil[j6])
                            {
                                if(juego.casVacias[i9])
                                    continue;
                                if(juego.hayPieza[l][i9] && vDePiezas[juego.pos[i9]] > vDePiezas[3])
                                    bonus += 25;
                                break;
                            }

                        }
                        break;
                    }
                    if(juego.casAtPorP[l][k7] == 0)
                    {
                        pa += movilidadAlfil[k7];
                        peligroRey += atacabilidadAlfil[i1][k7];
                    } else
                    {
                        pa += movilidadAlfil[k7] / 2;
                        peligroRey += atacabilidadAlfil[i1][k7] / 2;
                    }
                }

            }

        }

        for(i = 1; i <= juego.d[k][0]; i++)
        {
            pd += centralizacionDama[juego.d[k][i]];
            if(numeroDeJugada < 7 && bordeTablero[i1])
                break;
            if(mismoFlanco[i1][juego.d[k][i]])
                pd += 7;
            for(int k6 = 0; k6 <= 7; k6++)
            {
                int l7 = incDama[k6];
                for(int j9 = juego.d[k][i] + l7; dentroTablero[j9]; j9 += l7)
                {
                    if(!juego.casVacias[j9])
                        break;
                    if(juego.casAtPorP[l][j9] == 0)
                        peligroRey += atacabilidadDama[i1][j9];
                    else
                        peligroRey += atacabilidadDama[i1][j9] / 2;
                }

            }

        }

        if(primlin >= 3 && juego.d[l][0] > 0 && !enPrimeraLinea[l][juego.d[l][1]])
            pd += 25;
        if(juego.a[k][0] >= 2)
            pa += 75 - i2;
        if(j2 >= 15 && primlin == 0 && juego.d[l][0] > 0 && enPrimeraLinea[l][juego.d[l][1]])
            pd += 9;
        if(juego.p[k][0] > 1 && i2 >= 10)
        {
            int l6 = 0;
            int i8 = 0;
            for(int k9 = 41; k9 <= 49; k9++)
                if(juego.pos[k9] == 5 + 10 * k)
                {
                    if(juego.pos[k9 + l1] != 5 + 10 * l)
                    {
                        if(mismoFlanco[k9][i1])
                            l6 += 7;
                        else
                            l6 += 3;
                    } else
                    {
                        l6 -= 2;
                    }
                } else
                if(juego.pos[k9 + 10] == 5 + 10 * k)
                {
                    if(juego.pos[k9 + 10 + l1] != 5 + 10 * l)
                    {
                        if(mismoFlanco[k9][i1])
                            l6 += 7;
                        else
                            l6 += 3;
                    } else
                    {
                        l6 -= 2;
                    }
                } else
                if(l6 >= 8)
                {
                    i8 += l6;
                    l6 = 0;
                } else
                {
                    l6 = 0;
                }

            bonus += (i8 * (i2 * 6)) / 30;
        }
        for(int i7 = 1; i7 <= 8; i7++)
        {
            if(juego.peonesEnLinea[l][i7] >= 2)
                pp -= peonDoblado[i7] * (juego.peonesEnLinea[l][i7] - 1);
            if(juego.peonesEnLinea[l][i7 - 1] == 0 && juego.peonesEnLinea[l][i7] != 0 && juego.peonesEnLinea[l][i7 + 1] == 0)
                pp -= peonAislado[i7] * juego.peonesEnLinea[l][i7];
        }

        if(i2 >= 5)
        {
            for(int j8 = 1; j8 <= juego.p[l][0]; j8++)
            {
                for(int l9 = juego.p[l][j8] - l1; dentroTablero[l9]; l9 += l1)
                {
                    if(juego.casAtPorP[l][l9] != 0)
                        break;
                    if(septima[k][l9])
                        if(juego.t[k][0] != 0 && juego.peonesEnLinea[k][l9 % 10] == 0)
                            pp += 20;
                        else
                            pp += 13;
                }

            }

        }
        for(int k8 = 1; k8 <= juego.p[k][0]; k8++)
        {
            pp += puntosPAvanzados[k][juego.p[k][k8]];
            boolean flag6 = true;
            boolean flag8 = false;
            boolean flag9 = false;
            boolean flag10 = juego.casVacias[juego.p[k][k8] + l1];
            if(!flag10 && k != bandoC && juego.pos[juego.p[k][k8] + l1] == 5 + 10 * l)
                pp += 4;
            for(int j11 = juego.p[k][k8]; !enPrimeraLinea[l][j11]; j11 += l1)
            {
                if(juego.pos[j11] != 5 + 10 * l && juego.casAtPorP[l][j11] <= 0)
                    continue;
                flag6 = false;
                break;
            }

            if(juego.peonesEnLinea[k][juego.p[k][k8] % 10 + 1] > 0 || juego.peonesEnLinea[k][juego.p[k][k8] % 10 - 1] > 0)
            {
                flag8 = true;
                if(juego.casAtPorP[k][juego.p[k][k8] + l1] > 0 || flag10 && !septima[k][juego.p[k][k8]] && juego.casAtPorP[k][juego.p[k][k8] + 2 * l1] > 0)
                    flag9 = true;
            }
            if(flag6)
            {
                for(int k11 = juego.p[k][k8] - l1; dentroTablero[k11]; k11 -= l1)
                {
                    if(juego.casVacias[k11])
                        continue;
                    if(juego.pos[k11] == 2 + 10 * k)
                        pp += 25;
                    break;
                }

                if(flag10)
                {
                    if(flag9)
                        pp += puntosPPasadosSuperLigados[k][juego.p[k][k8]];
                    else
                    if(flag8)
                        pp += puntosPPasadosLigados[k][juego.p[k][k8]];
                    else
                        pp += puntosPPasadosNoLigados[k][juego.p[k][k8]];
                } else
                if(flag9)
                    pp += puntosPPasadosSuperLigados[k][juego.p[k][k8]] / 2;
                else
                if(flag8)
                    pp += puntosPPasadosLigados[k][juego.p[k][k8]] / 2;
                else
                    pp += puntosPPasadosNoLigados[k][juego.p[k][k8]] / 2;
            } else
            if(flag8)
            {
                pp += bonusPALigados[k][juego.p[k][k8]];
                if(i2 >= 13 && mismoCuadrante[i1][juego.p[k][k8]])
                    if(flag9 && flag10)
                        peligroRey += 25;
                    else
                        peligroRey += 5;
                if(flag10)
                    pp += 3;
            }
        }

        if(i2 >= 12)
            if(k == 0)
            {
                if(juego.pos[45] == 5 && (juego.peonesEnLinea[k][6] != 0 || juego.peonesEnLinea[k][4] != 0) && juego.pos[55] != 15)
                    if(juego.casVacias[55])
                        patc += 35;
                    else
                        patc += 25;
                if(juego.pos[44] == 5 && (juego.peonesEnLinea[k][5] != 0 || juego.peonesEnLinea[k][3] != 0) && juego.pos[54] != 15)
                    if(juego.casVacias[54])
                        patc += 35;
                    else
                        patc += 25;
            } else
            {
                if(juego.pos[55] == 15 && (juego.peonesEnLinea[k][6] != 0 || juego.peonesEnLinea[k][4] != 0) && juego.pos[45] != 5)
                    if(juego.casVacias[45])
                        patc += 35;
                    else
                        patc += 25;
                if(juego.pos[54] == 15 && (juego.peonesEnLinea[k][5] != 0 || juego.peonesEnLinea[k][3] != 0) && juego.pos[44] != 5)
                    if(juego.casVacias[55])
                        patc += 35;
                    else
                        patc += 25;
            }
        if(l == 0)
        {
            if(juego.pos[24] == 5)
            {
                patc += 40;
                if(juego.pos[34] != 0)
                {
                    patc += 36;
                    if(juego.pos[13] == 3)
                        patc += 48;
                }
            }
            if(juego.pos[25] == 5)
            {
                patc += 45;
                if(juego.pos[35] != 0)
                {
                    patc += 36;
                    if(juego.pos[16] == 3)
                        patc += 48;
                }
            }
            if(juego.pos[23] == 5 && juego.pos[44] == 5 && (juego.pos[25] == 5 || juego.pos[35] == 5))
            {
                patc += 20;
                if(!juego.casVacias[33])
                    patc += 55;
            }
            if(juego.pos[26] == 5 && juego.pos[45] == 5 && juego.pos[34] == 5)
            {
                patc += 15;
                if(!juego.casVacias[36])
                    patc += 40;
            }
        } else
        {
            if(juego.pos[74] == 15)
            {
                patc += 40;
                if(juego.pos[64] != 0)
                {
                    patc += 36;
                    if(juego.pos[83] == 13)
                        patc += 48;
                }
            }
            if(juego.pos[75] == 15)
            {
                patc += 45;
                if(juego.pos[65] != 0)
                {
                    patc += 36;
                    if(juego.pos[86] == 13)
                        patc += 48;
                }
            }
            if(juego.pos[73] == 15 && juego.pos[54] == 15 && (juego.pos[75] == 15 || juego.pos[65] == 15))
            {
                patc += 20;
                if(!juego.casVacias[63])
                    patc += 55;
            }
            if(juego.pos[76] == 15 && juego.pos[55] == 15 && juego.pos[64] == 15)
            {
                patc += 15;
                if(!juego.casVacias[66])
                    patc += 40;
            }
        }
        if(juego.ped[l])
        {
            if(l == 0 && juego.pos[26] != 5 || l == 1 && juego.pos[76] != 15)
            {
                pr += i2;
                peligroRey += i2 - 5;
            }
            if(!juego.casVacias[i1 + 1] && !juego.casVacias[i1 - l1])
                pr += 10;
        }
        if(!juego.pei[l] && !juego.ped[l] && i2 >= 13)
        {
            if(juego.t[k][0] > 0 || flag && i2 >= 16)
            {
                if(juego.peonesEnLinea[l][k1] == 0 || juego.peonesEnLinea[l][k1 - 1] == 0 && juego.peonesEnLinea[l][k1 + 1] == 0)
                {
                    peligroRey += 2 * i2 - 4;
                    if(juego.peonesEnLinea[k][k1] == 0)
                        peligroRey += i2;
                }
            } else
            if(juego.peonesEnLinea[k][k1] == 0 && juego.t[k][0] > 0)
                peligroRey += i2 - 6;
            if(k1 > 5)
            {
                if(juego.peonesEnLinea[l][8] == 0 || juego.peonesEnLinea[l][7] == 0)
                {
                    peligroRey += i2 + 12;
                    if(flag)
                        peligroRey += 12 * atacabilidadDama[i1][juego.d[k][1]];
                    if(juego.peonesEnLinea[k][8] == 0 && (juego.t[k][0] >= 1 && juego.t[k][1] % 10 == 8 || juego.t[k][0] >= 2 && juego.t[k][2] % 10 == 8))
                        peligroRey += 3 * i2;
                }
                if(k != bandoC && juego.pos[56 - 10 * k] == 5 + 10 * k && juego.pos[45 + 10 * k] == 5 + 10 * k)
                {
                    peligroRey += 40 + k1;
                    if(!juego.casVacias[26 + 50 * l] || juego.casAtPorP[k][26 + 50 * l] > 0)
                        if(juego.pos[26 + 50 * l] == 5 + 10 * l && juego.casVacias[36 + 30 * l])
                            peligroRey += 30;
                        else
                            peligroRey += 50;
                }
                if(juego.pos[57 - 10 * l] == 5 + 10 * k && juego.pos[58 - 10 * k] == 4 + 10 * k)
                    peligroRey += 30;
            } else
            if(k1 < 5 && (juego.peonesEnLinea[l][1] == 0 || juego.peonesEnLinea[l][2] == 0))
            {
                peligroRey += i2 + 5;
                if(flag && juego.d[k][1] % 10 <= 2)
                    peligroRey += 2 * i2 + 5;
            }
            pr += (puntosPosReyPrincipio[i1] * i2) / 9;
        }
        if(!juego.pei[k] && !juego.ped[k])
            if(j2 >= 13)
            {
                if(enPrimeraLinea[k][j1])
                    if(k == 0)
                    {
                        if(j1 % 10 > 4 && juego.pos[j1 + 1] != 2 && juego.pos[j1 + 2] != 2 && juego.pos[18] != 2)
                        {
                            if(juego.pos[27] == 5)
                                pr += 3 * j2 - 24;
                            else
                            if(juego.pos[37] == 5 && juego.pos[27] == 3)
                                pr += 3 * j2 - 19;
                            else
                            if(juego.pos[37] == 5 && juego.pos[36] == 3)
                                pr += 3 * j2 - 29;
                            if(juego.pos[28] == 5)
                                pr += 2 * j2 - 20;
                            else
                            if(juego.pos[38] == 5)
                                pr += 2 * j2 - 17;
                        } else
                        if(j1 % 10 < 5 && juego.pos[j1 - 1] != 2 && juego.pos[j1 - 2] != 2 && juego.pos[11] != 2)
                        {
                            if(juego.pos[23] == 5)
                                pr += 2 * j2 - 18;
                            if(juego.pos[22] == 5)
                                pr += 2 * j2 - 18;
                            else
                            if(juego.pos[22] == 3 && juego.pos[32] == 5)
                                pr += 2 * j2 - 18;
                        }
                    } else
                    if(j1 % 10 > 4 && (j1 >= 87 || juego.pos[j1 + 1] != 12) && (j1 >= 86 || juego.pos[j1 + 2] != 12) && juego.pos[88] != 12)
                    {
                        if(juego.pos[77] == 15)
                            pr += 3 * j2 - 24;
                        else
                        if(juego.pos[67] == 15 && juego.pos[77] == 13)
                            pr += 3 * j2 - 19;
                        else
                        if(juego.pos[67] == 15 && juego.pos[66] == 13)
                            pr += 3 * j2 - 29;
                        if(juego.pos[78] == 15)
                            pr += 2 * j2 - 20;
                        else
                        if(juego.pos[68] == 15)
                            pr += 2 * j2 - 17;
                    } else
                    if(j1 % 10 < 5 && juego.pos[j1 - 1] != 12 && juego.pos[j1 - 2] != 12 && juego.pos[81] != 12)
                    {
                        if(juego.pos[73] == 15)
                            pr += 2 * j2 - 18;
                        if(juego.pos[72] == 15)
                            pr += 2 * j2 - 18;
                        else
                        if(juego.pos[72] == 13 && juego.pos[62] == 15)
                            pr += 2 * j2 - 17;
                    }
            } else
            {
                for(int i10 = 0; i10 <= 7; i10++)
                {
                    if(!dentroTablero[j1 + adondesPAlrededor[i10]] || juego.pos[j1 + adondesPAlrededor[i10]] != 5 + 10 * k)
                        continue;
                    pr += 15;
                    break;
                }

                pr += (puntosPosReyFinal[j1] * (50 - i2)) / 48;
            }
        if(peligroRey > 0)
        {
            int j10 = 0;
            for(int k10 = 1; k10 <= juego.c[l][0]; k10++)
                if(mismoCuadrante[juego.c[l][k10]][i1])
                    j10++;

            for(int l10 = 1; l10 <= juego.a[l][0]; l10++)
                if(mismoCuadrante[juego.a[l][l10]][i1])
                    j10++;

            if(j10 > 4)
                j10 = 4;
            if(j10 == 0 && k != bandoC)
                peligroRey += peligroRey / 7;
            else
            if(j10 > 0)
                peligroRey -= (j10 * peligroRey) / 6;
            if(i2 >= 10)
                peligroRey = (peligroRey * (11 - (30 - i2) / 3)) / 10;
            else
                peligroRey /= 3;
            if(i2 >= 13)
                peligroRey += (puntosPosReyPrincipio[i1] * ((peligroRey + 120) - i2)) / (120 - i2);
            if(flag)
                peligroRey += peligroRey / 5;
        }
        pp = (pp * (75 - j2)) / 60;
        if(juego.p[k][0] > juego.p[l][0])
            pp += (juego.p[k][0] - juego.p[l][0]) * (60 - 2 * j2);
        patc = (patc * i2) / 30;
        if(i2 >= 14 && !juego.pei[l] && !juego.ped[l])
        {
            boolean flag7 = false;
            if(enPrimeraLinea[l][i1])
            {
                byte byte0;
                if(k1 > 4)
                    byte0 = 1;
                else
                    byte0 = -1;
                for(int i11 = i1 + byte0; dentroTablero[i11]; i11 += byte0)
                {
                    if(juego.pos[i11] != 2 + 10 * l)
                        continue;
                    if(juego.peonesEnLinea[l][i11 % 10] > 0)
                        flag7 = true;
                    break;
                }

                if(flag7 || byte0 == 1 && juego.pos[28 + 50 * l] == 2 + 10 * l || byte0 == -1 && juego.pos[21 + 50 * l] == 2 + 10 * l)
                    pr += 15 + 2 * i2;
            } else
            if(l == 1)
            {
                if(i1 == 74 && juego.pos[83] == 13 || i1 == 75 && juego.pos[86] == 13)
                    pr += 15 + 2 * i2;
            } else
            if(i1 == 24 && juego.pos[13] == 3 || i1 == 25 && juego.pos[16] == 3)
                pr += 15 + 2 * i2;
        }
        return pp + patc + pc + pa + pt + pd + pr + peligroRey + septimafila + choques + bonus + rinconesFeos;
    }

    private void llenarenCuadOCerca()
    {
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 11; l <= 88; l++)
                if(mismoCuadrante[k][l] || Math.abs(k % 10 - l % 10) <= 2 && Math.abs(k / 10 - l / 10) <= 2)
                    enCuadOCerca[k][l] = true;

        }

    }

    public final String transformar(int k)
    {
        int l = Math.abs(k);
        String s;
        if(k >= 0)
            s = "+";
        else
            s = "-";
        s = s + l / vDePiezas[5] + ".";
        int i1 = (int)((double)(l % vDePiezas[5]) / 2.5D);
        if(i1 == 0)
            s = s + i1 + "0";
        else
        if(i1 < 10)
            s = s + "0" + i1;
        else
            s = s + i1;
        return s;
    }

    private void llenarmovsCaballines()
    {
        int ai[] = {
            19, 21, 12, 8, -12, -21, -19, -8
        };
        for(int k = 11; k <= 88; k++)
        {
            int ai1[] = new int[8];
            int ai2[] = new int[8];
            int ai3[] = new int[8];
            for(int l = 0; l <= 7; l++)
                ai1[l] = k + ai[l];

            for(int i1 = 0; i1 <= 7; i1++)
                ai2[i1] = (Math.abs(5 - ai1[i1] % 10) + Math.abs(5 - ai1[i1] / 10)) / 2;

            for(int j1 = 0; j1 <= 7; j1++)
            {
                int k1 = 100;
                int i2 = 0;
                for(int k2 = 0; k2 <= 7; k2++)
                    if(ai2[k2] < k1)
                    {
                        k1 = ai2[k2];
                        i2 = k2;
                    }

                ai2[i2] = 100;
                ai3[j1] = i2;
            }

            for(int l1 = 0; l1 <= 7; l1++)
            {
                int j2 = k + ai[ai3[l1]];
                if(j2 > 0 && dentroTablero[j2])
                    movsCaballines[k][l1] = j2;
            }

        }

    }

    private void llenarmovsReyuzcos()
    {
        int ai[] = {
            1, 11, 10, 9, -1, -11, -10, -9
        };
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 0; l <= 7; l++)
                if(k + ai[l] > 0 && dentroTablero[k + ai[l]])
                    movsReyuzcos[k][l] = k + ai[l];

        }

    }

    private void llenarAtaquesCaballezcos()
    {
        int ai[] = {
            12, 21, 19, 8, -12, -21, -19, -8
        };
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 0; l <= 7; l++)
                if(k + ai[l] > 0 && dentroTablero[k + ai[l]])
                    atCab[k][k + ai[l]] = true;

        }

    }

    private void llenarAtacabilidades()
    {
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 11; l <= 88; l++)
                if(atRey[l][k])
                {
                    atacabilidadTorreNS[l][k] = 14;
                    atacabilidadTorreEO[l][k] = 13;
                } else
                if(enCuadOCerca[l][k])
                {
                    atacabilidadTorreNS[l][k] = 12 - Math.abs(l % 10 - k % 10);
                    atacabilidadTorreEO[l][k] = 5;
                }

        }

        for(int i1 = 11; i1 <= 88; i1++)
        {
            for(int j1 = 11; j1 <= 88; j1++)
                if(atRey[j1][i1])
                    atacabilidadDama[j1][i1] = 9;
                else
                    atacabilidadDama[j1][i1] = Math.max(0, 6 - casillas_de_separacion(j1, i1));

        }

        for(int k1 = 11; k1 <= 88; k1++)
        {
            for(int l1 = 11; l1 <= 88; l1++)
                if(atRey[l1][k1])
                    atacabilidadAlfil[l1][k1] = 9;
                else
                if(enCuadOCerca[l1][k1])
                    atacabilidadAlfil[l1][k1] = 4;

        }

    }

    private final int casillas_de_separacion(int k, int l)
    {
        return Math.max(Math.abs(k / 10 - l / 10), Math.abs(k % 10 - l % 10));
    }

    private void llenarmismoFlanco()
    {
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 11; l <= 88; l++)
                if(k % 10 > 4 && l % 10 > 4 || k % 10 < 5 && l % 10 < 5)
                    mismoFlanco[k][l] = true;

        }

    }

    private void llenarPotenciaDe()
    {
        potenciaDe[1] = 2;
        potenciaDe[2] = 5;
        potenciaDe[3] = 3;
        potenciaDe[4] = 9;
    }

    private void llenarnoRetrocede()
    {
        for(int k = 0; k <= 1; k++)
        {
            for(int l = 11; l <= 88; l++)
            {
                for(int i1 = 11; i1 <= 88; i1++)
                    if(l / 10 == i1 / 10)
                        noRetrocede[k][l][i1] = true;
                    else
                    if(k == 0 && i1 > l || k == 1 && l > i1)
                        noRetrocede[k][l][i1] = true;

            }

        }

    }

    private final void llenaratRey()
    {
        int ai[] = {
            1, 11, 10, 9, -1, -11, -10, -9
        };
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 0; l <= 7; l++)
                if(dentroTablero[k + ai[l]])
                    atRey[k][k + ai[l]] = true;

        }

    }

    private final void llenaratacapor()
    {
        atacapor[2][1] = true;
        atacapor[2][10] = true;
        atacapor[3][11] = true;
        atacapor[3][9] = true;
        atacapor[4][1] = true;
        atacapor[4][11] = true;
        atacapor[4][10] = true;
        atacapor[4][9] = true;
        atacapor[12][1] = true;
        atacapor[12][10] = true;
        atacapor[13][11] = true;
        atacapor[13][9] = true;
        atacapor[14][1] = true;
        atacapor[14][11] = true;
        atacapor[14][10] = true;
        atacapor[14][9] = true;
    }

    private final void llenarcualIncs()
    {
        int ai[] = {
            1, 11, 10, 9, -1, -11, -10, -9
        };
        for(int k = 11; k <= 88; k++)
        {
            for(int l = 0; l <= 7; l++)
            {
                for(int i1 = k + ai[l]; dentroTablero[i1]; i1 += ai[l])
                    cualInc[k][i1] = ai[l];

            }

        }

        int ai1[] = {
            11, 9, -11, -9
        };
        for(int j1 = 11; j1 <= 88; j1++)
        {
            for(int k1 = 0; k1 <= 3; k1++)
            {
                for(int l1 = j1 + ai1[k1]; dentroTablero[l1]; l1 += ai1[k1])
                    cualIncAlfil[j1][l1] = ai1[k1];

            }

        }

        int ai2[] = {
            1, 10, -1, -10
        };
        for(int i2 = 11; i2 <= 88; i2++)
        {
            for(int j2 = 0; j2 <= 3; j2++)
            {
                for(int k2 = i2 + ai2[j2]; dentroTablero[k2]; k2 += ai2[j2])
                    cualIncTorre[i2][k2] = ai2[j2];

            }

        }

    }

    private void llenarseptima()
    {
        for(int k = 0; k <= 1; k++)
        {
            for(int l = 11; l <= 88; l++)
                if(7 - 5 * k == l / 10)
                    septima[k][l] = true;

        }

    }

    private void llenarmismoCuadrante()
    {
        for(int k1 = 11; k1 <= 88; k1++)
        {
            int k = k1 % 10;
            int l = k1 / 10;
            for(int l1 = 11; l1 <= 88; l1++)
            {
                int i1 = l1 % 10;
                int j1 = l1 / 10;
                if(k > 4 && l > 4 && i1 > 4 && j1 > 4)
                    mismoCuadrante[k1][l1] = true;
                else
                if(k > 4 && l < 5 && i1 > 4 && j1 < 5)
                    mismoCuadrante[k1][l1] = true;
                else
                if(k < 5 && l < 5 && i1 < 5 && j1 < 5)
                    mismoCuadrante[k1][l1] = true;
                else
                if(k < 5 && l > 4 && i1 < 5 && j1 > 4)
                    mismoCuadrante[k1][l1] = true;
            }

        }

    }

    private final void llenarDentroTablero()
    {
        for(int k = 10; k <= 80; k += 10)
        {
            for(int l = 1; l <= 8; l++)
                dentroTablero[k + l] = true;

        }

    }

    public void tdn()
    {
        matearemos = false;
        for(int k = 0; k < 27; k++)
        {
            for(int l = 0; l <= 1; l++)
            {
                primCortante[k][l] = 0;
                segCortante[k][l] = 0;
                terCortante[k][l] = 0;
            }

        }

    }

    final int maxp = 30;
    final int iniSufiBueno = 26000;
    final int iniSufiMalo = -26000;
    final int muerte = 25050;
    final int ventanaPrincipalInicio = 65;
    final int ventanaPrincipalFinal = 55;
    final int rebalseVentanaPrincipal = 75;
    final int cuantoBastante = 350;
    final int ventanaMejoraInicio = 75;
    final int ventanaMejoraFinal = 60;
    final int cuantoRepasada = 300;
    final int profDePartida = 1;
    final int nodosMinimos = 2000;
    final int plylimite = 29;
    final int ptjeEmpate = 0;
    final int cuantasSC = 8;
    final int margenEval = 1000;
    final int margenFutilSC = 220;
    final int margenFutilCapturaUP = 230;
    final int margenFutilNormal = 200;
    final int conformidad = 185;
    final boolean descarteSinBusqueda = true;
    final boolean jugada_nula = false;
    final int maxExtensiones = 1;
    final int reduccion = 3;
    final int torreEnBuenFlanco = 6;
    final int peonAtCentro = 8;
    final int superpeonAtCentro = 16;
    final int cabConectados = 6;
    final int bonusADama = 38;
    final int peonObs = 3;
    final int bonusPeonLigAtac = 6;
    final int opoobspeon = 4;
    final int maxprespeoncentro = 40;
    final int peonCentralRey = 14;
    final int peonCentralDama = 5;
    final int peonalmedio = 25;
    final int superpeonalmedio = 35;
    final int bonusDosAlfiles = 75;
    final int alfilAtrapadoh7 = 450;
    final int alfilClavadoh7 = 450;
    final int torrecasiSPPF = 15;
    final int torreSPPF = 17;
    final int torreSPAF = 6;
    final int torreDespejada = 34;
    final int torreApoyaPasado = 25;
    final int torreContraPasado = 25;
    final int cabMismoFlanco = 7;
    final int peonReyObs = 36;
    final int peonDamaObs = 36;
    final int superpeonReyObs = 48;
    final int superpeonDamaObs = 48;
    final int patrasadoAB = 20;
    final int patrasadoCE = 13;
    final int cabAtPP = 17;
    final int alfilAtPP = 18;
    final int torreAtPP = 19;
    final int choqueCaballin = 15;
    final int superchoqueCaballin = 24;
    final int caballinContraPeon = 10;
    final int supercaballinContraPeon = 20;
    final int choqueMovAlfil = 13;
    final int superchoqueMovAlfil = 28;
    final int choqueMovDama = 5;
    final int choqueMovTorreEO = 6;
    final int choqueMovTorreNS = 18;
    final int torredobladaNS = 32;
    final int supertorredobladaNS = 56;
    final int cabRincon = 63;
    final int plussuperrincon = 16;
    final int caballinIntimidado = 5;
    final int superpeonAtRey = 25;
    final int peonAtRey = 5;
    final int damaMismoFlanco = 7;
    final int damaPorfiada = 9;
    final int damaImpetuosa = 25;
    final int peonDamaPorfiado = 40;
    final int peonReyPorfiado = 45;
    final int PADPorf = 20;
    final int PARPorf = 15;
    final int PADObs = 55;
    final int PARObs = 40;
    final int rPegPeonF = 15;
    final int factorDamaInf = 12;
    final int damaInfiltrando = 30;
    final int p5a = 40;
    final int sinescapef7 = 50;
    final int sinescapef7temp = 30;
    final int listoparaenrocar = 30;
    final int casilistoparaenrocar = 10;
    final int alfilClavaCaballo = 25;
    final int superalfilClavaCaballo = 36;
    final int torreClavaPMenor = 21;
    final int supertorreClavaPMenor = 32;
    final int rendicion = -1500;
    final int margenDescarteCC[] = {
        0, 225, 250, 450
    };
    final int margenDescarteSC[] = {
        0, -3, 30, 30
    };
    final int margennm_paraqs = 30;
    final int peonDoblado[] = {
        0, -28, -25, -20, -13, -13, -22, -27, -30
    };
    final int peonAislado[] = {
        0, -11, -14, -29, -34, -35, -30, -14, -11
    };
    final int bonusSeptimaRey[] = {
        0, 32, 116
    };
    final int bonusSeptimaReyConP[] = {
        0, 46, 146
    };
    final int bonusSeptimaP[] = {
        0, 17, 43
    };
    final boolean piezamenor[] = {
        false, true, false, true, false, false, false, false, false, false, 
        false, true, false, true, false, false, false
    };
    final boolean alfilClavaEsto[] = {
        false, true, true, false, true, false, false, false, false, false, 
        false, true, true, false, true, false, false
    };
    int idioma;
    int cualturno;
    boolean conEvaluaciones;
    int anteriormejorpuntaje;
    boolean debug;
    String librito[] = {
        "Librito de aperturas.", "Move in book."
    };
    String mateenin[] = {
        " Mate en ", " Mate in "
    };
    String pocomaterial[] = {
        "No hay material para hacer mate.", "No material to mate."
    };
    String reyahogado[] = {
        "Rey ahogado -> Tablas.", "Stalemate -> draw."
    };
    String unicaj[] = {
        "Unica...", "the only move"
    };
    String mate[] = {
        " Mate!", " Mate!"
    };
    String omenos[] = {
        " o menos.", " or less."
    };
    int profundidad;
    int profundidadMaxima;
    int bandoC;
    int bandoCont;
    String mensaje;
    String cabecera;
    int profuMortal;
    int hjugadas[][][];
    boolean huboComida[][];
    int i;
    int j;
    int auxil;
    int rey;
    int vaticinio;
    int orden[];
    int inc[] = {
        10, -10
    };
    int agresividad[];
    boolean anticipando;
    boolean yaJugo;
    int qji;
    int qjf;
    int cualInc[][];
    int cualIncTorre[][];
    int cualIncAlfil[][];
    boolean atacapor[][];
    boolean septima[][];
    boolean noRetrocede[][][];
    int potenciaDe[] = {
        0, 2, 5, 3, 10, 0, 0
    };
    int calidad[] = {
        3, 2, 1, 2, 0, 3, 0, 0, 0, 0, 
        0, 2, 1, 2, 0, 3, 0
    };
    boolean vaPorDiagonales[] = {
        false, false, false, true, true, false, false, false, false, false, 
        false, false, false, true, true, false, false
    };
    boolean vaPorLineas[] = {
        false, false, true, false, true, false, false, false, false, false, 
        false, false, true, false, true, false, false
    };
    int pp;
    int patc;
    int pc;
    int pa;
    int pt;
    int pd;
    int pr;
    int primlin;
    int peligroRey;
    int septimafila;
    int choques;
    int bonus;
    int rinconesFeos;
    boolean unica;
    boolean morimos;
    boolean matearemos;
    boolean soloActualizarReloj;
    boolean conTiempo;
    boolean tiempoFijo;
    boolean compuAhogado;
    boolean enemigoAhogado;
    boolean estabaJaque[];
    boolean esCaptura[];
    boolean seextendiojaque[];
    boolean jugnula[];
    int lamejorini;
    int lamejorfin;
    String mejorpuntaje;
    int mateEn;
    int proximaInf;
    int cuantoMas;
    long tiempoAnt;
    long tiempoAux;
    long ultimaActR;
    int difer;
    String mensajeFinal;
    final int adondesED[] = {
        10, 11, 21, -10, -9, -19
    };
    final int adondesEI[] = {
        10, 9, 8, -10, -11, -12
    };
    final int adondesPAlrededor[] = {
        1, 11, 10, 9, -1, -11, -10, -9
    };
    final String nombresCas[] = {
        "?", " ", " ", " ", " ", " ", " ", " ", " ", " ", 
        " ", "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", " ", 
        " ", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", " ", 
        " ", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", " ", 
        " ", "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4", " ", 
        " ", "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5", " ", 
        " ", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", " ", 
        " ", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", " ", 
        " ", "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"
    };
    long tiempoPartida;
    long limite;
    long tiempoPorJugada;
    long cuantoTiempoFijo;
    long tiempoRestante;
    int numeroDeJugada;
    int minimo;
    int maximo;
    boolean atRey[][];
    boolean mismoCuadrante[][];
    boolean atCab[][];
    boolean mismoFlanco[][];
    int mejoresRespuestas[][];
    final int puntosPosReyFinal[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 1, 4, 5, 5, 4, 1, 0, 0, 
        0, 4, 7, 15, 16, 16, 15, 7, 4, 0, 
        0, 5, 8, 23, 28, 28, 23, 8, 5, 0, 
        0, 8, 14, 28, 38, 38, 28, 14, 8, 0, 
        0, 8, 14, 28, 38, 38, 28, 14, 8, 0, 
        0, 5, 8, 23, 28, 28, 23, 8, 5, 0, 
        0, 4, 7, 15, 16, 16, 15, 7, 4, 0, 
        0, 0, 1, 4, 5, 5, 4, 1, 0
    };
    final int puntosPosReyPrincipio[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 0, 4, 8, 7, 5, 0, 1, 0, 
        0, 4, 4, 5, 15, 15, 5, 4, 4, 0, 
        0, 20, 23, 40, 50, 50, 40, 23, 20, 0, 
        0, 45, 56, 62, 90, 90, 62, 56, 45, 0, 
        0, 45, 56, 62, 90, 90, 62, 56, 45, 0, 
        0, 20, 23, 40, 50, 50, 40, 23, 20, 0, 
        0, 4, 4, 5, 15, 15, 5, 4, 4, 0, 
        0, 1, 0, 4, 8, 7, 5, 0, 1
    };
    final boolean casBlanca[] = {
        false, false, false, false, false, false, false, false, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false
    };
    final boolean casNegra[] = {
        false, false, false, false, false, false, false, false, false, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true, false, 
        false, true, false, true, false, true, false, true, false, false, 
        false, false, true, false, true, false, true, false, true
    };
    final boolean enPrimeraLinea[][] = {
        {
            false, false, false, false, false, false, false, false, false, false, 
            false, true, true, true, true, true, true, true, true, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false
        }, {
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, true, true, true, true, true, true, true, true
        }
    };
    boolean dentroTablero[];
    boolean ultimaJugada;
    final int vDePiezas[] = {
        0, 796, 1250, 833, 2330, 225, 4000, 0, 0, 0, 
        0, 796, 1250, 833, 2330, 225, 4000
    };
    final int cuantoMasMenos = 1;
    final int puntosPAvanzados[][] = {
        {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 
            0, 2, 2, 3, 4, 4, 3, 2, 2, 0, 
            0, 4, 4, 5, 5, 5, 5, 4, 4, 0, 
            0, 6, 6, 7, 7, 7, 7, 6, 6, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 6, 6, 7, 7, 7, 7, 6, 6, 0, 
            0, 4, 4, 5, 5, 5, 5, 4, 4, 0, 
            0, 2, 2, 3, 4, 4, 3, 2, 2, 0, 
            0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    final int bonusPALigados[][] = {
        {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 2, 2, 2, 4, 4, 2, 2, 2, 0, 
            0, 4, 5, 6, 9, 9, 6, 5, 4, 0, 
            0, 8, 8, 9, 11, 11, 9, 8, 8, 0, 
            0, 11, 12, 15, 17, 17, 15, 12, 11, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 11, 12, 15, 17, 17, 15, 12, 11, 0, 
            0, 8, 8, 9, 11, 11, 9, 8, 8, 0, 
            0, 4, 5, 6, 9, 9, 6, 5, 4, 0, 
            0, 2, 2, 2, 4, 4, 2, 2, 2, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    final int puntosPPasadosLigados[][] = {
        {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 40, 40, 40, 40, 40, 40, 40, 40, 0, 
            0, 40, 40, 40, 40, 40, 40, 40, 40, 0, 
            0, 64, 64, 64, 64, 64, 64, 64, 64, 0, 
            0, 88, 88, 88, 88, 88, 88, 88, 88, 0, 
            0, 112, 112, 112, 112, 112, 112, 112, 112, 0, 
            0, 136, 136, 136, 136, 136, 136, 136, 136, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 136, 136, 136, 136, 136, 136, 136, 136, 0, 
            0, 112, 112, 112, 112, 112, 112, 112, 112, 0, 
            0, 88, 88, 88, 88, 88, 88, 88, 88, 0, 
            0, 64, 64, 64, 64, 64, 64, 64, 64, 0, 
            0, 40, 40, 40, 40, 40, 40, 40, 40, 0, 
            0, 40, 40, 40, 40, 40, 40, 40, 40, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    final int puntosPPasadosSuperLigados[][] = {
        {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 
            0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 
            0, 80, 80, 80, 80, 80, 80, 80, 80, 0, 
            0, 116, 116, 116, 116, 116, 116, 116, 116, 0, 
            0, 165, 165, 165, 165, 165, 165, 165, 165, 0, 
            0, 210, 210, 210, 210, 210, 210, 210, 210, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 210, 210, 210, 210, 210, 210, 210, 210, 0, 
            0, 165, 165, 165, 165, 165, 165, 165, 165, 0, 
            0, 116, 116, 116, 116, 116, 116, 116, 116, 0, 
            0, 80, 80, 80, 80, 80, 80, 80, 80, 0, 
            0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 
            0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    final int puntosPPasadosNoLigados[][] = {
        {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 35, 35, 35, 35, 35, 35, 35, 35, 0, 
            0, 35, 35, 35, 35, 35, 35, 35, 35, 0, 
            0, 57, 57, 57, 57, 57, 57, 57, 57, 0, 
            0, 79, 79, 79, 79, 79, 79, 79, 79, 0, 
            0, 101, 101, 101, 101, 101, 101, 101, 101, 0, 
            0, 123, 123, 123, 123, 123, 123, 123, 123, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }, {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 123, 123, 123, 123, 123, 123, 123, 123, 0, 
            0, 101, 101, 101, 101, 101, 101, 101, 101, 0, 
            0, 79, 79, 79, 79, 79, 79, 79, 79, 0, 
            0, 57, 57, 57, 57, 57, 57, 57, 57, 0, 
            0, 35, 35, 35, 35, 35, 35, 35, 35, 0, 
            0, 35, 35, 35, 35, 35, 35, 35, 35, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0
        }
    };
    final int puntosCab[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 5, 7, 7, 7, 7, 5, 0, 0, 
        0, 2, 8, 21, 23, 23, 21, 8, 2, 0, 
        0, 3, 21, 28, 28, 28, 28, 21, 3, 0, 
        0, 4, 23, 28, 34, 34, 28, 23, 4, 0, 
        0, 4, 23, 28, 34, 34, 28, 23, 4, 0, 
        0, 3, 21, 28, 28, 28, 28, 21, 3, 0, 
        0, 2, 8, 21, 23, 23, 21, 8, 2, 0, 
        0, 0, 5, 7, 7, 7, 7, 5, 0
    };
    final boolean rinconTablero[] = {
        false, false, false, false, false, false, false, false, false, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, true, false, false, false, false, false, false, true
    };
    final boolean esC[] = {
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, true, true, false, false, false, false, 
        false, false, false, false, true, true, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false
    };
    final boolean caballitobacan[][] = {
        {
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false
        }, {
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, true, true, true, true, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false, false, 
            false, false, false, false, false, false, false, false, false
        }
    };
    final boolean bordeTablero[] = {
        false, false, false, false, false, false, false, false, false, false, 
        false, true, true, true, true, true, true, true, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, false, false, false, false, false, false, true, false, 
        false, true, true, true, true, true, true, true, true
    };
    boolean casVacias[];
    int victoria;
    int agresividades[][];
    int pinis[][];
    int pfins[][];
    int cinis[][];
    int cfins[][];
    int piezamov[][];
    int evals[];
    int comida[];
    boolean cortamos[];
    int primCortante[][];
    int segCortante[][];
    int terCortante[][];
    int comCortanteI[];
    int comCortanteF[];
    boolean capturaskiller;
    int puntajesUltimos[];
    Juego juegos[];
    int puntajesPrimeros[];
    int movsCaballines[][];
    int movsReyuzcos[][];
    int jugsAnteriores[][];
    int posAnalizadas;
    int nodosNormales;
    int nodosExtension;
    int nivel;
    boolean pvcompleta;
    boolean cuentaSoloFinal;
    int incAlfil[] = {
        11, 9, -11, -9
    };
    int incDama[] = {
        1, 11, 10, 9, -1, -11, -10, -9
    };
    int incTorre[] = {
        1, 10, -1, -10
    };
    boolean enCuadOCerca[][];
    int atacabilidadAlfil[][];
    int atacabilidadDama[][];
    int atacabilidadTorreNS[][];
    int atacabilidadTorreEO[][];
    int movilidadTorreEO[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 
        0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 
        0, 1, 1, 1, 2, 2, 1, 1, 1, 0, 
        0, 1, 1, 1, 2, 2, 1, 1, 1, 0, 
        0, 1, 1, 1, 2, 2, 1, 1, 1, 0, 
        0, 1, 1, 1, 2, 2, 1, 1, 1, 0, 
        0, 0, 1, 1, 2, 2, 1, 1, 0, 0, 
        0, 0, 0, 1, 1, 1, 1, 0, 0
    };
    int movilidadTorreNS[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 
        0, 1, 1, 2, 3, 3, 2, 1, 1, 0, 
        0, 1, 3, 5, 5, 5, 5, 3, 1, 0, 
        0, 2, 3, 6, 11, 11, 6, 3, 3, 0, 
        0, 2, 3, 6, 11, 11, 6, 3, 3, 0, 
        0, 1, 3, 5, 5, 5, 5, 3, 1, 0, 
        0, 1, 1, 2, 3, 3, 2, 1, 1, 0, 
        0, 0, 1, 1, 1, 1, 1, 1, 0
    };
    int movilidadAlfil[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 
        0, 1, 2, 1, 1, 1, 1, 2, 1, 0, 
        0, 1, 2, 3, 2, 2, 3, 2, 1, 0, 
        0, 1, 2, 2, 8, 8, 2, 2, 1, 0, 
        0, 1, 2, 2, 8, 8, 2, 2, 1, 0, 
        0, 1, 2, 3, 2, 2, 3, 2, 1, 0, 
        0, 1, 2, 1, 1, 1, 1, 2, 1, 0, 
        0, 0, 1, 1, 1, 1, 1, 1, 0
    };
    int centralizacionDama[] = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 2, 3, 3, 3, 3, 2, 0, 0, 
        0, 1, 4, 4, 7, 7, 4, 4, 1, 0, 
        0, 2, 5, 7, 10, 10, 7, 5, 2, 0, 
        0, 2, 5, 7, 10, 10, 7, 5, 2, 0, 
        0, 1, 4, 4, 7, 7, 4, 4, 1, 0, 
        0, 0, 2, 3, 3, 3, 3, 2, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0
    };
}