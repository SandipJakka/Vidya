package com.nomura.sandeep.chronicle.test;

import com.nomura.sandeep.chronicle.effective.Favourtites;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Typesafe Heterogenous container pattern
public class Favorites {
    private Map<Class<?>, Object> conatiner = new HashMap<>();

    private Map<Class<?>, Map<String, Object>> typeSafeDbContainer = new HashMap<>();

    public <T> void put(Class<T> type, T value) {
        // conatiner.put(Objects.requireNonNull(type), value);
        //safer approach... In this case client can't pass (Integer.class, "one")
        conatiner.put(Objects.requireNonNull(type), type.cast(value));
    }

    public <T> T get(Class<T> type) {
        //dynamic casting --- as we are storing object not the actual type.
        return type.cast(conatiner.get(type));
    }

    public static void main(String[] args) {
        Favourtites f = new Favourtites();

        f.putFavourite(String.class, "one");
        f.putFavourite(Integer.class, 1);
    }
}
