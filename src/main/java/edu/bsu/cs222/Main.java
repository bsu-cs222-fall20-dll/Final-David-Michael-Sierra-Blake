package edu.bsu.cs222;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainGUI.fxml"));
        primaryStage.setTitle("Game Title");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";
        TextArea currentSituationText;

        while (true) {
            Room room = roomBuilder.nextRoom(storyObject, roomName, storyName);
            String boxinfo = room.getRoomText();
            String action1 = room.getAction(0);
            String action2 = room.getAction(1);
            String action3 = room.getAction(2);
            String action1onclick = room.getActionResult(0);
            String action2onclick = room.getActionResult(1);
            String action3onclick = room.getActionResult(2);

            break;
        }
    }
}