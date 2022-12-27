package map;

import graphics.Display;
import items.DrawableObject;

public class Map {

    private DrawableObject[][] board;
    private int objectCounter = 0;

    public Map(int size) {
        this.board = new DrawableObject[size][size];
        Display.getInstance();
    }

    public void addToMap(DrawableObject object, int x, int y) {
        this.board[y][x] = object;
        Display.getInstance().turnOn(x, y, object.getColor());
    }

    /**
     * Vrati nakreslitelny objekt z mapy
     *
     * @param x horizontalna pozicia objektu na mape
     * @param y vertikalna pozicia objektu na mape
     * @return DrawableObject
     * @throws OutOfMapException vracia exception ked sa snazi
     */
    public DrawableObject getFromMap(int x, int y) throws OutOfMapException { //NOTE: porusenie zapuzdrenia?
        try {
            return this.board[y][x];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new OutOfMapException();
        }
    }

    public int getSize() {
        return this.board.length;
    }

    /**
     * Vymaze objekt z mapy, nastavi hodnotu v mape na null
     *
     * @param y vertikalna suradnica objektu
     * @param x horizontalna suradnica objektu
     * @return
     */
    public boolean removeFromMap(int y, int x) {
        Display.getInstance().turnOff(x, y);
        if (this.board[y][x] == null) {
//            System.out.println("Position already empty!"); //pre istotu
            return false;
        } else {
            this.board[y][x] = null;
            return true;
        }
    }

    /**
     * Vrati zapuzdrenu kopiu 2D pola
     *
     * @return 2 rozmerne pole drawable objektov
     */
    public DrawableObject[][] getBoard() {
        DrawableObject[][] temp = new DrawableObject[this.board.length][this.board.length];
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board.length; x++) {
                temp[y][x] = this.board[y][x];
            }
        }
        return temp;
    }
    
    public void incrementObjectCounter() {
        this.objectCounter++;
    }
    
    public void decrementObjectCounter() {
        this.objectCounter--;
    }
    
    public int getObjectCounter() {
        return this.objectCounter;
    }
    
}
