package Elections;

import Characters.*;
import Characters.Record;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Tato trieda reprezentuje volebny proces.
 */
public class Elections {
    private final List<Candidate> candidates = new ArrayList<>();
    private String winnerName;

    /**
     * Spusti volebny proces s danym poctom volicov kazdého typu a zobrazi vysledky.
     *
     * @param nVoters1Lower   Počet voličov typu 1 (nízky)
     * @param nVoters1Higher  Počet voličov typu 1 (vysoký)
     * @param nVoters2Lower   Počet voličov typu 2 (nízky)
     * @param nVoters2Higher  Počet voličov typu 2 (vysoký)
     * @param nVoters3Lower   Počet voličov typu 3 (nízky)
     * @param nVoters3Higher  Počet voličov typu 3 (vysoký)
     * @param resultsArea     TextArea pre zobrazenie vysledkov
     */
    public void runElection(int nVoters1Lower, int nVoters1Higher, int nVoters2Lower, int nVoters2Higher,
                            int nVoters3Lower, int nVoters3Higher, TextArea resultsArea) {
        candidates.clear();
        resetCandidateVotes();
        initializeCandidates();

        StringBuilder electionLog = new StringBuilder();
        simulateElection(nVoters1Lower, nVoters1Higher, nVoters2Lower, nVoters2Higher,
                nVoters3Lower, nVoters3Higher, electionLog);

        displayResults(electionLog, resultsArea);
        updateCandidateVotes();
        findWinner();
    }

    /**
     * Inicializuje kandidatov pre voľby.
     */
    private void initializeCandidates() {
        candidates.add(new Exodus());
        candidates.add(new Celestrix());
        candidates.add(new Nebuluna());
        candidates.add(new Primus());
        candidates.add(new Zodiacalia());
    }

    /**
     * Simuluje volby s danym poctom volicov kazdeho typu.
     *
     * @param nVoters1Lower   Pocet volicov typu 1 (nizky)
     * @param nVoters1Higher  Pocet voličov typu 1 (vysoky)
     * @param nVoters2Lower   Pocet voličov typu 2 (nizky)
     * @param nVoters2Higher  Pocet voličov typu 2 (vysoky)
     * @param nVoters3Lower   Pocet voličov typu 3 (nizky)
     * @param nVoters3Higher  Pocet voličov typu 3 (vysoky)
     * @param electionLog     StringBuilder pre zaznam volebnych udalosti
     */
    private void simulateElection(int nVoters1Lower, int nVoters1Higher, int nVoters2Lower, int nVoters2Higher,
                                  int nVoters3Lower, int nVoters3Higher, StringBuilder electionLog) {
        for (int i = 0; i < nVoters1Lower; i++) {
            voteAndPrint(new VoterType1Lower(), electionLog);
        }
        for (int i = 0; i < nVoters1Higher; i++) {
            voteAndPrint(new VoterType1Higher(), electionLog);
        }
        for (int i = 0; i < nVoters2Lower; i++) {
            voteAndPrint(new VoterType2Lower(), electionLog);
        }
        for (int i = 0; i < nVoters2Higher; i++) {
            voteAndPrint(new VoterType2Higher(), electionLog);
        }
        for (int i = 0; i < nVoters3Lower; i++) {
            voteAndPrint(new VoterType3Lower(), electionLog);
        }
        for (int i = 0; i < nVoters3Higher; i++) {
            voteAndPrint(new VoterType3Higher(), electionLog);
        }
    }

    /**
     * Vykona hlasovanie volica a zaznamena volebny zaznam.
     *
     * @param voter        Volic, ktory hlasuje
     * @param electionLog  StringBuilder pre zaznam volebnych udalosti
     */
    private void voteAndPrint(Voter voter, StringBuilder electionLog) {
        Record record = voter.getRecord();
        if (record != null) {
            electionLog.append(record.getName()).append(" ").append(record.getSurname())
                    .append(" ID: ").append(record.getId()).append(", Money: ").append(record.getMoney()).append(" $$$ ");
        } else {
            electionLog.append("Error: Voter record is null");
            return;
        }

        electionLog.append(getVoterTypeString(voter)).append(" is voting for: ");

        Candidate candidate = voter.vote(candidates);
        if (candidate != null) {
            electionLog.append(candidate.getName());
            candidate.increaseVote(voter);
        } else {
            electionLog.append("Error: Candidate is null\n");
        }

        if (record.getMoney() > 80000) {
            electionLog.append(" -> double vote value");
        }

        electionLog.append("\n");
    }

    /**
     * Vrati retazec reprezentujuci typ volica.
     *
     * @param voter  Volic
     * @return       Retazec reprezentujuci typ volica
     */
    private String getVoterTypeString(Voter voter) {
        if (voter instanceof VoterType1Lower) {
            return "( Type 1 Voter Lower)";
        } else if (voter instanceof VoterType2Lower) {
            return "( Type 2 Voter Lower)";
        } else if (voter instanceof VoterType3Lower) {
            return "( Type 3 Voter Lower)";
        } else if (voter instanceof VoterType1Higher) {
            return "( Type 1 Voter Higher)";
        } else if (voter instanceof VoterType2Higher) {
            return "( Type 2 Voter Higher)";
        } else if (voter instanceof VoterType3Higher) {
            return "( Type 3 Voter Higher)";
        } else {
            return "";
        }
    }

    /**
     * Zobrazi vysledky volieb v danom TextArea.
     *
     * @param electionLog  StringBuilder s volebnym logom
     * @param resultsArea  TextArea pre zobrazenie vysledkov
     */
    private void displayResults(StringBuilder electionLog, TextArea resultsArea) {
        resultsArea.setText(electionLog.toString());
    }

    /**
     * Resetuje pocet hlasov kandidatov.
     */
    private void resetCandidateVotes() {
        for (Candidate candidate : candidates) {
            candidate.resetVotes();
        }
    }

    /**
     * Aktualizuje pocet hlasov kazdeho kandidata.
     */
    public void updateCandidateVotes() {
        for (Candidate candidate : candidates) {
            int votes = candidate.getVotes();
            candidate.setVotes(votes);
        }
    }

    /**
     * Najde vitaza volieb a nastavi jeho meno.
     */
    private void findWinner() {
        Candidate winner = FindWinner.findWinner(candidates);
        if (winner != null) {
            winnerName = winner.getName();
        } else {
            winnerName = "No winner"; // Ak sa žiadny víťaz nenájde, nastavíme meno na "No winner"
        }
    }

    /**
     * Najde kandidata podla mena.
     *
     * @param name  Meno kandidata
     * @return      Kandidat s danym menom alebo null, ak neexistuje
     */
    public Candidate findCandidateByName(String name) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equalsIgnoreCase(name)) {
                return candidate;
            }
        }
        return null;
    }

    /**
     * Vrati pocet hlasov pre kazdeho kandidata.
     *
     * @return  Pole s poctom hlasov pre kazdeho kandidata
     */
    public int[] getCandidateVotes() {
        int[] candidateVotes = new int[candidates.size()];
        for (int i = 0; i < candidates.size(); i++) {
            candidateVotes[i] = candidates.get(i).getVotes()/2;
        }
        return candidateVotes;
    }

    /**
     * Vrati meno vitaza volieb.
     *
     * @return  Meno vitaza volieb
     */
    public String getWinnerName() {
        return winnerName;
    }
}
