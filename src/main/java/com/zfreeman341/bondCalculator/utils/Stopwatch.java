package com.zfreeman341.bondCalculator.utils;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
  private final Clock clock;

  public Stopwatch(Clock clock) {
    this.clock = clock;
  }

  public Duration runWithTimer(Runnable operation) {
    Instant startTime = clock.instant();
    operation.run();
    return Duration.between(startTime, clock.instant());
  }
}

