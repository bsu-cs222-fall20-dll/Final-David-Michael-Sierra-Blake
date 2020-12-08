package java.edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EnemyTest {

    @Test
    public void testNoEnemy() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject startRoom = rootObject.getAsJsonObject("TestStory").getAsJsonObject("StartRoom");

        JsonArray enemyArray = storyReader.enemyReceiver(startRoom);
        Assertions.assertEquals(enemyArray.toString(), "[]");
    }

    @Test
    public void testRoomThreeNotNull() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        Assertions.assertNotNull(enemy);
    }

    @Test
    public void testRoomThreeName() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        Assertions.assertEquals(enemy.getEnemyName(),"Skeleton");
    }

    @Test
    public void testRoomThreeHealth() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        Assertions.assertEquals(enemy.getEnemyHealth(),8);
    }

    @Test
    public void testRoomThreeAttack() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        Assertions.assertEquals(enemy.getEnemyAttack(),1);
    }

    @Test
    public void testRoomThreeDamage() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        int damage = 3;
        enemy.setEnemyHealth(damage);
        Assertions.assertEquals(enemy.getEnemyHealth(),5);
    }

    @Test
    public void testRoomThreeHit() throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        InputStream is = new FileInputStream("src/test/resources/test-story.json");
        JsonObject rootObject = storyReader.parse(is);
        JsonObject room = rootObject.getAsJsonObject("TestStory").getAsJsonObject("RoomThree");

        JsonArray enemyArray = storyReader.enemyReceiver(room);
        String enemyName = enemyArray.get(0).toString().replace("\"", "");

        Enemy enemy = new Enemy(enemyName);
        int hit = enemy.getEnemyHit();
        Assertions.assertEquals(hit,4);
    }
}
