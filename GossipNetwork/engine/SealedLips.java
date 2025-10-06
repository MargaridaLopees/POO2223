/**
 * Classe SealedLips - extends the abastract class PersonClass.
 *
 * @authors Margrida Lopes n64557
 */

package engine;

import data_structures.Iterator;

public class SealedLips extends PersonClass{

    /**
     * SealedLips class constructor.
     * Creates a new person SealedLips.
     *
     * @param name the name of the person.
     */
    public SealedLips(String name) {
        super(name);
    }

    @Override
    public boolean canShareGossip(){
        boolean hasAPersonalGossip = false;
        Iterator<Gossip> it = listAllGossip();
        while(!hasAPersonalGossip && it.hasNext()){
            if(it.next().isPersonTarget(this)){
                hasAPersonalGossip = true;
            }
        }
        return hasAPersonalGossip;
    }

    @Override
    protected Gossip getNextGossip() {
        if (!getGossips().get(0).isPersonTarget(this)) {
            reArrangeGossips(getGossips().get(0));
            return getNextGossip();
        } else {
            return getGossips().get(0);
        }
    }

}
