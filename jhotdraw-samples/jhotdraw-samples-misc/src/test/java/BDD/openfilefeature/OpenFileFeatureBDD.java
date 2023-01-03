package BDD.openfilefeature;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit.JGivenClassRule;
import com.tngtech.jgiven.junit.JGivenMethodRule;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.jhotdraw.samples.svg.Main;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.awt.*;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class OpenFileFeatureBDD extends AssertJSwingJUnitTestCase {
    public static final String IMAGES_PATH = "src/test/resources/org.jhotdraw/";
    public static final String IMAGE_NAME = "actions.png";
    public static FrameFixture frame;
    @ClassRule
    public static final JGivenClassRule writerRule = new JGivenClassRule();

    @Rule
    public final JGivenMethodRule scenarioRule = new JGivenMethodRule();

    @ScenarioStage
    GivenClickOpenFile givenClickOpenFile;

    @ScenarioStage
    WhenSelectValidFile whenSelectValidFile;

    @ScenarioStage
    ThenFileIsAddedToDrawing thenFileIsAddedToDrawing;

    @Override
    protected void onSetUp() {
        application(Main.class).start();
    }
    @Test
    public void openValidFileIntoDrawing() {
        FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return "unnamed - JHotDraw SVG".equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot());

        OpenFileFeatureBDD.frame = frame;

        givenClickOpenFile.given().openingOpenFileDialog();
        whenSelectValidFile.when().selectingValidFile().and().clickOk();
        thenFileIsAddedToDrawing.then().theFileIsAddedToDrawing();
    }
}
