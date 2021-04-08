package Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML TextField usernameInput;
    //this is just a simple controller class for loading the login screen for player
    //then the player/user can login and it'll switch controllers and login
    private void warnUser(String message){
        try{
            Stage secondaryStages = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/Warning.fxml"));
            Scene secondaryScene = new Scene(loader.load());
            secondaryStages.setTitle("Error");
            secondaryStages.setScene(secondaryScene);
            Label label = (Label) secondaryScene.lookup("#warningLabel");
            label.setText(message);
            secondaryStages.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void login(){
        String username = usernameInput.getText();
        if (username == null || username.length()==0){
            warnUser("Username field can't be empty");
            return;
        }

        try{
            Stage currentStage = (Stage) usernameInput.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/MainClientUi.fxml"));

            currentStage.setScene(new Scene(loader.load()));
            MainClientController controller = loader.getController();
            controller.initData(username);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
