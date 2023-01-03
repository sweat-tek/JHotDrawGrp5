package org.jhotdraw.draw.figure;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class SVGOpacityStrategy implements OpacityStrategy{

    GroupFigure group;

    public SVGOpacityStrategy(GroupFigure group) {
        this.group = group;
    }

    public boolean checkOpacity(Graphics2D g, double opacity) {
        opacity = Math.min(Math.max(0d, opacity), 1d);
        if (opacity == 0d || opacity == 1d) {
            return true;
        }
        Rectangle2D.Double drawingArea = group.getDrawingArea();
        Rectangle2D clipBounds = g.getClipBounds();
        if (clipBounds != null) {
            Rectangle2D.intersect(drawingArea, clipBounds, drawingArea);
        }

        if (!drawingArea.isEmpty()) {
            group.templateDraw(drawingArea, g, opacity);
        }
        return false;
    }

    @Override
    public boolean execute(Graphics2D g, double opacity) {
        return checkOpacity(g, opacity);
    }
}
