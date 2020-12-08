package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static java.lang.System.exit;


public class GUI {
    Room room;
    String roomName;
    String storyName;
    ArrayList<String> actionResults = new ArrayList<>();
    int choice;


    Button actionButton1 = new Button();
    Button actionButton2 = new Button();
    Button actionButton3 = new Button();
    Button exitButton = new Button();
    TextArea roomText = new TextArea();


    RoomBuilder roomBuilder = new RoomBuilder();
    BattleRoom battleRoom = new BattleRoom();


    public GUI(Stage primaryStage, String storyName, String roomName, JsonObject storyObject) throws FileNotFoundException, InterruptedException {
        this.roomName = roomName;
        this.storyName = storyName;

        createGame(primaryStage);
        update(storyObject, roomName);

        actionButton1.setOnAction(actionEvent -> {
            try {
                setActionChoice(0);
                update(storyObject, actionResults.get(0));
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        actionButton2.setOnAction(actionEvent -> {
            try {
                setActionChoice(1);
                update(storyObject, actionResults.get(1));
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        actionButton3.setOnAction(actionEvent -> {
            try {
                setActionChoice(2);
                update(storyObject, actionResults.get(2));
            } catch (FileNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> {
            exit(0);
        });
    }

    public void createGame(Stage primaryStage) {
        VBox parent = new VBox();
        primaryStage.setHeight(500);
        primaryStage.setWidth((750));
        primaryStage.setTitle("Dungeon Game");

        parent.getChildren().add(actionButton1);
        parent.getChildren().add(actionButton2);
        parent.getChildren().add(actionButton3);
        parent.getChildren().add(exitButton);
        parent.getChildren().add(roomText);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public void update(JsonObject storyObject, String roomName) throws FileNotFoundException, InterruptedException {
        //Removing this InterruptedException throw causes errors above, but keeping it causes a warning here. Therefore, keeping it here.
        this.room = roomBuilder.nextRoom(storyObject, roomName, storyName);
        roomText.setText(room.getRoomText());
        actionResults.clear();

        exitButton.setText("Exit");
        exitButton.setVisible(false);
        actionButton2.setText("");
        actionButton3.setText("");
        actionButton2.setVisible(false);
        actionButton3.setVisible(false);

        Puzzle puzzle = room.getPuzzle();

        if (room.getExitRoom()) {
            System.out.println("working!");
            exitButton.setVisible(true);
            actionButton1.setVisible(false);
            actionButton2.setVisible(false);
            actionButton3.setVisible(false);
        } else if(room.getEnemies().size() > 0) {
            actionResults.add(battleRoom.battle(room, actionButton1, roomText));
        } else if (!puzzle.getPuzzleType().equals("null")) {
            actionButton2.setText(room.getAction(1));
            actionButton1.setText(room.getAction(0));
            actionButton2.setVisible(true);
            actionResults.add(puzzleAction(puzzle));
        } else {
            actionButton1.setText(room.getAction(0));
            actionResults.add(room.getActionResult(0));
            if (room.getActions().size() > 1) {
                actionButton2.setText(room.getAction(1));
                actionButton2.setVisible(true);
                actionResults.add(room.getActionResult(1));
                if (room.getActions().size() > 2) {
                    actionButton3.setText(room.getAction(2));
                    actionButton3.setVisible(true);
                    actionResults.add(room.getActionResult(2));
                }
            }
        }
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public String puzzleAction(Puzzle puzzle) {
        int actionChoice = choice;
        if(actionChoice == 1) {
            return "EndLose";
        } else {
            return puzzle.getIfPassAction();
        }
    }

    public void setActionChoice(int actionChoice) {
        choice = actionChoice;
    }



}
