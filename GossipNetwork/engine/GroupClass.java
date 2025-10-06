/**
 * Classe GroupClass - implements the Interface Group.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;

public class GroupClass implements Group {

    private static final int EMPTY_GROUP = 0;
    private Array<Person> personInGroup;

    /**
     * GroupClass class constructor.
     * Creates a new group of people.
     */
    public GroupClass() {
        personInGroup = new ArrayClass<>();
    }


    @Override
    public void addPersonToGroup(Person person) {
        personInGroup.insertLast(person);
        person.changeGroup();
    }

    @Override
    public void removePersonFromGroup(Person person) {
        personInGroup.removeAt(getPersonIdx(person));
    }

    /**
     * Returns the position of the person in the group.
     *
     * @param person the person.
     * @return the person's position in the group, or a negative value if not in the group.
     * @pre person != null
     */
    private int getPersonIdx(Person person) {
        return personInGroup.searchIndexOf(person);
    }

    @Override
    public int groupSize() {
        return personInGroup.size();
    }

    @Override
    public boolean isPersonInGroup(Person person) {
        return (getPersonIdx(person) >= 0);
    }

    @Override
    public Iterator<Person> listAllLPerson() {
        return personInGroup.iterator();
    }

    @Override
    public Iterator<Person> listPeopleExceptOne(Person person) {
        Array<Person> peopleExceptMe = new ArrayClass<>();
        for(int i = 0; i!= personInGroup.size(); i++){
            if(!person.getName().equals(personInGroup.get(i).getName())){
                peopleExceptMe.insertLast(personInGroup.get(i));
            }
        }
        return peopleExceptMe.iterator();
    }

    @Override
    public boolean isEmpty() {
        return personInGroup.size() == EMPTY_GROUP;
    }

    @Override
    public void shareGossipWithGroup(Gossip gossip) {
        for (int i = 0; i != personInGroup.size(); i++) {
            Person person = personInGroup.get(i);
            if (!gossip.isCreator(person) && !person.hasGossip(gossip)) {
                person.addGossip(gossip);
            }
        }
    }

}
