package edu.bsu.cs222;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/MainGUI.fxml"));
        primaryStage.setTitle("Game Title");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        while(true) {
            Room room = roomBuilder.nextRoom(storyObject, roomName, storyName);
            String boxinfo = room.getRoomText();
            String actionbox1title = room.getAction(0);
            String actionbox2title = room.getAction(1);
            String actionbox3title = room.getAction(2);
            String actionbox1onclick = room.getActionResult(0);
            String actionbox2onclick = room.getActionResult(1);
            String actionbox3onclick = room.getActionResult(2);

            break;
        }
    }

    public static void main(String[] args) { launch(args); }
}
