package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;

public class Player {
    int health;
    int attack;
    int healthBonus;
    int attackBonus;
    int points;

    public Player() throws FileNotFoundException {
        PlayerReader playerReader = new PlayerReader();
        JsonObject playerObject = playerReader.parsePlayer();
        JsonObject player = playerReader.receivePlayer(playerObject);

        health = playerReader.healthReceiver(player);
        attack = playerReader.attackReceiver(player);
        healthBonus = playerReader.healthBonusReceiver(player);
        attackBonus = playerReader.attackBonusReceiver(player);
    }

    public int getHealth() {
        return health;
    }

    private void setHealthAfterBonus() {
        int bonus = getHealthBonus();
        this.health += bonus;
    }

    public void setHealthAfterDamage(int damage) {
        this.health -= damage;
    }

    public int getAttack() {
        return attack;
    }

    private void setAttack() {
        int attackAddition = getAttackBonus();
        this.attack += attackAddition;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
        setHealthAfterBonus();
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
        setAttack();
    }

    public void addPoint() {
        points += 1;
    }

    public void subtractPoint() {
        points -= 1;
    }

    public void spendPointOnHealth() {
        if(points > 0) {
            subtractPoint();
            setHealthBonus(3);
        }
    }

    public void spendPointOnDamage() {
        if(points > 0) {
            subtractPoint();
            setAttackBonus(1);
        }
    }

    public int getPoints() {
        return points;
    }
}
