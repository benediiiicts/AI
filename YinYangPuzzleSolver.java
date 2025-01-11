import java.util.*;

public class YinYangPuzzleSolver {
    private int N;
    private final Random random = new Random();

    public YinYangPuzzleSolver(int gridSize) {
        this.N = gridSize;
    }

    public int[][] solvePuzzle(int[][] initialGrid) {
        int populationSize = 100;
        int generations = 1000;
        double mutationRate = 0.2;

        // Inisialisasi populasi awal
        List<Individual> population = PopulationInitializer.initialize(initialGrid, populationSize, N);

        Individual bestOverall = null;

        for (int generation = 0; generation < generations; generation++) {
            // Hitung fitness setiap individu
            FitnessEvaluator.evaluate(population, initialGrid, N);

            // Seleksi individu terbaik (elitism)
            population.sort(Comparator.comparingInt(ind -> ind.fitness));
            Individual best = population.get(0);

            if (bestOverall == null || best.fitness < bestOverall.fitness) {
                bestOverall = best;
            }

            // Jika solusi ditemukan
            if (best.fitness == 0) {
                System.out.println("Solution found in generation " + generation);
                return best.grid;
            }

            // Seleksi parent dan menghasilkan populasi baru
            List<Individual> newPopulation = new ArrayList<>();
            for (int i = 0; i < populationSize; i++) {
                Individual parent1 = Selection.selectParent(population);
                Individual parent2 = Selection.selectParent(population);
                Individual child = GeneticOperators.crossover(parent1, parent2, initialGrid, N);
                GeneticOperators.mutate(child, initialGrid, mutationRate, N);
                newPopulation.add(child);
            }

            population = newPopulation;
        }

        System.out.println("No solution found within generation limit. Returning best solution found.");
        return bestOverall.grid;
    }
}