package datamanagement;

public class TranslationSegment {

    private int segmentID;
    private String sourceText;
    private String targetText;

    public TranslationSegment() {

    }

    public TranslationSegment(String source) {
        this.setSourceText(source);
    }

    public int getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(int segmentID) {
        this.segmentID = segmentID;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }
}
