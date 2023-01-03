import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.jhotdraw.samples.svg.Main;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class MainTest extends AssertJSwingJUnitTestCase {

    private final String IMAGES_PATH = "src/test/resources/org.jhotdraw/";
    private final String IMAGE_NAME = "actions.png";

    @Override
    protected void onSetUp() {
        application(Main.class).start();
    }

    @Test
    public void testOpenFileFeature() {
        FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return "unnamed - JHotDraw SVG".equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot());

        GenericTypeMatcher<JMenuItem> fileMenuItemMatcher = new GenericTypeMatcher<JMenuItem>(JMenuItem.class) {
            @Override
            protected boolean isMatching(JMenuItem menuItem) {
                return "File".equals(menuItem.getText());
            }
        };

        frame.menuItem(fileMenuItemMatcher).click();

        GenericTypeMatcher<JMenuItem> openFileMatcher = new GenericTypeMatcher<JMenuItem>(JMenuItem.class) {
            @Override
            protected boolean isMatching(JMenuItem menuItem) {
                return "Open...".equals(menuItem.getText());
            }
        };

        frame.menuItem(openFileMatcher).click();

        Path path = Paths.get(IMAGES_PATH + IMAGE_NAME).toAbsolutePath();

        frame.textBox("GTKFileChooser.fileNameTextField").setText(path.toString());
        GenericTypeMatcher<JButton> okMatcher = new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton item) {
                return "OK".equals(item.getText());
            }
        };

        frame.button(okMatcher).click();

        frame.requireTitle(IMAGE_NAME + " - JHotDraw SVG");
    }
}