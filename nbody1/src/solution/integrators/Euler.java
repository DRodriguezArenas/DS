package solution.integrators;

import solution.Universe;
import solution.util.Vector;

public class Euler extends Integrator {

    public Euler(double timeStep) {
        super(timeStep);
    }

    @Override
    public void move(Universe universe) {
        int numBodies = universe.getNumBodies();
        // Primer calculem tot, després actualitzem: no barrejar posicions velles i noves
        Vector[] newPositions = new Vector[numBodies];
        Vector[] newVelocities = new Vector[numBodies];

        for (int i = 0; i < numBodies; i++) {
            Vector a = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
            newVelocities[i] = universe.getBodyVelocity(i).plus(a.scale(timeStep));
            newPositions[i] = universe.getBodyPosition(i).plus(newVelocities[i].scale(timeStep));
        }
        for (int i = 0; i < numBodies; i++) {
            universe.setBodyPosition(i, newPositions[i]);
            universe.setBodyVelocity(i, newVelocities[i]);
        }
    }
}
