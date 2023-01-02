package org.jhotdraw.draw.figure;

import java.awt.*;

public interface OpacityStrategy {

    public boolean execute(Graphics2D g, double opacity);
}
