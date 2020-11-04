package edu.bsu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class BattleTest {
    @Test
    public void printMonsters() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        System.out.println(monsterList);
    }

    @Test
    public void printSkeleton() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        System.out.println(skeleton);
    }

    @Test
    public void printSkeletonHealth() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        JsonPrimitive skeletonHealth = battle.healthReceiver(skeleton);
        System.out.println(skeletonHealth);
    }

    @Test
    public void printSkeletonAttack() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Skeleton";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        JsonPrimitive skeletonHealth = battle.attackReceiver(skeleton);
        System.out.println(skeletonHealth);
    }

    @Test
    public void printFallback() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monsterName = "Karen";
        JsonObject monsterObject = battle.receiveEnemy(monsterList, monsterName);
        System.out.println(monsterObject);
    }

    @Test
    public void printFallbackHealth() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Karen";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        JsonPrimitive skeletonHealth = battle.healthReceiver(skeleton);
        System.out.println(skeletonHealth);
    }

    @Test
    public void printFallbackAttack() throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject monsterList = battle.parseEnemies();
        String monster = "Karen";
        JsonObject skeleton = battle.receiveEnemy(monsterList, monster);
        JsonPrimitive skeletonHealth = battle.attackReceiver(skeleton);
        System.out.println(skeletonHealth);
    }
}
