/**
 * LandmarkClass class - implements the Interface Landmark.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;

public class LandmarkClass implements Landmark {

    private final String name;
    private final int capacity;
    public Array<Group> peopleAtLandmark;

    /**
     * LandmarkClass constructor.
     * Creates a new Landmark.
     *
     * @param name     the landmark name.
     * @param capacity the landmark capacity.
     * @pre name != null && capacity != null
     */
    public LandmarkClass(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        peopleAtLandmark = new ArrayClass<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getOccupation() {
        int occupation = 0;
        for (int i = 0; i < peopleAtLandmark.size(); i++) {
            occupation += peopleAtLandmark.get(i).groupSize();
        }
        return occupation;
    }

    @Override
    public boolean isFull() {
        return (capacity == getOccupation());
    }

    @Override
    public int getNumberOfGroups() {
        return peopleAtLandmark.size();
    }

    @Override
    public void removePerson(Person person) {
        int indexOfPersonsGroups = indexGroup(person);
        Group group = getGroupByIndex(indexOfPersonsGroups);
        group.removePersonFromGroup(person);
        if (group.isEmpty()) {
            peopleAtLandmark.removeAt(indexOfPersonsGroups);
        }
    }

    @Override
    public void addPerson(Person person) {
        Group newGroup = new GroupClass();
        newGroup.addPersonToGroup(person);
        peopleAtLandmark.insertLast(newGroup);
    }

    @Override
    public Group getGroupByIndex(int index) {
        return peopleAtLandmark.get(index);
    }

    /**
     * Returns the vectorial position of the group that the person is on
     *
     * @param person Person to look for
     * @return vectorial position the person is in within the group.
     * @pre person != null && community.hasPerson(person.getName())
     */
    private int indexGroup(Person person) {
        int group = 0;
        while (group < peopleAtLandmark.size() && (!peopleAtLandmark.get(group).isPersonInGroup(person)))
            group++;
        return group;
    }

    @Override
    public Group getGroup(Person person) {
        return getGroupByIndex(indexGroup(person));
    }

    @Override
    public Iterator<Group> getAllGroups() {
        return peopleAtLandmark.iterator();
    }
}
