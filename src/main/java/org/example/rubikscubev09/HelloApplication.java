package org.example.rubikscubev09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.rubikscubev09.Files.FilesReader;
import org.example.rubikscubev09.cubes.Controller_Cube_2by2;
import org.example.rubikscubev09.data.Graph;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("HelloApplication.start");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cube Simulator");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("HelloApplication.main");
        launch();
    }

}