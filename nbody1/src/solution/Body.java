package solution;

import solution.util.Vector;

public class Body {
    private Vector r;             // position
    private Vector v;             // velocity
    private Vector a;             // acceleration (la necessita el LeapFrog)
    private final double mass;
    private final double G;

    public Body(Vector r, Vector v, double mass) { // constructor amb G real
        this(r, v, mass, 6.67e-11);
    }

    public Body(Vector r, Vector v, double mass, double g) { // constructor amb G custom
        this.r = r;
        this.v = v;
        this.a = new Vector(2);   // acceleració inicial zero
        this.mass = mass;
        this.G = g;
    }

    public void move(Vector f, double dt) {
        Vector acc = f.scale(1 / mass);
        v = v.plus(acc.scale(dt));
        r = r.plus(v.scale(dt));
    }

    public Vector forceFrom(Body b) {
        Vector delta = b.r.minus(this.r);
        double dist = delta.magnitude();
        double magnitude = (G * this.mass * b.mass) / (dist * dist);
        return delta.direction().scale(magnitude);
    }

    // --- getters ---
    public Vector getPosition()     { return r; }
    public Vector getVelocity()     { return v; }
    public Vector getAcceleration() { return a; }
    public double getMass()         { return mass; }

    // --- setters (els fa servir Universe, cridat pels integradors) ---
    public void setPosition(Vector r)     { this.r = r; }
    public void setVelocity(Vector v)     { this.v = v; }
    public void setAcceleration(Vector a) { this.a = a; }

    @Override
    public String toString() {
        return "position " + r + ", velocity " + v + ", mass " + mass;
    }
}
