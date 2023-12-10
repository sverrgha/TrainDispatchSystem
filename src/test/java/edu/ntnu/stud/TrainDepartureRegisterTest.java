package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {
  @Nested
  @DisplayName("Positive tests for TrainDepartureRegister")
  class PositiveTrainDepartureRegisterTest {
     @BeforeAll
    public static void setUp() {
      System.out.println("Setting up test");
    }
    @Test
    @DisplayName("checkIfRegistered() should return true if trainNumber is already registered")
    void testCheckIfRegisteredPositive() {
      TrainDepartureRegister trainDepartureRegister = new TrainDepartureRegister();
      TrainDeparture newTrainDeparture = new TrainDeparture("Line1", "Dest1",
              "123", LocalTime.now(), LocalTime.now());
      trainDepartureRegister.registerTrainDeparture(newTrainDeparture);

      boolean isRegistered = trainDepartureRegister.checkIfRegistered("123");

      assertTrue(isRegistered);
    }

    @Test
    @DisplayName("checkIfRegistered() should return false if trainNumber is not already registered")
    void testCheckIfRegisteredFalse() {
      // Assuming trainDepartures has TrainDeparture objects
      TrainDepartureRegister trainDepartureRegister = new TrainDepartureRegister();
      TrainDeparture newTrainDeparture = new TrainDeparture("Line1", "Dest1",
              "123", LocalTime.now(), LocalTime.now());
      trainDepartureRegister.registerTrainDeparture(newTrainDeparture);

      boolean isRegistered = trainDepartureRegister.checkIfRegistered("321");

      assertFalse(isRegistered);
    }

    @Test
    @DisplayName("registerTrainDeparture() actually registers the train departure")
    public void testRegisterTrainDeparture() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "12345",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);
      register.registerTrainDeparture(newDeparture);
      TrainDeparture retrievedDeparture = register.findDepartureByTrainNumber("12345");
      assertNotNull(retrievedDeparture);
      assertEquals(newDeparture, retrievedDeparture);
    }

    @Test
    @DisplayName("removeTrainDeparture() removes train departure with specified train number")
    public void testRemoveTrainDeparturePositive() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture);
      register.removeTrainDeparture("123");

      assertNull(register.findDepartureByTrainNumber("12345"));
    }

    @Test
    @DisplayName("findDepartureByTrainNumber() returns the correct train departure")
    public void testFindDepartureByTrainNumberCorrect() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture);
      TrainDeparture retrievedDeparture = register.findDepartureByTrainNumber("123");

      assertEquals(newDeparture, retrievedDeparture);
    }

    @Test
    @DisplayName("findDeparturesByTrainNumber() returns null if no train departures are found")
    public void testFindDeparturesByTrainNumberNull() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture);
      TrainDeparture retrievedDeparture = register.findDepartureByTrainNumber("321");

      assertNull(retrievedDeparture);
    }

    @Test
    @DisplayName("findDeparturesByDestination() returns the correct train departures")
    public void testFindDeparturesByDestinationCorrect() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture1 = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);
      TrainDeparture newDeparture2 = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture1);
      register.registerTrainDeparture(newDeparture2);


      List<TrainDeparture> newDepartures = new ArrayList<>();
      newDepartures.add(newDeparture1);
      newDepartures.add(newDeparture2);
      List<TrainDeparture> retrievedDepartures = register
              .findDeparturesByDestination("Destination Y");

      assertEquals(newDepartures, retrievedDepartures);
    }

    @Test
    @DisplayName("findDeparturesByDestination() returns empty "
            + "list if no train departures are found")
    public void testFindDeparturesByDestinationEmpty() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture1 = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);
      TrainDeparture newDeparture2 = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture1);
      register.registerTrainDeparture(newDeparture2);

      List<TrainDeparture> retrievedDepartures = register
              .findDeparturesByDestination("Destination Z");

      assertTrue(retrievedDepartures.isEmpty());
    }

    @Test
    @DisplayName("removeDepartedTrains() removes all trains that have departed")
    public void testRemoveDepartedTrainsRemoved() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture1 = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);
      TrainDeparture newDeparture2 = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 15), LocalTime.of(0, 0), 5);

      register.registerTrainDeparture(newDeparture1);
      register.registerTrainDeparture(newDeparture2);

      register.removeDepartedTrains(LocalTime.of(9, 16));

      assertTrue(register.findDeparturesByDestination("Destination Y").isEmpty());
    }

    @Test
    @DisplayName("removeDepartedTrains() does not remove trains that have not departed")
    public void testRemoveDepartedTrainsNotRemoved() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(newDeparture);

      register.removeDepartedTrains(LocalTime.of(9, 15));

      assertNotNull(register.findDepartureByTrainNumber("123"));
    }

    @Test
    @DisplayName("sortByDepartureTime() sorts the list of train "
            + "departures by their scheduled departure time ignoring delay")
    public void testSortByDepartureTime() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      TrainDeparture newDeparture1 = new TrainDeparture(
              "Line X", "Destination Y", "123",
              LocalTime.of(9, 0), LocalTime.of(0, 0), 3);
      TrainDeparture newDeparture2 = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 15), LocalTime.of(0, 0), 5);

      register.registerTrainDeparture(newDeparture2);
      register.registerTrainDeparture(newDeparture1);

      List<TrainDeparture> sortedDepartures = register.sortByDepartureTime();

      assertEquals(newDeparture1, sortedDepartures.get(0));
      assertEquals(newDeparture2, sortedDepartures.get(1));
    }
  }

  @Nested
  @DisplayName("Negative tests for TrainDepartureRegister")
  class NegativeTrainDepartureRegisterTest {
    @Test
    @DisplayName("registerTrainDeparture throws 'Ill.Arg.Exc.' on duplicate trainNumber")
    public void testRegisterDuplicateTrainNumber() {
      try {
        TrainDepartureRegister register = new TrainDepartureRegister();
        TrainDeparture departure1 = new TrainDeparture(
                "Line A", "Destination X", "54321",
                LocalTime.of(10, 0), LocalTime.of(0, 20), 5);
        TrainDeparture departure2 = new TrainDeparture(
                "Line B", "Destination Y", "54321",
                LocalTime.of(11, 0), LocalTime.of(0, 25), 7);
        register.registerTrainDeparture(departure1);
        register.registerTrainDeparture(departure2);
      } catch (IllegalArgumentException e) {
        assertEquals("Train number already registered. "
                + "Please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("removeTrainDeparture() throws Ill.Arg.Exc if train number is not registered")
    public void testRemoveTrainDepartureNegative() {
      TrainDepartureRegister register = new TrainDepartureRegister();
      try {
        register.removeTrainDeparture("123");
      } catch (IllegalArgumentException e) {
        assertEquals("Train number is not registered, nothing happened.", e.getMessage());
      }
    }
  }

}
