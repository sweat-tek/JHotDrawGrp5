package org.jhotdraw.gui.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.DefaultDrawingEditor;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.action.AttributeAction;
import org.jhotdraw.draw.action.ColorIcon;
import org.jhotdraw.draw.figure.Figure;

import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.util.ResourceBundleUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;

public class WhenSelectingColor extends Stage<WhenSelectingColor> {

    @ExpectedScenarioState
    private RectangleFigure shape;

    public void i_choose_a_color() {
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        String labelKey = "attribute.fillColor";
        DrawingEditor editor = new DefaultDrawingEditor();
        HashMap<AttributeKey<?>, Object> attr = new HashMap<>();
        Color chosenColor = Color.BLACK;
        //Use the new method
        AttributeAction action = ButtonFactory.createAndConfigureAttributeAction(
                editor,
                new HashMap<>(),
                labels.getToolTipTextProperty(labelKey),
                new ColorIcon(chosenColor,
                        "ChosenColor"));
        //Test the newly implemented method
        attr.put(FILL_COLOR, chosenColor);
        action.applyAttributesTo(attr, new HashSet<Figure>(){{
            add(shape);
        }});
    }
}
