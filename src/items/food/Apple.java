package items.food;

import java.awt.Color;
import player.Player;


public class Apple extends Food {
    
    public Apple(int x, int y) {
        super(x, y, new Color(255, 0, 0));
    }

    @Override
    public void eat(Player player) {
        player.grow();
        player.raiseScore(5);
    }
    
}
