// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 


class Juego
{

    public Juego()
    {
        pos = new int[89];
        c = new int[2][11];
        t = new int[2][11];
        a = new int[2][11];
        d = new int[2][10];
        p = new int[2][9];
        contador50m = 0;
        casVacias = new boolean[89];
        hayPieza = new boolean[2][90];
        seComioAlPaso = false;
        seComio = false;
        quesecomio = 0;
        seEnroco = false;
        seCorono = false;
        peonesEnLinea = new int[2][10];
        otroAuxiliar = 10;
        comoEstaba = true;
        ultimaPieza = 1;
        casAtPorP = new int[2][90];
        material = new int[2];
        potencia = new int[2];
        hayEscaqueAP = false;
        cualAP = 0;
    }

    public final boolean noNosJaquearon(int j, int k, int l, int i1)
    {
        if(seComioAlPaso || seCorono || seEnroco)
            return minimoatacante(j, 1 - i1) == 0;
        inc = cualInc[j][k];
        if(inc != 0)
        {
            for(int j1 = j + inc; dentroTablero[j1]; j1 += inc)
            {
                if(casVacias[j1])
                    continue;
                if(hayPieza[1 - i1][j1] && atacapor[pos[j1]][Math.abs(inc)])
                    return false;
                break;
            }

        }
        if(ultimaPieza == 6)
            return true;
        if(ultimaPieza == 5)
            return casAtPorP[1 - i1][j] <= 0;
        if(ultimaPieza == 1)
            return !atCab[j][l];
        inc = cualInc[j][l];
        if(inc == 0 || !atacapor[ultimaPieza][Math.abs(inc)])
            return true;
        for(int k1 = j + inc; dentroTablero[k1]; k1 += inc)
        {
            if(casVacias[k1])
                continue;
            if(k1 == l)
                return false;
            break;
        }

        return true;
    }

    public final boolean noDejaJaqueHacia(int j, int k, int l, int i1)
    {
        if(k == j)
            return noDejaJaque(k, l, l % 10, l / 10, l, i1);
        if(pos[k] % 10 == 5 && k / 10 == 5 - i1 && casVacias[l] && Math.abs(l - k) != 10)
            return noDejaJaque(k, l, j % 10, j / 10, j, i1);
        inc = cualInc[j][k];
        if(inc == 0)
            return true;
        if(pos[k] % 10 == 5 && septima[i1][k])
            l = ((k + 8) - 20 * i1) + l / 10;
        if(inc == cualInc[j][l])
            return true;
        for(i = j + inc; dentroTablero[i]; i += inc)
        {
            if(casVacias[i] || i == k)
                continue;
            if(hayPieza[1 - i1][i] && atacapor[pos[i]][Math.abs(inc)])
                return false;
            break;
        }

        return true;
    }

    public final void mover(int j, int k, int l)
    {
        int i1 = pos[j] % 10;
        ultimaPieza = i1;
        seEnroco = false;
        seComio = false;
        seComioAlPaso = false;
        seCorono = false;
        if(i1 != 5)
        {
            contador50m++;
            hayEscaqueAP = false;
        } else
        {
            contador50m = 0;
            inc = 10 - 20 * l;
            casAtPorP[l][j + inc + 1]--;
            casAtPorP[l][(j + inc) - 1]--;
            if(septima[l][j])
            {
                seComio = true;
                seCorono = true;
                int j1 = ((j + 8) - 20 * l) + k / 10;
                int l1 = k % 10;
                material[l] -= vDePiezas[5];
                for(int j2 = 1; j2 <= p[l][0]; j2++)
                {
                    if(p[l][j2] != j)
                        continue;
                    for(int l2 = j2; l2 < p[l][0]; l2++)
                        p[l][l2] = p[l][l2 + 1];

                    p[l][0]--;
                    break;
                }

                peonesEnLinea[l][j % 10]--;
                pos[j] = l1 + 10 * l;
                int ai[][];
                if(l1 == 1)
                    ai = c;
                else
                if(l1 == 2)
                    ai = t;
                else
                if(l1 == 3)
                    ai = a;
                else
                    ai = d;
                ai[l][ai[l][0] + 1] = j;
                ai[l][0]++;
                material[l] += vDePiezas[l1];
                potencia[l] += potenciaDe[l1];
                i1 = l1;
                k = j1;
            }
        }
        casVacias[j] = true;
        hayPieza[l][j] = false;
        casVacias[k] = false;
        hayPieza[l][k] = true;
        switch(i1)
        {
        case 1: // '\001'
            for(int k1 = 1; k1 <= c[l][0]; k1++)
            {
                if(c[l][k1] != j)
                    continue;
                c[l][k1] = k;
                break;
            }

            break;

        case 2: // '\002'
            if(pei[l] && j == l * 70 + 11)
                pei[l] = false;
            else
            if(ped[l] && j == l * 70 + 18)
                ped[l] = false;
            for(int i2 = 1; i2 <= t[l][0]; i2++)
            {
                if(t[l][i2] != j)
                    continue;
                t[l][i2] = k;
                break;
            }

            break;

        case 3: // '\003'
            for(int k2 = 1; k2 <= a[l][0]; k2++)
            {
                if(a[l][k2] != j)
                    continue;
                a[l][k2] = k;
                break;
            }

            break;

        case 4: // '\004'
            for(int i3 = 1; i3 <= d[l][0]; i3++)
            {
                if(d[l][i3] != j)
                    continue;
                d[l][i3] = k;
                break;
            }

            break;

        case 5: // '\005'
            hayEscaqueAP = false;
            if(j / 10 == 5 * l + 2 && k - j == 2 * inc && casAtPorP[1 - l][j + inc] > 0)
            {
                hayEscaqueAP = true;
                cualAP = k;
            }
            casAtPorP[l][k + inc + 1]++;
            casAtPorP[l][(k + inc) - 1]++;
            for(int j3 = 1; j3 <= p[l][0]; j3++)
            {
                if(p[l][j3] != j)
                    continue;
                p[l][j3] = k;
                if((k - j) % 10 == 0)
                    continue;
                peonesEnLinea[l][j % 10]--;
                peonesEnLinea[l][k % 10]++;
                if(pos[k] == 0)
                {
                    seComio = true;
                    seComioAlPaso = true;
                    quesecomio = 5;
                    int k3 = (j + k % 10) - j % 10;
                    pos[k] = pos[j];
                    pos[k3] = 0;
                    pos[j] = 0;
                    l = 1 - l;
                    material[l] -= vDePiezas[5];
                    peonesEnLinea[l][k % 10]--;
                    casAtPorP[l][(k3 - inc) + 1]--;
                    casAtPorP[l][k3 - inc - 1]--;
                    casVacias[k3] = true;
                    hayPieza[l][k3] = false;
                    for(int i4 = 1; i4 <= p[l][0]; i4++)
                    {
                        if(p[l][i4] != k3)
                            continue;
                        for(int k4 = i4; k4 < p[l][0]; k4++)
                            p[l][k4] = p[l][k4 + 1];

                        p[l][0]--;
                        break;
                    }

                    return;
                }
                break;
            }

            break;

        case 6: // '\006'
            if(pei[l])
                pei[l] = false;
            if(ped[l])
                ped[l] = false;
            posRey[l] = k;
            if(k == j + 2)
            {
                contador50m = 0;
                pos[k] = pos[j];
                pos[j] = 0;
                mover(l * 70 + 18, l * 70 + 16, l);
                return;
            }
            if(k == j - 2)
            {
                contador50m = 0;
                pos[k] = pos[j];
                pos[j] = 0;
                mover(l * 70 + 11, l * 70 + 14, l);
                return;
            }
            break;
        }
        if(pos[k] == 0)
        {
            pos[k] = pos[j];
            pos[j] = 0;
            return;
        }
        seComio = true;
        contador50m = 0;
        l = 1 - l;
        hayPieza[l][k] = false;
        int l3 = pos[k] % 10;
        quesecomio = l3;
        material[l] -= vDePiezas[l3];
        potencia[l] -= potenciaDe[l3];
label0:
        switch(l3)
        {
        default:
            break;

        case 1: // '\001'
            for(int j4 = 1; j4 <= c[l][0]; j4++)
            {
                if(c[l][j4] != k)
                    continue;
                for(int l4 = j4; l4 < c[l][0]; l4++)
                    c[l][l4] = c[l][l4 + 1];

                c[l][0]--;
                break;
            }

            break;

        case 2: // '\002'
            if(pei[l] && k == l * 70 + 11)
                pei[l] = false;
            else
            if(ped[l] && k == l * 70 + 18)
                ped[l] = false;
            for(int i5 = 1; i5 <= t[l][0]; i5++)
                if(t[l][i5] == k)
                {
                    for(int j5 = i5; j5 < t[l][0]; j5++)
                        t[l][j5] = t[l][j5 + 1];

                    t[l][0]--;
                    break label0;
                }

            break;

        case 3: // '\003'
            for(int k5 = 1; k5 <= a[l][0]; k5++)
                if(a[l][k5] == k)
                {
                    for(int l5 = k5; l5 < a[l][0]; l5++)
                        a[l][l5] = a[l][l5 + 1];

                    a[l][0]--;
                    break label0;
                }

            break;

        case 4: // '\004'
            for(int i6 = 1; i6 <= d[l][0]; i6++)
                if(d[l][i6] == k)
                {
                    for(int j6 = i6; j6 < d[l][0]; j6++)
                        d[l][j6] = d[l][j6 + 1];

                    d[l][0]--;
                    break label0;
                }

            break;

        case 5: // '\005'
            peonesEnLinea[l][k % 10]--;
            inc = 10 - 20 * l;
            casAtPorP[l][k + inc + 1]--;
            casAtPorP[l][(k + inc) - 1]--;
            for(int k6 = 1; k6 <= p[l][0]; k6++)
                if(p[l][k6] == k)
                {
                    for(int l6 = k6; l6 < p[l][0]; l6++)
                        p[l][l6] = p[l][l6 + 1];

                    p[l][0]--;
                    break label0;
                }

            break;
        }
        pos[k] = pos[j];
        pos[j] = 0;
    }

    public final boolean topamoscondeslizante(int j, int k, int l)
    {
        for(int i1 = j + l; dentroTablero[i1]; i1 += l)
            if(!casVacias[i1])
                return hayPieza[k][i1] && atacapor[pos[i1]][Math.abs(l)];

        return false;
    }

    public final int minimoatacante(int j, int k)
    {
        if(casAtPorP[k][j] > 0)
            return 5;
        for(int l = 1; l <= c[k][0]; l++)
            if(atCab[c[k][l]][j])
                return 1;

        for(int i1 = 1; i1 <= a[k][0]; i1++)
        {
            inc = cualIncAlfil[a[k][i1]][j];
            if(inc != 0)
            {
                int j1 = a[k][i1] + inc;
                do
                {
                    if(j1 == j)
                        return 3;
                    if(!casVacias[j1])
                        break;
                    j1 += inc;
                } while(true);
            }
        }

        for(int k1 = 1; k1 <= t[k][0]; k1++)
        {
            inc = cualIncTorre[t[k][k1]][j];
            if(inc != 0)
            {
                int l1 = t[k][k1] + inc;
                do
                {
                    if(l1 == j)
                        return 2;
                    if(!casVacias[l1])
                        break;
                    l1 += inc;
                } while(true);
            }
        }

        for(int i2 = 1; i2 <= d[k][0]; i2++)
        {
            inc = cualInc[d[k][i2]][j];
            if(inc != 0)
            {
                int j2 = d[k][i2] + inc;
                do
                {
                    if(j2 == j)
                        return 4;
                    if(!casVacias[j2])
                        break;
                    j2 += inc;
                } while(true);
            }
        }

        return !atRey[posRey[k]][j] ? 0 : 6;
    }

    public final boolean noDejaJaque(int j, int k, int l, int i1, int j1, int k1)
    {
        turnoCont = 1 - k1;
        for(int l1 = 1; l1 <= c[turnoCont][0]; l1++)
            if(atCab[c[turnoCont][l1]][j1] && k != c[turnoCont][l1])
                return false;

        if(atRey[j1][posRey[turnoCont]])
            return false;
        if(hayEscaqueAP && pos[j] == 5 + 10 * k1 && j / 10 == 5 - k1 && k == (cualAP + turnoCont * 20) - 10)
        {
            casVacias[cualAP] = true;
            otroAuxiliar = cualAP;
        }
        casVacias[j] = true;
        comoEstaba = casVacias[k];
        casVacias[k] = false;
        if(d[turnoCont][0] > 0 || t[turnoCont][0] > 0)
        {
            if(l <= 7)
                for(i = j1 + 1; dentroTablero[i]; i++)
                {
                    if(casVacias[i])
                        continue;
                    if(i != k && !hayPieza[k1][i] && vaPorLineas[pos[i]])
                    {
                        d(j, k);
                        return false;
                    }
                    break;
                }

            if(l >= 2)
                for(i = j1 - 1; dentroTablero[i]; i--)
                {
                    if(casVacias[i])
                        continue;
                    if(i != k && !hayPieza[k1][i] && vaPorLineas[pos[i]])
                    {
                        d(j, k);
                        return false;
                    }
                    break;
                }

            if(i1 >= 2)
                for(i = j1 - 10; i > 10; i -= 10)
                {
                    if(casVacias[i])
                        continue;
                    if(i != k && !hayPieza[k1][i] && vaPorLineas[pos[i]])
                    {
                        d(j, k);
                        return false;
                    }
                    break;
                }

            if(i1 <= 7)
                for(i = j1 + 10; i < 89; i += 10)
                {
                    if(casVacias[i])
                        continue;
                    if(i != k && !hayPieza[k1][i] && vaPorLineas[pos[i]])
                    {
                        d(j, k);
                        return false;
                    }
                    break;
                }

        }
        if(d[turnoCont][0] > 0 || a[turnoCont][0] > 0)
        {
            for(i = j1 + 11; dentroTablero[i]; i += 11)
            {
                if(casVacias[i])
                    continue;
                if(i != k && !hayPieza[k1][i] && vaPorDiagonales[pos[i]])
                {
                    d(j, k);
                    return false;
                }
                break;
            }

            for(i = j1 + 9; dentroTablero[i]; i += 9)
            {
                if(casVacias[i])
                    continue;
                if(i != k && !hayPieza[k1][i] && vaPorDiagonales[pos[i]])
                {
                    d(j, k);
                    return false;
                }
                break;
            }

            for(i = j1 - 11; dentroTablero[i]; i -= 11)
            {
                if(casVacias[i])
                    continue;
                if(i != k && !hayPieza[k1][i] && vaPorDiagonales[pos[i]])
                {
                    d(j, k);
                    return false;
                }
                break;
            }

            for(i = j1 - 9; dentroTablero[i]; i -= 9)
            {
                if(casVacias[i])
                    continue;
                if(i != k && !hayPieza[k1][i] && vaPorDiagonales[pos[i]])
                {
                    d(j, k);
                    return false;
                }
                break;
            }

        }
        if(casAtPorP[turnoCont][j1] > 0)
            if(k1 == 1)
            {
                if(i1 != 1)
                {
                    if(l >= 2 && otroAuxiliar != j1 - 11 && k != j1 - 11 && pos[j1 - 11] == 5)
                    {
                        d(j, k);
                        return false;
                    }
                    if(l <= 7 && otroAuxiliar != j1 - 9 && k != j1 - 9 && pos[j1 - 9] == 5)
                    {
                        d(j, k);
                        return false;
                    }
                }
            } else
            if(i1 != 8)
            {
                if(l >= 2 && otroAuxiliar != j1 + 9 && k != j1 + 9 && pos[j1 + 9] == 15)
                {
                    d(j, k);
                    return false;
                }
                if(l <= 7 && otroAuxiliar != j1 + 11 && k != j1 + 11 && pos[j1 + 11] == 15)
                {
                    d(j, k);
                    return false;
                }
            }
        d(j, k);
        return true;
    }

    private final void d(int j, int k)
    {
        casVacias[otroAuxiliar] = false;
        otroAuxiliar = 10;
        casVacias[k] = comoEstaba;
        casVacias[j] = false;
    }

    public final boolean vacioEntre(int j, int k)
    {
        int l = cualInc[j][k];
        for(int i1 = j + l; i1 != k; i1 += l)
            if(!casVacias[i1])
                return false;

        return true;
    }

    int pos[];
    int c[][];
    int t[][];
    int a[][];
    int d[][];
    int p[][];
    int posRey[] = {
        15, 85
    };
    boolean ped[] = {
        true, true
    };
    boolean pei[] = {
        true, true
    };
    int contador50m;
    boolean casVacias[];
    boolean hayPieza[][];
    boolean seComioAlPaso;
    boolean seComio;
    int quesecomio;
    boolean seEnroco;
    boolean seCorono;
    int peonesEnLinea[][];
    int otroAuxiliar;
    boolean comoEstaba;
    boolean vaPorDiagonales[];
    boolean vaPorLineas[];
    int potenciaDe[];
    int turnoCont;
    int i;
    int ultimaPieza;
    boolean septima[][];
    int cualInc[][];
    int cualIncTorre[][];
    int cualIncAlfil[][];
    boolean atacapor[][];
    int casAtPorP[][];
    final int vDePiezas[] = {
        0, 796, 1250, 833, 2300, 225, 4000
    };
    int material[];
    int potencia[];
    boolean hayEscaqueAP;
    int cualAP;
    int inc;
    boolean atCab[][];
    boolean atRey[][];
    boolean dentroTablero[];
}
