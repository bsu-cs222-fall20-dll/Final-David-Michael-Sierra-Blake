package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Terminal {

    public void printMainMenu(){
        System.out.println("Game Options");
        System.out.println("------------");
        System.out.println("1) Water Story");
        System.out.println("2) Test Story");
        System.out.println("3) Quit");
    }

    public ArrayList<String> selectOption(String choice) {
        ArrayList<String> choiceList = new ArrayList<>();
        if (choice.equals("1")) {
            choiceList.add("WaterStory");
            choiceList.add("water-story");
        } else if (choice.equals("2")) {
            choiceList.add("TestStory");
            choiceList.add("test-story");
        } else if (choice.equals("3")) {
            exit(0);
        } else {
            System.out.println("Enter Valid Input");
            printMainMenu();
        }
        return choiceList;
    }

    public void printRoom(Room room){
        System.out.println(room.getRoomText());
        for (String action: room.getActions()) {
            System.out.println(action);
        }
    }

    public int getActionChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What choice do you choose?");
        int choice = Integer.parseInt(scanner.nextLine());
        choice = choice - 1;
        return choice;
    }
}
