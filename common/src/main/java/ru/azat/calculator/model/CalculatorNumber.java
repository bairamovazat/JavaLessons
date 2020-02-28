package ru.azat.calculator.model;

public interface CalculatorNumber<T> {
    CalculatorNumber<T> addition(CalculatorNumber<T> number);

    CalculatorNumber<T> subtraction(CalculatorNumber<T> number);

    CalculatorNumber<T> multiplication(CalculatorNumber<T> number);

    CalculatorNumber<T> division(CalculatorNumber<T> number);

    T getValue();
}
