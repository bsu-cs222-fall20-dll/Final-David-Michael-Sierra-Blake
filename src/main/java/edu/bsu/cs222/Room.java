package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Room {
    String text;
    ArrayList<String> actions = new ArrayList<>();
    ArrayList<String> actionResults = new ArrayList<>();
    Puzzle puzzle;
    ArrayList<Enemy> enemies = new ArrayList<>();
    String enemyClear;
    Boolean exitRoom;

    public Room(JsonObject story, String roomName, String storyName) throws FileNotFoundException {
        StoryReader storyReader = new StoryReader();
        JsonObject currentRoom = storyReader.roomReceiver(story, roomName, storyName);

        if (roomName.equals("End")) {
            System.out.println("Ending game.");
            exit(0);
        }
        text = storyReader.textReceiver(currentRoom).replace("\\n", "\n");
        if(!roomName.equals("EndWin") && !roomName.equals("EndLose")) {
            puzzle = new Puzzle(currentRoom);
            enemyClear = storyReader.enemyClear(currentRoom);

            JsonObject actionObject = storyReader.actionsReceiver(currentRoom);
            ArrayList<JsonArray> actionList = storyReader.getActionList(actionObject);
            for (JsonArray action : actionList) {
                actions.add(storyReader.getActionListAction(action));
                actionResults.add(storyReader.getActionListResult(action));
            }

            JsonArray enemyArray = storyReader.enemyReceiver(currentRoom);
            for (int i = 0; i < enemyArray.size(); i++) {
                String enemyName = enemyArray.get(i).toString().replace("\"", "");
                enemies.add(new Enemy(enemyName));
            }
            exitRoom = false;

        } else {
            exitRoom = true;
        }


    }

    public String getRoomText() {
        return text;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getAction(int number) {
        return actions.get(number);
    }

    public ArrayList<String> getActionResults() {
        return actionResults;
    }

    public String getActionResult(int number) {
        return actionResults.get(number);
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemy(int number) {
        return enemies.get(number);
    }

    public String getEnemyClear() {
        return enemyClear;
    }

    public Boolean getExitRoom() { return exitRoom;}
}
