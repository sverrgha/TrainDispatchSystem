package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TrainDepartureRegister {
  private ArrayList<TrainDeparture> trainDepartures = new ArrayList<>();

  public boolean registerTrainDeparture(TrainDeparture newTrainDeparture) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == newTrainDeparture.getTrainNumber()) {
        return true;
      }
    }
    trainDepartures.add(newTrainDeparture);
    return false;
  }

  public TrainDeparture findDepartureByTrainNumber(int trainNumber) {
    for (TrainDeparture trainDeparture : trainDepartures) {
      if (trainDeparture.getTrainNumber() == trainNumber) {
        return trainDeparture;
      }
    }
    return null;
  }

  public ArrayList<TrainDeparture> findDeparturesByDestination(String destination) {
    ArrayList<TrainDeparture> foundDepartures = new ArrayList<>();
    for (TrainDeparture trainDeparture : trainDepartures) {
      if ((trainDeparture.getDestination().equalsIgnoreCase(destination))) {
        foundDepartures.add(trainDeparture);
      }
    }
    return foundDepartures;
  }

  public void removeDepartedTrains() {
    trainDepartures.removeIf(trainDeparture -> trainDeparture.departureTimeWithDelay().isBefore(LocalTime.now()));
  }

  public ArrayList<TrainDeparture> sortByDepartureTime(){
    ArrayList<TrainDeparture> sorted = new ArrayList<>(trainDepartures);
    sorted.sort(Comparator.comparing(TrainDeparture::departureTimeWithDelay));
    return sorted;

  }
}
