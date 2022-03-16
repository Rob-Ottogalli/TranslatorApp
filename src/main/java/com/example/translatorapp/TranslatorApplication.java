package com.example.translatorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import datamanagement.Reader;
import java.io.IOException;

public class TranslatorApplication extends Application {
    private Reader textReader = new Reader();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TranslatorApplication.class.getResource("hello-view.fxml"));
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        TextField importText = new TextField();
        importText.setPromptText("Enter your file path and name.");
        importText.setPrefColumnCount(20);
        GridPane.setConstraints(importText, 0, 0);

//        importText.getText();

        Button importButton = new Button("Import file");
        GridPane.setConstraints(importButton, 1, 0);
        importButton.setOnAction(e -> textReader.process(importText.getText()));


        Button displaySourceTextText = new Button("Display source text");
        GridPane.setConstraints(displaySourceTextText, 1, 1);
        displaySourceTextText.setOnAction(e -> this.displaySourceText(2, 0));
//
//        for (int i=0; i < textReader.textLines.size(); i++) {
//            System.out.println(textReader.textLines.get(i));
//        }
//        System.out.println("This line executed.");

        Text text1 = new Text();
        text1.setText("Joe Momma");
        GridPane.setConstraints(text1, 4, 4);

        layout.getChildren().addAll(importButton, importText, displaySourceTextText, text1);

        Scene scene = new Scene(layout, 500, 300);
        stage.setTitle("Translator App");
        stage.setScene(scene);
        stage.show();
    }

    private void displaySourceText(int x, int y) {
        for (int i=0; i < textReader.textLines.size(); i++) {
            Text sourceLine = new Text();
            sourceLine.setText(textReader.textLines.get(i));
            GridPane.setConstraints(sourceLine, x, y);
            x++;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}