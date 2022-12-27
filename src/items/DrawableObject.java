package items;

import graphics.Display;
import java.awt.Color;

public abstract class DrawableObject {
    private int x;
    private int y;
    private Color color;
    
    public DrawableObject(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Color getColor() {
        return this.color;
    }
    
    public void changeColor(Color color) {
        this.color = color;
        Display.getInstance().turnOff(this.x, this.y);
        Display.getInstance().turnOn(this.x, this.y, this.color);
    }
    
    
}
