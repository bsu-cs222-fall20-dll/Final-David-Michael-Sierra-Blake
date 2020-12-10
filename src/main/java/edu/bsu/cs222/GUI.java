package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class GUI {
    Room room;
    String roomName;
    String storyName;
    ArrayList<String> actionResults = new ArrayList<>();
    int choice;
    Boolean puzzleRoom = false;
    Puzzle puzzle;
    Player player;


    Button actionButton1 = new Button();
    Button actionButton2 = new Button();
    Button actionButton3 = new Button();
    Button exitButton = new Button();
    Button statsButton = new Button();
    TextArea roomText = new TextArea();


    RoomBuilder roomBuilder = new RoomBuilder();
    BattleRoom battleRoom = new BattleRoom();


    public GUI(Stage primaryStage, String storyName, String roomName, JsonObject storyObject, Player player) throws FileNotFoundException {
        this.roomName = roomName;
        this.storyName = storyName;
        this.player = player;

        createGame(primaryStage);
        beautifyText();
        beautifyActionButton1();
        beautifyActionButton2();
        beautifyActionButton3();
        beautifyExitButton();
        beautifyStatsButton();
        update(storyObject, roomName);

        actionButton1.setOnAction(actionEvent -> {
            try {
                if(puzzleRoom) {
                    if(puzzle.getPuzzleType().equalsIgnoreCase("calculator")) {
                        new Calculator(primaryStage, storyName, storyObject, puzzle, this.player);
                    } else if (puzzle.getPuzzleType().equalsIgnoreCase("rps")) {
                        new RPS(primaryStage, storyName, storyObject, puzzle, this.player);
                    } else {
                        new Scrambler(primaryStage, storyName, storyObject, puzzle, this.player);
                    }
                } else {
                    setActionChoice(0);
                    update(storyObject, actionResults.get(0));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        actionButton2.setOnAction(actionEvent -> {
            try {
                if(puzzleRoom) {
                    this.player.setHealthAfterDamage(5);
                    if (this.player.getHealth() <= 0) {
                        setActionChoice(1);
                        update(storyObject, actionResults.get(1));
                    } else {
                        setActionChoice(0);
                        update(storyObject, actionResults.get(0));
                    }
                } else {
                    setActionChoice(1);
                    update(storyObject, actionResults.get(1));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        actionButton3.setOnAction(actionEvent -> {
            try {
                setActionChoice(2);
                update(storyObject, actionResults.get(2));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> {
            try {
                new MainMenu(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        statsButton.setOnAction(actionEvent -> new PlayerGUI(primaryStage, storyName, this.roomName, storyObject, player));
    }

    public void createGame(Stage primaryStage) {
        VBox parent = new VBox();
        primaryStage.setHeight(500);
        primaryStage.setWidth((750));
        primaryStage.setTitle("Dungeon Game");
        BackgroundImage myBI= null;
        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("src/main/resources/ice-cave-background.png"),1188,681,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parent.setBackground(new Background(myBI));

        parent.getChildren().add(roomText);
        parent.getChildren().add(actionButton1);
        parent.getChildren().add(actionButton2);
        parent.getChildren().add(actionButton3);
        parent.getChildren().add(exitButton);
        parent.getChildren().add(statsButton);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public void beautifyText() {
        roomText.setTranslateX(187);
        roomText.setTranslateY(125);
        roomText.setMaxHeight(200);
        roomText.setMaxWidth(350);
        roomText.setWrapText(true);
    }

    public void beautifyActionButton1() {
        actionButton1.setTranslateX(30);
        actionButton1.setTranslateY(200);
        actionButton1.setMaxHeight(30);
        actionButton1.centerShapeProperty();
    }

    public void beautifyActionButton2() {
        actionButton2.setTranslateX(250);
        actionButton2.setTranslateY(175);
        actionButton2.setMaxHeight(30);
    }

    public void beautifyActionButton3() {
        actionButton3.setTranslateX(475);
        actionButton3.setTranslateY(150);
        actionButton3.setMaxHeight(30);
    }

    public void beautifyExitButton() {
        exitButton.setTranslateX(350);
        exitButton.setTranslateY(125);
        exitButton.setMaxHeight(30);
        exitButton.setMinWidth(50);
    }

    public void beautifyStatsButton() {
        statsButton.setTranslateX(550);
        statsButton.setTranslateY(125);
        statsButton.setMaxHeight(30);
        statsButton.setMinWidth(50);
        statsButton.setText("Stats Menu");
    }

    public void update(JsonObject storyObject, String roomName) throws FileNotFoundException {
        this.roomName = roomName;
        this.room = roomBuilder.nextRoom(storyObject, roomName, storyName);
        updateInitial();

        if (room.getExitRoom()) {
            updateExitRoom();
        } else if(room.getEnemies().size() > 0) {
            actionResults.add(battleRoom.battle(room, actionButton1, roomText, player));
        } else if (!puzzle.getPuzzleType().equals("null")) {
            updatePuzzleRoom();
        } else {
            updateNormalRoom();
        }
    }

    public void updateInitial() {
        roomText.setText(room.getRoomText());
        actionResults.clear();

        exitButton.setText("Exit");
        exitButton.setVisible(false);
        actionButton2.setText("");
        actionButton3.setText("");
        actionButton2.setVisible(false);
        actionButton3.setVisible(false);
        statsButton.setVisible(false);

        puzzle = room.getPuzzle();
    }

    public void updateExitRoom() {
        exitButton.setVisible(true);
        actionButton1.setVisible(false);
        actionButton2.setVisible(false);
        actionButton3.setVisible(false);
    }

    public void updatePuzzleRoom() {
        actionButton1.setText(room.getAction(0));
        actionResults.add(puzzle.getIfPassAction());
        actionResults.add(puzzle.getIfFailAction());
        statsButton.setVisible(true);
        puzzleRoom = true;
        if (!puzzle.getIfPassAction().equalsIgnoreCase("LastRoom")) {
            actionButton2.setText(room.getAction(1));
            actionButton2.setVisible(true);
        }
    }

    public void updateNormalRoom() {
        actionButton1.setText(room.getAction(0));
        actionResults.add(room.getActionResult(0));
        statsButton.setVisible(true);
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

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }


    public void setActionChoice(int actionChoice) {
        choice = actionChoice;
    }



}
