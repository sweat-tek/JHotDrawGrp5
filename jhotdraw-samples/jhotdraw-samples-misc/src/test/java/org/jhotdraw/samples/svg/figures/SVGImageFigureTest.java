package org.jhotdraw.samples.svg.figures;

import junit.framework.AssertionFailedError;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class SVGImageFigureTest {
    private final String IMAGE_PATH = "src/test/resources/org.jhotdraw/";

    @Test
    public void loadImageFromFileTest() {
        SVGImageFigure svgImageFigure = new SVGImageFigure();
        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());
        File file = new File(uri);

        try {
            svgImageFigure.loadImage(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionFailedError();
        }

        String message = assertThrows(IOException.class, () -> {
            URI newUri = URI.create("file:" + Paths.get(IMAGE_PATH + "corruptFile.png").toAbsolutePath());
            File corruptFile = new File(newUri);
            svgImageFigure.loadImage(corruptFile);
        }).getMessage();

        assertTrue(message.contains("Couldn't load image from file"));

        message = assertThrows(IOException.class, () -> {
            URI newUri = URI.create("file:" + Paths.get(IMAGE_PATH + "test.txt").toAbsolutePath());
            File corruptFile = new File(newUri);
            svgImageFigure.loadImage(corruptFile);
        }).getMessage();

        assertTrue(message.contains("Couldn't load image from file"));
    }

    @Test
    public void loadImageFromInputStreamTest() {
        SVGImageFigure svgImageFigure = new SVGImageFigure();
        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());

        InputStream file;
        try {
            file = Files.newInputStream(new File(uri).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(file);

        try {
            svgImageFigure.loadImage(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionFailedError();
        }

        String message = assertThrows(IOException.class, () -> {
            URI newUri = URI.create("file:" + Paths.get(IMAGE_PATH + "corruptFile.png").toAbsolutePath());
            InputStream corruptInputStream = Files.newInputStream(new File(newUri).toPath());
            svgImageFigure.loadImage(corruptInputStream);
        }).getMessage();

        assertTrue(message.contains("Couldn't load image from file"));
    }
}