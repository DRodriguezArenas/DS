package solution.plotters;

import solution.util.Vector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class Offline implements Plotter {
    private final String fname;
    private final int numBodies;
    private PrintWriter printWriter;

    public Offline(String fname, int numBodies) {
        this.fname = fname;
        this.numBodies = numBodies;
    }

    @Override
    public void start() {
        try {
            printWriter = new PrintWriter(new FileWriter(fname));
        } catch (IOException e) {
            throw new RuntimeException("No es pot obrir " + fname, e);
        }
        printWriter.println(numBodies);
    }

    @Override
    public void draw(Vector[] bodiesPosition) {
        for (Vector position : bodiesPosition) {
            printWriter.printf(Locale.US, "%f %f\n", position.cartesian(0), position.cartesian(1));
        }
    }

    @Override
    public void stop() {
        printWriter.close(); // sense això el fitxer pot quedar incomplet
    }
}
