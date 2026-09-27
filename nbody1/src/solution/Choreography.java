package solution;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Choreography extends Universe{
    public Choreography(int nchoreography) {
        String fname = "data/simo-initial-conditions.txt";
        final int NUM_CHOREOGRAPHIES = 345;
        assert (nchoreography>=1) && (nchoreography<=NUM_CHOREOGRAPHIES);
        double c1=0, c2=0, c3=0, c4=0, c5=0;
        try {
            Scanner in = new Scanner(new FileReader(fname));
            for (int i=1; i<=nchoreography; i++) {
                c1 = Double.parseDouble(in.next());
                c2 = Double.parseDouble(in.next());
                c3 = Double.parseDouble(in.next());
                c4 = Double.parseDouble(in.next());
                c5 = Double.parseDouble(in.next());
            }
            Vector r1 = new Vector(new double[]{-2*c1,0});
            Vector r2 = new Vector(new double[]{c1,c2});
            Vector r3 = new Vector(new double[]{c1,-c2});
            Vector v1 = new Vector(new double[]{0,-2*c4});
            Vector v2 = new Vector(new double[]{c3,c4});
            Vector v3 = new Vector(new double[]{-c3,c4});
            radius = 0.5;
            numBodies = 3;
            bodies = new Body[numBodies];
            double mass = 1./3 ;
            double G = 1.0;
            bodies[0] = new Body(r1, v1, mass,G);
            bodies[1] = new Body(r2, v2, mass,G);
            bodies[2] = new Body(r3, v3, mass,G);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
