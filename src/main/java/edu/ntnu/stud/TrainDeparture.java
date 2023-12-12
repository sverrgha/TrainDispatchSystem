package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * Represents information about a train departure.
 * Contains two constructors, one if trackNumber is specified/decided and one if not.
 * constructor verifies that the input is valid, and throws IllegalArgumentException if not.
 * Includes getters for all attributes and setters for delay and trackNumber.
 * Goal: act as a model for a train departure.
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
   * The trackNumber for the train.
   */
  private int trackNumber;

  /**
   * Constructs a TrainDeparture object, if trackNumber is specified/decided.
   *
   * @param line          The line or route of the train.
   * @param destination   The destination of the train.
   * @param trainNumber   The unique identifier of the train.
   * @param departureTime The scheduled departure time of the train.
   * @param delay         The delay in the departure time, if any.
   * @param trackNumber         The trackNumber for the train
   */
  public TrainDeparture(String line, String destination, String trainNumber,
                        LocalTime departureTime, LocalTime delay, int trackNumber) {
    verifyStringParameter(line, "Line");
    verifyStringParameter(destination, "Destination");
    verifyStringParameter(trainNumber, "Train number");
    verifyLocalTimeParameter(departureTime, "Departure time");

    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    setTrackNumber(trackNumber);
    setDelay(delay);
  }
  /**
   * Constructs a TrainDeparture object, if trackNumber is not specified/decided.
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
    verifyLocalTimeParameter(departureTime, "Departure time");


    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
    this.trackNumber = -1;
    setDelay(delay);
  }

  /**
   * Verifies that the string parameter is not null or blank.
   *
   * @param parameter     The string parameter to be verified.
   * @param parameterName The name of the parameter to be used in the exception message.
   * @throws IllegalArgumentException If the string parameter is null or blank.
   */
  private static void verifyStringParameter(String parameter, String parameterName)
          throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter '"
              + parameterName + "' was a blank string, please retry registration.");
    }
  }

  /**
   * Verifies that the LocalTime parameter is not null.
   *
   * @param time     The LocalTime parameter to be verified.
   * @param parameterName The name of the parameter to be used in the exception message.
   * @throws IllegalArgumentException If the LocalTime parameter is null.
   */
  private void verifyLocalTimeParameter(LocalTime time, String parameterName)
          throws IllegalArgumentException {
    if (time == null) {
      throw new IllegalArgumentException("The time for the parameter '"
              + parameterName + "' time was null, please retry registration.");
    }
    if (parameterName.equals("delay")) {
      int maxHours = 23 - departureTime.getHour();
      int maxMin = 59 - departureTime.getMinute();
      if (time.isAfter(LocalTime.of(maxHours, maxMin))) {
        throw new IllegalArgumentException("Delay + departure time must be max 23:59, "
                + "delay can't be more than " + maxHours + ":" + maxMin);
      }
    }
  }

  /**
   * Verifies that the trackNumber parameter is not null.
   *
   * @param trackNumber     The trackNumber parameter to be verified.
   * @throws IllegalArgumentException If the trackNumber parameter is null.
   */
  private static void verifyTrackNumberNumber(int trackNumber)
          throws IllegalArgumentException {
    if (trackNumber < -1) {
      throw new IllegalArgumentException("Track number must be -1 "
              + "or a positive number. Please retry");
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
   * Sets the delay if the input is valid.
   *
   * @param delay The new delay in the departure time.
   */
  public void setDelay(LocalTime delay) {
    verifyLocalTimeParameter(delay, "delay");
    this.delay = delay;
  }

  /**
     * Gets the trackNumber the train is using.
     *
     * @return The trackNumber the train is using.
     */
  public int getTrackNumber() {
    return  trackNumber;
  }

  /**
   * Sets the delay in the departure time.
   *
   * @param trackNumber The new trackNumber the train is using.
   */
  public void setTrackNumber(int trackNumber) {
    verifyTrackNumberNumber(trackNumber);
    this.trackNumber = trackNumber;
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
            ((delay.equals(LocalTime.MIDNIGHT)) ? "" : delay),
            ((trackNumber == -1) ? "" : trackNumber));

  }
}
