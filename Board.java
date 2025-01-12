public class Board {
    private int[] encodingPapan;
    private int ukuran;

    public Board(int[][] arrPapan, int ukuran) {
        this.ukuran = ukuran;
        this.encodingPapan = new int[ukuran * ukuran];

        // Isi encoding
        for (int i = 0; i < ukuran; i++) {
            for (int j = 0; j < ukuran; j++) {
                this.encodingPapan[(i * ukuran) + j] = arrPapan[i][j];
            }
        }
    }

    public int[] getEncodingPapan() {
        return encodingPapan;
    }

    public void setPapan(int idx, int value) {
        encodingPapan[idx] = value;
    }

    public int calculateFitness() {
        int fitness = 0;

        // Rule 1: Penalti untuk area 2x2 dengan warna sama
        for (int i = 0; i < ukuran - 1; i++) {
            for (int j = 0; j < ukuran - 1; j++) {
                int idx = i * ukuran + j;
                int a = encodingPapan[idx];
                int b = encodingPapan[idx + 1];
                int c = encodingPapan[idx + ukuran];
                int d = encodingPapan[idx + ukuran + 1];
                if (a == b && b == c && c == d && a != 0) {
                    fitness -= 5; // Penalti besar
                }
            }
        }

        // Rule 2: Semua hitam (1) harus terhubung
        fitness += checkConnectivity(1);

        // Rule 3: Semua putih (2) harus terhubung
        fitness += checkConnectivity(2);

        // Penalti untuk sel kosong
        for (int val : encodingPapan) {
            if (val == 0) {
                fitness -= 1; // Penalti kecil untuk setiap sel kosong
            }
        }

        return fitness;
    }

    private int checkConnectivity(int target) {
        boolean[] visited = new boolean[ukuran * ukuran];
        int connectedSize = 0;

        // Cari sel pertama dengan nilai `target`
        for (int i = 0; i < ukuran * ukuran; i++) {
            if (encodingPapan[i] == target) {
                connectedSize = dfs(i, target, visited);
                break;
            }
        }

        // Jika semua sel dengan nilai `target` terhubung, berikan poin
        int totalCount = count(target);
        return (connectedSize == totalCount) ? 10 : -10;
    }

    private int dfs(int idx, int target, boolean[] visited) {
        if (idx < 0 || idx >= ukuran * ukuran || visited[idx] || encodingPapan[idx] != target) {
            return 0;
        }

        visited[idx] = true;
        int x = idx / ukuran, y = idx % ukuran;
        int size = 1;

        // Periksa tetangga
        if (x > 0) size += dfs(idx - ukuran, target, visited); // Atas
        if (x < ukuran - 1) size += dfs(idx + ukuran, target, visited); // Bawah
        if (y > 0) size += dfs(idx - 1, target, visited); // Kiri
        if (y < ukuran - 1) size += dfs(idx + 1, target, visited); // Kanan

        return size;
    }

    private int count(int target) {
        int count = 0;
        for (int val : encodingPapan) {
            if (val == target) count++;
        }
        return count;
    }
}