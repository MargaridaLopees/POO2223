/**
 * Interface Group.
 *
 * @authors Margrida Lopes n64557
 */

package engine;

import data_structures.Iterator;

public interface Group {

    /**
     * Adds a person to the group.
     *
     * @param person the person to add.
     * @pre pessoa != null && community.hasPerson(person.getName())
     */
    void addPersonToGroup(Person person);

    /**
     * Removes a person from a group.
     *
     * @param person the person to remove.
     * @pre person != null
     */
    void removePersonFromGroup(Person person);

    /**
     * Returns the number of people in the group.
     *
     * @return the number of people in the group.
     */
    int groupSize();

    /**
     * Checks if a given person is currently in the group.
     *
     * @param person the person.
     * @return true if the person is in the group, and false if not.
     * @pre person != null
     */
    boolean isPersonInGroup(Person person);

    /**
     * Lists all existing people in the community.
     *
     * @return an iterator that lists all the people in the community.
     * @pre hasAddedPeople();
     */
    Iterator<Person> listAllLPerson();

    /**
     * Lists all other people in the group.
     *
     * @param person the person who is not listed.
     * @return all other people in the group.
     * @pre person != null && community.hasPerson(person.getName())
     */
    Iterator<Person> listPeopleExceptOne(Person person);

    /**
     * Returns true if the group has no person in it.
     *
     * @return true if the group has no person in it and false otherwise.
     */
    boolean isEmpty();

    /**
     * Share the gossip with the group.
     *
     * @param gossip the gossip.
     * @pre gossip != nul
     */
    void shareGossipWithGroup(Gossip gossip);
}
