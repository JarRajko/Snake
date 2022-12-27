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
 * @author 
 */
public class PineApple extends Food {

    public PineApple(int x, int y) {
        super(x, y, new Color(250, 190, 0));
    }

    @Override
    public void eat(Player player) {
        player.speedUp();
        player.raiseScore(10);
    }
    
}
