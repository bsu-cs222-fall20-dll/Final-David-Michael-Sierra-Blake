package edu.bsu.cs222;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainMenu(primaryStage);
    }
}