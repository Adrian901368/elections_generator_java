package Characters;

/**
 * Trieda predstavujuca sľuby kandidstov.
 */
public class Promises {
    private String name; // Nazov slubu

    /**
     * Konstruktor pre vytvorenie slubu s danym nazvom.
     * @param name Názov slubu
     */
    public Promises(String name) {
        this.name = name;
    }

    /**
     * Vrati textovu reprezentaciu slubu.
     * @return Textova reprezentacia slubu
     */
    @Override
    public String toString() {
        return name;
    }
}
