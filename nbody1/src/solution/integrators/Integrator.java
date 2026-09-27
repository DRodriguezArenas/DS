package solution.integrators;

import solution.Universe;

public abstract class Integrator {
    protected double timeStep;

    public Integrator(double timeStep) {
        this.timeStep = timeStep;
    }

    public double getTimeStep() {
        return timeStep;
    }

    // Avança un pas de temps l'estat de tots els cossos de l'univers
    public abstract void move(Universe universe);
}
