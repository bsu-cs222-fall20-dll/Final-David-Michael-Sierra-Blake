package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Terminal {

    public void printMainMenu(){
        System.out.println("Game Options");
        System.out.println("------------");
        System.out.println("1) Water Story");
        System.out.println("2) Dream Story");
        System.out.println("3) Quit");
    }

    public ArrayList<String> selectOption(String choice) {
        ArrayList<String> choiceList = new ArrayList<>();
        boolean looping = true;
        while(looping) {
            switch (choice) {
                case "1":
                    choiceList.add("WaterStory");
                    choiceList.add("water-story");
                    looping = false;
                    break;
                case "2":
                    choiceList.add("DreamStory");
                    choiceList.add("dream");
                    looping = false;
                    break;
                case "3":
                    exit(0);
                default:
                    System.out.println("Enter Valid Input");
                    printMainMenu();
                    break;
            }
        }
        return choiceList;
    }

    public void printRoom(Room room){
        System.out.println(room.getRoomText());
    }

    public int getActionChoice(Room room){
        int numberedChoice = 1;
        for (String action: room.getActions()) {
            System.out.println(numberedChoice + ") " + action);
            numberedChoice++;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("What choice do you choose?");
        int choice = Integer.parseInt(scanner.nextLine());
        choice = choice - 1;
        return choice;
    }

    public void startLoop(JsonObject story, String roomName, String storyName) throws FileNotFoundException {
        BattleRoom battleRoom = new BattleRoom();
        boolean running = true;
        while (running) {
            Room room = new Room(story, roomName, storyName);
            Puzzle puzzle = room.getPuzzle();
            printRoom(room);
            if(room.getEnemies().size() > 0) {
                roomName = battleRoom.battle(room);
            } else if (!puzzle.getPuzzleType().equals("null")) {
                roomName = puzzleAction(puzzle, room);
            } else {
                int actionChoice = getActionChoice(room);
                roomName = room.getActionResult(actionChoice);
            }
            if(roomName.equals("EndWin") || roomName.equals("EndLose")) {
                running = false;
            }
            System.out.println();
        }
        Room endRoom = new Room(story, roomName, storyName);
        System.out.println(endRoom.getRoomText());
        exit(0);
    }

    public String puzzleAction(Puzzle puzzle, Room room) {
        int actionChoice = getActionChoice(room);
        if(actionChoice == 1) {
            return "EndLose";
        } else {
            return puzzle.getIfPassAction();
        }
    }

}
