package java.edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;


public class RoomBuilder {
    public Room nextRoom(JsonObject story, String roomName, String storyName) throws FileNotFoundException {
        return new Room(story, roomName, storyName);
    }

}
