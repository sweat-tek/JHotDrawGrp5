import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;


public class GivenASvgRectangle extends Stage<GivenASvgRectangle> {
    @ProvidedScenarioState
    public SVGRectFigure rectFigure;


    public GivenASvgRectangle a_Rectangle() {
        rectFigure = new SVGRectFigure(10, 10, 10, 10, 0, 0);
        return this;
    }
}