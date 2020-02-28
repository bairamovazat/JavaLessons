package ru.azat.calculator.util;

import ru.azat.calculator.Calculator;
import ru.azat.calculator.DoubleCalculator;
import ru.azat.calculator.model.CalculatorNumber;

import java.util.function.Supplier;

public class CalculatorUtils {
    public static <T> CalculatorNumber<T> addition(CalculatorNumber<T> first, CalculatorNumber<T> second, Supplier<Calculator<T>> calculatorSupplier) {
        Calculator<T> calculator = calculatorSupplier.get();
        return calculator.addition(first, second);
    }

    public static CalculatorNumber<Double> addition(CalculatorNumber<Double> first, CalculatorNumber<Double> second) {
        return addition(first, second, DoubleCalculator::new);
    }
}
