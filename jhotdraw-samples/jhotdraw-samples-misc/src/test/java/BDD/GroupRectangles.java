package BDD;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.jhotdraw.gui.JPopupButton;

public class GroupRectangles extends Stage<GroupRectangles> {

    @As("The rectangles can be grouped")
    public void group_the_selected_rectangles(FrameFixture frame) {
        GenericTypeMatcher<JPopupButton> group = new GenericTypeMatcher<JPopupButton>(JPopupButton.class) {
            @Override
            protected boolean isMatching(JPopupButton button) {
                return "Group".equals(button.getName());
            }
        };

        frame.button(group).click();

        self();
    }
}
