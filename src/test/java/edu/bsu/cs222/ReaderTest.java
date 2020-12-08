package java.edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

public class ReaderTest {

    @Test
    public void getStoryNameTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        String storyName = storyReader.getStoryName(is);
        Assertions.assertEquals(storyName, "TestStory");
    }

    @Test
    public void roomReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        Assertions.assertEquals(room.toString(), "{\"Text\":\"This is the start room to the test story.\",\"Actions\":{\"Action1\":[\"This is the first action.\",\"RoomTwo\"],\"Action2\":[\"This is the second action.\",\"RoomThree\"],\"Action3\":[\"This is the third action. It will end the game.\",\"End\"]},\"Puzzle\":[\"null\",\"ifPass\",\"ifFail\"],\"Enemies\":[],\"EnemyClear\":\"null\"}");
    }

    @Test
    public void roomReceiverRoomTwoTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
        Assertions.assertNotNull(room);
    }

    @Test
    public void textReceiverTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "This is the start room to the test story.");
    }
    @Test
    public void enemyReceiverTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        JsonArray enemies = storyReader.enemyReceiver(room);
        Assertions.assertEquals(enemies.toString(), "[]");

    }
    @Test
    public void startRoomActionsTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        Assertions.assertEquals(actions.toString(), "{\"Action1\":[\"This is the first action.\",\"RoomTwo\"],\"Action2\":[\"This is the second action.\",\"RoomThree\"],\"Action3\":[\"This is the third action. It will end the game.\",\"End\"]}");
    }

    @Test
    public void actionListAllThree() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),3);
    }

    @Test
    public void actionListOnlyTwo() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        Assertions.assertEquals(actionList.size(),2);
    }

    @Test
    public void actionListActionFirst() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
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
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
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
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(0);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "DoPuzzle");
    }

    @Test
    public void actionListResultSecond() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "RoomTwo", "TestStory");
        JsonObject actions = storyReader.actionsReceiver(room);
        ArrayList<JsonArray> actionList = storyReader.getActionList(actions);
        JsonArray action = actionList.get(1);
        String actionResult = storyReader.getActionListResult(action);
        Assertions.assertEquals(actionResult, "IgnorePuzzle");
    }

    @Test
    public void puzzleTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        JsonArray puzzle = storyReader.puzzleReceiver(room);
        Assertions.assertEquals(puzzle.toString(), "[\"null\",\"ifPass\",\"ifFail\"]");
    }
    @Test
    public void enemyClearTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "StartRoom", "TestStory");
        String clear = storyReader.enemyClear(room);
        Assertions.assertEquals(clear, "null");
    }

    @Test
    public void endWinTest() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "EndWin", "TestStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "You have won the test story.");
    }

    @Test
    public void endLoseTest() throws FileNotFoundException{
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = storyReader.roomReceiver(rootObject, "EndLose", "TestStory");
        String text = storyReader.textReceiver(room);
        Assertions.assertEquals(text, "You have lost the test story.");
    }


}
