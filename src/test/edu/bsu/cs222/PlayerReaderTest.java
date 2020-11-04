package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class PlayerReaderTest {
    @Test
    public void testPlayer() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        Assertions.assertEquals(playerObject.toString(), "{\"Player\":{\"Stats\":{\"AttackBonus\":0,\"HealthBonus\":0},\"Attack\":4,\"Health\":15}}");
    }

    @Test
    public void testPlayerStepDown() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        Assertions.assertEquals(player.toString(), "{\"Stats\":{\"AttackBonus\":0,\"HealthBonus\":0},\"Attack\":4,\"Health\":15}");
    }

    @Test
    public void testPlayerHealth() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        int playerHealth = playerReader.healthReceiver(player);
        Assertions.assertEquals(playerHealth, 15);
    }

    @Test
    public void testPlayerAttack() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        int playerAttack = playerReader.attackReceiver(player);
        Assertions.assertEquals(playerAttack, 4);
    }

    @Test
    public void testPlayerStats() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        JsonObject playerStats = playerReader.statsReceiver(player);
        Assertions.assertEquals(playerStats.toString(), "{\"AttackBonus\":0,\"HealthBonus\":0}");
    }

    @Test
    public void testPlayerHealthBonus() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        int healthBonus = playerReader.healthBonusReceiver(player);
        Assertions.assertEquals(healthBonus, 0);
    }

    @Test
    public void testPlayerAttackBonus() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);
        int attackBonus = playerReader.attackBonusReceiver(player);
        Assertions.assertEquals(attackBonus, 0);
    }
}
