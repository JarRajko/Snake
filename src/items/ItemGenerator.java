package items;

import items.food.AncientApple;
import items.food.Apple;
import items.food.Food;
import items.food.PineApple;
import items.food.Poison;
import items.food.SuspiciousApple;
import items.objects.BlackHole;
import items.objects.GameObject;
import items.objects.Obstacle;
import java.util.Random;
import map.Map;
import map.OutOfMapException;

/**
 * Trieda ktora sa stara o pridavanie veci do mapy
 *
 * @author
 */
public class ItemGenerator {

    private Map map;
    private int appleCounter = 0;
    private int pineAppleCounter = 0;
    private int ancientAppleCounter = 0;
    private int poisionCounter = 0;
    private int suspiciousAppleCounter = 0;
    private int timeCounter = 0;
    private int tick = 1;
    private int minute = 60;
    private int foodLimit = 12;//35;
    private boolean stopGeneratingStuff = false;

    public ItemGenerator(Map map) {
        this.map = map;
    }

    private void generateFood(Food food) {
        DrawableObject o = this.getRandomEmptyTile();
        if (food instanceof Apple) {
            this.map.addToMap(new Apple(o.getX(), o.getY()), o.getX(), o.getY());
            this.appleCounter++;
        } else if (food instanceof SuspiciousApple) {
            this.map.addToMap(new SuspiciousApple(o.getX(), o.getY()), o.getX(), o.getY());
            this.suspiciousAppleCounter++;
        } else if (food instanceof AncientApple) {
            this.map.addToMap(new AncientApple(o.getX(), o.getY()), o.getX(), o.getY());
            this.ancientAppleCounter++;
        } else if (food instanceof Poison) {
            this.map.addToMap(new Poison(o.getX(), o.getY()), o.getX(), o.getY());
            this.poisionCounter++;
        } else if (food instanceof PineApple) {
            this.map.addToMap(new PineApple(o.getX(), o.getY()), o.getX(), o.getY());
            this.pineAppleCounter++;
        } else {
            //System.out.println("no food!!!");
            return;
        }
    }

    private void generateObject(GameObject itemObject) {
        DrawableObject o = this.getRandomEmptyTile();

        if (itemObject instanceof BlackHole) {
            this.map.addToMap(new BlackHole(o.getX(), o.getY()), o.getX(), o.getY());
        } else if (itemObject instanceof Obstacle) {
            this.map.addToMap(new Obstacle(o.getX(), o.getY()), o.getX(), o.getY());
        }
    }

    public void fullTestSeed() { //NOTE: Delete
        for (int y = 0; y < this.map.getSize(); y++) {
            for (int x = 0; x < this.map.getSize(); x++) {
                this.map.addToMap(new Apple(x, y), x, y);
            }
        }
    }

    private DrawableObject getRandomEmptyTile() {
        Random rand = new Random();
        int x;
        int y;

        while (true) {
            x = rand.nextInt(this.map.getSize());
            y = rand.nextInt(this.map.getSize());

            try {
                if (this.map.getFromMap(x, y) == null) {
                    //staci vratit drawable object ale ten sa neda instancovat, ide hlavne o tie suradnice
                    return new Apple(x, y);
                }
            } catch (OutOfMapException ex) {
                continue; //toto je zbytocne jak mrtvemu zimnik ale nechcel som to nechat prazdne
            }
        }
    }

    public void foodEaten(Food food) {
        if ((this.ancientAppleCounter + this.appleCounter + this.pineAppleCounter + this.poisionCounter + this.suspiciousAppleCounter) < this.foodLimit) {
            this.stopGeneratingStuff = false;
        }
        if (food instanceof Apple) {
            this.appleCounter--;
        } else if (food instanceof PineApple) {
            this.pineAppleCounter++;
        } else if (food instanceof AncientApple) {
            this.ancientAppleCounter--;
        } else if (food instanceof Poison) {
            this.poisionCounter--;
        } else if (food instanceof SuspiciousApple) {
            this.suspiciousAppleCounter--;
        }
    }

    public void tik() {
        if ((this.ancientAppleCounter + this.appleCounter + this.pineAppleCounter + this.poisionCounter + this.suspiciousAppleCounter) > this.foodLimit) {
            this.stopGeneratingStuff = true;
        }
        this.timeCounter++;
        if (!this.stopGeneratingStuff) {
            if (this.timeCounter % (this.tick * 5) == 0) {
                this.doEveryFiveTicks();
                System.out.println("5");
            }
            if (this.timeCounter % (this.tick * 10) == 0) {
                this.doEveryTenTicks();
                System.out.println("10");
            }
            if (this.timeCounter % (this.tick * 20) == 0) {
                this.doEveryTwentyTicks();
                System.out.println("20");
            }
            if (this.timeCounter % (this.tick * 30) == 0) {
                this.doEveryThirtyTicks();
                System.out.println("30");
            }
            if (this.timeCounter % (this.tick * 60) == 0) {
                this.timeCounter = 0;
                System.out.println("minuta");
            }

            while (this.appleCounter < 10) {
                Apple a = new Apple(-1, -1);
                this.generateFood(a);
                System.out.println("aaaaaaaaa");
            }
        }
    }

    private void doEveryFiveTicks() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            PineApple p = new PineApple(-1, -1);
            this.generateFood(p);
        } else {
            Poison p = new Poison(-1, -1);
            this.generateFood(p);
        }
    }

    private void doEveryTenTicks() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            AncientApple p = new AncientApple(-1, -1);
            this.generateFood(p);
        } else {
            SuspiciousApple p = new SuspiciousApple(-1, -1);
            this.generateFood(p);
        }
    }

    private void doEveryTwentyTicks() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            Obstacle o = new Obstacle(-1, -1);
            this.generateObject(o);
        } else {
            BlackHole b = new BlackHole(-1, -1);
            this.generateObject(b);
        }
    }

    private void doEveryThirtyTicks() {

    }
}
