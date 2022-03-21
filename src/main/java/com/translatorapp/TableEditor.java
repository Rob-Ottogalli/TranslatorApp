package com.translatorapp;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;


public class TableEditor {

    private TableView editorView = new TableView();


    public TableEditor() {
        editorView.setEditable(true);
        initialize();
    }

    private void initialize() {
        // Add columns
        TableColumn<TranslationSegment, Integer> segmentIDColumn = new TableColumn<>("ID");
        segmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("segmentID"));
        segmentIDColumn.setPrefWidth(50);

        TableColumn<TranslationSegment, String> sourceColumn = new TableColumn<>("Source");
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("sourceText"));
        sourceColumn.setPrefWidth(250);

        TableColumn<TranslationSegment, String> targetColumn = new TableColumn<>("Target");
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("targetText"));
        targetColumn.setCellFactory(TextFieldTableCell.<TranslationSegment>forTableColumn());
        targetColumn.setPrefWidth(250);
        targetColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<TranslationSegment, String>>() {
                @Override
                public void handle(CellEditEvent<TranslationSegment, String> targetSegmentText) {
                    ((TranslationSegment) targetSegmentText.getTableView().getItems().get(
                            targetSegmentText.getTablePosition().getRow())
                            ).setTargetText(targetSegmentText.getNewValue());
                }
            }
        );


        TableColumn<TranslationSegment, String> filenameColumn = new TableColumn<>("File Name");
        filenameColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));
        filenameColumn.setPrefWidth(100);

        // Attach columns to editor and set editor dimensions
        this.editorView.getColumns().addAll(segmentIDColumn, sourceColumn, targetColumn, filenameColumn);
        editorView.setMinWidth(650);
        editorView.setPlaceholder(new Label("No segments to display"));

        // Set minimum height of each row
        editorView.setRowFactory(param -> {
            return new TableRow() {
                @Override
                public void updateIndex(int i) {
                    super.updateIndex(i);
                    setMinHeight(50);
                }
            };
        });
    }

    public TableView getEditorView() {
        return editorView;
    }

    public void addSegment(TranslationSegment segment) {
        this.editorView.getItems().add(segment);
    }


}
