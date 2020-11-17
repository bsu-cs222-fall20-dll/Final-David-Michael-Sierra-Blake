package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Terminal terminal = new Terminal();
        StoryReader storyReader = new StoryReader();
        BattleRoom battleRoom = new BattleRoom();
        Scanner scanner = new Scanner(System.in);

        terminal.printMainMenu();

        String choice = scanner.nextLine();
        ArrayList<String> choiceList = terminal.selectOption(choice);
        String storyName = choiceList.get(0);
        JsonObject story = storyReader.parse(new FileInputStream("src/test/resources/" + choiceList.get(1) + ".json"));
        String roomName = "StartRoom";
        boolean running = true;
        while (running) {
            Room room = new Room(story, roomName, storyName);
            terminal.printRoom(room);
            if(room.getEnemies().size() > 0) {
                roomName = battleRoom.battle(room);
            } else {
                int actionChoice = terminal.getActionChoice();
                roomName = room.getActionResult(actionChoice);
            }
            if(roomName.equals("EndWin") || roomName.equals("EndLose")) {
                running = false;
            }
        }
        Room endRoom = new Room(story, roomName, storyName);
        System.out.println(endRoom.getRoomText());
        exit(0);
    }
}
