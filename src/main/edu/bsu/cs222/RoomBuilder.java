package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


public class RoomBuilder {
    StoryReader storyReader = new StoryReader();

    public JsonObject firstRoom(JsonObject story, String storyName) {
        JsonObject currentRoom = storyReader.roomReceiver(story, "StartRoom", storyName);

        return storyReader.actionsReceiver(currentRoom);


    }

    public JsonObject nextRoom(JsonObject story, String roomName, String storyName) {
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);

        if (roomName.equals("\"LastRoom\"")) {
            lastRoom();
        } else if (!storyReader.puzzleReceiver(currentRoom).toString().equals("[\"null\", \"ifPass\", \"ifFail\"]")){
            return puzzleRoomInitiate(story, roomName, storyName);
        } else if (!storyReader.enemyReceiver(currentRoom).toString().equals("[]")) {
            enemyRoom(story, roomName, storyName);
        } else if (roomName.equals("\"EndWin\"")) {
            winRoom(story, roomName, storyName);
        } else if (roomName.equals("\"EndLose\"")) {
            loseRoom(story, roomName, storyName);
        } else {
            emptyRoom();
        }
        return null;
    }

    public JsonPrimitive enemyRoom(JsonObject story, String roomName, String storyName) {
        System.out.println("Enemy Room!");
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);

        return storyReader.enemyClear(currentRoom);

    }

    public JsonObject puzzleRoomInitiate(JsonObject story, String roomName, String storyName) {
        System.out.println("Puzzle room!");

        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);

        return storyReader.actionsReceiver(currentRoom);
    }

    public String puzzleRoomAction(JsonObject story, String roomName, String actionChoice, String storyName) {
        if(actionChoice.equals("DoPuzzle")) {
            return puzzleRoom(story, roomName, storyName);
        } else if (actionChoice.equals("IgnorePuzzle")) {
            loseRoom(story, roomName, storyName);
        }
        return null;
    }

    public String puzzleRoom(JsonObject story, String roomName, String storyName) {
        System.out.println("Doing the puzzle!");
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);

        JsonArray puzzleArray = storyReader.puzzleReceiver(currentRoom);

        Puzzle puzzle = new Puzzle();
        return puzzle.ifPassAction(puzzleArray);

    }

    public void emptyRoom() {
    }

    public void lastRoom() {

    }

    public void loseRoom(JsonObject story, String roomName, String storyName) {
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);
        String roomText = storyReader.textReceiver(currentRoom);
        System.out.println(roomText);
    }

    public void winRoom(JsonObject story, String roomName, String storyName) {
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);
        String roomText = storyReader.textReceiver(currentRoom);
        System.out.println(roomText);
    }


}
