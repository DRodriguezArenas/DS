package solution;

public class Main {

    public static void main(String[] args) {
        // Exemples d'arguments d'execució:
        //  - Per fitxer:        1000 0 trace file data/3body.txt
        //  - Per central:        10 0 trace central 10 0.5
        //  - Per n+1:            10 0 trace nplus1 20
        //  - Per coreografia:    0.01 10 trace choreography 1

        double dt = Double.parseDouble(args[0]);
        int pauseTime = Integer.parseInt(args[1]);
        boolean trace = args[2].toLowerCase().equals("trace");
        String mode = args[3].toLowerCase();

        Universe universe = null;

        switch (mode) {
            case "file":
                String fname = args[4];
                universe = new ConfigurationFromFile(fname);
                break;
            case "central":
                int numBodiesCentral = Integer.parseInt(args[4]);
                double angleVelPos = Double.parseDouble(args[5]);
                universe = new CentralConfiguration(numBodiesCentral, angleVelPos);
                break;
            case "nplus1":
                int numPlanets = Integer.parseInt(args[4]);
                universe = new Nplus1(numPlanets);
                break;
            case "choreography":
                int nChoreography = Integer.parseInt(args[4]);
                universe = new Choreography(nChoreography);
                break;
            default:
                System.out.println("Mode desconegut: " + mode);
                return;
        }

        NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace);
        simulator.simulate();
    }
}