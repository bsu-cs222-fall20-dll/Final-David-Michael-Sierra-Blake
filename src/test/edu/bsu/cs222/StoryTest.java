package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class StoryTest {

    @Test
    public void testPuzzleFullPath() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject story = storyReader.parse(is);

        JsonObject firstRoomActions = roomBuilder.firstRoom(story); // Room 1

        ArrayList<JsonArray> actionList = storyReader.getActionList(firstRoomActions);
        String actionResult = storyReader.getActionListResult(actionList.get(0)); // First action on actionList for StartRoom

        JsonObject puzzleActions = roomBuilder.nextRoom(story, actionResult); // Room 2

        ArrayList<JsonArray> puzzleActionList = storyReader.getActionList(puzzleActions);
        String puzzleActionResult = storyReader.getActionListResult(puzzleActionList.get(0));
        String nextRoom = roomBuilder.puzzleRoomAction(story, actionResult, puzzleActionResult);

        roomBuilder.nextRoom(story, nextRoom); // Room 4


    }

    @Test
    public void testPuzzleIgnore() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject story = storyReader.parse(is);

        JsonObject firstRoomActions = roomBuilder.firstRoom(story); // Room 1

        ArrayList<JsonArray> actionList = storyReader.getActionList(firstRoomActions);
        String actionResult = storyReader.getActionListResult(actionList.get(0)); // First action on actionList for StartRoom

        JsonObject puzzleActions = roomBuilder.nextRoom(story, actionResult); // Room 2

        ArrayList<JsonArray> puzzleActionList = storyReader.getActionList(puzzleActions);
        String puzzleActionResult = storyReader.getActionListResult(puzzleActionList.get(1));
        roomBuilder.puzzleRoomAction(story, actionResult, puzzleActionResult);
    }

    @Test
    public void testEnemyRoom() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject story = storyReader.parse(is);

        JsonObject firstRoomActions = roomBuilder.firstRoom(story); // Room 1

        ArrayList<JsonArray> actionList = storyReader.getActionList(firstRoomActions);
        String actionResult = storyReader.getActionListResult(actionList.get(1)); // First action on actionList for StartRoom

        JsonPrimitive enemyClear = roomBuilder.enemyRoom(story, actionResult);
        roomBuilder.nextRoom(story, enemyClear.toString());
    }
}
