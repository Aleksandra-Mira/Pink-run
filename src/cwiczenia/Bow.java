package cwiczenia;

import java.awt.*;

public class Bow {
    final int startAngle = 0;
    final int arcAngle = 180;
    public void draw(Graphics g, int x, int y, int width, int height, Color color){
        g.setColor(color);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(5));
        g2d.drawArc(x,y,width,height,startAngle,arcAngle);
    }
}
