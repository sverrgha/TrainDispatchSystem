package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 * Goal: run the UserInterface, via the main method.
 */
public class TrainDispatchApp {
    /**
     * The main method of the application.
     *
     * @param args arguments for the main method.
     */
  public static void main(String[] args) {
    UserInterface program = new UserInterface();
    program.init();
    program.start();
  }
}
