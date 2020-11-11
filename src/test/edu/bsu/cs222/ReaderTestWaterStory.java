package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReaderTestWaterStory {
    @Test
    public void testPrintsStartRoom() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("WaterStory").getAsJsonObject("StartRoom");

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
    public void getStoryNameTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        String storyName = storyReader.getStoryName(is);
        Assertions.assertEquals(storyName, "WaterStory");
    }

    @Test
    public void roomReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        Assertions.assertEquals(room.toString(), "{\"Text\":\"You wake up in a cold, stone room, leaned against one of the walls. Water is dripping from the ceiling, and there's enough in the room to go up to your waist. Where are you? You look around the room. There is a door ahead of you, it's old wood looking as if it will fall apart soon.\",\"Actions\":{\"Action1\":[\"Go through the door.\",\"EnemyRoom1\"]},\"Puzzle\":[\"null\",\"ifPass\",\"ifFail\"],\"Enemies\":[],\"EnemyClear\":\"null\"}");
    }

    @Test
    public void roomReceiverRoomTwoTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        Assertions.assertNotNull(room);
    }

    @Test
    public void textReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "You wake up in a cold, stone room, leaned against one of the walls. Water is dripping from the ceiling, and there's enough in the room to go up to your waist. Where are you? You look around the room. There is a door ahead of you, it's old wood looking as if it will fall apart soon.");
    }
    @Test
    public void enemyReceiverTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        JsonArray enemies = storyReader.enemyReceiver(room);
        System.out.println(enemies);
        Assertions.assertEquals(enemies.toString(), "[]");

    }
    @Test
    public void startRoomActionsTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        System.out.println(actions);
        Assertions.assertEquals(actions.toString(), "{\"Action1\":[\"Go through the door.\",\"EnemyRoom1\"]}");
    }

    @Test
    public void actionListOnlyOne() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),1);
    }

    @Test
    public void actionListOnlyTwo() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),2);
    }

    @Test
    public void actionListActionFirst() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(0);
        String actionResult = storyReader.getActionListAction(action);
        Assertions.assertEquals(actionResult, "\"Attempt the puzzle.\"");
    }

    @Test
    public void actionListActionSecond() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(1);
        String actionResult = storyReader.getActionListAction(action);
        Assertions.assertEquals(actionResult, "\"Ignore the puzzle.\"");
    }

    @Test
    public void actionListResultFirst() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(0);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "DoPuzzle");
    }

    @Test
    public void actionListResultSecond() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "PuzzleRoom1", "WaterStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(1);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "IgnorePuzzle");
    }

    @Test
    public void puzzleTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        JsonArray puzzle = storyReader.puzzleReceiver(room);
        System.out.println(puzzle);
        Assertions.assertEquals(puzzle.toString(), "[\"null\",\"ifPass\",\"ifFail\"]");
    }
    @Test
    public void enemyClearTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "WaterStory");
        JsonPrimitive clear = storyReader.enemyClear(room);
        System.out.println(clear);
        Assertions.assertEquals(clear.toString(), "\"null\"");
    }

    @Test
    public void endWinTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "EndWin", "WaterStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "With no knowledge of how you arrived, you escaped the watery dungeon below you. The sun shines on the field you find yourself in, with no civilization in sight.");
    }

    @Test
    public void endLoseTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/water-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "EndLose", "WaterStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "With no knowledge of how you got there, you have succumbed to the watery dungeon you woke up in. Game Over.");
    }
}
