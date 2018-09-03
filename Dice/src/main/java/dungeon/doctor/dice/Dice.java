package dungeon.doctor.dice;

import java.util.Random;

public class Dice {
    private Random r = new Random();
    private static Dice instance;

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public int roll(int numDice, int typeDie) {
        int result = 0;
        for (int i = 0; i < numDice; ++i) {
            result += r.nextInt() % typeDie;
        }
        return result;
    }

}
