
// Kelas utama Main untuk membaca puzzle dari file, memecahkan puzzle, dan menulis hasilnya ke file
import java.io.*;
import java.util.*;

public class Main {
    // Fungsi utama yang dijalankan saat program dieksekusi
    public static void main(String[] args) {
        try {
            // Membaca input puzzle dari file "inputAlvin.txt"
            List<int[][]> puzzles = readPuzzlesFromFile("inputAlvin.txt");

            // Membuat file output untuk menulis hasil solusi puzzle
            BufferedWriter writer = new BufferedWriter(new FileWriter("outputAlvin.txt"));

            // Proses setiap puzzle
            int puzzleNumber = 1; // Menandai nomor puzzle
            for (int[][] initialGrid : puzzles) {
                // Membuat objek solver untuk memecahkan puzzle
                YinYangPuzzleSolver solver = new YinYangPuzzleSolver(initialGrid.length);
                // Menyelesaikan puzzle dan mendapatkan solusi
                int[][] solution = solver.solvePuzzle(initialGrid);

                // Menulis hasil ke file output
                writer.write("Random Seed: " + GeneratorRandom.getSeed() + "\n");
                writer.write("Puzzle " + puzzleNumber + " Solution:\n");

                // Menulis solusi puzzle dalam bentuk grid
                for (int[] row : solution) {
                    for (int cell : row) {
                        writer.write(cell + " "); // Menulis setiap sel grid
                    }
                    writer.newLine(); // Pindah ke baris baru setelah setiap baris grid
                }
                writer.newLine(); // Pindah ke baris baru setelah setiap puzzle
                puzzleNumber++; // Menambah nomor puzzle
            }

            // Menutup file writer setelah semua puzzle diproses
            writer.close();
            // Menampilkan pesan ke layar bahwa hasil telah ditulis ke file output
            System.out.println("Solutions written to outputAlvin.txt");
        } catch (IOException e) {
            // Menangani kemungkinan error dalam proses input/output
            e.printStackTrace();
        }
    }

    // Fungsi untuk membaca puzzle dari file
    private static List<int[][]> readPuzzlesFromFile(String fileName) throws IOException {
        List<int[][]> puzzles = new ArrayList<>(); // Menyimpan semua puzzle
        BufferedReader reader = new BufferedReader(new FileReader(fileName)); // Membaca file input
        String line;

        // Membaca baris per baris file input
        while ((line = reader.readLine()) != null) {
            // Mengabaikan baris kosong atau baris yang tidak relevan
            if (line.trim().isEmpty() || !line.startsWith("Input")) {
                continue;
            }

            try {
                // Parsing ukuran grid dari baris "Input NxN:"
                int gridSize = Integer.parseInt(line.split("x")[0].split(" ")[1]);
                int[][] grid = new int[gridSize][gridSize]; // Membuat grid kosong sesuai ukuran

                // Baca baris-baris berikutnya untuk mengisi grid
                for (int i = 0; i < gridSize; i++) {
                    line = reader.readLine(); // Baca baris berikutnya
                    if (line == null || line.trim().isEmpty()) {
                        // Jika baris hilang atau kosong, lemparkan exception
                        throw new IOException("Puzzle grid is incomplete or missing rows.");
                    }

                    // Memisahkan nilai-nilai sel berdasarkan spasi
                    String[] cells = line.trim().split("\\s+");
                    if (cells.length != gridSize) {
                        // Jika jumlah elemen dalam baris tidak sesuai ukuran grid, lemparkan exception
                        throw new IOException("Puzzle grid row length does not match the expected size.");
                    }

                    // Mengisi grid dengan nilai dari input
                    for (int j = 0; j < gridSize; j++) {
                        grid[i][j] = Integer.parseInt(cells[j]);
                    }
                }

                // Menambahkan grid yang telah dibaca ke dalam daftar puzzle
                puzzles.add(grid);

            } catch (NumberFormatException | IOException e) {
                // Menangani error jika parsing atau pembacaan grid gagal
                throw new IOException("Error parsing puzzle: " + e.getMessage());
            }
        }

        // Menutup reader setelah selesai membaca file
        reader.close();
        // Mengembalikan daftar puzzle yang telah dibaca
        return puzzles;
    }
}