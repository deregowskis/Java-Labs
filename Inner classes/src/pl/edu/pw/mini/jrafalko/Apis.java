package pl.edu.pw.mini.jrafalko;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apis {

    private ArrayList<Pszczola> pszczoly;

    public int iloscPszczolWUlu() {return pszczoly.size();}
    public ArrayList<Pszczola> getPszczoly() {return pszczoly;}
    public void infoOUlu() {for (Pszczola p : pszczoly) System.out.println(p);}

    public Apis(){

    }

    public Apis(ArrayList<Pszczola> pszczoly) {
        this.pszczoly = pszczoly;
        pszczoly.add(new KrolowaMatka("Helena",25, 1000));
    }

    public void zyciePszczol(){
        ArrayList<Truten> lista = new ArrayList<>();
        for(int i=0;i<iloscPszczolWUlu();i++){
            if(pszczoly.get(i) instanceof KrolowaMatka){
                int krolowa = i;
            }
            if(pszczoly.get(i) instanceof Truten){
                lista.add(pszczoly.get(i));
            }
        }
        //nie zdążyłem do końca
    }

    public void watkiPszczol(){
        for(int i = 0;i<iloscPszczolWUlu();i++){
            pszczoly.get(i).run();
        }
    }

    public void sortujWgSilyIImienia(){
        //nie wiem czemu, ale to nie zadziałało :(
        //pszczoly.sort(Pszczola.PorownanieSily);
    }

    public void dodajPszczole(Pszczola pszczola){
        pszczoly.add(pszczola);
    }

    public void dodajZolnierza(String imie, int silaAtaku, int wiek){
        pszczoly.add(new Pszczola(imie,silaAtaku,wiek){
            @Override
            public String toString() {
                return "Zolnierz{" +
                        "imie='" + imie + '\'' +
                        ", silaAtaku=" + silaAtaku +
                        ", wiek=" + wiek +
                        '}';
            }

            public void run(){
                System.out.println("Walka to moje życie!!!");
            }
        });
    }


}
