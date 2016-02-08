package fractal;

import processing.core.PApplet;
import processing.core.PImage;

public class Orbs extends PApplet
{
    class Orb
    {

        public void setPosition(float f, float f1)
        {
            x = f;
            y = f1;
        }

        public void orbit()
        {
            t += tv;
            x = orbitals[pid].x + r * cos(t);
            y = orbitals[pid].y + r * sin(t);
            tv *= 0.99942F;
        }

        public void draw()
        {
            float f = random(-0.22F, 0.22F);
            float f2 = random(-0.22F, 0.22F);
            stroke(red(myc), green(myc), blue(myc), 40F);
            point(x + f, y + f2);
            if(sumtv() < 1.00001F)
            {
                float f4 = random(6.283185F);
                float f1 = orbitals[pid].x + r * cos(f4);
                float f3 = orbitals[pid].y + r * sin(f4);
                stroke(red(myc), green(myc), blue(myc), 18F);
                point(f1, f3);
                f4 = random(1.0F);
                f1 = x + f4 * (orbitals[pid].x - x);
                f3 = y + f4 * (orbitals[pid].y - y);
                stroke(0.0F, 18F);
                point(f1, f3);
            }
        }

        public float sumtv()
        {
            if(pid != id)
                return orbitals[pid].sumtv() + tv;
            else
                return tv + 1.0F;
        }

        int id;
        int pid;
        float r;
        float t;
        float tv;
        float tvd;
        float x;
        float y;
        int d;
        int myc;

        Orb(int i, int j)
        {
            id = i;
            pid = j;
            if(id != pid)
            {
                d = orbitals[pid].d + 1;
                r = random(1.0F, 1.0F + (0.4F * (float)dimAlto) / (float)d);
                t = -1.570796F;
                tv = random(0.0001F, 0.02F / (float)(d + 1));
                if(random(100F) < 50F)
                    tv *= -1.0F;
                tvd = random(1.001F, 1.01F);
            } else
            {
                r = 0.0F;
            }
            myc = somecolor();
        }
    }


    public void setup()
    {
        size(dimAncho, dimAlto, "processing.core.PGraphics3");
        takecolor("longConejo.gif");
        background(0);
        noFill();
        framerate(30F);
        orbitals = new Orb[num];
        resetAll();
    }

    public void draw()
    {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < num; j++)
                orbitals[j].orbit();

            for(int k = 0; k < num; k++)
                orbitals[k].draw();

        }

    }

    public void mousePressed()
    {

    }

    public void resetAll()
    {
        for(int i = 0; i < num; i++)
        {
            int j = i;
            if((float)i > (float)num * 0.1F)
                j = PApplet.toInt(random(i));
            orbitals[i] = new Orb(i, j);
            if(i == j)
                orbitals[i].setPosition(dimAncho / 2, dimAlto / 2);
        }

    }

    public int somecolor()
    {
        return goodcolor[PApplet.toInt(random(numpal))];
    }

    public void takecolor(String s)
    {
        PImage pimage = loadImage(s);
        image(pimage, 0.0F, 0.0F);
        for(int i = 0; i < pimage.width; i++)
        {
            for(int j = 0; j < pimage.height; j++)
            {
                int k = get(i, j);
                boolean flag = false;
                for(int l = 0; l < numpal; l++)
                {
                    if(k != goodcolor[l])
                        continue;
                    flag = true;
                    break;
                }

                if(!flag)
                {
                    if(numpal >= maxpal)
                        break;
                    goodcolor[numpal] = k;
                    numpal++;
                }
                if(random(10000F) < 100F && numpal < maxpal)
                {
                    if(random(100F) < 50F)
                    {
                        goodcolor[numpal] = -1;
                        print("w");
                    } else
                    {
                        goodcolor[numpal] = 0xff000000;
                        print("b");
                    }
                    numpal++;
                }
            }

        }

    }


    public Orbs(int ancho,int alto)
    {
        dimAncho=ancho;
        dimAlto=alto;
        num = 200;
        maxpal = 512;
        numpal = 0;
        goodcolor = new int[maxpal];
    }

    int dimAncho,dimAlto;
    int num;
    Orb orbitals[];
    int maxpal;
    int numpal;
    int goodcolor[];
}