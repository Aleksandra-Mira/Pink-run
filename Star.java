package cwiczenia;

import java.awt.*;

public class Star {
    private Color color = new Color(255,255,0);
    private int size;

    public Star(int size){
        this.size = size;
    }
    public void draw(Graphics g, int x, int y){
        int nPoints = 8;
        int[] xPoints = { x + (15 / size) , x + (30 / size) , x + (60 / size), x + (30 / size) ,
                x + (15 / size) , x , x - (30 / size) , x };
        int[] yPoints = { y + (30 / size) , y  , y - (15 / size), y - (30 / size) ,
                y - (60 / size) , y - (30 / size) , y - (15 / size) , y };
        g.setColor(color);
        g.fillPolygon(xPoints,yPoints,nPoints);
    }
}
