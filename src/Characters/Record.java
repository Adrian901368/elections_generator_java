package Characters;

/**
 * Trieda predstavujuca zaznam o osobe.
 */
public class Record {
    private String name;
    private String surname;
    private int id; //
    private int money; // Mnozstvo penazi na ucte volica

    /**
     * Konstruktor pre vytvorenie zaznamu s danymi udajmi.
     * @param name Meno volica
     * @param surname Priezvisko volica
     * @param id ID osoby
     * @param money mnozstvo penazi na ucte volica
     */
    public Record(String name, String surname, int id, int money) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.money = money;
    }

    /**
     * vrati meno volica.
     * @return Meno volica
     */
    public String getName() {
        return name;
    }

    /**
     * Vrati priezvisko volica.
     * @return Priezvisko volica
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Vrati ID volica.
     * @return ID volica
     */
    public int getId() {
        return id;
    }

    /**
     * mnozstvo penazi na ucte volica
     * @return mnozstvo penazi na ucte volica
     */
    public int getMoney() {
        return money;
    }
}