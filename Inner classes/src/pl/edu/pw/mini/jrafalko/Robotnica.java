package pl.edu.pw.mini.jrafalko;

import java.util.Random;

public class Robotnica extends Pszczola{

    int iloscWyprodukowanegoMiodu;

    public Robotnica(String imie, int wiek, int silaAtaku, int iloscWyprodukowanegoMiodu) {
        super(imie, wiek);
        this.silaAtaku = silaAtaku;
        this.iloscWyprodukowanegoMiodu = iloscWyprodukowanegoMiodu;
    }

    void zbierajNektar(int zwieksz){
        iloscWyprodukowanegoMiodu+=zwieksz;
    }

    @Override
    public String toString() {
        return "Robotnica{" +
                "iloscWyprodukowanegoMiodu=" + iloscWyprodukowanegoMiodu +
                ", imie='" + imie + '\'' +
                ", silaAtaku=" + silaAtaku +
                ", wiek=" + wiek +
                '}';
    }

    @Override
    public void run() {
        Random r = new Random();
        zbierajNektar(r.nextInt()*20);
        };
    }


