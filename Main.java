import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Baca input dari file
            List<int[][]> puzzles = readPuzzlesFromFile("inputAlvin.txt");

            // Buat file output untuk menyimpan hasil
            BufferedWriter writer = new BufferedWriter(new FileWriter("outputAlvin.txt"));

            // Proses setiap puzzle
            int puzzleNumber = 1;
            for (int[][] initialGrid : puzzles) {
                YinYangPuzzleSolver solver = new YinYangPuzzleSolver(initialGrid.length);
                int[][] solution = solver.solvePuzzle(initialGrid);

                // Tulis hasil ke file output
                writer.write("Puzzle " + puzzleNumber + " Solution:\n");
                for (int[] row : solution) {
                    for (int cell : row) {
                        writer.write(cell + " ");
                    }
                    writer.newLine();
                }
                writer.newLine();
                puzzleNumber++;
            }

            writer.close();
            System.out.println("Solutions written to outputAlvin.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk membaca puzzle dari file input
    private static List<int[][]> readPuzzlesFromFile(String fileName) throws IOException {
        List<int[][]> puzzles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            // Abaikan baris kosong atau baris yang tidak relevan
            if (line.trim().isEmpty() || !line.startsWith("Input")) {
                continue;
            }

            try {
                // Parsing ukuran grid dari baris "Input NxN:"
                int gridSize = Integer.parseInt(line.split("x")[0].split(" ")[1]);
                int[][] grid = new int[gridSize][gridSize];

                // Baca baris-baris berikutnya untuk mengisi grid
                for (int i = 0; i < gridSize; i++) {
                    line = reader.readLine();
                    if (line == null || line.trim().isEmpty()) {
                        throw new IOException("Puzzle grid is incomplete or missing rows.");
                    }

                    String[] cells = line.trim().split("\\s+");
                    if (cells.length != gridSize) {
                        throw new IOException("Puzzle grid row length does not match the expected size.");
                    }

                    for (int j = 0; j < gridSize; j++) {
                        grid[i][j] = Integer.parseInt(cells[j]);
                    }
                }

                puzzles.add(grid);

            } catch (NumberFormatException | IOException e) {
                throw new IOException("Error parsing puzzle: " + e.getMessage());
            }
        }

        reader.close();
        return puzzles;
    }
}
