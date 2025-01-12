import java.util.*;

public class Selection {
    // Variabel random digunakan untuk memilih individu secara acak dari populasi.
    private static final Random random = new Random();

    public static Individual selectParent(List<Individual> population) {
        int tournamentSize = 5; // Ukuran turnamen (jumlah individu yang dipilih secara acak untuk kompetisi).
        List<Individual> tournament = new ArrayList<>(); // Daftar untuk menyimpan peserta turnamen.
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(random.nextInt(population.size())));
        }

        // Mencari individu dengan nilai fitness terendah di turnamen
        // (fitness rendah lebih baik).
        return Collections.min(tournament, Comparator.comparingInt(ind -> ind.fitness));
    }
}