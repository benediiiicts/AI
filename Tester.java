import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input ukuran
        int ukuran = sc.nextInt();
        
        //representasi papan
        char[][] arrPapan = new char[ukuran][ukuran];

        //input isi papan
        for(int i = 0; i < ukuran; i++) {
            String input = sc.next();
            for(int j = 0; j < ukuran; j++) {
                arrPapan[i][j] = input.charAt(j);
            }
        }

        //buat obj solver sekaligus encoding board
        YinYangSolver ys = new YinYangSolver(arrPapan, ukuran);

        ys.solve();
        sc.close();
    }
}
