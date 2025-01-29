package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class StatisticsFormController {

    @FXML
    private BarChart bchartPhysician;

    @FXML
    private ComboBox cmbDurationPatient;

    @FXML
    private ComboBox cmbDurationPhysician;

    @FXML
    private ComboBox cmbDurationSales;

    @FXML
    private LineChart lchartExpenditure;

    @FXML
    private LineChart lchartIncome;

    @FXML
    private LineChart lchartPatient;

    @FXML
    private LineChart lchartProfit;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
