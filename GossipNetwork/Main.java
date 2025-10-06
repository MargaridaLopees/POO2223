/**
 * Main.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

import data_structures.Array;
import data_structures.ArrayClass;
import data_structures.Iterator;
import engine.*;

import java.util.Scanner;

public class Main {

    /**
     * Receives user input and gives feedback.
     * Returns an object ´COMMUNITY´ and a Scanner ´in´ for reading the commands.
     * The program stops when the entered command is 'EXIT' - Terminates the execution of the program.
     */
    public static void main(String[] args) {
        Community community = new CommunityClass();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = getComand(in);
            executeComand(command, in, community);
        } while (!command.equals(Constants.EXIT_COMMAND));
        in.close();
    }

    /**
     * Returns the command written in the console by the user.
     *
     * @param in the scanner to read the command.
     * @return the command written in the console by the user.
     * @pre in != null
     */
    private static String getComand(Scanner in) {
        return in.next().toUpperCase();
    }

    /**
     * Executes the command requested by the user.
     * <p>
     * HELP command - Shows the available commands.
     *
     * @param command   the command requested by the user.
     * @param in        the scanner to read the command and necessary parameters to execute the command.
     * @param community the community.
     * @pre command != null && in != null && community != null
     */
    private static void executeComand(String command, Scanner in, Community community) {
        switch (command) {
            case Constants.HELP_COMMAND -> System.out.println(Constants.HELP);
            case Constants.NEW_LANDMARK_COMMAND -> executeNewLandmarkCommand(in, community);
            case Constants.ALL_LANDMARKS_COMMAND -> executeAllLandmarksCommand(community);
            case Constants.FORGETFUL_COMMAND -> executeForgetfulCommand(in, community);
            case Constants.GOSSIPER_COMMAND -> executeGossiperCommand(in, community);
            case Constants.SEALED_COMMAND -> executeSealedCommand(in, community);
            case Constants.PEOPLE_COMMAND -> executePeopleCommand(community);
            case Constants.GO_COMMAND -> executeGoCommand(in, community);
            case Constants.JOIN_COMMAND -> executeJoinCommand(in, community);
            case Constants.GROUPS_COMMAND -> executeGroupsCommand(in, community);
            case Constants.ISOLATE_COMMAND -> executeIsolateCommand(in, community);
            case Constants.START_COMMAND -> executeStartCommand(in, community);
            case Constants.GOSSIP_COMMAND -> executeGossipCommand(in, community);
            case Constants.SECRETS_COMMAND -> executeSecretsCommand(in, community);
            case Constants.HOTTEST_COMMAND -> executeHottestCommand(community);
            case Constants.INFOTAINMENT_COMMAND -> executeInfotainmentCommand(in, community);
            case Constants.EXIT_COMMAND -> System.out.println(Constants.EXIT);
            default -> System.out.println(Constants.UNKNOWN);
        }
    }

    /**
     * Adds a new landmark to the community.
     * Returns an error message when:
     * the landmark capacity is less or equal to 0;
     * the landmark name is "home";
     * the landmark name already exists in the community.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeNewLandmarkCommand(Scanner in, Community community) {
        int capacity = in.nextInt();
        String landmarkName = in.nextLine().trim();
        if (capacity <= 0) {
            System.out.printf(Constants.INVALID_LANDMARK_CAPACITY, capacity);
        } else if (landmarkName.equals(Constants.HOME)) {
            System.out.println(Constants.LANDMARK_HOME);
        } else if (community.hasLandmark(landmarkName)) {
            System.out.printf(Constants.LANDMARK_ALREADY_EXISTS, landmarkName);
        } else {
            community.addLandmark(landmarkName, capacity);
            System.out.printf(Constants.LANDMARK_ADDED, landmarkName);
        }
    }

    /**
     * Displays the list of landmarks in the community
     * Returns an error message if there are no landmarks to list.
     *
     * @param community the community.
     * @pre community != null
     */
    private static void executeAllLandmarksCommand(Community community) {
        if (!community.hasAddedLandmarks()) {
            System.out.println(Constants.ANY_LANDMARKS);
        } else {
            Iterator<Landmark> it = community.listAllLandmarks();
            while (it.hasNext()) {
                Landmark landmark = it.next();
                System.out.printf(Constants.LIST_LANDMARK, landmark.getName(), landmark.getCapacity(), landmark.getOccupation());
            }
        }
    }


    /**
     * Adds a forgetful person to the community.
     * Returns an error message when:
     * the gossip capacity is less or equal to 0;
     * the forgetful person's name already exists in the community.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeForgetfulCommand(Scanner in, Community community) {
        int capacity = in.nextInt();
        String name = in.nextLine().trim();
        if (capacity <= 0) {
            System.out.printf(Constants.INVALID_GOSSIPS_CAPACITY, capacity);
        } else if (community.hasPerson(name)) {
            System.out.printf(Constants.ALREADY_EXISTS, name);
        } else {
            community.addForgetful(name, capacity);
            System.out.printf(Constants.FORGETFUL_PERSON, name, capacity);
        }
        System.out.println();
    }

    /**
     * Adds a gossiper person to the community
     * Returns an error message if the gossiper person’s name already exists in the community.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeGossiperCommand(Scanner in, Community community) {
        String name = in.nextLine().trim();
        if (community.hasPerson(name)) {
            System.out.printf(Constants.ALREADY_EXISTS, name);
        } else {
            community.addGossiper(name);
            System.out.printf(Constants.GOSSIPER_PERSON, name);
        }
        System.out.println();
    }

    /**
     * Adds a sealed lips person to the community.
     * Returns an error message if the sealed lips person's name already exists in the community.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeSealedCommand(Scanner in, Community community) {
        String name = in.nextLine().trim();
        if (community.hasPerson(name)) {
            System.out.printf(Constants.ALREADY_EXISTS, name);
        } else {
            community.addSealedLips(name);
            System.out.printf(Constants.SEALED_PERSON, name);
        }
        System.out.println();
    }

    /**
     * Lists all the persons in the community.
     * Returns an error message if there are no people to list.
     *
     * @param community the community.
     * @pre community != null
     */
    private static void executePeopleCommand(Community community) {
        if (!community.hasAddedPeople()) {
            System.out.printf(Constants.NOT_PEOPLE_YET);
            System.out.println();
        } else {
            Iterator<Person> it = community.listAllLPerson();
            while (it.hasNext()) {
                Person person = it.next();
                System.out.printf(Constants.LIST_PEOPLE, person.getName(), person.getLandmarkName(), person.getKnownGossipsQuantity());
                System.out.println();
            }
        }
    }

    /**
     * Moves a person to a landmark, or home.
     * Returns an error message when:
     * the person's name does not exist in the community;
     * the place the person is going is a not known landmark;
     * the person decides to go to the place the person is already in;
     * the landmark is already full.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeGoCommand(Scanner in, Community community) {
        String name = in.nextLine().trim();
        String place = in.nextLine().trim();
        if (!community.hasPerson(name)) {
            System.out.printf(Constants.NOT_EXISTS, name);
        } else if (place.equals(Constants.HOME)) {
            community.goHome(name);
            System.out.printf(Constants.AT_HOME, name);
        } else if (!community.hasLandmark(place)) {
            System.out.printf(Constants.UNKNOWN_LANDMARK, place);
        } else if (community.getPerson(name).getLandmarkName().equals(place)) {
            System.out.printf(Constants.ALREADY_THERE, place, name);
        } else if (community.getLandmark(place).isFull()) {
            community.goHome(name);
            System.out.printf(Constants.LANDMARK_FULL, place, name);
        } else {
            community.movePerson(name, place);
            System.out.printf(Constants.PERSON_MOVED, name, place);
        }
        System.out.println();
    }

    /**
     * Joins a person to a group.
     * Returns an error message when:
     * the first and the second person are the same;
     * the first person’s name does not exist in the community;
     * the second person’s name does not exist in the community;
     * the first person is at home, rather than in a landmark;
     * the second person is not in the same landmark;
     * the first person and the second person are already in the same group.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeJoinCommand(Scanner in, Community community) {
        String name1 = in.nextLine().trim();
        String name2 = in.nextLine().trim();
        if (name1.equals(name2)) {
            System.out.printf(Constants.NEEDS_COMPANY, name1);
        } else if (!community.hasPerson(name1)) {
            System.out.printf(Constants.NOT_EXISTS, name1);
        } else if (!community.hasPerson(name2)) {
            System.out.printf(Constants.NOT_EXISTS, name2);
        } else if (community.getPerson(name1).isAtHome()) {
            System.out.printf(Constants.AT_HOME, name1);
        } else if (!community.inSameLandmark(name1, name2)) {
            System.out.printf(Constants.IS_NOT_IN, name2, community.getPerson(name1).getLandmarkName());
        } else if (community.inSameGroup(name1, name2)) {
            System.out.printf(Constants.SAME_GROUP, name1, name2);
        } else {
            System.out.printf(Constants.JOINED, name1, formatPeopleInGroup(name2, community),
                    community.getPerson(name2).getLandmarkName());
            community.joinGroup(name1, name2);
        }
    }


    /**
     * Returns a string with the names of the people in the group of a particular person.
     *
     * @param name      the name of the person.
     * @param community the community.
     * @return a string with the names of the people in the group of a particular person.
     */
    private static String formatPeopleInGroup(String name, Community community) {
        Person person = community.getPerson(name);
        Iterator<Person> it = community.getLandmark(person.getLandmarkName()).getGroup(person).listAllLPerson();
        return getAllPeopleInGroup(it);
    }

    /**
     * Returns a string with the names of the people in the group.
     *
     * @param it the iterator.
     * @return a string with the names of the people in the group.
     */
    private static String getAllPeopleInGroup(Iterator<Person> it) {
        String peopleInGroup = "";
        while (it.hasNext()) {
            Person person = it.next();
            peopleInGroup += (person.getName());
            if (it.hasNext()) {
                peopleInGroup += (", ");
            }
        }
        return peopleInGroup;
    }

    /**
     * Lists the groups composition in a landmark.
     * Returns an error message when:
     * the question is asked about groups at “home”;
     * the landmark does not exist;
     * nobody is in the landmark.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeGroupsCommand(Scanner in, Community community) {
        String landmarkName = in.nextLine().trim();
        if (landmarkName.equals(Constants.HOME)) {
            System.out.println(Constants.HOME_SURVEILLANCE);
        } else if (!community.hasLandmark(landmarkName)) {
            System.out.printf(Constants.NOT_EXISTS, landmarkName);
        } else if (community.getLandmark(landmarkName).getOccupation() == 0) {
            System.out.printf(Constants.NOBODY_AT_LANDMARK, landmarkName);
        } else {
            System.out.printf(Constants.GROUPS_AT_LANDMARK, community.getLandmark(landmarkName).getNumberOfGroups(), landmarkName);
            Iterator<Group> it = community.getLandmark(landmarkName).getAllGroups();
            while (it.hasNext()) {
                Group group = it.next();
                Iterator<Person> personIterator = group.listAllLPerson();
                System.out.println(getAllPeopleInGroup(personIterator));
            }
        }
    }


    /**
     * Makes a person leave the current group, but not the landmark the person is currently on.
     * Returns an error message when:
     * the person’s name does not exist in the community;
     * the person is at home;
     * the person is already alone.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeIsolateCommand(Scanner in, Community community) {
        String name = in.nextLine().trim();
        if (!community.hasPerson(name)) {
            System.out.printf(Constants.NOT_EXISTS, name);
        } else if (community.getPerson(name).isAtHome()) {
            System.out.printf(Constants.AT_HOME, name);
        } else if (community.isPersonAlone(name)) {
            System.out.printf(Constants.ALONE, name);
        } else {
            community.isolatePerson(name);
            System.out.printf(Constants.NOW_ALONE, name, community.getPerson(name).getLandmarkName());
        }
    }


    /**
     * Starts a new gossip.
     * Returns an error message when:
     * the person’s name does not exist in the community;
     * the number n of gossip targets is less or equal to 0;
     * any of the targets of the gossip does not exist;
     * there is already a similar gossip, with the same creator, involved people, and description.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeStartCommand(Scanner in, Community community) {
        String gossiper = in.nextLine().trim();
        int numberOfTargets = Integer.parseInt(in.nextLine());
        Array<String> targetsNamesArray = new ArrayClass<>();
        for (int i = 0; i < numberOfTargets; i++) {
            targetsNamesArray.insertLast(in.nextLine());
        }
        String description = in.nextLine();
        if (!community.hasPerson(gossiper)) {
            System.out.printf(Constants.NOT_EXISTS, gossiper);
        } else if (numberOfTargets <= 0) {
            System.out.printf(Constants.TARGETS, numberOfTargets);
        } else if (community.arePeopleCreated(numberOfTargets, targetsNamesArray) < numberOfTargets) {
            System.out.printf(Constants.NOT_EXISTS, targetsNamesArray.get(community.arePeopleCreated(numberOfTargets, targetsNamesArray)));
        } else {
            Array<Person> targetPersonArray = new ArrayClass<>();
            for (int i = 0; i < numberOfTargets; i++) {
                targetPersonArray.insertLast(community.getPerson(targetsNamesArray.get(i)));
            }
            if (!community.isGossipUnique(community.getPerson(gossiper), targetPersonArray, description, numberOfTargets)) {
                System.out.println(Constants.DUPLICATE);
            } else {
                System.out.printf(Constants.GOSSIP_STRING, formatTargetPeople(targetPersonArray), description);
                community.addGossip(gossiper, targetPersonArray, description, numberOfTargets);
            }
        }
    }

    private static String formatTargetPeople(Array<Person> targetPersonArray) {
        String peopleInGroup = "";
        for (int i = 0; i != targetPersonArray.size(); i++) {
            peopleInGroup += targetPersonArray.get(i).getName();
            if (i + 1 != targetPersonArray.size()) {
                peopleInGroup += ", ";
            }
        }
        return peopleInGroup;
    }

    /**
     * Share a gossip within the current group in the current landmark.
     * Returns an error message when:
     * the person’s name does not exist in the community;
     * the person is alone, either in a landmark, or at home;
     * the person has no gossips to share;
     * the person happens to be a “sealed lips” person, that person might actually know of a few gossips,
     * but if all of them are about someone else, the person would not be willing to share any of them.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the commuity.
     * @pre in != null && community != null
     */
    private static void executeGossipCommand(Scanner in, Community community) {
        String person = in.nextLine().trim();
        if (!community.hasPerson(person)) {
            System.out.printf(Constants.NOT_EXISTS, person);
        } else if (community.isPersonAlone(person)) {
            System.out.printf(Constants.NOBODY_TO_SHARE, person);
        } else if (community.getPerson(person).getKnownGossipsQuantity() == 0) {
            System.out.printf(Constants.KNOWS_NOTHING, person);
        } else if (!community.getPerson(person).canShareGossip()) {
            System.out.printf(Constants.NOT_WISH_GOSSIP, person);
        } else {
            community.shareGossip(person);
            Iterator<Person> it = community.getOtherPeopleInGroup(person);
            System.out.printf(Constants.SHARED, person, getAllPeopleInGroup(it));
            Iterator<Gossip> gossipIt = community.getLatestGossips(person);
            while (gossipIt.hasNext()) {
                Gossip gossip = gossipIt.next();
                System.out.println(gossip.getDescription());
            }
        }
    }


    /**
     * Lists the gossip about a particular person.
     * Returns an error messagem when:
     * the person’s name does not exist in the community;
     * the person has no secrets.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the commuity.
     * @pre in != null && community != null
     */
    private static void executeSecretsCommand(Scanner in, Community community) {
        String person = in.nextLine().trim();
        if (!community.hasPerson(person)) {
            System.out.printf(Constants.NOT_EXISTS, person);
        } else if (!community.isPersonATarget(person)){
            System.out.printf(Constants.BORING, person);
        } else {
            Iterator<Gossip> it = community.gossipsAboutPerson(person);
            System.out.printf(Constants.KNOWS_SECRETS, person);
            while (it.hasNext()) {
                Gossip gossip = it.next();
                System.out.printf(Constants.SECRET, community.getNumberAwarePeople(gossip), gossip.getDescription());
            }
        }
    }


    /**
     * Lists the gossips a particular person is aware of.
     * Returns an error message when:
     * the person’s name does not exist in the community;
     * the person knows nothing.
     *
     * @param in        the scanner to read necessary parameters to execute the command.
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeInfotainmentCommand(Scanner in, Community community) {
        String name = in.nextLine().trim();
        if (!community.hasPerson(name)) {
            System.out.printf(Constants.NOT_EXISTS, name);
        } else if (community.getPerson(name).getKnownGossipsQuantity() == 0) {
            System.out.printf(Constants.KNOWS_NOTHING, name);
        } else {
            System.out.printf(Constants.KNOWS_THINGS, name);
            Iterator<Gossip> allGossips = community.getPerson(name).listAllGossip();
            while (allGossips.hasNext()) {
                Gossip gossip = allGossips.next();
                System.out.println(gossip.getDescription());
            }
        }
    }


    /**
     * Lists the most shared gossip.
     * Returns an error message when:
     * There may be no known gossips;
     * There may be no shared gossips.
     *
     * @param community the community.
     * @pre in != null && community != null
     */
    private static void executeHottestCommand(Community community) {
        if (!community.hasAddedGossips()) {
            System.out.println(Constants.NO_GOSSIPS_ADDED);
        } else if (community.getNumberSharesMostShared() == 0) {
            System.out.println(Constants.NO_GOSSIPS_SHARED);
        } else {
            Iterator<Gossip> it = community.listMostSharedGossips();
            System.out.printf(Constants.HOTTEST, community.getNumberSharesMostShared());
            while (it.hasNext()) {
                Gossip gossip = it.next();
                System.out.println(gossip.getDescription());
            }
        }

    }
}


