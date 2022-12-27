/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items.objects;

import java.awt.Color;
import game.Difficulty;
import game.Game;
import player.Player;

/**
 *
 * @author Rajko
 */
public class Obstacle extends GameObject {

    public Obstacle(int x, int y) {
        super(x, y, new Color(0, 0, 0));
    }

    @Override
    public void reactionOnImpact(Player player, Game game) {
        if (player.getDifficulty() == Difficulty.HARDCORE) {
            game.gameOver();
        }
    }

}
