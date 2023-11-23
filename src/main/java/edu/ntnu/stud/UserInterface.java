package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
  private TrainDepartureRegister trainDepartureRegister;
  private boolean finished = false;
  private Scanner scanner;

  public void init(){
    trainDepartureRegister = new TrainDepartureRegister();
    scanner = new Scanner(System.in);
  }
  public void start() {
    TrainDeparture trainDeparture1 = new TrainDeparture("A1", "Oslo",
            161, LocalTime.of(12,45), LocalTime.of(0,0), 2);
    TrainDeparture trainDeparture2 = new TrainDeparture("L12", "Oslo",
            160, LocalTime.of(13,0), LocalTime.of(0,0), 1);
    TrainDeparture trainDeparture3 = new TrainDeparture("B4", "Trondheim",
            150, LocalTime.of(14,20), LocalTime.of(0,0));
    trainDepartureRegister.registerTrainDeparture(trainDeparture1);
    trainDepartureRegister.registerTrainDeparture(trainDeparture2);
    trainDepartureRegister.registerTrainDeparture(trainDeparture3);
    while (!finished) {
      showMenu();
      choice();
    }
  }
  public void showMenu(){
    System.out.println("\n1. Show train departures.");
    System.out.println("2. Update current time.");
    System.out.println("3. Register new TrainDeparture.");
    System.out.println("4. Find departure by train number.");
    System.out.println("5. Find departures to a destination.");
    System.out.println("9. Exit.\n");
    System.out.println("Please enter a number:");
  }
  public void choice(){
    int menuChoice = Integer.parseInt(scanner.nextLine());

    switch (menuChoice){
      case 1:
        printTrainDeparturesList(trainDepartureRegister.sortByDepartureTime());
        break;
      case 2:
        trainDepartureRegister.removeDepartedTrains();
        break;
      case 3 -> registerTrain();
      case 4:
        System.out.println("Input train number to search for:");
        int trainNumber = Integer.parseInt(scanner.nextLine());
        System.out.println(trainDepartureRegister.findDepartureByTrainNumber(trainNumber));
        break;
      case 5:
        System.out.println("Input your destination:");
        String destination = scanner.nextLine();
        printTrainDeparturesList(trainDepartureRegister.findDeparturesByDestination(destination));
        break;
      case 9:
        System.out.println("Thanks for using the Train Dispatch App!");
        finished = true;
        break;
    }
  }
  public void registerTrain(){
    System.out.println("Input line:");
    String line = scanner.nextLine();
    System.out.println("Input destination:");
    String destination = scanner.nextLine();
    System.out.println("Input train number");
    int trainNumber = Integer.parseInt(scanner.nextLine());
    System.out.println("Input departure time (hh:mm)");
    LocalTime departureTime = LocalTime.parse(scanner.nextLine());
    System.out.println("Input delay (hh:mm)");
    LocalTime delay = LocalTime.parse(scanner.nextLine());
    TrainDeparture newTrainDeparture = new TrainDeparture(line, destination, trainNumber, departureTime, delay);
    trainDepartureRegister.registerTrainDeparture(newTrainDeparture);
  }
  public void printTrainDeparturesList(ArrayList<TrainDeparture> listToBePrinted){
    for(TrainDeparture trainDeparture: listToBePrinted){
      System.out.println(trainDeparture);
    }
  }

}
