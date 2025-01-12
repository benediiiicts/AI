
// Import library untuk menggunakan kelas Random dan List
import java.util.*;

public class PopulationInitializer {
    // Fungsi untuk menginisialisasi populasi awal
    public static List<Individual> initialize(int[][] initialGrid, int populationSize, int N) {
        // Membuat list untuk menyimpan individu-individu dalam populasi
        List<Individual> population = new ArrayList<>();
        // Mendapatkan instance Random dari kelas GeneratorRandom
        Random random = GeneratorRandom.getRandom();

        // Proses untuk mengisi populasi dengan individu-individu baru
        for (int i = 0; i < populationSize; i++) {
            // Membuat grid baru dengan ukuran N x N untuk individu baru
            int[][] grid = new int[N][N];
            // Mengisi setiap sel dalam grid
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // Jika sel kosong (nilai 0), isi dengan angka acak (1 atau 2).
                    // Jika sel sudah terisi sesuai grid awal, biarkan nilainya tetap.
                    grid[r][c] = (initialGrid[r][c] == 0) ? random.nextInt(2) + 1 : initialGrid[r][c];
                }
            }
            // Menambahkan individu baru yang telah dibuat ke dalam populasi
            population.add(new Individual(grid));
        }
        // Mengembalikan populasi yang telah diinisialisasi
        return population;
    }
}
