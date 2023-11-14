package org.example;
import java.util.ArrayList;
import java.time.LocalTime;

public class UserInterface {
  TrainDepartureRegister trainDepartureRegister;
  public void init(){
    trainDepartureRegister = new TrainDepartureRegister();
  }
  public void start() {
    TrainDeparture trainDeparture1 = new TrainDeparture("Oslo", "A1", 161, LocalTime.of(12,45), LocalTime.of(0,0));
    TrainDeparture trainDeparture2 = new TrainDeparture("Bergen", "L12", 160, LocalTime.of(13,0), LocalTime.of(0,0));
    TrainDeparture trainDeparture3 = new TrainDeparture("Trondheim", "B4", 150, LocalTime.of(14,20), LocalTime.of(0,0));
    trainDepartureRegister.registerTrainDeparture(trainDeparture1);
    trainDepartureRegister.registerTrainDeparture(trainDeparture2);
    trainDepartureRegister.registerTrainDeparture(trainDeparture3);
    printTrainDeparturesList(trainDepartureRegister.sortByDepartureTime());
  }
  public void printTrainDeparturesList(ArrayList<TrainDeparture> listToBePrinted){
    for(TrainDeparture trainDeparture: listToBePrinted){
      System.out.println(trainDeparture);
    }
  }

}
