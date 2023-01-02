/*
 * @(#)ODGGroupFigure.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.odg.figures;

import org.jhotdraw.draw.figure.GroupFigure;
import org.jhotdraw.draw.figure.Figure;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import org.jhotdraw.draw.*;
import static org.jhotdraw.draw.AttributeKeys.TRANSFORM;
import org.jhotdraw.draw.figure.SVGOpacityStrategy;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.handle.TransformHandleKit;
import org.jhotdraw.samples.odg.ODGAttributeKeys;
import static org.jhotdraw.samples.odg.ODGAttributeKeys.*;

/**
 * ODGGroupFigure.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ODGGroupFigure extends GroupFigure implements ODGFigure {

    private static final long serialVersionUID = 1L;
    private HashMap<AttributeKey<?>, Object> attributes = new HashMap<AttributeKey<?>, Object>();

    /**
     * Creates a new instance.
     */
    public ODGGroupFigure() {
        ODGAttributeKeys.setDefaults(this);
    }

    @Override
    public <T> void set(AttributeKey<T> key, T value) {
        if (key == OPACITY) {
            attributes.put(key, value);
        } else {
            super.set(key, value);
        }
        invalidate();
    }

    @Override
    public <T> T get(AttributeKey<T> key) {
        return key.get(attributes);
    }

    @Override
    public Map<AttributeKey<?>, Object> getAttributes() {
        return new HashMap<AttributeKey<?>, Object>(attributes);
    }

    @SuppressWarnings("unchecked")
    public void setAttributes(Map<AttributeKey<?>, Object> map) {
        for (Map.Entry<AttributeKey<?>, Object> entry : map.entrySet()) {
            set((AttributeKey<Object>) entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void draw(Graphics2D g) {
        double opacity = get(OPACITY);
        SVGOpacityStrategy ops = new SVGOpacityStrategy(this);
        if (ops.execute(g, opacity)){
            super.draw(g);
        }
    }

    @Override
    public Rectangle2D.Double getBounds() {
        if (cachedBounds == null) {
            if (getChildCount() == 0) {
                cachedBounds = new Rectangle2D.Double();
            } else {
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
            }
        }
        return (Rectangle2D.Double) cachedBounds.clone();
    }

    @Override
    public LinkedList<Handle> createHandles(int detailLevel) {
        LinkedList<Handle> handles = new LinkedList<Handle>();
        if (detailLevel == 0) {
            TransformHandleKit.addTransformHandles(this, handles);
        }
        return handles;
    }

    @Override
    public boolean isEmpty() {
        return getChildCount() == 0;
    }

    public ODGGroupFigure clone() {
        ODGGroupFigure that = (ODGGroupFigure) super.clone();
        that.attributes = new HashMap<AttributeKey<?>, Object>(this.attributes);
        return that;
    }
}
