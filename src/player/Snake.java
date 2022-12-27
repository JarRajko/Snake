package player;

import game.Difficulty;
import game.Game;
import items.DrawableObject;
import items.ItemGenerator;
import items.food.Food;
import items.objects.GameObject;
import java.awt.Color;
import java.util.ArrayList;
import map.Direction;
import map.Map;
import map.OutOfMapException;

public class Snake {

    private ArrayList<SnakeSegment> segments;
    private SnakeSegment tail;
    private SnakeSegment head;
    private Direction currentDirection;
    //premenna pre pocitanie segmentov
    //ulahci hladanie chvostu
    private int segmentCounter = 3;
    private final Map map;
    private final Player player;
    private boolean movedInThisDirection = false;
    private final ItemGenerator generator;
    private Game game;
    
    private Color SNAKE_HEAD = new Color(0, 255, 100);
    private Color SNAKE_BODY = new Color(0, 200, 100);
    private Color SNAKE_TAIL = new Color(0, 150, 100);

    public Snake(Map map, Player player, ItemGenerator generator, Game game) {
        this.segments = new ArrayList<>();
        this.map = map;
        this.player = player;
        this.game = game;
        this.generator = generator;
        this.tail = new SnakeSegment(0, 0, this.SNAKE_TAIL, 0);
        SnakeSegment body = new SnakeSegment(1, 0, this.SNAKE_BODY, 1);
        this.head = new SnakeSegment(2, 0, this.SNAKE_HEAD, 2);
        this.currentDirection = Direction.RIGHT;

        //----SNAKE INIT--------------------------------------------------------
        this.segments.add(this.head);
        this.segments.add(body);
        this.segments.add(this.tail);

        this.map.addToMap(this.head, this.head.getX(), this.head.getY());
        this.map.addToMap(body, body.getX(), body.getY());
        this.map.addToMap(this.tail, this.tail.getX(), this.tail.getY());
    }

    public boolean move() {
        DrawableObject nextBlock = null;
        int xL = 0;
        int yL = 0;
        try {
            switch (this.currentDirection) {
                case DOWN:
                    nextBlock = this.map.getFromMap(this.head.getX(), this.head.getY() + 1);
                    xL = this.head.getX();
                    yL = this.head.getY() + 1;
                    break;
                case UP:
                    nextBlock = this.map.getFromMap(this.head.getX(), this.head.getY() - 1);
                    xL = this.head.getX();
                    yL = this.head.getY() - 1;
                    break;
                case LEFT:
                    nextBlock = this.map.getFromMap(this.head.getX() - 1, this.head.getY());
                    xL = this.head.getX() - 1;
                    yL = this.head.getY();
                    break;
                case RIGHT:
                    nextBlock = this.map.getFromMap(this.head.getX() + 1, this.head.getY());
                    xL = this.head.getX() + 1;
                    yL = this.head.getY();
                    break;
                default:
                    throw new AssertionError();
            }

            if (nextBlock == null) { //ak je pred hadikom prazdne policko
                this.deleteSegment(this.tail);
                this.tail = this.findTail();
                this.tail.changeColor(this.SNAKE_TAIL);

                this.head.changeColor(this.SNAKE_BODY);
                this.head = new SnakeSegment(xL, yL, this.SNAKE_HEAD, this.segmentCounter++);
                this.segments.add(this.head);
                this.map.addToMap(this.head, xL, yL);
                this.movedInThisDirection = true;
                return true;
            } else { //ak je pred hadikom nejaky objekt
                if (this.reactToObject(nextBlock)) {
                    this.movedInThisDirection = true; 
                    return true;
                } else {
                    return false;
                }
            }
        } catch (OutOfMapException e) { //hadik sa pokusa pohnut mimo mapku
            return false;
        }
    }

    public void grow() {
        this.head.changeColor(this.SNAKE_BODY);
        SnakeSegment newHead = null;
        switch (this.currentDirection) {
            case DOWN:
                newHead = new SnakeSegment(this.head.getX(), this.head.getY() + 1, this.SNAKE_HEAD, this.segmentCounter++);
                break;
            case UP:
                newHead = new SnakeSegment(this.head.getX(), this.head.getY() - 1, this.SNAKE_HEAD, this.segmentCounter++);
                break;
            case RIGHT:
                newHead = new SnakeSegment(this.head.getX() + 1, this.head.getY(), this.SNAKE_HEAD, this.segmentCounter++);
                break;
            case LEFT:
                newHead = new SnakeSegment(this.head.getX() - 1, this.head.getY(), this.SNAKE_HEAD, this.segmentCounter++);
                break;
            default:
                throw new AssertionError();
        }
        this.head = newHead;
        this.segments.add(this.head);
        this.map.addToMap(this.head, this.head.getX(), this.head.getY());
    }

    /**
     * Zmeni smer hadika podla zadaneho argumentu
     *
     * @param direction smer ktorym by sa mal hadik uberat
     * @return sprava o tom ci sa podarilo zmenit smer
     */
    public boolean changeDirection(Direction direction) {
        if (direction != Direction.getOppositeDirection(this.currentDirection) && this.movedInThisDirection) {
            this.currentDirection = direction;
            this.movedInThisDirection = false;
            return true;
        }
        return false;
    }

    /**
     * Zmeni smer hadika nedobrovolne
     */
    public void changeDirection() {
        while (true) {
            Direction randomDirection = Direction.getRandomDirection();

            if (randomDirection != this.currentDirection && randomDirection != Direction.getOppositeDirection(this.currentDirection)) {
                this.currentDirection = randomDirection;
                this.movedInThisDirection = false;
                break;
            }
        }
    }

    /**
     * Metoda vracia zapuzdrenu kopiu hlavy
     *
     * @return kopia hlavy
     */
    public SnakeSegment getHead() {
        return this.head.getCopyOfSegment();
    }

    /**
     * Metoda vracia zapuzdrenu kopiu hada
     *
     * @return Kopiu hada
     */
    public Snake getSnakeCopy() {
        Snake copy = new Snake(this.map, this.player, this.generator, this.game);
        copy.setCurrentDirection(this.currentDirection);
        copy.setHead(this.head.getCopyOfSegment());
        copy.setTail(this.tail.getCopyOfSegment());
        copy.setSegments(new ArrayList<SnakeSegment>(this.segments));
        return copy;
    }

    /**
     * Vracia zapuzdrenu kopiu arraylistu
     *
     * @return segmenty hada
     */
    public ArrayList<SnakeSegment> getSnakeSegments() {
        return new ArrayList<>(this.segments);
    }

    public void cutHead() {
        this.deleteSegment(this.head);
        this.head = this.findHead();
        this.head.changeColor(this.SNAKE_HEAD);
    }

    public int getLength() {
        return this.segments.size();
    }
    
    //------------Private settery pre dodrzanie zapuzdrenia---------------------
    private void setSegments(ArrayList<SnakeSegment> segments) {
        this.segments = segments;
    }

    private void setTail(SnakeSegment tail) {
        this.tail = tail;
    }

    private void setHead(SnakeSegment head) {
        this.head = head;
    }

    private void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
    //--------------------------------------------------------------------------

    private void deleteSegment(SnakeSegment segment) {
        this.map.removeFromMap(segment.getY(), segment.getX());
        this.segments.remove(segment);
    }

    /**
     * Vrati clanok hadika s najmensim cislom, pri jedeni sa pridavaju segmenty
     * a maze chvost, takze posledny clanok ma vzdy najmensie cislo a to je
     * chvost.
     *
     * @return Posledny clanok hadika, chvost.
     */
    private SnakeSegment findTail() {
        int min = Integer.MAX_VALUE;
        SnakeSegment s = null;

        for (int i = 0; i < this.segments.size(); i++) { //"for each" prejde cez vsetky segmenty hada
            if (this.segments.get(i).getIdentificator() < min) {
                min = this.segments.get(i).getIdentificator();
                s = this.segments.get(i);
            }
        }
        return s;
    }

    /**
     * Vrati clanok hadika s najvacsim cislom, to jest hlava chvost.
     *
     * @return Prvy clanok hadika, hlava.
     */
    private SnakeSegment findHead() {
        int max = Integer.MIN_VALUE;
        SnakeSegment s = null;

        for (SnakeSegment segment : this.segments) {
            if (segment.getIdentificator() > max) {
                max = segment.getIdentificator();
                s = segment;
            }
        }
        return s;
    }

    private boolean reactToObject(DrawableObject object) {
        if (object instanceof Food) { //hadik nasiel jedlo tak ho aj zhuta
            this.eatFood((Food) object);
            return true;
        } else if (object instanceof SnakeSegment) { //hadik si ide ukusnut telo
            if (this.player.getDifficulty() == Difficulty.HARDCORE) {
                this.game.gameOver();
                return false;
            }
            SnakeSegment eatenPart = (SnakeSegment) object;
            ArrayList<SnakeSegment> toDel = new ArrayList();

            for (SnakeSegment segment : this.segments) {
                if (segment.getIdentificator() <= eatenPart.getIdentificator()) {
                    toDel.add(segment);
                }
            }

            for (SnakeSegment snakeSegment : toDel) {
                this.deleteSegment(snakeSegment);
            }
        } else if (object instanceof GameObject) {
            ((GameObject)object).reactionOnImpact(this.player, this.game);
        }
        return false;
    }

    private void eatFood(Food food) {
        this.map.removeFromMap(food.getY(), food.getX());
        food.hide();//keby sa ta pytal preco je food.hide tu a nie v triede food
        //tak povies ze ti to bugovalo a ten hadik sa vykresloval tak ako mal az pri pohybe
        food.eat(this.player);
        this.map.decrementObjectCounter();
        this.generator.foodEaten(food);
    }

}
