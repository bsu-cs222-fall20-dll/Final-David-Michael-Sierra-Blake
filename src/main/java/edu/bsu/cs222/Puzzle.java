package java.edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Puzzle {
    String puzzleType;
    String ifPassAction;
    String ifFailAction;

    public Puzzle(JsonObject room) {
        StoryReader storyReader = new StoryReader();
        JsonArray puzzle = storyReader.puzzleReceiver(room);
        puzzleType = puzzleType(puzzle);
        ifPassAction = ifPassAction(puzzle);
        ifFailAction = ifFailAction(puzzle);
    }

    public String puzzleType(JsonArray puzzle) {
        String type = puzzle.get(0).toString();
        type = removeQuotes(type);
        return type;
    }

    public String ifPassAction(JsonArray puzzle) {
        String ifPass = puzzle.get(1).toString();
        ifPass = removeQuotes(ifPass);
        return ifPass;
    }

    public String ifFailAction(JsonArray puzzle) {
        String ifFail = puzzle.get(2).toString();
        ifFail = removeQuotes(ifFail);
        return ifFail;
    }

    public String removeQuotes(String input) {
        return input.replace("\"", "");
    }

    public String getPuzzleType() {
        return puzzleType;
    }

    public String getIfPassAction() {
        return ifPassAction;
    }

    public String getIfFailAction() {
        return ifFailAction;
    }

    @Override
    public String toString() {
        return("Puzzle Type: " + getPuzzleType() + "\nOn Pass: " + getIfPassAction() + "\nOn Fail: " + getIfFailAction() + "\n");
    }
}
