package controller.appointment;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import controller.DashboardFormController;
import controller.doctor.DoctorController;
import controller.patient.PatientController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Doctor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentFormController implements Initializable {

    @FXML
    private JFXCheckBox checkIndoor;

    @FXML
    private JFXCheckBox checkOutdoor;

    @FXML
    private JFXComboBox cmbSpeciality;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblAppointId.setText(new AppointmentController().nextId());
        loadPatientId();
        loadDoctorId();
        loadSpecialityTypes();

    }

    @FXML
    void btnBook(ActionEvent event) {
//        Doctor doctor = new Doctor(
//                txtId.getText(),
//                txtSpeciality.getText(),
//                txtAvailability.getText(),
//                txtEmpId.getText()
//        );
//        boolean isAdded = new DoctorController().addDoctor(doctor);
//        Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
//        alert.show();
//        if (isAdded){
//            txtId.setText(new DoctorController().nextId());
//            txtSpeciality.setText("");
//            txtAvailability.setText("");
//            txtEmpId.setText("");
//        }

        //lblAppointId.setText(new AppointmentController().nextId());
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

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

    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnShowAppointmentOnAction(ActionEvent event) {

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

    private void loadSpecialityTypes(){
        cmbSpeciality.setItems(new DoctorController().getSpeciality());
    }


}
