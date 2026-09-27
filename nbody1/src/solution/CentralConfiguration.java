package solution;

public class CentralConfiguration extends Universe{
    public CentralConfiguration(int numBodies, double angleVelPos) {
        final double RADIUS = 1e11;
        final double GAMMA = 5e-5;
        final double MASS = 1e33;
        final double DISTANCE = 0.4*RADIUS;
        double velocityMagnitude = GAMMA*DISTANCE;

        this.numBodies = numBodies;
        radius = RADIUS;
        bodies = new Body[numBodies];

        for (int i=0; i<numBodies; i++) {
            double anglePos = (2*Math.PI*i)/numBodies;
            double rx = DISTANCE*Math.cos(anglePos);
            double ry = DISTANCE*Math.sin(anglePos);
            double vx = velocityMagnitude*Math.cos(anglePos + angleVelPos);
            double vy = velocityMagnitude*Math.sin(anglePos + angleVelPos);
            bodies[i] = new Body(new Vector(new double[]{rx,ry}), new Vector(new double[]{vx,vy}), MASS);
        }
    }
}
