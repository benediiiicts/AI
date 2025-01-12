import java.util.*;
public class YinYangSolver {
    private int ukuran;

    public YinYangSolver(int[][] arrPapan, int ukuran) {
        this.ukuran = ukuran;
    }

    public void solve() {
        int populasiSize = 100;
        List<Board> populasi = new ArrayList<>();
        Random rand = new Random();

        // Inisialisasi populasi
        for (int i = 0; i < populasiSize; i++) {
            int[][] arr = randomBoard();
            populasi.add(new Board(arr, ukuran));
        }

        // Iterasi algoritma genetik
        for (int iterasi = 1; iterasi <= 500; iterasi++) {
            // Evaluasi fitness
            populasi.sort(Comparator.comparingInt(Board::calculateFitness).reversed());

            // Berhenti jika solusi ditemukan
            if (populasi.get(0).calculateFitness() >= 0) {
                printBoard(populasi.get(0));
                return;
            }

            // Seleksi & Crossover
            List<Board> nextGen = new ArrayList<>();
            for (int i = 0; i < populasiSize / 2; i++) {
                Board parent1 = populasi.get(rand.nextInt(populasiSize / 2));
                Board parent2 = populasi.get(rand.nextInt(populasiSize / 2));
                nextGen.add(crossover(parent1, parent2));
            }

            // Mutasi
            for (Board child : nextGen) {
                if (rand.nextDouble() < 0.1) {
                    mutate(child);
                }
            }

            populasi = nextGen;
        }

        System.out.println("Tidak ditemukan solusi.");
    }

    private int[][] randomBoard() {
        int[][] board = new int[ukuran][ukuran];
        Random rand = new Random();
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < ukuran; j++) {
                board[i][j] = rand.nextInt(3); // 0, 1, atau 2
            }
        }
        return board;
    }

    private Board crossover(Board parent1, Board parent2) {
        int[][] childArr = new int[ukuran][ukuran];
        Random rand = new Random();
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < ukuran; j++) {
                childArr[i][j] = rand.nextBoolean()
                        ? parent1.getEncodingPapan()[i * ukuran + j]
                        : parent2.getEncodingPapan()[i * ukuran + j];
            }
        }
        return new Board(childArr, ukuran);
    }

    private void mutate(Board board) {
        Random rand = new Random();
        int idx = rand.nextInt(ukuran * ukuran);
        board.setPapan(idx, rand.nextInt(3)); // 0, 1, atau 2
    }

    private void printBoard(Board board) {
        int[] encoding = board.getEncodingPapan();
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < ukuran; j++) {
                System.out.print(encoding[i * ukuran + j] + " ");
            }
            System.out.println();
        }
    }
}