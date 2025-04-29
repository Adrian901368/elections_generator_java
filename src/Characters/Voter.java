package Characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Trieda reprezentujuca volica.
 */
public class Voter {
    private List<String> acceptedPromises; // Zoznam prijatych slubov
    private Record record; // Záznam o volicovi
    /**
     * Konstruktor pre vytvorenie volica so zadanym zaznamom.
     * @param record Zaznam o volicovi
     */
    public Voter(Record record) {
        this.acceptedPromises = new ArrayList<>();
        this.record = record;
    }

    /**
     * Vrati zaznam o volicovi.
     * @return Zaznam o volicovi
     */
    public Record getRecord() {
        return record;
    }

    /**
     * Prida prijaty slub do zoznamu prijatych slubov volica.
     * @param promise Prijaty slub
     */
    public void addAcceptedPromise(String promise) {
        acceptedPromises.add(promise);
    }

    /**
     * Vráti zzoznam prijatych slubov volica.
     * @return Zoznam prijatych slubov
     */
    public List<String> getAcceptedPromises() {
        return acceptedPromises;
    }

    /**
     * Hlasuje za kandidata zo zoznamu kandidatov na zaklade prijatych slubov.
     * @param candidates Zoznam kandidatov
     * @return Kandidat, za ktoreho volic hlasoval, alebo null ak nie je ziadny vhodny kandidat
     */
    public Candidate vote(List<Candidate> candidates) {
        List<Candidate> eligibleCandidates = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (isCandidateGood(candidate)) {
                eligibleCandidates.add(candidate);
            }
        }
        if (eligibleCandidates.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return eligibleCandidates.get(random.nextInt(eligibleCandidates.size()));
    }

    /**
     * Overi, ci je kandidat vhodny na zaklade prijatych slubov volica.
     * @param candidate Kandidat na overenie
     * @return true, ak je kandidat vhodny, inak false
     */
    private boolean isCandidateGood(Candidate candidate) {
        for (String promise : candidate.canPromise()) {
            if (acceptedPromises.contains(promise)) {
                return true;
            }
        }
        return false;
    }
}
