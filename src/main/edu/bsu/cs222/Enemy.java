package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;

public class Enemy {
    String name;
    int health;
    int attack;
    int hit;

    public Enemy(String enemyName) throws FileNotFoundException {
        Battle battle = new Battle();
        JsonObject enemyList = battle.parseEnemies();
        JsonObject enemyObject = battle.receiveEnemy(enemyList, enemyName);
        health = battle.healthReceiver(enemyObject);
        attack = battle.attackReceiver(enemyObject);
        name = enemyName;
        hit = battle.getHit(enemyObject);
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

    public int getEnemyHit() {
        return hit;
    }

    @Override
    public String toString() {
        return("Enemy Type: " + getEnemyName() + "\nEnemy Health: " + getEnemyHealth() + "\nEnemy Attack: " + getEnemyAttack() + "\n");
    }
}

