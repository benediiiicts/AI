import java.util.*;

public class GeneticOperators {
     private static final Random random = new Random();

    public static Individual crossover(Individual parent1, Individual parent2, int[][] initialGrid, int N) {
        int[][] childGrid = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                childGrid[r][c] = (initialGrid[r][c] == 0) ? 
                                  (random.nextBoolean() ? parent1.grid[r][c] : parent2.grid[r][c]) : 
                                  initialGrid[r][c];
            }
        }
        return new Individual(childGrid);
    }

    public static void mutate(Individual individual, int[][] initialGrid, double mutationRate, int N) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (initialGrid[r][c] == 0 && random.nextDouble() < mutationRate) {
                    individual.grid[r][c] = random.nextInt(2) + 1;
                }
            }
        }
    }
}
