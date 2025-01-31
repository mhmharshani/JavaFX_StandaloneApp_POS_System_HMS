package controller.doctor;

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
import model.Doctor;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(new DoctorController().nextId());
    }

    @FXML
    void btnAddDoctorOnAction(ActionEvent event) {
        Doctor doctor = new Doctor(
                txtId.getText(),
                txtSpeciality.getText(),
                txtAvailability.getText(),
                txtEmpId.getText()
        );
        boolean isAdded = new DoctorController().addDoctor(doctor);
        Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
        alert.show();
        if (isAdded){
            txtId.setText(new DoctorController().nextId());
            txtSpeciality.setText("");
            txtAvailability.setText("");
            txtEmpId.setText("");
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


}
