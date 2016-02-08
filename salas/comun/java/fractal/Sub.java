package fractal;

import processing.core.PApplet;
import processing.core.PImage;

public class Sub extends PApplet
{
    class Cr
    {

        public void findStart()
        {
            int i = 0;
            int j = 0;
            boolean flag = false;
            for(int k = 0; !flag || k++ > 1000;)
            {
                i = PApplet.toInt(random(dimx));
                j = PApplet.toInt(random(dimy));
                if(cgrid[j * dimx + i] < 10000)
                    flag = true;
            }

            if(flag)
            {
                int l = cgrid[j * dimx + i];
                if(random(100F) < 50F)
                    l -= 90 + PApplet.toInt(random(-2F, 2.1F));
                else
                    l += 90 + PApplet.toInt(random(-2F, 2.1F));
                startCrack(i, j, l);
            }
        }

        public void startCrack(int i, int j, int k)
        {
            x = i;
            y = j;
            t = k;
            x += 0.61F * cos((t * 3.141593F) / 180F);
            y += 0.61F * sin((t * 3.141593F) / 180F);
        }

        public void move()
        {
            x += 0.42F * cos((t * 3.141593F) / 180F);
            y += 0.42F * sin((t * 3.141593F) / 180F);
            float f = 0.33F;
            int i = PApplet.toInt(x + random(-f, f));
            int j = PApplet.toInt(y + random(-f, f));
            regionColor();
            stroke(0.0F, 85F);
            point(x + random(-f, f), y + random(-f, f));
            if(i >= 0 && i < dimx && j >= 0 && j < dimy)
            {
                if(cgrid[j * dimx + i] > 10000 || abs((float)cgrid[j * dimx + i] - t) < (float)5)
                    cgrid[j * dimx + i] = PApplet.toInt(t);
                else
                if(abs((float)cgrid[j * dimx + i] - t) > 2.0F)
                {
                    findStart();
                    makeCrack();
                }
            } else
            {
                findStart();
                makeCrack();
            }
        }

        public void regionColor()
        {
            float f = x;
            float f1 = y;
            boolean flag = true;
            while(flag) 
            {
                f += 0.81F * sin((t * 3.141593F) / 180F);
                f1 -= 0.81F * cos((t * 3.141593F) / 180F);
                int i = PApplet.toInt(f);
                int j = PApplet.toInt(f1);
                if(i >= 0 && i < dimx && j >= 0 && j < dimy)
                {
                    if(cgrid[j * dimx + i] <= 10000)
                        flag = false;
                } else
                {
                    flag = false;
                }
            }
            sp.render(f, f1, x, y);
        }

        float x;
        float y;
        float t;
        SP sp;

        Cr()
        {
            findStart();
            sp =  new SP();
        }
    }

    class SP
    {

        public void render(float f, float f1, float f2, float f3)
        {
            g += random(-0.05F, 0.05F);
            float f4 = 1.0F;
            if(g < 0.0F)
                g = 0.0F;
            if(g > f4)
                g = f4;
            int i = 64;
            float f5 = g / (float)(i - 1);
            for(int j = 0; j < i; j++)
            {
                float f6 = 0.1F - (float)j / ((float)i * 10F);
                stroke(red(c), green(c), blue(c), f6 * 256F);
                point(f2 + (f - f2) * sin(sin((float)j * f5)), f3 + (f1 - f3) * sin(sin((float)j * f5)));
            }

        }

        int c;
        float g;

        SP()
        {
            c = somecolor();
            g = random(0.01F, 0.1F);
        }
    }


    public void setup()
    {
        size(dimx, dimy, "processing.core.PGraphics3");
        background(0);
        takecolor("pollockShimmering.gif");
        cgrid = new int[dimx * dimy];
        cracks = new Cr[maxnum];
        begin();
    }

    public void draw()
    {
        for(int i = 0; i < num; i++)
            cracks[i].move();

    }

    public void mousePressed()
    {
    }

    public void makeCrack()
    {
        if(num < maxnum)
        {
            cracks[num] = new Cr();
            num++;
        }
    }

    public void begin()
    {
        for(int i = 0; i < dimy; i++)
        {
            for(int l = 0; l < dimx; l++)
                cgrid[i * dimx + l] = 10001;

        }

        for(int j = 0; j < 16; j++)
        {
            int i1 = PApplet.toInt(random(dimx * dimy - 1));
            cgrid[i1] = PApplet.toInt(random(360F));
        }

        num = 0;
        for(int k = 0; k < 3; k++)
            makeCrack();

        background(0);
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

                if(!flag && numpal < maxpal)
                {
                    goodcolor[numpal] = k;
                    numpal++;
                }
            }

        }

    }


    public Sub(int ancho,int alto)
    {

        dimx = ancho;
        dimy = alto;
        num = 0;
        maxnum = 150;
        maxpal = 512;       
        numpal = 0;
        goodcolor = new int[maxpal];
    }

    int dimx;
    int dimy;
    int num;
    int maxnum;
    int cgrid[];
    Cr cracks[];
    int maxpal;
    int numpal;
    int goodcolor[];
    SP sands[];
}