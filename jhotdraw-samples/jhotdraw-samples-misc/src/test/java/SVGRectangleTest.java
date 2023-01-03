import org.junit.Test;
import com.tngtech.jgiven.junit.ScenarioTest;

public class SVGRectangleTest
        extends ScenarioTest<GivenASvgRectangle, WhenTryingToMove, ThenTheRectangleShouldChangePosition> {

    @Test
    public void svgRectangleShouldMove() {
        given().a_Rectangle();
        when().trying_to_move_the_rectangle();
        then().the_rectangle_should_have_repositioned();
    }
}

