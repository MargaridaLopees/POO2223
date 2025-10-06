/**
 * Forgetful class - extends the abstratc class Person.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;

public class Forgetful extends PersonClass {

    private final int capacity;
    private static final int INDEX_LAST_KNOWN_GOSSIP = 0;
    private int oldestGossipIndex;

    private final Array<Gossip> temporalGossipOrder;

    /**
     * Forgetful class constructor.
     * It creates a new forgetful person.
     *
     * @param name     the person's name.
     * @param capacity the total number of gossips it can keep.
     * @pre name != null && capacity != null
     */
    public Forgetful(String name, int capacity) {
        super(name);
        this.capacity = capacity;
        temporalGossipOrder = new ArrayClass<>(capacity);
        oldestGossipIndex = 0;
    }

    @Override
    public void addGossip(Gossip gossip) {
        if (getKnownGossipsQuantity() >= capacity) {
            removeOldestGossip();
        }
        super.addGossip(gossip);
        temporalGossipOrder.insertLast(gossip);
    }

    /**
     * Removes the oldest gossip.
     */
    private void removeOldestGossip() {
        Gossip oldestGossip = temporalGossipOrder.get(INDEX_LAST_KNOWN_GOSSIP);
        getGossips().removeAt(getGossips().searchIndexOf(oldestGossip));
        temporalGossipOrder.removeAt(INDEX_LAST_KNOWN_GOSSIP);
        oldestGossip.decreaseNumberOfPeopleAware();
    }

    @Override
    protected Gossip getNextGossip() {
        Gossip gossip = temporalGossipOrder.get(oldestGossipIndex++);
        oldestGossipIndex = oldestGossipIndex % capacity;
        return gossip;
    }

    @Override
    public void changeGroup() {
        oldestGossipIndex = 0;
    }

    @Override
    protected void reArrangeGossips(Gossip gossip) {
        getGossips().removeAt(getGossips().searchIndexOf(gossip));
        getGossips().insertLast(gossip);
    }

}
