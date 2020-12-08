package edu.bsu.cs222;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainMenu mainMenu = new MainMenu(primaryStage);

    }
}