import java.util.*;

public class GeneticOperators {
    private static final Random random = GeneratorRandom.getRandom();

    public static Individual crossover(Individual parent1, Individual parent2, int[][] initialGrid, int N) {
        int[][] childGrid = new int[N][N]; // Grid untuk individu baru (anak).

        // Proses persilangan untuk setiap sel dalam grid.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Jika sel kosong pada grid awal, pilih nilai secara acak dari salah satu parent.
                // Jika sel sudah diisi di grid awal, pertahankan nilai tersebut.
                childGrid[r][c] = (initialGrid[r][c] == 0)
                                    ? (random.nextBoolean() ? parent1.grid[r][c] : parent2.grid[r][c])
                                    : initialGrid[r][c];
            }
        }
        return new Individual(childGrid);
    }

    public static void mutate(Individual individual, int[][] initialGrid, double mutationRate, int N) {
        // Iterasi melalui setiap sel dalam grid individu.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Hanya sel kosong pada grid awal yang dapat dimutasi.
                if (initialGrid[r][c] == 0 && random.nextDouble() < mutationRate) {
                    individual.grid[r][c] = random.nextInt(2) + 1; // Mutasi: Ubah nilai sel menjadi angka acak (1 atau 2).
                }
            }
        }
    }
}
