package BDD.openfilefeature;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WhenSelectValidFile extends Stage<WhenSelectValidFile> {
    @As( "selecting a valid file")
    public WhenSelectValidFile selectingValidFile() {
        FrameFixture frame = OpenFileFeatureBDD.frame;
        Path path = Paths.get(OpenFileFeatureBDD.IMAGES_PATH + OpenFileFeatureBDD.IMAGE_NAME).toAbsolutePath();

        frame.textBox("GTKFileChooser.fileNameTextField").setText(path.toString());

        return self();
    }

    @As( "clicks the 'OK' button")
    public WhenSelectValidFile clickOk() {
        FrameFixture frame = OpenFileFeatureBDD.frame;
        GenericTypeMatcher<JButton> okMatcher = new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton item) {
                return "OK".equals(item.getText());
            }
        };

        frame.button(okMatcher).click();

        return self();
    }
}
