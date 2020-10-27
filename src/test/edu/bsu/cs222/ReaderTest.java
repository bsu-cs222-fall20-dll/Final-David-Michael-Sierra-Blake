package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ReaderTest {

    @Test
    public void testPrintsStartRoom() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");

        JsonPrimitive startRoomText = startRoom.getAsJsonPrimitive("Text");
        JsonObject startRoomActions = startRoom.getAsJsonObject("Actions");
        JsonArray startRoomActions1 = startRoomActions.getAsJsonArray("Action1");
        JsonArray startRoomActions2 = startRoomActions.getAsJsonArray("Action2");
        JsonArray startRoomActions3 = startRoomActions.getAsJsonArray("Action3");
        JsonArray startRoomPuzzle = startRoom.getAsJsonArray("Puzzle");
        JsonArray startRoomEnemies = startRoom.getAsJsonArray("Enemies");
        JsonPrimitive startRoomEnemyClear = startRoom.getAsJsonPrimitive("EnemyClear");

        System.out.println(startRoomText);
        System.out.println(startRoomActions1);
        System.out.println(startRoomActions2);
        System.out.println(startRoomActions3);
        System.out.println(startRoomPuzzle);
        System.out.println(startRoomEnemies);
        System.out.println(startRoomEnemyClear);
    }

    @Test
    public void roomReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject);
        Assertions.assertEquals(room.toString(), "{\"Text\":\"This is the start room to the test story.\",\"Actions\":{\"Action1\":[\"This is the first action.\",\"RoomTwo\"],\"Action2\":[\"This is the second action.\",\"RoomThree\"],\"Action3\":[\"This is the third action. It will end the game.\",\"End\"]},\"Puzzle\":[\"null\",\"ifPass\",\"ifFail\"],\"Enemies\":[],\"EnemyClear\":\"null\"}");
    }

    @Test
    public void textReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject);
        JsonPrimitive text = storyReader.textReceiver(room);
        Assertions.assertEquals(text.toString(), "\"This is the start room to the test story.\"");
    }


}
