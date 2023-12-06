package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
  public static String scanString() {
    String out = "";
    Scanner scanner = new Scanner(System.in);
    while(out.isEmpty()) {
      if (scanner.hasNextLine()){
        out = scanner.nextLine();
      }
      if (out.isEmpty()){
        System.out.println("String was empty, please try again.");
      }
    }
    return out;
  }
  public static String scanStringForTrainDeparture(String parameter){
    System.out.println("Enter the " + parameter + ":");
    return scanString();
  }
  public static LocalTime scanLocalTime() {
    LocalTime out = null;
    boolean validInput = false;
    Scanner scanner = new Scanner(System.in);
    while (!validInput) {
      if (scanner.hasNextLine()) {
        try{
          out = LocalTime.parse(scanner.nextLine());
          validInput = true;
        } catch (DateTimeException e) {
          System.out.println("Input must be at the format 'HH:mm' and " +
                  "must be between 00:00 and 23:59, please try again.");
        }
      }
    }
    return out;
  }
  public static LocalTime scanLocalTimeForTrainDeparture(String parameter) {
    System.out.println("Enter the " + parameter + " (HH:mm):");
    return scanLocalTime();
  }
  public static int scanInt(){
    int out = -1;
    Scanner scanner = new Scanner(System.in);
    while(out < 1) {
      if (scanner.hasNextInt()){
        try{
          out = scanner.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Input could not be read as a number, please try again.");
        }

      }
      if (out < 1){
        System.out.println("Number must be 1 or higher, please try again.");
      }
    }
    return out;
  }
  public static int scanIntForTrainDeparture(String parameter) {
    System.out.println("Enter the " + parameter + ":");
    return scanInt();
  }

  public static boolean scanIfTrackIsSet() {
    System.out.println("Is the track number decided yet (y/n)?");
    boolean out = false;
    String input = "";
    Scanner scanner = new Scanner(System.in);
    while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
      if (scanner.hasNextLine()){
        input = scanner.nextLine();
      }
      if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
        System.out.println("Input must be either 'y' for yes or 'n' for no, please try again");
      } else if (input.equalsIgnoreCase("y")) {
        out = true;
      }
    }
    return out;
  }
}
