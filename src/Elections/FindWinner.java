package Elections;

import Characters.Candidate;

import java.util.List;

/**
 * trieda na najdenie vitaza medzi zoznamom kandidatov na zaklade ich hlasov.
 */
public class FindWinner {

    /**
     * Najde kandidata s najvyssim poctom hlasov zo zadaneho zoznamu kandidatov.
     *
     * @param candidates Zoznam kandidatov, medzi ktorymi sa ma najst vitaz.
     * @return Kandidat s najvyssim poctom hlasov. Ak je zoznam prazdny, vrati null.
     */
    public static Candidate findWinner(List<Candidate> candidates) {
        if (candidates.isEmpty()) {
            return null;
        }

        Candidate winner = candidates.get(0);
        for (Candidate candidate : candidates) {
            if (candidate.getVotes() > winner.getVotes()) {
                winner = candidate;
            }
        }
        return winner;
    }
}
