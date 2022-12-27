/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items.food;

import java.awt.Color;
import player.Player;

/**
 *
 * @author Rajko
 */
public class Poison extends Food {

    
    public Poison(int x, int y) {
        super(x, y, new Color(30, 100, 50));
    }

    @Override
    public void eat(Player player) {
        player.cutSnakeHead();
        player.slowDown();
        player.changeDirection();
        player.raiseScore(-50);
    }
    
}
