package pl.edu.pw.mini.jrafalko;
import pl.edu.pw.mini.jrafalko.cargo.*;
import pl.edu.pw.mini.jrafalko.truck.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n--------1---------");
        task1();
        System.out.println("\n--------2---------");
        task2();
        System.out.println("\n--------3---------");
        task3();
        System.out.println("\n--------4---------");
        task4();
        System.out.println("\n--------5---------");
        task5();
        System.out.println("\n--------6---------");
        task6();
        System.out.println("\n--------7---------");
        task7();
        System.out.println("\n--------8---------");
        task8();
        System.out.println("\n--------9---------");
        task9();
        System.out.println("\n--------10---------");
        task10();
        System.out.println("\n--------11---------");
        task11();
        System.out.println("\n--------12---------");
        task12();
        System.out.println("\n--------13---------");
        task13();
        System.out.println("\n--------14---------");
        task14();
        System.out.println("\n--------15---------");
        task15();
    }

    public static void task1() {
        Class c1 = null;
        try {
            c1 = Class.forName("pl.edu.pw.mini.jrafalko.cargo.Barrel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert c1 != null;
        System.out.println(Arrays.toString(c1.getDeclaredConstructors()));
    }

    public static void task2(){
        Class c2 = null;
        try {
            c2 = Class.forName("pl.edu.pw.mini.jrafalko.cargo.Chest");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        assert c2 != null;
        Constructor[] lista = (c2.getDeclaredConstructors());
        for(Constructor constructor : lista){
            if(Arrays.toString(constructor.getParameterTypes()).equals("[boolean, class java.lang.String]")){
                System.out.println("TAK");
                return;
            }
        }
        System.out.println("NIE");
    }

    public static void task3() {
        System.out.println(Cargo.class.getPackage());
    }

    public static  void task4() {
        Class c4 = null;
        try {
            c4 = Class.forName("pl.edu.pw.mini.jrafalko.cargo.BagOfPotatoes");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        assert c4 != null;
        Method[] lista = c4.getDeclaredMethods();
        for(Method metoda : lista){
            if(Modifier.isPrivate(metoda.getModifiers())){
                System.out.println(metoda);
            }
        }
    }

    public static void task5() {
        System.out.println(SpareWeel.sticker);
    }

    public static void task6() {
        System.out.println(Barrel.class.getSuperclass().getName());
    }

    public static void task7() {
        Class cl7 = null;
        try {
            cl7 = Class.forName("pl.edu.pw.mini.jrafalko.cargo.Cargo");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(Cargo.class.getInterfaces()));
        for(Class<?> klasa : Cargo.class.getInterfaces()){
            if (klasa.getPackage().equals(cl7.getPackage())){
                System.out.println("TAK");
                return;
            }
        }
        System.out.println("ŻODYN");
    }

    public static void task8() {
        Class c8 = null;
        try {
            c8 = Class.forName("pl.edu.pw.mini.jrafalko.truck.SpareWeel");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        assert c8 != null;
        Object nowy = null;
        try {
            nowy = c8.newInstance();
        } catch (IllegalAccessException | InstantiationException e){
            e.printStackTrace();
        }
        for(Field pole : c8.getDeclaredFields()){
            pole.setAccessible(true);
            if(pole.getName().equals("tireSize")){
                try {
                    System.out.println(pole.get(nowy));
                } catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void task9() {

    }

    public static void task10() {
        Class c10 = null;
        try {
            c10 = Class.forName("pl.edu.pw.mini.jrafalko.truck.Truck");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Object nowy = null;
        try {
            nowy = c10.newInstance();
        } catch (IllegalAccessException | InstantiationException e){
            e.printStackTrace();
        }
        System.out.println("Utworzono obiekt: " + nowy);
    }

    public static void task11() {
        //todo 11
    }

    public static void task12() {
        //todo 12
    }

    public static void task13() {
        //todo 13
    }

    public static void task14() {
        //todo 14

    }

    public static void task15() {
        //todo 15
    }

    ;
}
