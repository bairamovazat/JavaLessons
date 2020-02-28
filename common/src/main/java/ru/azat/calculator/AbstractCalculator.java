package ru.azat.calculator;

import ru.azat.calculator.model.CalculatorNumber;

public abstract class AbstractCalculator<T> implements Calculator<T> {

    @Override
    public CalculatorNumber<T> addition(CalculatorNumber<T> first, CalculatorNumber<T> second) {
        return first.addition(second);
    }

    @Override
    public CalculatorNumber<T> subtraction(CalculatorNumber<T> first, CalculatorNumber<T> second) {
        return first.subtraction(second);
    }

    @Override
    public CalculatorNumber<T> multiplication(CalculatorNumber<T> first, CalculatorNumber<T> second) {
        return first.multiplication(second);
    }

    @Override
    public CalculatorNumber<T> division(CalculatorNumber<T> first, CalculatorNumber<T> second) {
        return first.division(second);
    }
}
