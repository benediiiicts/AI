import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input ukuran
        int ukuran = sc.nextInt();

        // Representasi papan
        int[][] arrPapan = new int[ukuran][ukuran];

        // Input isi papan
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < ukuran; j++) {
                arrPapan[i][j] = sc.nextInt(); // 0 = kosong, 1 = hitam, 2 = putih
            }
        }
        System.out.println();

        // Buat solver
        YinYangSolver ys = new YinYangSolver(arrPapan, ukuran);

        ys.solve();
        sc.close();
    }
}
