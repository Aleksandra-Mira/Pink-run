package cwiczenia;

import java.awt.*;

public class Person {
    final int height = 60;
    public void draw(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x,y,height,height);
    }
}
