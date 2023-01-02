package org.jhotdraw.draw.figure;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class OpacityStrategy {

    GroupFigure group;

    public OpacityStrategy(GroupFigure group) {
        this.group = group;
    }

    public boolean checkOpacity(Graphics2D g, double opacity) {
        opacity = Math.min(Math.max(0d, opacity), 1d);
        if (opacity != 0d) {
            if (opacity != 1d) {
                Rectangle2D.Double drawingArea = group.getDrawingArea();
                Rectangle2D clipBounds = g.getClipBounds();
                if (clipBounds != null) {
                    Rectangle2D.intersect(drawingArea, clipBounds, drawingArea);
                }

                if (!drawingArea.isEmpty()) {
                    group.nonEmptyDraw(drawingArea, g, opacity);
                }
                return false;
            }
        }
        return true;
    }

}
