package racepack;

import racepack.templates.LandRacer;

public class FastCamel extends LandRacer {
    @Override
    public String getName() {
        return "Fast Camel";
    }

    @Override
    public double getSpeed() {
        return 40;
    }

    @Override
    protected double getActivityTime() {
        return 10;
    }

    @Override
    protected double getRestDuration(int restCounter) {
        if(restCounter == 0)
            return 5;
        else if(restCounter == 1)
            return 6.5;
        else
            return 8;
    }
}
