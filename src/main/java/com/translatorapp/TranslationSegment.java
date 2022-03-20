package com.translatorapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TranslationSegment {

    private int segmentID = 0;
    private final StringProperty sourceText = new SimpleStringProperty();
    private String targetText = null;

    public TranslationSegment() {

    }

    public TranslationSegment(String source) {
        this.setSourceText(source);
    }

    public TranslationSegment(int id, String source) {
        this.setSegmentID(id);
        this.setSourceText(source);
    }

    public int getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(int segmentID) {
        this.segmentID = segmentID;
    }

    public final StringProperty sourceTextProperty() {
        return sourceText;
    }

    public String getSourceText() {
        return sourceText.get();
    }

    public void setSourceText(String sourceText) {
        this.sourceText.set(sourceText);
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String toString() {
        return this.getSegmentID() + " " + this.getSourceText();
    }
}
