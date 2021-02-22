package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.Cenzura;
import pl.edu.pw.mini.jrafalko.Pracownik;
import pl.edu.pw.mini.jrafalko.Produkty;

public class Monter extends Pracownik {
    @Cenzura.zeroInt
    private int iloscWyprodukowanychElementow;
    private Produkty produkowanyElement;

    public Monter(String imie, String nazwisko, int wiek,
                  int iloscWyprodukowanychElementow, Produkty produkowanyElement) {
        super(imie, nazwisko, wiek);
        this.iloscWyprodukowanychElementow = iloscWyprodukowanychElementow;
        this.produkowanyElement = produkowanyElement;
    }

    @Override
    @Cenzura.repeat(count = 10)
    protected void zwiekszZysk() {
        wypracowanyZysk += 2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", iloscWyprodukowanychElementow=" + iloscWyprodukowanychElementow +
                ", produkowanyElement=" + produkowanyElement +
                ", monter";
    }
}
