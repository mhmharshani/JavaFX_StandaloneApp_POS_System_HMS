package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import dto.Doctor;
import service.ServiceFactory;
import service.custom.DoctorService;
import service.custom.StatisticsService;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ComboBox cmbReportType;

    @FXML
    private LineChart lchartExpenditure;

    @FXML
    private LineChart lchartIncome;

    @FXML
    private LineChart lchartPatient;

    @FXML
    private LineChart lchartProfit;

    DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
    StatisticsService statisticsService = ServiceFactory.getInstance().getServiceType(ServiceType.CHART);

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTimePeriod();
        loadReportType();

        plotPhysicianLoadGraph();

    }

    private void loadTimePeriod(){
        ObservableList timePeriod = statisticsService.getTimePeriod();
        cmbDurationPhysician.setItems(timePeriod);
        cmbDurationPatient.setItems(timePeriod);
        cmbDurationSales.setItems(timePeriod);
    }

    private void loadReportType(){
        cmbReportType.setItems(statisticsService.getReportType());
    }

    private String currentDate(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String format = dateFormat.format(date);
        return format;
    }

    private void plotPhysicianLoadGraph(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Physician");
        cmbDurationPhysician.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            List<Doctor> allDoctor = doctorService.getAll();
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


    public void cmbReportTypeOnAction(ActionEvent actionEvent) {

    }
}
