/**
 * Classe CommunityClass - implements the Interface Community.
 *
 * @authors Margrida Lopes n64557
 */

package engine;

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;

public class CommunityClass implements Community {

    private Array<Landmark> landmarks;
    private Array<Person> people;
    private Array<Gossip> gossips;


    /**
     * Community Class Constructor.
     * Community object is created.
     */
    public CommunityClass() {
        landmarks = new ArrayClass<>();
        people = new ArrayClass<>();
        gossips = new ArrayClass<>();
    }

    /**
     * Returns the index of the landmark.
     *
     * @param landmarkName the landmark's name.
     * @return the landmark index or a negative value if it does not exist.
     * @pre landmarkName != null && hasLandmark(landmarkName)
     */
    private int landmarkIndex(String landmarkName) {
        int idx = 0;
        while (idx < landmarks.size() && !landmarks.get(idx).getName().equals(landmarkName))
            idx++;
        if (idx >= landmarks.size())
            idx = -1;
        return idx;
    }

    /**
     * Returns the index of the person.
     *
     * @param name the person's name.
     * @return the person index or a negative value if it does not exist.
     * @pre name != null && hasPerson(name)
     */
    private int personIndex(String name) {
        int idx = 0;
        while (idx < people.size() && !people.get(idx).getName().equals(name))
            idx++;
        if (idx >= people.size())
            idx = -1;
        return idx;
    }

    @Override
    public boolean hasLandmark(String landmarkName) {
        return (landmarkIndex(landmarkName) >= 0);
    }

    @Override
    public void addLandmark(String landmarkName, int capacity) {
        Landmark newLandmark = new LandmarkClass(landmarkName, capacity);
        landmarks.insertLast(newLandmark);
    }

    @Override
    public boolean hasAddedLandmarks() {
        return (landmarks.size() > 0);
    }

    @Override
    public Iterator<Landmark> listAllLandmarks() {
        return landmarks.iterator();
    }

    @Override
    public boolean hasPerson(String name) {
        return (personIndex(name) >= 0);
    }

    @Override
    public void addForgetful(String name, int capacity) {
        Person newForgetful = new Forgetful(name, capacity);
        people.insertLast(newForgetful);
    }

    @Override
    public void addGossiper(String name) {
        Person newGossiper = new Gossiper(name);
        people.insertLast(newGossiper);
    }

    @Override
    public void addSealedLips(String name) {
        Person newSealedLips = new SealedLips(name);
        people.insertLast(newSealedLips);
    }

    @Override
    public boolean hasAddedPeople() {
        return (people.size() > 0);
    }

    @Override
    public Iterator<Person> listAllLPerson() {
        return people.iterator();
    }

    @Override
    public void goHome(String name) {
        Person person = getPerson(name);
        movePersonToLandmark(person, Constants.HOME);
    }

    @Override
    public Person getPerson(String name) {
        return people.get(personIndex(name));
    }

    @Override
    public Landmark getLandmark(String place) {
        return landmarks.get(landmarkIndex(place));
    }

    @Override
    public void movePerson(String name, String place) {
        Person person = getPerson(name);
        movePersonToLandmark(person, place);
        getLandmark(place).addPerson(person);
    }

    /**
     * Moves to landmark only if the person is not at home.
     *
     * @param person       to be moved.
     * @param landmarkName where the person should be moved.
     * @pre person != null && landmarkName != null && hasPerson(person.getName()) && hasLandmark(landmarkName)
     */
    private void movePersonToLandmark(Person person, String landmarkName) {
        exitPreviousLandmark(person);
        person.changeLandmark(landmarkName);
    }

    /**
     * If person is not at home, it will exit its previous landmark.
     *
     * @param person that is in a landmark or home.
     * @pre person != null && hasPerson(person.getName())
     */
    private void exitPreviousLandmark(Person person) {
        if (!person.isAtHome()) {
            getLandmark(person.getLandmarkName()).removePerson(person);
        }
    }

    @Override
    public boolean inSameGroup(String name1, String name2) {
        Person person1 = getPerson(name1);
        Person person2 = getPerson(name2);
        return getLandmark(person1.getLandmarkName()).getGroup(person1).isPersonInGroup(person2);
    }

    @Override
    public boolean inSameLandmark(String name1, String name2) {
        Person person1 = getPerson(name1);
        Person person2 = getPerson(name2);
        return person1.getLandmarkName().equals(person2.getLandmarkName());
    }

    @Override
    public void joinGroup(String name1, String name2) {
        Person person1 = getPerson(name1);
        Person person2 = getPerson(name2);
        exitPreviousLandmark(person1);
        getLandmark(person2.getLandmarkName()).getGroup(person2).addPersonToGroup(person1);
    }

    @Override
    public boolean isPersonAlone(String name) {
        Person person = getPerson(name);
        int numberOfPerson = getLandmark(person.getLandmarkName()).getGroup(person).groupSize();
        return (numberOfPerson == 1) || person.getLandmarkName().equals(Constants.HOME);
    }

    @Override
    public void isolatePerson(String name) {
        Person person = getPerson(name);
        getLandmark(person.getLandmarkName()).removePerson(person);
        getLandmark(person.getLandmarkName()).addPerson(person);
    }

    @Override
    public int arePeopleCreated(int numberOfTargets, Array<String> targetsNamesArray) {
        int i = 0;
        while (hasPerson(targetsNamesArray.get(i))) {
            i++;
        }
        return i;
    }

    @Override
    public boolean isGossipUnique(Person person, Array<Person> targetPersonArray, String description, int numberOfTargets) {
        Gossip gossip = new GossipClass(person, targetPersonArray, description, numberOfTargets);
        boolean unique = true;
        int i = 0;
        while (unique && i != gossips.size()) {
            if (gossip.isEqualGossip(gossips.get(i))) {
                unique = false;
            }
            i++;
        }
        return unique;
    }

    @Override
    public void addGossip(String gossiper, Array<Person> targetPersonArray, String description, int numberOfTargets) {
        Person person = getPerson(gossiper);
        Gossip newGossip = new GossipClass(person, targetPersonArray, description, numberOfTargets);
        addGossipToCommunity(newGossip);
        person.addGossip(newGossip);

    }

    public void shareGossip(String name) {
        Person person = getPerson(name);
        person.shareGossip(getLandmark(person.getLandmarkName()).getGroup(person));
    }

    /**
     * Adds a new gossip to the community.
     *
     * @param gossip the gossip.
     * @pre gossip != null
     */
    private void addGossipToCommunity(Gossip gossip) {
        gossips.insertLast(gossip);
    }

    public Iterator<Person> getOtherPeopleInGroup(String personName) {
        Person person = getPerson(personName);
        return getLandmark(person.getLandmarkName()).getGroup(person).listPeopleExceptOne(person);
    }

    @Override
    public Iterator<Gossip> getLatestGossips(String personName) {
        return getPerson(personName).getLatestGossips();
    }

    @Override
    public Iterator<Gossip> gossipsAboutPerson(String personName) {
        Person person = getPerson(personName);
        Array<Gossip> gossipsAboutPerson = new ArrayClass<>();
       for (int i = 0; i < gossips.size(); i++) {
           if (gossips.get(i).isPersonTarget(person) && gossips.get(i).hasPeopleAware()) {
               gossipsAboutPerson.insertLast(gossips.get(i));
           }
       }
        return gossipsAboutPerson.iterator();
    }

    @Override
    public int getNumberAwarePeople(Gossip gossip) {
        int total = 0;
        for (int i = 0; i < people.size() ; i++) {
            if (people.get(i).hasGossip(gossip) && gossip.hasPeopleAware()) {
                total++;
            }
        }
        return total;
    }

    @Override
    public boolean hasAddedGossips() {
        return gossips.size() > 0;
    }

    @Override
    public int getNumberSharesMostShared() {
        int total = 0;
        for (int i = 0; i < gossips.size(); i++) {
            if (gossips.get(i).getNumberOfShares() > total && gossips.get(i).hasPeopleAware()) {
                total = gossips.get(i).getNumberOfShares();
            }
        }
        return total;
    }

    @Override
    public Iterator<Gossip> listMostSharedGossips() {
        Array<Gossip> mostShared = new ArrayClass<>();
        for (int i = 0; i < gossips.size(); i++) {
            if (gossips.get(i).getNumberOfShares() == getNumberSharesMostShared() && gossips.get(i).hasPeopleAware()) {
                mostShared.insertLast(gossips.get(i));
            }
        }
        return mostShared.iterator();
    }

    @Override
    public boolean isPersonATarget(String name) {
        Person person = getPerson(name);
        int i = 0;
        boolean found = false;
        while(!found && i < gossips.size()){
            if(gossips.get(i).hasPeopleAware()){
                found = gossips.get(i).isPersonTarget(person);
            }
            i++;
        }
        return found;
    }

}
