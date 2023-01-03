package BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;

import javax.swing.*;

public class SelectRectangles extends Stage<SelectRectangles> {

    @As("the selection tool is selected")
    public SelectRectangles select_selection_tool(FrameFixture frame) {
        GenericTypeMatcher<JToggleButton> selectionTool = new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
            @Override
            protected boolean isMatching(JToggleButton button) {
                return "Selection".equals(button.getName());
            }
        };

        frame.toggleButton(selectionTool).click();

        return self();
    }

    @As("the rectangles are selected")
    public void select_rectangles(FrameFixture frame) {
        JPanelFixture canvas = frame.panel("Canvas");
        canvas.robot().moveMouse(950, 500);
        canvas.robot().pressMouse(MouseButton.LEFT_BUTTON);
        canvas.robot().moveMouse(980, 540);
        canvas.robot().releaseMouse(MouseButton.LEFT_BUTTON);

        self();
    }

}
