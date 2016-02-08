import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class BROCOyCASANOVA_Menu_Cubo extends Applet
    implements Runnable, ImageObserver
{

    public int b()
    {
        char c1 = '\0';
        if(P >= 1)
            c1 = '\u6E35';
        if(P >= 3)
            c1 = '\u36B0';
        if(P >= 5)
            c1 = '\u2454';
        if(P >= 7)
            c1 = '\u1AF4';
        if(P >= 9)
            c1 = '\u154A';
        if(P >= 10)
            c1 = '\u1531';
        if(P >= 12)
            c1 = '\u1180';
        if(P >= 14)
            c1 = '\u0EDD';
        if(P >= 16)
            c1 = '\u0CE4';
        if(P >= 18)
            c1 = '\u0B59';
        if(P >= 20)
            c1 = '\u0A1E';
        if(P >= 22)
            c1 = '\u091A';
        if(P >= 24)
            c1 = '\u083E';
        if(P >= 26)
            c1 = '\u078A';
        if(P >= 28)
            c1 = '\u06EC';
        if(P >= 30)
            c1 = '\u0667';
        if(P >= 32)
            c1 = '\u05F1';
        if(P >= 34)
            c1 = '\u0588';
        if(P >= 36)
            c1 = '\u052C';
        if(P >= 38)
            c1 = '\u04DA';
        if(P >= 40)
            c1 = '\u0491';
        if(P >= 42)
            c1 = '\u044D';
        if(P >= 44)
            c1 = '\u0410';
        if(P >= 46)
            c1 = '\u03D9';
        if(P >= 48)
            c1 = '\u03A6';
        if(P >= 50)
            c1 = '\u0377';
        if(P >= 52)
            c1 = '\u034B';
        if(P >= 54)
            c1 = '\u0323';
        if(P >= 56)
            c1 = '\u02FE';
        if(P >= 58)
            c1 = '\u02DB';
        if(P >= 60)
            c1 = '\u02BB';
        if(P >= 62)
            c1 = '\u029C';
        if(P >= 64)
            c1 = '\u0280';
        if(P >= 66)
            c1 = '\u0265';
        if(P >= 68)
            c1 = '\u024C';
        if(P >= 70)
            c1 = '\u0234';
        if(P >= 72)
            c1 = '\u021E';
        if(P >= 74)
            c1 = '\u0209';
        if(P >= 76)
            c1 = '\u01F5';
        if(P >= 78)
            c1 = '\u01E2';
        if(P >= 80)
            c1 = '\u01D0';
        if(P >= 82)
            c1 = '\u01BF';
        if(P >= 84)
            c1 = '\u01AF';
        if(P >= 86)
            c1 = '\u019F';
        if(P >= 88)
            c1 = '\u0191';
        if(P >= 90)
            c1 = '\u0183';
        if(P >= 92)
            c1 = '\u0175';
        if(P >= 94)
            c1 = '\u0168';
        if(P >= 96)
            c1 = '\u015C';
        if(P >= 98)
            c1 = '\u0150';
        if(P >= 100)
            c1 = '\u0145';
        if(P >= 102)
            c1 = '\u013A';
        if(P >= 104)
            c1 = '\u012F';
        if(P >= 106)
            c1 = '\u0125';
        if(P >= 108)
            c1 = '\u011B';
        if(P >= 110)
            c1 = '\u0112';
        if(P >= 112)
            c1 = '\u0109';
        if(P >= 114)
            c1 = '\u0100';
        if(P >= 116)
            c1 = '\370';
        if(P >= 118)
            c1 = '\357';
        if(P >= 120)
            c1 = '\350';
        if(P >= 122)
            c1 = '\340';
        if(P >= 124)
            c1 = '\331';
        if(P >= 126)
            c1 = '\322';
        if(P >= 128)
            c1 = '\313';
        if(P >= 130)
            c1 = '\305';
        if(P >= 132)
            c1 = '\276';
        if(P >= 134)
            c1 = '\270';
        if(P >= 136)
            c1 = '\262';
        if(P >= 138)
            c1 = '\254';
        if(P >= 140)
            c1 = '\246';
        if(P >= 142)
            c1 = '\241';
        if(P >= 144)
            c1 = '\234';
        if(P >= 146)
            c1 = '\226';
        if(P >= 148)
            c1 = '\221';
        if(P >= 150)
            c1 = '\215';
        if(P >= 152)
            c1 = '\210';
        if(P >= 154)
            c1 = '\203';
        if(P >= 156)
            c1 = '\177';
        if(P >= 158)
            c1 = 'z';
        if(P >= 160)
            c1 = 'v';
        if(P >= 162)
            c1 = 'r';
        if(P >= 164)
            c1 = 'n';
        if(P >= 166)
            c1 = 'j';
        if(P >= 168)
            c1 = 'f';
        if(P >= 170)
            c1 = 'b';
        if(P >= 172)
            c1 = '^';
        if(P >= 174)
            c1 = '[';
        if(P >= 176)
            c1 = 'W';
        if(P >= 178)
            c1 = 'T';
        if(P >= 180)
            c1 = 'P';
        if(P >= 182)
            c1 = 'M';
        if(P >= 184)
            c1 = 'J';
        if(P >= 186)
            c1 = 'G';
        if(P >= 188)
            c1 = 'D';
        if(P >= 190)
            c1 = 'A';
        if(P >= 192)
            c1 = '>';
        if(P >= 194)
            c1 = ';';
        if(P >= 196)
            c1 = '8';
        if(P >= 198)
            c1 = '5';
        if(P >= 200)
            c1 = '3';
        if(P >= 202)
            c1 = '0';
        if(P >= 204)
            c1 = '-';
        if(P >= 206)
            c1 = '+';
        if(P >= 208)
            c1 = '(';
        if(P >= 210)
            c1 = '&';
        if(P >= 212)
            c1 = '$';
        if(P >= 214)
            c1 = '!';
        if(P >= 216)
            c1 = '\037';
        if(P >= 218)
            c1 = '\035';
        if(P >= 220)
            c1 = '\032';
        if(P >= 222)
            c1 = '\030';
        if(P >= 224)
            c1 = '\026';
        if(P >= 226)
            c1 = '\024';
        if(P >= 228)
            c1 = '\022';
        if(P >= 230)
            c1 = '\020';
        if(P >= 232)
            c1 = '\016';
        if(P >= 234)
            c1 = '\f';
        if(P >= 236)
            c1 = '\n';
        if(P >= 238)
            c1 = '\t';
        if(P >= 240)
            c1 = '\007';
        if(P >= 242)
            c1 = '\005';
        if(P >= 244)
            c1 = '\003';
        if(P >= 246)
            c1 = '\001';
        if(P >= 248)
            c1 = '\0';
        if(P >= 250)
            c1 = '\uFFFE';
        if(P >= 252)
            c1 = '\uFFFD';
        if(P >= 254)
            c1 = '\uFFFB';
        if(P >= 256)
            c1 = '\uFFFA';
        if(P >= 258)
            c1 = '\uFFF8';
        if(P >= 260)
            c1 = '\uFFF7';
        if(P >= 262)
            c1 = '\uFFF5';
        if(P >= 264)
            c1 = '\uFFF4';
        if(P >= 266)
            c1 = '\uFFF2';
        if(P >= 268)
            c1 = '\uFFF1';
        if(P >= 270)
            c1 = '\uFFEF';
        if(P >= 272)
            c1 = '\uFFEE';
        if(P >= 274)
            c1 = '\uFFED';
        if(P >= 276)
            c1 = '\uFFEB';
        if(P >= 278)
            c1 = '\uFFEA';
        if(P >= 280)
            c1 = '\uFFE9';
        if(P >= 282)
            c1 = '\uFFE8';
        if(P >= 284)
            c1 = '\uFFE6';
        if(P >= 286)
            c1 = '\uFFE5';
        if(P >= 288)
            c1 = '\uFFE4';
        if(P >= 290)
            c1 = '\uFFE3';
        if(P >= 292)
            c1 = '\uFFE2';
        if(P >= 294)
            c1 = '\uFFE1';
        if(P >= 296)
            c1 = '\uFFDF';
        if(P >= 298)
            c1 = '\uFFDE';
        if(P >= 300)
            c1 = '\uFFDD';
        return c1;
    }

    private final void c()
    {
        int k2 = 0;
        int l2 = 0;
        F = 0;
        for(int j2 = 0; j2 < 24; j2 += 4)
        {
            int i1 = u[k2++];
            int j1 = u[k2++];
            int k1 = u[k2++];
            int l1 = u[k2++];
            int i2 = (ca[j1] - ca[i1]) * (ca[k1 + 1] - ca[i1 + 1]) - (ca[k1] - ca[i1]) * (ca[j1 + 1] - ca[i1 + 1]);
            if(i2 <= 0)
            {
                F++;
                dk[l2++] = j2;
            }
        }

    }

    public synchronized boolean d()
    {
        prepareImage(bT, this);
        if(bk)
        {
            for(int i1 = 0; i1 < 3; i1++)
            {
                notifyAll();
                Thread.yield();
                try
                {
                    Thread.sleep(100L);
                }
                catch(InterruptedException _ex) { }
            }

            return bO;
        } else
        {
            return false;
        }
    }

    public BROCOyCASANOVA_Menu_Cubo()
    {
        bk = false;
        I = false;
        H = false;
        L = false;
        bO = false;
        c = false;
        bI = false;
        s = false;
        bS = false;
    }

    private final void e()
    {
        if(bJ)
        {
            if(System.currentTimeMillis() - p > 10000L)
                bJ = false;
        } else
        {
            e = ch;
            f = ci;
            g = cj;
        }
        m();
        c();
        o();
    }

    public void destroy()
    {
        if(bT != null)
            bT.flush();
        bT = null;
        if(bN != null)
            bN.flush();
        bN = null;
        if(bM != null)
            bM.dispose();
        bM = null;
        System.gc();
    }

    private final void f()
    {
        bK = true;
        if(m)
            try
            {
                System.arraycopy(n, 0, r, 0, cW);
            }
            catch(ArrayIndexOutOfBoundsException _ex)
            {
                stop();
            }
            catch(ArrayStoreException _ex)
            {
                stop();
            }
        e();
        int i1 = cq;
        if(!bS)
            cq = (r[bQ / cg + (bR / cg) * dd] & 0xff000000) >> 24; 
        if(cq != i1)
            if(cq == -1)
            {
                b.setCursor(0);
                showStatus("");
            } else
            {
                b.setCursor(12);
                showStatus(cB[cq - 1]);
            }
        bK = false;
    }

    Image a(String s1)
    {
        try
        {
            return b(s1);
        }
        catch(NoSuchMethodError _ex)
        {
            return b(s1);
        }
    }

    synchronized Image b(String s1)
    {
        URL url = null;
        Image image = null;
        try
        {
            url = new URL(getDocumentBase(), s1);
        }
        catch(MalformedURLException _ex) { }
        try
        {
            try
            {
                InputStream inputstream = getClass().getResourceAsStream(url.toString());
                if(inputstream != null)
                {
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1024);
                    byte abyte0[] = new byte[512];
                    boolean flag = false;
                    byte abyte1[] = null;
                    try
                    {
                        while(!flag)
                        {
                            int k1 = inputstream.read(abyte0, 0, 512);
                            if(k1 != -1)
                            {
                                bytearrayoutputstream.write(abyte0, 0, k1);
                                bytearrayoutputstream.flush();
                            } else
                            {
                                flag = true;
                            }
                        }
                        abyte1 = bytearrayoutputstream.toByteArray();
                        bytearrayoutputstream.close();
                        inputstream.close();
                    }
                    catch(IOException _ex)
                    {
                        abyte1 = null;
                    }
                    System.gc();
                    if(abyte1 != null)
                    {
                        image = getToolkit().createImage(abyte1);
                        prepareImage(image, this);
                    }
                }
            }
            catch(NoSuchMethodError _ex) { }
        }
        catch(SecurityException _ex) { }
        if(image == null)
        {
            for(int i1 = 0; i1 < 5;)
                try
                {
                    if(i1 % 2 == 0)
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    else
                        image = getImage(url);
                    i1++;
                    MediaTracker mediatracker = new MediaTracker(this);
                    notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try
                    {
                        mediatracker.addImage(image, 0);
                        mediatracker.waitForID(0);
                    }
                    catch(InterruptedException _ex)
                    {
                        image = null;
                    }
                    if(mediatracker.isErrorID(0))
                        image = null;
                    else
                        i1 = 6;
                }
                catch(NullPointerException _ex)
                {
                    System.gc();
                }

        }
        if(image == null)
        {
            for(int j1 = 0; j1 < 25; j1++)
            {
                showStatus("Imagen" + s1 + "no encontrada.");
                try
                {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch(InterruptedException _ex) { }
            }

        } else
        {
            while(image.getWidth(this) < 0)
            {
                notifyAll();
                Thread.currentThread();
                Thread.yield();
                try
                {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch(InterruptedException _ex) { }
            }
        }
        return image;
    }

    void g()
    {
        Insets insets = N.insets();
        N.setResizable(true);
        N.resize(J + insets.left + insets.right, G + insets.bottom + insets.top);
        N.repaint();
        N.validate();
        N.setResizable(false);
        N.move(Integer.valueOf(getParameter("VentanaFlotanteX")).intValue(), Integer.valueOf(getParameter("VentanaFlotanteY")).intValue());
    }

    public void a(String s1, int i1)
    {
        try
        {
            b(s1, i1);
            return;
        }
        catch(NoSuchMethodError _ex)
        {
            b(s1, i1);
        }
    }

    public void b(String s1, int i1)
    {
        try
        {
            URL url = new URL(getDocumentBase(), s1);
            try
            {
                DataInputStream datainputstream = new DataInputStream(url.openStream());
                if(datainputstream != null)
                {
                    ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(1024);
                    byte abyte0[] = new byte[512];
                    boolean flag = false;
                    int k1 = 0;
                    boolean flag1 = false;
                    try
                    {
                        while(!flag1)
                        {
                            int j1 = datainputstream.read(abyte0, 0, 512);
                            if(j1 == -1)
                            {
                                flag1 = true;
                            } else
                            {
                                bytearrayoutputstream.write(abyte0, 0, j1);
                                bytearrayoutputstream.flush();
                                k1 += j1;
                            }
                        }
                        byte abyte1[] = bytearrayoutputstream.toByteArray();
                        bytearrayoutputstream.close();
                        bytearrayoutputstream = null;
                        datainputstream.close();
                        System.gc();
                        if(i1 == 0)
                        {
                            for(int l1 = 0; l1 < k1; l1++)
                            {
                                byte byte0 = abyte1[l1];
                                if(byte0 == 13 || byte0 == 10)
                                    abyte1[l1] = 32;
                            }

                            try
                            {
                                cl = new String(abyte1);
                                return;
                            }
                            catch(NoSuchMethodError _ex)
                            {
                                cl = new String(abyte1, 0);
                            }
                            return;
                        }
                        int i2 = 1;
                        for(int j2 = 0; j2 < k1; j2++)
                            if(abyte1[j2] == 10)
                                i2++;

                        bl = new String[i2 - 1];
                        int ai[] = new int[i2 + 1];
                        int ai1[] = new int[i2 + 1];
                        ai[0] = 0;
                        int k2 = 0;
                        int l2 = 0;
                        for(int i3 = 0; i3 < k1; i3++)
                        {
                            byte byte1 = abyte1[i3];
                            if(byte1 == 10)
                            {
                                ai[k2 + 1] = i3 + 1;
                                if(l2 == 13)
                                    ai1[k2] = i3 - ai[k2] - 1;
                                else
                                    ai1[k2] = i3 - ai[k2];
                                k2++;
                            }
                            l2 = byte1;
                        }

                        ai1[k2] = k1 - ai[k2 + 1] - 1;
                        try
                        {
                            for(int j3 = 0; j3 < i2 - 1; j3++)
                                try
                                {
                                    bl[j3] = new String(abyte1, ai[j3], ai1[j3]);
                                }
                                catch(NoSuchMethodError _ex)
                                {
                                    bl[j3] = new String(abyte1, 0, ai[j3], ai1[j3]);
                                }

                            return;
                        }
                        catch(StringIndexOutOfBoundsException _ex)
                        {
                            bl = null;
                        }
                        return;
                    }
                    catch(IOException _ex)
                    {
                        return;
                    }
                }
            }
            catch(IOException _ex)
            {
                return;
            }
        }
        catch(MalformedURLException _ex) { }
    }

    public void a(Graphics g1)
    {
        g1.setFont(K);
        if(a == 0)
        {
            di = dj;
        } else
        {
            bj += cX;
            di = dj - (int)Math.abs((double)a * Math.sin(((double)bj / 90D) * 3.1415926535897931D));
        }
        if(cs != 0)
        {
            for(int i1 = 0; i1 < cZ; i1++)
            {
                int k1 = cx[cr + i1];
                g1.copyArea(i1, k1, 1, cu, 0, cd - k1);
            }

            if(cG)
            {
                g1.setColor(cp);
                g1.drawString(cl, dg + 1, cd + cm + 1);
            }
            g1.setColor(cC);
            g1.drawString(cl, dg, cd + cm);
            for(int j1 = 0; j1 < cZ; j1++)
                g1.copyArea(j1, cd, 1, cv, 0, cy[cr + j1]);

            cr -= cw;
            if(cr < 0)
                cr += 360;
        } else
        {
            if(cG)
            {
                g1.setColor(cp);
                g1.drawString(cl, dg + 1, di + 1);
            }
            g1.setColor(cC);
            g1.drawString(cl, dg, di);
        }
        dg -= cY;
        if(dg < -cn)
            dg = cZ;
    }

    public boolean imageUpdate(Image image, int i1, int j1, int k1, int l1, int i2)
    {
        if(image == bT)
        {
            if(i1 == 16)
                bO = true;
            return true;
        } else
        {
            return true;
        }
    }

    public void init()
    {
        L = false;
        setLayout(null);
        addNotify();
        cU = getToolkit();
        String s1 = null;
        s1 = getParameter("Creditos");
	  if(!s1.equalsIgnoreCase("BROCOyCASANOVA")) while(true);
        String s5 = getParameter("MostrarEnOtroMarco");
        if(s5.equalsIgnoreCase("SI"))
            bI = true;
        Container container;
        for(container = getParent(); !(container instanceof Frame); container = ((Component)container).getParent());
        b = (Frame)container;
        b.setCursor(3);
        String s6 = getParameter("SobreImagen");
        if(s6 != null && !s6.equalsIgnoreCase("NO"))
        {
            bT = a(s6);
            if(bT != null)
            {
                String s7 = getParameter("SobreImagenX");
                if(s7 == null)
                    s7 = "0";
                bU = Integer.valueOf(s7).intValue();
                String s8 = getParameter("SobreImagenY");
                if(s8 == null)
                    s8 = "0";
                bV = Integer.valueOf(s8).intValue();
            }
        }
        bz = getParameter("MinSYNC");
        if(bz == null)
            bz = "10";
        bo = Integer.valueOf(bz).intValue();
        db = new String[7];
        db[1] = new String(getParameter("Enlace1"));
        db[2] = new String(getParameter("Enlace2"));
        db[3] = new String(getParameter("Enlace3"));
        db[4] = new String(getParameter("Enlace4"));
        db[5] = new String(getParameter("Enlace5"));
        db[6] = new String(getParameter("Enlace6"));
        bc = getParameter("Imagen1");
        bd = getParameter("Imagen2");
        be = getParameter("Imagen3");
        bf = getParameter("Imagen4");
        bg = getParameter("Imagen5");
        bh = getParameter("Imagen6");
        cB = new String[6];
        for(int k3 = 0; k3 < 6; k3++)
            cB[k3] = getParameter("MensajeEstado" + String.valueOf(k3 + 1));

        bq = getParameter("Residuo");
        if(bq == null)
            bq = "1";
        bv = getParameter("RotacionX");
        if(bv == null)
            bv = "4";
        bw = getParameter("RotacionY");
        if(bw == null)
            bw = "6";
        bx = getParameter("RotacionZ");
        if(bx == null)
            bx = "8";
        by = getParameter("ColorFondoRojo");
        if(by == null)
            by = "64";
        bA = getParameter("ColorFondoVerde");
        if(bA == null)
            bA = "96";
        bB = getParameter("ColorFondoAzul");
        if(bB == null)
            bB = "160";
        bC = getParameter("BorrarFondo");
        if(bC.equalsIgnoreCase("SI"))
            m = true;
        else
            m = false;
        br = getParameter("RetardoMemoria");
        bs = getParameter("Prioridad");
        bu = getParameter("PrioridadMinima");
        bn = Integer.valueOf(br).intValue();
        bY = Integer.valueOf(bs).intValue();
        bZ = Integer.valueOf(bu).intValue();
        if(bn < 0)
            bn = 0;
        if(bY > 10)
            bY = 10;
        else
        if(bY < 1)
            bY = 1;
        if(bZ > 10)
            bZ = 10;
        else
        if(bZ < 1)
            bZ = 1;
        cg = Integer.valueOf(bq).intValue();
        ch = Float.valueOf(bv).floatValue() / 100F;
        ci = Float.valueOf(bw).floatValue() / 100F;
        cj = Float.valueOf(bx).floatValue() / 100F;
        k = Integer.valueOf(by).intValue();
        j = Integer.valueOf(bA).intValue();
        h = Integer.valueOf(bB).intValue();
        i = 0xff000000 | k << 16 | j << 8 | h;
        if(cg > 8)
            cg = 8;
        else
        if(cg < 1)
            cg = 1;
        String s9 = getParameter("VentanaFlotante");
        if(s9 == null)
            s9 = "NO";
        if(s9.equalsIgnoreCase("SI"))
        {
            I = true;
            J = Integer.valueOf(getParameter("VentanaFlotanteAncho")).intValue();
            G = Integer.valueOf(getParameter("VentanaFlotanteAlto")).intValue();
            String s10 = getParameter("VentanaFlotanteDelante");
            if(s10 == null)
                s10 = "NO";
            if(s10.equalsIgnoreCase("SI"))
                H = true;
        } else
        {
            I = false;
        }
        if(I)
        {
            ce = J;
            cd = G;
        } else
        {
            ce = size().width;
            cd = size().height;
        }
        dd = ce / cg; 
        P = cd / cg;  
        Q = P / 2;
        R = dd / 2;
        cT = P * 3;
        cW = dd * P;
        t = b();
        dm = t + 256;
        cA = new int[cW];
        r = new int[cW];
        for(int l2 = 0; l2 < cW; l2++)
            r[l2] = i;

        bD = 0.2F;
        cH = new int[0x10000];
        cJ = new int[0x10000];
        cL = new int[0x10000];
        cN = new int[0x10000];
        cP = new int[0x10000];
        cR = new int[0x10000];
        cI = new int[0x10000];
        cK = new int[0x10000];
        cM = new int[0x10000];
        cO = new int[0x10000];
        cQ = new int[0x10000];
        cS = new int[0x10000];
        cz = new float[1280];
        ca = new int[24];
        dk = new int[6];
        ck = new int[P * 6];
        for(int l1 = 0; l1 < 1024; l1++)
            cz[l1] = (float)Math.sin(((double)l1 * 2D * 3.1415926535897931D) / 1024D);

        for(int i2 = 0; i2 < 256; i2++)
            cz[i2 + 1024] = cz[i2];

        if(m)
        {
            n = new int[cW];
            for(int i3 = 0; i3 < cW; i3++)
                n[i3] = i;

        }
        try
        {
            h();
        }
        catch(NoSuchMethodError _ex)
        {
            h();
        }
        n();
        bN = createImage(ce, cd + cu);
        bM = bN.getGraphics();
        if(I)
        {
            N = new Frame(getParameter("VentanaFlotanteTitulo"));
            N.add("Center", this);
        }
    }

    void h()
    {
        cc = new MemoryImageSource(dd, P, new DirectColorModel(24, 0xff0000, 65280, 255), r, 0, dd);
        String s1;
        try
        {
            s1 = System.getProperty("java.version");
        }
        catch(SecurityException _ex)
        {
            s1 = "unk";
        }
        if(!s1.startsWith("1.0"))
            try
            {
                cc.setAnimated(true);
                cc.setFullBufferUpdates(true);
                V = createImage(cc);
                cc.newPixels();
                bk = true;
            }
            catch(NoSuchMethodError _ex)
            {
                bk = false;
            }
        if(!bk)
        {
            cc = null;
            cb = new BROCOyCASANOVA_Imagenes(dd, P, new DirectColorModel(24, 0xff0000, 65280, 255), r, 0, dd);
            V = createImage(cb);
        }
    }

    private final void a(int i1, int j1, int k1, int l1, int i2, int j2, int k2,
            int l2)
    {
        int k4 = cT;
        int ai[] = ck;
        int l4 = 0;
        if(j1 == j2)
            return;
        if(j2 < j1)
        {
            j1 -= j2;
            l4 += j2 + j2 + j2 + k4;
            int i3 = (i1 - i2 << 16) / j1;
            int k3 = (k1 - k2) / j1;
            int i4 = (l1 - l2) / j1;
            i2 <<= 16;
            do
            {
                ai[l4++] = i2 >> 16;
                ai[l4++] = k2 & 0xff00;
                ai[l4++] = l2 & 0xff00;
                i2 += i3;
                k2 += k3;
                l2 += i4;
            } while(--j1 > 0);
            return;
        }
        j2 -= j1;
        l4 += j1 + j1 + j1;
        int j3 = (i2 - i1 << 16) / j2;
        int l3 = (k2 - k1) / j2;
        int j4 = (l2 - l1) / j2;
        i1 <<= 16;
        do
        {
            ai[l4++] = i1 >> 16;
            ai[l4++] = k1 & 0xff00;
            ai[l4++] = l1 & 0xff00;
            i1 += j3;
            k1 += l3;
            l1 += j4;
            j2--;
        } while(j2 > 0);
    }

    private final boolean i()
    {
        bE = new MediaTracker(this);
        showStatus("Cargando Imagenes...");
        W = a(bc);
        s = true;
        a(W);
        bi = 1;
        repaint();
        X = a(bd);
        a(X);
        bi = 2;
        repaint();
        Y = a(be);
        a(Y);
        bi = 3;
        repaint();
        Z = a(bf);
        a(Z);
        bi = 4;
        repaint();
        ba = a(bg);
        a(ba);
        bi = 5;
        repaint();
        bb = a(bh);
        a(bb);
        bi = 6;
        repaint();
        bp = null;
        bp = getParameter("ImagenFondo");
        if(!bp.equalsIgnoreCase("NO"))
        {
            Image image = a(bp);
            if(image != null && image.getWidth(this) == dd && image.getHeight(this) == P)
            {
                PixelGrabber pixelgrabber1 = new PixelGrabber(image, 0, 0, dd, P, n, 0, dd);
                try
                {
                    pixelgrabber1.grabPixels();
                }
                catch(InterruptedException _ex) { }
                for(df = 0; df < cW; df++)
                    n[df] |= 0xff000000;

            }
        }
        if(W.getWidth(this) == 256)
        {
            PixelGrabber pixelgrabber = new PixelGrabber(W, 0, 0, 256, 256, cH, 0, 256);
            try
            {
                pixelgrabber.grabPixels();
            }
            catch(InterruptedException _ex) { }
            PixelGrabber pixelgrabber2 = new PixelGrabber(X, 0, 0, 256, 256, cJ, 0, 256);
            try
            {
                pixelgrabber2.grabPixels();
            }
            catch(InterruptedException _ex) { }
            PixelGrabber pixelgrabber4 = new PixelGrabber(Y, 0, 0, 256, 256, cL, 0, 256);
            try
            {
                pixelgrabber4.grabPixels();
            }
            catch(InterruptedException _ex) { }
            PixelGrabber pixelgrabber6 = new PixelGrabber(Z, 0, 0, 256, 256, cN, 0, 256);
            try
            {
                pixelgrabber6.grabPixels();
            }
            catch(InterruptedException _ex) { }
            PixelGrabber pixelgrabber8 = new PixelGrabber(ba, 0, 0, 256, 256, cP, 0, 256);
            try
            {
                pixelgrabber8.grabPixels();
            }
            catch(InterruptedException _ex) { }
            PixelGrabber pixelgrabber10 = new PixelGrabber(bb, 0, 0, 256, 256, cR, 0, 256);
            try
            {
                pixelgrabber10.grabPixels();
            }
            catch(InterruptedException _ex) { }
        } else
        {
            int ai[] = new int[16384];
            PixelGrabber pixelgrabber3 = new PixelGrabber(W, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber3.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber3 = null;
            a(ai, cH);
            PixelGrabber pixelgrabber5 = new PixelGrabber(X, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber5.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber5 = null;
            a(ai, cJ);
            PixelGrabber pixelgrabber7 = new PixelGrabber(Y, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber7.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber7 = null;
            a(ai, cL);
            PixelGrabber pixelgrabber9 = new PixelGrabber(Z, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber9.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber9 = null;
            a(ai, cN);
            PixelGrabber pixelgrabber11 = new PixelGrabber(ba, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber11.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber11 = null;
            a(ai, cP);
            PixelGrabber pixelgrabber12 = new PixelGrabber(bb, 0, 0, 128, 128, ai, 0, 128);
            try
            {
                pixelgrabber12.grabPixels();
            }
            catch(InterruptedException _ex) { }
            pixelgrabber12 = null;
            a(ai, cR);
        }
        X.flush();
        X = null;
        Y.flush();
        Y = null;
        Z.flush();
        Z = null;
        ba.flush();
        ba = null;
        bb.flush();
        bb = null;
        a(cH, cI, 0x1000000);
        a(cJ, cK, 0x2000000);
        a(cL, cM, 0x3000000);
        a(cN, cO, 0x4000000);
        a(cP, cQ, 0x5000000);
        a(cR, cS, 0x6000000);
        a(cH, 0x1000000);
        a(cJ, 0x2000000);
        a(cL, 0x3000000);
        a(cN, 0x4000000);
        a(cP, 0x5000000);
        a(cR, 0x6000000);
        return true;
    }

    private final void a(int i1, int ai[])
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        int l3 = ca[u[i1]];
        int j4 = ca[u[i1++] + 1];
        int k4 = ca[u[i1]];
        int l4 = ca[u[i1++] + 1];
        int i5 = ca[u[i1]];
        int j5 = ca[u[i1++] + 1];
        int k5 = ca[u[i1]];
        int l5 = ca[u[i1++] + 1];
        int k2;
        int l2;
        if(j4 > l4)
        {
            k2 = l4;
            l2 = j4;
        } else
        {
            k2 = j4;
            l2 = l4;
        }
        if(k2 > j5)
            k2 = j5;
        else
        if(l2 < j5)
            l2 = j5;
        if(k2 > l5)
            k2 = l5;
        else
        if(l2 < l5)
            l2 = l5;
        l2 -= k2;
        if(l2 <= 0)
            return;
        a(l3, j4, 65280, 65280, k4, l4, 65280, 0);
        a(k4, l4, 65280, 0, i5, j5, 0, 0);
        a(i5, j5, 0, 0, k5, l5, 0, 65280);
        a(k5, l5, 0, 65280, l3, j4, 65280, 65280);
        int k6 = dd;
        int ai1[] = ck;
        int ai2[] = r;
        int ai3[] = ai;
        int j1 = (k2 - 1) * k6;
        int i6 = k2 + k2 + k2;
        int j6 = i6 + cT;
        do
        {
            j1 += k6;
            int i4 = ai1[i6++];
            int i3 = ai1[j6++] - i4;
            int k1 = ai1[i6++];
            int i2 = ai1[j6++];
            int l1 = ai1[i6++];
            int j2 = ai1[j6++];
            if(i3 > 0)
            {
                int j3 = (i2 - k1) / i3;
                int k3 = (j2 - l1) / i3;
                do
                {
                    ai2[j1 + i4++] = ai3[(l1 & 0xff00) + (k1 >> 8)];
                    k1 += j3;
                    l1 += k3;
                } while(--i3 > 0);
            }
        } while(--l2 > 0);
    }

    void a(int ai[], int i1)
    {
        for(int k2 = 0; k2 < 0x10000; k2++)
        {
            int j1 = (ai[k2] & 0xff0000) >> 16;
            int k1 = (ai[k2] & 0xff00) >> 8;
            int l1 = ai[k2] & 0xff;
            int i2 = j1 + k1 + l1;
            int j2;
            if(i2 > 600)
                j2 = (200 - i2 / 3) / 4;
            else
                j2 = 0;
            j1 = (int)((float)j1 / 1.3F);
            j1 -= j2;
            k1 = (int)((float)k1 / 1.3F);
            k1 -= j2;
            l1 = (int)((float)l1 / 1.3F);
            l1 -= j2;
            if(j1 < 0)
                j1 = 0;
            if(k1 < 0)
                k1 = 0;
            if(l1 < 0)
                l1 = 0;
            ai[k2] = j1 << 16 | k1 << 8 | l1 | i1;
        }

    }

    void a(int ai[], int ai1[], int i1)
    {
        for(int k2 = 0; k2 < 0x10000; k2++)
        {
            int j1 = (ai[k2] & 0xff0000) >> 16;
            int k1 = (ai[k2] & 0xff00) >> 8;
            int l1 = ai[k2] & 0xff;
            int i2 = j1 + k1 + l1;
            int j2;
            if(i2 < 600)
                j2 = (200 - i2 / 3) / 3;
            else
                j2 = 0;
            j1 = (int)((float)j1 * 1.2F);
            j1 += j2;
            k1 = (int)((float)k1 * 1.2F);
            k1 += j2;
            l1 = (int)((float)l1 * 1.2F);
            l1 += j2;
            if(j1 > 255)
                j1 = 255;
            if(k1 > 255)
                k1 = 255;
            if(l1 > 255)
                l1 = 255;
            ai1[k2] = j1 << 16 | k1 << 8 | l1 | i1;
        }

    }

    public final synchronized boolean mouseDown(Event event, int i1, int j1)
    {
        if(!bS)
            cq = (r[i1 + j1 * ce] & 0xff000000) >> 24;
        if(cq == -1)
        {
            showStatus("");
        } else
        {
           URL url = null;
           if(!db[cq].equalsIgnoreCase("NO"))
           {
               showStatus(cB[cq - 1]);
               try
               {
                   url = new URL(getDocumentBase(), db[cq]);
               }
               catch(MalformedURLException _ex)
               {
                    showStatus("Error en el HiperVínculo");
                }
                if(bI)
                    getAppletContext().showDocument(url, getParameter("NombreMarco"));
                else
                   getAppletContext().showDocument(url);
           }
        }
        return true;
    }

    public final boolean mouseEnter(Event event, int i1, int j1)
    {
        bS = false;
        return true;
    }

    public final boolean mouseExit(Event event, int i1, int j1)
    {
        e /= 10F;
        f /= 10F;
        g /= 10F;
        bS = true;
        cq = -1;
        return true;
    }

    public synchronized boolean mouseMove(Event event, int i1, int j1)
    {
        p = System.currentTimeMillis();
        bJ = true;
        if(i1 > ce - 1)
            i1 = ce - 1;
        if(j1 > cd - 1)
            j1 = cd - 1;
        if(!bK)
        {
            if(!bS)
                cq = (r[i1 / cg + (j1 / cg) * dd] & 0xff000000) >> 24;
            if(cq == -1)
            {
                b.setCursor(0);
                showStatus("");
            } else
            {
                if(db[cq].equalsIgnoreCase("NO"))
                    b.setCursor(0);
                else
                if(b.getCursorType() != 12)
                    b.setCursor(12);
                showStatus(cB[cq - 1]);
            }
        }
        bQ = i1;
        bR = j1;
        e = (((float)(cd / 2 - j1) * bD) / (float)cd) * 2.0F;
        f = (((float)(ce / 2 - i1) * -bD) / (float)ce) * 2.0F;
        g = 0.0F;
        return true;
    }

    public void paint(Graphics g1)
    {
	  String cadena;
        if(!L)
        {
            if(s)
                switch(bi)
                {
                case 1: // '\001'
                    bM.drawImage(W, 0, 0, ce, cd, this);
                    break;

                case 2: // '\002'
                    bM.drawImage(X, 0, 0, ce, cd, this);
                    break;

                case 3: // '\003'
                    bM.drawImage(Y, 0, 0, ce, cd, this);
                    break;

                case 4: // '\004'
                    bM.drawImage(Z, 0, 0, ce, cd, this);
                    break;

                case 5: // '\005'
                    bM.drawImage(ba, 0, 0, ce, cd, this);
                    break;

                case 6: // '\006'
                    bM.drawImage(bb, 0, 0, ce, cd, this);
                    break;
                }
            else
            if(cg == 1)
            {
                bM.drawImage(V, 0, 0, this);
            } else 
            {	
                k();
                bM.drawImage(V, 0, 0, ce, cd, this);
            }
            bM.setColor(Color.black);
            bM.drawString("Espere", (ce / 2 - 16) + 1, (cd / 2 - 4 - 6) + 1);
            bM.drawString("Cargando", (ce / 2 - 20) + 1, cd / 2 + 2 + 1);
            bM.setColor(Color.white);
            bM.drawString("Espere", ce / 2 - 16, cd / 2 - 4 - 6);
            bM.drawString("Cargando", ce / 2 - 20, cd / 2 + 2);
            g1.drawImage(bN, 0, 0, this);
            return;
        }
        if(V != null)
        {
            if(cg == 1)
            {
                bM.drawImage(V, 0, 0, this);
            } else
            {	
                k();
                bM.drawImage(V, 0, 0, ce, cd, this);
            }
            if(bT != null)
                j();
            if(cF)
                b(bM);
            g1.drawImage(bN, 0, 0, this);
        }
    }

    public synchronized void j()
    {
        if(c)
        {
            notifyAll();
            while(!bO)
            {
                Thread.yield();
                try
                {
                    Thread.sleep(8L);
                }
                catch(InterruptedException _ex) { }
            }
            bO = false;
        }
        bM.drawImage(bT, bU, bV, this);
    }

    public synchronized void a(Image image)
    {
        int i1 = 0;
        prepareImage(image, ce, cd, this);
        notifyAll();
        for(; (i1 & 0xf0) == 0; i1 = checkImage(image, ce, cd, this))
            Thread.yield();

    }

    public synchronized void k()
    {
        int i1 = 0;
        prepareImage(V, ce, cd, this);
        notifyAll();
        for(; (i1 & 0xf0) == 0; i1 = checkImage(V, ce, cd, this))
            Thread.yield();

    }

    public final void l()
    {
        try
        {
            if(bk)
            {
                cc.newPixels();
                return;
            } else
            {
                cb.startProduction(cb.getConsumer());
                return;
            }
        }
        catch(NoSuchMethodError _ex)
        {
            return;
        }
    }

    private final void a(int ai[], int ai1[])
    {
        int i1 = 0;
        for(int j1 = 0; j1 < 0x10000;)
        {
            for(df = 0; df < 256; df++)
                ai1[j1 + df] = ai[i1 + (df >> 1)];

            int k1 = j1;
            j1 += 256;
            for(df = 0; df < 256; df++)
                ai1[j1 + df] = ai1[k1 + df];

            j1 += 256;
            i1 += 128;
        }

    }

    private final void m()
    {
        int i1 = 8;
        float f4 = 0.0F;
        float f6 = 0.0F;
        float f8 = 0.0F;
        int k1 = 0;
        int l1 = 0;
        float f12 = (float)Math.sin(e);
        float f13 = (float)Math.cos(e);
        float f14 = (float)Math.sin(f);
        float f15 = (float)Math.cos(f);
        float f16 = (float)Math.sin(g);
        float f17 = (float)Math.cos(g);
        float f10 = f12 * f16;
        float f11 = f12 * f17;
        w = f15 * f17 + f10 * f14;
        x = f11 * f14 - f16 * f15;
        y = f14 * f13;
        z = f16 * f13;
        A = f13 * f17;
        B = -f12;
        C = f10 * f15 - f14 * f17;
        D = f14 * f16 + f11 * f15;
        E = f13 * f15;
        float f18 = bP[0] * w + bP[1] * z + bP[2] * C;
        float f19 = bP[0] * x + bP[1] * A + bP[2] * D;
        float f20 = bP[0] * y + bP[1] * B + bP[2] * E;
        float f21 = bP[3] * w + bP[4] * z + bP[5] * C;
        float f22 = bP[3] * x + bP[4] * A + bP[5] * D;
        float f23 = bP[3] * y + bP[4] * B + bP[5] * E;
        float f24 = bP[6] * w + bP[7] * z + bP[8] * C;
        float f25 = bP[6] * x + bP[7] * A + bP[8] * D;
        float f26 = bP[6] * y + bP[7] * B + bP[8] * E;
        bP[0] = f18;
        bP[1] = f19;
        bP[2] = f20;
        bP[3] = f21;
        bP[4] = f22;
        bP[5] = f23;
        bP[6] = f24;
        bP[7] = f25;
        bP[8] = f26;
        do
        {
            float f5 = bW[k1++];
            float f7 = bW[k1++];
            float f9 = bW[k1++];
            float f1 = f5 * f18 + f7 * f21 + f9 * f24;
            float f2 = f5 * f19 + f7 * f22 + f9 * f25;
            float f3 = f5 * f20 + f7 * f23 + f9 * f26;
            int j1 = (int)f3 + dm;
            ca[l1++] = (int)((double)f1 * (256D / (double)j1) + (double)R);
            ca[l1++] = (int)((double)f2 * (256D / (double)j1) + (double)Q);
            ca[l1++] = j1;
        } while(--i1 > 0);
    }

    public void run()
    {
        if(I)
            g();
        cV.setPriority(bZ);
        showStatus("");
        if(!L)
        {
            L = i();
            if(!L)
            {
                showStatus("Error Cargando Imagenes.");
                stop();
                return;
            }
        }
        cV.setPriority(bY);
        showStatus("");
        System.gc();
        o = System.currentTimeMillis();
        Graphics g1 = getGraphics();
        if(bT != null && !c)
            c = d();
        b.setCursor(0);
        while(cV != null)
        {
            f();
            if(++l == bn)
            {
                System.gc();
                l = 0;
            }
            try
            {
                l();
            }
            catch(NoSuchMethodError _ex) { }
            if(cg == 1)
            {
                bM.drawImage(V, 0, 0, this);
            } else
            {
                k();
                bM.drawImage(V, 0, 0, ce, cd, this);
            }
            if(bT != null)
                j();
            if(cF)
                b(bM);
            g1.drawImage(bN, 0, 0, this);
            p();
        }
    }

    byte a(int i1, int j1, int k1, int l1)
    {
        int i2 = i1 - j1;
        if(i2 >= k1)
        {
            return (byte)i2;
        } else
        {
            int j2 = k1 - i2 - 1;
            return (byte)(l1 - j2);
        }
    }

    public void n()
    {
        String s1 = null;
        cF = false;
        s1 = getParameter("TextoDeslizandose");
        if(s1 != null && !s1.equalsIgnoreCase("NO"))
        {
            String s2 = null;
            s2 = getParameter("TipoTexto");
            if(s2 == null)
                s2 = "horizontal";
            if(s2.equals("horizontal"))
                co = 0;
            else
            if(s2.equals("vertical"))
                co = 1;
            else
            if(s2.equals("ZoomAcercar"))
                co = 2;
            else
            if(s2.equals("ZoomAlejar"))
                co = 3;
            if(co == 0)
            {
                a(s1, 0);
                if(cl != null)
                    cF = true;
            } else
            {
                a(s1, 1);
                if(bl != null)
                    cF = true;
            }
        }
        if(cF)
        {
            String s3 = null;
            s3 = getParameter("TextoVelocidad");
            if(s3 == null)
                s3 = "0";
            cY = Integer.valueOf(s3).intValue();
            String s4 = null;
            s4 = getParameter("TextoFuente");
            if(s4 == null)
                s4 = "Arial";
            int i1 = 0;
            String s5 = null;
            s5 = getParameter("TextoNegrita");
            if(s5.equalsIgnoreCase("SI"))
                i1++;
            String s6 = null;
            s6 = getParameter("TextoCursiva");
            if(s6 == null)
                s6 = "NO";
            if(s6.equalsIgnoreCase("SI"))
                i1 += 2;
            String s7 = null;
            s7 = getParameter("TextoTam");
            if(s7 == null)
                s7 = "12";
            int j1 = Integer.valueOf(s7).intValue();
            K = new Font(s4, i1, j1);
            String s8 = null;
            s8 = getParameter("TextoSombra");
            if(s8.equalsIgnoreCase("SI"))
                cG = true;
            else
                cG = false;
            cC = new Color(Integer.valueOf(getParameter("TextoColorRojo")).intValue(), Integer.valueOf(getParameter("TextoColorVerde")).intValue(), Integer.valueOf(getParameter("TextoColorAzul")).intValue());
            cp = new Color(Integer.valueOf(getParameter("TextoSombraColorRojo")).intValue(), Integer.valueOf(getParameter("TextoSombraColorVerde")).intValue(), Integer.valueOf(getParameter("TextoSombraColorAzul")).intValue());
            cZ = size().width;
            da = size().height;
            if(co == 0)
            {
                String s9 = null;
                s9 = getParameter("TextoDeslucido");
                if(s9 == null)
                    s9 = "0";
                dj = Integer.valueOf(s9).intValue();
                if(dj < 0)
                    dj = 0;
                String s12 = null;
                s12 = getParameter("TextoAmplitudSalto");
                if(s12 == null)
                    s12 = "0";
                a = Integer.valueOf(s12).intValue();
                String s14 = null;
                s14 = getParameter("TextoVelocidadSalto");
                if(s14 == null)
                    s14 = "0";
                cX = Integer.valueOf(s14).intValue();
                String s15 = null;
                s15 = getParameter("TextoAmplitudSeno");
                if(s15 == null)
                    s15 = "0";
                cs = Integer.valueOf(s15).intValue();
                String s16 = null;
                s16 = getParameter("TextoVelocidadSeno");
                if(s16 == null)
                    s16 = "0";
                cw = Integer.valueOf(s16).intValue();
                String s17 = null;
                s17 = getParameter("TextoAnguloSeno");
                if(s17 == null)
                    s17 = "0";
                ct = Integer.valueOf(s17).intValue();
                FontMetrics fontmetrics1 = getGraphics().getFontMetrics(K);
                cn = fontmetrics1.stringWidth(cl);
                cm = fontmetrics1.getHeight();
                q = fontmetrics1.getMaxDescent();
                dg = cZ;
                if(dj < cm - q)
                    dj = cm - q;
                else
                if(dj > da - q)
                    dj = da - q;
                if(cs != 0)
                {
                    cx = new int[cZ + 360];
                    cy = new int[cZ + 360];
                    for(int i2 = 0; i2 < cZ + 360; i2++)
                    {
                        cx[i2] = ((int)((double)cs * Math.sin(((double)ct * (double)i2 * 3.1415926535897931D) / 180D)) - cm - q) + dj;
                        cy[i2] = cx[i2] - cd;
                    }

                    cr = 360;
                    cu = cm + q + 1;
                    cv = cu - 1;
                    return;
                }
            } else
            {
                if(co == 1)
                {
                    String s10 = null;
                    s10 = getParameter("TextoEspacioVertical");
                    if(s10 == null)
                        s10 = "10";
                    int k1 = Integer.valueOf(s10).intValue();
                    FontMetrics fontmetrics = getGraphics().getFontMetrics(K);
                    M = fontmetrics.getHeight() + k1;
                    dc = new int[bl.length];
                    for(U = 0; U < bl.length; U++)
                        dc[U] = (cZ - fontmetrics.stringWidth(bl[U])) / 2;

                    bH = -M;
                    return;
                }
                if(co >= 2)
                {
                    String s11 = null;
                    s11 = getParameter("TextoFuenteMinima");
                    if(s11 == null)
                        s11 = "2";
                    cE = Integer.valueOf(s11).intValue();
                    String s13 = null;
                    s13 = getParameter("TextoFuenteMaxima");
                    if(s13 == null)
                        s13 = "72";
                    cD = Integer.valueOf(s13).intValue();
                    bL = cD - cE;
                    K = null;
                    dp = new Font[bL];
                    int l1 = cE;
                    for(U = 0; U < bL; U++)
                        dp[U] = new Font(s4, i1, l1++);

                    T = (float)cZ / 2.0F;
                    S = (float)da / 2.0F;
                    if(co == 3)
                    {
                        _flddo = bL - 1;
                        return;
                    }
                    _flddo = 0;
                }
            }
        }
    }

    public void b(Graphics g1)
    {
        switch(co)
        {
        case 0: // '\0'
            a(g1);
            return;

        case 1: // '\001'
            c(g1);
            return;
        }
        d(g1);
    }

    public synchronized void start()
    {
        if(cV == null)
        {
            cV = new Thread(this);
            cV.start();
            if(I)
            {
                g();
                N.show();
            }
        }
    }

    public synchronized void stop()
    {
        if(cV != null && cV.isAlive())
            cV.stop();
        if(I)
            N.hide();
        cV = null;
    }

    private final void o()
    {
        int i1 = F;
        do
        {
            i1--;
            int j1 = dk[i1];
            int k1 = j1 >> 2;
            if(cq == k1 + 1)
                switch(k1)
                {
                case 5: // '\005'
                    a(j1, cS);
                    break;

                case 4: // '\004'
                    a(j1, cQ);
                    break;

                case 3: // '\003'
                    a(j1, cO);
                    break;

                case 2: // '\002'
                    a(j1, cM);
                    break;

                case 1: // '\001'
                    a(j1, cK);
                    break;

                case 0: // '\0'
                    a(j1, cI);
                    break;
                }
            else
                switch(k1)
                {
                case 5: // '\005'
                    a(j1, cR);
                    break;

                case 4: // '\004'
                    a(j1, cP);
                    break;

                case 3: // '\003'
                    a(j1, cN);
                    break;

                case 2: // '\002'
                    a(j1, cL);
                    break;

                case 1: // '\001'
                    a(j1, cJ);
                    break;

                case 0: // '\0'
                    a(j1, cH);
                    break;
                }
        } while(i1 > 0);
    }

    public final void update(Graphics g1)
    {
        paint(g1);
    }

    public void c(Graphics g1)
    {
        g1.setFont(K);
        bH += cY;
        if(bH > da + bl.length * M)
            bH = -M;
        if(cG)
        {
            for(int i1 = 0; i1 < bl.length; i1++)
            {
                String s1 = bl[i1];
                int k1 = dc[i1];
                int l1 = (da - bH) + i1 * M;
                g1.setColor(cp);
                g1.drawString(s1, k1 + 1, l1 + 1);
                g1.setColor(cC);
                g1.drawString(s1, k1, l1);
            }

            return;
        }
        g1.setColor(cC);
        for(int j1 = 0; j1 < bl.length; j1++)
            g1.drawString(bl[j1], dc[j1], (da - bH) + j1 * M);

    }

    public synchronized void p()
    {
        Thread.yield();
        cU.sync();
        long l1 = 10L - (System.currentTimeMillis() - o);
        if(l1 > 0L)
            try
            {
                Thread.sleep(l1);
            }
            catch(InterruptedException _ex) { }
        else
            try
            {
                Thread.sleep(1L);
            }
            catch(InterruptedException _ex) { }
        o = System.currentTimeMillis();
        try
        {
            Thread.sleep(bo);
            return;
        }
        catch(InterruptedException _ex)
        {
            return;
        }
    }

    public void d(Graphics g1)
    {
        String s1 = bl[dn];
        g1.setFont(dp[_flddo]);
        FontMetrics fontmetrics = g1.getFontMetrics(dp[_flddo]);
        int i1 = (int)(T - (float)fontmetrics.stringWidth(s1) / 2.0F);
        int j1 = (int)(S + (float)fontmetrics.getHeight() / 4F);
        if(cG)
        {
            g1.setColor(cp);
            g1.drawString(s1, i1 + 1, j1 + 1);
        }
        g1.setColor(cC);
        g1.drawString(s1, i1, j1);
        if(co == 3)
        {
            _flddo -= cY;
            if(_flddo <= 1)
            {
                _flddo = bL - 1;
                dn++;
                if(dn >= bl.length)
                {
                    dn = 0;
                    return;
                }
            }
        } else
        {
            _flddo += cY;
            if(_flddo >= bL)
            {
                _flddo = 0;
                dn++;
                if(dn >= bl.length)
                    dn = 0;
            }
        }
    }

    private static String c(String s1)
    {
        char ac[] = s1.toCharArray();
        int i1 = ac.length;
        int j1 = 0;
label0:
        do
        {
            int k1 = 0;
            ac[j1] ^= '{';
            do
            {
                j1++;
                k1++;
                if(i1 != j1)
                    switch(k1)
                    {
                    case 1: // '\001'
                        ac[j1] ^= '\031';
                        break;

                    case 2: // '\002'
                        ac[j1] ^= 'r';
                        break;

                    case 3: // '\003'
                        ac[j1] ^= '!';
                        break;

                    case 4: // '\004'
                        ac[j1] ^= 'm';
                        break;

                    case 5: // '\005'
                        continue label0;
                    }
                else
                    return new String(ac);
            } while(true);
        } while(true);
    }

    private int a;
    Frame b;
    boolean c;
    float e;
    float f;
    float g;
    int h;
    int i;
    int j;
    int k;
    int l;
    boolean m;
    int n[];
    long o;
    long p;
    int q;
    int r[];
    boolean s;
    int t;
    final int u[] = {
        9, 6, 3, 0, 12, 15, 18, 21, 3, 6,
        18, 15, 12, 21, 9, 0, 0, 3, 15, 12,
        9, 21, 18, 6
    };
    static final int v = 6;
    float w;
    float x;
    float y;
    float z;
    float A;
    float B;
    float C;
    float D;
    float E;
    int F;
    int G;
    boolean H;
    boolean I;
    int J;
    Font K;
    boolean L;
    int M;
    Frame N;
    private Graphics O;
    int P;
    int Q;
    int R;
    float S;
    float T;
    int U;
    private Image V;
    private Image W;
    private Image X;
    private Image Y;
    private Image Z;
    private Image ba;
    private Image bb;
    String bc;
    String bd;
    String be;
    String bf;
    String bg;
    String bh;
    int bi;
    int bj;
    boolean bk;
    String bl[];
    URL bm;
    int bn;
    int bo;
    String bp;
    String bq;
    String br;
    String bs;
    String bt;
    String bu;
    String bv;
    String bw;
    String bx;
    String by;
    String bz;
    String bA;
    String bB;
    String bC;
    float bD;
    MediaTracker bE;
    static final int bF = 1024;
    static final float bG = 1024F;
    int bH;
    boolean bI;
    boolean bJ;
    boolean bK;
    int bL;
    private Graphics bM;
    private Image bN;
    boolean bO;
    float bP[] = {
        1.0F, 0, 0, 0, 1.0F, 0, 0, 0, 1.0F
    };
    int bQ;
    int bR;
    boolean bS;
    private Image bT;
    int bU;
    int bV;
    final float bW[] = {
        -64F, -64F, -64F, 64F, -64F, -64F, 64F, 64F, -64F, -64F,
        64F, -64F, -64F, -64F, 64F, 64F, -64F, 64F, 64F, 64F,
        64F, -64F, 64F, 64F
    };
    static final int bX = 8;
    int bY;
    int bZ;
    int ca[];
    BROCOyCASANOVA_Imagenes cb;
    MemoryImageSource cc;
    int cd;
    int ce;
    int cg;
    float ch;
    float ci;
    float cj;
    int ck[];
    String cl;
    int cm;
    int cn;
    int co;
    Color cp;
    int cq;
    int cr;
    int cs;
    int ct;
    int cu;
    int cv;
    int cw;
    int cx[];
    int cy[];
    float cz[];
    int cA[];
    String cB[];
    Color cC;
    int cD;
    int cE;
    boolean cF;
    boolean cG;
    int cH[];
    int cI[];
    int cJ[];
    int cK[];
    int cL[];
    int cM[];
    int cN[];
    int cO[];
    int cP[];
    int cQ[];
    int cR[];
    int cS[];
    int cT;
    Toolkit cU;
    Thread cV;
    int cW;
    int cX;
    int cY;
    int cZ;
    int da;
    String db[];
    int dc[];
    int dd;
    int df;
    int dg;
    int dh;
    int di;
    int dj;
    int dk[];
    static final int dl = 256;
    int dm;
    int dn;
    int _flddo;
    Font dp[];
}
