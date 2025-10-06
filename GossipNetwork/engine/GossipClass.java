/**
 * Classe GossipClass - implements the Interface Gossip.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

import data_structures.Array;

public class GossipClass implements Gossip {


    final Person creator;
    final Array<Person> targets;
    final String description;
    final int numberOfTargets;
    int numberOfShares;
    int numberOfPeopleAware;


    /**
     * GossipClass constructor.
     * Creates a new gossip.
     *
     * @param creator         the name of the creator of the gossip.
     * @param targets         the people involved in the gossip.
     * @param description     the gossip.
     * @param numberOfTargets the number of people involved.
     * @pre creator != null && targets != null && description != null && numberOfTargets != null
     */
    public GossipClass(Person creator, Array<Person> targets, String description, int numberOfTargets) {
        this.creator = creator;
        this.targets = targets;
        this.description = description;
        this.numberOfTargets = numberOfTargets;
        numberOfShares = 0;
        numberOfPeopleAware = 0;
    }

    @Override
    public String getCreator() {
        return creator.getName();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    @Override
    public int getNumberOfShares() {
        return numberOfShares;
    }

    @Override
    public void increaseNumberOfShares() {
        numberOfShares++;
    }

    @Override
    public void increaseNumberOfPeopleAware() {
        numberOfPeopleAware++;
    }

    @Override
    public void decreaseNumberOfPeopleAware() {
        numberOfPeopleAware--;
    }

    @Override
    public boolean hasPeopleAware() {
        return numberOfPeopleAware>0;
    }

    @Override
    public Array<Person> getTargets() {
        return targets;
    }

    public boolean isEqualGossip(Gossip otherGossip) {
        return creator.getName().equals(otherGossip.getCreator()) &&
               isSamePeopleInvolved(otherGossip.getTargets()) &&
               description.equals(otherGossip.getDescription());
    }

    @Override
    public boolean isCreator(Person person) {
        return creator.getName().equals(person.getName());
    }

    @Override
    public boolean isPersonTarget(Person person) {
        boolean isTarget = false;
        int i = 0;
        while (!isTarget && i != targets.size()) {
            isTarget = targets.get(i).getName().equals(person.getName());
            i++;
        }
        return isTarget;
    }


    /**
     * Checks if a person is a person involved in gossip.
     *
     * @param person the person.
     * @return true if involved and false if not.
     * @pre person != null && community.hasPerson(person.getName())
     */
    private boolean isPersonATarget(Person person) {
        int i = 0;
        boolean found = false;
        while (i < targets.size() && !found) {
            if (targets.get(i).getName().equals(person.getName())) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Checks that all people involved in two gossips are the same.
     *
     * @param people the people involved in the other gossip.
     * @return true if they are all the same and false if they are not.
     * @pre people != null
     */
    private boolean isSamePeopleInvolved(Array<Person> people) {
        boolean samePeopleInvolved = people.size() == targets.size();
        int i = 0;
        while (samePeopleInvolved && i < people.size()) {
            if (!isPersonATarget(people.get(i))) {
                samePeopleInvolved = false;
            }
            i++;
        }
        return samePeopleInvolved;
    }
}
