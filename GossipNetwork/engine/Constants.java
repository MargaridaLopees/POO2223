/**
 * Constants class.
 * All the constants used in this work are in this class.
 *
 * @authors Margrida Lopes n64557 and Diogo Castelos n66045
 */

package engine;

public class Constants {

    private Constants() {
        //Empety on purpose
    }

    // TEXT WRITTEN BY THE USER _COMMANDS
    public static final String EXIT_COMMAND = "EXIT";
    public static final String HELP_COMMAND = "HELP";
    public static final String NEW_LANDMARK_COMMAND = "LANDMARK";
    public static final String ALL_LANDMARKS_COMMAND = "LANDMARKS";
    public static final String FORGETFUL_COMMAND = "FORGETFUL";
    public static final String GOSSIPER_COMMAND = "GOSSIPER";
    public static final String SEALED_COMMAND = "SEALED";
    public static final String PEOPLE_COMMAND = "PEOPLE";
    public static final String GO_COMMAND = "GO";
    public static final String JOIN_COMMAND = "JOIN";
    public static final String GROUPS_COMMAND = "GROUPS";
    public static final String ISOLATE_COMMAND = "ISOLATE";
    public static final String START_COMMAND = "START";
    public static final String GOSSIP_COMMAND = "GOSSIP";
    public static final String SECRETS_COMMAND = "SECRETS";
    public static final String INFOTAINMENT_COMMAND = "INFOTAINMENT";
    public static final String HOTTEST_COMMAND = "HOTTEST";


    // FEEDBACK WRITTEN BY THE PROGRAM IN THE CONSOLE
    public static final String EXIT = "Bye!";
    public static final String HELP = """
            landmark - adds a new landmark to the community
            landmarks - displays the list of landmarks in the community
            forgetful - adds a forgetful person to the community
            gossiper - adds a gossiper person to the community
            sealed - adds a sealed lips person to the community
            people - lists all the persons in the community
            go - moves a person to a landmark, or home
            join - joins a person to a group
            groups - lists the groups composition in a landmark
            isolate - makes a person leave the current group, but not the landmark the person is currently on
            start - starts a new gossip
            gossip - share a gossip within the current group in the current landmark
            secrets - lists the gossip about a particular person
            infotainment - lists the gossips a particular person is aware of
            hottest - lists the most shared gossip
            help - shows the available commands
            exit - terminates the execution of the program""";
    public static final String INVALID_LANDMARK_CAPACITY = "Invalid landmark capacity %d!\n";
    public static final String UNKNOWN = "Unknown command. Type help to see available commands.";
    public static final String LANDMARK_HOME = "Cannot create a landmark called home. You know, there is no place like home!";
    public static final String LANDMARK_ALREADY_EXISTS = "Landmark %s already exists!\n";
    public static final String LANDMARK_ADDED = "%s added.\n";
    public static final String ANY_LANDMARKS = "This community does not have any landmarks yet!";
    public static final String LIST_LANDMARK = "%s: %d %d.\n";
    public static final String FORGETFUL_PERSON = "%s can only remember up to %d gossips.";
    public static final String INVALID_GOSSIPS_CAPACITY = "Invalid gossips capacity %d!";
    public static final String GOSSIPER_PERSON = "%s is a gossiper.";
    public static final String ALREADY_EXISTS = "%s already exists!";
    public static final String SEALED_PERSON = "%s lips are sealed.";
    public static final String LIST_PEOPLE = "%s at %s knows %d gossips.";
    public static final String NOT_EXISTS = "%s does not exist!\n";
    public static final String UNKNOWN_LANDMARK = "Unknown place %s!";
    public static final String ALREADY_THERE = "What do you mean go to %s? %s is already there!";
    public static final String LANDMARK_FULL = "%s is too crowded! %s went home.";
    public static final String PERSON_MOVED = "%s is now at %s.";
    public static final String NEEDS_COMPANY = "%s needs company from someone else!\n";
    public static final String AT_HOME = "%s is at home!\n";
    public static final String IS_NOT_IN = "%s is not in %s!\n";
    public static final String SAME_GROUP = "%s and %s are already in the same group!\n";
    public static final String NOT_PEOPLE_YET = "This community does not have any people yet!";
    public static final String GROUPS_AT_LANDMARK = "%d groups at %s:\n";
    public static final String NOBODY_AT_LANDMARK = "Nobody is at %s!\n";
    public static final String HOME_SURVEILLANCE = "You must understand we have no surveillance tech at home! Privacy is our top concern!";
    public static final String ALONE = "%s is already alone!\n";
    public static final String NOW_ALONE = "%s is now alone at %s.\n";
    public static final String GOSSIP_STRING = "Have you heard about %s? %s\n";
    public static final String TARGETS = "Invalid number %d of gossip targets!\n";
    public static final String DUPLICATE = "Duplicate gossip!";
    public static final String JOINED = "%s joined %s, at the %s.\n";
    public static final String KNOWS_NOTHING = "%s knows nothing!\n";
    public static final String NOBODY_TO_SHARE = "%s has nobody to gossip with right now!\n";
    public static final String SECRET = "%d %s\n";
    public static final String NOT_WISH_GOSSIP = "%s does not wish to gossip right now!";
    public static final String BORING = "%s lives a very boring life!\n";
    public static final String SHARED = "%s shared with %s, some hot news!\n";
    public static final String KNOWS_THINGS = "%s knows things:\n";
    public static final String KNOWS_SECRETS = "Here is what we know about %s:\n";
    public static final String NO_GOSSIPS_ADDED = "No gossips we are aware of!";
    public static final String NO_GOSSIPS_SHARED = "No gossips were shared, yet!";
    public static final String HOTTEST = "The hottest gossips were shared %d times!\n";

    // OTHERS
    public static final String HOME = "home";
}
