package lab2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    Button sendButton;
    @FXML
    Button getButton;
    @FXML
    TextField getField;
    @FXML
    TextField sendField;

    Database db = Database.getInstance();
    @FXML
    public void sendMove(){
        String move = sendField.getText();
        if (!move.trim().isEmpty()){
            db.sendMove(move);
        }
        sendField.setText("");
    }

    @FXML
    public void getMove(){
        String move = db.getMove();
        getField.setText(move);
    }
}
