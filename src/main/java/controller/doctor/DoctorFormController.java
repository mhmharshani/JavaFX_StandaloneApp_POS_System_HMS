package controller.doctor;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Doctor;
import model.DoctorSession;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorFormController implements Initializable {

    @FXML
    private TableColumn colAvailability;

    @FXML
    private TableColumn colEmpId;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colSpeciality;

    @FXML
    private TableView tblDoctor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSpeciality;

    @FXML
    private JFXCheckBox checkNoLimit;

    @FXML
    private JFXComboBox cmbSessionName;

    @FXML
    private JFXComboBox cmbStatus;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtTime;

    @FXML
    private JFXComboBox cmbDuration;

    @FXML
    private Label lblSessionId;

    @FXML
    private JFXComboBox cmbDoctorId;

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(new DoctorController().nextId());
        loadDoctorId();
        lblSessionId.setText(new DoctorController().nextSessionId());
        loadSessionNames();
        loadSessionStatus();
        checkBoxState();

    }

    @FXML
    void checkBoxNoLimitOnAction(ActionEvent event) {
    }

    void checkBoxState(){
        checkNoLimit.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                if(newValue){
                    txtNumber.setText("Unlimited");
                }
                else{
                    txtNumber.setText("");
                }
            }
        });
    }

    @FXML
    void btnScheduleASessionOnAction(ActionEvent event) {

        DoctorSession doctorSession = new DoctorSession(
                lblSessionId.getText(),
                cmbSessionName.getValue().toString(),
                dpDate.getValue().toString(),
                txtTime.getText(),
                txtNumber.getText(),
                cmbStatus.getValue().toString(),
                cmbDoctorId.getValue().toString()
        );

        try {
            boolean isAdded = new DoctorController().addSession(doctorSession);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
            alert.show();
            if (isAdded){
                lblSessionId.setText(new DoctorController().nextSessionId());
                cmbSessionName.setValue(null);
                dpDate.setValue(null);
                txtTime.setText("");
                txtNumber.setText("");
                cmbStatus.setValue(null);
                cmbDoctorId.setValue(null);
                checkNoLimit.setSelected(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnAddDoctorOnAction(ActionEvent event) {
        Doctor doctor = new Doctor(
                txtId.getText(),
                txtSpeciality.getText(),
                txtAvailability.getText(),
                txtEmpId.getText()
        );
        try {
            boolean isAdded = new DoctorController().addDoctor(doctor);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
            alert.show();
            if (isAdded){
                txtId.setText(new DoctorController().nextId());
                txtSpeciality.setText("");
                txtAvailability.setText("");
                txtEmpId.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Doctor doctor = new DoctorController().searchDoctorById(txtSearch.getText());
        if (doctor !=null){
            boolean isDeleted = new DoctorController().deleteDoctor(txtSearch.getText());
            if(isDeleted){
                txtId.setText(new DoctorController().nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Doctor Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Doctor doctor = new DoctorController().searchDoctorById(txtSearch.getText());
        if (doctor!=null){
            loadTable(doctor);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Doctor Found !!").show();
        }
    }

    @FXML
    void btnShowDoctorsOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));

        List<Doctor> doctorArrayList = new DoctorController().getAll();
        ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList();
        doctorArrayList.forEach(doctor -> {
            doctorObservableList.add(doctor);
        });

        tblDoctor.setItems(doctorObservableList);
    }

    public void loadTable(Doctor doctor){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));

        ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList();
        doctorObservableList.add(doctor);

        tblDoctor.setItems(doctorObservableList);
    }

    private void loadDoctorId(){
        cmbDoctorId.setItems(new DoctorController().getDoctorId());
    }

    private void loadSessionNames(){
        cmbSessionName.setItems(new DoctorController().getSessionNames());
    }

    private void loadSessionStatus(){
        cmbStatus.setItems(new DoctorController().getSessionStatus());
    }
}

