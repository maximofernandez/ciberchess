package fractal;

import processing.core.PApplet;

public class BinaryRing extends PApplet
{


    public void setup()
    {
        size(dimAncho, dimAlto, "processing.core.PGraphics3");
        background(0);
        framerate(30F);
        kaons = new Particle[num];
        for(int i = 0; i < num; i++)
        {
            int j = PApplet.toInt(30F * sin((6.283185F * (float)i) / (float)num) + (float)(dimAncho / 2));
            int k = PApplet.toInt(30F * cos((6.283185F * (float)i) / (float)num) + (float)(dimAlto / 2));
            float f = (3.141593F * (float)i) / (float)num;
            kaons[i] = new Particle(j, k, f);
        }

    }

    public void draw()
    {
        for(int i = 0; i < num; i++)
            kaons[i].move();

        if(random(10000F) > 9950F)
            if(blackout)
                blackout = false;
            else
                blackout = true;
    }

    public void mousePressed()
    {
        if(blackout)
            blackout = false;
        else
            blackout = true;
    }


    public BinaryRing(int ancho, int alto)
    {
        num = 5000;
        dimAncho = ancho;
        dimAlto = alto;
        blackout = false;
    }

    int num;
    int dimAncho;
    int dimAlto;
    boolean blackout;
    Particle kaons[];
    
    
    
    
class Particle
{

    public void move()
    {
        xx = x;
        yy = y;
        x += vx;
        y += vy;
        vx += (random(100F) - random(100F)) * 0.005F;
        vy += (random(100F) - random(100F)) * 0.005F;
        stroke(red(i), green(i), blue(i), 24F);
        line(ox + xx, oy + yy, ox + x, oy + y);
        line(ox - xx, oy + yy, ox - x, oy + y);
        age++;
        if(age > 200)
        {
            float f = random(6.283185F);
            x = 30F * sin(f);
            y = 30F * cos(f);
            xx = 0.0F;
            yy = 0.0F;
            vx = 0.0F;
            vy = 0.0F;
            age = 0;
            if(blackout)
                i = 0xff000000;
            else
                i = -1;
        }
    }

    private final void _mththis()
    {
        age = PApplet.toInt(random(200F));
    }

    float ox;
    float oy;
    float x;
    float y;
    float xx;
    float yy;
    float vx;
    float vy;
    int age;
    int i;

    public Particle( int j, int k, float f)
    {
        _mththis();
        ox = dimAncho / 2;
        oy = dimAlto / 2;
        x = PApplet.toInt(ox - (float)j);
        y = PApplet.toInt(oy - (float)k);
        xx = 0.0F;
        yy = 0.0F;
        vx = 2.0F * cos(f);
        vy = 2.0F * sin(f);
        if(blackout)
            i = 0xff000000;
        else
            i = -1;
    }


}    
    
    
    
    
}