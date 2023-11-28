package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
  private TrainDepartureRegister trainDepartureRegister;
  private boolean finished;
  private Scanner scanner;
  private LocalTime time = LocalTime.of(0, 0);
  public void init(){
    trainDepartureRegister = new TrainDepartureRegister();
    finished = false;
    scanner = new Scanner(System.in);

  }
  public void start() {
    TrainDeparture trainDeparture1 = new TrainDeparture("A1", "Oslo",
            "161", LocalTime.of(12,45), LocalTime.of(0,0), 2);
    TrainDeparture trainDeparture2 = new TrainDeparture("L12", "Oslo",
            "160", LocalTime.of(13,0), LocalTime.of(0,0), 1);
    TrainDeparture trainDeparture3 = new TrainDeparture("B4", "Trondheim",
            "150", LocalTime.of(14,20), LocalTime.of(0,0));
    trainDepartureRegister.registerTrainDeparture(trainDeparture1);
    trainDepartureRegister.registerTrainDeparture(trainDeparture2);
    trainDepartureRegister.registerTrainDeparture(trainDeparture3);
    while (!finished) {
      showMenu();
      choice();
    }
  }
  private void showMenu(){
    System.out.println("\n");
    System.out.println("1. Show train departures.");
    System.out.println("2. Update current time.");
    System.out.println("3. Register new train departure.");
    System.out.println("4. Remove registered train departure");
    System.out.println("5. Find departure by train number.");
    System.out.println("6. Find departures to a destination.");
    System.out.println("7. Set new delay.");
    System.out.println("8. Set new track");
    System.out.println("9. Exit.\n");
    System.out.println("Please enter the number corresponding to wanted action:");
  }
  private void choice(){
    try {
      int menuChoice = Integer.parseInt(scanner.nextLine());

      switch (menuChoice){
        case 1 -> showTrainDepartures();
        case 2 -> updateTime();
        case 3 -> registerTrain();
        case 4 -> removeDeparture();
        case 5 -> searchTrainNumber();
        case 6 -> searchDestination();
        case 7 -> setNewDelay();
        case 8 -> setNewTrack();
        case 9 -> endProgram();
        default -> System.out.println("Invalid input! Please enter a valid number.");
      }

    }catch (NumberFormatException e){
      System.out.println("Invalid input! Please enter a valid number.");
    }
  }
  private void showTrainDepartures(){
    printTrainDeparturesList(trainDepartureRegister.sortByDepartureTime());
  }
  private void updateTime(){
    System.out.println("Input new time (hh:mm):");
    time = LocalTime.parse(scanner.nextLine());
    trainDepartureRegister.removeDepartedTrains(time);
  }
  private void registerTrain(){
    System.out.println("Input line:");
    String line = scanner.nextLine();
    System.out.println("Input destination:");
    String destination = scanner.nextLine();
    System.out.println("Input train number:");
    String trainNumber = scanner.nextLine();
    System.out.println("Input departure time (hh:mm):");
    LocalTime departureTime = LocalTime.parse(scanner.nextLine());
    System.out.println("Input delay (hh:mm):");
    LocalTime delay = LocalTime.parse(scanner.nextLine());
    TrainDeparture newTrainDeparture = new TrainDeparture(line, destination, trainNumber, departureTime, delay);
    trainDepartureRegister.registerTrainDeparture(newTrainDeparture);
  }
  private void removeDeparture() {
    System.out.println("Input train number:");
    String trainNumber = scanner.nextLine();
    trainDepartureRegister.removeTrainDeparture(trainNumber);
  }
  private void searchTrainNumber(){
    System.out.println("Input train number to search for:");
    String trainNumber = scanner.nextLine();
    printDisplayOverview();
    System.out.println(trainDepartureRegister.findDepartureByTrainNumber(trainNumber));
  }
  private void searchDestination(){
    System.out.println("Input your destination:");
    String destination = scanner.nextLine();
    printTrainDeparturesList(trainDepartureRegister.findDeparturesByDestination(destination));
  }
  private void setNewDelay(){
    System.out.println("Input train number to search for:");
    String trainNumber = scanner.nextLine();
    System.out.println("Input new delay:");
    try {
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setNewDelay(LocalTime.parse(scanner.nextLine()));
    } catch (DateTimeException e){
      System.out.println("Invalid time format. Please try again");
    }
  }
  private void setNewTrack() {
    System.out.println("Input train number to search for:");
    String trainNumber = scanner.nextLine();
    System.out.println("Input new track:");
    try {
      trainDepartureRegister.findDepartureByTrainNumber(trainNumber)
              .setTrack(Integer.parseInt(scanner.nextLine()));
    } catch (DateTimeException e){
      System.out.println("Invalid input. Please try again");
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
            "Track:",
            "Delay:");
    System.out.println("|" + "-".repeat(tableHeader.length()-2));
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
