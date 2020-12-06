package racepack;

import racepack.templates.AirRacer;

public class Broom extends AirRacer {
    @Override
    public String getName() {
        return "Broom";
    }

    @Override
    public double getSpeed() {
        return 20;
    }

    @Override
    protected double getDistanceReducer(int distance) {
        return (int)(distance / 1000) * 0.01;
    }
}
