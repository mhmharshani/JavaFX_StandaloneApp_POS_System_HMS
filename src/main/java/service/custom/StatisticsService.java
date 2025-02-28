package service.custom;

import javafx.collections.ObservableList;
import service.SuperService;

public interface StatisticsService extends SuperService {

    ObservableList getTimePeriod();

    ObservableList getReportType();
}
