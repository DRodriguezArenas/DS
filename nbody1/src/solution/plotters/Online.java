package solution.plotters;

import solution.util.StdDraw;
import solution.util.Vector;

public class Online implements Plotter {
    private final boolean trace;
    private final double radius;
    private final int pauseTime;

    public Online(boolean trace, double radius, int pauseTime) {
        this.trace = trace;
        this.radius = radius;
        this.pauseTime = pauseTime;
    }

    @Override
    public void start() {
        createCanvas();
    }

    @Override
    public void draw(Vector[] bodiesPosition) {
        if (!trace) {
            StdDraw.clear();
        }
        drawBodies(bodiesPosition);
        StdDraw.show();
        StdDraw.pause(pauseTime);
    }

    @Override
    public void stop() {
        // res a fer: la finestra es queda oberta
    }

    private void createCanvas() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.025);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
    }

    private void drawBodies(Vector[] bodiesPosition) {
        for (Vector pos : bodiesPosition) {
            StdDraw.point(pos.cartesian(0), pos.cartesian(1));
        }
    }
}
