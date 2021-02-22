package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.Cenzura;
import pl.edu.pw.mini.jrafalko.Pracownik;

public class Dyrektor extends Pracownik {

    private String ksywka;
    @Cenzura.replaceString("Władywostok")
    private String miastoUrodzenia;

    @Cenzura.shortenTo3String
    private String charakterystykaOsobowosci;
    private int iloscPodwladnych;

    public Dyrektor(String imie, String nazwisko, int wiek, String ksywka,
                    String miastoUrodzenia, String charakterystykaOsobowosci,
                    int iloscPodwladnych) {
        super(imie, nazwisko, wiek);
        this.ksywka = ksywka;
        this.miastoUrodzenia = miastoUrodzenia;
        this.charakterystykaOsobowosci = charakterystykaOsobowosci;
        this.iloscPodwladnych = iloscPodwladnych;
    }

    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 10;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ksywka='" + ksywka + '\'' +
                ", miastoUrodzenia='" + miastoUrodzenia + '\'' +
                ", charakterystykaOsobowosci='" + charakterystykaOsobowosci + '\'' +
                ", iloscPodwladnych=" + iloscPodwladnych +
                ", dyrektor";
    }
}
