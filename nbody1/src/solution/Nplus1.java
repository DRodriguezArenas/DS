package solution;

public class Nplus1 extends Universe{
    private double randomBetween(double a, double b) {
        return a + Math.random() * (b - a);
    }

    public Nplus1(int numPlanets) {
        final double MAX_VELOCITY = 1e05;
        final double MIN_VELOCITY = 1e04;
        final double MIN_MASS = 1e20;
        final double MAX_MASS = 1e30;
        final double RADIUS = 1e12;
        final double MASS = 1e39;

        radius = RADIUS;
        numBodies = numPlanets + 1;
        bodies = new Body[numBodies];
        bodies[0] = new Body(new Vector(new double[2]), new Vector(new double[2]), MASS);

        for (int i = 1; i < numBodies; i++) {
            double angle = randomBetween(-Math.PI, Math.PI);
            double rho = randomBetween(RADIUS/4, RADIUS/2);
            double rx = Math.cos(angle)*rho;
            double ry = Math.sin(angle)*rho;
            double vx = -ry/1000. + randomBetween(MIN_VELOCITY, MAX_VELOCITY);
            double vy = rx/1000. + randomBetween(MIN_VELOCITY, MAX_VELOCITY);
            double mass = randomBetween(MIN_MASS, MAX_MASS);
            double[] position = {rx, ry};
            double[] velocity = {vx, vy}; Vector r = new Vector(position);
            Vector v = new Vector(velocity);
            bodies[i] = new Body(r, v, mass);
        }
    }
}
