package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;

public class Enemy {
    String name;
    int health;
    int attack;

    public Enemy(String enemyName) throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject enemyList = battle.parseEnemies();
        JsonObject enemyObject = battle.receiveEnemy(enemyList, enemyName);
        health = battle.healthReceiver(enemyObject);
        attack = battle.attackReceiver(enemyObject);
        name = enemyName;
    }

    public String getEnemyName() {
        return name;
    }

    public int getEnemyHealth() {
        return health;
    }

    public int getEnemyAttack() {
        return attack;
    }

    public void setEnemyHealth(int damage) {
        health = health - damage;
    }
}
