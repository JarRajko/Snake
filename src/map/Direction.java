
package map;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
    
    public static Direction getOppositeDirection(Direction direction) {
        if (direction == Direction.DOWN) {
            return Direction.UP;
        } else if (direction == Direction.UP) {
            return Direction.DOWN;
        }  else if (direction == Direction.LEFT) {
            return Direction.RIGHT;
        }  else {
            return Direction.LEFT;
        }
    }
    
    public static Direction getRandomDirection() {
        Random rand = new Random();
        return Direction.values()[rand.nextInt(Direction.values().length)];
    }
}
