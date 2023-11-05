package com.zfreeman341.bondCalculator;

import static javax.swing.SwingUtilities.invokeLater;

import com.zfreeman341.bondCalculator.utils.Stopwatch;
import java.time.Clock;

public class BondCalculatorApplication {

  public static void main(String[] args) {
    BondCalculator bondCalculator = new BondCalculator();
    Clock clock = Clock.systemDefaultZone();
    Stopwatch stopwatch = new Stopwatch(clock);

    invokeLater(() -> {
      new BondCalculatorGui(bondCalculator, stopwatch);
    });
  }
}