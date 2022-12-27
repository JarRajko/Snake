package player;

import items.DrawableObject;
import java.awt.Color;

/**
 * Vytvara segment hadika, s farbou, x,y poziciou a identifikatorom
 * @author 
 */
public class SnakeSegment extends DrawableObject {
    
    //premenna na cislovanie segmentov
    private final int identificator;
    
    /**
     *
     * @param x pozicia segmentu
     * @param y pozicia segmentu
     * @param color awt farba aku bude mat clanok hadika
     * @param identificator pouziva sa pri hladani chvostu, identifikator ma kazdy segment jedinecny
     */
    public SnakeSegment(int y, int x, Color color, int identificator) {
        super(y, x, color);
        this.identificator = identificator;
    }
    
    /**
     * Vracia zapuzdrenu kopiu segmentu hadika
     * @return kopia segmentu hadika
     */
    public SnakeSegment getCopyOfSegment() {
        return new SnakeSegment(super.getX(), super.getY(), super.getColor(), this.identificator);
    }

    public int getIdentificator() {
        return this.identificator;
    }      
}
