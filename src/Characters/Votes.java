package Characters;

/**
 * Rozhranie definujuce spravanie hlasovania.
 */
public interface Votes {
    /**
     * Zvysi pocet hlasov na zaklade typu volica.
     * @param voter Volic, ktory hlasuje
     */
    void increaseVote(Voter voter);

    /**
     * Vrati pocet hlasov.
     * @return Pocet hlasov
     */
    int getVotes();
}
