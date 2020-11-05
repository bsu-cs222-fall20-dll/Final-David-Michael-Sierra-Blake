package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class Room {
    String text;
    ArrayList<String> actions;
    ArrayList<String> actionResults;

    public Room(JsonObject story, String roomName, String storyName) {
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);
        text = storyReader.textReceiver(currentRoom);
        ArrayList<JsonArray> actionList = storyReader.getActionList(storyReader.actionsReceiver(currentRoom));
        for(JsonArray action : actionList) {
            actions.add(storyReader.getActionListAction(action));
            actionResults.add(storyReader.getActionListResult(action));
        }
    }

    public String getRoomText() {
        return text;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getAction(int number) {
        return actions.get(number);
    }

    public ArrayList<String> getActionResults() {
        return actionResults;
    }

    public String getActionResult(int number) {
        return actionResults.get(number);
    }
}
