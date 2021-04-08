package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jdk.jfr.Event;

import java.beans.EventHandler;

public class MainClientController {
    @FXML Label welcomeLabel;

    private String username;
    private Stage primaryStage;

    //-- Private Methods --\\
    private void selectAction(String actionName){ // this is the method that gets fired when any of the action buttons are pressed

    }

    //-- Controller Methods --\\
    public void initData(String username){
        this.username = username;
        welcomeLabel.setText("Welcome to Rock Paper Scissors "+username);
        primaryStage = (Stage) welcomeLabel.getScene().getWindow();
    }

    public void playGame(){
        //method is fired when button is pressed
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/RockPaperScissors.fxml"));
            primaryStage.setScene(new Scene(loader.load()));

            //getting the buttons to apply images through code
            Button rockButton = (Button) primaryStage.getScene().lookup("#rock");
            Button paperButton = (Button) primaryStage.getScene().lookup("#paper");
            Button scissorsButton = (Button) primaryStage.getScene().lookup("#scissors");

            rockButton.setGraphic(new ImageView(new Image("/icon.jpg")));
            paperButton.setGraphic(new ImageView(new Image("/icon.jpg")));
            scissorsButton.setGraphic(new ImageView(new Image("/icon.jpg")));

            //connecting to action methods
            rockButton.setOnAction((actionEvent -> {selectAction("rock");}));
            paperButton.setOnAction((actionEvent -> {selectAction("paper");}));
            scissorsButton.setOnAction((actionEvent -> {selectAction("scissors");}));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
