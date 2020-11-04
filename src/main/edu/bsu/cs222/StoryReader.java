package edu.bsu.cs222;

import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

//Suppressed because although deprecated, the JsonParser is
//the best course of action for our project's needs and still works fully
@SuppressWarnings("deprecation")
public class StoryReader {
    public JsonObject parse(InputStream input) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public String getStoryName(InputStream input) {
        String parseValue = parse(input).toString();
        int colonLocation = parseValue.indexOf(":");
        return parseValue.substring(2,colonLocation - 1);
    }

    public JsonObject roomReceiver(JsonObject rootObject, String room, String story) {
        return rootObject.getAsJsonObject(story).getAsJsonObject(room);
    }

    public JsonPrimitive textReceiver(JsonObject room) {
        return room.getAsJsonPrimitive("Text");
    }

    public JsonArray enemyReceiver(JsonObject rootObject) {
        return rootObject.getAsJsonArray("Enemies");
    }
    public JsonObject actionsReceiver(JsonObject rootObject){
        return rootObject.getAsJsonObject("Actions");
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
