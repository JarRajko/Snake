package graphics;

import items.DrawableObject;
import java.awt.Color;

public class Display {

    private int size = 30;
    //pixely pre vykreslovanie stvorcov
    private int squareSize = 20;
    private int squareDistance = 0;
    private int paddingLeft = 10;
    private int paddingUp = 10;
    private Stvorec[][] displayTable;
    private Color backgroundColor = Color.DARK_GRAY;
    private static Display display;

    public static Display getInstance() {
        if (Display.display == null) {
            Display.display = new Display();
        }
        return Display.display;
    }
    
    private Display() {
        this.displayTable = new Stvorec[this.size][this.size];
        this.displayInit();
    }

    /**
     * Vytvori na platne pole stvorcov ktore sa potom
     * iba zhasina a vypina podla udajov na mape
     */
    private void displayInit() {
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.displayTable[y][x] = new Stvorec();
                this.displayTable[y][x].zmenStranu(this.squareSize);
                this.displayTable[y][x].posunVodorovne((this.squareSize + this.squareDistance) * x);
                this.displayTable[y][x].posunZvisle((this.squareSize + this.squareDistance) * y);
                this.displayTable[y][x].posunVodorovne(this.paddingLeft);
                this.displayTable[y][x].posunZvisle(this.paddingUp);
                this.displayTable[y][x].zmenFarbu(this.backgroundColor);
                this.displayTable[y][x].zobraz();
            }
        }
    }

    /**
     * "Zasvieti" dany stvorcek na displeji.
     *
     * @param x horizontalna suradnica stvorceka
     * @param y vertikalna suradnica stvorceka
     * @param color farba na ktoru zasvietit
     */
    public void turnOn(int x, int y, Color color) {
        this.displayTable[y][x].zmenFarbu(color);
    }

    /**
     * "Zhasne" dany stvorcek na displeji.
     *
     * @param x horizontalna suradnica stvorceka
     * @param y vertikalna suradnica stvorceka
     */
    public void turnOff(int x, int y) {
        this.displayTable[y][x].zmenFarbu(this.backgroundColor);
    }
    
    public void changeBackgroundColorSoPlayerKnowHeCannotGrowWhileEatingApples() {
        if (this.backgroundColor == Color.DARK_GRAY) {
            this.backgroundColor = new Color(165, 100, 100);
        } else {
            this.backgroundColor = Color.DARK_GRAY;
        }
    }

}
