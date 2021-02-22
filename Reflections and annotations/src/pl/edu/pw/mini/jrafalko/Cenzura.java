package pl.edu.pw.mini.jrafalko;

import pl.edu.pw.mini.jrafalko.censor.Censorable;
import pl.edu.pw.mini.jrafalko.workers.Ksiegowa;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Cenzura implements Censorable {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface deleteString {}

    @Target(ElementType.FIELD)

    @Retention(RetentionPolicy.RUNTIME)
    public @interface shortenTo3String {}

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface replaceString {
        String value() default "....";
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface zeroInt {}

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface repeat {
        public int count() default 0;
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface bombki {}

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface removeOld{}

    @Override
    public List<Pracownik> cenzuruj(List<Pracownik> list) throws IllegalAccessException, InvocationTargetException {
        for (Pracownik pracownik : list) {
            //pierwsze
            if (pracownik.getClass().isAnnotationPresent(deleteString.class)) {
                for (Field f : pracownik.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getType().isAssignableFrom(java.lang.String.class)) {
                        try {
                            f.set(pracownik, "");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        ;
                    }
                }
            }
            //drugie
            if (pracownik.getClass().isAnnotationPresent(shortenTo3String.class)) {
                for (Field f : pracownik.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getType().isAssignableFrom(java.lang.String.class)) {
                        try {
                            Object stary = f.get(pracownik);
                            stary = ((String) stary).substring(0, 3);
                            stary = stary + "___";
                            f.set(pracownik, stary);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            //trzecie
            if (pracownik.getClass().isAnnotationPresent(replaceString.class)) {
                for (Field f : pracownik.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getType().isAssignableFrom(java.lang.String.class)) {
                        f.set(pracownik, f.getAnnotation(replaceString.class).value());
                    }
                }
            }
            //czwarte
            if (pracownik.getClass().isAnnotationPresent(zeroInt.class)) {
                for (Field f : pracownik.getClass().getDeclaredFields()) {
                    if (f.getType().isAssignableFrom(int.class)) {
                        f.setAccessible(true);
                        try {
                            f.set(pracownik, 0);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (pracownik.getClass().isAnnotationPresent(repeat.class)) {
                for (Method metoda : pracownik.getClass().getDeclaredMethods()) {
                    for(int i = 0; i < pracownik.getClass().getAnnotation(repeat.class).count(); i++)
                        metoda.invoke(pracownik.getClass());
                }
            }
        }
        return list;
    }
}
