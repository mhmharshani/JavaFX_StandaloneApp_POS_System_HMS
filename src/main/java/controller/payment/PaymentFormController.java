package controller.payment;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PaymentFormController {

    @FXML
    private DatePicker dpDate;

    @FXML
    private JFXComboBox cmbDesc;

    @FXML
    private JFXComboBox cmbNo;

    @FXML
    private JFXComboBox cmbSessionName;

    @FXML
    private TableColumn colBillId;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colGenDate;

    @FXML
    private TableColumn colGenTime;

    @FXML
    private TableColumn colPatientId;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTotal;

    @FXML
    private Label lblBookingFee;

    @FXML
    private Label lblDiscounts;

    @FXML
    private Label lblDocFee;

    @FXML
    private Label lblHosFee;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TableView tblBilling;

    @FXML
    private TextField txtDocName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayByCardOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayByCashOnAction(ActionEvent event) {

    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnShowPaymentsOnAction(ActionEvent event) {

    }

}
