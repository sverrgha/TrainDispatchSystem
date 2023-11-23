package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a register of train departures.
 * The register allows for the registration, retrieval,
 * and manipulation of train departure information.
 */
public class TrainDepartureRegister {

  /**
   * The list of registered train departures.
   */
  private ArrayList<TrainDeparture> trainDepartures = new ArrayList<>();

  /**
   * Registers a new train departure if it doesn't exist already.
   *
   * @param newTrainDeparture The train departure to be registered.
   * @return true if the train with the same number is already registered, false otherwise.
   */
  public boolean registerTrainDeparture(TrainDeparture newTrainDeparture) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == newTrainDeparture.getTrainNumber()) {
        return true;
      }
    }
    trainDepartures.add(newTrainDeparture);
    return false;
  }

  /**
   * Finds a train departure by its train number.
   *
   * @param trainNumber The train number to search for.
   * @return The train departure with the specified train number, or null if not found.
   */
  public TrainDeparture findDepartureByTrainNumber(int trainNumber) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == trainNumber) {
        return trainDeparture;
      }
    }
    return null;
  }

  /**
   * Finds train departures by destination.
   *
   * @param destination The destination to search for.
   * @return A list of train departures with the specified destination.
   */
  public ArrayList<TrainDeparture> findDeparturesByDestination(String destination) {
    ArrayList<TrainDeparture> foundDepartures = new ArrayList<>();
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getDestination().equalsIgnoreCase(destination)) {
        foundDepartures.add(trainDeparture);
      }
    }
    return foundDepartures;
  }

  /**
   * Removes trains that have already departed based on their scheduled departure time with delay.
   */
  public void removeDepartedTrains() {
    trainDepartures.removeIf(trainDeparture
            -> trainDeparture.departureTimeWithDelay().isBefore(LocalTime.now()));
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