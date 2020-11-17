package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class BattleTest {

    @Test
    public void testSkeleton() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        Assertions.assertEquals(skeleton.toString(), "{\"Health\":8,\"Attack\":1,\"Hit\":4}");
    }

    @Test
    public void testSkeletonHealth() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        int skeletonHealth = battle.healthReceiver(skeleton);
        Assertions.assertEquals(skeletonHealth, 8);
    }

    @Test
    public void testSkeletonAttack() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        int skeletonAttack = battle.attackReceiver(skeleton);
        Assertions.assertEquals(skeletonAttack, 1);
    }

    @Test
    public void testSkeletonHit() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        int skeletonHit = battle.getHit(skeleton);
        Assertions.assertEquals(skeletonHit, 4);
    }

    @Test
    public void testFallback() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monsterName = "Karen";
        JsonObject monsterObject = battle.receiveEnemy(monsterList, monsterName);
        Assertions.assertEquals(monsterObject.toString(), "{\"Health\":7,\"Attack\":1,\"Hit\":4}");
    }

    @Test
    public void testFallbackHealth() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monsterName = "Karen";
        JsonObject monsterObject = battle.receiveEnemy(monsterList, monsterName);
        int monsterHealth = battle.healthReceiver(monsterObject);
        Assertions.assertEquals(monsterHealth, 7);
    }

    @Test
    public void testFallbackAttack() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monsterName = "Karen";
        JsonObject monsterObject = battle.receiveEnemy(monsterList, monsterName);
        int monsterAttack = battle.attackReceiver(monsterObject);
        Assertions.assertEquals(monsterAttack, 1);
    }

    @Test
    public void testFallbackHit() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monsterName = "Karen";
        JsonObject monsterObject = battle.receiveEnemy(monsterList, monsterName);
        int monsterHit = battle.getHit(monsterObject);
        Assertions.assertEquals(monsterHit, 4);
    }
}
