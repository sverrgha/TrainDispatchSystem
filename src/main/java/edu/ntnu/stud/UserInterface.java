package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.List;

/**
 * This class contains methods for handling user input.
 * Goal: Handle input from the user.
 */
public class UserInterface {
  private TrainDepartureRegister trainDepartureRegister;
  private boolean finished;
  private LocalTime time;
  private static final String SHOW_DEPARTURES = "1";
  private static final String UPDATE_TIME = "2";
  private static final String REGISTER_NEW_TRAIN = "3";
  private static final String REMOVE_DEPARTURE = "4";
  private static final String SEARCH_TRAIN_NUMBER = "5";
  private static final String SEARCH_DESTINATION = "6";
  private static final String SET_NEW_DELAY = "7";
  private static final String SET_NEW_TRACK_NUMBER = "8";
  private static final String EXIT_PROGRAM = "9";

  /**
   * Constructs a UserInterface object.
   */
  public void init() {
    trainDepartureRegister = new TrainDepartureRegister();
    finished = false;
    time = LocalTime.of(0, 0);
    registerDefaultDepartures();
  }

  /**
   * Starts the user interface, and runs it until finished.
   */
  public void start() {
    while (!finished) {
      showMenu();
      choice();
    }
  }

  /**
   * Registers some preset train departures.
   */
  private void registerDefaultDepartures() {
    try {
      TrainDeparture trainDeparture1 = new TrainDeparture("A1", "Oslo",
              "161", LocalTime.of(12, 45), LocalTime.of(0, 0), 2);
      TrainDeparture trainDeparture2 = new TrainDeparture("L12", "Oslo",
              "160", LocalTime.of(13, 0), LocalTime.of(0, 45), 1);
      TrainDeparture trainDeparture3 = new TrainDeparture("B4", "Trondheim",
              "150", LocalTime.of(14, 20), LocalTime.of(0, 0));
      trainDepartureRegister.registerTrainDeparture(trainDeparture1);
      trainDepartureRegister.registerTrainDeparture(trainDeparture2);
      trainDepartureRegister.registerTrainDeparture(trainDeparture3);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prints the menu.
   */
  private static void showMenu() {
    System.out.println("\n");
    System.out.println("1. Show train departures.");
    System.out.println("2. Update current time.");
    System.out.println("3. Register new train departure.");
    System.out.println("4. Remove registered train departure");
    System.out.println("5. Find departure by train number.");
    System.out.println("6. Find departures to a destination.");
    System.out.println("7. Set new delay.");
    System.out.println("8. Set new track number");
    System.out.println("9. Exit.\n");
  }

  /**
   * Handles the user's choice.
   */
  private void choice() {
    String menuChoice = UserInput.scanString("your choice");

    switch (menuChoice) {
      case SHOW_DEPARTURES -> showTrainDepartures();
      case UPDATE_TIME -> updateTime();
      case REGISTER_NEW_TRAIN -> registerTrain();
      case REMOVE_DEPARTURE -> removeDeparture();
      case SEARCH_TRAIN_NUMBER -> searchTrainNumber();
      case SEARCH_DESTINATION -> searchDestination();
      case SET_NEW_DELAY -> setNewDelay();
      case SET_NEW_TRACK_NUMBER -> setNewTrackNumber();
      case EXIT_PROGRAM -> endProgram();
      default -> System.out.println("Invalid input! Please enter a valid number.");
    }
  }

  /**
   * Prints the list of train departures.
   */
  private void showTrainDepartures() {
    printTrainDeparturesList(trainDepartureRegister.sortByDepartureTime());
  }

  /**
   * Updates the time and removes departed trains.
   */
  private void updateTime() {
    LocalTime newTime = UserInput.scanLocalTime("new time");
    if (newTime.isBefore(time)) {
      System.out.println("Time cannot be set to a time before the current time. "
              + "Nothing changed.");
    } else {
      time = newTime;
    }
    trainDepartureRegister.removeDepartedTrains(time);
    System.out.println("Time was successfully updated to " + time);
  }

  /**
   * Registers a new train departure to the register.
   */
  private void registerTrain() {
    String trainNumber = UserInput.scanString("train number");
    boolean alreadyRegistered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (alreadyRegistered) {
      System.out.println("Train number already registered, returning to main menu...");
    } else {
      String line = UserInput.scanString("line");
      String destination = UserInput.scanString("destination");
      LocalTime departureTime = UserInput.scanLocalTime("departure time");
      LocalTime delay = UserInput.scanLocalTime("delay");
      boolean trackIsAssigned = UserInput.scanIfTrackIsAssigned();
      TrainDeparture newTrainDeparture;
      if (trackIsAssigned) {
        int trackNumber = UserInput.scanInt("track number");
        newTrainDeparture = new TrainDeparture(line, destination,
                trainNumber, departureTime, delay, trackNumber);
      } else {
        newTrainDeparture = new TrainDeparture(line, destination,
                trainNumber, departureTime, delay);
      }
      trainDepartureRegister.registerTrainDeparture(newTrainDeparture);
      System.out.println("Train departure was successfully registered.");
    }
  }

  /**
   * Removes a train departure from the register,
   * if it is found in register.
   *
   */
  private void removeDeparture() {
    String trainNumber = UserInput.scanString("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      trainDepartureRegister.removeTrainDeparture(trainNumber);
      System.out.println("Train departure was successfully removed.");
    } else {
      System.out.println("Train number not found in register.");
    }
  }

  /**
   * Searches for a train departure by its train number.
   */
  private void searchTrainNumber() {
    String trainNumber = UserInput.scanString("train number");
    TrainDeparture trainDeparture = trainDepartureRegister.findDepartureByTrainNumber(trainNumber);
    if (trainDeparture != null) {
      printDisplayOverview();
      System.out.println(trainDeparture);
    } else {
      System.out.println("Train number not found in register.");
    }
  }

  /**
   * Searches for train departures by destination.
   */
  private void searchDestination() {
    String destination = UserInput.scanString("destination");
    List<TrainDeparture> trainDepartures = trainDepartureRegister
            .findDeparturesByDestination(destination);
    if (!trainDepartures.isEmpty()) {
      printTrainDeparturesList(trainDepartures);
    } else {
      System.out.println("Could not find any departures to the destination.");
    }
  }

  /**
   * Sets a new delay for a train departure.
   */
  private void setNewDelay() {
    String trainNumber = UserInput.scanString("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      LocalTime delay = UserInput.scanLocalTime("delay");
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setDelay(delay);
      System.out.println("Delay was successfully set to " + delay);
    } else {
      System.out.println("Train number not found in register.");
    }

  }

  /**
   * Sets a new track number for a train departure.
   */
  private void setNewTrackNumber() {
    String trainNumber = UserInput.scanString("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      int trackNumber = UserInput.scanInt("track number");
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setTrackNumber(trackNumber);
      System.out.println("Track number was successfully set to " + trackNumber);
    } else {
      System.out.println("Train number not found in register.");
    }

  }

  /**
   * Ends the program.
   */
  private void endProgram() {
    System.out.println("Thanks for using the Train Dispatch App!");
    finished = true;
  }

  /**
   * Prints the overview of the train departures.
   */
  private void printDisplayOverview() {
    int width = 15;
    String tableHeader = String.format(("| %-" + width + "s ").repeat(6) + "|",
            "Departure time:",
            "Line:",
            "Train number:",
            "Destination:",
            "Delay:",
            "Track number:");
    System.out.println("|" + "-".repeat(tableHeader.length() / 2 - 3)
            + time
            + "-".repeat(tableHeader.length() / 2 - 3) + "|");
    System.out.println(tableHeader);
    System.out.println((("| %-" + width + "s ").repeat(6) + "|")
            .replace("%-" + width + "s", "-".repeat(width)));
  }

  /**
   * Prints a list of train departures with overlay.
   *
   * @param listToBePrinted The list of train departures to be printed.
   */
  private void printTrainDeparturesList(List<TrainDeparture> listToBePrinted) {
    printDisplayOverview();
    listToBePrinted.forEach(System.out::println);
  }
}
