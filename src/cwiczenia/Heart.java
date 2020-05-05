package cwiczenia;

import java.awt.*;

public class Heart  {
    final int width =60;
    final int height = 75;

    public void draw(Graphics g, int x, int y) {
        g.setColor(new Color(230, 90, 177));
        int[] triangleX = {
                x - 2*width/18,
                x + width + 2*width/18,
                (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {
                y + height - 2*height/3,
                y + height - 2*height/3,
                y + height };
        g.fillOval(
                x - width/12,
                y,
                width/2 + width/6,
                height/2);
        g.fillOval(
                x + width/2 - width/12,
                y,
                width/2 + width/6,
                height/2);
        g.fillPolygon(triangleX, triangleY, triangleX.length);
    }
}
