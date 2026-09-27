package solution;

import solution.integrators.Euler;
import solution.integrators.Integrator;
import solution.integrators.LeapFrog;
import solution.plotters.Offline;
import solution.plotters.Online;
import solution.plotters.Plotter;

public class Main {

    // Arguments: <dt> <integrador> <plotter> <args plotter> <configuracio> <args configuracio>
    //  integrador: euler | leapfrog
    //  plotter:    online <pauseTime> <trace|notrace>
    //              offline <fitxer sortida> <temps final>
    // Exemples:
    //  - 1000 leapfrog online 0 trace file data/3body.txt
    //  - 10 euler online 0 trace central 10 0.5
    //  - 10 leapfrog online 0 notrace nplus1 20
    //  - 0.01 leapfrog online 10 trace choreography 1
    //  - 1e-6 leapfrog offline choreography.txt 10 choreography 1
    public static void main(String[] args) {
        int k = 0;
        double dt = Double.parseDouble(args[k++]);
        String integratorName = args[k++].toLowerCase();
        String plotterName = args[k++].toLowerCase();

        // arguments del plotter: es guarden fins tenir l'univers (Online necessita el radi)
        int pauseTime = 0;
        boolean trace = false;
        String outFile = null;
        double endTime = 0;
        switch (plotterName) {
            case "online":
                pauseTime = Integer.parseInt(args[k++]);
                trace = args[k++].equalsIgnoreCase("trace");
                break;
            case "offline":
                outFile = args[k++];
                endTime = Double.parseDouble(args[k++]);
                break;
            default:
                System.out.println("Plotter desconegut: " + plotterName);
                return;
        }

        Universe universe;
        String mode = args[k++].toLowerCase();
        switch (mode) {
            case "file":
                universe = new ConfigurationFromFile(args[k++]);
                break;
            case "central":
                int numBodiesCentral = Integer.parseInt(args[k++]);
                double angleVelPos = Double.parseDouble(args[k++]);
                universe = new CentralConfiguration(numBodiesCentral, angleVelPos);
                break;
            case "nplus1":
                universe = new Nplus1(Integer.parseInt(args[k++]));
                break;
            case "choreography":
                universe = new Choreography(Integer.parseInt(args[k++]));
                break;
            default:
                System.out.println("Mode desconegut: " + mode);
                return;
        }

        Integrator integrator;
        switch (integratorName) {
            case "euler":
                integrator = new Euler(dt);
                break;
            case "leapfrog":
                integrator = new LeapFrog(dt);
                break;
            default:
                System.out.println("Integrador desconegut: " + integratorName);
                return;
        }

        Plotter plotter;
        if (plotterName.equals("online")) {
            plotter = new Online(trace, universe.getRadius(), pauseTime);
        } else {
            plotter = new Offline(outFile, universe.getNumBodies());
        }

        NBodySimulator simulator = new NBodySimulator(universe, plotter, integrator);
        if (plotterName.equals("online")) {
            simulator.simulate();           // infinita, com a la part 1
        } else {
            simulator.simulate(0, endTime); // acotada: cal tancar el fitxer
        }
    }
}
