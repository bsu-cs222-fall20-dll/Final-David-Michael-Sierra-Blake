package java.edu.bsu.cs222;

import java.io.FileNotFoundException;
import java.util.ArrayList;
public class BattleRoom extends Battle {


    public String battle(Room room) throws FileNotFoundException {
        System.out.println();
        System.out.println();

        int dice;
        Player player = new Player();

        ArrayList<Enemy> enemyArrayList = room.getEnemies();

        for(Enemy enemy: enemyArrayList) {
            System.out.println();
            System.out.println("Enemy: " + enemy.getEnemyName());
            while(enemy.getEnemyHealth() > 0) {
                dice = (int) (Math.random() * 6) + 1;
                if (dice > enemy.getEnemyHit()) {
                    enemy.setEnemyHealth(1);
                    System.out.println("You rolled a " + dice + ". You hit. Enemy health = " + enemy.getEnemyHealth());
                } else if (dice == enemy.getEnemyHit()) {
                    System.out.println("You did not hit. Enemy health = " + enemy.getEnemyHealth());
                } else {
                    player.setHealthAfterDamage(1);
                    System.out.println("You were hit by the enemy for 1 damage. Current health = " + player.getHealth());
                }
                if(player.getHealth() == 0) {
                    System.out.println("You have lost all of your health!");
                    return "EndLose";
                }
            }
        }
        System.out.println();
        System.out.println();
        return room.getEnemyClear();
    }
}
