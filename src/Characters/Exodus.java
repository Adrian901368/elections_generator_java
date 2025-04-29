package Characters;

/**
 * Trieda predstavujuca konkretneho kandidata Exodusa.
 */
public class Exodus extends Candidate {

    /**
     * Konstruktor pre vytvorenie kandidata Exodusa s preddefinovanymi slubmi.
     */
    public Exodus() {
        super("Exodus");
        addPromise(String.valueOf(new Promises("Zjednotenie jazyka"))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Zvysenie dotacii pre skoly"))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Podpora vesmirneho sportu"))); // Pridávanie sľubov
    }
}
