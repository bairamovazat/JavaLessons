package ru.azat.calculator.model;

public abstract class AbstractCalculatorNumber<T> implements CalculatorNumber<T> {
    public abstract CalculatorNumber<T> addition(CalculatorNumber<T> number);

    public abstract CalculatorNumber<T> subtraction(CalculatorNumber<T> number);

    public abstract CalculatorNumber<T> multiplication(CalculatorNumber<T> number);

    public abstract CalculatorNumber<T> division(CalculatorNumber<T> number);
}
