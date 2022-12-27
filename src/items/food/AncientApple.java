/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items.food;

import graphics.Display;
import java.awt.Color;
import player.Player;

/**
 * Zmeni stav rastu hraca
 * @author 
 */
public class AncientApple extends Food {

    /**
     * Po zjedeni hrac strati schopnost rast
     * po druhom zjedeni ju opat nadobudne
     * @param x horizontalna suradnica
     * @param y vertikalna suradnica
     */
    public AncientApple(int x, int y) {
        super(x, y, new Color(150, 10, 35));
    }

    
    @Override
    public void eat(Player player) {
        if (player.canGrow()) {
            player.disableGrowth();
            player.raiseScore(100);
        } else {
            player.enableGrowth();
        }
        Display.getInstance().changeBackgroundColorSoPlayerKnowHeCannotGrowWhileEatingApples();
    }
    
}
