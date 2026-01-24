package edu.kis.powp.jobs2d.canvas;

/**
 * Defines margins around a canvas.
 */
public class CanvasMargin {
    private final int top;
    private final int bottom;
    private final int left;
    private final int right;
    
    public CanvasMargin(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    
    public CanvasMargin(int margin) {
        this(margin, margin, margin, margin);
    }
    
    public static CanvasMargin none() {
        return new CanvasMargin(0);
    }
    
    public static CanvasMargin standard() {
        return new CanvasMargin(10);
    }
    
    public int getTop() {
        return top;
    }
    
    public int getBottom() {
        return bottom;
    }
    
    public int getLeft() {
        return left;
    }
    
    public int getRight() {
        return right;
    }
}