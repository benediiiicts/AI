import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta ukuran grid
        System.out.print("Masukkan ukuran grid (NxN): ");
        int gridSize = scanner.nextInt();

        // Meminta input grid awal
        int[][] initialGrid = new int[gridSize][gridSize];
        System.out.println("Masukkan grid awal (0 untuk kosong, 1 untuk hitam, 2 untuk putih):");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                initialGrid[i][j] = scanner.nextInt();
            }
        }

        YinYangPuzzleSolver solver = new YinYangPuzzleSolver(gridSize);
        int[][] solution = solver.solvePuzzle(initialGrid);

        System.out.println("Solution:");
        GridPrinter.printGrid(solution);
        scanner.close();
    }
}
