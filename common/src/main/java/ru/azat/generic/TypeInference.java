package ru.azat.generic;

import ru.azat.generic.model.A;
import ru.azat.generic.model.B;



public class TypeInference {
    static <T> T pick(T a1, T a2) { return a2; }

    public static void main(String[] args) {
//        pick(new A(), new B());
    }
}
