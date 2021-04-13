package Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Used to display the login screen for Rock Paper Scissors - Player enters name UI
 */
public class ClientCreatorController {

    public void createNewClient(){
        try{
            Stage secondaryStages = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/LoginUi.fxml"));

            secondaryStages.setTitle("Rock Paper Scissors");
            secondaryStages.setScene(new Scene(loader.load()));
            secondaryStages.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
