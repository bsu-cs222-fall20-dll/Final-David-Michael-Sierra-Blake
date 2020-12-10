package edu.bsu.cs222;

import com.google.gson.JsonObject;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Calculator {

    Button submitButton = new Button();
    Button continueButton = new Button();
    Button exitButton = new Button();
    TextArea displayText = new TextArea();
    TextArea inputText = new TextArea();
    Text problemText = new Text();
    Boolean leave = false;
    int tries = 0;
    String problem = "";
    int problemAnswer = 0;
    String guess = "";
    int rand = 0;
    int firstNumber = 0;
    int secondNumber = 0;
    String symbol = "";

    public Calculator(Stage primaryStage, String storyName, JsonObject storyObject, Puzzle puzzle, Player player) {

        createGUI(primaryStage);
        beautifyText();
        beautifyInputText();
        beautifySubmitButton();
        beautifyContinueButton();
        beautifyExitButton();
        beautifyProblemText();

        randomizeNumbers();
        randomSymbol();

        problemText.setText(problem);


        submitButton.setOnAction(actionEvent -> {
            guess = inputText.getText();
            checkAnswer();
        });
        continueButton.setOnAction(actionEvent -> {
            try {
                new GUI(primaryStage, storyName, puzzle.getIfPassAction(), storyObject, player);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> {
            try {
                new GUI(primaryStage, storyName, puzzle.getIfFailAction(), storyObject, player);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void createGUI(Stage primaryStage) {
        VBox parent = new VBox();
        primaryStage.setHeight(500);
        primaryStage.setWidth((750));
        primaryStage.setTitle("Dungeon Game");

        BackgroundImage myBI= null;
        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("src/main/resources/tile-floor.jpg"),1024,1024,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parent.setBackground(new Background(myBI));

        parent.getChildren().add(displayText);
        parent.getChildren().add(inputText);
        parent.getChildren().add(submitButton);
        parent.getChildren().add(continueButton);
        parent.getChildren().add(exitButton);
        parent.getChildren().add(problemText);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    public void beautifyText() {
        displayText.setTranslateX(187);
        displayText.setTranslateY(125);
        displayText.setMaxHeight(100);
        displayText.setMaxWidth(350);
        displayText.setWrapText(true);
        displayText.setText("Welcome to Calculator. Find the correct answer to the math problem shown. If you fail twice, you lose. Be sure to round down, when necessary.");
    }

    public void beautifyInputText() {
        inputText.setTranslateX(100);
        inputText.setTranslateY(235);
        inputText.setMaxHeight(30);
        inputText.setMaxWidth(150);
        inputText.setWrapText(true);
        inputText.setText("");
    }

    public void beautifySubmitButton() {
        submitButton.setTranslateX(30);
        submitButton.setTranslateY(200);
        submitButton.setMaxHeight(30);
        submitButton.setText("Submit");
        exitButton.setVisible(true);
    }

    public void beautifyContinueButton() {
        continueButton.setTranslateX(335);
        continueButton.setTranslateY(175);
        continueButton.setMaxHeight(30);
        continueButton.setText("Continue to next room");
        continueButton.setVisible(false);
    }

    public void beautifyExitButton() {
        exitButton.setTranslateX(640);
        exitButton.setTranslateY(150);
        exitButton.setMaxHeight(30);
        exitButton.setText("Exit");
        exitButton.setVisible(false);
    }

    public void beautifyProblemText() {
        problemText.setTranslateX(135);
        problemText.setTranslateY(102);
        problemText.setScaleX(2);
        problemText.setScaleY(2);
        problemText.setVisible(true);
    }

    public void randomizeNumbers() {
        firstNumber = (int) (Math.random() * 50);
        secondNumber = (int) (Math.random() * 10 + 1);
    }

    public void randomSymbol() {
        rand = (int) (Math.random() * 4);
        symbol = "";
        switch (rand) {
            case 1:
                symbol = "*";
                problem = firstNumber + " " + symbol + " " + secondNumber + " =";
                problemAnswer = firstNumber*secondNumber;
                break;
            case 2:
                symbol = "/";
                problem = firstNumber + " " + symbol + " " + secondNumber + " =";
                problemAnswer = firstNumber/secondNumber;
                break;
            case 4:
                symbol = "-";
                problem = firstNumber + " " + symbol + " " + secondNumber + " =";
                problemAnswer = firstNumber-secondNumber;
                break;
            default:
                symbol = "+";
                problem = firstNumber + " " + symbol + " " + secondNumber + " =";
                problemAnswer = firstNumber+secondNumber;
        }
    }


    public void checkAnswer() {
        if (guess.equalsIgnoreCase("")) {
            displayText.setText("You must input an answer.");
        } else if(Integer.parseInt(guess) == problemAnswer) {
            correctGuess();
        } else {
            incorrectGuess();
        }

    }

    public void correctGuess() {
        displayText.setText("You guessed correctly!");
        exitButton.setVisible(false);
        submitButton.setVisible(false);
        continueButton.setVisible(true);
        continueButton.setText("Continue to next room");
        leave = true;
    }

    public void incorrectGuess() {
        tries += 1;
        displayText.setText("You guess incorrectly! One guess remaining!");
        if (tries >= 2) {
            exitButton.setVisible(true);
            submitButton.setVisible(false);
            continueButton.setVisible(false);
            displayText.setText("You guessed incorrectly! You lose!");
            leave = true;
        }
    }
}
