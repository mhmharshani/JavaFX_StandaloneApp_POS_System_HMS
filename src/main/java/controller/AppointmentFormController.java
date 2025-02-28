package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dto.Appointment;
import dto.DoctorSession;
import service.ServiceFactory;
import service.custom.AppointmentService;
import service.custom.DoctorService;
import service.custom.PatientService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentFormController implements Initializable {

    @FXML
    private JFXButton btnMRecord;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private JFXCheckBox checkIndoor;

    @FXML
    private JFXComboBox cmbStatus;

    @FXML
    private JFXComboBox cmbTime;

    @FXML
    private TableColumn colAId;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colDocId;

    @FXML
    private TableColumn colNo;

    @FXML
    private TableColumn colPId;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTime;

    @FXML
    private TableColumn colSessionId;

    @FXML
    private DatePicker dpDate;

    @FXML
    private Label lblNumber;

    @FXML
    private TableView tblAppointment;

    @FXML
    private JFXComboBox<?> cmbDoctorId;

    @FXML
    private JFXComboBox<?> cmbPatientId;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblAppointId;

    @FXML
    private TextField txtSpeciality;

    @FXML
    private TextField txtSessionId;

    @Inject
    PatientService patientService;
    DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
    AppointmentService appointmentService = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnReserve.setDisable(true);
        lblAppointId.setText(appointmentService.nextId());
        loadPatientId();
        loadDoctorId();
        loadStatus();

        cmbDoctorId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                setSpeciality();
                if(dpDate.getValue()!=null){
                    setSessionTime(cmbDoctorId.getValue().toString());
                }
            }
        });

        dpDate.setOnAction(actionEvent -> {
            if(cmbDoctorId.getValue()!=null){
                setSessionTime(cmbDoctorId.getValue().toString());

            }
        });

        cmbTime.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                setSessionIdAndNumber(cmbDoctorId.getValue().toString(),dpDate.getValue().toString(),cmbTime.getValue().toString());
            }
        });

        checkBoxState();
    }

    @FXML
    void btnBook(ActionEvent event) {
        Appointment appointment = new Appointment(
                lblAppointId.getText(),
                dpDate.getValue().toString(),
                cmbTime.getValue().toString(),
                Integer.parseInt(lblNumber.getText()),
                cmbStatus.getValue().toString(),
                cmbDoctorId.getValue().toString(),
                cmbPatientId.getValue().toString(),
                txtSessionId.getText()
        );

        try {
            boolean isAdded = appointmentService.addAppointment(appointment);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
            alert.show();
            if (isAdded){
                lblAppointId.setText(appointmentService.nextId());
                dpDate.setValue(null);
                cmbTime.setValue(null);
                lblNumber.setText("");
                cmbStatus.setValue(null);
                txtSpeciality.setText("");
                cmbDoctorId.setValue(null);
                cmbPatientId.setValue(null);
                txtSessionId.setText("");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Appointment appointment = appointmentService.searchAppointmentById(txtSearch.getText());
        if (appointment!=null){
            boolean isDeleted = appointmentService.deleteAppointment(txtSearch.getText());
            if(isDeleted){
                lblAppointId.setText(appointmentService.nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Appointment Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnMedicalRecordOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) throws IOException {

       // -----------------Load Payment Form -------------------------------------------
        DashboardFormController dashboardFormController= DashboardFormController.dashboardFormController;
        URL resource = dashboardFormController.getClass().getResource("/view/payments_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);
        AnchorPane anchorPane=dashboardFormController.getAnchorPane();
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(load);

        //-------------------End Form-----------------------------------------------------

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        tblAppointment.refresh();
    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Appointment appointment = appointmentService.searchAppointmentById(txtSearch.getText());
        if (appointment!=null){
            loadTable(appointment);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Appointment Found !!").show();
        }
    }

    @FXML
    void btnShowAppointmentOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnAddPatientOnAction(ActionEvent event) throws IOException{

        //-----------------Load Patient Form -------------------------------------------
//        new DashboardFormController().btnPatientOnAction(new ActionEvent());

    }


    private void loadPatientId(){
        cmbPatientId.setItems(patientService.getPatientId());
    }

    private void loadDoctorId(){
        cmbDoctorId.setItems(doctorService.getDoctorId());
    }

    private void setSpeciality(){
        String id = cmbDoctorId.getValue().toString();
        txtSpeciality.setText(doctorService.searchDoctorById(id).getSpeciality());
    }

    private void loadStatus(){
        cmbStatus.setItems(appointmentService.getAppointmentStatus());
    }

    private void setSessionTime(String id){
        List<DoctorSession> doctorSessionsArrayList = doctorService.searchDoctorSessionByDocId(id);
        ObservableList<String> timeObservableArray = FXCollections.observableArrayList();
        if((!doctorSessionsArrayList.isEmpty()) && (dpDate.getValue()!=null)){
            doctorSessionsArrayList.forEach(doctorSession -> {
                if(dpDate.getValue().toString().equals(doctorSession.getDate())){
                    timeObservableArray.add(doctorSession.getTime());
                }
            });
        }

        if(timeObservableArray.isEmpty()){
            timeObservableArray.add("No Session Time");
            cmbTime.setItems(timeObservableArray);
        }
        else {
            cmbTime.setItems(timeObservableArray);
        }

    }

    private void setSessionIdAndNumber(String id,String date, String time){
        DoctorSession doctorSession = doctorService.searchDoctorSession(id,date,time);
        if(doctorSession!=null){
            txtSessionId.setText(doctorSession.getId());
            String nextNumber = appointmentService.nextNumber(doctorSession.getId());
            String numberLimit = doctorSession.getNumberLimit();

            if(numberLimit.equals("Unlimited")){
                lblNumber.setText(nextNumber);
            }
            else{
                if((Integer.parseInt(nextNumber))<=(Integer.parseInt(numberLimit))){
                    lblNumber.setText(nextNumber);
                }
                else{
                    lblNumber.setText("");
                    new Alert(Alert.AlertType.ERROR,"Session Limit Exceeded!! Try Another Session...").show();
                }
            }

        }
        else{
            txtSessionId.setText("");
            lblNumber.setText("");
        }

    }

    public void loadTable(){
        colAId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colNo.setCellValueFactory(new PropertyValueFactory<>("number"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));

        List<Appointment> appointmentArrayList = appointmentService.getAll();
        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
        appointmentArrayList.forEach(appointment -> {
            appointmentObservableList.add(appointment);
        });

        tblAppointment.setItems(appointmentObservableList);
    }

    public void loadTable(Appointment appointment){
        colAId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colNo.setCellValueFactory(new PropertyValueFactory<>("number"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));

        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
        appointmentObservableList.add(appointment);

        tblAppointment.setItems(appointmentObservableList);
    }

    void checkBoxState(){
        checkIndoor.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                if(newValue){
                    btnReserve.setDisable(false);
                }
                else{
                    btnReserve.setDisable(true);
                }
            }
        });

    }

}
