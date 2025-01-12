import java.util.Random;

public class GeneratorRandom {
    private static final long seed = 178456;
    private static Random random = null;

    public static Random getRandom() {
        if(random == null) {
            random = new Random(seed);
        }

        return random;
    }

    public static long getSeed() {
        return seed;
    }
}
