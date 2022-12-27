package player;

import game.Difficulty;
import game.Game;
import items.ItemGenerator;
import map.Direction;
import map.Map;

public class Player {

    private int score;
    private int speed; //Cim menej tym rychlejsie sa bude hybat 
    private String name;
    private Snake snake;
    private Difficulty difficulty;
    private boolean canGrow;
    private ItemGenerator generator;
    private Game game;

    public Player(Map map, ItemGenerator generator, Game game, String name, Difficulty difficulty) { //NOTE: TODO name, color
        this.generator = generator;
        this.name = name;
        this.game = game;
        this.snake = new Snake(map, this, this.generator, game);
        this.speed = 25;//0;
        this.canGrow = true;
        this.difficulty = difficulty;
    }

    public Snake getSnakeCopy() {
        return this.snake.getSnakeCopy();
    }

    public boolean changeDirection(Direction direction) {
        return this.snake.changeDirection(direction);
    }
    
    public void changeDirection() {
        this.snake.changeDirection();
    }

    public int getPlayerSpeed() {
        return this.speed;
    }

    public void move() { //NOTE: TODO inverse ovladania
        this.snake.move();
    }

    public void grow() {
        if (this.canGrow) {
            this.snake.grow(); //todo nejaky grow modifier
        }
    }

    public void speedUp() {
        if (this.speed > 4) {
            this.speed -= 3;
        } else {
            this.speed = 1;
        }
    }

    public void slowDown() {
        if (this.speed < 50) {
            this.speed += 3;
        } else {
            this.speed = 50;
        }
    }

    public int getScore() {
        return this.score;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void disableGrowth() {
        this.canGrow = false;
    }

    public void enableGrowth() {
        this.canGrow = true;
    }

    public boolean canGrow() {
        return this.canGrow;
    }

    public boolean canDestroyItems() {
        if (this.snake.getLength() > 20 && this.difficulty == Difficulty.HARDCORE) {
            return true;
        }
        return false;
    }

    public int snakeLength() {
        return this.snake.getLength();
    }

    public void cutSnakeHead() {
        if (this.snake.getLength() > 3) {
            this.snake.cutHead();
        }
        this.score -= 10;
    }
    
    /**
     * Zmeni skore. Ano berie aj zaporne hodnoty.
     * @param score
     */
    public void raiseScore(int score) {
        this.score += score;
    }

    public String getName() {
        return this.name;
    }
}

/*Zname bugy pri prilisne rychlom otoceni hadika sa odkusne cely
tak ze ostane iba hlava a chvost.

Ono to je ked mas sme hore napriklad ale este sa tam neposunulo telo
tak ti moze hodit uplne opacny smer, chyba v eat
Fix - Did move in direction

Poison niekedy vrati opacny smer takze sa hadik sam zje tak ze mu
ostane iba hlava a chvost*/
