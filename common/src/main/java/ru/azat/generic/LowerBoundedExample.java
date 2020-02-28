package ru.azat.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedExample {

    public static void testAll() {
        List<Double> doubleList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Serializable> serializableList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        testExtends(doubleList);
//        NOT WORK
//        testSuper(doubleList);

        testExtends(numberList);
        testSuper(numberList);

//        NOT WORK
//        testExtends(serializableList);
        testSuper(serializableList);
    }

    public static void testSuper(List<? super Number> list) {
        Double doubleValue = 0D;
        Number number = 0;
        Serializable serializable = 0;
        Object object = 0;
        list.add(number);
        //NOT WORK
//        list.add(serializable);
//        NOT WORK
//        list.add(object);
        list.add(number);
        list.add(doubleValue);
        //NOT WORK
//        doubleValue = list.get(0);
        //NOT WORK
//        number = list.get(0);
        //NOT WORK
//        serializable = list.get(0);
        object = list.get(0);

    }

    public static void testExtends(List<? extends Number> list) {
        Double doubleValue = 0D;
        Number number = 0;
        Serializable serializable = 0;
        Object object = 0;
        //NOT WORK
//        list.add(doubleValue);
        //NOT WORK
//        list.add(number);
        //NOT WORK
//        list.add(serializable);
        //NOT WORK
//        list.add(object);
        //NOT WORK
//        doubleValue = list.get(0);
        number = list.get(0);
        serializable = list.get(0);
        object = list.get(0);

    }
}
