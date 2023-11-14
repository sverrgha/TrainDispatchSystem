package org.example;

import java.time.LocalTime;

/**
 * Represents information about a train departure.
 */
public class TrainDeparture {


  /**
   * The line or route of the train.
   */
  private final String line;


  /**
   * The destination of the train.
   */
  private final String destination;
  /**
   * The unique identifier of the train.
   */
  private final int trainNumber;

  /**
   * The scheduled departure time of the train.
   */
  private final LocalTime departureTime;

  /**
   * The delay in the departure time, if any.
   */
  private LocalTime delay;

  /**
   * Constructs a TrainDeparture object.
   *
   * @param line          The line or route of the train.
   * @param trainNumber   The unique identifier of the train.
   * @param departureTime The scheduled departure time of the train.
   * @param delay         The delay in the departure time, if any.
   */
  public TrainDeparture(String destination, String line, int trainNumber,
                        LocalTime departureTime, LocalTime delay) {
    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.delay = delay;
  }

  /**
   * Gets the line or route of the train.
   *
   * @return The line or route of the train.
   */
  public String getLine() {
    return line;
  }

  /**
   * Gets the destination of the train.
   *
   * @return The destination of the train.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Gets the unique identifier of the train.
   *
   * @return The unique identifier of the train.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Gets the scheduled departure time of the train.
   *
   * @return The scheduled departure time of the train.
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Gets the delay in the departure time, if any.
   *
   * @return The delay in the departure time.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Sets the delay in the departure time.
   *
   * @param newDelay The new delay in the departure time.
   */
  public void setDelay(LocalTime newDelay) {
    delay = newDelay;
  }

  public LocalTime departureTimeWithDelay() {
    LocalTime newTime = departureTime;
    newTime = newTime.plusHours(delay.getHour());
    newTime = newTime.plusMinutes(delay.getMinute());
    return newTime;
  }

  /**
   * Returns a string representation of the TrainDeparture object.
   *
   * @return A string representation of the TrainDeparture object.
   */
  @Override
  public String toString() {
    return destination + " | " + line + " | " + trainNumber + " | " + departureTime + " | " + delay;
  }
}
