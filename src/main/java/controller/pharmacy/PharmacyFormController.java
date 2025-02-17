package controller.pharmacy;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PharmacyFormController {

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private TableColumn colAmount;

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colQty;

    @FXML
    private Label lblBillId;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TableView tblOderPharmacy;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtStock;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
