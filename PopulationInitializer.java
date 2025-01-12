import java.util.*;

public class PopulationInitializer {
    public static List<Individual> initialize(int[][] initialGrid, int populationSize, int N) {
        List<Individual> population = new ArrayList<>(); // Untuk menyimpan populasi awal.
        Random random = new Random();

        for (int i = 0; i < populationSize; i++) {
            int[][] grid = new int[N][N]; // Grid untuk individu baru.
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // Jika sel kosong (nilai 0), isi dengan angka acak (1 atau 2).
                    // Jika sel sudah diisi di grid awal, biarkan nilai tersebut.
                    grid[r][c] = (initialGrid[r][c] == 0) ? random.nextInt(2) + 1 : initialGrid[r][c];
                }
            }
            population.add(new Individual(grid));
        }
        return population;
    }
}
