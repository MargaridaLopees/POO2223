/**
 * Classe Gossiper - extends the abstract class Person.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */
package engine;

public class Gossiper extends PersonClass{

    /**
     * Gossipers class constructor.
     * Creates a new person gossiper.
     *
     * @param name the person's name.
     * @pre name != null
     */
    public Gossiper(String name) {
        super(name);
    }

    @Override
    protected Gossip getNextGossip() {
        return getGossips().get(0);
    }

    @Override
    public int howManyGossipToShare(){
        return 3;
    }

}
