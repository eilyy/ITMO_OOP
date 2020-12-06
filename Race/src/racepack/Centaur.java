package racepack;

import racepack.templates.LandRacer;

public class Centaur extends LandRacer {
    @Override
    public String getName() {
        return "Centaur";
    }

    @Override
    public double getSpeed() {
        return 15;
    }

    @Override
    protected double getActivityTime() {
        return 8;
    }

    @Override
    protected double getRestDuration(int restCounter) {
        return 2;
    }
}
