package org.example.rubikscubev09;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.rubikscubev09.Files.CubeToJsonConverter;
import org.example.rubikscubev09.Files.FilesReader;
import org.example.rubikscubev09.Files.GraphToJsonConverter;
import org.example.rubikscubev09.cubes.Controller_Cube_2by2;

import org.example.rubikscubev09.data.Cube;
import org.example.rubikscubev09.data.Graph;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Cube_UI_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser fileChooser;

    @FXML
    private GridPane cubePane;
    @FXML
    private Button btBack;
    @FXML
    private Button btSelctCube;
    @FXML
    private Button btTest;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Group group;

    private int h = 1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Cube_UI_Controller.initialize");

    }
    public void setGridSize(int n) {
        // Entferne alle existierenden Elemente aus der GridPane
        cubePane.getChildren().clear();

        // Entferne alte Spalten- und Zeilenbeschränkungen
        cubePane.getColumnConstraints().clear();
        cubePane.getRowConstraints().clear();

        // Definiere die Größe der Spalten und Zeilen
        for (int i = 0; i < n; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / n); // Gleichmäßige Verteilung der Spalten
            cubePane.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / n); // Gleichmäßige Verteilung der Zeilen
            cubePane.getRowConstraints().add(row);
        }

        // Füge dynamisch Labels zu den Zellen hinzu
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                Label label = new Label("(" + row + "," + col + ")");
                cubePane.add(label, col, row);  // Füge das Label an der Position (col, row) hinzu
            }
        }
    }
    public void pressedBtTest(ActionEvent event) {
        System.out.println("Cube_UI_Controller.pressedBtTest");
        setGridSize(5);
        System.out.println("-----StartTest-----");

        Controller_Cube_2by2 controllerCube2by2 = new Controller_Cube_2by2();
        controllerCube2by2.start();

        Cube cubeController = new Cube(controllerCube2by2.getG(),8,"2mal2");
        String filepath = System.getProperty("user.dir") +"\\" + cubeController.getName()+".json";
        cubeController.setPossibleMovesIntger(controllerCube2by2.getPossibleMovesIntger());
        cubeController.setPossibleMovesStringToInt(controllerCube2by2.getPossibleMovesStringToInt());


        CubeToJsonConverter.writeCubeToJson(cubeController,filepath);
        Cube cube = CubeToJsonConverter.readGCubeFromJson(filepath);


        System.out.println("-----EndTest-----");
    }

    public void pressed_btBack(ActionEvent event) throws IOException {
        System.out.println("Cube_UI_Controller.pressed_btBack");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cube schliesen");
        alert.setContentText("Sicher das du zurück zum Start willst?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void presed_SelectCube(ActionEvent event) {
        System.out.println("Cube_UI_Controller.presed_SelectCube");
        fileChooser = new FileChooser();
        fileChooser.setTitle("SelectCube");
        File cubeFile = fileChooser.showOpenDialog(stage);
    }

    private void setCubePane(Graph graph) {

    }
}
