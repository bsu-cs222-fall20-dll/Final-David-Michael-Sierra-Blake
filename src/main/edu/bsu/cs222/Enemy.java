package edu.bsu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Enemy {
    JsonPrimitive health;
    JsonPrimitive attack;

    public Enemy(JsonObject enemyName) {
        Battle battle = new Battle();
        health = battle.healthReceiver(enemyName);
        attack = battle.attackReceiver(enemyName);
    }

    public void getEnemyHealth() {

    }

    public void getEnemyAttack() {

    }

    public void setEnemyHealth() {

    }

    public void setEnemyAttack() {

    }
}
