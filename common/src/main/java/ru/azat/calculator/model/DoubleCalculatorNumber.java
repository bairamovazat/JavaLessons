package ru.azat.calculator.model;

public class DoubleCalculatorNumber extends AbstractCalculatorNumber<Double> {

    private Double value;

    public DoubleCalculatorNumber(Double value) {
        this.value = value;
    }

    @Override
    public CalculatorNumber<Double> addition(CalculatorNumber<Double> number) {
        return new DoubleCalculatorNumber(this.getValue() + number.getValue());
    }

    @Override
    public CalculatorNumber<Double> subtraction(CalculatorNumber<Double> number) {
        return new DoubleCalculatorNumber(this.getValue() + number.getValue());
    }

    @Override
    public CalculatorNumber<Double> multiplication(CalculatorNumber<Double> number) {
        return new DoubleCalculatorNumber(this.getValue() + number.getValue());
    }

    @Override
    public CalculatorNumber<Double> division(CalculatorNumber<Double> number) {
        return new DoubleCalculatorNumber(this.getValue() + number.getValue());
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DoubleCalculatorNumber{" +
                "value=" + value +
                '}';
    }

    public static DoubleCalculatorNumber from(Double value) {
        return new DoubleCalculatorNumber(value);
    }
}
