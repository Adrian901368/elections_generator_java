package Characters;

/**
 * Trieda predstavujuca konkretnu postavu Primus.
 */
public class Primus extends Candidate {

    /**
     * Konstruktor pre vytvorenie postavy Primus s preddefinovanymi slubmi.
     */
    public Primus() {
        super("Primus");
        addPromise(String.valueOf(new Promises("Pozdvihnutie minimalnej mzdy"))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Posunutie hranice predaja latok pre dosp."))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Podpora vesmirneho sportu"))); // Pridávanie sľubov
    }
}
