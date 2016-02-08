package fractal;

import processing.core.PApplet;
import processing.core.PImage;

public class Box extends PApplet
{
    class B
    {

        public void selfinit()
        {
            okToDraw = false;
            x = PApplet.toInt((float)dimborder + random(dimx - dimborder * 2));
            y = PApplet.toInt((float)dimborder + random(dim - dimborder * 2));
            d = 0;
            myc = readBackground(x, y);
        }

        public void draw()
        {
            expand();
            if(okToDraw)
            {
                fill(myc);
                rect(x, y, d, d);
            }
        }

        public void expand()
        {
            d += 2;
            int i = 0;
            for(int j = PApplet.toInt(x - d / 2 - 1); j < PApplet.toInt(x + d / 2); j++)
            {
                int l = PApplet.toInt(y - d / 2 - 1);
                i += checkPixel(j, l);
                l = PApplet.toInt(y + d / 2);
                i += checkPixel(j, l);
            }

            for(int k = PApplet.toInt(y - d / 2 - 1); k < PApplet.toInt(y + d / 2); k++)
            {
                int i1 = PApplet.toInt(x - d / 2 - 1);
                i += checkPixel(i1, k);
                i1 = PApplet.toInt(x + d / 2);
                i += checkPixel(i1, k);
            }

            if(i > 0)
            {
                selfinit();
                if(chaste)
                {
                    makeNewBox();
                    chaste = false;
                }
            } else
            {
                okToDraw = true;
            }
        }

        public int checkPixel(int i, int j)
        {
            if((i > dimborder && i < dimx - dimborder || i == 0 || i == dimx - 1) && (j > dimborder && j < dim - dimborder || j == 0 || j == dim - 1))
            {
                int k = get(i, j);
                return red(k) + blue(k) + green(k) <= 0.0F ? 0 : 1;
            } else
            {
                return 0;
            }
        }



        int x;
        int y;
        int d;
        int myc;
        boolean okToDraw;
        boolean chaste;


        B()
        {
            chaste = true;
            selfinit();
        }
    }


    public void setup()
    {
        size(dimx, dim, "processing.core.PGraphics3");
        rectMode(3);
        framerate(60F);
        noStroke();
        boxes = new B[maxnum];
        resetAll();
    }

    public void draw()
    {
        for(int i = 0; i < num; i++)
            boxes[i].draw();

    }

    public void mousePressed()
    {
    }

    public void resetAll()
    {
        num = 0;
        background(0);
        makeNewBox();
        makeNewBox();
        makeNewBox();
    }

    public void makeNewBox()
    {
        if(num < maxnum)
        {
            boxes[num] = new B();
            num++;
        }
    }


    public int readBackground(int i, int j)
    {   
        return (int) (micolor+(Math.random()*30));
    }


    public Box(int ancho,int alto)
    {
        num = 0;
        maxnum = 2000;
        dimx = ancho;
        dim = alto;
        dimborder = 10;
        micolor=(int) (Math.random()*150+75);
    }
    
    
    int micolor;
    int num;
    int maxnum;
    int dimx;
    int dim;
    int dimborder;
    int time;
    B boxes[];
}