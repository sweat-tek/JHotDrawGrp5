package org.jhotdraw.samples.svg.JGivenRectangle;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.junit.Assert;

public class ThenRectangleHasANewPosition extends Stage<ThenRectangleHasANewPosition> {
    @ExpectedScenarioState
    private SVGRectFigure clonedRect;


    public ThenRectangleHasANewPosition thenRectangleHasANewPosition(){

        assert clonedRect.getHeight() != 0 && clonedRect.getWidth() != 0;
        Assert.assertTrue(clonedRect.getX() == 40.0 && clonedRect.getY()== 40.0);

        return this;
    }

}
