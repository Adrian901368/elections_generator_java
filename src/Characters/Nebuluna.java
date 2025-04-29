package Characters;

/**
 * Trieda predstavujuca konkretnu postavu Nebuluna.
 */
public class Nebuluna extends Candidate {

    /**
     * Konstruktor pre vytvorenie postavy Nebuluna s preddefinovanymi slubmi.
     */
    public Nebuluna() {
        super("Nebuluna");
        addPromise(String.valueOf(new Promises("Spriemernenie vyhod pre vsetky triedy"))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Vystavba novych parkov"))); // Pridávanie sľubov
        addPromise(String.valueOf(new Promises("Podpora vesmirneho sportu"))); // Pridávanie sľubov
    }
}
