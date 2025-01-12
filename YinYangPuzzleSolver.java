import java.util.*;

public class YinYangPuzzleSolver {

    private int N; // Ukuran papan

    public YinYangPuzzleSolver(int gridSize) {
        this.N = gridSize;
    }

    public int[][] solvePuzzle(int[][] initialGrid) {
        int populationSize = 100; // Ukuran populasi individu dalam setiap generasi.
        int generations = 1000; // Jumlah maksimum generasi untuk dijalankan.
        double mutationRate = 0.2; // Tingkat probabilitas mutasi untuk individu.

        // Inisialisasi populasi awal
        List<Individual> population = PopulationInitializer.initialize(initialGrid, populationSize, N);

        Individual bestOverall = null; // Menyimpan individu terbaik yang ditemukan selama eksekusi.

        for (int generation = 0; generation < generations; generation++) {
            // Hitung fitness setiap individu dalam populasi.
            FitnessEvaluator.evaluate(population, initialGrid, N);

            // Urutkan populasi berdasarkan fitness (elitisme: individu terbaik di depan).
            population.sort(Comparator.comparingInt(ind -> ind.fitness));
            Individual best = population.get(0); // Individu terbaik di generasi ini.

            // Jika individu terbaik saat ini lebih baik dari yang sebelumnya, perbarui bestOverall.
            if (bestOverall == null || best.fitness < bestOverall.fitness) {
                bestOverall = best;
            }

            // Jika ditemukan solusi (fitness = 0), akhiri proses dan kembalikan solusi.
            if (best.fitness == 0) {
                System.out.println("Solution found in generation " + generation);
                return best.grid;
            }

            // Seleksi parent dan menghasilkan populasi baru
            List<Individual> newPopulation = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                // Seleksi parent secara acak menggunakan metode turnamen.
                Individual parent1 = Selection.selectParent(population);
                Individual parent2 = Selection.selectParent(population);

                // Lakukan crossover untuk membuat anak baru.
                Individual child = GeneticOperators.crossover(parent1, parent2, initialGrid, N);

                // Lakukan mutasi untuk menambah variasi pada anak.
                GeneticOperators.mutate(child, initialGrid, mutationRate, N);

                // Tambahkan anak ke dalam populasi baru.
                newPopulation.add(child);
            }

            // Perbarui populasi dengan generasi baru.
            population = newPopulation;
        }

        // Jika solusi tidak ditemukan dalam batas generasi, kembalikan solusi terbaik yang ditemukan.
        System.out.println("No solution found within generation limit. Returning best solution found.");
        return bestOverall.grid;
    }
}
