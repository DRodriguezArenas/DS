package solution;

import solution.integrators.Integrator;
import solution.plotters.Plotter;

public class NBodySimulator {
    private final Universe universe;
    private final Plotter plotter;
    private final Integrator integrator;

    public NBodySimulator(Universe universe, Plotter plotter, Integrator integrator) {
        this.universe = universe;
        this.plotter = plotter;
        this.integrator = integrator;
    }

    // Simulació amb temps acotat (útil per a offline)
    public void simulate(double initTime, double endTime) {
        plotter.start();
        for (double t = initTime; t < endTime; t += integrator.getTimeStep()) {
            plotter.draw(universe.getAllBodiesPosition());
            universe.update(integrator);
        }
        plotter.stop();
    }

    // Simulació infinita (online, com a la part 1)
    public void simulate() {
        plotter.start();
        while (true) {
            plotter.draw(universe.getAllBodiesPosition());
            universe.update(integrator);
        }
    }
}
