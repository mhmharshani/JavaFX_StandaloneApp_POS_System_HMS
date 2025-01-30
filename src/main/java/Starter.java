import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

public class Starter extends Application {

    public static void main(String[] args) {
        //System.out.println("Hello");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
        stage.show();
    }
}
