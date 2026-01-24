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
        CanvasMargin margin = canvas.getMargin();
        if (margin != null && !canvas.containsPointWithMargin(x, y, margin)) {
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
