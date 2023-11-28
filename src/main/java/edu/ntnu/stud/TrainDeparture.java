package edu.ntnu.stud;

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
  private final String trainNumber;

  /**
   * The scheduled departure time of the train.
   */
  private final LocalTime departureTime;

  /**
   * The delay in the departure time, if any.
   */
  private LocalTime delay;

  /**
   * The track for the train.
   */
  private int track;

  /**
   * Constructs a TrainDeparture object, if track is specified/decided.
   *
   * @param line          The line or route of the train.
   * @param destination   The destination of the train.
   * @param trainNumber   The unique identifier of the train.
   * @param departureTime The scheduled departure time of the train.
   * @param delay         The delay in the departure time, if any.
   * @param track         The track for the train
   */
  public TrainDeparture(String line, String destination, String trainNumber,
                        LocalTime departureTime, LocalTime delay, int track) {
    verifyStringParameter(line, "Line");
    verifyStringParameter(destination, "Destination");
    verifyStringParameter(trainNumber, "Train number");
    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.delay = delay;
    this.track = track;
  }
  /**
   * Constructs a TrainDeparture object, if track is not specified/decided.
   *
   * @param line          The line or route of the train.
   * @param destination   The destination of the train.
   * @param trainNumber   The unique identifier of the train.
   * @param departureTime The scheduled departure time of the train.
   * @param delay         The delay in the departure time, if any.
   */

  public TrainDeparture(String line, String destination, String trainNumber,
                        LocalTime departureTime, LocalTime delay) {
    verifyStringParameter(line, "Line");
    verifyStringParameter(destination, "Destination");
    verifyStringParameter(trainNumber, "Train number");

    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.delay = delay;
    this.track = -1;
  }
  private void verifyStringParameter(String parameter, String parameterName)
          throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter '"
              + parameterName + "' was a blank string, please retry registration.");
    }
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
  public String getTrainNumber() {
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
  public void setNewDelay(LocalTime newDelay) {
    delay = newDelay;
  }

  /**
   * Sets the delay in the departure time.
   *
   * @param track The new track the train is using.
   */
  public void setTrack(int track) {
    this.track = track;
  }

  /**
   * Calculates the departure time including delay.
   *
   * @return The departure time including the delay.
   */
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
    return String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |",
            departureTime,
            line,
            trainNumber,
            destination,
            ((track == -1) ? "": track),
            ((delay.equals(LocalTime.MIDNIGHT)) ? "": delay));
    }
}
