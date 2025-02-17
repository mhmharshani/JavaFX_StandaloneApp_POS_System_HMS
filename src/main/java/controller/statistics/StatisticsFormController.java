package controller.statistics;

import controller.doctor.DoctorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Doctor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StatisticsFormController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTimePeriod();
        plotPhysicianLoadGraph();

    }

    private void loadTimePeriod(){
        cmbDurationPhysician.setItems(new StatisticsController().gettimeperiod());
    }

    private void plotPhysicianLoadGraph(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Physician");
        cmbDurationPhysician.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            List<Doctor> allDoctor = new DoctorController().getAll();
            if(newValue!=null){
                String isSelected = newValue.toString();

                if(isSelected=="Daily"){
                    allDoctor.forEach(doctor ->{
                        series1.getData().add(new XYChart.Data(doctor.getId(),100));
                    });
                }
                else if(isSelected=="Weekly"){
                    allDoctor.forEach(doctor ->{
                        series1.getData().add(new XYChart.Data(doctor.getId(),60));
                    });
                }
                else if(isSelected=="Monthly"){
                    allDoctor.forEach(doctor ->{
                        series1.getData().add(new XYChart.Data(doctor.getId(),30));
                    });
                }
                else if(isSelected=="Annual"){
                    allDoctor.forEach(doctor ->{
                        series1.getData().add(new XYChart.Data(doctor.getId(),20));
                    });
                }
                else {
                    allDoctor.forEach(doctor ->{
                        series1.getData().add(new XYChart.Data(doctor.getId(),150));
                    });
                }

            }
            else{
                allDoctor.forEach(doctor ->{
                    series1.getData().add(new XYChart.Data(doctor.getId(),150));
                });
            }
        });
        bchartPhysician.getData().addAll(series1);

    }
}
