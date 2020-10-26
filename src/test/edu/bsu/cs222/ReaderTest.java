package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ReaderTest {

    @Test
    public void testPrints() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");
        JsonObject roomTwo = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomTwo");
        JsonObject roomThree = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");
        JsonObject roomFour = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomFour");

        JsonPrimitive startRoomText = startRoom.getAsJsonPrimitive("Text");
        JsonObject startRoomActions = startRoom.getAsJsonObject("Actions");
        JsonArray startRoomActions1 = startRoomActions.getAsJsonArray("Action1");
        JsonArray startRoomActions2 = startRoomActions.getAsJsonArray("Action2");
        JsonArray startRoomActions3 = startRoomActions.getAsJsonArray("Action3");

        System.out.println(startRoomText);
        System.out.println(startRoomActions1);
        System.out.println(startRoomActions2);
        System.out.println(startRoomActions3);

    }
}
