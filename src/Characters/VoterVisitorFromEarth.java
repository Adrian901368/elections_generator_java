package Characters;

public class VoterVisitorFromEarth extends Voter {
    /**
     * Konstruktor pre vytvorenie volica so zadanym zaznamom.
     *
     * @param record Zaznam o volicovi
     */
    public VoterVisitorFromEarth(Record record) { //tento voter simuluje cloveka zo sebe ktory prisiel volit ako navstevnik
        super(record);
    }
}
