// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 


class Posicionador
{

    public Posicionador(Juego juego)
    {
        j = juego;
    }

    public void posInicial()
    {
        j.potencia[0] = 30;
        j.potencia[1] = 30;
        j.contador50m = 0;
        j.posRey[0] = 15;
        j.posRey[1] = 85;
        j.ped[0] = true;
        j.ped[1] = true;
        j.pei[0] = true;
        j.pei[1] = true;
        j.c[0][0] = 2;
        j.c[1][0] = 2;
        j.t[0][0] = 2;
        j.t[1][0] = 2;
        j.a[0][0] = 2;
        j.a[1][0] = 2;
        j.d[0][0] = 1;
        j.d[1][0] = 1;
        j.p[0][0] = 8;
        j.p[1][0] = 8;
        for(int i = 0; i <= 1; i++)
        {
            j.c[i][1] = i * 70 + 12;
            j.c[i][2] = i * 70 + 17;
            j.t[i][1] = i * 70 + 11;
            j.t[i][2] = i * 70 + 18;
            j.a[i][1] = i * 70 + 13;
            j.a[i][2] = i * 70 + 16;
            j.d[i][1] = i * 70 + 14;
        }

        for(int k = 0; k <= 1; k++)
        {
            j.p[k][1] = k * 50 + 25;
            j.p[k][2] = k * 50 + 24;
            j.p[k][3] = k * 50 + 23;
            j.p[k][4] = k * 50 + 28;
            j.p[k][5] = k * 50 + 27;
            j.p[k][6] = k * 50 + 21;
            j.p[k][7] = k * 50 + 22;
            j.p[k][8] = k * 50 + 26;
        }

        for(int l = 31; l <= 68; l++)
            j.pos[l] = 0;

        for(int i1 = 21; i1 <= 28; i1++)
            j.pos[i1] = 5;

        for(int j1 = 71; j1 <= 78; j1++)
            j.pos[j1] = 15;

        for(int k1 = 0; k1 <= 1; k1++)
        {
            j.pos[k1 * 70 + 11] = k1 * 10 + 2;
            j.pos[k1 * 70 + 18] = k1 * 10 + 2;
            j.pos[k1 * 70 + 12] = k1 * 10 + 1;
            j.pos[k1 * 70 + 17] = k1 * 10 + 1;
            j.pos[k1 * 70 + 13] = k1 * 10 + 3;
            j.pos[k1 * 70 + 16] = k1 * 10 + 3;
            j.pos[k1 * 70 + 14] = k1 * 10 + 4;
            j.pos[k1 * 70 + 15] = k1 * 10 + 6;
        }

        for(int l1 = 0; l1 <= 1; l1++)
        {
            for(int i2 = 1; i2 <= 8; i2++)
                j.peonesEnLinea[l1][i2] = 1;

        }

        for(int j2 = 11; j2 <= 88; j2++)
            j.casVacias[j2] = false;

        for(int k2 = 31; k2 <= 68; k2++)
            j.casVacias[k2] = true;

        for(int l2 = 0; l2 <= 1; l2++)
        {
            for(int i3 = 11; i3 <= 88; i3++)
                j.hayPieza[l2][i3] = false;

        }

        for(int j3 = 11; j3 <= 28; j3++)
            j.hayPieza[0][j3] = true;

        for(int k3 = 71; k3 <= 88; k3++)
            j.hayPieza[1][k3] = true;

        j.casAtPorP = new int[2][90];
        j.casAtPorP[0][31] = 1;
        j.casAtPorP[0][38] = 1;
        for(int l3 = 32; l3 <= 37; l3++)
            j.casAtPorP[0][l3] = 2;

        j.casAtPorP[1][61] = 1;
        j.casAtPorP[1][68] = 1;
        for(int i4 = 62; i4 <= 67; i4++)
            j.casAtPorP[1][i4] = 2;

        int j4 = 2 * (j.vDePiezas[1] + j.vDePiezas[2] + j.vDePiezas[3]) + j.vDePiezas[4] + 8 * j.vDePiezas[5];
        j.material[0] = j4;
        j.material[1] = j4;
    }

    Juego j;
}
