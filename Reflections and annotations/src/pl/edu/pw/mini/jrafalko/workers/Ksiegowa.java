package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.Cenzura;
import pl.edu.pw.mini.jrafalko.Cenzura.deleteString;
import pl.edu.pw.mini.jrafalko.Pracownik;

@deleteString
public class Ksiegowa extends Pracownik {

    private String drugieImie;
    private String opiniaZewnetrzna;

    public Ksiegowa(String imie, String drugieImie, String nazwisko, int wiek,
                     String opiniaZewnetrzna) {
        super(imie, nazwisko, wiek);
        this.drugieImie = drugieImie;
        this.opiniaZewnetrzna = opiniaZewnetrzna;
    }

    @Override
    @Cenzura.repeat(count = 3)
    protected void zwiekszZysk() {
        wypracowanyZysk += 4;
    }

    @Override
    public String toString() {
        return "Pracownik:" +
                " imie='" + imie + '\'' +
                ", drugieImie='" + drugieImie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                ", wypracowanyZysk=" + wypracowanyZysk +
                ", opiniaZewnetrzna='" + opiniaZewnetrzna + '\'' +
                ", księgowa";
    }
}
