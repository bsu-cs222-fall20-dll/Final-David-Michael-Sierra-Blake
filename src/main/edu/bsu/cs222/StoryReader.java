package edu.bsu.cs222;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StoryReader {
    public JsonObject parse(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public JsonObject roomReceiver(JsonObject rootObject) {
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");
        System.out.println(room);
        return room;
    }

    public JsonPrimitive textReceiver(JsonObject room) {
        return room.getAsJsonPrimitive("Text");
    }

    public JsonArray enemyReceiver(JsonObject rootObject) {
        JsonArray enemies = rootObject.getAsJsonArray("Enemies");
        return enemies;
    }


}
