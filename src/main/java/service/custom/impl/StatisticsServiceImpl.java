package service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.custom.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {

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
