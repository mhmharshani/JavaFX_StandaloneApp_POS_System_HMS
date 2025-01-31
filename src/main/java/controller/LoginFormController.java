package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Hyperlink linkRegister;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    Stage stage = new Stage();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {
        String key = "N5523Adg@2";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM users WHERE email="+"'"+txtEmail.getText()+"'";
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(SQL);
        if (resultSet.next()) {
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    basicTextEncryptor.decrypt(resultSet.getString(4))
            );
            System.out.println(user);

            if(user.getPassword().equals(txtPassword.getText())){
                btnLogin.getScene().getWindow().hide();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.show();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Check Your Password!!").show();
            }

        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not Found!!").show();
        }

    }

    @FXML
    void linkRegisterOnAction(ActionEvent event) throws IOException {
        linkRegister.getScene().getWindow().hide();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_form.fxml"))));
        stage.show();
    }

}

