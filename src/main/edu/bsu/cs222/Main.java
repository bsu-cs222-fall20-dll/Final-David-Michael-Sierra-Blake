package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        String storyName;
        String storyJson;
        StoryReader storyReader = new StoryReader();
        RoomBuilder roomBuilder = new RoomBuilder();
        Scanner scanner = new Scanner(System.in);
        printMainMenu();
        String choice = scanner.nextLine();
        selectOption(choice);
        JsonObject story = storyReader.parse(new FileInputStream("src/test/resources/test-story.json"));
        storyName = "TestStory";
        String roomName = "StartRoom";
        while (true) {
            Room room = new Room(story, roomName, storyName);
            System.out.println(printRoom(room));
            int actionChoice = getActionChoice();
            roomName = room.getActionResult(actionChoice);
        }
    }
    public static void printMainMenu(){
        System.out.println("Game Options");
        System.out.println("------------");
        System.out.println("1) Water Story");
        System.out.println("2) Quit");
    }
    public String getStoryName() {
        return storyName;
    }

    public String getStoryJson() {
        return storyJson;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public void setStoryJson(String storyJson) {
        this.storyJson = storyJson;
    }

    public void selectOption(String choice) {
        if (choice.equals("1")) {
            setStoryName("WaterStory");
            setStoryJson("storyJson");
        } else if (choice.equals("2")) {
            break;
        } else {
            System.out.println("Enter Valid Input");
            printMainMenu();
        }
        return true;
    }
    public static void printRoom(Room room){
        System.out.println(room.getRoomText());
        System.out.println(room.getAction(0));
        System.out.println(room.getAction(1));
        System.out.println(room.getAction(2));

    }
    public static int getActionChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What choice do you choose?");
        int choice = Integer.parseInt(scanner.nextLine());
        choice = choice - 1;
        return choice;
    }
}
