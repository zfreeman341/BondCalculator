package com.zfreeman341.bondCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BondCalculatorTest {
    // Using a delta of 0.001 to handle floating-point inaccuracies.
    private static final double ACCEPTABLE_PERCISION = 1e-7;

    private BondCalculator bondCalculator;

    @BeforeEach
    public void setUp() {
        this.bondCalculator = new BondCalculator();
    }

    @ParameterizedTest(name = "Price = {4}: coupon={0}, years={1}, face={2}, rate={3}")
    @CsvSource({
        "0.10, 5, 1000, 0.15, 832.3922451",
        "0.15, 5, 1000, 0.15, 1000.0000000",
        "0.10, 5, 1000, 0.08, 1079.8542007",
        "0.10, 30, 1000, 0.19, 528.8807463"
    })
    public void testCalcPrice(double couponRate, int years, double face, double rate, double expectedPrice) {
        double calculatedPrice = bondCalculator.calcPrice(couponRate, years, face, rate);

        assertEquals(expectedPrice, calculatedPrice, ACCEPTABLE_PERCISION);
    }

    @ParameterizedTest(name = "Yield = {4}: coupon={0}, years={1}, face={2}, marketPrice={3}")
    @CsvSource({
        "0.10, 5, 1000, 832.4, 0.1499974",
        "0.10, 5, 1000, 1000, 0.1000000",
        "0.10, 5, 1000, 1079.85, 0.0800010",
        "0.10, 30, 1000, 528.8807463, 0.1900000"
    })
    public void testCalcYield(double couponRate, int years, double face, double marketPrice, double expectedYield) {
        double calculatedYield = bondCalculator.calcYield(couponRate, years, face, marketPrice);

        assertEquals(expectedYield, calculatedYield, ACCEPTABLE_PERCISION);
    }
}