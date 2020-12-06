package racepack;

import racepack.templates.AirRacer;

public class MagicCarpet extends AirRacer {
    @Override
    public String getName() {
        return "Magic Carpet";
    }

    @Override
    public double getSpeed() {
        return 10;
    }

    @Override
    protected double getDistanceReducer(int distance) {
        if(distance < 1000)
            return 0;
        else if(distance < 5000)
            return 0.03;
        else if(distance < 10000)
            return 0.1;
        else
            return 0.05;
    }
}
