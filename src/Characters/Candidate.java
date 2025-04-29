package Characters;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezentuje kandidata volebneho procesu.
 */
public class Candidate implements Votes { //dorobit agregaciu

    private String name; // Nazov kandidata
    private List<String> promises; // Zoznam slubov kandidata
    private int votes; // Pocet hlasov, ktore kandidat obdrzal

    /**
     * Konstruktor pre vytvorenie kandidata s danym menom.
     * @param name Meno kandidata
     */
    public Candidate(String name) {
        this.name = name;
        this.promises = new ArrayList<>();
        this.votes = 0;
    }

    /**
     * Pridava novy slub kandidata.
     * @param promise Novy slub kandidata
     */
    public void addPromise(String promise) {
        promises.add(promise);
    }

    /**
     * Vrati meno kandidata.
     * @return Meno kandidata
     */
    public String getName() {
        return name;
    }

    /**
     * Vrati zoznam slubov kandidata.
     * @return Zoznam slubov kandidata
     */
    public List<String> canPromise() {
        return promises;
    }

    /**
     * Zvysi pocet hlasov kandidata na zaklade informacii o volici.
     * @param voter Volic, ktory hlasuje
     */
    @Override
    public void increaseVote(Voter voter) {
        if (voter instanceof VoterType1Lower) {
            this.votes ++;
            if (voter.getRecord().getMoney() > 80000){ //ak maju na ucte viac ako 80000 vaha hlasu je  2x vacsia
                this.votes++;
            }
        } else if (voter instanceof VoterType1Higher) {
            this.votes += 2;
            if (voter.getRecord().getMoney() > 80000){
                this.votes += 2;
            }
        } else if (voter instanceof VoterType2Lower) {
            this.votes += 3;
            if (voter.getRecord().getMoney() >80000){
                this.votes += 3;
            }
        } else if (voter instanceof VoterType2Higher) {
            this.votes += 4;
            if (voter.getRecord().getMoney() > 80000){ //ak maju na ucte viac ako 80000 vaha hlasu je 2x vacsia
                this.votes += 4;
            }
        } else if (voter instanceof VoterType3Lower) {
            this.votes += 5;
            if (voter.getRecord().getMoney() > 80000){
                this.votes += 5;
            }
        } else if (voter instanceof VoterType3Higher) {
            this.votes += 6;
            if (voter.getRecord().getMoney() >80000){
                this.votes += 6;
            }
        } else if (voter instanceof VoterVisitorFromEarth){
            this.votes++;
            this.votes++;
        }
    }

    /**
     * Vrati pocet hlasov, ktore kandidat obdrzal.
     * @return Pocet hlasov
     */
    @Override
    public int getVotes() {
        return this.votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * Vrati textovu reprezentaciu kandidata.
     * @return Textova reprezentacia kandidata
     */
    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", promises=" + promises +
                ", votes=" + votes +
                '}';
    }

    public void resetVotes() {
        this.votes = 0; // Resetting the vote count to zero
    }


}
