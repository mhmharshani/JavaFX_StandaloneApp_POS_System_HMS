package controller.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatisticsController {


    public ObservableList gettimeperiod(){

        ObservableList<String> timelineList = FXCollections.observableArrayList();
        timelineList.add("Today");
        timelineList.add("Daily");
        timelineList.add("Weekly");
        timelineList.add("Monthly");
        timelineList.add("Annual");

        return timelineList;

    }
}
