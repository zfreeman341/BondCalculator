package com.zfreeman341.bondCalculator.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class StopwatchTest {
  private static final Runnable LONG_RUNNING_OPERATION = () -> {};
    private static final Instant STARTING_TIME = Instant.parse("2023-10-01T10:00:00.000Z");

  @Mock
  private Clock clock;
  @InjectMocks
  private Stopwatch stopwatch;

  @Test
  public void testRunWithTimer() {
when(clock.instant()).thenReturn(STARTING_TIME, STARTING_TIME.plusMillis(250));

    Duration timeElapsed = stopwatch.runWithTimer(LONG_RUNNING_OPERATION);

    assertEquals(timeElapsed.toNanos(), Duration.ofMillis(250).toNanos());
  }
}

