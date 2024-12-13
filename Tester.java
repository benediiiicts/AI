import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ukuran = sc.nextInt();

        char[][] arrPapan = new char[ukuran][ukuran];
        YinYangSolver ys = new YinYangSolver(arrPapan);

        ys.solve();
    }
}
