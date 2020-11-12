package edu.bsu.cs222;

public class BattleRoom {

    //TODO: create a variable for the player that is a number between 1 and 6
    //TODO: grab the Player class with something like Player player = new Player();
    //TODO: call the current room in the class (require it in the parameter)
    //TODO: grab the enemy with something like Enemy enemy = room.getEnemy(0); This would be the first enemy in the room
    //Whenever the roll variable is higher than the hit value of the enemy's enemy.getEnemyHit(),
    //decide the damage however you need, and then call enemy.setEnemyHealth(damage) to subtract the damage from their health (damage should be a positive int)
    //Whenever the roll variable is lower than the enemy's hit value, call the player's player.setHealthAfterDamage(damage) (damage should be a positive int)
    //This will continue until either the player is dead, or the enemy is dead

    //Eventually:
    //When the player dies, this will return the string "EndLose"
    //When the player wins, this will return the string stored in currentRoom.getEnemyClear()
    //If there is more than one enemy, the battle will be in a loop until the player dies or runs out of enemies to kill and wins
}
