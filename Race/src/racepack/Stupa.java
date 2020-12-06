package racepack;

import racepack.templates.AirRacer;

public class Stupa extends AirRacer {
    @Override
    public String getName() {
        return "Stupa";
    }

    @Override
    public double getSpeed() {
        return 8;
    }

    @Override
    protected double getDistanceReducer(int distance) {
        return 0.06;
    }
}
