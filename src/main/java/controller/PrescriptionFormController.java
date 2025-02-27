package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.Prescription;
import service.ServiceFactory;
import service.custom.DoctorService;
import service.custom.PatientService;
import service.custom.PrescriptionService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PrescriptionFormController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDoctorId();
        loadPatientId();
        lblPreId.setText(prescriptionService.nextId());
    }

    @FXML
    private TableColumn colDiagnosis;

    @FXML
    private TableColumn colDocId;

    @FXML
    private TableColumn colDose;

    @FXML
    private TableColumn colDuration;

    @FXML
    private TableColumn colMed;

    @FXML
    private TableColumn colPatientId;

    @FXML
    private TableColumn colPresId;

    @FXML
    private Label lblPreId;

    @FXML
    private TableView tblPrescription;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private TextField txtDosage;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtMedicine;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXComboBox<?> cmbDocId;

    @FXML
    private JFXComboBox<?> cmbPatientId;

    PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);
    DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
    PrescriptionService prescriptionService = ServiceFactory.getInstance().getServiceType(ServiceType.PRESCRIPTION);

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Prescription prescription = prescriptionService.searchPrescriptionById(txtSearch.getText());
        if (prescription!=null){
            boolean isDeleted = prescriptionService.deletePrescription(txtSearch.getText());
            if(isDeleted){
                lblPreId.setText(prescriptionService.nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Prescription Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnIssueOnAction(ActionEvent event) {
        Prescription prescription = new Prescription(
                lblPreId.getText(),
                txtDiagnosis.getText(),
                txtMedicine.getText(),
                txtDosage.getText(),
                txtDuration.getText(),
                cmbDocId.getValue().toString(),
                cmbPatientId.getValue().toString()
        );

        try {
            boolean isAdded = prescriptionService.addPrescription(prescription);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Issued Success !!")) : (new Alert(Alert.AlertType.ERROR, "Issued Failed !!"));
            alert.show();
            if (isAdded){
                lblPreId.setText(prescriptionService.nextId());
                txtDiagnosis.setText("");
                txtMedicine.setText("");
                txtDosage.setText("");
                txtDuration.setText("");
                cmbDocId.setValue(null);
                cmbPatientId.setValue(null);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Prescription prescription = prescriptionService.searchPrescriptionById(txtSearch.getText());
        if (prescription!=null){
            loadTable(prescription);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Prescription Found !!").show();
        }
    }

    @FXML
    void btnShowPrescriptionOnAction(ActionEvent event) {
        loadTable();
    }

    private void loadPatientId(){
        cmbPatientId.setItems(patientService.getPatientId());
    }

    private void loadDoctorId(){
        cmbDocId.setItems(doctorService.getDoctorId());
    }

    public void loadTable(){
        colPresId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        colDose.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        List<Prescription> prescriptionArrayList = prescriptionService.getAll();
        ObservableList<Prescription> prescriptionObservableList = FXCollections.observableArrayList();
        prescriptionArrayList.forEach(prescription -> {
            prescriptionObservableList.add(prescription);
        });

        tblPrescription.setItems(prescriptionObservableList);
    }

    public void loadTable(Prescription prescription){
        colPresId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        colDose.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDocId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        ObservableList<Prescription> prescriptionObservableList = FXCollections.observableArrayList();
        prescriptionObservableList.add(prescription);

        tblPrescription.setItems(prescriptionObservableList);
    }

}
