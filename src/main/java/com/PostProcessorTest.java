package com;

import com.translatorapp.PostProcessor;
import com.translatorapp.PreProcessor;
import com.translatorapp.Reader;
import org.junit.Assert;
import org.junit.Test;

public class PostProcessorTest {

    Reader textReader;
    PreProcessor textPreProcessor;
    PostProcessor textPostProcessor;

//    @Before
    public void setUp() {
        String filename = "testfile.txt";
        this.textReader = new Reader(filename);
        this.textPreProcessor = new PreProcessor(textReader);
        this.textPreProcessor.pseudoTranslate();
        this.textPreProcessor.printParsedLines();
    }

    @Test
    public void testWriteTargetLastLine() {

        this.setUp();
        this.textPreProcessor.pseudoTranslate();;
        this.textPostProcessor = new PostProcessor(this.textPreProcessor);
        this.textPostProcessor.exportTarget();

        String actual = this.textPostProcessor.targetFileContents;
        String expected = this.textPostProcessor.sourceFileContents;
        Assert.assertNotEquals(actual, expected);

    }

}
