public class Board {
    private char[] encodingPapan;
    private int ukuran;
    private Board parent1;
    private Board parent2;
    private Board child;

    public Board(char[][] arrPapan, int ukuran) {
        this.ukuran = ukuran;
        this.encodingPapan = new char[ukuran*ukuran];
        this.parent1 = null;
        this.parent2 = null;
        this.child = null;

        //isi encoding
        for(int i = 0; i < ukuran; i++) {
            for(int j = 0; j < ukuran; j++) {
                this.encodingPapan[(i*ukuran)+j] = arrPapan[i][j];
            }
        }
    }

    public void setPapan(int idx, char a) {
        encodingPapan[idx] = a;
    }

    public int calculateFitness() {
        //todo
        return 0;
    }
}