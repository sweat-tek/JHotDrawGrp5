package org.jhotdraw.samples.svg.JGivenRectangle;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.junit.Before;

import java.awt.geom.AffineTransform;

public class WhenRepositioningAndCloning extends Stage<WhenRepositioningAndCloning> {

    @ProvidedScenarioState
    @ExpectedScenarioState
    private SVGRectFigure rectFigure;

    private AffineTransform affineTransform;

    private SVGRectFigure clonedRect;

    @Before
    public void setUp() {
        affineTransform = new AffineTransform();
        assert clonedRect == null;
    }

    public WhenRepositioningAndCloning whenRepositioningAndCloning() {
        assert rectFigure != null;
        //clone
        clonedRect = rectFigure.clone();

        affineTransform.translate(30, 30);
        // Move rectangle
        clonedRect.transform(affineTransform);
        return this;
    }

}
