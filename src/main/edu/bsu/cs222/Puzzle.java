package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
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
}
