package org.jhotdraw.gui.action;

import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AttributeAction;
import org.jhotdraw.draw.action.ColorIcon;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.gui.JPopupButton;
import org.jhotdraw.util.ResourceBundleUtil;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;

import static org.junit.Assert.*;

public class ButtonFactoryTest {

    @Test
    public void testButtonNotNull() {
        //Create the button
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        String labelKey = "attribute.fillColor";
        DrawingEditor editor = new DefaultDrawingEditor();
        JPopupButton jpb = ButtonFactory.createEditorColorButton(
                editor,
                FILL_COLOR,
                ButtonFactory.DEFAULT_COLORS,
                ButtonFactory.DEFAULT_COLORS_COLUMN_COUNT,
                labelKey,
                labels);

        //Test if the method still works
        assertNotNull(jpb);
    }

    @Test
    public void testColorChange() {
        //Initial configuration of mock objects
        Color originalColor = Color.WHITE;
        RectangleFigure shape = new RectangleFigure();
        shape.set(FILL_COLOR, originalColor);

        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        String labelKey = "attribute.fillColor";
        DrawingEditor editor = new DefaultDrawingEditor();
        HashMap<AttributeKey<?>, Object> attr = new HashMap<>();

        //Change color
        Color chosenColor = Color.BLACK;
        //Use the new method
        AttributeAction action = ButtonFactory.createAndConfigureAttributeAction(
                editor,
                new HashMap<>(),
                labels.getToolTipTextProperty(labelKey),
                new ColorIcon(chosenColor,
                        "ChosenColor"));
        //Test the newly implemented method
        assertNotNull(action);
        attr.put(FILL_COLOR, chosenColor);
        action.applyAttributesTo(attr, new HashSet<Figure>(){{
            add(shape);
        }});

        //Assert color has changed
        assertNotEquals(originalColor, shape.get(FILL_COLOR));
    }

}
