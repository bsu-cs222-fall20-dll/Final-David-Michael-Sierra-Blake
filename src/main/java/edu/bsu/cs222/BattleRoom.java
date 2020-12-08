package java.edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BattleRoom extends Battle {


    public String battle(Room room, Button actionButton1, TextArea roomText) throws FileNotFoundException {
        System.out.println();
        System.out.println();

        StringBuilder battleText = new StringBuilder();

        int dice;
        Player player = new Player();

        ArrayList<Enemy> enemyArrayList = room.getEnemies();

        for(Enemy enemy: enemyArrayList) {
            System.out.println();
            battleText.append("Enemy: ").append(enemy.getEnemyName());
            while(enemy.getEnemyHealth() > 0) {
                dice = (int) (Math.random() * 6) + 1;
                if (dice > enemy.getEnemyHit()) {
                    enemy.setEnemyHealth(1);
                    battleText.append("\nYou rolled a ").append(dice).append(". You hit. Enemy health = ").append(enemy.getEnemyHealth());
                } else if (dice == enemy.getEnemyHit()) {
                    battleText.append("\nYou did not hit. Enemy health = ").append(enemy.getEnemyHealth());
                } else {
                    player.setHealthAfterDamage(1);
                    battleText.append("\nYou were hit by the enemy for 1 damage. Current health = ").append(player.getHealth());
                }
                if(player.getHealth() == 0) {
                    battleText.append("\nYou have lost all of your health!");
                    roomText.setText(battleText.toString());
                    actionButton1.setText("End Game");
                    return "EndLose";
                }
            }
        }
        roomText.setText(battleText.toString());
        System.out.println();
        System.out.println();
        actionButton1.setText("Continue to next room");
        return room.getEnemyClear();
    }
}
