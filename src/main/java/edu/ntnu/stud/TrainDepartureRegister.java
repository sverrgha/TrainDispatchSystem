package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a register with information about train departures departing from one station.
 * Goal: act as a register for train departures.
 *
 */
public class TrainDepartureRegister {

  /**
   * The list of registered train departures.
   */
  private final ArrayList<TrainDeparture> trainDepartures;

  /**
   * Constructs a TrainDepartureRegister object.
   */
  public TrainDepartureRegister() {
    trainDepartures = new ArrayList<>();
  }

  /**
     * Checks if a train departure is already registered.
     *
     * @param trainNumber The train number to check for.
     * @return True if the train departure is already registered, false otherwise.
     */
  public boolean checkIfRegistered(String trainNumber) {
    return trainDepartures.stream()
            .anyMatch(trainDeparture -> trainDeparture.getTrainNumber()
                    .equals(trainNumber));

  }

  /**
   * Registers a new train departure.
   *
   * @param newTrainDeparture The train departure to register.
   * @throws IllegalArgumentException if the train number is already registered.
   */
  public void registerTrainDeparture(TrainDeparture newTrainDeparture)
          throws IllegalArgumentException {
    boolean alreadyRegistered = checkIfRegistered(newTrainDeparture.getTrainNumber());
    if (alreadyRegistered) {
      throw new IllegalArgumentException("Train number already registered. "
              + "Please retry registration.");
    } else {
      trainDepartures.add(newTrainDeparture);
    }
  }

  /**
   * Removes a train departure from the register, if it is already registered.
   *
   * @param trainNumber The train number of the train departure to remove.
   * @throws IllegalArgumentException if the train number is not registered.
   */
  public void removeTrainDeparture(String trainNumber)
          throws IllegalArgumentException {
    boolean alreadyRegistered = checkIfRegistered(trainNumber);
    if (alreadyRegistered) {
      trainDepartures.removeIf(trainDeparture ->
              trainDeparture.getTrainNumber().equals(trainNumber));
    } else {
      throw new IllegalArgumentException("Train number is not registered, nothing happened.");
    }
  }

  /**
   * Finds a train departure by its train number.
   *
   * @param trainNumber The train number to search for.
   * @return The train departure with the specified train number, or null if not found.
   */
  public TrainDeparture findDepartureByTrainNumber(String trainNumber) {
    return trainDepartures.stream()
            .filter(trainDeparture -> trainDeparture.getTrainNumber().equals(trainNumber))
            .findFirst()
            .orElse(null);
  }

  /**
   * Finds train departures by destination.
   *
   * @param destination The destination to search for.
   * @return A list of train departures with the specified destination.
   */
  public List<TrainDeparture> findDeparturesByDestination(String destination) {
   
    return trainDepartures.stream()
            .filter(trainDeparture -> trainDeparture.getDestination().equalsIgnoreCase(destination))
            .collect(Collectors.toList());
  }

  /**
   * Removes trains that have departed based on their scheduled departure time with delay.
   *
   * @param time the updated time inputted by the user
   */
  public void removeDepartedTrains(LocalTime time) {
    trainDepartures.removeIf(trainDeparture
            -> trainDeparture.departureTimeWithDelay().isBefore(time));
  }

  /**
   * Sorts the list of train departures by their scheduled departure time without delay.
   *
   * @return A sorted list based on departure time of train departures.
   */
  public List<TrainDeparture> sortByDepartureTime() {
    ArrayList<TrainDeparture> sorted = new ArrayList<>(trainDepartures);
    sorted.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    return sorted;
  }
}