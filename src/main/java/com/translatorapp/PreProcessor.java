package com.translatorapp;

import java.util.Map;
import java.util.TreeMap;

public class PreProcessor {

    private Reader reader;
    private Map<Integer, TranslationSegment> parsedLines = new TreeMap<>();

    public PreProcessor(Reader reader) {
        this.reader = reader;
    }

    public void parseReader(){

        Map<Integer, String> textLines = this.reader.getTextLines();

        String line;
        int counter = 0;

        for (int i=0; i < textLines.size(); i++) {
            line = textLines.get(i);

            // Regex will split lines at .!? characters.
            // Will not split at . after "Mr.", "Ms.", "Mrs.", or "Esq."
            // Note:  If a line ends in a delimiter followed by ' or ",
            // the ' or " will be a new segment.

            String[] splitLine = line.split("(?<=(?<!(Mr?s?)|(Esq)|( [A-Z]))[/.!?]\\\"?)");

            // Store each line as a new Translation segment in the Map
            for (String sentence : splitLine) {
                // Get each segment
                sentence = sentence.trim();

                // Handle edge cases
                if ((sentence.equals("\"")) || (sentence.equals("\'"))) {
                    TranslationSegment previousSegment = this.parsedLines.get(counter-1);
                    previousSegment.setSourceText(previousSegment.getSourceText() + sentence);
                    this.parsedLines.put(counter-1, previousSegment);
                }
                else if (sentence != "") {
                    TranslationSegment newSegment = new TranslationSegment();
                    newSegment.setSegmentID(counter);
                    newSegment.setSourceText(sentence);
                    newSegment.setFilename(this.reader.getFilename());

                    // Add the line to the parseLines processor.
                    this.parsedLines.put(counter, newSegment);
                    counter++;
                }
            }
        }
    }

    public Map<Integer, TranslationSegment> getParsedLines() {
        return parsedLines;
    }

    public Reader getReader() {
        return reader;
    }
}
