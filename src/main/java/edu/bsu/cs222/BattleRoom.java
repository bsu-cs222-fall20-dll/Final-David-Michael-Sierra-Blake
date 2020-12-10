package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class BattleRoom extends Battle {


    public String battle(Room room, Button actionButton1, TextArea roomText, Player player) {
        StringBuilder battleText = new StringBuilder();

        int dice;

        ArrayList<Enemy> enemyArrayList = room.getEnemies();

        for(Enemy enemy: enemyArrayList) {
            battleText.append("Enemy: ").append(enemy.getEnemyName());
            while(enemy.getEnemyHealth() > 0) {
                dice = (int) (Math.random() * 6) + 1;
                if (dice > enemy.getEnemyHit()) {
                    enemy.setEnemyHealth(player.getAttack());
                    battleText.append("\nYou rolled a ").append(dice).append(". You hit. Enemy health = ").append(enemy.getEnemyHealth());
                } else if (dice == enemy.getEnemyHit()) {
                    battleText.append("\nNeither of you hit. Enemy health = ").append(enemy.getEnemyHealth());
                } else {
                    player.setHealthAfterDamage(enemy.getEnemyAttack());
                    battleText.append("\nYou were hit by the enemy for 1 damage. Current health = ").append(player.getHealth());
                }
                if(player.getHealth() == 0) {
                    battleText.append("\nYou have lost all of your health!");
                    roomText.setText(battleText.toString());
                    actionButton1.setText("End Game");
                    return "EndLose";
                }
            }
            battleText.append("\nEnemy defeated! One stat point added to your character.");
            player.addPoint();
            battleText.append("\nCurrent points: ").append(player.getPoints()).append("\n");
        }
        battleText.append("\n\nRoom defeated! One additional stat point rewarded.");
        player.addPoint();
        battleText.append("\nCurrent points: ").append(player.getPoints()).append("\n");
        roomText.setText(battleText.toString());
        actionButton1.setText("Continue to next room");
        return room.getEnemyClear();
    }
}
