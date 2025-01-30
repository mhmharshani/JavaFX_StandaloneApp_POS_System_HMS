package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormController {

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtCPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws SQLException, IOException {
        String key = "N5523Adg@2";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "INSERT INTO users (employee_id,username,email,password) VALUES (?,?,?,?)";

        if (txtPassword.getText().equals(txtCPassword.getText())){

            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email=" + "'" + txtEmail.getText() + "'");

            if (!resultSet.next()) {
                User user = new User(txtUserName.getText(), txtEmail.getText(), txtPassword.getText());
                PreparedStatement pstm = connection.prepareStatement(SQL);
                pstm.setString(1,txtId.getText());
                pstm.setString(2,user.getName());
                pstm.setString(3, user.getEmail());
                pstm.setString(4,basicTextEncryptor.encrypt(user.getPassword()));
                pstm.executeUpdate();

                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
                stage.show();

                //new Alert(Alert.AlertType.CONFIRMATION,"Registration Success!").show();

            } else{
                new Alert(Alert.AlertType.ERROR,"User Found!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Check Your Password!!").show();
        }

    }

    @FXML
    void linkLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();
    }

}
