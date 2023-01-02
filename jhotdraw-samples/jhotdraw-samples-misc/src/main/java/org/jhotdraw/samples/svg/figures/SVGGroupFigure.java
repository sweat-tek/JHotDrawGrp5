/*
 * @(#)SVGGroupFigure.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.samples.svg.figures;

import dk.sdu.mmmi.featuretracer.lib.FeatureEntryPoint;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.figure.GroupFigure;
import org.jhotdraw.draw.figure.SVGOpacityStrategy;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.handle.TransformHandleKit;
import org.jhotdraw.samples.svg.SVGAttributeKeys;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.jhotdraw.samples.svg.SVGAttributeKeys.*;

/**
 * SVGGroupFigure.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SVGGroupFigure extends GroupFigure implements SVGFigure {

    private static final long serialVersionUID = 1L;
    private HashMap<AttributeKey<?>, Object> attributes = new HashMap<AttributeKey<?>, Object>();

    /**
     * Creates a new instance.
     */
    @FeatureEntryPoint(value = "GroupSVGFigure")
    public SVGGroupFigure() {
        SVGAttributeKeys.setDefaults(this);
    }

    @Override
    public <T> void set(AttributeKey<T> key, T value) {
        if (key == OPACITY) {
            attributes.put(key, value);
        } else if (key == LINK || key == LINK_TARGET) {
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
        if (ops.execute(g, opacity)) {
            super.draw(g);
        }
    }

    @Override
    public LinkedList<Handle> createHandles(int detailLevel) {
        LinkedList<Handle> handles = new LinkedList<Handle>();
        switch (detailLevel) {
            case -1: // Mouse hover handles
                TransformHandleKit.addGroupHoverHandles(this, handles);
                break;
            case 0:
                TransformHandleKit.addGroupTransformHandles(this, handles);
                handles.add(new LinkHandle(this));
                break;
        }
        return handles;
    }

    @Override
    public boolean isEmpty() {
        return getChildCount() == 0;
    }

    @Override
    public SVGGroupFigure clone() {
        SVGGroupFigure that = (SVGGroupFigure) super.clone();
        that.attributes = new HashMap<AttributeKey<?>, Object>(this.attributes);
        return that;
    }
}
