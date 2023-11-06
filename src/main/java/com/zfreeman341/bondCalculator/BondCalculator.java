package com.zfreeman341.bondCalculator;

public class BondCalculator {
        private static final int MAX_ITERATIONS = 10000;
        private static final double TOLERANCE = 1e-7;

        /**
     * Calculates the price of a bond given the coupon rate, years to maturity, face value, and yield
     * @param couponRate The coupon rate of the bond
     * @param years The number of years until maturity
     * @param face The face value of the bond
     * @param rate The yield of the bond
     * @return The price of the bond
     */
    public double calcPrice(double couponRate, int years, double face, double rate) {
        double couponPayment = couponRate * face; // This will convert the coupon rate to actual payment
        return calculateBondPrice(couponPayment, years, face, rate);
    }


        /**
     * Calculates the yield of a bond given the coupon rate, years to maturity, face value, and market price
     * @param couponRate The coupon rate of the bond
     * @param years The number of years until maturity
     * @param face The face value of the bond
     * @param marketPrice The market price of the bond
     * @return The yield of the bond
         */
    public double calcYield(double couponRate, int years, double face, double marketPrice) {
        double couponPayment = couponRate * face; // Convert the coupon rate to actual annual payment
        double lowerBound = 0.0;
        double upperBound = 1.0;
        double estimatedYield = couponRate;

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            double calculatedPrice = calculateBondPrice(couponPayment, years, face, estimatedYield);
            if (Math.abs(calculatedPrice - marketPrice) < TOLERANCE) {
                return estimatedYield;
            } else if (calculatedPrice < marketPrice) {
                upperBound = estimatedYield;
            } else {
                lowerBound = estimatedYield;
            }
            if (Math.abs(upperBound - lowerBound) < TOLERANCE) {
                break;
            }

            estimatedYield = (upperBound + lowerBound) / 2.0;
        }

        return estimatedYield; // If it hasn't converged in MAX_ITERATIONS, this will return the last estimate (optimization)
    }

    // Calculates the actual bond price
    private  double calculateBondPrice(double coupon, int years, double face, double yield) {
        double price = 0;
        for (int i = 1; i <= years; i++) {
            price += coupon / Math.pow(1 + yield, i); // Present Value of Coupon
        }
        price += face / Math.pow(1 + yield, years); // Present Value of Face Value
        return price;
    }
}
