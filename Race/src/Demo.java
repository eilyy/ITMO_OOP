import racepack.*;
import racepack.race.Race;
import racepack.templates.*;

public class Demo {
    public static void main(String[] args) {
        Race<LandRacer> race = new Race<>();
        race.setDistance(1000);
        race.registerParticipant(new BactrianCamel());
        race.startRace();
        System.out.println(race.getBestTime());
        System.out.print(race.whoWon().getName());
    }
}
