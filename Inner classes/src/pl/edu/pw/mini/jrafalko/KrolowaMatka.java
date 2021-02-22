package pl.edu.pw.mini.jrafalko;

public class KrolowaMatka extends Pszczola {
    int iloscJaj;

    public KrolowaMatka(String imie, int wiek, int iloscJaj) {
        super(imie, wiek);
        this.silaAtaku = 100;
        this.iloscJaj = iloscJaj;
    }

    void zaplodnienie(){
        this.iloscJaj+=1000;
    }

    @Override
    public String toString() {
        return "KrolowaMatka{" +
                "iloscJaj=" + iloscJaj +
                ", imie='" + imie + '\'' +
                ", silaAtaku=" + silaAtaku +
                ", wiek=" + wiek +
                '}';
    }

    @Override
    public void run() {
        System.out.println("Lot godowy...");
    }

}
