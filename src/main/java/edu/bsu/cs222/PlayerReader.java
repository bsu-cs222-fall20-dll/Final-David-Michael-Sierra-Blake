package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;

//Suppressed because although deprecated, the JsonParser is
//the best course of action for our project's needs and still works fully
@SuppressWarnings("deprecation")
public class PlayerReader {
    public JsonObject parsePlayer() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/player.json"));
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public JsonObject receivePlayer(JsonObject rootObject) {
        return rootObject.getAsJsonObject("Player").getAsJsonObject();
    }

    public int healthReceiver(JsonObject playerObject) {
        return playerObject.getAsJsonPrimitive("Health").getAsInt();
    }

    public int attackReceiver(JsonObject playerObject) {
        return playerObject.getAsJsonPrimitive("Attack").getAsInt();
    }

    public JsonObject statsReceiver(JsonObject playerObject) {
        return playerObject.getAsJsonObject("Stats");
    }

    public int healthBonusReceiver(JsonObject player) {
        JsonObject stats = statsReceiver(player);
        return stats.getAsJsonPrimitive("HealthBonus").getAsInt();
    }

    public int attackBonusReceiver(JsonObject player) {
        JsonObject stats = statsReceiver(player);
        return stats.getAsJsonPrimitive("AttackBonus").getAsInt();
    }
}
