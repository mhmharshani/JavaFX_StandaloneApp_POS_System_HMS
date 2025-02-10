package controller.payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import controller.appointment.AppointmentController;
import controller.dashboard.DashboardController;
import controller.dashboard.DashboardFormController;
import controller.doctor.DoctorController;
import controller.patient.PatientController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Appointment;
import model.Billing;
import model.Dashboard;
import model.DoctorSession;
import util.Charge;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private JFXButton btnPayByCard;

    @FXML
    private JFXButton btnPayByCash;

    @FXML
    private JFXButton btnPrintBill;

    @FXML
    private JFXCheckBox checkPaid;

    @FXML
    private DatePicker dpDate;

    @FXML
    private JFXComboBox cmbDesc;

    @FXML
    private TextField txtNumber;

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
    private Label lblBillid;

    @FXML
    private Label lblPatientId;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TableView tblBilling;

    @FXML
    private TextField txtDocName;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXComboBox<?> cmbTime;

    @FXML
    private JFXComboBox<?> cmbDocId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblBillid.setText(new PaymentController().nextId());
        checkBoxState();

        cmbDocId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null){
                if(dpDate.getValue()!=null){
                    setSessionTime(cmbDocId.getValue().toString());
                }
            }
        });

        dpDate.setOnAction(actionEvent -> {
            if(cmbDocId.getValue()!=null){
                setSessionTime(cmbDocId.getValue().toString());

            }
        });

        cmbDesc.setItems(new PaymentController().getPaymentDesc());

    }

    @FXML
    void btnSearchDocOnAction(ActionEvent event) {
        ObservableList docIdObservableList = FXCollections.observableArrayList();
        List<String> docIdArrayList = new DoctorController().searchDoctorByName(txtDocName.getText());
        docIdArrayList.forEach(docId ->{
            docIdObservableList.add(docId);
        });
        cmbDocId.setItems(docIdObservableList);

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Billing billing = new PaymentController().searchPaymentById(txtSearch.getText());
        if (billing!=null){
            boolean isDeleted = new PaymentController().deletePayment(txtSearch.getText());
            if(isDeleted){
                lblBillid.setText(new PaymentController().nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Bill Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayByCardOnAction(ActionEvent event) {
        addBilling("Card Payment");
    }

    @FXML
    void btnPayByCashOnAction(ActionEvent event) {
        addBilling("Cash Payment");
    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @FXML
    void btnShowPaymentsOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        DoctorSession doctorSession = new DoctorController().searchDoctorSession(cmbDocId.getValue().toString(),dpDate.getValue().toString(),cmbTime.getValue().toString());

        if(doctorSession!=null){
            doctorSession.getId();
            Appointment appointment = new AppointmentController().searchAppointmentBySIdAndDocId(doctorSession.getId(),Integer.parseInt(txtNumber.getText()));
            if(appointment!=null) {
                lblPatientId.setText(appointment.getPatientId());
                lblPatientName.setText(new PatientController().searchPatientById(appointment.getPatientId()).getName());
                setCharges();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "NO appointment").show();
            }
        }
        else{
            new Alert(Alert.AlertType.ERROR,"Check input data").show();
            txtDocName.setText("");
            cmbDocId.setValue(null);
            txtNumber.setText("");
            dpDate.setValue(null);
            cmbTime.setValue(null);
        }

    }

    @FXML
    void btnSearchBillOnAction(ActionEvent event) {
        Billing billing = new PaymentController().searchPaymentById(txtSearch.getText());
        if (billing!=null){
            loadTable(billing);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Bill Found !!").show();
        }
    }

    void checkBoxState(){
        checkPaid.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
                System.out.println(newValue);
                if(newValue!=null) {
                    if (newValue) {
                        btnPayByCard.setDisable(true);
                        btnPayByCash.setDisable(true);
                    } else {
                        btnPayByCard.setDisable(false);
                        btnPayByCash.setDisable(false);
                    }
                }
            }
        });

    }

    private void setSessionTime(String id){
        List<DoctorSession> doctorSessionsArrayList = new DoctorController().searchDoctorSessionByDocId(id);
        ObservableList timeObservableArray = FXCollections.observableArrayList();
        if((!doctorSessionsArrayList.isEmpty()) && (dpDate.getValue()!=null)){
            doctorSessionsArrayList.forEach(doctorSession -> {
                if(dpDate.getValue().toString().equals(doctorSession.getDate())){
                    timeObservableArray.add(doctorSession.getTime());
                }
            });
        }

        if(timeObservableArray.isEmpty()){
            timeObservableArray.add("No Session Time");
            cmbTime.setItems(timeObservableArray);
        }
        else {
            cmbTime.setItems(timeObservableArray);
        }

    }

    private void setCharges(){

        lblDocFee.setText(Charge.DOCTORFEE.getFee().toString());
        lblHosFee.setText(Charge.HOSPITALFEE.getFee().toString());


        Double total,netTotal;

        if(checkPaid.isSelected()){
            total=Charge.DOCTORFEE.getFee()+Charge.HOSPITALFEE.getFee()+Charge.BOOKINGFEE.getFee();
            lblBookingFee.setText(Charge.BOOKINGFEE.getFee().toString());
        }
        else{
            total=Charge.DOCTORFEE.getFee()+Charge.HOSPITALFEE.getFee();
            lblBookingFee.setText("0.0");
        }
        netTotal=total-(total*Charge.DISCOUNT.getFee());
        lblTotalAmount.setText(netTotal.toString());
        lblDiscounts.setText(((Double)(total*Charge.DISCOUNT.getFee())).toString());
    }

    private void addBilling(String status){
        //------------------------Date---------------------------------------------
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        //------------------------Time---------------------------------------------
        LocalTime now = LocalTime.now();
        String time =now.getHour()+":"+now.getMinute()+":"+now.getSecond();

        Billing billing = new Billing(
                lblBillid.getText(),
                format,
                time,
                cmbDesc.getValue().toString(),
                Double.parseDouble(lblTotalAmount.getText()),
                status,
                lblPatientId.getText().toString()
        );

        try {
            boolean isDone = new PaymentController().addPayment(billing);
            Alert alert = isDone ? (new Alert(Alert.AlertType.CONFIRMATION, "Payment Success !!")) : (new Alert(Alert.AlertType.ERROR, "Payment Failed !!"));
            alert.show();
            if (isDone){
                lblBillid.setText(new PaymentController().nextId());
                lblPatientId.setText("");
                lblHosFee.setText("");
                lblHosFee.setText("");
                lblPatientName.setText("");
                lblDiscounts.setText("");
                lblTotalAmount.setText("");
                lblBookingFee.setText("");
                cmbDesc.setValue(null);

                txtDocName.setText("");
                cmbDocId.setValue(null);
                txtNumber.setText("");
                dpDate.setValue(null);
                cmbTime.setValue(null);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable(){

        colBillId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGenDate.setCellValueFactory(new PropertyValueFactory<>("genDate"));
        colGenTime.setCellValueFactory(new PropertyValueFactory<>("genTime"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        List<Billing> billingArrayList = new PaymentController().getAll();
        ObservableList<Billing> billingObservableList = FXCollections.observableArrayList();
        billingArrayList.forEach(appointment -> {
            billingObservableList.add(appointment);
        });
        tblBilling.setItems(billingObservableList);
    }

    public void loadTable(Billing billing){

        colBillId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colGenDate.setCellValueFactory(new PropertyValueFactory<>("genDate"));
        colGenTime.setCellValueFactory(new PropertyValueFactory<>("genTime"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        ObservableList<Billing> billingObservableList = FXCollections.observableArrayList();
        billingObservableList.add(billing);

        tblBilling.setItems(billingObservableList);
    }

}
