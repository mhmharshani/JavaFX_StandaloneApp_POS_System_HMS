package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ResourceFormController {

    @FXML
    private JFXButton btnSaveOnAction;

    @FXML
    private JFXButton btnSearchOnAction;

    @FXML
    private JFXButton btnShowAllOnAction;

    @FXML
    private JFXCheckBox checkNewRecord;

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

    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
