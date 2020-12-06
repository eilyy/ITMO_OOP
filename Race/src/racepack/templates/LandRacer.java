package racepack.templates;

public abstract class LandRacer implements Racer {
    protected abstract double getActivityTime();
    protected abstract double getRestDuration(int restCounter);

    public double getRaceTime(int distance) {
        int restCount = (int) (distance / getSpeed() / getActivityTime());
        double totalRestDuration = 0;
        for(int i = 0; i < restCount; i++) {
            totalRestDuration += getRestDuration(i);
        }
        return distance / getSpeed() + totalRestDuration;
    }
}
