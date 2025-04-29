package Characters;

/**
 * Trieda predstavujúca volica typu 3.
 */
public class VoterType3 extends Voter {

    /**
     * Konstruktor pre vytvorenie volica typu 3 s preddefinovanymi prijatymi slubmi.
     * @param record Zaznam o volicovi
     */
    public VoterType3(Record record) {
        super(record);
        addAcceptedPromise("Zjednotenie jazyka"); // Pridavanie prijatych sľubov
        addAcceptedPromise("Rychlejsi vyvoj"); // Pridavanie prijatych sľubov
    }
}
