package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for the TrainDepartureRegister class.
 */
public class TrainDepartureRegisterTest {
  private TrainDepartureRegister register;
  private TrainDeparture baseDeparture;

  @BeforeEach
  @DisplayName("Set up TrainDepartureRegister and TrainDeparture")
  void setUp() {
    register = new TrainDepartureRegister();
    baseDeparture = new TrainDeparture(
            "Line X", "Destination Y", "123",
            LocalTime.of(9, 0), LocalTime.of(0, 15), 3);
  }

  @Nested
  @DisplayName("Positive tests for TrainDepartureRegister")
  class PositiveTrainDepartureRegisterTest {
    @Test
    @DisplayName("checkIfRegistered() should return true if trainNumber is already registered")
    void testCheckIfRegisteredPositive() {
      register.registerTrainDeparture(baseDeparture);

      boolean isRegistered = register.checkIfRegistered(baseDeparture.getTrainNumber());

      assertTrue(isRegistered);
    }

    @Test
    @DisplayName("checkIfRegistered() should return false if trainNumber is not already registered")
    void testCheckIfRegisteredFalse() {
      register.registerTrainDeparture(baseDeparture);
      boolean isRegistered = register.checkIfRegistered(baseDeparture.getTrainNumber() + "1");

      assertFalse(isRegistered);
    }

    @Test
    @DisplayName("registerTrainDeparture() actually registers the train departure")
    public void testRegisterTrainDeparture() {
      register.registerTrainDeparture(baseDeparture);
      TrainDeparture retrievedDeparture = register
              .findDepartureByTrainNumber(baseDeparture.getTrainNumber());
      assertNotNull(retrievedDeparture);
      assertEquals(baseDeparture, retrievedDeparture);
    }

    @Test
    @DisplayName("removeTrainDeparture() removes train departure with specified train number")
    public void testRemoveTrainDeparturePositive() {
      register.registerTrainDeparture(baseDeparture);
      register.removeTrainDeparture(baseDeparture.getTrainNumber());

      assertNull(register.findDepartureByTrainNumber(baseDeparture.getTrainNumber()));
    }

    @Test
    @DisplayName("findDepartureByTrainNumber() returns the correct train departure")
    public void testFindDepartureByTrainNumberCorrect() {
      register.registerTrainDeparture(baseDeparture);
      TrainDeparture retrievedDeparture = register
              .findDepartureByTrainNumber(baseDeparture.getTrainNumber());

      assertEquals(baseDeparture, retrievedDeparture);
    }

    @Test
    @DisplayName("findDeparturesByTrainNumber() returns null if no train departures are found")
    public void testFindDeparturesByTrainNumberNull() {
      register.registerTrainDeparture(baseDeparture);
      TrainDeparture retrievedDeparture = register
              .findDepartureByTrainNumber(baseDeparture.getTrainNumber() + "1");

      assertNull(retrievedDeparture);
    }

    @Test
    @DisplayName("findDeparturesByDestination() returns the correct train departures")
    public void testFindDeparturesByDestinationCorrect() {

      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", baseDeparture.getDestination(), "321",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(baseDeparture);
      register.registerTrainDeparture(newDeparture);


      List<TrainDeparture> newDepartures = new ArrayList<>();
      newDepartures.add(baseDeparture);
      newDepartures.add(newDeparture);
      List<TrainDeparture> retrievedDepartures = register
              .findDeparturesByDestination(baseDeparture.getDestination());

      assertEquals(newDepartures, retrievedDepartures);
    }

    @Test
    @DisplayName("findDeparturesByDestination() returns empty "
            + "list if no train departures are found")
    public void testFindDeparturesByDestinationEmpty() {
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 0), LocalTime.of(0, 15), 3);

      register.registerTrainDeparture(baseDeparture);
      register.registerTrainDeparture(newDeparture);

      List<TrainDeparture> retrievedDepartures = register
              .findDeparturesByDestination("Destination Z");

      assertTrue(retrievedDepartures.isEmpty());
    }

    @Test
    @DisplayName("removeDepartedTrains() removes all trains that have departed")
    public void testRemoveDepartedTrainsRemoved() {
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", baseDeparture.getDestination(), "321",
              LocalTime.of(9, 15), LocalTime.of(0, 0), 5);

      register.registerTrainDeparture(baseDeparture);
      register.registerTrainDeparture(newDeparture);

      register.removeDepartedTrains(LocalTime.of(9, 16));

      assertTrue(register.findDeparturesByDestination(baseDeparture.getDestination()).isEmpty());
    }

    @Test
    @DisplayName("removeDepartedTrains() does not remove trains that have not departed")
    public void testRemoveDepartedTrainsNotRemoved() {
      register.registerTrainDeparture(baseDeparture);

      register.removeDepartedTrains(LocalTime.of(9, 15));

      assertNotNull(register.findDepartureByTrainNumber(baseDeparture.getTrainNumber()));
    }

    @Test
    @DisplayName("sortByDepartureTime() sorts the list of train "
            + "departures by their scheduled departure time ignoring delay")
    public void testSortByDepartureTime() {
      TrainDeparture newDeparture = new TrainDeparture(
              "Line X", "Destination Y", "321",
              LocalTime.of(9, 15), LocalTime.of(0, 0), 5);

      register.registerTrainDeparture(newDeparture);
      register.registerTrainDeparture(baseDeparture);

      List<TrainDeparture> sortedDepartures = register.sortByDepartureTime();

      assertEquals(baseDeparture, sortedDepartures.get(0));
      assertEquals(newDeparture, sortedDepartures.get(1));
    }
  }

  @Nested
  @DisplayName("Negative tests for TrainDepartureRegister")
  class NegativeTrainDepartureRegisterTest {
    @Test
    @DisplayName("registerTrainDeparture throws 'Ill.Arg.Exc.' on duplicate trainNumber")
    public void testRegisterDuplicateTrainNumber() {
      try {
        TrainDeparture newDeparture = new TrainDeparture(
                "Line B", "Destination Y", baseDeparture.getTrainNumber(),
                LocalTime.of(11, 0), LocalTime.of(0, 25), 7);
        register.registerTrainDeparture(baseDeparture);
        register.registerTrainDeparture(newDeparture);
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
