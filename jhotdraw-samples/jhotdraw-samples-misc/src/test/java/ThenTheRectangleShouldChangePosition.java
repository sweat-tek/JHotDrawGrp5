import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.junit.Assert;

public class ThenTheRectangleShouldChangePosition extends Stage<ThenTheRectangleShouldChangePosition> {

    @ExpectedScenarioState
    public SVGRectFigure rectFigure;


    public ThenTheRectangleShouldChangePosition the_rectangle_should_have_repositioned() {

        assert rectFigure.getHeight() != 0 && rectFigure.getWidth() != 0;
        Assert.assertTrue(rectFigure.getX() == 40.0 && rectFigure.getY()== 40.0);

        return self();
    }
}