/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items.objects;

import game.Difficulty;
import game.Game;
import java.awt.Color;
import player.Player;

/**
 *
 * @author Rajko
 */
public class BlackHole extends GameObject {

    public BlackHole(int x, int y) {
        super(x, y, new Color(50, 0, 50));
    }

    @Override
    public void reactionOnImpact(Player player, Game game) {
        if (player.getDifficulty() != Difficulty.EASY) {
            player.cutSnakeHead();
        }
    }
    
}
