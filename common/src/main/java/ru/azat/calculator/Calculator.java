package ru.azat.calculator;

import ru.azat.calculator.model.CalculatorNumber;

public interface Calculator<T> {
    CalculatorNumber<T> addition(CalculatorNumber<T> first, CalculatorNumber<T> second);
    CalculatorNumber<T> subtraction(CalculatorNumber<T> first, CalculatorNumber<T> second);
    CalculatorNumber<T> multiplication(CalculatorNumber<T> first, CalculatorNumber<T> second);
    CalculatorNumber<T> division(CalculatorNumber<T> first, CalculatorNumber<T> second);
}
