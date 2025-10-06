/**
 * Interface Landmark.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Iterator;

public interface Landmark {

    /**
     * Returns the name of the Landmark.
     *
     * @return Landmark's name.
     */
    String getName();

    /**
     * Returns the value of the landmark capacity.
     *
     * @return the value of the landmark capacity.
     */
    int getCapacity();

    /**
     * Returns the current number of people at landmark.
     *
     * @return Current number of people at landmark.
     */
    int getOccupation();

    /**
     * Checks if landmark is currently full.
     *
     * @return true if landmark is full, else false.
     */
    boolean isFull();

    /**
     * Returns the number of groups in the Landmark
     *
     * @return int groups in the Landmark
     */
    int getNumberOfGroups();

    /**
     * Removes the person from the landmark.
     *
     * @param person person to remove from the landmark.
     * @pre person != null && community.hasPerson(person.getName())
     */
    void removePerson(Person person);

    /**
     * Adds a new person to the landmark.
     *
     * @param person person to add to landmark.
     * @pre pessoa != null && community.hasPerson(person.getName())
     */
    void addPerson(Person person);

    /**
     * Returns the Group at the requested vectorial position
     *
     * @param index int with the vectorial position
     * @return returns the correct Group in the given vectorial position
     * @pre index != null
     */
    Group getGroupByIndex(int index);

    /**
     * Returns the group of a person
     *
     * @param person Person to look for
     * @return the group where that person is located at.
     * @pre person != null && community.hasPerson(person.getName())
     */
    Group getGroup(Person person);

    /**
     * List all groups in the landmark.
     *
     * @return an iterator that list all groups in the landmark.
     */
    Iterator<Group> getAllGroups();
}
