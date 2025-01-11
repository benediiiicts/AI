import java.util.*;

public class Selection {
    private static final Random random = new Random();

    public static Individual selectParent(List<Individual> population) {
        int tournamentSize = 5;
        List<Individual> tournament = new ArrayList<>();
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(random.nextInt(population.size())));
        }
        return Collections.min(tournament, Comparator.comparingInt(ind -> ind.fitness));
    }
}