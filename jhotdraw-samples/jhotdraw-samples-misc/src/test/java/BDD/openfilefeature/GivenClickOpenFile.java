package BDD.openfilefeature;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import javax.swing.*;

public class GivenClickOpenFile extends Stage<GivenClickOpenFile> {
    @As( "opening the 'open file' dialog in the menu" )
    public void openingOpenFileDialog() {
        FrameFixture frame = OpenFileFeatureBDDTest.frame;
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
    }
}
