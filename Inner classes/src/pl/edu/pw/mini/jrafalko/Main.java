package pl.edu.pw.mini.jrafalko;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    /*
     * Klasa Main - 1p
     * Klasa Apis - 7p
     * Przejrzysty/czytelny kod - 1p
     */
    public static void main(String[] args) {

        Apis ul = new Apis();
        System.out.println("W ulu jest: " + ul.iloscPszczolWUlu() + " pszczół");
        ul.infoOUlu();

        ArrayList<Pszczola> lista = new ArrayList<>();
        ul.dodajPszczole(new Truten("Geniek",10,true));
        ul.dodajPszczole(new Robotnica("Mariola",30,45,13));
        ul.dodajPszczole(new KrolowaMatka("Anita",24,540));

        ul.zyciePszczol();
        System.out.println("\nW ulu jest: " + ul.iloscPszczolWUlu() + " pszczół");
        ul.infoOUlu();
        System.out.println("\nPszczoły posortowane wg siły i imienia:");
        ul.sortujWgSily();
        ul.infoOUlu();
        System.out.println("\nPszczoły posortowane wg siły:");
        //--------TODO-------------------------
        //Sortowanie listy pszczół

        //-------------------------------------
        ul.infoOUlu();
        System.out.println("\nŻołnierz:");
        ul.dodajZolnierza("Helena", 99, 10);
        System.out.println((ul.getPszczoly().get(ul.getPszczoly().size() - 1)));
        System.out.println("\nWątki pszczół:");
        ul.watkiPszczol();
    }
}
