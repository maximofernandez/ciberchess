package fractal;
import processing.core.PApplet;

public class St extends PApplet
{
    class Bit
    {

        public void awaken()
        {
            state = 1;
        }

        public void draw()
        {
            if(state > 0)
            {
                int i = getX();
                int j = getY();
                stroke(red(myc), green(myc), blue(myc), 100F);
                point(i, j);
                float f = distc / 2;
                for(int k = PApplet.toInt(-f); k < PApplet.toInt(f); k++)
                {
                    float f1 = 0.2F - 0.19F * abs((float)k / f);
                    stroke(red(myc), green(myc), blue(myc), f1 * 256F);
                    point(i, j + k);
                    point(i + k, j);
                }

                stroke(0.0F, 44F);
                point(i - 1, j - 1);
                stroke(255F, 44F);
                point(i + 11, j - 1);
                x += PApplet.toInt(floor(random(-100F, 199F) / 100F));
                y += PApplet.toInt(floor(random(-100F, 199F) / 100F));
                age++;
                if(x < 0 || x >= dim || age > maxage)
                {
                    x = ox;
                    age = 0;
                }
                if(y < 0 || y >= dim || age > maxage)
                {
                    y = oy;
                    age = 0;
                }
                if(random(10000F) > 9600F)
                    state += PApplet.toInt(random(4));
            }
        }

        public int getState()
        {
            return state;
        }

        public int getX()
        {
            return PApplet.toInt(x * distc + state % distc);
        }

        public int getY()
        {
            return PApplet.toInt(y * distc + state / distc);
        }

        public void setState(int i)
        {
            state = i;
        }

        int id;
        int x;
        int y;
        int ox;
        int oy;
        int state;
        int age;
        int myc;

        Bit(int i)
        {
            id = i;
            x = PApplet.toInt(random(dim));
            y = PApplet.toInt(random(dim));
            ox = x;
            oy = y;
            int j = PApplet.toInt(floor((goodcolor.length * y) / dim));
            myc = goodcolor[j];
            state = 0;
        }
    }


    public void setup()
    {
        size(dimAncho, dimAlto, "processing.core.PGraphics3");
        background(0);
        for(int i = 0; i < num; i++)
        {
            bit[i] = new Bit(i);
            bit[i].awaken();
        }

    }

    public void draw()
    {
        if(drawing)
        {
            for(int i = 0; i < num; i++)
                bit[i].draw();

        }
    }


    public int somecolor()
    {
        return goodcolor[PApplet.toInt(random(goodcolor.length))];
    }


    public St(int ancho,int alto)
    {
    	dimAncho=ancho;
    	dimAlto=alto;
        dim = ancho/20;
        distc = 20;
        num = 128;
        maxage = 100;
        bit = new Bit[num];
        drawing = true;
        goodcolor = (new int[] {
            0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff6b6556, 0xffa09c84, 0xff908b7c, 
            0xff79746e, 0xff755d35, 0xff937343, 0xff9c6b4b, 0xffab8259, 0xffaa8a61, 0xff578375, 0xfff0f6f2, 0xffd0e0e5, 0xffd7e5ec, 
            0xffd3dfea, 0xffc2d7e7, 0xffa5c6e3, 0xffa6cbe6, 0xffadcbe5, 0xff77839d, 0xffd9d9b9, 0xffa9a978, 0xff727b5b, 0xff6b7c4b, 
            0xff546d3e, 0xff47472e, 0xff727b52, 0xff898a6a, 0xff919272, 0xffac623b, 0xffcb6a33, 0xff9d5c30, 0xff843f2b, 0xff652c2a, 
            0xff7e372b, 0xff403229, 0xff47392b, 0xff3d2626, 0xff362c26, 0xff57392c, 0xff998a72, 0xff864d36, 0xff544732
        });
    }

    int dim,dimAncho,dimAlto;
    int distc;
    int num;
    int maxage;
    Bit bit[];
    boolean drawing;
    int goodcolor[];
}