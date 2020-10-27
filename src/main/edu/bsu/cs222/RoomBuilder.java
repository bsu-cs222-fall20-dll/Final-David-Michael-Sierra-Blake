package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;

public class RoomBuilder {

    public JsonObject firstRoom(JsonObject story) {
        StoryReader storyReader = new StoryReader();

        JsonObject currentRoom = storyReader.roomReceiver(story, "StartRoom");

        JsonObject actions = storyReader.actionsReceiver(currentRoom);


        return actions;

    }

    public JsonObject nextRoom(JsonObject story, String roomName) {
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);
        JsonObject roomReturns;

        if (roomName.equals("\"LastRoom\"")) {
            lastRoom(story, roomName);
        } else if (!storyReader.puzzleReceiver(currentRoom).toString().equals("[\"null\", \"ifPass\", \"ifFail\"]")){
            return puzzleRoomInitiate(story, roomName);
        } else if (!storyReader.enemyReceiver(currentRoom).toString().equals("[]")) {
            enemyRoom(story, roomName);
        } else {
            emptyRoom(story, roomName);
        }
        return null;
    }

    public JsonPrimitive enemyRoom(JsonObject story, String roomName) {
        System.out.println("Enemy Room!");
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        JsonPrimitive text = storyReader.textReceiver(currentRoom);
        JsonArray enemies = storyReader.enemyReceiver(currentRoom);
        JsonPrimitive enemyClear = storyReader.enemyClear(currentRoom);
        JsonArray puzzle = storyReader.puzzleReceiver(currentRoom);

        return enemyClear;

    }

    public JsonObject puzzleRoomInitiate(JsonObject story, String roomName) {
        System.out.println("Puzzle room!");

        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        return storyReader.actionsReceiver(currentRoom);
    }

    public String puzzleRoomAction(JsonObject story, String roomName, String actionChoice) {
        if(actionChoice.equals("DoPuzzle")) {
            return puzzleRoom(story, roomName);
        } else if (actionChoice.equals("IgnorePuzzle")) {
            finish("EndLose");
        }
        return null;
    }

    public String puzzleRoom(JsonObject story, String roomName) {
        System.out.println("Doing the puzzle!");
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        JsonArray puzzleArray = storyReader.puzzleReceiver(currentRoom);

        Puzzle puzzle = new Puzzle();
        String puzzleType = puzzle.puzzleType(puzzleArray);
        String ifPass = puzzle.ifPassAction(puzzleArray);
        String ifFail = puzzle.ifFailAction(puzzleArray);

        return ifPass;
    }

    public void emptyRoom(JsonObject story, String roomName) {
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        JsonPrimitive text = storyReader.textReceiver(currentRoom);
        JsonObject actions = storyReader.actionsReceiver(currentRoom);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray enemies = storyReader.enemyReceiver(currentRoom);
        JsonPrimitive enemyClear = storyReader.enemyClear(currentRoom);
        JsonArray puzzle = storyReader.puzzleReceiver(currentRoom);
    }

    public void lastRoom(JsonObject story, String roomName) {
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        finish("EndWin");
    }

    public void finish(String endType) {
        if(endType.equals("EndLose")) {
            System.out.println("You lose the game!");
        } else if (endType.equals("EndWin")) {
            System.out.println("You win the game!");
        } else {
            System.out.println("The game has ended.");
        }
    }


}
