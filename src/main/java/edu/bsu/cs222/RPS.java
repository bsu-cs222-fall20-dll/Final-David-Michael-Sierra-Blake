package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class RPS {
        Button rockButton = new Button();
        Button paperButton = new Button();
        Button scissorsButton = new Button();
        TextArea displayText = new TextArea();
        String throwType = "";
        Boolean leave = false;
        int tries = 0;


        public RPS(Stage primaryStage, String storyName, JsonObject storyObject, Puzzle puzzle) {

                createGUI(primaryStage);
                beautifyText();
                beautifyRockButton();
                beautifyPaperButton();
                beautifyScissorsButton();

                rockButton.setOnAction(actionEvent -> {
                        try {
                                if(leave) {
                                        new GUI(primaryStage, storyName, puzzle.getIfPassAction(), storyObject);
                                } else {
                                        throwType = randomizeThrow();
                                        if (throwType.equalsIgnoreCase("rock")) {
                                                tieThrow();
                                        } else if (throwType.equalsIgnoreCase("paper")) {
                                                losingThrow();
                                        } else {
                                                winningThrow();
                                        }
                                }
                        } catch (FileNotFoundException | InterruptedException e) {
                                e.printStackTrace();
                        }
                });
                paperButton.setOnAction(actionEvent -> {
                        try {
                                if(leave) {
                                        new GUI(primaryStage, storyName, puzzle.getIfFailAction(), storyObject);
                                } else {
                                        throwType = randomizeThrow();
                                        if (throwType.equalsIgnoreCase("paper")) {
                                                displayText.setText("A tie! Try again.");
                                        } else if (throwType.equalsIgnoreCase("scissors")) {
                                                losingThrow();
                                        } else {
                                                winningThrow();
                                        }
                                }
                        } catch (FileNotFoundException | InterruptedException e) {
                                e.printStackTrace();
                        }
                });
                scissorsButton.setOnAction(actionEvent -> {
                        try {
                                if(leave) {
                                        new GUI(primaryStage, storyName, puzzle.getIfPassAction(), storyObject);
                                } else {
                                        throwType = randomizeThrow();
                                        if (throwType.equalsIgnoreCase("scissors")) {
                                                displayText.setText("A tie! Try again.");
                                        } else if (throwType.equalsIgnoreCase("rock")) {
                                                losingThrow();
                                        } else {
                                                winningThrow();
                                        }
                                }
                        } catch (FileNotFoundException | InterruptedException e) {
                                e.printStackTrace();
                        }
                });
        }

        public void createGUI(Stage primaryStage) {
                VBox parent = new VBox();
                primaryStage.setHeight(500);
                primaryStage.setWidth((750));
                primaryStage.setTitle("Dungeon Game");

                parent.getChildren().add(displayText);
                parent.getChildren().add(rockButton);
                parent.getChildren().add(paperButton);
                parent.getChildren().add(scissorsButton);

                primaryStage.setScene(new Scene(parent));
                primaryStage.show();

        }

        public void beautifyText() {
                displayText.setTranslateX(187);
                displayText.setTranslateY(125);
                displayText.setMaxHeight(200);
                displayText.setMaxWidth(350);
                displayText.setWrapText(true);
                displayText.setText("Welcome to Rock Paper Scissors. Try to guess the correct throw against the computers. If you win, you get to move on.");
        }

        public void beautifyRockButton() {
                rockButton.setTranslateX(30);
                rockButton.setTranslateY(200);
                rockButton.setMaxHeight(30);
                rockButton.setText("Rock");
        }

        public void beautifyPaperButton() {
                paperButton.setTranslateX(335);
                paperButton.setTranslateY(175);
                paperButton.setMaxHeight(30);
                paperButton.setText("Paper");
        }

        public void beautifyScissorsButton() {
                scissorsButton.setTranslateX(640);
                scissorsButton.setTranslateY(150);
                scissorsButton.setMaxHeight(30);
                scissorsButton.setText("Scissors");
        }

        public String randomizeThrow() {
                int rand = (int) (Math.random() * 3);

                String gameMove = "";
                if (rand == 0) {
                        gameMove = "rock";
                }
                if (rand == 1) {
                        gameMove = "paper";
                }
                if (rand == 2) {
                        gameMove = "scissors";
                }
                return gameMove;
        }

        public void winningThrow() {
                displayText.setText("You win!");
                paperButton.setVisible(false);
                scissorsButton.setVisible(false);
                rockButton.setText("Continue to next room");
                leave = true;
        }

        public void losingThrow() {
                displayText.setText("You lost the throw!");
                tries += 1;
                if (tries >= 3) {
                        rockButton.setVisible(false);
                        scissorsButton.setVisible(false);
                        paperButton.setText("Exit");
                        displayText.setText("You lost the third throw! You lose the puzzle!");
                        leave = true;
                }
        }

        public void tieThrow() {
                displayText.setText("A tie! Try again.");
                tries += 1;
                if (tries >= 3) {
                        rockButton.setVisible(false);
                        scissorsButton.setVisible(false);
                        paperButton.setText("Exit");
                        displayText.setText("You tied the third throw! You lose the puzzle!");
                        leave = true;
                }
        }
}
