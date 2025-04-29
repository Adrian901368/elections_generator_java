package Characters;

/**
 * Trieda predstavujuca volica typu 1.
 */
public class VoterType1 extends Voter {

    /**
     * Konstruktor pre vytvorenie volica typu 1 s preddefinovanymi prijatymi slubmi.
     * @param record Zaznam o volicovi
     */
    public VoterType1(Record record) {
        super(record);
        addAcceptedPromise("Pozdvihnutie minimalnej mzdy"); // Pridavanie prijatych slubov
        addAcceptedPromise("Vystavba novych parkov"); // Pridavanie prijatych slubov
        addAcceptedPromise("Spriemernenie vyhod pre vsetky triedy"); // Pridavanie prijatych slubov
        addAcceptedPromise("Podpora vesmirneho sportu"); // Pridavanie prijatych slubov
    }
}
