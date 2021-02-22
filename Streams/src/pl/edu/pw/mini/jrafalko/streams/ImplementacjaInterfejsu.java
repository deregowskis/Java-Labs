package pl.edu.pw.mini.jrafalko.streams;

import pl.edu.pw.mini.jrafalko.figures.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImplementacjaInterfejsu implements MetodyStrumieniowe {

    List<Figura2D> figury;

    public ImplementacjaInterfejsu(List<Figura2D> fig) {
        this.figury = fig;
    }

    /**
     * 1
     * @return Największa figura względem właściwości wysokosc
     * 0,5 pkt
     */
    @Override
    public Figura2D getNajwiekszaFigure() {
        return figury
                .stream()
                .max(Comparator.comparingDouble(Figura2D::getWysokosc))
                .orElse(null);
    }

    /**
     * 2
     * @return Figura2D o najmniejszym polu powierzchni
     * 0,5 pkt
     */
    @Override
    public Figura2D getFigureONajmniejszymPolu() {
        return figury
                .stream()
                .filter(fig -> !(fig instanceof Figura3D))
                .min(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .orElse(null);
    }

    /**
     * 3
     * @return Najwyższa figura 3D
     * 0,5 pkt
     */
    @Override
    public Figura2D getNajwyzszaFigure3D() {
        return figury
                .stream()
                .filter(fig -> (fig instanceof Figura3D ))
                .min(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .orElse(null);
    }

    /**
     * 4
     * @return Najmniejszy sześcian względem objętości
     * 1 pkt
     */
    @Override
    public Figura2D getNajmniejszySzescian() {

        return figury
                .stream()
                .filter(fig -> (fig instanceof Szescian))
                .map(fig -> (Szescian) fig)
                .min(Comparator.comparingDouble(Szescian::objetosc)).orElse(null);
    }

    /**
     * 5
     * @return Lista figur posortowanych względem pola powierzchni
     * 0,5 pkt
     */
    @Override
    public List<Figura2D> getPosortowaneWzgledemPowiezchni() {
        return figury
                .stream()
                .sorted(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .collect(Collectors.toList());
    }

    /**
     * 6
     * @return Figura z posortowanych malejaco względem obwodu,
     *         nr figury podany w argumencie,
     *         pomijając te, które obwodu nie mają
     * 1 pkt
     */
    @Override
    public Figura2D getFigureZPosortowanychMalejacoWgObwodu(int nr) {

        return figury
                .stream()
                .filter(fig -> !(fig instanceof Figura3D))
                .sorted(Comparator.comparingDouble(Figura2D::obwod).reversed())
                .skip(nr-1).limit(1)
                .findAny().get();
    }

    /**
     * 7
     * @return Lista pierwszych figur posortowanych rosnąco względem pola powierzchni,
     *         o wysokości nie większej niż 10 i polu powierzchni nie mniejszym niż 10,
     *         ilość zwracanych figur w argumencie
     * 1 pkt
     */
    @Override
    public List<Figura2D> getPierwszePosortowaneRosnacoWgPowierzchni(int ilosc) {

        return figury
                .stream()
                .filter(fig -> fig.getWysokosc() <= 10 && fig.polePowierzchni() >= 10)
                .sorted(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .limit(ilosc)
                .collect(Collectors.toList());

    }

    /**
     * 8
     * @return Lista wszystkich sześcianów o długości boku nie większej niż bok
     * 1 pkt
     */
    @Override
    public List<Figura2D> getSzesciany(int bok) {

        return figury
                .stream()
                .filter(fig -> fig instanceof Szescian)
                .filter(fig -> (fig.getWysokosc() <= bok))
                .collect(Collectors.toList());
    }

    /**
     * 9
     * @return Koło o najmniejszym polu powierzchni
     * 0,5 pkt
     */
    @Override
    public Figura2D getNajmniejszeKolo() {
        return figury
                .stream()
                .filter(fig -> fig instanceof Kolo)
                .min(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .orElse(null);
    }

    /**
     * 10
     * @return Mapa figur względem ID
     * 1 pkt
     */
    @Override
    public Map<Integer, Figura2D> mapaWgId() {

        return figury
                .stream()
                .collect(Collectors.toMap(Figura2D::getId, Function.identity()));
    }

    /**
     * 11
     * @return Ilość figur o polu powierzchni nie większym niż pole
     * 0,5 pkt
     */
    @Override
    public int getiloscMalych(double pole) {

        return (int) figury
                .stream()
                .filter(fig -> fig.polePowierzchni() <= pole)
                .count();
    }

    /**
     * 12
     * @return Posortowany ciąg figur względem pola powierzchni zaczynając od podanej
     * 0,5 pkt
     */
    @Override
    public List<Figura2D> posortowaneWgPolaZaczynajacOd(int nr) {
        return figury
                .stream()
                .sorted(Comparator.comparingDouble(Figura2D::polePowierzchni))
                .skip(nr)
                .collect(Collectors.toList());
    }
}
