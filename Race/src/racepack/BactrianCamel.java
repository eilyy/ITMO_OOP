package racepack;

import racepack.templates.LandRacer;

public class BactrianCamel extends LandRacer {
    @Override
    public String getName() {
        return "Bactrian Camel";
    }

    @Override
    public double getSpeed() {
        return 10;
    }

    @Override
    protected double getActivityTime() {
        return 30;
    }

    @Override
    protected double getRestDuration(int restCounter) {
        return (restCounter == 0) ? 5 : 8;
    }
}
