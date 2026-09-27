package solution.integrators;

import solution.Universe;
import solution.util.Vector;

public class LeapFrog extends Integrator {

    public LeapFrog(double timeStep) {
        super(timeStep);
    }

    @Override
    public void move(Universe universe) {
        int numBodies = universe.getNumBodies();
        Vector[] xNew = new Vector[numBodies];
        Vector[] acc = new Vector[numBodies];

        // 1. noves posicions amb l'acceleració actual
        for (int i = 0; i < numBodies; i++) {
            Vector a = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
            Vector x = universe.getBodyPosition(i);
            Vector v = universe.getBodyVelocity(i);
            xNew[i] = x.plus(v.scale(timeStep)).plus(a.scale(timeStep * timeStep).scale(0.5));
            acc[i] = a;
        }
        // 2. aplicar-les totes alhora
        for (int i = 0; i < numBodies; i++) {
            universe.setBodyPosition(i, xNew[i]);
            universe.setBodyAcceleration(i, acc[i]);
        }
        // 3. amb les posicions noves, noves acceleracions i velocitats
        for (int i = 0; i < numBodies; i++) {
            Vector aNew = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
            Vector v = universe.getBodyVelocity(i);
            Vector vNew = v.plus(universe.getBodyAcceleration(i).plus(aNew).scale(0.5).scale(timeStep));
            universe.setBodyVelocity(i, vNew);
            universe.setBodyAcceleration(i, aNew);
        }
    }
}
