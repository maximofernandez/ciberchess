package fractal;

import processing.core.PApplet;

public class CityTraveler extends PApplet
{
    class City
    {

        public void draw()
        {
            stroke(0.0F, 220F);
            point(x, y);
        }

        public void move()
        {
            vx += (cities[friend].x - x) / 2500F;
            vy += (cities[friend].y - y) / 2500F;
            vx *= 0.96F;
            vy *= 0.96F;
            x += vx;
            y += vy;
            draw();
            if(random(10000F) > 9990F)
                friend = PApplet.toInt(random(num));
        }

        private final void _mththis()
        {
            population = 5;
            myc = somecolor();
        }

        float x;
        float y;
        int friend;
        float vx;
        float vy;
        int population;
        int myc;

        City(float f, float f1)
        {
            _mththis();
            x = f;
            y = f1;
            friend = PApplet.toInt(random(num));
        }
    }


    public void setup()
    {
        size(ancho, alto, "processing.core.PGraphics3");
        background(0);
        cities = new City[maxnum];
        int i = 0;
        float f = (float)alto / sqrt(num);
        for(int j = 0; (float)j < sqrt(num); j++)
        {
            for(int k = 0; (float)k < sqrt(num); k++)
            {
                cities[i] = new City((float)j * f + f / 2.0F, (float)k * f + f / 2.0F);
                cities[i].draw();
                i++;
            }

        }

    }

    public void draw()
    {
        for(int i = 0; i < numtravelers; i++)
        {
            a = PApplet.toInt(random(num - 1));
            for(b = PApplet.toInt(random(num - 1)); citydistance(a, b) > (float)mind; b = PApplet.toInt(random(num - 1)));
            t = random(3.141593F);
            float f = sin(t) * (cities[b].x - cities[a].x) + cities[a].x;
            float f1 = sin(t) * (cities[b].y - cities[a].y) + cities[a].y;
            if(random(1000F) > 990F)
            {
                f += random(-1.5F, 1.5F);
                f1 += random(-1.5F, 1.5F);
            }
            stroke(red(cities[b].myc), green(cities[b].myc), blue(cities[b].myc), 51F);
            point(f, f1);
            stroke(red(cities[a].myc), green(cities[a].myc), blue(cities[a].myc), 51F);
            point(f, f1);
        }

        for(int j = 0; j < num; j++)
            cities[j].move();

    }

    public float citydistance(int i, int j)
    {
        if(i != j)
        {
            float f = cities[j].x - cities[i].x;
            float f1 = cities[j].y - cities[i].y;
            float f2 = sqrt(f * f + f1 * f1);
            return f2;
        } else
        {
            return 0.0F;
        }
    }

    public int somecolor()
    {
        return goodcolor[PApplet.toInt(random(goodcolor.length))];
    }


    public CityTraveler(int ancho,int alto)
    {
        num = 64;
        maxnum = num * num + 1;
        numtravelers = 5000;
        this.ancho = ancho;
        this.alto = alto;
        mind = 200;
        goodcolor = (new int[] {
            0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 
            0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff000000, 0xff6b6556, 0xffa09c84, 
            0xff908b7c, 0xff79746e, 0xff755d35, 0xff937343, 0xff9c6b4b, 0xffab8259, 0xffaa8a61, 0xff578375, 0xfff0f6f2, 0xffd0e0e5, 
            0xffd7e5ec, 0xffd3dfea, 0xffc2d7e7, 0xffa5c6e3, 0xffa6cbe6, 0xffadcbe5, 0xff77839d, 0xffd9d9b9, 0xffa9a978, 0xff727b5b, 
            0xff6b7c4b, 0xff546d3e, 0xff47472e, 0xff727b52, 0xff898a6a, 0xff919272, 0xffac623b, 0xffcb6a33, 0xff9d5c30, 0xff843f2b, 
            0xff652c2a, 0xff7e372b, 0xff403229, 0xff47392b, 0xff3d2626, 0xff362c26, 0xff57392c, 0xff998a72, 0xff864d36, 0xff544732
        });
    }

    int a;
    int b;
    float t;
    int num;
    int maxnum;
    int numtravelers;
    int ancho,alto;
    int mind;
    City cities[];
    int goodcolor[];
    
    
    
}