package edu.bsu.cs222;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String storyName = "WaterStory";
        String roomName = "StartRoom";

        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();
        InputStream is = new FileInputStream("src/main/resources/water-story.json");

        JsonObject storyObject = storyReader.parse(is);

        GUI gui = new GUI(primaryStage, storyName, roomName, storyObject);


        gui.setRoom(roomBuilder.nextRoom(storyObject, roomName, storyName));


    }
}