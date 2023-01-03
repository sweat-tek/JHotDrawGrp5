import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;


import java.awt.geom.AffineTransform;

public class WhenTryingToMove extends Stage<WhenTryingToMove> {
    @ExpectedScenarioState
    @ProvidedScenarioState
    public SVGRectFigure rectFigure;

    private AffineTransform affineTransform;



    public WhenTryingToMove trying_to_move_the_rectangle() {
        assert rectFigure != null;
        assert rectFigure.getHeight() != 0 && rectFigure.getWidth() != 0;
        affineTransform = new AffineTransform();

        affineTransform.translate(30, 30);
        // Move rectangle
        rectFigure.transform(affineTransform);

        return this;
    }
}