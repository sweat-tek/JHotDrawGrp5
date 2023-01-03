package org.jhotdraw.samples.svg;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.jhotdraw.gui.JPopupButton;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class FeatureTest extends AssertJSwingJUnitTestCase {

    @Override
    protected void onSetUp() {
        application(Main.class).start();
    }

    @Test
    public void groupAndUngroupRect() {
        FrameFixture frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return "unnamed - JHotDraw SVG".equals(frame.getTitle()) && frame.isShowing();
            }
        }).using(robot());

        GenericTypeMatcher<JToggleButton> rectangleTool = new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
            @Override
            protected boolean isMatching(JToggleButton button) {
                return "Rectangle".equals(button.getName());
            }
        };

        GenericTypeMatcher<JToggleButton> selectionTool = new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
            @Override
            protected boolean isMatching(JToggleButton button) {
                return "Selection".equals(button.getName());
            }
        };

        GenericTypeMatcher<JPopupButton> group = new GenericTypeMatcher<JPopupButton>(JPopupButton.class) {
            @Override
            protected boolean isMatching(JPopupButton button) {
                return "Group".equals(button.getName());
            }
        };

        frame.toggleButton(rectangleTool).click();
        JPanelFixture canvas = frame.panel("Canvas");
        canvas.robot().moveMouse(960, 520);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(970, 530);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(960, 500);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(970, 510);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);
        //Select
        frame.toggleButton(selectionTool).click();
        canvas.robot().moveMouse(950, 500);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(980, 540);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);
        //group
        frame.button(group).click();

    }
}
