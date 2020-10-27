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
        Puzzle puzzle = new Puzzle();

        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");
        JsonArray startRoomPuzzle = startRoom.getAsJsonArray("Puzzle");
        String puzzleType = puzzle.puzzleType(startRoomPuzzle);
        Assertions.assertEquals(puzzleType, "null");
    }
}
