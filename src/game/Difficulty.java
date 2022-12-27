/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author
 */
public enum Difficulty {
    EASY("Nie je sa coho bat. Mas aj sancu vyhrat."),
    NORMAL("Mozes si ukusnut z chvosta."),
    SNAKE("Ked narazis do prekazky tak si prehral. Mozes si odkusnut chvost"),
    HARDCORE("Kontakt s chvostom a prekazkou znamena koniec hry. \n"
            + "A este nejake specialne prekvapkanie :)");

    private String info;

    Difficulty(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return this.info;
    }
}
