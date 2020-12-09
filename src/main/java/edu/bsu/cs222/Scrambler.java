package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

//Suppressed because although deprecated, the JsonParser is
//the best course of action for our project's needs and still works fully
@SuppressWarnings("deprecation")

public class Scrambler {
    Button submitButton = new Button();
    Button continueButton = new Button();
    Button exitButton = new Button();
    TextArea displayText = new TextArea();
    TextArea inputText = new TextArea();
    Text wordText = new Text();
    Boolean leave = false;
    int tries = 0;
    String wordScrambled = "";
    String word = "";
    String guess = "";

    public Scrambler(Stage primaryStage, String storyName, JsonObject storyObject, Puzzle puzzle) {

        createGUI(primaryStage);
        beautifyText();
        beautifyInputText();
        beautifySubmitButton();
        beautifyContinueButton();
        beautifyExitButton();
        beautifyWordText();

        try {
            receiveWord(parseWordScrambler(), randomizeWord());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        wordText.setText(wordScrambled);


        submitButton.setOnAction(actionEvent -> {
            guess = inputText.getText();
            checkWord();
        });
        continueButton.setOnAction(actionEvent -> {
            try {
                new GUI(primaryStage, storyName, puzzle.getIfPassAction(), storyObject);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(actionEvent -> {
            try {
                new GUI(primaryStage, storyName, puzzle.getIfFailAction(), storyObject);
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

        parent.getChildren().add(displayText);
        parent.getChildren().add(inputText);
        parent.getChildren().add(submitButton);
        parent.getChildren().add(continueButton);
        parent.getChildren().add(exitButton);
        parent.getChildren().add(wordText);

        primaryStage.setScene(new Scene(parent));
        primaryStage.show();

    }

    public void beautifyText() {
        displayText.setTranslateX(187);
        displayText.setTranslateY(125);
        displayText.setMaxHeight(100);
        displayText.setMaxWidth(350);
        displayText.setWrapText(true);
        displayText.setText("Welcome to Scrambler. Unscramble the word shown to progress. Fail twice to lose.");
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

    public void beautifyWordText() {
        wordText.setTranslateX(120);
        wordText.setTranslateY(100);
        wordText.setVisible(true);
    }

    public String randomizeWord() {
        int rand = (int) (Math.random() * 10);
        return "Word" + rand;
    }

    public JsonObject parseWordScrambler() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(new FileInputStream("src/main/resources/dictionary.json"));
        JsonElement rootElement = parser.parse(reader);
        return rootElement.getAsJsonObject();
    }

    public void receiveWord(JsonObject rootObject, String dictionary) {
        JsonObject wordScrambler = rootObject.getAsJsonObject("Dictionary").getAsJsonObject(dictionary);
        if (wordScrambler == null) {
            wordScrambler = rootObject.getAsJsonObject("Dictionary").getAsJsonObject("Fallback");
        }

        wordScrambled = wordScrambler.getAsJsonPrimitive("Scrambled").toString().replace("\"", "");
        word =  wordScrambler.getAsJsonPrimitive("Unscrambled").toString().replace("\"", "");
    }

    public void checkWord() {
        if(guess.equalsIgnoreCase(word)) {
            displayText.setText("You guessed correctly!");
            exitButton.setVisible(false);
            submitButton.setVisible(false);
            continueButton.setVisible(true);
            continueButton.setText("Continue to next room");
            leave = true;
        } else if (guess.equalsIgnoreCase("")) {
            displayText.setText("You must input an answer.");
        } else {
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
}



