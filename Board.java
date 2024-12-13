import java.util.Arrays;

public class Board {
    private char[][] papan;

    public Board(char[][] arrPapan) {
        this.papan = arrPapan;
    }

    public void setPapan(int i, int j, char a) {
        this.papan[i][j] = a;
    }
}