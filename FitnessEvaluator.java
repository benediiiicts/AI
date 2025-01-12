import java.util.*;

public class FitnessEvaluator {
    public static void evaluate(List<Individual> population, int[][] initialGrid, int N) {
        for (Individual individual : population) {
            // Hitung fitness untuk setiap individu dan simpan di atribut fitness.
            individual.fitness = calculateFitness(individual.grid, initialGrid, N);
        }
    }

    private static int calculateFitness(int[][] grid, int[][] initialGrid, int N) {
        int violations = 0;

        // Cek aturan "tidak ada kotak 2x2 dengan warna sama"
        for (int r = 0; r < N - 1; r++) {
            for (int c = 0; c < N - 1; c++) {
                if (grid[r][c] == grid[r + 1][c] &&     // Warna di sel bawah.
                    grid[r][c] == grid[r][c + 1] &&     // Warna di sel kanan.
                    grid[r][c] == grid[r + 1][c + 1]) { // Warna di sel diagonal kanan bawah.
                    violations++; // Tambahkan pelanggaran jika ditemukan kotak 2x2 dengan warna sama.
                }
            }
        }

        // Tambahkan penalti untuk konektivitas warna
        violations += calculateConnectivityPenalty(grid, N);

        return violations;
    }

    private static int calculateConnectivityPenalty(int[][] grid, int N) {
        boolean[][] visited = new boolean[N][N]; // Untuk melacak sel yang sudah diperiksa.
        int penalty = 0;

        // Cari setiap warna
        for (int color = 1; color <= 2; color++) {
            int clusters = 0; // Jumlah cluster terpisah untuk warna yang sedang diperiksa.
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // Jika sel memiliki warna yang sedang diperiksa dan belum dikunjungi.
                    if (grid[r][c] == color && !visited[r][c]) {
                        clusters++;
                        markConnected(grid, visited, r, c, color, N);
                    }
                }
            }
            // Penalti jika ada lebih dari satu cluster untuk warna tertentu.
            if (clusters > 1) {
                penalty += clusters - 1;
            }
        }

        return penalty;
    }

    private static void markConnected(int[][] grid, boolean[][] visited, int startRow, int startCol, int color, int N) {
        Queue<int[]> queue = new LinkedList<>(); // Antrian untuk BFS.
        queue.add(new int[]{startRow, startCol}); // Tambahkan sel awal ke antrian.
        visited[startRow][startCol] = true; // Tandai sel awal sebagai dikunjungi.

        // Arah pergerakan (atas, bawah, kiri, kanan).
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Lakukan BFS untuk menandai semua sel terhubung.
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];

            // Periksa semua arah.
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Periksa validitas posisi baru dan apakah memiliki warna yang sama.
                if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && !visited[newRow][newCol] && grid[newRow][newCol] == color) {
                    visited[newRow][newCol] = true; // Tandai sebagai dikunjungi.
                    queue.add(new int[]{newRow, newCol}); // Tambahkan ke antrian.
                }
            }
        }
    }
}
