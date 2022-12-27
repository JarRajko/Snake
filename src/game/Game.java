package game;

import graphics.Display;
import map.Map;
import player.Player;
import graphics.Platno;
import items.ItemGenerator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import map.Direction;

public class Game {

    private Player player;
    private Map map;
    private ItemGenerator generator;
    private Manazer man;
    private Display display;
    private boolean pause;
    private final int boardSize = 30;
    private final int drawingSpeed = 25; //0,25 sekundy
    private long currentTickCounter = 0; //pomocna premenna na sledovanie casu
    private long currentPlayerSpeedTickCounter = 0; //pomocna premenna pri pohybe hraca
    private long playerSpeed;

    public Game() {
        String name = JOptionPane.showInputDialog("Zadaj svoje ctene meno: ");
        String[] diffs = {"IZI","NORMAL","SNAKE","HARDKOR"};
        String difikulty = (String) JOptionPane.showInputDialog(new JFrame("Input Dialog Example 3"), 
        "Aka je tvoja oblubena obtiaznost?",
        "Monzosti:",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        diffs, 
        diffs[2]);
        
        Difficulty difficulty = Difficulty.NORMAL;
        if(difikulty == null) {
            difficulty = Difficulty.NORMAL;
            difikulty = "Špekulantskú";
        } else if (difikulty.equals(diffs[0])) {
            difficulty = Difficulty.EASY;
        } else if (difikulty.equals(diffs[1])) {
            difficulty = Difficulty.NORMAL;
        } else if (difikulty.equals(diffs[2])) {
            difficulty = Difficulty.SNAKE;
        } else if (difikulty.equals(diffs[3])) {
            difficulty = Difficulty.HARDCORE;
        }
        JOptionPane.showMessageDialog(null, "Zvolili ste si " + difikulty + " obtiaznost. \n" + difficulty.getInfo());
        Platno.dajPlatno().setVisible(true);
        this.map = new Map(this.boardSize);
        this.generator = new ItemGenerator(this.map);
        this.player = new Player(this.map, this.generator, this, name, difficulty);
        this.man = new Manazer();
        this.man.spravujObjekt(this);
    }

    public void aktivuj() {
        System.out.println("Stlacil si enter. Co sa podla teba malo stat?");
    }

    public void zrus() {
        this.pause = !this.pause;

        if (this.pause) {
            JOptionPane.showMessageDialog(null, "Hra je pozastavena. \nStlacte esc pre pokracovanie.");
        }
    }

    public void posunHore() {
        if (!this.pause) {
            this.player.changeDirection(Direction.UP);
        }
    }

    public void posunDole() {
        if (!this.pause) {
            this.player.changeDirection(Direction.DOWN);
        }
    }

    public void posunVlavo() {
        if (!this.pause) {
            this.player.changeDirection(Direction.LEFT);
        }
    }

    public void posunVpravo() {
        if (!this.pause) {
            this.player.changeDirection(Direction.RIGHT);
        }
    }

    //tik ktory vola manazer, tik sa vola kazdych 0,001 sekundy (upravil som to)
    //dal som to kvoli tomu aby sa mohol ten hadik rychlejsie hybat
    //obraz sa prekresluje kazdych "drawingSpeed" (250 milisekund) / 4 krat za sekundu
    public void tik() {
        if (!this.pause) {
            this.playerSpeed = this.player.getPlayerSpeed();
            this.currentTickCounter++;
            this.currentPlayerSpeedTickCounter++;

            if (this.currentTickCounter >= this.drawingSpeed) {
                this.currentTickCounter = 0;
                this.generator.tik();
            }

            if (this.currentPlayerSpeedTickCounter >= this.playerSpeed) {
                this.player.move();
                this.currentPlayerSpeedTickCounter = 0;
            }
        }
    }
    
    public void gameOver() {
        this.man = null; //manazer.prestanSpravovatObjekt v novsichv verziach to bolo
        JOptionPane.showMessageDialog(null, "Koniec hry " + this.player.getName() + ". Tvoje skore je: " + this.player.getScore() + " Obtiaznost: " + this.player.getDifficulty().toString());
        System.exit(0);
    }

}
