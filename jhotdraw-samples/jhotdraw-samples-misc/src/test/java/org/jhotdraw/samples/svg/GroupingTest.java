package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.figure.GroupFigure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.samples.svg.figures.SVGGroupFigure;
import org.junit.Test;
import java.awt.geom.Rectangle2D;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class GroupingTest {

    @Test
    public void addToGroupTest() {
        SVGGroupFigure group = new SVGGroupFigure();
        RectangleFigure rect1 = new RectangleFigure(0, 0, 5, 5);
        RectangleFigure rect2 = new RectangleFigure(10, 10, 5, 5);
        group.add(rect2);
        group.add(rect1);
        assertEquals(2, group.getChildCount());
    }

    @Test
    public void boundsNoChildrenTest() {
        GroupFigure group = new SVGGroupFigure();
        assertNotNull(group.getBounds());
    }

    @Test
    public void boundsWithChildrenTest() {
        GroupFigure group = new SVGGroupFigure();
        RectangleFigure rect1 = new RectangleFigure(0, 0, 5, 5);
        RectangleFigure rect2 = new RectangleFigure(10, 10, 5, 5);
        group.add(rect1);
        group.add(rect2);
        assertEquals(new Rectangle2D.Double(0, 0, 15, 15), group.getBounds());
    }

}