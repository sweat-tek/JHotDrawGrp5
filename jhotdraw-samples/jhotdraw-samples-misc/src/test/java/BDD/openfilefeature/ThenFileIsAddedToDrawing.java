package BDD.openfilefeature;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import org.assertj.swing.fixture.FrameFixture;

public class ThenFileIsAddedToDrawing extends Stage<ThenFileIsAddedToDrawing> {
    @As("the file is added to the drawing panel with the correct title")
    public void theFileIsAddedToDrawing() {
        FrameFixture frame = OpenFileFeatureBDD.frame;
        frame.requireTitle(OpenFileFeatureBDD.IMAGE_NAME + " - JHotDraw SVG");
    }
}
