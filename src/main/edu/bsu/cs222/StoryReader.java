package edu.bsu.cs222;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

public class StoryReader {
    public JsonObject parse(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public JsonObject roomReceiver(JsonObject rootObject, String room) {
        JsonObject roomObject = rootObject.getAsJsonObject("TestStory").getAsJsonObject(room);
        return roomObject;
    }

    public JsonPrimitive textReceiver(JsonObject room) {
        return room.getAsJsonPrimitive("Text");
    }

    public JsonArray enemyReceiver(JsonObject rootObject) {
        JsonArray enemies = rootObject.getAsJsonArray("Enemies");
        return enemies;
    }
    public JsonObject actionsReceiver(JsonObject rootObject){
        JsonObject actions = rootObject.getAsJsonObject("Actions");
        return actions;
    }

    public ArrayList<JsonArray> getActionList(JsonObject actions) {
        ArrayList<JsonArray> actionList = new ArrayList<>();
        actionList.add(actions.getAsJsonArray("Action1"));
        actionList.add(actions.getAsJsonArray("Action2"));
        actionList.add(actions.getAsJsonArray("Action3"));

        actionList.removeIf(Objects::isNull);
        return actionList;
    }

    public String getActionListAction(JsonArray action) {
        return (action.get(0).toString());
    }

    public String getActionListResult(JsonArray action) {
        return action.get(1).toString().replace("\"", "");
    }

    public JsonArray puzzleReceiver(JsonObject rootObject){
        return rootObject.getAsJsonArray("Puzzle");
    }
    public JsonPrimitive enemyClear(JsonObject rootObject){
        return rootObject.getAsJsonPrimitive("EnemyClear");
    }


}
