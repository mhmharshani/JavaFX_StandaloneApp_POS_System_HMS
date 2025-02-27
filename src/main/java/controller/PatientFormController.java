package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.Patient;
import service.ServiceFactory;
import service.custom.PatientService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colAge;

    @FXML
    private TableColumn colGender;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colNIC;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPhone;

    @FXML
    private TableView tblPatient;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSearch;

    PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtId.setText(patientService.nextId());
    }

    @FXML
    void btnAddPatientOnAction(ActionEvent event) {

        Patient patient = new Patient(
                txtId.getText(),
                txtName.getText(),
                txtNIC.getText(),
                txtAddress.getText(),
                txtGender.getText(),
                txtPhone.getText(),
                Integer.parseInt(txtAge.getText())
        );
        boolean isAdded = false;
        try {
            isAdded = patientService.addPatient(patient);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
            alert.show();
            if (isAdded){
                txtId.setText(patientService.nextId());
                txtName.setText("");
                txtNIC.setText("");
                txtAddress.setText("");
                txtGender.setText("");
                txtPhone.setText("");
                txtAge.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Patient patient = patientService.searchPatientByNIC(txtSearch.getText());
        if (patient !=null){
            boolean isDeleted = patientService.deletePatient(txtSearch.getText());
            if(isDeleted){
                txtId.setText(patientService.nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Patient Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Patient patient = patientService.searchPatientByNIC(txtSearch.getText());
        if (patient !=null){
            loadTable(patient);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Patient Found !!").show();
        }
    }

    @FXML
    void btnShowPatientsOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        List<Patient> patientArrayList = patientService.getAll();
        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();
        patientArrayList.forEach(patient -> {
            patientObservableList.add(patient);
        });

        tblPatient.setItems(patientObservableList);
    }

    public void loadTable(Patient patient){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();
        patientObservableList.add(patient);

        tblPatient.setItems(patientObservableList);
    }



}
