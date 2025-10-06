/**
 * Interface Community.
 *
 * @authors Margrida Lopes n64557
 */

package engine;

import data_structures.Array;
import data_structures.Iterator;

public interface Community {

    /**
     * Checks if a given landmark exists in the community.
     *
     * @param landmarkName the name of the landmark
     * @return true if the landmark exists, false if it does not.
     * @pre landmarkName != null
     */
    boolean hasLandmark(String landmarkName);

    /**
     * Adds a new landmark to the community.
     *
     * @param landmarkName the name of the landmark.
     * @param capacity     the capacity of the landmark.
     * @pre landmarkName != null && capacity != null && !hasLandmark(landmarkName);
     */
    void addLandmark(String landmarkName, int capacity);

    /**
     * Checks if there are any landmarks in the community.
     *
     * @return true if there are any landmarks added, false if there are no landmarks added.
     */
    boolean hasAddedLandmarks();

    /**
     * Lists all existing landmarks in the community.
     *
     * @return an iterator that lists all existing landmarks in the community.
     * @pre hasAddedLandmarks();
     */
    Iterator<Landmark> listAllLandmarks();

    /**
     * Checks if a given person exists in the community.
     *
     * @param name the person's name.
     * @return true if the person already exists in the community, false if not yet.
     * @pre name != null
     */
    boolean hasPerson(String name);

    /**
     * Adds a new person 'Forgetful'.
     *
     * @param name     the person's name.
     * @param capacity the maximum value of gossips that the person knows.
     * @pre name != null & capacity != null && !hasPerson(name)
     */
    void addForgetful(String name, int capacity);

    /**
     * Adds a new person 'Gossiper'.
     *
     * @param name the person's name.
     * @pre name != null && !hasPerson(name)
     */
    void addGossiper(String name);

    /**
     * Adds a new person 'SealedLips'.
     *
     * @param name the person's name.
     * @pre name != null & !hasPerson(name)
     */
    void addSealedLips(String name);

    /**
     * Checks if there are people in the community.
     *
     * @return true if there are people in the community, false if there are none.
     */
    boolean hasAddedPeople();

    /**
     * Lists all existing people in the community.
     *
     * @return an iterator that lists all the people in the community.
     * @pre hasAddedPeople();
     */
    Iterator<Person> listAllLPerson();

    /**
     * Place a given person at home.
     *
     * @param name the person's name.
     * @pre name != null && hasPerson(name)
     */
    void goHome(String name);

    /**
     * Returns the person with the given name.
     *
     * @param name the person's name.
     * @return the person with the given name.
     * @pre name != null && hasPerson(name)
     */
    Person getPerson(String name);

    /**
     * Returns the landmark with the given name.
     *
     * @param place the name of the landmark.
     * @return a landmark
     * @pre landmarkName != null && hasLandmark(landmarkName)
     */
    Landmark getLandmark(String place);

    /**
     * Moves a person to a landmark.
     *
     * @param name  the person's name.
     * @param place a landmark.
     * @pre name != nul && landmark != null && hasPerson(name) && hasLandmark(landmark)
     */
    void movePerson(String name, String place);

    /**
     * Checks if two people are in the same group.
     *
     * @param name1 the first person's name.
     * @param name2 the second person's name.
     * @return true if they are in the same group and false if they are not.
     * @pre name1 != null && name2 != null && hasPerson(name1) && hasPerson(name2)
     */
    boolean inSameGroup(String name1, String name2);

    /**
     * Checks if two people are in the same landmark.
     *
     * @param name1 the first person's name.
     * @param name2 the second person's name.
     * @return true if they are in the same landmark and false if they are not.
     * @pre name1 != null && name2 != null && hasPerson(name1) && hasPerson(name2)
     */
    boolean inSameLandmark(String name1, String name2);

    /**
     * Adds the first person to the second person's group.
     *
     * @param name1 the first person's name.
     * @param name2 the second person's name.
     * @pre name1 != null && name2 != null && hasPerson(name1) && hasPerson(name2)
     */
    void joinGroup(String name1, String name2);

    /**
     * Checks if a given person is alone.
     *
     * @param name the name of the person.
     * @return true if alone and false if not.
     * @pre name != null && hasPerson(name)
     */
    boolean isPersonAlone(String name);

    /**
     * Isolate a given person.
     *
     * @param name the name of the person.
     * @pre name != null && hasPerson(name)
     */
    void isolatePerson(String name);

    /**
     * Returns the index of the first person who does not belong to the community.
     *
     * @param numberOfTargets   the number of people to check.
     * @param targetsNamesArray the array of people to check.
     * @return the index of the first person who does not belong to the community.
     * @pre numberOfTargets != null && targetsNamesArray != null
     */
    int arePeopleCreated(int numberOfTargets, Array<String> targetsNamesArray);

    /**
     * Check if gossip exists.
     *
     * @param person            the name of the person who created the gossip.
     * @param targetPersonArray the people involved.
     * @param description       the gossip.
     * @param numberOfTargets   the number of people involved in gossip.
     * @return true if gossip already exists, false if not yet.
     * @pre person != null && hasPerson(person.getName()) && targetPersonArray != null &&
     * description != null && numberOfTargets != null
     */
    boolean isGossipUnique(Person person, Array<Person> targetPersonArray, String description, int numberOfTargets);

    /**
     * Adds the new gossip to the person's gossips.
     *
     * @param gossiper          the person's name.
     * @param targetPersonArray the people involved in the gossip.
     * @param description       the gossip.
     * @param numberOfTargets   the number of people involved in gossip.
     * @pre person != null && hasPerson(person.getName()) && targetPersonArray != null &&
     * description != null && numberOfTargets != null
     */
    void addGossip(String gossiper, Array<Person> targetPersonArray, String description, int numberOfTargets);

    /**
     * A gossip is shared in the group.
     *
     * @param name the person's name.
     * @pre name != null && hasPerson(name)
     */
    void shareGossip(String name);

    /**
     * Lists all other people in the group.
     *
     * @param personName the person's name.
     * @return all other people in the group.
     * @pre personName != null && hasPerson(personName)
     */
    Iterator<Person> getOtherPeopleInGroup(String personName);

    /**
     * Lists the last shared gossips.
     *
     * @param personName the person's name.
     * @return the last shared gossips.
     * @pre personName != null && hasPerson(personName)
     */
    Iterator<Gossip> getLatestGossips(String personName);

    /**
     * Lists all the gossips about a given person.
     *
     * @param personName the person's name.
     * @return all the gossips about a given person.
     */
    Iterator<Gossip> gossipsAboutPerson(String personName);

    /**
     * Returns the number of people who know the secret.
     *
     * @return the number of people who know the secret.
     */
    int getNumberAwarePeople(Gossip gossip);

    /**
     * Checks if there are gossips in the community.
     *
     * @return true if there are and false if not.
     */
    boolean hasAddedGossips();

    /**
     * Returns the number of times the most shared gossip was shared.
     *
     * @return the number of times the most shared gossip was shared.
     */
    int getNumberSharesMostShared();

    /**
     * Lists the most shared gossips.
     *
     * @return the most shared gossips.
     */
    Iterator<Gossip> listMostSharedGossips();

    /**
     * Checks if a given person name is a target of a gossip.
     *
     * @param name person's name.
     * @return true if the person is a target, false if not.
     * @pre name != null
     */
    boolean isPersonATarget(String name);
}
