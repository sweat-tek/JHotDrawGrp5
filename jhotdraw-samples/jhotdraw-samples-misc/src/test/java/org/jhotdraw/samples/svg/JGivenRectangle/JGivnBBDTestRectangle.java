package org.jhotdraw.samples.svg.JGivenRectangle;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

public class JGivnBBDTestRectangle extends ScenarioTest<GivenSomeRectangle, WhenRepositioningAndCloning, ThenRectangleHasANewPosition> {
    @Test
    public void cloneMoveSVGRectangle(){
            given().givenSVGRectangle();
            when().whenRepositioningAndCloning();
            then().thenRectangleHasANewPosition();
        }
}
