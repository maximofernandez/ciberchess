package fractal;
import processing.core.PApplet;
import processing.core.PImage;

public class Hap extends PApplet
{
    class F
    {

        public void expose()
        {
            for(int i = -2; i < 3; i++)
            {
                float f = 0.5F - (float)abs(i) / (float)5;
                stroke(0.0F, 256F * f);
                point(x + (float)i, y);
                stroke(255F, 256F * f);
                point((x + (float)i) - 1.0F, y - 1.0F);
            }

            for(int j = -2; j < 3; j++)
            {
                float f1 = 0.5F - (float)abs(j) / (float)5;
                stroke(0.0F, 256F * f1);
                point(x, y + (float)j);
                stroke(255F, 256F * f1);
                point(x - 1.0F, (y + (float)j) - 1.0F);
            }

        }

        public void exposeConnections()
        {
            for(int i = 0; i < numcon; i++)
            {
                float f = friends[connections[i]].x;
                float f1 = friends[connections[i]].y;
                for(int j = 0; j < numsands; j++)
                    sands[j].render(x, y, f, f1);

            }

        }

        public void render()
        {
            for(int i = PApplet.toInt(x - (float)numcon); i < PApplet.toInt(x + (float)numcon); i++)
            {
                for(int j = PApplet.toInt(y - (float)numcon); j < PApplet.toInt(y + (float)numcon); j++)
                {
                    stroke(myc);
                    point(i, j);
                }

            }

        }

        public void renderConnections()
        {
            for(int i = 0; i < numcon; i++)
            {
                float f = friends[connections[i]].x - x;
                float f1 = friends[connections[i]].y - y;
                int j = PApplet.toInt(1.0F + sqrt(f * f + f1 * f1) / 6F);
                for(int k = 0; k < j; k++)
                {
                    float f2 = (1.0F + cos(((float)k * 3.141593F) / (float)j)) / 2.0F;
                    int l = PApplet.toInt(x + f2 * f);
                    int i1 = PApplet.toInt(y + f2 * f1);
                    stroke(0xff333333);
                    point(l, i1);
                }

            }

        }

        public void move()
        {
            x += vx;
            y += vy;
            vx *= 0.92F;
            vy *= 0.92F;
        }

        public void connectTo(int i)
        {
            if(numcon < maxcon && !friendOf(i))
            {
                connections[numcon] = i;
                numcon++;
            }
        }

        public boolean friendOf(int i)
        {
            boolean flag = false;
            for(int j = 0; j < numcon; j++)
                if(connections[j] == i)
                    flag = true;

            return flag;
        }

        public void findHappyPlace()
        {
            float f = 0.0F;
            float f1 = 0.0F;
            for(int i = 0; i < num; i++)
                if(friends[i] != this)
                {
                    float f2 = friends[i].x - x;
                    float f3 = friends[i].y - y;
                    float f4 = sqrt(f2 * f2 + f3 * f3);
                    float f5 = atan2(f3, f2);
                    boolean flag = false;
                    for(int j = 0; j < numcon; j++)
                        if(connections[j] == i)
                            flag = true;

                    if(flag)
                    {
                        if(f4 > (float)lencon)
                        {
                            f += (float)4 * cos(f5);
                            f1 += (float)4 * sin(f5);
                        }
                    } else
                    if(f4 < (float)lencon)
                    {
                        f += ((float)lencon - f4) * cos(f5 + 3.141593F);
                        f1 += ((float)lencon - f4) * sin(f5 + 3.141593F);
                    }
                }

            vx += f / 42.22F;
            vy += f1 / 42.22F;
        }

        private final void _mththis()
        {
            maxcon = 10;
            lencon = 10 + PApplet.toInt(random(50F));
            connections = new int[maxcon];
            numsands = 3;
            sands = new SandPainter[numsands];
            myc = somecolor();
        }

        float x;
        float y;
        float dx;
        float dy;
        float vx;
        float vy;
        int id;
        int numcon;
        int maxcon;
        int lencon;
        int connections[];
        int numsands;
        SandPainter sands[];
        int myc;

        F(float f, float f1, int i)
        {
            _mththis();
            dx = x = f;
            dy = y = f1;
            id = i;
            numcon = 0;
            for(int j = 0; j < numsands; j++)
                sands[j] =  new SandPainter();

        }
    }

    class SandPainter
    {

        public void render(float f, float f1, float f2, float f3)
        {
            stroke(red(c), green(c), blue(c), 28F);
            point(f2 + (f - f2) * sin(p), f3 + (f1 - f3) * sin(p));
            g += random(-0.05F, 0.05F);
            float f4 = 0.22F;
            if(g < -f4)
                g = -f4;
            if(g > f4)
                g = f4;
            float f5 = g / 10F;
            for(int i = 0; i < 11; i++)
            {
                float f6 = 0.1F - (float)(i / 110);
                stroke(red(c), green(c), blue(c), 256F * f6);
                point(f2 + (f - f2) * sin(p + sin((float)i * f5)), f3 + (f1 - f3) * sin(p + sin((float)i * f5)));
                point(f2 + (f - f2) * sin(p - sin((float)i * f5)), f3 + (f1 - f3) * sin(p - sin((float)i * f5)));
            }

        }

        float p;
        int c;
        float g;

        SandPainter()
        {
            p = random(1.0F);
            c = somecolor();
            g = random(0.01F, 0.1F);
        }
    }


    public void setup()
    {
        size(dimAncho, dimAlto, "processing.core.PGraphics3");
        takecolor("longStickhorse.gif");
        background(0);
        framerate(50F);
        friends = new F[num];
        resetAll();
    }

    public void draw()
    {
        for(int i = 0; i < num; i++)
            friends[i].move();

        for(int j = 0; j < num; j++)
        {
            friends[j].expose();
            friends[j].exposeConnections();
        }

        if(time % 2 == 0)
        {
            for(int k = 0; k < num; k++)
                friends[k].findHappyPlace();

        }
        time++;
    }


    public void resetAll()
    {
        for(int i = 0; i < num; i++)
        {
            float f = (float)(dimAncho / 2) + 0.4F * (float)dimAncho * cos((6.283185F * (float)i) / (float)num);
            float f1 = (float)(dimAlto / 2) + 0.4F * (float)dimAlto * sin((6.283185F * (float)i) / (float)num);
            friends[i] = new F(f, f1, i);
        }

        for(int j = 0; (float)j < (float)num * 2.2F; j++)
        {
            int k = PApplet.toInt(floor(random(num)));
            int l = PApplet.toInt(floor((float)k + random(22F)) % (float)num);
            if(l >= num)
            {
                l = 0;
                print("+");
            } else
            if(l < 0)
            {
                l = 0;
                print("+");
            }
            if(k != l)
            {
                friends[k].connectTo(l);
                friends[l].connectTo(k);
            }
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
            for(int k = 0; k < pimage.height; k++)
            {
                int l = get(i, k);
                boolean flag = false;
                for(int i1 = 0; i1 < numpal; i1++)
                {
                    if(l != goodcolor[i1])
                        continue;
                    flag = true;
                    break;
                }

                if(!flag && numpal < maxpal)
                {
                    goodcolor[numpal] = l;
                    numpal++;
                }
            }

        }

        for(int j = 0; j < 22; j++)
        {
            goodcolor[numpal] = 0xff000000;
            numpal++;
            goodcolor[numpal] = -1;
            numpal++;
        }

    }


    public Hap(int ancho,int alto)
    {
    	dimAncho=ancho;
    	dimAlto=alto;
        dim = 500;
        num = 64;
        time = 0;
        maxpal = 512;
        numpal = 0;
        goodcolor = new int[maxpal];
    }

    int dim,dimAncho,dimAlto;
    int num;
    int time;
    F friends[];
    int maxpal;
    int numpal;
    int goodcolor[];
}