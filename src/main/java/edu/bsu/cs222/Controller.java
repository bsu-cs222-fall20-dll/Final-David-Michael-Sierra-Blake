package java.edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    public TextArea currentSituationField;
    public TextArea currentActionsField;
    public TextArea statsField;
    public TextArea skillPointField;
    public Button actionButton1;
    public Button actionButton2;
    public Button actionButton3;


    public void doAction3(javafx.event.ActionEvent actionEvent) {
        System.out.println("Action!");
    }

    public void doAction2(javafx.event.ActionEvent actionEvent) {
    }

    public void doAction1(javafx.event.ActionEvent actionEvent) {
    }

    public void displayMenu(ActionEvent actionEvent) {
    }

    public void update(Room room) {
        actionButton3.setText(room.getAction(2));
    }
}