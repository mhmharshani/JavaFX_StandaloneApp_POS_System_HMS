package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import dto.MedicalRecord;
import dto.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.PatientService;
import service.custom.ResourceService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ResourceFormController implements Initializable {

    @FXML
    private Label lblRecordId;

    @FXML
    private JFXCheckBox checkNewRecord;

    @FXML
    private JFXComboBox<?> cmbPatientId;

    @FXML
    private JFXComboBox cmbCapacity;

    @FXML
    private JFXComboBox cmbPId;

    @FXML
    private JFXComboBox cmbRoomId;

    @FXML
    private JFXComboBox cmbType;

    @FXML
    private TableColumn colAvailability;

    @FXML
    private TableColumn colCapacity;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colDiagnosis;

    @FXML
    private TableColumn colPId;

    @FXML
    private TableColumn colPatientId;

    @FXML
    private TableColumn colRId;

    @FXML
    private TableColumn colRoomId;

    @FXML
    private TableColumn colTreatment;

    @FXML
    private TableColumn colType;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView tblMedicalRecord;

    @FXML
    private TableView tblRoom;

    @FXML
    private TextField txtDiagnosis;

    @FXML
    private TextField txtPatientId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTreatment;

    ResourceService resourceService = ServiceFactory.getInstance().getServiceType(ServiceType.ROOM);
    @Inject
    PatientService patientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadResourceFormDetails();
        loadPatientId();
        lblRecordId.setText(resourceService.nextId());
    }

    private void loadResourceFormDetails() {
        List<Room> allRooms = resourceService.getAllRooms();
        cmbType.setItems(resourceService.loadRoomTypes());
        cmbCapacity.setItems(resourceService.loadRoomCapacity());

    }

    private void loadRoomId(){

        List<Room> allRooms = resourceService.getAllRooms();
        ObservableList<String> roomIdObservableList = FXCollections.observableArrayList();

        allRooms.forEach(room -> {
            if(room.getAvailability().equals("Yes")){
                if((cmbType.getValue().toString().equals(room.getType()))&&(cmbCapacity.getValue().toString().equals(room.getCapacity()))){
                    roomIdObservableList.add(room.getId());
                }
            }
        });
        if(roomIdObservableList.isEmpty()){
            new Alert(Alert.AlertType.INFORMATION, "No Rooms Available !!").show();
        }
        else{
            cmbRoomId.setItems(roomIdObservableList);
        }
    }

    @FXML
    void btnBookOnAction(ActionEvent event) {
        Room room = new Room(
                cmbRoomId.getValue().toString(),
                cmbType.getValue().toString(),
                cmbCapacity.getValue().toString(),
                "No",
                cmbPatientId.getValue().toString()
        );
        try {
            boolean isUpdated = resourceService.bookRoom(cmbRoomId.getValue().toString(),room);
            Alert alert = isUpdated ? (new Alert(Alert.AlertType.CONFIRMATION, "Booked Success !!")) : (new Alert(Alert.AlertType.ERROR, "Booked Failed !!"));
            alert.show();
            if (isUpdated){
                cmbPatientId.setValue(null);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        MedicalRecord medicalRecord = new MedicalRecord(
                lblRecordId.getText(),
                dpDate.getValue().toString(),
                txtDiagnosis.getText(),
                txtTreatment.getText(),
                cmbPId.getValue().toString()
        );

        try {
            boolean isAdded = resourceService.addMedicalRecord(medicalRecord);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Saved Success !!")) : (new Alert(Alert.AlertType.ERROR, "Saved Failed !!"));
            alert.show();
            if (isAdded){
                lblRecordId.setText(resourceService.nextId());
                dpDate.setValue(null);
                txtDiagnosis.setText("");
                txtTreatment.setText("");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSearchRoomOnAction(ActionEvent event) {
        loadRoomId();
    }

    @FXML
    void btnSearchRecordOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteRecordOnAction(ActionEvent event) {

    }

    @FXML
    void btnShowAllOnAction(ActionEvent event) {
        loadRoomTable();
    }

    @FXML
    void btnShowAllRecordOnAction(ActionEvent event) {
       loadRecordTable();
    }

    private void loadPatientId(){

        cmbPatientId.setItems(patientService.getPatientId());
        cmbPId.setItems(patientService.getPatientId());
    }

    public void loadRoomTable(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        List<Room> roomArrayList = resourceService.getAllRooms();
        ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
        roomArrayList.forEach(room -> {
            roomObservableList.add(room);
        });

        tblRoom.setItems(roomObservableList);
    }

    public void loadRecordTable(){
        colRId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colTreatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        colPId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        List<MedicalRecord> medicalRecordsArrayList = resourceService.getAllRecords();
        ObservableList<MedicalRecord> medicalRecordObservableList = FXCollections.observableArrayList();
        medicalRecordsArrayList.forEach(medicalRecord -> {
            medicalRecordObservableList.add(medicalRecord);
        });
        tblMedicalRecord.setItems(medicalRecordObservableList);
    }

}
