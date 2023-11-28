package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

public class TrainDepartureTest {

  @Test
  public void testDepartureTimeWithDelay(){
    TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
            "161", LocalTime.of(12,45), LocalTime.of(0,0), 2);
    trainDeparture.setNewDelay(LocalTime.of(5,30));

    LocalTime expectedTimeWithDelay = LocalTime.of(18,15);

    assertEquals(expectedTimeWithDelay, trainDeparture.departureTimeWithDelay());
  }
  @Test
  public void testToStringWithTrack(){
    TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
            "161", LocalTime.of(12,45), LocalTime.of(0,0), 2);

    String expectedStringWithTrack = "12:45 | A1 | Oslo | 161 | 00:00 | 2";

    assertEquals(expectedStringWithTrack, trainDeparture.toString());
  }
  @Test
  public void testToStringWithoutTrack(){
    TrainDeparture trainDeparture = new TrainDeparture("A1", "Oslo",
            "161", LocalTime.of(12,45), LocalTime.of(0,0));

    String expectedStringWithTrack = "12:45 | A1 | Oslo | 161 | 00:00";

    assertEquals(expectedStringWithTrack, trainDeparture.toString());
  }
}
