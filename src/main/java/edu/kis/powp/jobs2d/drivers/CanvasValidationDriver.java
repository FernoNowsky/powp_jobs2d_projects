package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.canvas.CanvasMargin;
import edu.kis.powp.jobs2d.canvas.ICanvas;

public class CanvasValidationDriver implements Job2dDriver {
    private final ICanvas canvas;
    private boolean isCanvasExceeded = false;
    private boolean isMarginExceeded = false;

    public CanvasValidationDriver(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void setPosition(int x, int y) {
        checkBounds(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        checkBounds(x, y);
    }

    private void checkBounds(int x, int y) {
        if (!canvas.containsPoint(x, y)) {
            isCanvasExceeded = true;
        }
        // We should add margin to the canvas class. By this we could use getMargin() method. Now it is not possible.
        if (!canvas.containsPointWithMargin(x, y, CanvasMargin.standard())) {
            isMarginExceeded = true;
        }
    }

    public boolean isCanvasExceeded() {
        return isCanvasExceeded;
    }

    public boolean isMarginExceeded() {
        return isMarginExceeded;
    }
}
