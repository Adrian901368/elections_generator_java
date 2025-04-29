package Characters;


/**
 * Kandidat s nazvom Zodiacalia a jeho sluby.
 */
public class Zodiacalia extends Candidate {
    /**
     * Konstruktor pre vytvorenie noveho kandidata Zodiacalia s prednastavenymi slubmi.
     */
    public Zodiacalia() {
        super("Zodiacalia");
        addPromise(String.valueOf(new Promises("Pozdvihnutie minimálnej mzdy")));
        addPromise(String.valueOf(new Promises("Spriemernenie vyhod pre vsetky triedy")));
        addPromise(String.valueOf(new Promises("Zjednotenie jazyka")));
    }
}
