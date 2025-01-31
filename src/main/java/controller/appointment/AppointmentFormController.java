package controller.appointment;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AppointmentFormController {

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

    @FXML
    void btnBook(ActionEvent event) {

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

}
