package controller.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatisticsController {


    public ObservableList getTimePeriod(){

        ObservableList<String> timelineList = FXCollections.observableArrayList();
        timelineList.add("Today");
        timelineList.add("Daily");
        timelineList.add("Weekly");
        timelineList.add("Monthly");
        timelineList.add("Annual");

        return timelineList;

    }
    public ObservableList getReportType(){

        ObservableList<String> reportTypeList = FXCollections.observableArrayList();
        reportTypeList.add("Patient Report");
        reportTypeList.add("Physician Report");
        reportTypeList.add("Sales Report");

        return reportTypeList;
    }
}
