package fractal;

import processing.core.PApplet;

public class Bubble extends PApplet
{
    class Axion
    {

        public void collide()
        {
            x = ancho / 2;
            y = alto / 2;
            theta = random(6.283185F);
            speed = random(1.0F, 6F);
            speedD = random(0.998F, 1.0F);
            thetaD = 0.0F;
            for(thetaDD = 0.0F; Bubble.abs(thetaDD) < 1E-005F; thetaDD = random(-0.001F, 0.001F));
        }

        public void move()
        {
            stroke(16F, 150F);
            point(x, y);
            for(int i = 1; i < 5; i++)
            {
                stroke(255F, 30 - i * 6);
                point(x, y - (float)i);
            }

            for(int j = 1; j < 5; j++)
            {
                stroke(0.0F, 30 - j * 6);
                point(x, y + (float)j);
            }

            x += vx;
            y += vy;
            vx = speed * sin(theta);
            vy = speed * cos(theta);
            theta += thetaD;
            thetaD += thetaDD;
            speed *= speedD;
            speedD *= 0.9999F;
            if(random(1000F) > 996F)
            {
                speed *= -1.0F;
                speedD = 2.0F - speedD;
                if(random(100F) > 30F)
                {
                    x = ancho / 2;
                    y = alto / 2;
                    collide();
                }
            }
        }

        float x;
        float y;
        float vx;
        float vy;
        float theta;
        float speed;
        float speedD;
        float thetaD;
        float thetaDD;

        Axion(float f, float f1)
        {
            x = f;
            y = f1;
        }
    }

    class Hadron
    {

        public void collide()
        {
            x = ancho / 2;
            y = alto / 2;
            theta = random(6.283185F);
            speed = random(0.5F, 3.5F);
            speedD = random(0.996F, 1.001F);
            thetaD = 0.0F;
            for(thetaDD = 0.0F; Bubble.abs(thetaDD) < 1E-005F; thetaDD = random(-0.001F, 0.001F));
            myc = 0xff00ff00;
        }

        public void move()
        {
            stroke(255F, 28F);
            point(x, y - 1.0F);
            stroke(0.0F, 28F);
            point(x, y + 1.0F);
            x += vx;
            y += vy;
            vx = speed * sin(theta);
            vy = speed * cos(theta);
            theta += thetaD;
            thetaD += thetaDD;
            speed *= speedD;
            if(random(1000F) > 997F)
            {
                speedD = 1.0F;
                thetaDD = 1E-005F;
                if(random(100F) > 70F)
                {
                    x = ancho / 2;
                    y = alto / 2;
                    collide();
                }
            }
            if(x < (float)(-ancho) || x > (float)(ancho * 2) || y < (float)(-alto) || y > (float)(alto * 2))
                collide();
        }

        float x;
        float y;
        float vx;
        float vy;
        float theta;
        float speed;
        float speedD;
        float thetaD;
        float thetaDD;
        int myc;

        Hadron(float f, float f1)
        {
            x = f;
            y = f1;
        }
    }

    class Quark
    {

        public void collide()
        {
            x = ancho / 2;
            y = alto / 2;
            theta = collisionTheta + random(-0.11F, 0.11F);
            speed = random(0.5F, 3);
            speedD = random(0.996F, 1.001F);
            thetaD = 0.0F;
            for(thetaDD = 0.0F; Bubble.abs(thetaDD) < 1E-005F; thetaDD = random(-0.001F, 0.001F));
        }

        public void move()
        {
            stroke(red(myc), green(myc), blue(myc), 32F);
            point(x, y);
            point((float)ancho - x, y);
            x += vx;
            y += vy;
            vx = speed * sin(theta);
            vy = speed * cos(theta);
            theta += thetaD;
            thetaD += thetaDD;
            speed *= speedD;
            if(random(1000F) > 997F)
            {
                speed *= -1.0F;
                speedD = 2.0F - speedD;
            }
            if(x < (float)(-ancho) || x > (float)(ancho * 2) || y < (float)(-alto) || y > (float)(alto * 2))
                collide();
        }

        private final void _mththis()
        {
            myc = 0xff000000;
        }

        float x;
        float y;
        float vx;
        float vy;
        float theta;
        float speed;
        float speedD;
        float thetaD;
        float thetaDD;
        int myc;

        Quark(float f, float f1)
        {
            _mththis();
            x = f;
            y = f1;
        }
    }

    class Muon
    {

        public void collide()
        {
            x = ancho / 2;
            y = alto / 2;
            speed = random(2.0F, 32F);
            speedD = random(0.0001F, 0.001F);
            theta = collisionTheta + random(-0.1F, 0.1F);
            thetaD = 0.0F;
            for(thetaDD = 0.0F; Bubble.abs(thetaDD) < 0.001F; thetaDD = random(-0.1F, 0.1F));
            int i = PApplet.toInt(((float)(goodcolor.length - 1) * (theta + 3.141593F)) / 6.283185F);
            if(i < goodcolor.length && i >= 0)
            {
                myc = goodcolor[i];
                mya = goodcolor[goodcolor.length - i - 1];
            }
        }

        public void move()
        {
            stroke(red(myc), green(myc), blue(myc), 42F);
            point(x, y);
            stroke(red(mya), green(mya), blue(mya), 42F);
            point((float)ancho - x, y);
            x += speed * sin(theta);
            y += speed * cos(theta);
            theta += thetaD;
            thetaD += thetaDD;
            speed -= speedD;
            if(x < (float)(-ancho) || x > (float)(ancho * 2) || y < (float)(-alto) || y > (float)(alto * 2))
                collide();
        }

        float x;
        float y;
        float speed;
        float theta;
        float speedD;
        float thetaD;
        float thetaDD;
        int myc;
        int mya;

        Muon(float f, float f1)
        {
            x = f;
            y = f1;
        }
    }


    public void setup()
    {
        size(ancho, alto, "processing.core.PGraphics3");
        background(0);
        noStroke();
        for(int i = 0; i < maxAxion; i++)
            axion[i] = new Axion(ancho / 2, alto / 2);

        for(int j = 0; j < maxHadron; j++)
            hadron[j] = new Hadron(ancho / 2, alto / 2);

        for(int k = 0; k < maxQuark; k++)
            quark[k] = new Quark(ancho / 2, alto / 2);

        for(int l = 0; l < maxMuon; l++)
            muon[l] = new Muon(ancho / 2, alto / 2);

        collideOne();
        
        if (modo==1)
          mousePressed();
        else
        {
          key=' ';
          keyReleased();
        }  
    }

    public void draw()
    {
        if(boom)
        {
            for(int i = 0; i < hadron.length; i++)
                hadron[i].move();

            for(int j = 0; j < muon.length; j++)
                muon[j].move();

            for(int k = 0; k < quark.length; k++)
                quark[k].move();

            for(int l = 0; l < axion.length; l++)
                axion[l].move();

        }
    }

    public void mousePressed()
    {
        boom = true;
        for(int i = 0; i < 11; i++)
            collideOne();

    }

    public void keyReleased()
    {
        if(key == ' ')
        {
            boom = true;
            background(0);
            collideAll();
        }
    }

    public int somecolor()
    {
        return goodcolor[PApplet.toInt(random(goodcolor.length))];
    }

    public void collideAll()
    {
        collisionTheta = random(6.283185F);
        if(hadron.length > 0)
        {
            for(int i = 0; i < maxHadron; i++)
                hadron[i].collide();

        }
        if(quark.length > 0)
        {
            for(int j = 0; j < maxQuark; j++)
                quark[j].collide();

        }
        if(muon.length > 0)
        {
            for(int k = 0; k < maxMuon; k++)
                muon[k].collide();

        }
        if(axion.length > 0)
        {
            for(int l = 0; l < maxAxion; l++)
                axion[l].collide();

        }
    }

    public void collideOne()
    {
        collisionTheta = atan2(ancho / 2 - mouseX, alto / 2 - mouseY);
        if(hadron.length > 0)
        {
            int i = PApplet.toInt(random(hadron.length));
            hadron[i].collide();
        }
        if(quark.length > 0)
        {
            int j = PApplet.toInt(random(quark.length));
            quark[j].collide();
        }
        if(muon.length > 0)
        {
            int k = PApplet.toInt(random(muon.length));
            muon[k].collide();
        }
    }


    public Bubble(int ancho,int alto, int modo)
    {
        this.ancho = ancho;
        this.alto = alto;
        this.modo = modo;
        maxMuon = 550;
        maxQuark = 339;
        maxHadron = 100;
        maxAxion = 11;
        boom = false;
        muon = new Muon[maxMuon];
        quark = new Quark[maxQuark];
        hadron = new Hadron[maxHadron];
        axion = new Axion[maxAxion];
        goodcolor = (new int[] {
            0xff3a242b, 0xff3b2426, 0xff352325, 0xff836454, 0xff7d5533, 0xff8b7352, 0xffb1a181, 0xffa4632e, 0xffbb6b33, 0xffb47249, 
            0xffca7239, 0xffd29057, 0xffe0b87e, 0xffd9b166, 0xfff5eabe, 0xfffcfadf, 0xffd9d1b0, 0xfffcfadf, 0xffd1d1ca, 0xffa7b1ac, 
            0xff879a8c, 0xff9186ad, 0xff776a8e
        });
    }

    int ancho,alto,modo;
    int maxMuon;
    int maxQuark;
    int maxHadron;
    int maxAxion;
    float collisionTheta;
    boolean boom;
    Muon muon[];
    Quark quark[];
    Hadron hadron[];
    Axion axion[];
    int goodcolor[];
}