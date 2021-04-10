import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class RoundData {
    private final SimpleStringProperty player1Username = new SimpleStringProperty();
    private final SimpleStringProperty player2Username = new SimpleStringProperty();
    private final SimpleStringProperty winnerUsername = new SimpleStringProperty();
    private final SimpleStringProperty timeOfRound = new SimpleStringProperty();

    public RoundData(){
        this("N/A", "N/A", "N/A", "");
    }

    public RoundData(String player1Username, String player2Username, String winnerUsername){
        this(player1Username, player2Username, winnerUsername, "");
    }

    public RoundData(String player1Username, String player2Username, String winnerUsername, String timeOfRound) {
        setPlayer1Username(player1Username);
        setPlayer2Username(player2Username);
        setWinnerUsername(winnerUsername);
        if (timeOfRound == null || timeOfRound.length() == 0){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            setTimeOfRound(dtf.format(LocalDateTime.now()));
        }
    }

    //getters
    public String getPlayer1Username() {return player1Username.get();}
    public String getPlayer2Username() {return player2Username.get();}
    public String getWinnerUsername() {return winnerUsername.get();}
    public String getTimeOfRound() {return timeOfRound.get();}

    //setters
    public void setPlayer1Username(String value) {player1Username.set(value);};
    public void setPlayer2Username(String value) {player2Username.set(value);}
    public void setWinnerUsername(String value) {winnerUsername.set(value);}
    public void setTimeOfRound(String value) {timeOfRound.set(value);}

    //vale returners
    public StringProperty player1UsernameProperty() {return player1Username;}
    public StringProperty player2UsernameProperty() {return player2Username;}
    public StringProperty winnerUsernameProperty() {return winnerUsername;}
    public StringProperty timeOfRoundProperty() {return timeOfRound;}
}
