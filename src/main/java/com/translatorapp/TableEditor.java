package com.translatorapp;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class TableEditor {

    private TableView editorView = new TableView();


    public TableEditor() {
        editorView.setEditable(true);
        initialize();
    }

    private void initialize() {
        TableColumn<TranslationSegment, Integer> segmentIDColumn = new TableColumn<>("ID");
        segmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("segmentID"));

        TableColumn<TranslationSegment, String> sourceSegment = new TableColumn<>("Source");
        sourceSegment.setCellValueFactory(new PropertyValueFactory<>("sourceText"));

        TableColumn<TranslationSegment, String> targetSegment = new TableColumn<>("Target");
        targetSegment.setCellValueFactory(new PropertyValueFactory<>("targetText"));
        targetSegment.setCellFactory(TextFieldTableCell.<TranslationSegment>forTableColumn());

        this.editorView.getColumns().addAll(segmentIDColumn, sourceSegment, targetSegment);
        editorView.setPlaceholder(new Label("No segments to display"));
    }

    public TableView getEditorView() {
        return editorView;
    }

    public void addSegment(TranslationSegment segment) {
        this.editorView.getItems().add(new TranslationSegment(segment.getSegmentID(), segment.getSourceText()));
    }

}
