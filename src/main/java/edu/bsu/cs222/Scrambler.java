package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Scrambler {
    TextArea displayText = new TextArea();
    TextArea inputText = new TextArea();
    String throwType = "";
    Boolean leave = false;
    int tries = 5;
    public JsonObject parseWordScrambler() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/dictionary.json"));
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }
    public JsonObject recieveWord(JsonObject rootObject, String dictionary) {
        JsonObject wordScrambler = rootObject.getAsJsonObject("Dictionary").getAsJsonObject(dictionary);
        if (wordScrambler == null) {
            wordScrambler = rootObject.getAsJsonObject("Dictionary").getAsJsonObject("Fallback");
        }
        return wordScrambler;
    }



