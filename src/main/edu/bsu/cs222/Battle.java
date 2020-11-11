package edu.bsu.cs222;

import com.google.gson.*;

import java.io.*;

public class Battle {
    //Suppressed because although deprecated, the JsonParser is
    //the best course of action for our project's needs and still works fully
    @SuppressWarnings("deprecation")
    public JsonObject parseEnemies() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/monsters.json"));
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public JsonObject receiveEnemy(JsonObject rootObject, String monster) {
        JsonObject monsterEnemy = rootObject.getAsJsonObject("Monsters").getAsJsonObject(monster);
        if (monsterEnemy == null) {
            monsterEnemy = rootObject.getAsJsonObject("Monsters").getAsJsonObject("Fallback");
        }
        return monsterEnemy;
    }

    public int healthReceiver(JsonObject monster) {
        return monster.getAsJsonPrimitive("Health").getAsInt();
    }

    public int attackReceiver(JsonObject monster) {
        return monster.getAsJsonPrimitive("Attack").getAsInt();
    }

}
