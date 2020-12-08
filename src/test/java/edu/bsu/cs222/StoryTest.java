package java.edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StoryTest {

    @Test
    public void testPuzzleFullPath() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        JsonObject story = storyReader.parse(new FileInputStream("src/test/resources/test-story.json"));
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room startRoom = roomBuilder.nextRoom(story, roomName, storyName); //Room 1
        String nextRoomName = startRoom.getActionResult(0); //Action 1: "Go to Room 2"
        Room nextRoom = roomBuilder.nextRoom(story, nextRoomName, storyName); //Room 2
        Puzzle puzzle = nextRoom.getPuzzle();
        String ifPassAction = puzzle.getIfPassAction(); //Action on pass: "Go to Room 4"
        nextRoom = roomBuilder.nextRoom(story, ifPassAction, storyName); // Room 4
        nextRoomName = nextRoom.getActionResult(0); //Action 1: "Go to EndWin"
        nextRoom =  roomBuilder.nextRoom(story, nextRoomName, storyName); // EndWin
        Assertions.assertEquals(nextRoom.getRoomText(),"You have won the test story.");
    }

    @Test
    public void testPuzzleIgnore() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        JsonObject story = storyReader.parse(new FileInputStream("src/test/resources/test-story.json"));
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room startRoom = roomBuilder.nextRoom(story, roomName, storyName); //Room 1
        String nextRoomName = startRoom.getActionResult(0); //Action 1: "Go to Room 2"
        Room nextRoom = roomBuilder.nextRoom(story, nextRoomName, storyName); //Room 2
        Puzzle puzzle = nextRoom.getPuzzle();
        String ifFailAction = puzzle.getIfFailAction(); //Action on fail: "Go to EndLose"
        nextRoom = roomBuilder.nextRoom(story, ifFailAction, storyName); //EndLose
        Assertions.assertEquals(nextRoom.getRoomText(),"You have lost the test story.");
    }

    @Test
    public void testEnemyRoom() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();

        JsonObject story = storyReader.parse(new FileInputStream("src/test/resources/test-story.json"));
        String storyName = "TestStory";
        String roomName = "StartRoom";

        Room startRoom = roomBuilder.nextRoom(story, roomName, storyName); //Room 1
        String nextRoomName = startRoom.getActionResult(1); //Action 1: "Go to Room 3"
        Room nextRoom = roomBuilder.nextRoom(story, nextRoomName, storyName); //Room 3
        String enemyClear = nextRoom.getEnemyClear(); //Action: "Go to Room 4"
        nextRoom = roomBuilder.nextRoom(story, enemyClear, storyName); // Room 4
        nextRoomName = nextRoom.getActionResult(0); //Action 1: "Go to EndWin"
        nextRoom =  roomBuilder.nextRoom(story, nextRoomName, storyName); // EndWin
        Assertions.assertEquals(nextRoom.getRoomText(),"You have won the test story.");
    }
}
