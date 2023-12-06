package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a register of train departures.
 * The register allows for the registration, retrieval,
 * and manipulation of train departure information.
 */
public class TrainDepartureRegister {

  /**
   * The list of registered train departures.
   */
  private final ArrayList<TrainDeparture> trainDepartures = new ArrayList<>();

  public boolean checkIfRegistered(String trainNumber) {
    return trainDepartures.stream()
            .anyMatch(trainDeparture -> trainDeparture.getTrainNumber()
                    .equals(trainNumber));

  }
  /**
   * Registers a new train departure if it doesn't exist already.
   *
   * @param newTrainDeparture The train departure to be registered.
   */
  public void registerTrainDeparture(TrainDeparture newTrainDeparture) {
    boolean alreadyRegistered = checkIfRegistered(newTrainDeparture.getTrainNumber());
    if (alreadyRegistered) {
      throw new IllegalArgumentException("Train number already registered. Please retry registration.");
    }
    trainDepartures.add(newTrainDeparture);
  }

  public void removeTrainDeparture(String trainNumber){
    trainDepartures.removeIf(trainDeparture -> trainDeparture.getTrainNumber().equals(trainNumber));
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
   * @param newTime the updated time inputted by the user
   */
  public void removeDepartedTrains(LocalTime newTime) {
    trainDepartures.removeIf(trainDeparture
            -> trainDeparture.departureTimeWithDelay().isBefore(newTime));
  }

  /**
   * Sorts the list of train departures by their scheduled departure time without delay.
   *
   * @return A sorted list based on departure time of train departures.
   */
  public ArrayList<TrainDeparture> sortByDepartureTime() {
    ArrayList<TrainDeparture> sorted = new ArrayList<>(trainDepartures);
    sorted.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    return sorted;
  }
}