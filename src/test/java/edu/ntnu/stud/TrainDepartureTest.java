package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the TrainDeparture class.
 */
public class TrainDepartureTest {
  String line;
  String destination;
  String trainNumber;
  LocalTime departureTime;
  LocalTime delay;
  int trackNumber;

  @BeforeEach
  @DisplayName("Set up TrainDeparture-parameters")
  void setUp() {
    line = "Line A";
    destination = "Destination X";
    trainNumber = "12345";
    departureTime = LocalTime.of(10, 30);
    delay = LocalTime.of(0, 15);
    trackNumber = 2;
  }

  @Nested
  @DisplayName("Positive tests for TrainDeparture")
  class PositiveTrainDepartureTest {
    @Test
    @DisplayName("Test valid TrainDeparture construction with track number")
    public void testValidTrainDepartureConstructionWithTrackNumber() {
      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay, trackNumber);

      assertEquals(line, departure.getLine());
      assertEquals(destination, departure.getDestination());
      assertEquals(trainNumber, departure.getTrainNumber());
      assertEquals(departureTime, departure.getDepartureTime());
      assertEquals(delay, departure.getDelay());
      assertEquals(trackNumber, departure.getTrackNumber());
    }

    @Test
    @DisplayName("Test valid TrainDeparture construction without track number")
    public void testValidTrainDepartureConstructionWithoutTrackNumber() {
      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay);

      assertEquals(line, departure.getLine());
      assertEquals(destination, departure.getDestination());
      assertEquals(trainNumber, departure.getTrainNumber());
      assertEquals(departureTime, departure.getDepartureTime());
      assertEquals(delay, departure.getDelay());
      assertEquals(-1, departure.getTrackNumber());
    }

    @Test
    @DisplayName("Test valid departureTimeWithDelay() with delay")
    public void testDepartureTimeWithDelay() {
      TrainDeparture trainDeparture = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay, trackNumber);

      LocalTime expectedTimeWithDelay = LocalTime.of(10, 45);

      assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
    }

    @Test
    @DisplayName("Test valid departureTimeWithDelay() without delay")
    public void testDepartureTimeWithDelayWithoutDelay() {
      TrainDeparture trainDeparture = new TrainDeparture(line, destination,
              trainNumber, departureTime, LocalTime.of(0, 0), trackNumber);

      LocalTime expectedTimeWithDelay = LocalTime.of(10, 30);

      assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
    }

    @Test
    @DisplayName("Test valid toString() with delay and trackNumber")
    public void testToStringWithDelayAndTrackNumber() {
      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay, trackNumber);

      String expectedOutput = String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
              departureTime,
              line,
              trainNumber,
              destination,
              ((delay.equals(LocalTime.MIDNIGHT)) ? "" : delay),
              trackNumber);

      assertEquals(expectedOutput, departure.toString());
    }

    @Test
    @DisplayName("Test valid toString() without zero delay and no trackNumber")
    public void testToStringWithoutDelayAndTrackNumber() {
      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, LocalTime.of(0, 0));

      String expectedOutput = String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
              departureTime,
              line,
              trainNumber,
              destination,
              "",
              "");

      assertEquals(expectedOutput, departure.toString());
    }
  }

  @Nested
  @DisplayName("Negative tests for TrainDeparture")
  class NegativeTrainDepartureTest {
    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank line")
    public void testInvalidTrainDepartureConstructionBlankLine() {

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture("", destination, trainNumber, departureTime, delay, trackNumber));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank destination")
    public void testInvalidTrainDepartureConstructionBlankDestination() {
      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, "", trainNumber, departureTime, delay, trackNumber));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank train number")
    public void testInvalidTrainDepartureConstructionBlankTrainNumber() {
      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, "", departureTime, delay, trackNumber));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with null departure time")
    public void testInvalidTrainDepartureConstructionNullDepartureTime() {
      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, null, delay, trackNumber));

    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with null delay")
    public void testInvalidTrainDepartureConstructionNullDelay() {

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, null));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with "
            + "departure time plus delay more than 23:59")
    public void testInvalidTrainDepartureConstructionInvalidDelay() {
      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber,
                      departureTime, LocalTime.of(23, 59)));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction "
            + "with negative track number")
    public void testInvalidTrainDepartureConstructionInvalidTrack() {

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, -2));
    }
  }
}
