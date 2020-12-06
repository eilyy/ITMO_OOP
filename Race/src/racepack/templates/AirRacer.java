package racepack.templates;

public abstract class AirRacer implements Racer {
    protected abstract double getDistanceReducer(int distance);

    public double getRaceTime(int distance) {
        return distance * (1.0 - getDistanceReducer(distance)) / getSpeed();
    }
}