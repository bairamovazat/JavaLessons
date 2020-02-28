package ru.azat.generic;

import ru.azat.generic.model.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Common {
    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }

    public static void faultyMethod(List<String>... l) {
        Object[] objectArray = l;     // Valid
        objectArray[0] = Arrays.asList(42);
        String s = l[0].get(0);       // ClassCastException thrown here
    }

    public static void main(String[] args) throws Exception {
        List<A> list = new ArrayList<>();
        append(list, A.class);
    }
}
