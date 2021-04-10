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

    //constants\\
    private final double buttonFitWidth = 250;
    private final double buttonFitHeight = 320;

    //-- Private Methods --\\
    private void selectAction(String actionName){ // this is the method that gets fired when any of the action buttons are pressed
        if (actionName.equalsIgnoreCase("rock")){
            System.out.println("Player pressed rock");
        }else if(actionName.equalsIgnoreCase("paper")){
            System.out.println("Player pressed paper");
        }else if(actionName.equalsIgnoreCase("scissors")){
            System.out.println("Player pressed scissors");
        }else{
            System.out.println("No such action "+actionName);
        }
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

            //generating image views
            ImageView rockView = new ImageView(new Image("/Rock.png"));
            ImageView paperView = new ImageView(new Image("/Paper.png"));
            ImageView scissorsView = new ImageView(new Image("/Scissors.png"));
            rockView.setFitWidth(buttonFitWidth);
            rockView.setFitHeight(buttonFitHeight);
            paperView.setFitWidth(buttonFitWidth);
            paperView.setFitHeight(buttonFitHeight);
            scissorsView.setFitWidth(buttonFitWidth);
            scissorsView.setFitHeight(buttonFitHeight);

            //adding image view to buttons
            rockButton.setGraphic(rockView);
            paperButton.setGraphic(paperView);
            scissorsButton.setGraphic(scissorsView);

            //connecting to action methods
            rockButton.setOnAction((actionEvent -> {selectAction("rock");}));
            paperButton.setOnAction((actionEvent -> {selectAction("paper");}));
            scissorsButton.setOnAction((actionEvent -> {selectAction("scissors");}));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
