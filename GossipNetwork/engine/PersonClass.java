/**
 * Abstract class PersonClass - implements the interface Person.
 *
 * @authors Margrida Lopes n64557
 */

package engine;

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;

public abstract class PersonClass implements Person {

    private final String name;
    private String landmark;
    private Array<Gossip> gossips;

    private Gossip latestGossipAdded;

    /**
     * PersonClass constructor.
     * Creates a new person.
     *
     * @param name the person's name.
     */
    public PersonClass(String name) {
        this.name = name;
        landmark = Constants.HOME;
        gossips = new ArrayClass<>();
        latestGossipAdded = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLandmarkName() {
        return landmark;
    }

    @Override
    public int getKnownGossipsQuantity() {
        return gossips.size();
    }

    @Override
    public boolean isAtHome() {
        return (landmark.equals(Constants.HOME));
    }

    @Override
    public void changeLandmark(String newLandmarkName) {
        landmark = newLandmarkName;
    }

    @Override
    public Iterator<Gossip> listAllGossip() {
        return gossips.iterator();
    }

    protected Array<Gossip> getGossips() {
        return gossips;
    }

    @Override
    public void addGossip(Gossip gossip) {
        if (latestGossipAdded == null) {
            gossips.insertLast(gossip);
        } else {
            gossips.insertAt(gossip, gossips.searchIndexOf(latestGossipAdded) + 1);
        }
        latestGossipAdded = gossip;
        gossip.increaseNumberOfPeopleAware();
    }

    @Override
    public boolean canShareGossip() {
        return getKnownGossipsQuantity() > 0;
    }

    @Override
    public void shareGossip(Group group) {
        int i = 0;
        while (i < gossips.size() && i != howManyGossipToShare()) {
            gossips.get(i).increaseNumberOfShares();
            Gossip gossip = getNextGossip();
            reArrangeGossips(gossip);
            group.shareGossipWithGroup(gossip);
            i++;
        }
    }

    protected void reArrangeGossips(Gossip gossip) {
        gossips.removeAt(0);
        gossips.insertLast(gossip);
    }

    protected abstract Gossip getNextGossip();

    public int howManyGossipToShare() {
        return 1;
    }

    @Override
    public void changeGroup() {
        // Empty on purpose.
    }

    @Override
    public Iterator<Gossip> getLatestGossips() {
        Array<Gossip> latestGossips = new ArrayClass<>();
        for (int i = (getKnownGossipsQuantity()-numberOfLatestGossips()); i != getKnownGossipsQuantity(); i++) {
            latestGossips.insertLast(gossips.get(i));
        }
        return latestGossips.iterator();
    }

    /**
     * Returns the number of the latest gossips added.
     *
     * @return the number of the latest gossips added.
     */
    private int numberOfLatestGossips() {
        if(getKnownGossipsQuantity()<=howManyGossipToShare()){
            return getKnownGossipsQuantity();
        }else{
            return howManyGossipToShare();
        }
    }

    @Override
    public boolean hasGossip(Gossip gossip) {
        return gossips.searchIndexOf(gossip) != -1;
    }
}
