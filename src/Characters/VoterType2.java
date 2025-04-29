package Characters;

/**
 * Trieda predstavujuca volica typu 2.
 */
public class VoterType2 extends Voter {

    /**
     * Konstruktor pre vytvorenie volica typu 2 s preddefinovanymi prijatymi slubmi.
     * @param record Zaznam o volicovi
     */
    public VoterType2(Record record) {
        super(record);
        addAcceptedPromise("Posunutie hranice predaja latok pre dosp."); // Pridavanie prijatych slubov
        addAcceptedPromise("Zvysenie dotacii pre skoly"); // Pridavanie prijatych slubov
    }
}
