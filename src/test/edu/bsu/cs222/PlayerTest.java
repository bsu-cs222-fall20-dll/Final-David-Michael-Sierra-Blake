package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class PlayerTest {
    @Test
    public void testPlayerHealth() throws FileNotFoundException {
        Player player = new Player();
        Assertions.assertEquals(player.getHealth(), 15);
    }

    @Test
    public void testPlayerAttack() throws FileNotFoundException {
        Player player = new Player();
        Assertions.assertEquals(player.getAttack(), 4);
    }

    @Test
    public void testPlayerAttackBonus() throws FileNotFoundException {
        Player player = new Player();
        Assertions.assertEquals(player.getAttackBonus(), 0);
    }

    @Test
    public void testPlayerHealthBonus() throws FileNotFoundException {
        Player player = new Player();
        Assertions.assertEquals(player.getHealthBonus(), 0);
    }

    @Test
    public void testEditAttackBonus() throws FileNotFoundException {
        Player player = new Player();
        int newAttackBonus = 2;
        player.setAttackBonus(newAttackBonus);
        Assertions.assertEquals(player.getAttack(), 6);
    }

    @Test
    public void testEditHealthBonus() throws FileNotFoundException {
        Player player = new Player();
        int newHealthBonus = 7;
        player.setHealthBonus(newHealthBonus);
        Assertions.assertEquals(player.getHealth(), 22);
    }

    @Test
    public void testEditHealthWithDamage() throws FileNotFoundException {
        Player player = new Player();
        int damage = 3;
        player.setHealthAfterDamage(damage);
        Assertions.assertEquals(player.getHealth(), 12);
    }
}
