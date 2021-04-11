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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClientController {
    @FXML Label welcomeLabel;

    private String username;
    private Stage primaryStage;

    private Socket socket = null; // used to store client socket
    private PrintWriter networkOut = null; // used to write to socket
    private BufferedReader networkIn = null; // used to read from socket

    public static String SERVER_ADDRESS = "localhost"; // server address
    public static int    SERVER_PORT = 16789; // server port

    //constants\\
    private final double buttonFitWidth = 250;
    private final double buttonFitHeight = 320;

    //-- Private Methods --\\
    private void selectAction(String actionName){ // this is the method that gets fired when any of the action buttons are pressed
        networkOut.println("GETCLIENTS");
        int id = -1;
        try {
            System.out.println("hello");
            id = networkIn.read();
            System.out.println(id);
            if (id >= 2) {
                System.err.println("Too Many Players");
            }
        } catch (IOException e) {
            System.out.println("IOException while opening a read/write connection");
        }
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
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT); // sets up socket
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOException while connecting to server: "+SERVER_ADDRESS);
        }
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true); // sends up writer
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // sets up reader
        } catch (IOException e) {
            System.err.println("IOException while opening a read/write connection");
        }
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
