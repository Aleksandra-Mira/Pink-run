package cwiczenia;

import java.awt.*;

public class Glitter {
    private Star s1 = new Star(1);
    private Star s2 = new Star(2);
    private Star s3 = new Star(3);
    private int x;
    private int y;
    public Glitter (int x, int y){
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g)
    {
        s1.draw(g,x,y);
        s2.draw(g,x - 40,y - 50);
        s3.draw(g, x-25,y+ 25);
    }
}
