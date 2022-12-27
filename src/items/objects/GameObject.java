/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items.objects;

import game.Game;
import items.DrawableObject;
import java.awt.Color;
import player.Player;

/**
 *
 * @author 
 */
public abstract class GameObject extends DrawableObject {
    
    public GameObject(int x, int y, Color color) {
        super(x, y, color);
    }
    
    public abstract void reactionOnImpact(Player player, Game game);
    
}
