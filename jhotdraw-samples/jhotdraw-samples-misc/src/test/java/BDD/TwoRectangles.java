package BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;

import javax.swing.*;

public class TwoRectangles extends Stage<TwoRectangles> {

    @As("selecting the rectangle tool")
    public TwoRectangles select_rectangle_tool(FrameFixture frame) {
        GenericTypeMatcher<JToggleButton> rectangleTool = new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
            @Override
            protected boolean isMatching(JToggleButton button) {
                return "Rectangle".equals(button.getName());
            }
        };
        frame.toggleButton(rectangleTool).click();
        return self();
    }

    @As("Drawing rectangles")
    public void draw_rectangles(FrameFixture frame) {
        JPanelFixture canvas = frame.panel("Canvas");
        canvas.robot().moveMouse(960, 520);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(970, 530);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(960, 500);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(970, 510);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);

        self();
    }

}
