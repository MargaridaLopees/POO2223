/**
 * Interface Gossip.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Array;

public interface Gossip {

    String getCreator();

    /**
     * Returns the gossip.
     *
     * @return the gossip.
     */
    String getDescription();

    /**
     * Returns the number os people involved in the gossip.
     *
     * @return the number os people involved in the gossip.
     */
    int getNumberOfTargets();

    /**
     * Returns the number of times the gossip was shared.
     *
     * @return the number of times the gossip was shared.
     */
    int getNumberOfShares();

    /**
     * Increases the number of times the secret was shared.
     */
    void increaseNumberOfShares();

    /**
     * Increases the number of people aware of the gossip.
     */
    void increaseNumberOfPeopleAware();

    /**
     * Descreases the number of people aware of the gossip.
     */
    void decreaseNumberOfPeopleAware();

    /**
     * Returns true if this gossip has any person aware of it. In other words, if they remember it.
     * @return true if this gossip has any person aware of it. In other words, if they remember it.
     */
    boolean hasPeopleAware();

    /**
     * Returns all the people involved in the gossip.
     *
     * @return all the people involved in the gossip.
     */
    Array<Person> getTargets();

    /**
     * Checks if two gossips are the same.
     *
     * @param gossip the gossip.
     * @return true if they are equal and false if they are not,
     * @pre gossip != null
     */
    boolean isEqualGossip(Gossip gossip);

    /**
     * Checks whether a given person is the creator of the gossip.
     *
     * @param person the person.
     * @return true if it is the creator, false if not.
     * @pre person != null && community.hasPerson(person.getName())
     */
    boolean isCreator(Person person);

    /**
     * Checks if a given person is one of the gossip targets.
     *
     * @param person the person.
     * @return true if it is and false if not.
     */
    boolean isPersonTarget(Person person);


}
