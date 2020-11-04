package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;

public class Battle {
    public JsonObject parseEnemies() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(new FileInputStream("src/test/resources/monsters.json"));
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public JsonObject receiveEnemy(JsonObject rootObject, String monster) {
        return rootObject.getAsJsonObject("Monsters").getAsJsonObject(monster);
    }

    public JsonPrimitive healthReceiver(JsonObject monster) {
        return monster.getAsJsonPrimitive("Health");
    }

    public JsonPrimitive attackReceiver(JsonObject monster) {
        return monster.getAsJsonPrimitive("Attack");
    }

}
