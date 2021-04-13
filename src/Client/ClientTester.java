package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Client class that begins the program and creates the new connections to the server
 */
public class ClientTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/TesterMenu.fxml"));
        primaryStage.setTitle("Client opener");

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Launches the initial UI
     * @param args - Command line Arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
