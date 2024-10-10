package org.example.rubikscubev09;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.rubikscubev09.data.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import  org.example.rubikscubev09.cubes.*;

public class Controller_Ui_2by2 implements Initializable {

    private IlogicalCubes controller2by2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String lastTruns ="";
    private String delimiter = "\n";
    private boolean linesVisibel = false;
    @FXML
    private Button bt_Back;
    @FXML
    private Button bt_reset;
    @FXML
    private Button bt_linesOn_Off;

    //Cube settings
    @FXML
    private Button bt_StartScrambel;
    @FXML
    private Button bt_StartAutoSolve;

    //Buttons for turns
    @FXML
    private Button bt_TurnU;
    @FXML
    private Button bt_TurnD;
    @FXML
    private Button bt_TurnR;
    @FXML
    private Button bt_TurnL;
    @FXML
    private Button bt_TurnF;
    @FXML
    private Button bt_TurnB;
    @FXML
    private Button bt_TurnUi;
    @FXML
    private Button bt_TurnDi;
    @FXML
    private Button bt_TurnRi;
    @FXML
    private Button bt_TurnLi;
    @FXML
    private Button bt_TurnFi;
    @FXML
    private Button bt_TurnBi;



    @FXML
    private Circle epse_U;
    @FXML
    private Circle epse_D;
    @FXML
    private Ellipse epse_R;
    @FXML
    private Ellipse epse_L;
    @FXML
    private Ellipse epse_F;
    @FXML
    private Ellipse epse_B;



    @FXML
    private Text txt_Standart0;
    @FXML
    private Text txt_Standart1;
    @FXML
    private Text txt_Standart2;
    @FXML
    private Text txt_Standart3;
    @FXML
    private Text txt_Standart4;
    @FXML
    private Text txt_Standart5;
    @FXML
    private Text txt_Standart6;
    @FXML
    private Text txt_Standart7;

    @FXML
    private Text txt_Cubesolved;

    @FXML
    private TextArea txtA_lastTurns;

    //Option Methodes
    public void pressed_btBack(ActionEvent event) throws IOException {
        System.out.println("Controller_Ui_2by2.pressed_btBack");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cube schliesen");
        alert.setContentText("Sicher das du zurück zum Start willst?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void pressed_bt_Reset(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_Reset");
        controller2by2 = new Controller_Cube_2by2();
        controller2by2.start();
        lastTruns = "";
        updateTextFields();
    }

    public void pressed_bt_linesOn_Off(ActionEvent event){
        linesVisibel = !linesVisibel;
        setLinesVisibel();
    }

    //Cube Settings buttons
    public void pressed_btStartScrambel(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btStartScrambel");
        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            int randomNr = random.nextInt(6);
            controller2by2.doStep(randomNr+1);
            System.out.println("random Number: " +randomNr);
            lastTruns += randomNr + "\n";
        }
        updateTextFields();
    }
    public void pressed_btStartAutoSolve(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btStartAutoSolve");
        Random r = new Random();
        int counter = 0;
        while(!controller2by2.checkCubeSolved()&& (counter >=0)){
            int i = r.nextInt(6);
            controller2by2.doStep(i+1);
            lastTruns += i+1 + delimiter;
            counter++;
        }
        updateTextFields();
        Alert solved = new Alert(Alert.AlertType.INFORMATION);
        if(controller2by2.checkCubeSolved()){
            solved.setTitle("Cube solved");
            solved.setHeaderText("Solved after " + counter);
        }else{
            solved.setTitle("Cube not Solved");
            solved.setHeaderText("try Agian");
        }
        solved.showAndWait();

    }


    //Turn methods
    public void pressed_btTurnU(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btTurnU");
        String value = "U";
        doStep(value);
    }
    public void pressed_btTurnD(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btTurnD");
        String value = "D";
        doStep(value);
    }
    public void pressed_btTurnR(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btTurnR");
        String value = "R";
        doStep(value);
    }
    public void pressed_btTurnL(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btTurnL");
        String value = "L";
        doStep(value);
    }
    public void pressed_btTurnF(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_btTurnF");
        String value = "F";
        doStep(value);
    }
    public void pressed_btTurnB(ActionEvent event) {
        System.out.println("Controller_Ui_2by2.pressed_btTurnB");
        String value = "B";
        doStep(value);
    }
    public void pressed_bt_TurnUi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnUi");
        doInvertStep("U'", "U");
    }
    public void pressed_bt_TurnDi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnDi");
        doInvertStep("D'", "D");
    }
    public void pressed_bt_TurnRi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnRi");
        doInvertStep("R'", "R");
    }
    public void pressed_bt_TurnLi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnLi");
        doInvertStep("L'", "L");
    }
    public void pressed_bt_TurnFi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnFi");
        doInvertStep("F'", "F");
    }
    public void pressed_bt_TurnBi(ActionEvent event){
        System.out.println("Controller_Ui_2by2.pressed_bt_TurnBi");
        doInvertStep("B'", "B");
    }




    private void doStep(String value){
        System.out.println("Controller_Ui_2by2.doStep");
        controller2by2.doStep(value);
        lastTruns += value + delimiter;
        updateTextFields();
    }
    private void doInvertStep(String value, String step){
        System.out.println("Controller_Ui_2by2.doInvertStep");
        for(int i = 0; i<3; i++){
            controller2by2.doStep(step);
        }
        lastTruns += value + delimiter;
        updateTextFields();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller_Ui_2by2.initialize");
        controller2by2 =  new Controller_Cube_2by2();
        controller2by2.start();
        setLinesVisibel();
        updateTextFields();
    }
    private void setLinesVisibel(){
        epse_U.setVisible(linesVisibel);
        epse_B.setVisible(linesVisibel);
        epse_D.setVisible(linesVisibel);
        epse_F.setVisible(linesVisibel);
        epse_R.setVisible(linesVisibel);
        epse_L.setVisible(linesVisibel);
    }
    private void updateTextFields(){
        System.out.println("Controller_Ui_2by2.updateTextFields");
        Point[] points = controller2by2.getPoints();
        System.out.println("length: " + points.length);
        txt_Standart0.setText(points[0].getName());
        txt_Standart0.setFill(points[0].getColor());

        txt_Standart1.setText(points[1].getName());
        txt_Standart1.setFill(points[1].getColor());

        txt_Standart2.setText(points[2].getName());
        txt_Standart2.setFill(points[2].getColor());

        txt_Standart3.setText(points[3].getName());
        txt_Standart3.setFill(points[3].getColor());

        txt_Standart4.setText(points[4].getName());
        txt_Standart4.setFill(points[4].getColor());

        txt_Standart5.setText(points[5].getName());
        txt_Standart5.setFill(points[5].getColor());

        txt_Standart6.setText(points[6].getName());
        txt_Standart6.setFill(points[6].getColor());

        txt_Standart7.setText(points[7].getName());
        txt_Standart7.setFill(points[7].getColor());

        txtA_lastTurns.setText(lastTruns);
        if(controller2by2.checkCubeSolved()){
            txt_Cubesolved.setText("Solved");
            txt_Cubesolved.setFill(Color.GREEN);
            txt_Cubesolved.setVisible(true);
        }else{
            txt_Cubesolved.setText("Not Solved");
            txt_Cubesolved.setVisible(false);
        }


    }
    private void sleep(long milis)  {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
