package solution.plotters;

import solution.util.Vector;

public interface Plotter {
    void start();
    void draw(Vector[] bodiesPosition);
    void stop();
}
