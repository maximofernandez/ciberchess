// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 

class maleitor
{

    public maleitor()
    {
        libro = new librito();
        conLibro = true;
        ultimapos = 11;
        vaACoronar = false;
        posiniC = 0;
        posfinC = 0;
        jueguito = new Juego();
        verificador = new vju();
        jugadas = new int[200][2][2];
        huboComida = new boolean[200][2];
        ultposi = 0;
        ultposf = 0;
        ultpieza = 0;
        estaticamente = 0;
        libro.tdn();
        libro.cargarJugadas();
        invencible = new cerebrin();
        invencible.hjugadas = jugadas;
        invencible.huboComida = huboComida;
        numJug = 0;
        jueguito.atRey = invencible.atRey;
        jueguito.atCab = invencible.atCab;
        jueguito.dentroTablero = invencible.dentroTablero;
        jueguito.vaPorLineas = invencible.vaPorLineas;
        jueguito.vaPorDiagonales = invencible.vaPorDiagonales;
        jueguito.cualInc = invencible.cualInc;
        jueguito.cualIncAlfil = invencible.cualIncAlfil;
        jueguito.cualIncTorre = invencible.cualIncTorre;
        jueguito.atacapor = invencible.atacapor;
        jueguito.septima = invencible.septima;
        jueguito.potenciaDe = invencible.potenciaDe;
        comienzo = new Posicionador(jueguito);
        comienzo.posInicial();
        mensajes = new String[3];
        jugs = new int[3];
    }

    public void corona(int i, int j)
    {
        posfinC += j;
        jueguito.mover(posiniC, posfinC, i);
        ultposi = posiniC;
        ultposf = posfinC;
        ultpieza = 5;
        if(i == 0)
            numJug++;
        jugadas[numJug][i][0] = posiniC;
        jugadas[numJug][i][1] = posfinC;
        invencible.soloActualizarReloj = false;
    }

    public boolean esValida(int i, int j, int k)
    {
        if(verificador.verificarValidez(jueguito, j, k, i))
        {
            if(!verificador.quiereCoronar)
            {
                if(libro.dentroDelLibro)
                    libro.actualizarLlave(j, k);
                ultpieza = jueguito.pos[j] % 10;
                jueguito.mover(j, k, i);
                ultposi = j;
                ultposf = k;
                if(i == 0)
                    numJug++;
                jugadas[numJug][i][0] = j;
                jugadas[numJug][i][1] = k;
                huboComida[numJug][i] = jueguito.seComio;
                return true;
            } else
            {
                verificador.quiereCoronar = false;
                vaACoronar = true;
                huboComida[numJug][i] = true;
                posiniC = j;
                posfinC = 20 + (k % 10 - j % 10) * 10;
                return true;
            }
        } else
        {
            return false;
        }
    }

    public int[] juega(int i, long l)
    {
        boolean flag = false;
        if(libro.dentroDelLibro && conLibro)
        {
            int ai[] = libro.respuesta();
            if(ai[0] != 0)
            {
                jugs[0] = ai[0];
                jugs[1] = ai[1];
                jugs[2] = 0;
                flag = true;
                invencible.mensajeFinal = invencible.librito[invencible.idioma];
            }
        }
        invencible.tiempoRestante = l;
        invencible.conTiempo = true;
        invencible.numeroDeJugada = numJug + 1;
        if(!flag)
        {
            jugs = invencible.jugar(jueguito, i, ultposi, ultposf);
            if(libro.dentroDelLibro)
                libro.actualizarLlave(jugs[0], jugs[1]);
        }
        huboComida[numJug][i] = jueguito.seComio;
        if(jugs[2] != -1 && jugs[2] != 2)
        {
            jueguito.mover(jugs[0], jugs[1], i);
            ultimapos = jugs[1];
            if(i == 0)
                numJug++;
            jugadas[numJug][i][0] = jugs[0];
            jugadas[numJug][i][1] = jugs[1];
            return jugs;
        } else
        {
            return jugs;
        }
    }

    public void tdn()
    {
        libro.tdn();
        comienzo.posInicial();
        invencible.tdn();
        numJug = 0;
        ultposi = 0;
        ultposf = 0;
        ultpieza = 0;
    }

    librito libro;
    boolean conLibro;
    int ultimapos;
    boolean vaACoronar;
    int posiniC;
    int posfinC;
    Juego jueguito;
    Posicionador comienzo;
    vju verificador;
    cerebrin invencible;
    String mensajes[];
    int cualMensaje[];
    int jugs[];
    int numJug;
    int jugadas[][][];
    boolean huboComida[][];
    int ultposi;
    int ultposf;
    int ultpieza;
    int estaticamente;
}
