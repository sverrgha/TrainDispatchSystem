package edu.ntnu.stud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TrainDepartureRegisterTest {

  @Test
  @DisplayName("registerTrainDeparture ")
  public void testRegisterTrainDeparture() {
    TrainDepartureRegister register = new TrainDepartureRegister();

    // Creating a new train departure
    TrainDeparture newDeparture = new TrainDeparture(
            "Line X", "Destination Y", "12345", LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

    // Registering the new train departure
    register.registerTrainDeparture(newDeparture);

    // Retrieving the registered train departure by its train number
    TrainDeparture retrievedDeparture = register.findDepartureByTrainNumber("12345");

    // Asserting that the retrieved departure matches the registered one
    assertNotNull(retrievedDeparture);
    assertEquals(newDeparture, retrievedDeparture);
  }

  @Test
  @DisplayName("registerTrainDeparture throws 'Ill.Arg.Exc.' on duplicate trainNumber")
  public void testRegisterDuplicateTrainNumber() {
    TrainDepartureRegister register = new TrainDepartureRegister();

    // Creating two train departures with the same train number
    TrainDeparture departure1 = new TrainDeparture(
            "Line A", "Destination X", "54321", LocalTime.of(10, 0), LocalTime.of(0, 20), 5);
    TrainDeparture departure2 = new TrainDeparture(
            "Line B", "Destination Y", "54321", LocalTime.of(11, 0), LocalTime.of(0, 25), 7);

    // Registering the first departure
    register.registerTrainDeparture(departure1);

    // Trying to register the second departure with the same train number
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
      register.registerTrainDeparture(departure2));

    // Verifying that an exception is thrown due to duplicate train number
    assertNotNull(exception);
    assertEquals("Train number already registered. Please try registration again.", exception.getMessage());
  }

}
