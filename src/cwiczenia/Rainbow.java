package cwiczenia;

import java.awt.*;

public class Rainbow extends Bow {
    Color[] rainbowColors = {
                (new Color(237,28,36)),
                (new Color(255,242,0)),
                (new Color(181,230,29)),
                (new Color(44,192,231)),
                (new Color(28,54,236)),
                (new Color(128,82,190))};
    private int width = 90;
    private int height = 100;
    int x, y;
    public Rainbow(int x, int y){
        this.x = x;
        this.y = y;

    }
    public void draw (Graphics g){
        for (int i = 0; i < rainbowColors.length ; i++) {
            new Bow().draw(g,x+(5*i),y +(5*i),width-(10*i),height-(10*i),rainbowColors[i]);
        }
    }
}
