package pl.edu.pw.mini.jrafalko;

import org.w3c.dom.ls.LSOutput;

import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Consumer;

public class Lotnisko {

    private List<Samolot> samoloty;
    Random rand = new Random();

    public Lotnisko(int iloscSamolotow) {
        this.samoloty = new ArrayList<>();
        for (int i = 0; i < iloscSamolotow; i++) {
            int r = rand.nextInt(3);
            GeneratorNazw nowanazwa = () -> {
                StringBuilder builder = new StringBuilder();
                String alfabet = "qwertyuiopasdfghjklzxcvbnm";
                int l = rand.nextInt(20);
                for(int j = 0; j < l; j++) {
                    builder.append(alfabet.charAt(rand.nextInt(alfabet.length())));
                }
                return builder.toString();
            };
            switch (r) {
                case 0:
                    String nazwa0 = nowanazwa.generator();
                    int predkoscMax = 500 + rand.nextInt(500);
                    int maxIloscPasazerow = 100 + rand.nextInt(200);
                    samoloty.add(new SamolotPasazerski(nazwa0, predkoscMax, maxIloscPasazerow));
                    break;
                case 1:
                    String nazwa1 = nowanazwa.generator();
                    predkoscMax = 300 + rand.nextInt(400);
                    int maxZaladunek = 10 + rand.nextInt(90);
                    samoloty.add(new SamolotTowarowy(nazwa1, predkoscMax, maxZaladunek));
                    break;
                case 2:
                    String nazwa2 = nowanazwa.generator();
                    predkoscMax = 900 + rand.nextInt(2100);
                    samoloty.add(new Mysliwiec(nazwa2, predkoscMax));
                    break;
            }
        }
    }//end konstruktor Lotnisko

    public void wypiszSamoloty() {
        for (int i = 0; i < this.samoloty.size(); i++) {
            System.out.println(this.samoloty.get(i));
        }
    }

    public void startSamolotow() {
        for (int i = 0; i < this.samoloty.size(); i++) {
            samoloty.get(i).lec(1 + rand.nextInt(24));
        }
    }

    public void odprawaSamolotow() {
        for (int i = 0; i < this.samoloty.size(); i++) {
            if (samoloty.get(i) instanceof SamolotPasazerski) {
                try {
                    samoloty.get(i).odprawa(rand.nextInt(400));
                } catch (WyjatekLotniczy wyjatekLotniczy) {
                    System.out.println(wyjatekLotniczy.getMessage());
                }
            } else if (samoloty.get(i) instanceof SamolotTowarowy) {
                try {
                    samoloty.get(i).odprawa(rand.nextInt(200));
                } catch (WyjatekLotniczy wyjatekLotniczy) {
                    System.out.println(wyjatekLotniczy.getMessage());
                }
            } else {
                samoloty.get(i).odprawa(rand.nextInt(10));
            }
        }
    }

    public void dzialaniaLotniskowe() {
        samoloty.forEach((Samolot sam)-> {
            int r = rand.nextInt(400);
            System.out.println(sam);
            sam.laduj();
            sam.odprawa(r);
            sam.lec(10);
            if (sam instanceof Mysliwiec) {
                ((Mysliwiec) sam).atak();
            }
        });
        }


    public void sortowanieSamolotow() {
        samoloty.sort(Comparator.comparingInt((Samolot sam) -> sam.predkoscMax));
        wypiszSamoloty();
        samoloty.sort(Comparator.comparing((Samolot sam) -> sam.nazwa));
        ArrayList<Samolot> samolotyMniejNizPiec = new ArrayList();
        for(int i = 0;i<samoloty.size();i++){
            if(((samoloty.get(i)).nazwa).length()>5){
                System.out.println(samoloty.get(i));
            }else{
                samolotyMniejNizPiec.add(samoloty.get(i));
            }
        }
        samolotyMniejNizPiec.forEach(System.out::println);
        }


    public void sortowanieLosowe() {
        Sortowanie sorto = () -> {
            int i = rand.nextInt(1);
            if(i == 1) return Comparator.comparingInt((Samolot sam) -> sam.predkoscMax);
            else return Comparator.comparing((Samolot sam) -> sam.nazwa);
        };
        samoloty.sort((Comparator<Samolot>) sorto.losuj());
        samoloty.forEach((sam) -> System.out.println(sam));
    }

    private abstract class Samolot {
        protected String nazwa;
        protected int predkoscMax;
        protected int iloscGodzinWPowietrzu;
        protected boolean wPowietrzu;
        protected boolean poOdprawie;

        public Samolot(String nazwa, int predkoscMax) {
            this.nazwa = nazwa;
            this.predkoscMax = predkoscMax;
        }

        public void lec(int godziny) {
            if (poOdprawie) {
                iloscGodzinWPowietrzu += godziny;
                if (!wPowietrzu) {
                    wPowietrzu = true;
                    System.out.println("Startujemy...");
                } else {
                    System.out.println("Lecimy...");
                }
            } else {
                System.out.println("Nie możemy wystartować");
            }
        }

        public void laduj() {
            if (wPowietrzu) {
                wPowietrzu = false;
                poOdprawie = false;
                System.out.println("Lądujemy...");
            } else {
                System.out.println("I tak jesteśmy na ziemi");
            }
        }

        public abstract void odprawa(int iloscZaladuku) throws WyjatekLotniczy;
    }//end Samolot

    private class SamolotPasazerski extends Samolot {
        private int maxIloscPasazerow;
        private int iloscPasazerow;

        public SamolotPasazerski(String nazwa, int predkoscMax, int maxIloscPasazerow) {
            super(nazwa, predkoscMax);
            this.maxIloscPasazerow = maxIloscPasazerow;
            this.iloscPasazerow = 0;
        }


        @Override
        public String toString() {
            return "Samolot pasażerski " +
                    "o nazwie '" + nazwa + '\'' +
                    ". Predkość maksymalna " + predkoscMax +
                    ", w powietrzu spędził łącznie " + iloscGodzinWPowietrzu + " godzin" +
                    ", moze zabrac na pokład " + maxIloscPasazerow + " pasażerów. " +
                    (wPowietrzu ? "Obecnie leci z " + iloscPasazerow + " pasażerami na pokładzie." :
                            "Aktualnie uziemiony");
        }

        @Override
        public void odprawa(int iloscZaladuku) throws WyjatekLotniczy {
            iloscPasazerow = iloscZaladuku;
            if(iloscPasazerow<maxIloscPasazerow/2){
                poOdprawie = false;
                try{
                throw new WyjatekEkonomiczny("Za mało pasażerów, nie opłaca się lecieć");}
                catch (WyjatekEkonomiczny nowy){
                    nowy.getStackTrace();
                }
            }
            if(iloscPasazerow>maxIloscPasazerow){
                int roznica = iloscPasazerow-maxIloscPasazerow;
                iloscPasazerow=maxIloscPasazerow;
                poOdprawie = true;
                try{
                    throw new WyjatekPrzeladowania("pasazerow", roznica);}
                catch (WyjatekPrzeladowania nowy){
                    nowy.getStackTrace();
                };
            }
        }
    }//end SamolotPasazerski

    private class SamolotTowarowy extends Samolot {
        int maxZaladunek;
        int ladunek;

        public SamolotTowarowy(String nazwa, int predkoscMax, int maxZaladunek) {
            super(nazwa, predkoscMax);
            this.maxZaladunek = maxZaladunek;
            this.ladunek = 0;
        }

        @Override
        public void odprawa(int iloscZaladuku) throws WyjatekLotniczy {
            ladunek  = iloscZaladuku;
            if(ladunek<maxZaladunek/2){
                poOdprawie = false;
                try{
                throw new WyjatekEkonomiczny("Za mało towaru, nie opłaca się lecieć");}
                catch(WyjatekEkonomiczny nowy){
                    nowy.getStackTrace();
                }
            }
                if(ladunek>maxZaladunek){
                int roznica = ladunek-maxZaladunek;
                ladunek=maxZaladunek;
                poOdprawie = true;
                try{
                throw new WyjatekPrzeladowania("ton ładunku", roznica);}
                catch (WyjatekPrzeladowania nowy){
                    nowy.getStackTrace();
                };
            }
        }

        @Override
        public String toString() {
            return "Samolot towarowy " +
                    "o nazwie '" + nazwa + '\'' +
                    ". Predkość maksymalna " + predkoscMax +
                    ", w powietrzu spędził łącznie " + iloscGodzinWPowietrzu + " godzin" +
                    ", moze zabrac na pokład " + maxZaladunek + " ton ładunku. " +
                    (wPowietrzu ? "Obecnie leci z " + ladunek + " t. ładunku." :
                            "Aktualnie uziemiony");
        }
    }//end SamolotTowarowy

    private class Mysliwiec extends Samolot {
        private int iloscRakiet;

        public Mysliwiec(String nazwa, int predkoscMax) {
            super(nazwa, predkoscMax);
            this.iloscRakiet = 0;
        }

        @Override
        public String toString() {
            return "Myśliwiec " +
                    "o nazwie '" + nazwa + '\'' +
                    ". Predkość maksymalna " + predkoscMax +
                    ", w powietrzu spędził łącznie " + iloscGodzinWPowietrzu + " godzin. " +
                    (wPowietrzu ? "Obecnie leci, rakiet: " + iloscRakiet + "." :
                            "Aktualnie uziemiony.");
        }

        @Override
        public void odprawa(int iloscZaladuku) throws WyjatekLotniczy {
            iloscRakiet = iloscZaladuku;
        }

        public void atak(){
            if(wPowietrzu){
                iloscRakiet-=1;
                System.out.println("Ataaaaak!");
            }
            if(iloscRakiet==0){
                wPowietrzu = false;
            }
        }
    }//end Mysliwiec

}//end Lotnisko