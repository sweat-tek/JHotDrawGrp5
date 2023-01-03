package org.jhotdraw.samples.svg;

import junit.framework.AssertionFailedError;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.io.InputFormat;
import org.jhotdraw.gui.JFileURIChooser;
import org.junit.Test;

import javax.swing.filechooser.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SVGViewTest {
    private final String IMAGE_PATH = "src/test/resources/org.jhotdraw/";

    @Test
    public void readImageWithCorrectSelectedFormatTest() {
        SVGView svgView = new SVGView();

        JFileURIChooser chooserMock = createChooser(svgView);

        chooserMock.setFileFilter(
                Arrays.stream(chooserMock.getChoosableFileFilters())
                        .filter(ff -> ff.getDescription().toLowerCase().contains("png")).findFirst().get()
        );

        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());

        try {
            svgView.read(uri, chooserMock);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionFailedError();
        }
    }

    @Test
    public void readImageWithIncorrectSelectedFormatTest() {
        SVGView svgView = new SVGView();

        JFileURIChooser chooserMock = createChooser(svgView);

        String correctFilter = "png";

        for (FileFilter ff : chooserMock.getChoosableFileFilters()) {
            if (!ff.getDescription().toLowerCase().contains(correctFilter)) {
                chooserMock.setFileFilter(ff);
            }

            URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "actions.png").toAbsolutePath());

            try {
                svgView.read(uri, chooserMock);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AssertionFailedError();
            }
        }

    }

    @Test
    public void readImageWithUnsupportedFormatTest() {
        SVGView svgView = new SVGView();

        JFileURIChooser chooserMock = createChooser(svgView);
        URI uri = URI.create("file:" + Paths.get(IMAGE_PATH + "TestLab1.pdf").toAbsolutePath());
        String message = assertThrows(IOException.class, () -> svgView.read(uri, chooserMock)).getMessage();

        assertEquals("The format of the file is not supported.", message);
    }

    private JFileURIChooser createChooser(SVGView view) {
        JFileURIChooser chooserMock = new JFileURIChooser();
        final HashMap<FileFilter, InputFormat> fileFilterInputFormatMap = new HashMap<>();
        chooserMock.putClientProperty(SVGApplicationModel.INPUT_FORMAT_MAP_CLIENT_PROPERTY, fileFilterInputFormatMap);

        Drawing d = view.createDrawing();
        for (InputFormat format : d.getInputFormats()) {
            FileFilter ff = format.getFileFilter();

            fileFilterInputFormatMap.put(ff, format);
            chooserMock.addChoosableFileFilter(ff);
        }

        chooserMock.setFileFilter(chooserMock.getChoosableFileFilters()[0]);

        return chooserMock;
    }
}