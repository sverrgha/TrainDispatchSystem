package edu.ntnu.stud;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {
  private TrainDepartureRegister trainDepartureRegister;
  private boolean finished = false;
  private LocalTime time = LocalTime.of(0, 0);
  private static final String SHOW_MENU = "1";
  private static final String UPDATE_TIME = "2";
  private static final String REGISTER_NEW_TRAIN = "3";
  private static final String REMOVE_DEPARTURE = "4";
  private static final String SEARCH_TRAIN_NUMBER = "5";
  private static final String SEARCH_DESTINATION = "6";
  private static final String SET_NEW_DELAY = "7";
  private static final String SET_NEW_TRACK_NUMBER = "8";
  private static final String EXIT_PROGRAM = "9";

  public void init(){
    trainDepartureRegister = new TrainDepartureRegister();
    registerDefaultDepartures();
  }
  public void start() {
    while (!finished) {
      showMenu();
      choice();
    }
  }
  private void registerDefaultDepartures() {
    try {
      TrainDeparture trainDeparture1 = new TrainDeparture("A1", "Oslo",
              "161", LocalTime.of(12,45), LocalTime.of(0,0), 2);
      TrainDeparture trainDeparture2 = new TrainDeparture("L12", "Oslo",
              "160", LocalTime.of(13,0), LocalTime.of(0,0), 1);
      TrainDeparture trainDeparture3 = new TrainDeparture("B4", "Trondheim",
              "150", LocalTime.of(14,20), LocalTime.of(0,0));
      trainDepartureRegister.registerTrainDeparture(trainDeparture1);
      trainDepartureRegister.registerTrainDeparture(trainDeparture2);
      trainDepartureRegister.registerTrainDeparture(trainDeparture3);
    } catch (IllegalArgumentException e){
      System.out.println(e.getMessage());
    }
  }
  private static void showMenu(){
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
    System.out.println("Please enter the number corresponding to wanted action:");
  }
  private void choice(){
    String menuChoice = UserInput.scanString();

    switch (menuChoice) {
      case SHOW_MENU -> showTrainDepartures();
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
  private void showTrainDepartures(){
    printTrainDeparturesList(trainDepartureRegister.sortByDepartureTime());
  }
  private void updateTime(){
    time = UserInput.scanLocalTimeForTrainDeparture("new time");
    trainDepartureRegister.removeDepartedTrains(time);
  }
  private void registerTrain(){
    String line = UserInput.scanStringForTrainDeparture("line");
    String destination = UserInput.scanStringForTrainDeparture("destination");
    String trainNumber = UserInput.scanStringForTrainDeparture("train number");
    LocalTime departureTime = UserInput.scanLocalTimeForTrainDeparture("departure time");
    LocalTime delay = UserInput.scanLocalTimeForTrainDeparture("delay");
    boolean trackIsSet = UserInput.scanIfTrackIsSet();

    TrainDeparture newTrainDeparture;
    if (trackIsSet) {
      int trackNumber = UserInput.scanIntForTrainDeparture("track number");
      newTrainDeparture = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay, trackNumber);
    }
    else {
      newTrainDeparture = new TrainDeparture(line, destination,
              trainNumber, departureTime, delay);
    }
    trainDepartureRegister.registerTrainDeparture(newTrainDeparture);
    /*try {
      trainDepartureRegister.registerTrainDeparture(newTrainDeparture);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }*/
  }
  private void removeDeparture() {
    String trainNumber = UserInput.scanStringForTrainDeparture("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      trainDepartureRegister.removeTrainDeparture(trainNumber);
    }
    else {
      System.out.println("Train number not found in register.");
    }
  }
  private void searchTrainNumber(){
    String trainNumber = UserInput.scanStringForTrainDeparture("train number");
    TrainDeparture trainDeparture = trainDepartureRegister.findDepartureByTrainNumber(trainNumber);
    if (trainDeparture != null) {
      printDisplayOverview();
      System.out.println(trainDeparture);
    } else {
      System.out.println("Train number not found in register.");
    }
  }
  private void searchDestination(){
    String destination = UserInput.scanStringForTrainDeparture("destination");
    List<TrainDeparture> trainDepartures = trainDepartureRegister.findDeparturesByDestination(destination);
    if (!trainDepartures.isEmpty()) {
      printTrainDeparturesList(trainDepartures);
    }
    else {
      System.out.println("Could not find any departures to the destination.");
    }
  }
  private void setNewDelay(){
    String trainNumber = UserInput.scanStringForTrainDeparture("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      LocalTime delay = UserInput.scanLocalTimeForTrainDeparture("delay");
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setNewDelay(delay);
    }
    else {
      System.out.println("Train number not found in register.");
    }

  }
  private void setNewTrackNumber() {
    String trainNumber = UserInput.scanStringForTrainDeparture("train number");
    boolean registered = trainDepartureRegister.checkIfRegistered(trainNumber);
    if (registered) {
      int trackNumber = UserInput.scanIntForTrainDeparture("track number");
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setTrackNumber(trackNumber);
    }
    else {
      System.out.println("Train number not found in register.");
    }

  }
  private void endProgram(){
    System.out.println("Thanks for using the Train Dispatch App!");
    finished = true;
  }
  private void printDisplayOverview(){
    int width = 15;
    String tableHeader = String.format(("| %-" + width + "s ").repeat(6) + "|",
            "Departure time:",
            "Line:",
            "Train number:",
            "Destination:",
            "Track number:",
            "Delay:");
    System.out.println("|" + "-".repeat(tableHeader.length()/2-3)
            + time
            + "-".repeat(tableHeader.length()/2-3) + "|");
    System.out.println(tableHeader);
    System.out.println((("| %-" + width + "s ").repeat(6) + "|")
            .replace("%-" + width + "s", "-".repeat(width)));
  }
  private void printTrainDeparturesList(List<TrainDeparture> listToBePrinted){
    printDisplayOverview();
    listToBePrinted.forEach(System.out::println);
  }
}
