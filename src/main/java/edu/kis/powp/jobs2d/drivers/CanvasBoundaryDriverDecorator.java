package edu.kis.powp.jobs2d.drivers;

import java.util.logging.Logger;

import edu.kis.powp.jobs2d.canvas.CanvasMargin;
import edu.kis.powp.jobs2d.canvas.ICanvas;
import edu.kis.powp.jobs2d.features.CanvasFeature;
import edu.kis.powp.jobs2d.visitor.DriverVisitor;
import edu.kis.powp.jobs2d.visitor.VisitableJob2dDriver;

public class CanvasBoundaryDriverDecorator implements VisitableJob2dDriver {

    private final VisitableJob2dDriver delegate;
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public CanvasBoundaryDriverDecorator(VisitableJob2dDriver delegate) {
        this.delegate = delegate;
    }

    public VisitableJob2dDriver getDelegate() {
        return delegate;
    }

    @Override
    public void setPosition(int x, int y) {
        checkBounds(x, y);
        delegate.setPosition(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        checkBounds(x, y);
        delegate.operateTo(x, y);
    }

    private void checkBounds(int x, int y) {
        ICanvas canvas = CanvasFeature.getCanvasManager().getCurrentCanvas();
        if (canvas == null) {
            return;
        }

        if (!canvas.containsPoint(x, y)) {
            logger.warning("Operation exceeds canvas boundaries: (x:" + x + ", y:" + y + ")");
            return;
        }

        CanvasMargin margin = canvas.getMargin();
        if (margin != null && !canvas.containsPointWithMargin(x, y, margin)) {
            logger.warning("Operation exceeds canvas margin: (x:" + x + ", y:" + y + ")");
        }
    }

    @Override
    public void accept(DriverVisitor visitor) {
        delegate.accept(visitor);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
