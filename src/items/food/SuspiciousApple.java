
package items.food;

import java.awt.Color;
import java.util.Random;
import player.Player;

/**
 * SuspiciousApple spomali hadika
 * @author 
 */
public class SuspiciousApple extends Food {

    public SuspiciousApple(int x, int y) {
        super(x, y, new Color(255, 0, 100));
        Apple a = new Apple(0,0); //toto je tu na zistenie farby jablka
        
        Random rand = new Random();
    }

    @Override
    public void eat(Player player) {
        player.slowDown();
    }
    
}
