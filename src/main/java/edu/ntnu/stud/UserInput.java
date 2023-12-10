package edu.ntnu.stud;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains methods for reading user input.
 * Goal: read user input.
 */
public class UserInput {
  /**
   * Reads a string from the user, and ensures that the String is valid.
   *
   * @return The string read from the user.
   */
  public static String scanString(String parameter) {
    System.out.println("Enter the " + parameter + ":");
    String out = "";
    Scanner scanner = new Scanner(System.in);
    while (out.isEmpty()) {
      if (scanner.hasNextLine()) {
        out = scanner.nextLine();
      }
      if (out.isEmpty()) {
        System.out.println("String was empty, please try again.");
      }
    }
    return out;
  }

  /**
   * Reads a LocalTime from the user, and ensures that the LocalTime is valid.
   *
   * @return The LocalTime read from the user.
   */
  public static LocalTime scanLocalTime(String parameter) {
    System.out.println("Enter the " + parameter + " (HH:mm):");
    LocalTime out = null;
    Scanner scanner = new Scanner(System.in);
    while (out == null) {
      if (scanner.hasNextLine()) {
        try {
          out = LocalTime.parse(scanner.nextLine());
        } catch (DateTimeException e) {
          System.out.println("Input must be at the format 'HH:mm' and "
                  + "must be between 00:00 and 23:59, please try again.");
        }
      }
    }
    return out;
  }

  /**
   * Reads an integer from the user, and ensures that the integer is valid.
   *
   * @return The integer read from the user.
   */
  public static int scanInt(String parameter) {
    System.out.println("Enter the " + parameter + ":");
    int out = -1;
    Scanner scanner = new Scanner(System.in);
    while (out < 1) {
      if (scanner.hasNextInt()) {
        try {
          out = scanner.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Input could not be read as a number, please try again.");
        }

      }
      if (out < 1) {
        System.out.println("Number must be 1 or higher, please try again.");
      }
    }
    return out;
  }

  /**
     * Reads 'y' for yes or 'n' for no from user to know if track number is assigned.
     *
     * @return The boolean corresponding to answer from user.
     */
  public static boolean scanIfTrackIsAssigned() {
    System.out.println("Is the track number has been assigned (y/n)?");
    boolean out = false;
    String input = "";
    Scanner scanner = new Scanner(System.in);
    while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
      if (scanner.hasNextLine()) {
        input = scanner.nextLine();
      }
      switch (input.toLowerCase()) {
        case "y" -> out = true;
        case "n" -> out = false;
        default -> System.out.println("Input must be either 'y' "
        + "for yes or 'n' for no, please try again");
      }
    }
    return out;
  }
}
