import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene scene= new Scene(root,1200,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Air Quality Project");
        primaryStage.show();

    }
}
