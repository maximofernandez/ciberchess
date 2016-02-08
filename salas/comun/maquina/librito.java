// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 

import java.util.Hashtable;

class librito
{

    public librito()
    {
        lineas = new Hashtable();
        transposiciones = new Hashtable();
        dentroDelLibro = true;
        llaveActual = "A";
        porcentaje = new int[20];
        coord = new int[20][2];
        id = new String[20];
    }

    public void tdn()
    {
        llaveActual = "A";
        dentroDelLibro = true;
    }

    public int[] respuesta()
    {
        int ai[] = new int[2];
        if(transposiciones.containsKey(llaveActual))
            llaveActual = transposiciones.get(llaveActual).toString();
        if(!lineas.containsKey(llaveActual))
        {
            dentroDelLibro = false;
            return ai;
        }
        opciones = lineas.get(llaveActual).toString();
        int i = opciones.indexOf("/");
        if(i <= 0)
        {
            dentroDelLibro = false;
            return ai;
        }
        int j = 0;
        for(int k = 0; k < i; k += 7)
        {
            porcentaje[j] = Integer.parseInt(opciones.substring(k + 1, k + 3));
            id[j] = opciones.substring(k, k + 1);
            coord[j][0] = convertir(opciones.substring(k + 3, k + 5));
            coord[j][1] = convertir(opciones.substring(k + 5, k + 7));
            j++;
        }

        int l = (int)(Math.random() * 99D);
        int i1 = 0;
        for(int j1 = 0; j1 < j; j1++)
        {
            i1 += porcentaje[j1];
            if(l <= i1)
            {
                llaveActual = llaveActual + id[j1];
                ai[0] = coord[j1][0];
                ai[1] = coord[j1][1];
                return ai;
            }
        }

        return ai;
    }

    void actualizarLlave(int i, int j)
    {
        if(transposiciones.containsKey(llaveActual))
            llaveActual = transposiciones.get(llaveActual).toString();
        if(!lineas.containsKey(llaveActual))
        {
            dentroDelLibro = false;
            return;
        }
        opciones = lineas.get(llaveActual).toString();
        int k = opciones.indexOf("/");
        for(int l = 0; l < k; l += 7)
            if(i == convertir(opciones.substring(l + 3, l + 5)) && j == convertir(opciones.substring(l + 5, l + 7)))
            {
                llaveActual = llaveActual + opciones.substring(l, l + 1);
                return;
            }

        for(int i1 = k + 1; i1 < opciones.length(); i1 += 5)
            if(i == convertir(opciones.substring(i1 + 1, i1 + 3)) && j == convertir(opciones.substring(i1 + 3, i1 + 5)))
            {
                llaveActual = llaveActual + opciones.substring(i1, i1 + 1);
                return;
            }

        dentroDelLibro = false;
    }

    private int convertir(String s)
    {
        String s1 = "0abcdefgh";
        int i = Integer.parseInt(s.substring(1, 2));
        int j = s1.indexOf(s.substring(0, 1));
        return i * 10 + j;
    }

    public void cargarJugadas()
    {
        lineas.put("A", "A70e2e4B30d2d4/Cg1f3Db1c3Ec2c4Ff2f4Gb2b3Hg2g3Ia2a3Jh2h3");
        lineas.put("AA", "A95e7e5C05c7c6/Bc7c5De7e6Ed7d6Fg8f6Gb8c6Hd7d5Ig7g6");
        lineas.put("AAA", "A95g1f3C05b1c3/Bf1c4Df2f4Ed2d4Fc2c4");
        lineas.put("AAAA", "A50b8c6B50g8f6/Cd7d6Dd7d5");
        lineas.put("AAAAA", "A30f1b5B30d2d4E30c2c3C10f1c4/Db1c3");
        lineas.put("AAAAAA", "A60g8f6B40a7a6/Cg8e7Df8d6Ed7d6");
        lineas.put("AAAAAAA", "A99e1g1/Bd2d3");
        lineas.put("AAAAAAAA", "A60f8c5B40f6e4/");
        lineas.put("AAAAAAAAA", "A99c2c3/");
        lineas.put("AAAAAAAAAA", "A99e8g8/");
        lineas.put("AAAAAAAAAAA", "A99d2d4/");
        lineas.put("AAAAAAAAAAAA", "A50c5b6B50c5e7/");
        lineas.put("AAAAAAAAAAAAB", "A99d4e5/Bf1e1/");
        lineas.put("AAAAAAAAAAAABA", "A99f6e4/");
        lineas.put("AAAAAAAAAAAABAA", "A99d1d5/Bf1e1");
        lineas.put("AAAAAAAAAAAABAAA", "A99e4c5/");
        lineas.put("AAAAAAAAAAAABAAB", "A99e4c5/");
        lineas.put("AAAAAAAAAAAAA", "A40c1g5B40f1e1C20d4e5/");
        lineas.put("AAAAAAAAAAAAAA", "A99h7h6/");
        lineas.put("AAAAAAAAAAAAAB", "A80d7d6B20e5d4/");
        lineas.put("AAAAAAAAAAAAAC", "A99f6e4/");
        lineas.put("AAAAAAAAAAAAACA", "A70d1d5B30b1d2/");
        lineas.put("AAAAAAAAAAAAACAA", "A99e4c5/");
        lineas.put("AAAAAAAAAAAAACAB", "A99d7d5/");
        lineas.put("AAAAAAAAB", "A99d2d4/Bf1e1");
        lineas.put("AAAAAAAABA", "A99e4d6/Bf8e7");
        lineas.put("AAAAAAAABAA", "A99b5c6/");
        lineas.put("AAAAAAAABAAA", "A99d7c6/Bb7c6");
        lineas.put("AAAAAAAABAAAA", "A99d4e5/");
        lineas.put("AAAAAAAABAAAAA", "A99d6f5/Bd6e4");
        lineas.put("AAAAAAB", "A99b5a4/Bb5c6");
        lineas.put("AAAAAABA", "A60g8f6B40d7d6/");
        lineas.put("AAAAAABAA", "A50e1g1B50c2c3/");
        lineas.put("AAAAAABAAA", "A50f8e7B50b7b5/");
        lineas.put("AAAAAABAAAB", "A99a4b3/");
        lineas.put("AAAAAABAAABA", "A50f8e7B50f8c5/");
        lineas.put("AAAAAABAAB", "C99f8e7/Ab7b5Bf6e4");
        lineas.put("AAAAAABAABA", "A99a4c2/");
        lineas.put("AAAAAABAABB", "A99e1g1/");
        lineas.put("AAAAAABAB", "A70c2c3B30e1g1/");
        lineas.put("AAAAAABB", "A99d7c6/");
        lineas.put("AAAAAABBA", "/Ad2d4Bb1c3Ce1g1");
        lineas.put("AAAAAABBAC", "A99c8g4/");
        lineas.put("AAAAAABBACA", "/Ah2h3");
        lineas.put("AAAAAABBACAA", "A99h7h5/");
        lineas.put("AAAAAAE", "A70d2d4B30c2c3/");
        lineas.put("AAAAAB", "A99e5d4/");
        lineas.put("AAAAABA", "A99f3d4/Bc2c3Cf1c4");
        lineas.put("AAAAABAA", "A40g8f6B60f8c5/");
        lineas.put("AAAAABAAA", "A70d4c6B30b1c3/");
        lineas.put("AAAAABAAAA", "A99b7c6/");
        lineas.put("AAAAABAAAAA", "A99e4e5/");
        lineas.put("AAAAABAAAAAA", "A99d8e7/");
        lineas.put("AAAAABAAAB", "A99f8b4/");
        lineas.put("AAAAABAAB", "A65d4b3B35c1e3/Cd4f5Dd4f6");
        lineas.put("AAAAABAABA", "A99c5b6/");
        lineas.put("AAAAABAABAA", "A99a2a4/");
        lineas.put("AAAAABAABAAA", "A60d8f6B40a7a5/Ca7a6");
        lineas.put("AAAAABAABAAAC", "A99b1c3/");
        lineas.put("AAAAABAABB", "A99d8f6/");
        lineas.put("AAAAABAABBA", "A99c2c3/");
        lineas.put("AAAAABAABC", "A99d8f6/");
        lineas.put("AAAAABAABD", "A99d8f6/");
        lineas.put("AAAAAC", "A80f8c5C20d7d6/Bg8f6Dc6d4");
        lineas.put("AAAAACA", "A50b1c3B50d2d3/Cc2c3Db2b4Ee1g1");
        lineas.put("AAAAACAA", "A99g8f6/");
        lineas.put("AAAAACAB", "A99g8f6/");
        lineas.put("AAAAACAC", "A99g8f6/");
        lineas.put("AAAAACAD", "A99c5b4/");
        lineas.put("AAAAACAE", "A99g8f6/");
        lineas.put("AAAAACB", "A40d2d3B40f3g5C20d2d4/");
        lineas.put("AAAAACBB", "A99d7d5/");
        lineas.put("AAAAACBBA", "A99e4d5/");
        lineas.put("AAAAACBBAA", "A99c6a5/");
        lineas.put("AAAAACBBAAA", "A99c4b5/");
        lineas.put("AAAAACD", "A99c2c3/");
        lineas.put("AAAAAD", "A99g8f6/Bf8c5Cf8b4");
        lineas.put("AAAAADA", "A60f1b5B30d2d4C10f1c4/");
        lineas.put("AAAAADAA", "A99f8b4/Bf8c5Cd7d6");
        lineas.put("AAAAAE", "B99d7d5/Ag8f6");
        lineas.put("AAAAAEA", "A99d2d4/");
        lineas.put("AAAAAEB", "A99d1a4/");
        lineas.put("AAAAAEBA", "A99d8d6/");
        lineas.put("AAAAB", "A60d2d4B40f3e5/Cb1c3Df1c4");
        lineas.put("AAAABA", "A80f6e4B20e5d4/");
        lineas.put("AAAABAA", "A99f1d3/");
        lineas.put("AAAABAAA", "A99d7d5/");
        lineas.put("AAAABAAAA", "A99f3e5/");
        lineas.put("AAAABAB", "A99e4e5/");
        lineas.put("AAAABABA", "A99f6e4/");
        lineas.put("AAAABABAA", "A99d1d4/");
        lineas.put("AAAABABAAA", "A99d7d5/");
        lineas.put("AAAABABAAAA", "A99e5d6/");
        lineas.put("AAAABB", "A99d7d6/");
        lineas.put("AAAABBA", "A99e5f3/Be5f7");
        lineas.put("AAAABBAA", "A99f6e4/");
        lineas.put("AAAABBAAA", "A80d2d4B20d1e2/Cd2d3");
        lineas.put("AAAABBAAAA", "A99d6d5/");
        lineas.put("AAAABBAAAAA", "A99f1d3/");
        lineas.put("AAAABBAAAAAA", "A60f8d6B40f8e7/");
        lineas.put("AAAABBAAAAAAA", "A99e1g1/");
        lineas.put("AAAABBAAAAAAAA", "A99e8g8/");
        lineas.put("AAAABBAAAAAAB", "A99e1g1/");
        lineas.put("AAAABBAAAAAABA", "A99e8g8/");
        lineas.put("AAAABBAAAB", "A99d8e7/");
        lineas.put("AAAABBAAABA", "A99d2d3/");
        lineas.put("AAAABBAAABAA", "A99e4f6/");
        lineas.put("AAAABBAAABAAA", "A99c1g5/");
        lineas.put("AAAABBAAABAAAA", "A60e7e2B40b8d7/");
        lineas.put("AAAABBAAABAAAAB", "A99b1c3/");
        lineas.put("AAAABBAAABAAAABA", "A90e7e2B10h7h6/");
        lineas.put("AAAABBAAAC", "A99e4f6/");
        lineas.put("AAAABBAAACA", "A99d3d4/");
        lineas.put("AAAABBAAACAA", "A50d6d5B50f8e7/");
        lineas.put("AAAABBAAACAAB", "A99f1d3/");
        lineas.put("AAAABBAAACAABA", "A99c8g4/");
        lineas.put("AAAABBAB", "A99e8f7/");
        lineas.put("AAAABBABA", "/Ad2d4Bf1c4");
        lineas.put("AAAABBABAA", "A25g7g6B25f8e7C25d8e8D25d8e7/");
        lineas.put("AAAABBABAB", "A99c8e6/");
        lineas.put("AAAABC", "A50f8b4B50b8c6/");
        lineas.put("AAAABD", "A99f6e4/");
        lineas.put("AAAAD", "A99e4d5/");
        lineas.put("AAAADA", "/Af8d6");
        lineas.put("AAAADAA", "A60d2d3B20f1c4C20f1b5/");
        lineas.put("AAAB", "A99g8f6/Bb8c6");
        lineas.put("AAABA", "A99b1c3/Bg1f3");
        lineas.put("AAABB", "A99b1c3/Bg1f3");
        lineas.put("AAAC", "A99g8f6/");
        lineas.put("AAAD", "A70e5f4B30f8c5/");
        lineas.put("AAADA", "/Ag1f3Bf1c4");
        lineas.put("AAADAA", "A50d7d5B50f8e7/");
        lineas.put("AAADAAA", "A99e4d5/");
        lineas.put("AAADAAAA", "A99g8f6/");
        lineas.put("AAADAAAAA", "/Af1b5");
        lineas.put("AAADAAAAAA", "A99c7c6");
        lineas.put("AAADAAAAAAA", "/Ad5c6");
        lineas.put("AAADAAAAAAAA", "A99b7c6/");
        lineas.put("AAADAAAAAAAAA", "/Ab5c4");
        lineas.put("AAADAAAAAAAAAA", "A99f6d5/");
        lineas.put("AAADAAB", "/Af1c4");
        lineas.put("AAADAABA", "A99g8f6/");
        lineas.put("AAADAABAA", "/Ae4e5Bd2d3");
        lineas.put("AAADAABAAA", "A99f6g4/");
        lineas.put("AAADAABAAAA", "/Ae1g1");
        lineas.put("AAADAABAAAAA", "A99b8c6/");
        lineas.put("AAADAABAAAAAA", "/Ad2d4");
        lineas.put("AAADAABAAAAAAA", "A99d7d5/");
        lineas.put("AAADAABAAB", "A99d7d5/");
        lineas.put("AAAE", "A99e5d4/");
        lineas.put("AAAEA", "/Ac2c3");
        lineas.put("AAAEAA", "A99d4c3/");
        lineas.put("AAAEAAA", "/Af1c4");
        lineas.put("AAAEAAAA", "A99c3b2/");
        lineas.put("AAAEAAAAA", "/Ac1b2");
        lineas.put("AAAEAAAAAA", "A99d7d5/");
        lineas.put("AAB", "A99g1f3/Bb1c3Cf1c4Dd2d4");
        lineas.put("AABA", "A33e7e6B33d7d6C33b8c6/Dd8c7");
        lineas.put("AABAA", "A99d2d4/");
        lineas.put("AABAAA", "A99c5d4/");
        lineas.put("AABAAAA", "A99f3d4/");
        lineas.put("AABAB", "A10f1b5B30c2c3C60d2d4/");
        lineas.put("AABABA", "A80c8d7C20b8c6/Bb8d7");
        lineas.put("AABABAA", "A99b5d7/");
        lineas.put("AABABAAA", "A99d8d7/");
        lineas.put("AABABAAAA", "A50e1g1B50c2c4/");
        lineas.put("AABABAAAAB", "/Ab8c6Bg8f6Ce7e5Dd7g4");
        lineas.put("AABABAAAABA", "A99e1g1/");
        lineas.put("AABABAAAABB", "A99b1c3/");
        lineas.put("AABABAAAABC", "A99b1c3/");
        lineas.put("AABABAAAABD", "A99e1g1/");
        lineas.put("AABABAAAABDA", "A99g4e4/");
        lineas.put("AABABAAAABDAA", "A99d2d4/");
        lineas.put("AABABAAAABDAAA", "/Ac5d4Bb8d7");
        lineas.put("AABABAAAABDAAAA", "A99f3d4/");
        lineas.put("AABABAAAABDAAAB", "A99b1c3/");
        lineas.put("AABABAB", "A70c2c3B30d2d4/");
        lineas.put("AABABAC", "A99b5c6/");
        lineas.put("AABABACA", "A99b7c6/");
        lineas.put("AABABACAA", "A50c2c3B50e1g1/");
        lineas.put("AABABAAAAA", "A50b8c6B50g8f6/");
        lineas.put("AABABAAAAAA", "A99c2c3/");
        lineas.put("AABABAAAAAB", "A99f1e1/");
        lineas.put("AABABB", "A99g8f6/");
        lineas.put("AABABBA", "A99f1d3/");
        lineas.put("AABABC", "A99c5d4/");
        lineas.put("AABABCA", "A99f3d4/");
        lineas.put("AABABCAA", "A99g8f6/");
        lineas.put("AABABCAAA", "A99b1c3/");
        lineas.put("AABAC", "A60d2d4B40f1b5/");
        lineas.put("AABACA", "A99c5d4/");
        lineas.put("AABACAA", "A99f3d4/");
        lineas.put("AABACAAA", "A60g8f6B40g7g6/");
        lineas.put("AABACAAAA", "A99b1c3/");
        lineas.put("AABACAAAB", "A60b1c3B40c2c3/");
        lineas.put("AABAD", "A99b1c3/");
        lineas.put("AABD", "A99c5d4/");
        lineas.put("AABDA", "A60g1f3B40c2c3/");
        lineas.put("AABDAA", "A99b8c6/");
        lineas.put("AAC", "A40b1c3B60d2d4/");
        lineas.put("AACA", "A99d7d5/");
        lineas.put("AACAA", "A50e4d5B50d2d4/");
        lineas.put("AACB", "A99d7d5/");
        lineas.put("AACBA", "A50e4d5B50b1c3/");
        lineas.put("AACBAA", "A99c6d5/");
        lineas.put("AACBAAA", "A99c2c4/");
        lineas.put("AACBAB", "A99d5e4/");
        lineas.put("AACBABA", "A99c3e4/");
        lineas.put("AACBABAA", "A99c8f5/");
        lineas.put("AACBABAAA", "A99e4g3/");
        lineas.put("AACBABAAAA", "A99f5g6/");
        lineas.put("AAD", "A85d2d4B15d2d3/");
        lineas.put("AADA", "A99d7d5/Bc7c5Cc7c6");
        lineas.put("AADAA", "C30e4d5D70b1d2/Ab1c3Be4e5");
        lineas.put("AADAAA", "A99f8b4/");
        lineas.put("AADAAAA", "A40e4e5B60e4d5/Cg1e2");
        lineas.put("AADAAAAA", "A99c7c5/");
        lineas.put("AADAAAAAA", "A60g1f3B40d1g4/");
        lineas.put("AADAAC", "A99e6d5/");
        lineas.put("AADAACA", "A99f1d3/");
        lineas.put("AADAACAA", "A99f8d6/Bb8c6Cg8f6Dc7c5");
        lineas.put("AADAACAAA", "A50c2c3B50d1f3/");
        lineas.put("AADAACAAB", "A99c2c3/");
        lineas.put("AADAACAAC", "A99g1f3/");
        lineas.put("AADAAD", "A70c7c5/Bg8f6Cb8d7");
        lineas.put("AADAADA", "A99e4d5/");
        lineas.put("AADAADAA", "A99e6d5/Bd8d5");
        lineas.put("AADAADAAA", "A50g1f3B50f1b5/");
        lineas.put("AADAADAAAA", "A99b8c6/Bg8f6Ca7a6/");
        lineas.put("AADAADAAAAA", "A99f1b5/");
        lineas.put("AADAADAAAAAA", "A99f8d6/Bd8e7/");
        lineas.put("AADAADAAAAAAA", "A80d4c5/");
        lineas.put("AADAADAAB", "A99g1f3/");
        lineas.put("AADAADC", "A99g1f3/");
        lineas.put("AADAB", "A99g1f3/");
        lineas.put("AADAC", "A99g1f3/");
        lineas.put("AAE", "A99d2d4/");
        lineas.put("AAF", "A99e4e5/");
        lineas.put("AAFA", "A99f6d5/");
        lineas.put("AAFAA", "A99d2d4/");
        lineas.put("AAFAAA", "A99d7d6/Bb8c6");
        lineas.put("AAFAAAA", "A70g1f3B30c2c4/");
        lineas.put("AAFAAAAA", "A99c8g4/");
        lineas.put("AAFAAAAAA", "A99f1e2/");
        lineas.put("AAFAAAAAAA", "/Ac7c6Be7e6Cb8c6");
        lineas.put("AAFAAAAAAAA", "A99c2c4/");
        lineas.put("AAFAAAAAAAB", "A99e1g1/");
        lineas.put("AAFAAAAAAAC", "A50e1g1B50c2c4/");
        lineas.put("AAFAAAB", "A99c2c4/");
        lineas.put("AAG", "A99d2d4/");
        lineas.put("AAGA", "/Ad7d5");
        lineas.put("AAGAA", "A50e4e5B50e4d5/");
        lineas.put("AAH", "A99e4d5/Bd2d4");
        lineas.put("AAHA", "/Ag8f6Bd8d5");
        lineas.put("AAHAA", "A99d2d4/Bc2c4");
        lineas.put("AAHAAA", "/Ac8g4Bf6d5");
        lineas.put("AAHAAAA", "A70f1e2B30f2f3/");
        lineas.put("AAHAAAB", "A80c2c4B20g1f3/");
        lineas.put("AAHAB", "A99b1c3/");
        lineas.put("AAHABA", "/Ad5d8Bd5a5Cd5e6");
        lineas.put("AAHABAA", "A99d2d4/");
        lineas.put("AAHABAB", "A99d2d4/");
        lineas.put("AAHABAC", "A80f1e2B20d1e2/");
        lineas.put("AAI", "A99d2d4/");
        lineas.put("AB", "A60g8f6B30d7d5D10f7f5/Ce7e6");
        lineas.put("ABA", "A50c2c4B30g1f3C20c1f4/Df2f3Ee2e3");
        lineas.put("ABAA", "A99e7e6/Bg7g6");
        lineas.put("ABAAA", "A40b1c3B40g1f3C20g2g3/De2e3");
        lineas.put("ABAAAA", "A50f8b4B50d7d5/");
        lineas.put("ABAAAAA", "A50c1g5B50g1f3/Cd1c2Da2a3Ee2e3");
        lineas.put("ABAAAAAB", "A99c7c5/");
        lineas.put("ABAAAAAC", "A99c7c5/Be8g8Cd7d5Db8c6");
        lineas.put("ABAAAAACA", "A90d4c5B10e2e3/");
        lineas.put("ABAAAAACAA", "A99e8g8/");
        lineas.put("ABAAAAACAAA", "A40a2a3B30g1f3C30c1g5/");
        lineas.put("ABAAAAACAAAA", "A99b4c5/");
        lineas.put("ABAAAAACAAAAA", "A60g1f3Bb2b4/");
        lineas.put("ABAAAAACAAAAAB", "A99c5e7/");
        lineas.put("ABAAAAAE", "A60e8g8B20c7c5C20b7b6/");
        lineas.put("ABAAAB", "A99b7b6/Bf8e7Cf8b4");
        lineas.put("ABAAABA", "A99b1c3/Bg2g3");
        lineas.put("ABAAABAA", "A99c8b7/");
        lineas.put("ABAAABC", "A99c1d2/");
        lineas.put("ABAAAD", "A60b7b6B25c7c5Cd7d5/");
        lineas.put("ABAAB", "A99b1c3/");
        lineas.put("ABAABA", "A99f8g7/Bd7d5");
        lineas.put("ABAABAA", "A99g1f3/");
        lineas.put("ABAABAB", "A99c4d5/");
        lineas.put("ABAABABA", "A99f6d5/");
        lineas.put("ABAABABAA", "A99e2e4/");
        lineas.put("ABAB", "A99e7e6/");
        lineas.put("ABABA", "A99c2c4/Be2e3Cc1g5Dg2g3Ec1f4");
        lineas.put("ABABAA", "A99b7b6/");
        lineas.put("ABABAAA", "A50g2g3B25b1c3C25e2e3/");
        lineas.put("ABABAAAA", "A60c8b7B20f8e7C20f8b4/");
        lineas.put("ABABAB", "A99b7b6/");
        lineas.put("ABABABA", "A50c2c4B50f1d3/");
        lineas.put("ABABABAA", "A99c8b7/");
        lineas.put("ABABABAB", "A99c8b7/");
        lineas.put("ABABAC", "A99c7c5/");
        lineas.put("ABAD", "A99d7d5/");
        lineas.put("ABADA", "/Ae2e4");
        lineas.put("ABADAA", "A99d5e4/");
        lineas.put("ABADAAA", "/Ab1c3");
        lineas.put("ABADAAAA", "A99e4f3/");
        lineas.put("ABADAAAAA", "/Ad1f3Bg1f3");
        lineas.put("ABADAAAAAA", "A99d8d4/");
        lineas.put("ABADAAAAAAA", "/Ac1e3");
        lineas.put("ABADAAAAAAAA", "A99d4g4/");
        lineas.put("ABADAAAAAAAAA", "/Af3f2");
        lineas.put("ABADAAAAAAAAAA", "A99c7c6/");
        lineas.put("ABADAAAAAB", "A99c8g4/");
        lineas.put("ABAE", "A99e7e6/");
        lineas.put("ABAEA", "/Af1d3Bc2c4Cg1f3Db1d2");
        lineas.put("ABAEAA", "A30d7d5B30c7c5C40b7b6/");
        lineas.put("ABAEAAC", "/Ac2c4Bg1f3Cf2f4");
        lineas.put("ABAEAACC", "A99c8b7/");
        lineas.put("ABAEAACCA", "A99g1f3/");
        lineas.put("ABAEAACCAA", "A80c7c5B20f8e7/");
        lineas.put("ABB", "A80g1f3C20c1f4/De2e3Ec2c4Fe2e4Gb1c3Bb1d2");
        lineas.put("ABBA", "A99g8f6/Bc7c5");
        lineas.put("ABBAA", "A50e2e3B30c2c4C20c1f4/");
        lineas.put("ABBAAB", "A70e7e6B30d5c4/");
        lineas.put("ABBAABB", "A99e2e3/");
        lineas.put("ABBAABBA", "A99e7e6/");
        lineas.put("ABBAB", "A99d4c5/");
        lineas.put("ABBABA", "A99e7e6/Bb8c6Cb8a6/");
        lineas.put("ABBABAA", "A99c2c4/");
        lineas.put("ABBABAB", "A99c2c4/");
        lineas.put("ABBB", "A99g8f6/");
        lineas.put("ABBC", "A50g8f6B30c8f5C20c7c5/");
        lineas.put("ABBCA", "A60g1f3B40e2e3/");
        lineas.put("ABBCB", "A99g1f3/");
        lineas.put("ABBCC", "A65e2e3B35d4c5/");
        lineas.put("ABBCCB", "A99b8c6/");
        lineas.put("ABBE", "A99e7e6/Bc7c6Cd5c4");
        lineas.put("ABBEA", "A50b1c3B50g1f3/Ce2e3");
        lineas.put("ABBEAA", "A99g8f6/");
        lineas.put("ABBEAAA", "A40c1g5B40g1f3D20c4d5/Ce2e3");
        lineas.put("ABBEAAAA", "A99f8e7/");
        lineas.put("ABBEAAAAA", "A99e2e3/Bg1f3");
        lineas.put("ABBEAAAAAA", "A99b8d7/");
        lineas.put("ABBEAAAAAAA", "A99g1f3/");
        lineas.put("ABBEAAAAAB", "A99b8d7/");
        lineas.put("ABBEAAAD", "A99e6d5/");
        lineas.put("ABBEAAADA", "A99c1g5/");
        lineas.put("ABBEAAADAA", "A99f8e7/");
        lineas.put("ABBEAAADAAA", "A60e2e3B40g1f3/");
        lineas.put("ABBEAAADAAAA", "A99e8g8/");
        lineas.put("ABBEAAADAAAB", "A99e8g8/");
        lineas.put("ABBEAAAC", "A65b8d7C20b7b6D15c7c5/");
        lineas.put("ABBEAB", "A90g8f6B10c7c5/");
        lineas.put("ABBEAC", "A90g8f6B10c7c5/");
        lineas.put("ABBEC", "A99g1f3/");
        lineas.put("ABBECA", "/Ag8f6Be7e6");
        lineas.put("ABBF", "A99d5e4/");
        lineas.put("ABBFA", "/Ab1c3");
        lineas.put("ABBFAA", "A99g8f6/");
        lineas.put("ABBFAAA", "/Af2f3");
        lineas.put("ABBG", "A99g8f6/");
        lineas.put("ABBGA", "/Ac1g5Be2e4");
        lineas.put("ABBGAB", "A99d5e4/");
        lineas.put("ABC", "A99c2c4/");
        lineas.put("ABCA", "/Ag8f6Bb8c6Cd7d5Df7f5Ef8b4Fb7b6");
        lineas.put("ABD", "A70c2c4B30g1f3/Cg2g3Dc1g5Eb1c3Fe2e4");
        lineas.put("ABDA", "A80g8f6B20e7e6/");
        lineas.put("ABDB", "A80g8f6B20e7e6/");
        lineas.put("ABDC", "A99g8f6/");
        lineas.put("ABDD", "A99h7h6/");
        lineas.put("ABDE", "A99g8f6/");
        lineas.put("ABDF", "A99f5e4/");
        lineas.put("AC", "A99d7d5/");
        lineas.put("ACA", "C80d2d4A20c2c4/Bg2g3");
        lineas.put("ACAA", "A40d5d4B60e7e6/");
        lineas.put("ACAAA", "/Ae2e3");
        lineas.put("ACAAAA", "A99c7c5/");
        lineas.put("ACAAB", "/Ad2d4");
        lineas.put("ACAB", "A40g8f6B60c7c5/");
        lineas.put("ACABA", "/Af1g2Bc2c4");
        lineas.put("ACABAA", "A33c8f5B33c7c5C33c7c6/");
        lineas.put("ACABB", "/Af1g2Bc2c4Cd2d4");
        lineas.put("ACABBA", "A40b8c6B40g8f6C20c8f5/");
        lineas.put("ACABBB", "A60e7e6B40g8f6/");
        lineas.put("ACABBC", "A99c5d4/");
        lineas.put("AD", "A99d7d5/");
        lineas.put("AE", "A99e7e5/");
        lineas.put("AEA", "/Ab1c3Bd2d4Cg2g3De2e4");
        lineas.put("AEAA", "A99g8f6/");
        lineas.put("AEAAA", "/Ag2g3");
        lineas.put("AEAAAA", "A99c7c6/");
        lineas.put("AEAB", "A99e5d4/");
        lineas.put("AEAC", "A99d7d6/");
        lineas.put("AEACA", "/Af1g2");
        lineas.put("AEACAA", "A99f7f5/");
        lineas.put("AEAD", "A99g8f6/");
        lineas.put("AF", "A99b7b6/");
        lineas.put("AFA", "/Ag1f3Be2e3");
        lineas.put("AFAA", "A99c8b7/");
        lineas.put("AG", "A99b7b6/");
        lineas.put("AGA", "A99c1b2/");
        lineas.put("AGAA", "A99c8b7/");
        lineas.put("AGAAA", "A99e2e3/Bd2d4");
        lineas.put("AGAAAA", "A99e7e6/");
        lineas.put("AGAAAB", "A99e7e6/");
        lineas.put("AGAAABA", "A99e2e3/");
        lineas.put("AGAAABAA", "A99g8f6/");
        lineas.put("AI", "A50e7e5B50d7d5/");
        lineas.put("AJ", "A50e7e5B50d7d5/");
        llenarTransposiciones();
    }

    private void llenarTransposiciones()
    {
        transposiciones.put("AAABAB", "AAAABD");
        transposiciones.put("AAAABCB", "AAAAADA");
        transposiciones.put("AACAAB", "AACBAB");
        transposiciones.put("ABAAAAB", "ABBEAAA");
        transposiciones.put("ABBFAAAA", "ABADAAAA");
        transposiciones.put("ACAC", "ABBA");
        transposiciones.put("ACAABA", "ABBEAB");
        transposiciones.put("ABBGABA", "ABBFAAA");
        transposiciones.put("ABCAA", "ABAAA");
        transposiciones.put("ABCAC", "ABBEA");
        transposiciones.put("ABCAD", "ABDAB");
        transposiciones.put("AADABA", "AABAAA");
        transposiciones.put("AAAF", "AEAD");
    }

    Hashtable lineas;
    Hashtable transposiciones;
    boolean dentroDelLibro;
    String opciones;
    String llaveActual;
    int porcentaje[];
    int coord[][];
    String id[];
}
