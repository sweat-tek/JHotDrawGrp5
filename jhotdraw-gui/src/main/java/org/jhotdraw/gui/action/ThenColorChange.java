package org.jhotdraw.gui.action;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.figure.RectangleFigure;

import java.awt.*;

import static org.junit.Assert.*;
import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;

public class ThenColorChange extends Stage<ThenColorChange> {

    @ExpectedScenarioState
    private Color originalColor;

    @ExpectedScenarioState
    private RectangleFigure shape;

    public void the_shape_is_the_chosen_color() {
        assertNotEquals(originalColor, shape.get(FILL_COLOR));
    }
}
