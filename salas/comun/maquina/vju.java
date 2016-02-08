// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 


class vju
{

    public vju()
    {
        quiereCoronar = false;
    }

    public boolean verificarValidez(Juego juego, int i, int j, int k)
    {
        int l = juego.pos[i] % 10;
        int i1 = juego.pos[j] % 10;
        int j1 = i % 10;
        int k1 = i / 10;
        int l1 = j % 10;
        int i2 = j / 10;
        int j2 = Math.abs(i - j);
        int k2 = juego.posRey[k] % 10;
        int l2 = juego.posRey[k] / 10;
        if(juego.casVacias[i] || !juego.hayPieza[k][i] || j2 == 0 || juego.hayPieza[k][j])
            return false;
        if(l == 1)
        {
            if(j2 != 12 && j2 != 21 && j2 != 19 && j2 != 8)
                return false;
            return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
        }
        if(l == 5)
        {
            if(k == 0)
            {
                if(i > j)
                    return false;
                if(j1 == l1)
                {
                    if(i2 - k1 > 2 || juego.pos[i + 10] != 0)
                        return false;
                    if(i2 - k1 == 2 && (k1 != 2 || juego.pos[j] != 0))
                        return false;
                    if(!juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k))
                        return false;
                    if(k1 == 7)
                    {
                        quiereCoronar = true;
                        return true;
                    } else
                    {
                        return true;
                    }
                }
                if(j2 != 9 && j2 != 11)
                    return false;
                if(i1 == 0)
                {
                    if(!juego.hayEscaqueAP)
                        return false;
                    if(j != juego.cualAP + 10)
                        return false;
                }
                if(!juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k))
                    return false;
                if(k1 == 7)
                {
                    quiereCoronar = true;
                    return true;
                } else
                {
                    return true;
                }
            }
            if(i < j)
                return false;
            if(j1 == l1)
            {
                if(k1 - i2 > 2 || juego.pos[i - 10] != 0)
                    return false;
                if(k1 - i2 == 2 && (k1 != 7 || i1 != 0))
                    return false;
                if(!juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k))
                    return false;
                if(k1 == 2)
                {
                    quiereCoronar = true;
                    return true;
                } else
                {
                    return true;
                }
            }
            if(j2 != 9 && j2 != 11)
                return false;
            if(i1 == 0)
            {
                if(!juego.hayEscaqueAP)
                    return false;
                if(j != juego.cualAP - 10)
                    return false;
            }
            if(!juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k))
                return false;
            if(k1 == 2)
            {
                quiereCoronar = true;
                return true;
            } else
            {
                return true;
            }
        }
        if(l == 6)
        {
            if(j == i + 2 && juego.ped[k])
            {
                if(!juego.casVacias[juego.posRey[k] + 1] || !juego.casVacias[juego.posRey[k] + 2])
                    return false;
                return juego.noDejaJaque(i, i + 1, k2 + 1, l2, i + 1, k) && juego.noDejaJaque(i, i + 2, k2 + 2, l2, i + 2, k) && juego.noDejaJaque(i, i, k2, l2, i, k);
            }
            if(j == i - 2 && juego.pei[k])
            {
                if(!juego.casVacias[juego.posRey[k] - 1] || !juego.casVacias[juego.posRey[k] - 2])
                    return false;
                return juego.noDejaJaque(i, i - 1, k2 - 1, l2, i - 1, k) && juego.noDejaJaque(i, i - 2, k2 - 2, l2, i - 2, k) && juego.noDejaJaque(i, i, k2, l2, i, k);
            }
            int i3 = Math.abs(juego.posRey[k] - j);
            if(i3 != 1 && i3 != 10 && i3 != 11 && i3 != 9)
                return false;
            return juego.noDejaJaque(i, j, j % 10, j / 10, j, k);
        }
        if(l == 2 || l == 4)
        {
            if(k1 == i2)
            {
                if(j1 < l1)
                {
                    for(int j3 = i + 1; j3 < j; j3++)
                        if(!juego.casVacias[j3])
                            return false;

                    return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
                }
                for(int k3 = j + 1; k3 < i; k3++)
                    if(!juego.casVacias[k3])
                        return false;

                return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
            }
            if(j1 == l1)
            {
                if(k1 < i2)
                {
                    for(int l3 = i + 10; l3 < j; l3 += 10)
                        if(!juego.casVacias[l3])
                            return false;

                    return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
                }
                for(int i4 = j + 10; i4 < i; i4 += 10)
                    if(!juego.casVacias[i4])
                        return false;

                return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
            }
            if(l == 2)
                return false;
        }
        if(l == 3 || l == 4)
        {
            if(j2 % 11 == 0)
            {
                int j4 = j2 / 11;
                if(i < j)
                {
                    if(j1 > 8 - j4 || k1 > 8 - j4)
                        return false;
                    for(int l4 = i + 11; l4 < j; l4 += 11)
                        if(!juego.casVacias[l4])
                            return false;

                    return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
                }
                if(j1 <= j4 || k1 <= j4)
                    return false;
                for(int i5 = j + 11; i5 < i; i5 += 11)
                    if(!juego.casVacias[i5])
                        return false;

                return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
            }
            if(j2 % 9 == 0)
            {
                int k4 = j2 / 9;
                if(i < j)
                {
                    if(j1 <= k4 || k1 > 8 - k4)
                        return false;
                    for(int j5 = i + 9; j5 < j; j5 += 9)
                        if(!juego.casVacias[j5])
                            return false;

                    return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
                }
                if(j1 > 8 - k4 || k1 <= k4)
                    return false;
                for(int k5 = j + 9; k5 < i; k5 += 9)
                    if(!juego.casVacias[k5])
                        return false;

                return juego.noDejaJaque(i, j, k2, l2, juego.posRey[k], k);
            } else
            {
                return false;
            }
        } else
        {
            return true;
        }
    }

    boolean quiereCoronar;
}
