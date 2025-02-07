package controller.appointment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import controller.DashboardFormController;
import controller.doctor.DoctorController;
import controller.patient.PatientController;
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
import model.Appointment;
import model.Doctor;
import model.DoctorSession;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnReserve.setDisable(true);
        lblAppointId.setText(new AppointmentController().nextId());
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
        boolean isAdded = new AppointmentController().addAppointment(appointment);
        Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
        alert.show();
        if (isAdded){
            lblAppointId.setText(new AppointmentController().nextId());
            dpDate.setValue(null);
            cmbTime.setValue(null);
            lblNumber.setText("");
            cmbStatus.setValue(null);
            txtSpeciality.setText("");
            cmbDoctorId.setValue(null);
            cmbPatientId.setValue(null);
            txtSessionId.setText("");
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Appointment appointment = new AppointmentController().searchAppointmentById(txtSearch.getText());
        if (appointment!=null){
            boolean isDeleted = new AppointmentController().deleteAppointment(txtSearch.getText());
            if(isDeleted){
                lblAppointId.setText(new AppointmentController().nextId());
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
    void btnPayNowOnAction(ActionEvent event) {

        //-----------------Load Payment Form -------------------------------------------
//        URL resource = this.getClass().getResource("/view/payments_form.fxml");
//        assert  resource != null;
//        Parent load = null;
//        try {
//            load = FXMLLoader.load(resource);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        AnchorPane loadFormContent = new DashboardFormController().getLoadFormContent();
//        loadFormContent.getChildren().clear();
//        loadFormContent.getChildren().add(load);

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
        Appointment appointment = new AppointmentController().searchAppointmentById(txtSearch.getText());
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
        cmbPatientId.setItems(new PatientController().getPatientId());
    }

    private void loadDoctorId(){
        cmbDoctorId.setItems(new DoctorController().getDoctorId());
    }

    private void setSpeciality(){
        String id = cmbDoctorId.getValue().toString();
        txtSpeciality.setText(new DoctorController().searchDoctorById(id).getSpeciality());
    }

    private void loadStatus(){
        cmbStatus.setItems(new AppointmentController().getAppointmentStatus());
    }

    private void setSessionTime(String id){
        List<DoctorSession> doctorSessionsArrayList = new DoctorController().searchDoctorSessionByDocId(id);
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
        DoctorSession doctorSession = new DoctorController().searchDoctorSession(id,date,time);
        if(doctorSession!=null){
            txtSessionId.setText(doctorSession.getId());
            String nextNumber = new AppointmentController().nextNumber(doctorSession.getId());
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

        List<Appointment> appointmentArrayList = new AppointmentController().getAll();
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
