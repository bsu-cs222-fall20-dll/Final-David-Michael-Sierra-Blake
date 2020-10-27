package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

public class ReaderTest {

    @Test
    public void testPrintsStartRoom() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");

        JsonPrimitive startRoomText = startRoom.getAsJsonPrimitive("Text");
        JsonObject startRoomActions = startRoom.getAsJsonObject("Actions");
        JsonArray startRoomActions1 = startRoomActions.getAsJsonArray("Action1");
        JsonArray startRoomActions2 = startRoomActions.getAsJsonArray("Action2");
        JsonArray startRoomActions3 = startRoomActions.getAsJsonArray("Action3");
        JsonArray startRoomPuzzle = startRoom.getAsJsonArray("Puzzle");
        JsonArray startRoomEnemies = startRoom.getAsJsonArray("Enemies");
        JsonPrimitive startRoomEnemyClear = startRoom.getAsJsonPrimitive("EnemyClear");

        System.out.println(startRoomText);
        System.out.println(startRoomActions1);
        System.out.println(startRoomActions2);
        System.out.println(startRoomActions3);
        System.out.println(startRoomPuzzle);
        System.out.println(startRoomEnemies);
        System.out.println(startRoomEnemyClear);
    }

    @Test
    public void roomReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        Assertions.assertEquals(room.toString(), "{\"Text\":\"This is the start room to the test story.\",\"Actions\":{\"Action1\":[\"This is the first action.\",\"RoomTwo\"],\"Action2\":[\"This is the second action.\",\"RoomThree\"],\"Action3\":[\"This is the third action. It will end the game.\",\"End\"]},\"Puzzle\":[\"null\",\"ifPass\",\"ifFail\"],\"Enemies\":[],\"EnemyClear\":\"null\"}");
    }

    @Test
    public void roomReceiverRoomTwoTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        storyReader.roomReceiver(rootObject, "RoomTwo");
    }

    @Test
    public void textReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonPrimitive text = storyReader.textReceiver(room);
        Assertions.assertEquals(text.toString(), "\"This is the start room to the test story.\"");
    }
    @Test
    public void enemyReceiverTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonArray enemies = storyReader.enemyReceiver(room);
        System.out.println(enemies);
        Assertions.assertEquals(enemies.toString(), "[]");

    }
    @Test
    public void startRoomActionsTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonObject actions = storyReader.actionsReceiver(room);
        System.out.println(actions);
        Assertions.assertEquals(actions.toString(), "{\"Action1\":[\"This is the first action.\",\"RoomTwo\"],\"Action2\":[\"This is the second action.\",\"RoomThree\"],\"Action3\":[\"This is the third action. It will end the game.\",\"End\"]}");
    }

    @Test
    public void actionListAllThree() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),3);
    }

    @Test
    public void actionListOnlyTwo() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),2);
    }

    @Test
    public void actionListActionFirst() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(0);
        String actionResult = storyReader.getActionListAction(action);
        Assertions.assertEquals(actionResult, "\"Attempt the puzzle.\"");
    }

    @Test
    public void actionListActionSecond() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(1);
        String actionResult = storyReader.getActionListAction(action);
        Assertions.assertEquals(actionResult, "\"Ignore the puzzle.\"");
    }

    @Test
    public void actionListResultFirst() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(0);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "\"DoPuzzle\"");
    }

    @Test
    public void actionListResultSecond() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(1);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "\"IgnorePuzzle\"");
    }

    @Test
    public void puzzleTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonArray puzzle = storyReader.puzzleReceiver(room);
        System.out.println(puzzle);
        Assertions.assertEquals(puzzle.toString(), "[\"null\",\"ifPass\",\"ifFail\"]");
    }
    @Test
    public void enemyClearTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom");
        JsonPrimitive clear = storyReader.enemyClear(room);
        System.out.println(clear);
        Assertions.assertEquals(clear.toString(), "\"null\"");
    }


}
