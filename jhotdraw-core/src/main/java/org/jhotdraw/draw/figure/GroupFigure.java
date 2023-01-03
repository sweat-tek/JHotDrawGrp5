/*S
 * @(#)GroupFigure.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.figure;

import org.jhotdraw.geom.Geom;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import static org.jhotdraw.draw.AttributeKeys.TRANSFORM;

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
    public Rectangle2D.Double getBounds() {
        if (cachedBounds == null && getChildCount() == 0) {
            cachedBounds = new Rectangle2D.Double();
            return (Rectangle2D.Double) cachedBounds.clone();
        }
        for (Figure f : children) {
            Rectangle2D.Double bounds = f.getBounds();
            if (f.get(TRANSFORM) != null) {
                bounds.setRect(f.get(TRANSFORM).createTransformedShape(bounds).getBounds2D());
            }
            if (cachedBounds == null || cachedBounds.isEmpty()) {
                cachedBounds = bounds;
            } else {
                cachedBounds.add(bounds);
            }
        }
        return (Rectangle2D.Double) cachedBounds.clone();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1));
        buf.append('@');
        buf.append(hashCode());
        if (getChildCount() > 0) {
            buf.append('(');
            for (Iterator<Figure> i = getChildren().iterator(); i.hasNext(); ) {
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
