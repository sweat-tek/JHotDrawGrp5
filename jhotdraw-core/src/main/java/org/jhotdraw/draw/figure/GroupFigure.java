/*S
 * @(#)GroupFigure.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.figure;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.geom.Geom;

/**
 * A {@link org.jhotdraw.draw.figure.Figure} which groups a collection of figures.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class GroupFigure extends AbstractCompositeFigure {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance.
     */
    @FeatureEntryPoint(value= "GroupFigure")
    public GroupFigure() {
        setConnectable(false);
    }

    /**
     * This is a default implementation that chops the point at the rectangle
     * returned by getBounds() of the figure.
     * <p>
     * Figures which have a non-rectangular shape need to override this method.
     * <p>
     * FIXME Invoke chop on each child and return the closest point.
     */
    public Point2D.Double chop(Point2D.Double from) {
        Rectangle2D.Double r = getBounds();
        return Geom.angleToPoint(r, Geom.pointToAngle(r, from));
    }



    public void nonEmptyDraw(Rectangle2D.Double drawingArea, Graphics2D g, double opacity) {
        BufferedImage buf = new BufferedImage(
                Math.max(1, (int) ((2 + drawingArea.width) * g.getTransform().getScaleX())),
                Math.max(1, (int) ((2 + drawingArea.height) * g.getTransform().getScaleY())),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D gr = buf.createGraphics();
        gr.scale(g.getTransform().getScaleX(), g.getTransform().getScaleY());
        gr.translate((int) -drawingArea.x, (int) -drawingArea.y);
        gr.setRenderingHints(g.getRenderingHints());
        super.draw(gr);
        gr.dispose();
        Composite savedComposite = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
        g.drawImage(buf, (int) drawingArea.x, (int) drawingArea.y,
                2 + (int) drawingArea.width, 2 + (int) drawingArea.height, null);
        g.setComposite(savedComposite);
    }


    /**
     * Returns true if all children of the group are transformable.
     */
    @Override
    public boolean isTransformable() {
        for (Figure f : children) {
            if (!f.isTransformable()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1));
        buf.append('@');
        buf.append(hashCode());
        if (getChildCount() > 0) {
            buf.append('(');
            for (Iterator<Figure> i = getChildren().iterator(); i.hasNext();) {
                Figure child = i.next();
                buf.append(child);
                if (i.hasNext()) {
                    buf.append(',');
                }
            }
            buf.append(')');
        }
        return buf.toString();
    }


}
