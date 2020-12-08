package edu.bsu.cs222;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;

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