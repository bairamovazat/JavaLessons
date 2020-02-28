package ru.azat.generic;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.azat.calculator.Calculator;
import ru.azat.calculator.AbstractCalculator;
import ru.azat.calculator.DoubleCalculator;
import ru.azat.calculator.model.DoubleCalculatorNumber;

/**
 * Mockito.mock, verify, when и argumentCaptor
 */
public class CalculatorTest {

    @Test
    public void testDoubleCalculatorAddition() {
        DoubleCalculatorNumber firstTestNumber = DoubleCalculatorNumber.from(1D);
        DoubleCalculatorNumber secondTestNumber = DoubleCalculatorNumber.from(2D);

        Calculator<Double> calculator = org.mockito.Mockito.mock(DoubleCalculator.class);
        Mockito.when(calculator.addition(firstTestNumber, secondTestNumber))
                .thenReturn(DoubleCalculatorNumber.from(4D));

        Assert.assertEquals(4D, calculator.addition(firstTestNumber, secondTestNumber).getValue(), 0.0);
    }

    @Test
    public void testDoubleCalculatorAdditionWithAnyArguments() {
        Calculator<Double> calculator = org.mockito.Mockito.mock(DoubleCalculator.class);

        Mockito.when(
                calculator.addition(Mockito.any(DoubleCalculatorNumber.class), Mockito.any(DoubleCalculatorNumber.class))
        ).thenReturn(DoubleCalculatorNumber.from(4D));

        Assert.assertEquals(4D, calculator.addition(DoubleCalculatorNumber.from(1D), DoubleCalculatorNumber.from(2D)).getValue(), 0.0);
        Assert.assertEquals(4D, calculator.addition(DoubleCalculatorNumber.from(2D), DoubleCalculatorNumber.from(1D)).getValue(), 0.0);
        Assert.assertEquals(4D, calculator.addition(DoubleCalculatorNumber.from(2D), DoubleCalculatorNumber.from(2D)).getValue(), 0.0);
    }

    @Test
    public void testDoubleCalculatorAdditionSimpleVerify() {
        Calculator<Double> calculator = org.mockito.Mockito.mock(DoubleCalculator.class);

        Mockito.when(calculator.addition(Mockito.any(DoubleCalculatorNumber.class), Mockito.any(DoubleCalculatorNumber.class)))
                .thenReturn(DoubleCalculatorNumber.from(4D));

        calculator.addition(DoubleCalculatorNumber.from(-1D), DoubleCalculatorNumber.from(-1D));
        calculator.addition(DoubleCalculatorNumber.from(2D), DoubleCalculatorNumber.from(2D));

        Mockito.verify(calculator, Mockito.times(2))
                .addition(Mockito.any(DoubleCalculatorNumber.class), Mockito.any(DoubleCalculatorNumber.class));
    }

    @Test
    public void testAbstractCalculatorAdditionSimpleArgumentCaptor() {
        ArgumentCaptor<DoubleCalculatorNumber> doubleCalculatorNumberArgumentCaptor = ArgumentCaptor.forClass(DoubleCalculatorNumber.class);

        Calculator<Double> calculator = Mockito.mock(DoubleCalculator.class);

        Mockito.when(calculator.addition(Mockito.any(DoubleCalculatorNumber.class), Mockito.any(DoubleCalculatorNumber.class)))
                .thenReturn(DoubleCalculatorNumber.from(4D));

        calculator.addition(new DoubleCalculatorNumber(1D), new DoubleCalculatorNumber(2D));

        Mockito.verify(calculator, Mockito.times(1))
                .addition(doubleCalculatorNumberArgumentCaptor.capture(), doubleCalculatorNumberArgumentCaptor.capture());

        Assert.assertEquals(doubleCalculatorNumberArgumentCaptor.getAllValues().size(), 2);

        Assert.assertEquals(doubleCalculatorNumberArgumentCaptor.getAllValues().get(0).getValue(), 1D, 0.0);
        Assert.assertEquals(doubleCalculatorNumberArgumentCaptor.getAllValues().get(1).getValue(), 2D, 0.0);

    }
}
