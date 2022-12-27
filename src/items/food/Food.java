package items.food;

import graphics.Display;
import items.DrawableObject;
import java.awt.Color;
import player.Player;

public abstract class Food extends DrawableObject {    
        
    public Food(int x, int y, Color color) {
        super(x, y, color);
    }
    
    public abstract void eat(Player player);
    
    public void hide() {
        Display.getInstance().turnOff(this.getX(), this.getY());
    }
    
}
