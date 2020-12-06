package racepack;

import racepack.templates.LandRacer;

public class Boots extends LandRacer {
    @Override
    public String getName() {
        return "All-Terrain Boots";
    }

    @Override
    public double getSpeed() {
        return 6;
    }

    @Override
    protected double getActivityTime() {
        return 60;
    }

    @Override
    protected double getRestDuration(int restCounter) {
        return (restCounter == 0) ? 10 : 5;
    }
}
