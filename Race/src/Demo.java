import racepack.*;
import racepack.race.Race;
import racepack.templates.Racer;

public class Demo {
    public static void main(String[] args) {
        Race<Racer> race = new Race<Racer>();
        race.setDistance(1000);
        race.registerParticipant(new BactrianCamel());
        race.startRace();
        System.out.println(race.getBestTime());
        System.out.print(race.whoWon().getName());
    }
}
