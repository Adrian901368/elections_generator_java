package Characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Trieda predstavujuca volica typu 2 s nizsim socialnym statusom.
 */
public class VoterType2Lower extends VoterType2 {

    /**
     * Konstruktor pre vytvorenie volica typu 2 s nizsim socialnym statusom s nahodnymi udajmi.
     */
    public VoterType2Lower() {
        super(new Record(getRandomName(), getRandomSurname(), getRandomId(), getRandomMoney()));
    }

    private static String getRandomName() {
        String[] names = {"Orion", "Lyra", "Nova", "Cassiopeia", "Draco", "Vega", "Astra", "Apollo", "Luna", "Sirius", "Celeste", "Phoenix", "Nebula", "Galen", "Andromeda", "Rigel", "Selene", "Cosmo", "Stella", "Orionis", "Thalia", "Atlas", "Aurora", "Titan", "Seraphina", "Artemis", "Zephyr", "Perseus", "Elara", "Oberon"};
        return names[new Random().nextInt(names.length)];
    }

    private static String getRandomSurname() {
        String[] surnames = {"Stark", "Blackwood", "Frost", "Nightingale", "Silverwind", "Starfall", "Moonstone", "Winterbourne", "Shadowcaster", "Skyward", "Sunflare", "Darkwater", "Frostfire", "Stardust", "Stormrider", "Lightbringer", "Shadowborn", "Sunwalker", "Moonshadow", "Brightstar", "Wilder", "Fireheart", "Swiftwing", "Frostbane", "Starlight", "Sunblade", "Moonflower", "Skywatcher", "Nightfall", "Dawnbreaker"};
        return surnames[new Random().nextInt(surnames.length)];
    }

    private static int getRandomId() {
        return new Random().nextInt(100000);
    }

    private static int getRandomMoney() {
        return new Random().nextInt(100000);
    }

    /**
     * Hlasuje za kandidata zo zoznamu kandidatov na zaklade prijatych slubov a zvysuje pocet hlasov vybraneho kandidata o 1.
     * @param candidates Zoznam kandidatov
     * @return Kandidat, za ktoreho volic hlasoval, alebo null ak nie je ziadny vhodny kandidat
     */
    @Override
    public Candidate vote(List<Candidate> candidates) {
        // Vyberieme kandidatov, ktori maju aspon jeden prijatelny slub pre volica typu 1
        List<Candidate> eligibleCandidates = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (isCandidateGood(candidate)) {
                eligibleCandidates.add(candidate);
            }
        }

        if (eligibleCandidates.isEmpty()) {
            return null; // Žiadni vhodní kandidáti
        }

        // Náhodný výber z vhodných kandidátov
        Candidate chosenCandidate = eligibleCandidates.get(new Random().nextInt(eligibleCandidates.size()));
        // Zvýšime počet hlasov vybraného kandidáta o 1
        chosenCandidate.increaseVote(this);
        return chosenCandidate;
    }

    /**
     * Overi, ci je kandidat vhodny na zaklade prijatych slubov volica typu 2 s nizsim socialnym statusom.
     * @param candidate Kandidat na overenie
     * @return true, ak je kandidat vhodny, inak false
     */
    private boolean isCandidateGood(Candidate candidate) {
        for (String promise : candidate.canPromise()) {
            if (getAcceptedPromises().contains(promise)) {
                return true;
            }
        }
        return false;
    }
}
