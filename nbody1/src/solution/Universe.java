package solution;

import solution.integrators.Integrator;
import solution.util.Vector;

public abstract class Universe {
    protected int numBodies;
    protected double radius;
    protected Body[] bodies;

    public double getRadius()  { return radius; }
    public int getNumBodies()  { return numBodies; }

    // Força total que reben els altres cossos sobre el cos i
    // (és el bucle interior de l'antic update)
    public Vector computeForceOn(int i) {
        Vector force = new Vector(2);
        for (int j = 0; j < numBodies; j++) {
            if (i != j) {
                force = force.plus(bodies[i].forceFrom(bodies[j]));
            }
        }
        return force;
    }

    // Ara Universe ja no sap COM es mou: ho delega a l'integrador
    public void update(Integrator integrator) {
        integrator.move(this);
    }

    public Vector[] getAllBodiesPosition() {
        Vector[] positions = new Vector[numBodies];
        for (int i = 0; i < numBodies; i++) {
            positions[i] = bodies[i].getPosition();
        }
        return positions;
    }

    // --- accés per índex: així els integradors no depenen de Body ---
    public double getBodyMass(int i)         { return bodies[i].getMass(); }
    public Vector getBodyPosition(int i)     { return bodies[i].getPosition(); }
    public Vector getBodyVelocity(int i)     { return bodies[i].getVelocity(); }
    public Vector getBodyAcceleration(int i) { return bodies[i].getAcceleration(); }

    public void setBodyPosition(int i, Vector pos)     { bodies[i].setPosition(pos); }
    public void setBodyVelocity(int i, Vector vel)     { bodies[i].setVelocity(vel); }
    public void setBodyAcceleration(int i, Vector acc) { bodies[i].setAcceleration(acc); }
}
