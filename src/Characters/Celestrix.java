package Characters;

/**
 * Trieda predstavujuca konkretneho kandidata Celestrixa.
 */
public class Celestrix extends Candidate {

    /**
     * Konstruktor pre vytvorenie kandidata Celestrixa s preddefinovanymi slubmi.
     */
    public Celestrix() {
        super("Celestrix");
        addPromise(String.valueOf(new Promises("Posunutie hranice predaja latok pre dosp."))); // Pridavanie slubov
        addPromise(String.valueOf(new Promises("Rychlejsi vyvoj"))); // Pridavanie slubov
        addPromise(String.valueOf(new Promises("Pozdvihnutie minimalnej mzdy"))); // Pridavanie slubov
    }
}
