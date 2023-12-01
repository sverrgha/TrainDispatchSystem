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
    setTrackNumber(trackNumber);
    setNewDelay(delay);

    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
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
    setTrackNumber(trackNumber);
    setNewDelay(delay);

    this.line = line;
    this.destination = destination;
    this.trainNumber = trainNumber;
    this.departureTime = departureTime;
  }
  private static void verifyStringParameter(String parameter, String parameterName)
          throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter '"
              + parameterName + "' was a blank string, please retry registration.");
    }
  }
  private static void verifyLocalTimeParameter(LocalTime time, String parameterName)
          throws IllegalArgumentException {
    if (time == null) {
      throw new IllegalArgumentException("The time for the parameter '"
              + parameterName + "' time was null, please retry registration.");
    }
    else if (time.isBefore(LocalTime.of(0, 0))
            || time.isAfter(LocalTime.of(23,59))) {
      throw new IllegalArgumentException("The time for the parameter'"
              + parameterName + "' must be between 00:00 and 23:59, please retry registration.");
    }
  }
  private static void verifyTrackNumberNumber(int trackNumber)
          throws IllegalArgumentException {
    if (trackNumber < -1) {
      throw new IllegalArgumentException("Track number must be -1 or a positive number. Please retry");
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
    verifyLocalTimeParameter(newDelay, "newDelay");
    delay = newDelay;
  }

  public int getTrackNumber(){
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
            ((trackNumber == -1) ? "": trackNumber),
            ((delay.equals(LocalTime.MIDNIGHT)) ? "": delay));
    }
}
