package racepack.race;

import racepack.templates.Racer;

import java.util.Vector;

public class Race<T extends Racer> {
    private Vector<T> participants = new Vector<>();

    private int distance = 0;
    private T winner = null;
    private double bestTime = 0;

    public void startRace() throws IllegalStateException {
        double winnerTime = Double.MAX_VALUE;
        if(this.distance == 0 || this.participants.isEmpty())
            throw new IllegalStateException("The race is not set up yet");
        if(this.winner != null)
            throw new IllegalStateException("The race is already over");
        for(T i : this.participants) {
            if(i.getRaceTime(this.distance) < winnerTime) {
                this.winner = i;
                winnerTime = i.getRaceTime(this.distance);
            }
        }
        this.bestTime = winnerTime;
    }

    public T whoWon() throws IllegalStateException {
        if(winner == null)
            throw new IllegalStateException("The race is not over yet");
        return this.winner;
    }

    public double getBestTime() throws IllegalStateException {
        if(winner == null)
            throw new IllegalStateException("The race is not over yet");
        return this.bestTime;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) throws IllegalArgumentException {
        this.distance = distance;
        if(this.distance == 0)
            throw new IllegalArgumentException("Distance can not be equal to 0");
    }

    public void registerParticipant(T racer) {
        this.participants.add(racer);
    }
}
