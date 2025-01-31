package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane loadFormContent;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDateAndTime();

//        URL resource = this.getClass().getResource("/view/main_form.fxml");
//        assert  resource != null;
//        Parent load = null;
//        try {
//            load = FXMLLoader.load(resource);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        loadFormContent.getChildren().clear();
//        loadFormContent.getChildren().add(load);


    }

    private void setDateAndTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String format = dateFormat.format(date);
        lblDate.setText(format);

        //----------------------------------------------------------------------

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setProfileName(){
        //Type here, Incomplete
    }

    @FXML
    void btnAppointmentsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/appointment_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/main_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);

    }

    @FXML
    void btnDoctorOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/doctor_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {

        btnLogout.getScene().getWindow().hide();

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();


    }

    @FXML
    void btnMessageOnAction(ActionEvent event) {

    }

    @FXML
    void btnNotificationOnAction(ActionEvent event) {

    }

    @FXML
    void btnPatientOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/patient_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnPaymentsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/payments_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnPharmacyOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/pharmacy_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnPrescriptionOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/prescription_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnProfileOnAction(ActionEvent event) {

    }

    @FXML
    void btnResourceOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/resource_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnStaffOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/staff_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }

    @FXML
    void btnStatisticsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/statistics_form.fxml");
        assert  resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContent.getChildren().clear();
        loadFormContent.getChildren().add(load);
    }



}
