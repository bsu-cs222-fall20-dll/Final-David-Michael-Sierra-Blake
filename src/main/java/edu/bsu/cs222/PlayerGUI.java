package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayerGUI {

    Button upgradeHealthButton = new Button();
    Button upgradeAttackButton = new Button();
    Button returnButton = new Button();
    TextArea roomText = new TextArea();
    Text statsText = new Text();

    Player player;


    public PlayerGUI(Stage primaryStage, String storyName, String roomName, JsonObject storyObject, Player player) {
        this.player = player;

        beautifyHealthButton();
        beautifyAttackButton();
        beautifyReturnButton();
        beautifyRoomText();
        beautifyStatsText();
        updateStatsText();
        createGUI(primaryStage);

        returnButton.setOnAction(actionEvent -> {
            try {
                new GUI(primaryStage, storyName, roomName, storyObject, player);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        upgradeAttackButton.setOnAction(actionEvent -> {
            player.spendPointOnDamage();
            updateStatsText();
        });
        upgradeHealthButton.setOnAction(actionEvent -> {
            player.spendPointOnHealth();
            updateStatsText();
        });
    }

    public void createGUI(Stage primaryStage) {
        VBox parent = new VBox();
        primaryStage.setHeight(500);
        primaryStage.setWidth((750));
        primaryStage.setTitle("Dungeon Game");

        BackgroundImage myBI= null;
        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("src/main/resources/water-background.jpg"),1280,720,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parent.setBackground(new Background(myBI));

        parent.getChildren().add(roomText);
        parent.getChildren().add(upgradeHealthButton);
        parent.getChildren().add(upgradeAttackButton);
        parent.getChildren().add(returnButton);
        parent.getChildren().add(statsText);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    public void beautifyRoomText() {
        roomText.setTranslateX(187);
        roomText.setTranslateY(125);
        roomText.setMaxHeight(200);
        roomText.setMaxWidth(350);
        roomText.setWrapText(true);
    }

    public void beautifyStatsText() {
        statsText.setTranslateX(205);
        statsText.setTranslateY(-155);
        statsText.setScaleX(2);
        statsText.setScaleY(2);
    }

    public void beautifyHealthButton() {
        upgradeHealthButton.setTranslateX(200);
        upgradeHealthButton.setTranslateY(175);
        upgradeHealthButton.setMaxHeight(30);
        upgradeHealthButton.setText("Upgrade Health");
    }

    public void beautifyAttackButton() {
        upgradeAttackButton.setTranslateX(425);
        upgradeAttackButton.setTranslateY(150);
        upgradeAttackButton.setMaxHeight(30);
        upgradeAttackButton.setText("Upgrade Attack");
    }

    public void beautifyReturnButton() {
        returnButton.setTranslateX(550);
        returnButton.setTranslateY(125);
        returnButton.setMaxHeight(30);
        returnButton.setMinWidth(50);
        returnButton.setText("Return to room");
    }

    public void updateStatsText() {
        statsText.setText("Points: " + player.getPoints());
        roomText.setText("Here, you can update your health and attack stats by spending points gotten from defeating monsters.\n\nCurrent Health: " + player.getHealth() + "\nCurrent Attack: " + player.getAttack());
    }

}
