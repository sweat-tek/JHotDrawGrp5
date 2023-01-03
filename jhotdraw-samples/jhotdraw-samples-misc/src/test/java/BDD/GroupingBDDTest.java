package BDD;

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

public class GroupingBDDTest extends AssertJSwingJUnitTestCase {
    @Override
    protected void onSetUp() {
        application(Main.class).start();
    }

    @ClassRule
    public static final JGivenClassRule WRITER_RULE = new JGivenClassRule();

    @Rule
    public final JGivenMethodRule scenarioRule = new JGivenMethodRule();

    @ScenarioStage
    TwoRectangles twoRectangles;
    @ScenarioStage
    SelectRectangles selectRectangles;
    @ScenarioStage
    GroupRectangles groupRectangles;

    @Test
    public void groupingBehaviorTest() {
        FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return "unnamed - JHotDraw SVG".equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot());

        twoRectangles.given().select_rectangle_tool(frame).and().draw_rectangles(frame);
        selectRectangles.when().select_selection_tool(frame).and().select_rectangles(frame);
        groupRectangles.then().group_the_selected_rectangles(frame);
    }



}
