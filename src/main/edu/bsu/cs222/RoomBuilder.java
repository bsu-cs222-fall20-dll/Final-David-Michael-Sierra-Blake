package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;


public class RoomBuilder {
    StoryReader storyReader = new StoryReader();

    public JsonObject firstRoom(JsonObject story) {
        JsonObject currentRoom = storyReader.roomReceiver(story, "StartRoom");

        return storyReader.actionsReceiver(currentRoom);


    }

    public JsonObject nextRoom(JsonObject story, String roomName) {
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        if (roomName.equals("\"LastRoom\"")) {
            lastRoom();
        } else if (!storyReader.puzzleReceiver(currentRoom).toString().equals("[\"null\", \"ifPass\", \"ifFail\"]")){
            return puzzleRoomInitiate(story, roomName);
        } else if (!storyReader.enemyReceiver(currentRoom).toString().equals("[]")) {
            enemyRoom(story, roomName);
        } else {
            emptyRoom();
        }
        return null;
    }

    public JsonPrimitive enemyRoom(JsonObject story, String roomName) {
        System.out.println("Enemy Room!");
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        return storyReader.enemyClear(currentRoom);

    }

    public JsonObject puzzleRoomInitiate(JsonObject story, String roomName) {
        System.out.println("Puzzle room!");

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
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName);

        JsonArray puzzleArray = storyReader.puzzleReceiver(currentRoom);

        Puzzle puzzle = new Puzzle();
        return puzzle.ifPassAction(puzzleArray);

    }

    public void emptyRoom() {
    }

    public void lastRoom() {
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
