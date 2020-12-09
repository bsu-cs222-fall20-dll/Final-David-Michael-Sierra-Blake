package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static java.lang.System.exit;

public class MainMenu {

    StoryReader storyReader = new StoryReader();
    RoomBuilder roomBuilder = new RoomBuilder();

    Button storyButton1 = new Button();
    Button storyButton2 = new Button();
    Button storyButton3 = new Button();
    Button exitButton = new Button();
    TextArea menuText = new TextArea();

    InputStream waterStoryIS = new FileInputStream("src/main/resources/water-story.json");
    InputStream dreamStoryIS = new FileInputStream("src/main/resources/dream.json");
    InputStream caveStoryIS = new FileInputStream("src/main/resources/cave-story.json");

    JsonObject waterStoryObject = storyReader.parse(waterStoryIS);
    JsonObject dreamStoryObject = storyReader.parse(dreamStoryIS);
    JsonObject caveStoryObject = storyReader.parse(caveStoryIS);

    String waterStoryName = "WaterStory";
    String dreamStoryName = "DreamStory";
    String caveStoryName = "CaveStory";


    public MainMenu(Stage primaryStage) throws FileNotFoundException{

        createMenu(primaryStage);
        beautifyText();
        beautifyStoryButton1();
        beautifyStoryButton2();
        beautifyStoryButton3();
        beautifyExitButton();

        storyButton1.setOnAction(actionEvent -> {
            try {
                GUI gui = new GUI(primaryStage, waterStoryName, "StartRoom", waterStoryObject);
                gui.setRoom(roomBuilder.nextRoom(waterStoryObject, "StartRoom", waterStoryName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        storyButton2.setOnAction(actionEvent -> {
            try {
                GUI gui = new GUI(primaryStage, dreamStoryName, "StartRoom", dreamStoryObject);
                gui.setRoom(roomBuilder.nextRoom(dreamStoryObject, "StartRoom", dreamStoryName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        storyButton3.setOnAction(actionEvent -> {
            try {
                GUI gui = new GUI(primaryStage, caveStoryName, "StartRoom", caveStoryObject);
                gui.setRoom(roomBuilder.nextRoom(caveStoryObject, "StartRoom", caveStoryName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> exit(0));
    }

    public void createMenu(Stage primaryStage) {
        VBox parent = new VBox();
        primaryStage.setHeight(500);
        primaryStage.setWidth((750));
        primaryStage.setTitle("Dungeon Game");

        parent.getChildren().add(menuText);
        parent.getChildren().add(storyButton1);
        parent.getChildren().add(storyButton2);
        parent.getChildren().add(storyButton3);
        parent.getChildren().add(exitButton);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public void beautifyText() {
        menuText.setTranslateX(187);
        menuText.setTranslateY(125);
        menuText.setMaxHeight(200);
        menuText.setMaxWidth(350);
        menuText.setWrapText(true);
        menuText.setText("Choose one of the three stories below, or click exit to quit the game.");
    }

    public void beautifyStoryButton1() {
        storyButton1.setTranslateX(30);
        storyButton1.setTranslateY(200);
        storyButton1.setMaxHeight(30);
        storyButton1.setText("Water Story");
    }

    public void beautifyStoryButton2() {
        storyButton2.setTranslateX(335);
        storyButton2.setTranslateY(175);
        storyButton2.setMaxHeight(30);
        storyButton2.setText("Dream Story");
    }

    public void beautifyStoryButton3() {
        storyButton3.setTranslateX(640);
        storyButton3.setTranslateY(150);
        storyButton3.setMaxHeight(30);
        storyButton3.setText("Cave Story");
    }

    public void beautifyExitButton() {
        exitButton.setTranslateX(350);
        exitButton.setTranslateY(155);
        exitButton.setMaxHeight(30);
        exitButton.setMinWidth(50);
        exitButton.setText("Exit");
    }
}
