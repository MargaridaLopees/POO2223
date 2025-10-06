/**
 * Interface Person.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Iterator;

public interface Person {

    /**
     * Returns de person's name.
     *
     * @return the person's name.
     */
    String getName();

    /**
     * Returns the landmark where the person is currently located.
     *
     * @return the landmark where the person is currently located.
     */
    String getLandmarkName();

    /**
     * Returns the number of gossips the person knows.
     *
     * @return the number of gossips the person knows.
     */
    int getKnownGossipsQuantity();

    /**
     * Check if the person is at home.
     *
     * @return true if it is and false if not.
     */
    boolean isAtHome();

    /**
     * Changes the person's landmark.
     *
     * @param newLandmarkName the landmark.
     * @pre landmark != null && community.hasLandmark(landmark.getName())
     */
    void changeLandmark(String newLandmarkName);

    /**
     * List all goosips that the person knows.
     *
     * @return an iterator that list all gossips that the person knows.
     */
    Iterator<Gossip> listAllGossip();

    /**
     * Adds a new gossip to the array of gossips the person knows.
     *
     * @param gossip the new gossip.
     * @pre gossip != null
     *
     */
    void addGossip(Gossip gossip);

    /**
     * Checks if the person can share a gossip.
     *
     * @return true if you can share and false if you can't.
     */
    boolean canShareGossip();

    /**
     * Shares a gossip with the rest of the group.
     *
     * @param group the group the person is in.
     * @pre group != null
     */
    void shareGossip(Group group);

    /**
     * Returns the number of gossips the person can share.
     *
     * @return the number of gossips the person can share.
     */
    int howManyGossipToShare();

    /**
     * Change the group person.
     */
    void changeGroup();

    /**
     * Lists the latests gossips the person knows.
     *
     * @return the latests gossips the person knows.
     */
    Iterator<Gossip> getLatestGossips();

    /**
     * Checks if the person knows the given gossip.
     *
     * @param gossip The gossip.
     * @return true if the person knows and false if don't.
     * @pre gossip != null
     */
    boolean hasGossip(Gossip gossip);

}

