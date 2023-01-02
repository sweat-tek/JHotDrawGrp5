package org.jhotdraw.gui.action;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.figure.RectangleFigure;
import com.tngtech.jgiven.Stage;

import java.awt.*;

import static org.jhotdraw.draw.AttributeKeys.FILL_COLOR;

public class GivenShape extends Stage<GivenShape> {

    @ProvidedScenarioState
    private RectangleFigure shape;

    @ProvidedScenarioState
    private Color originalColor;

    public void i_have_selected_a_shape_on_the_canvas() {
        originalColor = Color.WHITE;
        shape = new RectangleFigure();
        shape.set(FILL_COLOR, originalColor);
    }
}
