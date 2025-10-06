/**
 * Class Constants.
 * All the constants responsible for the feedback given by the program.
 *
 * @authors
 */
package engine;

public class Constants {

    private Constants() {
        // Empety on purpose
    }

    public static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";
    public static final String BYE = "Bye!";
    public static final String HELP = """
            register - registers a user in the system
            users - lists all registered users
            movie - uploads a new movie
            series - uploads a new series
            shows - lists all shows
            artist - adds bio information about an artist
            credits - lists the bio and credits of an artist
            review - adds a review to a show
            reviews - lists the reviews of a show
            genre - lists shows of given genres
            released - lists shows released in a given year
            avoiders - lists artists that have no common projects
            friends - lists artists that have more projects together
            help - shows the available commands
            exit - terminates the execution of the program""";
    public static final String USER_REGISTERED = "User %s was registered as %s.\n";
    public static final String UNKNOWN_USER_TYPE = "Unknown user type!";
    public static final String USER_ALREADY_EXISTS = "User %s already exists!\n";
    public static final String NO_USERS_REGISTERED = "No users registered.";
    public static final String ALL_USERS = "All registered users:";
    public static final String USER_POSTED = "User %s has posted %d reviews\n";
    public static final String ADMIN_UPLOADED = "Admin %s has uploaded %d shows\n";
    public static final String MOVIE_ADDED = "Movie %s (%d) was uploaded [%d new artists were created].\n";
    public static final String ADMIN_NOT_EXIST = "Admin %s does not exist!\n";
    public static final String WRONG_PASSWORD = "Invalid authentication!";
    public static final String SHOW_ALREADY_EXISTS = "Show %s already exists!\n";
    public static final String SERIES_ADDED = "Series %s (%d) was uploaded [%d new artists were created].\n";
    public static final String NO_SHOWS_ADDED = "No shows have been uploaded.";
    public static final String ALL_SHOWS = "All shows:";
    public static final String BIO_UPDATED = "%s bio was updated.\n";
    public static final String BIO_CREATED = "%s bio was created.\n";
    public static final String BIO_ALREADY_AVAILABLE = "Bio of %s is already available!\n";
    public static final String NO_INFORMATION = "No information about %s!\n";
    public static final String REVIEW = "Review for %s was registered [%d reviews].\n";
    public static final String USER_NOT_EXIST = "User %s does not exist!\n";
    public static final String ADMIN_CANNOT_REVIEW = "Admin %s cannot review shows!\n";
    public static final String SHOW_NOT_EXIST = "Show %s does not exist!\n";
    public static final String ALREADY_REVIEWED = "%s has already reviewed %s!\n";
    public static final String HAS_NO_REVIEWS = "Show %s has no reviews.\n";
    public static final String AVERAGE_REVIEWS = "Reviews of %s [%.1f]:\n";
    public static final String REVIEW_CLASSIFICATION = "Review of %s (%s): %s [%s]\n";
    public static final String NO_SHOWS_FOUND = "No show was found within the criteria.";
    public static final String SEARCH = "Search by genre:";
    public static final String GENRE_YEAR_MOVIE = "Movie %s by %s released on %d [%.1f]\n";
    public static final String GENRE_YEAR_SERIES = "Series %s by %s released on %d [%.1f]\n";
    public static final String SHOWS_RELEASED_ON = "Shows released on %d:\n";
    public static final String NO_ARTISTS = "No artists yet!";
    public static final String SMALL_WORLD = "It is a small world!";
    public static final String NUMBER_ARTISTS = "These %d artists never worked together:\n";
    public static final String NO_COLLAB = "No collaborations yet!";
    public static final String ARTISTS_WORKED_TOGETHER = "These artists have worked on %d projects together:\n";
    public static final String LIST_SHOW = "%s; %s; %d; %s; %d; %s; %s\n";
    public static final String LIST_CREDIT = "%s; %d; %s [%s]\n";


    // Other constants
    public static final String ADMIN = "admin";
    public static final String CRITIC = "critic";
    public static final String AUDIENCE = "audience";
    public static final String MOVIE = "movie";
    public static final String SERIES = "series";
    public static final String DIRECTOR = "director";
    public static final String CREATOR = "creator";
    public static final String ACTOR = "actor";
    public static final String EXCELLENT = "excellent";
    public static final String GOOD = "good";
    public static final String AVERAGE = "average";
    public static final String POOR = "poor";
    public static final String TERRIBLE = "terrible";


}
