package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the TrainDeparture class.
 */
public class TrainDepartureTest {

  @Nested
    @DisplayName("Positive tests for TrainDeparture")
    class PositiveTrainDepartureTest {
    @Test
    @DisplayName("Test valid TrainDeparture construction with track number")
    public void testValidTrainDepartureConstructionWithTrackNumber() {
      String line = "Line A";
      String destination = "Destination X";
      String trainNumber = "12345";
      LocalTime departureTime = LocalTime.of(10, 30);
      LocalTime delay = LocalTime.of(0, 15);
      int track = 3;

      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay, track);

      assertEquals(line, departure.getLine());
      assertEquals(destination, departure.getDestination());
      assertEquals(trainNumber, departure.getTrainNumber());
      assertEquals(departureTime, departure.getDepartureTime());
      assertEquals(delay, departure.getDelay());
      assertEquals(track, departure.getTrackNumber());
    }
    @Test
    @DisplayName("Test valid TrainDeparture construction without track number")
    public void testValidTrainDepartureConstructionWithoutTrackNumber() {
      String line = "Line A";
      String destination = "Destination X";
      String trainNumber = "12345";
      LocalTime departureTime = LocalTime.of(10, 30);
      LocalTime delay = LocalTime.of(0, 15);

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
      TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
              "161", LocalTime.of(12, 45), LocalTime.of(0, 0), 2);

      LocalTime expectedTimeWithDelay = LocalTime.of(12, 45);

      assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
    }
    @Test
    @DisplayName("Test valid departureTimeWithDelay() without delay")
    public void testDepartureTimeWithDelayWithoutDelay() {
      TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
              "161", LocalTime.of(12, 45), LocalTime.of(0, 0), 2);

      LocalTime expectedTimeWithDelay = LocalTime.of(12, 45);

      assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
    }
    @Test
    @DisplayName("Test valid toString() with delay and trackNumber")
    public void testToStringWithDelayAndTrackNumber() {
      String line = "Line A";
      String destination = "Destination X";
      String trainNumber = "12345";
      LocalTime departureTime = LocalTime.of(10, 30);
      LocalTime delay = LocalTime.of(0, 15);
      int trackNumber = 3;

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
    @DisplayName("Test valid toString() without delay and trackNumber")
    public void testToStringWithoutDelayAndTrackNumber() {
      String line = "Line A";
      String destination = "Destination X";
      String trainNumber = "12345";
      LocalTime departureTime = LocalTime.of(10, 30);
      LocalTime delay = LocalTime.of(0, 0);

      TrainDeparture departure = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay);

      String expectedOutput = String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
              departureTime,
              line,
              trainNumber,
              destination,
              "",
              ((delay.equals(LocalTime.MIDNIGHT)) ? "" : delay));

      assertEquals(expectedOutput, departure.toString());
    }
  }

  @Nested
  @DisplayName("Negative tests for TrainDeparture")
  class NegativeTrainDepartureTest {
    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank line")
    public void testInvalidTrainDepartureConstructionBlankLine() {
      String line = "";
      String destination = "Destination Y";
      String trainNumber = "54321";
      LocalTime departureTime = LocalTime.of(8, 0);
      LocalTime delay = LocalTime.of(0, 10);
      int track = 5;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank destination")
    public void testInvalidTrainDepartureConstructionBlankDestination() {
      String line = "Line B";
      String destination = "";
      String trainNumber = "67890";
      LocalTime departureTime = LocalTime.of(12, 45);
      LocalTime delay = LocalTime.of(0, 10);
      int track = 5;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with blank train number")
    public void testInvalidTrainDepartureConstructionBlankTrainNumber() {
      String line = "Line B";
      String destination = "Destination Z";
      String trainNumber = "";
      LocalTime departureTime = LocalTime.of(12, 45);
      LocalTime delay = LocalTime.of(0, 10);
      int track = 5;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with null departure time")
    public void testInvalidTrainDepartureConstructionNullDepartureTime() {
      String line = "Line B";
      String destination = "Destination Z";
      String trainNumber = "67890";
      LocalTime departureTime = null;
      LocalTime delay = LocalTime.of(0, 10);
      int track = 5;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));

    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with null delay")
    public void testInvalidTrainDepartureConstructionNullDelay() {
      String line = "Line B";
      String destination = "Destination Z";
      String trainNumber = "67890";
      LocalTime departureTime = LocalTime.of(12, 45);
      LocalTime delay = null;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay));
    }
    @Test
    @DisplayName("Test invalid TrainDeparture construction with departure time plus delay more than 23:59")
    public void testInvalidTrainDepartureConstructionInvalidDelay() {
      String line = "Line B";
      String destination = "Destination Z";
      String trainNumber = "67890";
      LocalTime departureTime = LocalTime.of(12, 45);
      LocalTime delay = LocalTime.of(12, 45);

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay));
    }

    @Test
    @DisplayName("Test invalid TrainDeparture construction with negative track number")
    public void testInvalidTrainDepartureConstructionInvalidTrack() {
      String line = "Line C";
      String destination = "Destination W";
      String trainNumber = "13579";
      LocalTime departureTime = LocalTime.of(15, 20);
      LocalTime delay = LocalTime.of(0, 5);
      int track = -2;

      assertThrows(IllegalArgumentException.class, () ->
              new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
    }




  }
}
