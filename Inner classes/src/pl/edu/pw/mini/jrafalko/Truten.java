package pl.edu.pw.mini.jrafalko;

public class Truten extends Pszczola{
    boolean przydatny;

    public Truten(String imie, int wiek, boolean przydatny) {
        super(imie, wiek);
        this.silaAtaku = 0;
        this.przydatny = przydatny;
    }

    void zaplodnienie(KrolowaMatka krolowa){
        if(!przydatny){
            return;
        }
        krolowa.zaplodnienie();
        przydatny = false;
        System.out.println("Truteń " + imie + " właśnie zaruchał królową " + krolowa.imie + " i zdechł.");
    }

    @Override
    public String toString() {
        return "Truten{" +
                "przydatny=" + przydatny +
                ", imie='" + imie + '\'' +
                ", silaAtaku=" + silaAtaku +
                ", wiek=" + wiek +
                '}';
    }

    @Override
    public void run() {
        double r = Math.random();
        if(r>=0.5){
            return;
        }else{
            // nie wiedziałem jak dodać królową z danego ula do zapłodnienia :(
        }
    }
}
