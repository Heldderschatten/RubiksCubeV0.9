package org.example.rubikscubev09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.rubikscubev09.Files.FilesReader;
import org.example.rubikscubev09.cubes.Controller_Cube_2by2;
import org.example.rubikscubev09.data.Graph;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("HelloApplication.start");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cube Simulator!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("HelloApplication.main");
        /*Controller_Cube_2by2 c = new Controller_Cube_2by2();
        c.start();
        //System.out.println(c.toString());
        FilesReader fr = new FilesReader();
        String filename = "2by2File.json";
        fr.makeSafe(c.getG(),filename);


        Graph g  = fr.readFiels( System.getProperty("user.dir")+ System.getProperty("file.separator") + filename);

        System.out.println(g.toString());
        if(g.toString().equals(c.getG().toString())){
            System.out.println("test Passed");
        }else{
            System.out.println("Test failed");
        }*/
        /*System.out.println("user.dir: " + System.getProperty("user.dir"));
        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("java.io.temdir" + System.getProperty("java.io.tempdir"));*/
        launch();
    }

}