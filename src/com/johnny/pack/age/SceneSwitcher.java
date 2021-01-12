package com.johnny.pack.age;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.util.Optional;

public class SceneSwitcher extends Application {

    public static void main(String[] args){
        launch(args);
    }

    // Class level fields for Click-Counter scene
    int clickCount = 0;
    Label lblClicks;
    Button btnClickMe;
    Button btnSwitchToScene2;
    Scene scene1;

    // Class level fields for Add-Subtract scene
    int count = 0;
    Label lblCounter;
    Button btnAdd;
    Button btnSubtract;
    Button btnSwitchToScene1;
    Scene scene2;

    // Quit button
    Button btnQuit1;
    Button btnQuit2;

    // Class level field for stage
    Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        btnQuit1 = new Button("Quit");
        btnQuit1.setOnAction(e -> btnQuit_Click());

        btnQuit2 = new Button("Quit");
        btnQuit2.setOnAction(e -> btnQuit_Click());

        // Build the counter scene
        lblClicks = new Label("You have not clicked the button!");

        btnClickMe = new Button("Click Me!");
        btnClickMe.setOnAction(e -> btnClickMe_Click());

        btnSwitchToScene2 = new Button("Switch!");
        btnSwitchToScene2.setOnAction(e -> btnSwitchToScene2_Click());

        VBox pane1 = new VBox(10);
        pane1.getChildren().addAll(lblClicks, btnClickMe, btnSwitchToScene2, btnQuit1);

        scene1 = new Scene(pane1, 250, 150);

        // Build the add-subtract scene
        lblCounter = new Label(Integer.toString(count));

        btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> btnAdd_Click());

        btnSubtract = new Button("Subtract");
        btnSubtract.setOnAction(e -> btnSubtract_Click());

        btnSwitchToScene1 = new Button("Switch!");
        btnSwitchToScene1.setOnAction(e -> btnSwitchToScene1_Click());

        HBox pane2 = new HBox(10);
        pane2.getChildren().addAll(lblCounter, btnAdd, btnSubtract, btnSwitchToScene1, btnQuit2);

        scene2 = new Scene(pane2, 300, 75);

        primaryStage.setScene(scene1);
        primaryStage.setTitle("Scene Switcher");
        primaryStage.setOnCloseRequest(e ->
        {
            e.consume();
            btnQuit_Click();
        });
        primaryStage.show();
    }

    private void btnClickMe_Click() {
        clickCount++;
        if(clickCount == 1){
            lblClicks.setText("You clicked the button once!");
        } else {
            lblClicks.setText("You clicked the button " + clickCount + " times!");
        }
    }

    private void btnSwitchToScene2_Click() {
        stage.setScene(scene2);
    }

    private void btnAdd_Click() {
        count++;
        lblCounter.setText(Integer.toString(count));
    }

    private void btnSubtract_Click() {
        count--;
        lblCounter.setText(Integer.toString(count));
    }

    private void btnSwitchToScene1_Click() {
        stage.setScene(scene1);
    }

    private void btnQuit_Click() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = a.showAndWait();
        if(confirm.isPresent() && confirm.get() == ButtonType.YES){
            stage.close();
        }
    }

}
