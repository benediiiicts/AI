// Kelas GeneratorRandom untuk menghasilkan nilai acak menggunakan seed tetap
import java.util.Random;

public class GeneratorRandom {
    // Seed yang digunakan untuk Random
    private static final long seed = 178456;
    // Variabel static untuk menyimpan instance Random
    private static Random random = null;

    // Fungsi untuk mendapatkan instance Random
    public static Random getRandom() {
        // Jika random belum diinisialisasi, buat instance baru dengan seed yang telah
        // ditentukan
        if (random == null) {
            random = new Random(seed);
        }

        // Kembalikan instance Random
        return random;
    }

    // Fungsi untuk mendapatkan seed yang digunakan
    public static long getSeed() {
        return seed;
    }
}
