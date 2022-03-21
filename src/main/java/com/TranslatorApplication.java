package com;

import com.translatorapp.PreProcessor;
import com.translatorapp.Reader;
import com.translatorapp.TableEditor;
import com.translatorapp.TranslationSegment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class TranslatorApplication extends Application {
    private Reader textReader = new Reader();
    private PreProcessor textPreProcessor;
    private TableEditor table = new TableEditor();
    private TableView tableEditor = this.table.getEditorView();

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
        importButton.setOnAction(e -> this.importFile(importText));

        this.displayTable(layout,0, 3);
//        this.tableEditor.getItems().add(new TranslationSegment(1, "This is a string."));

        Button displaySourceTextText = new Button("Parse file");
        GridPane.setConstraints(displaySourceTextText, 1, 1);
        displaySourceTextText.setOnAction(e -> this.parseFile());
//        displaySourceTextText.setOnAction(e -> this.displaySourceText(layout,0, 2));

//
//        for (int i=0; i < textReader.textLines.size(); i++) {
//            System.out.println(textReader.textLines.get(i));
//        }
//        System.out.println("This line executed.");

//        Text text1 = new Text();
//        text1.setText("Joe Momma");
//        GridPane.setConstraints(text1, 0, 4);

        Button printTextToConsole = new Button("Print text");
        GridPane.setConstraints(printTextToConsole, 1, 2);
        printTextToConsole.setOnAction(e -> {this.printTable();});

        layout.getChildren().addAll(importButton, importText, displaySourceTextText, printTextToConsole);

        Scene scene = new Scene(layout, 900, 500);
        stage.setTitle("Translator App");
        stage.setScene(scene);
        stage.show();
    }

    private void importFile(TextField importText) {
        this.textReader.process(importText.getText());
        this.textPreProcessor = new PreProcessor(this.textReader);
        System.out.println("Imported Successfully");
    }


    private void displayTable(GridPane layout, int x, int y) {
        GridPane.setConstraints(this.tableEditor, x, y);
        layout.getChildren().addAll(this.tableEditor);
    }

    private void parseFile() {

        this.textPreProcessor.parseReader();

        Map<Integer, TranslationSegment> parsedLines = this.textPreProcessor.getParsedLines();
        for (int i=0; i < parsedLines.size(); i++) {
            TranslationSegment segment = parsedLines.get(i);
            this.table.addSegment(segment);
        }

        parsedLines.entrySet().forEach(entry -> {
            System.out.println(entry.getValue());
        });
    }

    private void displaySourceText(GridPane layout, int x, int y) {
        Label sourceLabel = new Label();
        sourceLabel.setText("Source Text");
        GridPane.setConstraints(sourceLabel, x, y);
        layout.getChildren().add(sourceLabel);

        Map<Integer, TranslationSegment> parsedLines = textPreProcessor.getParsedLines();


        for (int i=0; i < parsedLines.size(); i++) {

            Text sourceLine = new Text();
            sourceLine.setText(i + parsedLines.get(i).getSourceText());
            GridPane.setConstraints(sourceLine, x, y+1);
            layout.getChildren().addAll(sourceLine);
            y++;
        }
    }

    private void printTable() {
        if (this.textPreProcessor != null) {
            this.textPreProcessor.getParsedLines().entrySet().forEach(entry ->
                    System.out.println(entry.getValue().toString()));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}