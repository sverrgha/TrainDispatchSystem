package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the TrainDeparture class.
 */
public class TrainDepartureTest {
  @Test
  public void testValidTrainDepartureConstruction() {
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
  public void testInvalidTrainDepartureConstructionNullDelay() {
    String line = "Line B";
    String destination = "Destination Z";
    String trainNumber = "67890";
    LocalTime departureTime = LocalTime.of(12, 45);
    LocalTime delay = null; // Null delay

    assertThrows(IllegalArgumentException.class, () ->
      new TrainDeparture(line, destination, trainNumber, departureTime, delay));
  }

  @Test
  public void testInvalidTrainDepartureConstructionBlankLine() {
    String line = ""; // Blank line
    String destination = "Destination Y";
    String trainNumber = "54321";
    LocalTime departureTime = LocalTime.of(8, 0);
    LocalTime delay = LocalTime.of(0, 10);
    int track = 5;

    assertThrows(IllegalArgumentException.class, () ->
            new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
  }

  @Test
  public void testInvalidTrainDepartureConstructionInvalidTrack() {
    String line = "Line C";
    String destination = "Destination W";
    String trainNumber = "13579";
    LocalTime departureTime = LocalTime.of(15, 20);
    LocalTime delay = LocalTime.of(0, 5);
    int track = -2; // Negative track number

    assertThrows(IllegalArgumentException.class, () ->
      new TrainDeparture(line, destination, trainNumber, departureTime, delay, track));
  }

  @Test
  public void testDepartureTimeWithDelay() {
    TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
            "161", LocalTime.of(12, 45), LocalTime.of(0, 0), 2);
    trainDeparture.setDelay(LocalTime.of(5, 30));

    LocalTime expectedTimeWithDelay = LocalTime.of(18, 15);

    assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
  }

  @Test
  public void testToString() {
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
            ((trackNumber == -1) ? "" : trackNumber));

    assertEquals(expectedOutput, departure.toString());
  }
}
