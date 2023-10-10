package com.nomura.sandeep.chronicle.effective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favourtites {
    final Map<Class<?>, Object> favourites = new HashMap<>();

    public <T> void putFavourite(Class<T> type, T instance) {
        favourites.put(type, instance);
    }

    public <T> T favourite(Class<T> type) {
        return type.cast(favourites.get(type));
    }

    public static void main(String[] args) {
        Favourtites f = new Favourtites();
        f.putFavourite(String.class, "String");
        f.putFavourite(Integer.class, 10);
        f.putFavourite(Double.class, 2.5d);
        f.putFavourite(List.class, new ArrayList<String>());
        f.putFavourite(List.class, new ArrayList<Double>());
      //  f.putFavourite(List.<String>class, new ArrayList<String>());     // not-possible


        System.out.printf("val == %f %n", f.favourite(Double.class));
        System.out.printf("val == %f %n", f.favourite(Class.class));
        System.out.printf("val == %f %n", f.favourite(Class.class));
    }
}
