package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PuzzleTest {

    @Test
    public void puzzleTypeTestNull() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();


        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");

        Puzzle puzzle = new Puzzle(startRoom);
        Assertions.assertEquals(puzzle.getPuzzleType(), "null");
    }

    @Test
    public void puzzleTypeTestColor() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomTwo");

        Puzzle puzzle = new Puzzle(room);
        Assertions.assertEquals(puzzle.getPuzzleType(), "color");
    }

    @Test
    public void puzzleTestIfPass() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomTwo");

        Puzzle puzzle = new Puzzle(room);

        Assertions.assertEquals(puzzle.getIfPassAction(), "LastRoom");
    }

    @Test
    public void puzzleTestIfFail() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomTwo");

        Puzzle puzzle = new Puzzle(room);
        Assertions.assertEquals(puzzle.getIfFailAction(), "EndLose");
    }
}
