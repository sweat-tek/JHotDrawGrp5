package org.jhotdraw.samples.svg;

import org.jhotdraw.samples.svg.figures.SVGRectFigure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.AffineTransform;

public class UnitTestRectangle {

    private SVGRectFigure rectangle;
    private AffineTransform affineTransform;


    @Before
    public void setup(){
        rectangle = new SVGRectFigure(10, 10, 5, 5, 0, 0);
        affineTransform = new AffineTransform();
    }

    @Test
    public void testMovementFunctionality(){
        // Making the cordinates
        affineTransform.translate(30, 30);
        // Move rectangle
        rectangle.transform(affineTransform);
        // Assert
        Assert.assertTrue(rectangle.getX() == 40.0 && rectangle.getY()== 40.0);
    }
    @Test
    public void testCloning(){
        // Clone
        SVGRectFigure clonedRect = rectangle.clone();
        // Assert
        Assert.assertTrue(rectangle.getHeight() == clonedRect.getHeight() && rectangle.getWidth() == clonedRect.getWidth());
    }


}
