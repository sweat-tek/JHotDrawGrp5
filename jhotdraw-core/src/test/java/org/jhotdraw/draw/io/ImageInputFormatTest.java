package org.jhotdraw.draw.io;

import junit.framework.AssertionFailedError;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.figure.ImageHolderFigure;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class ImageInputFormatTest {

    private final String IMAGE_PATH = "src/test/resources/org.jhotdraw/";

    @Test
    public void readImageFromUri() {
        ImageHolderFigure imageHolderFigure = Mockito.mock(ImageHolderFigure.class);
        when(imageHolderFigure.clone()).thenReturn(Mockito.mock(ImageHolderFigure.class));

        Drawing drawing = Mockito.mock(Drawing.class);
        ImageInputFormat inputFormat = new ImageInputFormat(imageHolderFigure);

        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());

        try {
            inputFormat.read(uri, drawing, false);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionFailedError();
        }

        assertThrows(IOException.class, () -> {
            URI badUri = URI.create("file:" + Paths.get(IMAGE_PATH + "thisDoesNotExists.png").toAbsolutePath());
            inputFormat.read(badUri, drawing, false);
        });
    }

    @Test
    public void readImageFromFile() {
        ImageHolderFigure imageHolderFigure = Mockito.mock(ImageHolderFigure.class);
        when(imageHolderFigure.clone()).thenReturn(Mockito.spy(imageHolderFigure));

        Drawing drawing = Mockito.mock(Drawing.class);
        ImageInputFormat inputFormat = new ImageInputFormat(imageHolderFigure);

        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());
        File file = new File(uri);

        try {
            inputFormat.read(file, drawing, false);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionFailedError();
        }

        assertThrows(IOException.class, () -> {
            File badFile = new File(IMAGE_PATH + "thisDoesNotExists.png");
            inputFormat.read(badFile, drawing, false);
        });
    }
}