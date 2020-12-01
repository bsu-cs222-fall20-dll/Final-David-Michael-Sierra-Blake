package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RoomTest {
    @Test
    public void testStartRoomText() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getRoomText(), "This is the start room to the test story.");
    }

    @Test
    public void testStartRoomActionResult1() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getActionResult(0), "RoomTwo");
    }

    @Test
    public void testStartRoomActions() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getActions().toString(), "[This is the first action., This is the second action., This is the third action. It will end the game.]");
    }

    @Test
    public void testStartRoomAction1() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getAction(1), "This is the second action.");
    }

    @Test
    public void testStartRoomActionResults() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getActionResults().toString(), "[RoomTwo, RoomThree, End]");

    }

    @Test
    public void testStartRoomPuzzle() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getPuzzle().toString(), "Puzzle Type: null\n" +
                "On Pass: ifPass\n" +
                "On Fail: ifFail\n");

    }

    @Test
    public void testStartRoomEnemy() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        Assertions.assertEquals(room.getEnemies().toString(), "[]");

    }

    @Test
    public void testStartRoomEnemy1Null() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");

        JsonObject storyObject = storyReader.parse(is);
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room room = new Room(storyObject, roomName, storyName);
        ArrayList<Enemy> enemies = room.getEnemies();
        Enemy finalEnemy = null;
        for (int i = 0; i < enemies.size(); i++) {
            finalEnemy = room.getEnemy(i);
        }
        Assertions.assertNull(finalEnemy);

    }


}
