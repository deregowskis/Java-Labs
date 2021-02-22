package pl.edu.pw.mini.jrafalko;

import java.util.Comparator;

public abstract class Pszczola implements Runnable {
    String imie;
    int silaAtaku;
    int wiek;

    public Pszczola(String imie, int silaAtaku, int wiek) {
        this.imie = imie;
        this.silaAtaku = silaAtaku;
        this.wiek = wiek;
    }
    public Pszczola(String imie, int wiek) {
        this.imie = imie;
        this.wiek = wiek;
    }

    static class PorownanieSily implements Comparator<Pszczola> {
        @Override
        public int compare(Pszczola p1, Pszczola p2) {
            if(p1.silaAtaku>p2.silaAtaku){
                return 1;
            }
            if(p1.silaAtaku<p2.silaAtaku){
                return -1;
            }else{
                return 0;
            }
        }
    }

    @Override
    public void run() {
    }

}
