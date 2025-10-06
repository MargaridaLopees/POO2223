/**
 * Main.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

import engine.*;
import exceptions.*;

import java.util.*;

public class Main {

    /**
     * Enum that defines the commands.
     * Text written by the user.
     */
    private enum Command {
        EXIT("EXIT"), HELP("HELP"), REGISTER("REGISTER"),
        USERS("USERS"), MOVIE("MOVIE"), SERIES("SERIES"),
        SHOWS("SHOWS"), ARTIST("ARTIST"), CREDITS("CREDITS"),
        REVIEW("REVIEW"), REVIEWS("REVIEWS"), GENRE("GENRE"),
        RELEASED("RELEASED"), AVOIDERS("AVOIDERS"), FRIENDS("FRIENDS"),
        UNKNOWN_COMMAND("");
        private final String commandName;

        Command(String commandName) {
            this.commandName = commandName;
        }

        public String getCommandName() {
            return commandName;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CineReviews cReviews = new CineReviewsClass();
        Command command;
        do {
            command = getCommand(in);
            executeCommand(in, cReviews, command);
        } while (!command.equals(Command.EXIT));
        in.close();
    }

    /**
     * Reads the new command to execute.
     *
     * @param in the scanner needed to read the input.
     * @pre in != null
     */
    private static Command getCommand(Scanner in) {
        String input = in.next().toUpperCase();
        for (Command command : Command.values())
            if (command.getCommandName().equals(input))
                return command;
        return Command.UNKNOWN_COMMAND;
    }

    /**
     * Command interpreter.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @param command  the command to execute.
     * @pre in != null && cReviews != null && command != null
     */
    private static void executeCommand(Scanner in, CineReviews cReviews, Command command) {
        switch (command) {
            // UNKNOW_COMMAND is a command that the program doesn't cover.
            case UNKNOWN_COMMAND -> System.out.println(Constants.UNKNOWN_COMMAND);
            // After de EXIT command, the program terminates.
            case EXIT -> System.out.println(Constants.BYE);
            // HELP command shows the available commands.
            case HELP -> System.out.println(Constants.HELP);
            case REGISTER -> executeRegister(in, cReviews);
            case USERS -> executeUsers(cReviews);
            case MOVIE -> executeMovie(in, cReviews);
            case SERIES -> executeSeries(in, cReviews);
            case SHOWS -> executeShows(cReviews);
            case ARTIST -> executeArtist(in, cReviews);
            case CREDITS -> executeCredits(in, cReviews);
            case REVIEW -> executeReview(in, cReviews);
            case REVIEWS -> executeReviews(in, cReviews);
            case GENRE -> executeGenre(in, cReviews);
            case RELEASED -> executeRelease(in, cReviews);
            case AVOIDERS -> executeAvoiders(cReviews);
            case FRIENDS -> executeFriends(cReviews);
        }
    }

    /**
     * Registers a user in the system.
     * This coomand receives the user type, and the username.
     * If the user type is admin, the command also receives the account password.
     * The command will show an error message if the user type is unknown,
     * or if the user identifier already exists.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeRegister(Scanner in, CineReviews cReviews) {
        String userType = in.next();
        String userName = in.next().trim();
        String password = null;
        if (userType.equals(Constants.ADMIN)) {
            password = in.next().trim();
        }
        in.nextLine();
        try {
            cReviews.registerNewUser(userType, userName, password);
            System.out.printf(Constants.USER_REGISTERED, userName, userType);
        } catch (UnknownUserTypeException e) {
            System.out.println(Constants.UNKNOWN_USER_TYPE);
        } catch (UsernameAlreadyExistsException e) {
            System.out.printf(Constants.USER_ALREADY_EXISTS, userName);
        }
    }

    /**
     * Lists all registeres users alphabetically ordered by identifier.
     * The command will show an error message when there are no registered users.
     *
     * @param cReviews the application.
     * @pre cReviews != null
     */
    private static void executeUsers(CineReviews cReviews) {
        try {
            printUsers(cReviews.listAllUsers());
        } catch (NoRegisteredUsersException e) {
            System.out.println(Constants.NO_USERS_REGISTERED);
        }
    }

    /**
     * Lists all users.
     *
     * @param it the iterator.
     * @pre it != null
     */
    private static void printUsers(Iterator<User> it) {
        System.out.println(Constants.ALL_USERS);
        while (it.hasNext()) {
            User user = it.next();
            if (user instanceof Admin) {
                System.out.printf(Constants.ADMIN_UPLOADED, user.getUserName(), ((Admin) user).getNumberShows());
            } else {
                System.out.printf(Constants.USER_POSTED, user.getUserName(), ((Reviewer) user).getNumberReviews());
            }
        }
    }

    /**
     * Uploads a new movie.
     * The command receives as argumensts the admin username and theirs password,
     * folloews by the movie title, the director's name, the duration in minutes,
     * the agr certification, the year of release, the genres and the name of each of the cast members.
     * Any of the artits that do not yet exist in the application are added.
     * The command will show an error message if the username does not exist or is not an admin user,
     * the password is wrong or if the movie title already exists.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeMovie(Scanner in, CineReviews cReviews) {
        String admin = in.next();
        String password = in.nextLine().trim();
        String movieTitle = in.nextLine();
        String director = in.nextLine();
        int duration = in.nextInt();
        in.nextLine();
        String ageCertification = in.nextLine();
        int year = in.nextInt();
        in.nextLine();
        int noGenre = in.nextInt();
        in.nextLine();
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < noGenre; i++) {
            genres.add(in.next().trim());
        }
        int noCast = in.nextInt();
        in.nextLine();
        List<String> cast = new ArrayList<>();
        for (int i = 0; i < noCast; i++) {
            cast.add(in.nextLine());
        }
        try {
            int noNewArtists = cReviews.uploadNewMovie(admin, password, movieTitle, director, duration,
                    ageCertification, year, genres, cast);
            System.out.printf(Constants.MOVIE_ADDED, movieTitle, year, noNewArtists);
        } catch (UnknownUserException | UserIsNotAdminException e) {
            System.out.printf(Constants.ADMIN_NOT_EXIST, admin);
        } catch (WrongPasswordException e) {
            System.out.println(Constants.WRONG_PASSWORD);
        } catch (MovieTitleAlreadyExistsException e) {
            System.out.printf(Constants.SHOW_ALREADY_EXISTS, movieTitle);
        }
    }

    /**
     * Uploads a new series. The command receives as arguments the admin username and their password,
     * the series title, the name of the creator, the number os seasons, the age certification,
     * the year of release, the series genres and the name of each of the cast members.
     * Any of the artists that do not yet exist in the application are added.
     * The command will show an error message if the username does not exist or is not an admin user,
     * the password is wrong or if the series title already exists.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeSeries(Scanner in, CineReviews cReviews) {
        String admin = in.next().trim();
        String password = in.nextLine().trim();
        String seriesTitle = in.nextLine();
        String creator = in.nextLine();
        int noSeasons = in.nextInt();
        in.nextLine();
        String ageCertification = in.nextLine();
        int year = in.nextInt();
        in.nextLine();
        int noGenre = in.nextInt();
        in.nextLine();
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < noGenre; i++) {
            genres.add(in.next().trim());
        }
        int noCast = in.nextInt();
        in.nextLine();
        List<String> cast = new ArrayList<>();
        for (int i = 0; i < noCast; i++) {
            cast.add(in.nextLine());
        }
        try {
            int noNewArtists = cReviews.uploadNewSeries(admin, password, seriesTitle, creator, noSeasons,
                    ageCertification, year, genres, cast);
            System.out.printf(Constants.SERIES_ADDED, seriesTitle, year, noNewArtists);
        } catch (UnknownUserException | UserIsNotAdminException e) {
            System.out.printf(Constants.ADMIN_NOT_EXIST, admin);
        } catch (WrongPasswordException e) {
            System.out.println(Constants.WRONG_PASSWORD);
        } catch (SeriesTitleAlreadyExistsException e) {
            System.out.printf(Constants.SHOW_ALREADY_EXISTS, seriesTitle);
        }
    }

    /**
     * List all shows.
     * For each movie, it will show the title, director, duration, age certification, release year, the main
     * genre (the first one to be given), and the cast. For each series, it will show the title, creator,
     * number of seasons, age certification, release year, the main genre, and the cast.
     * The command will show an error message if there are no shows uploaded in the application.
     *
     * @param cReviews the application.
     * @pre cReviews != null
     */
    private static void executeShows(CineReviews cReviews) {
        try {
            printAllShows(cReviews.listAllShows());
        } catch (NoShowsUploadedException e) {
            System.out.println(Constants.NO_SHOWS_ADDED);
        }
    }

    /**
     * Lists all shows.
     *
     * @param it the iterator.
     * @pre it != null
     */
    private static void printAllShows(Iterator<Show> it) {
        System.out.println(Constants.ALL_SHOWS);
        while (it.hasNext()) {
            Show show = it.next();
            if (show instanceof Movie) {
                System.out.printf(Constants.LIST_SHOW, show.getShowTitle(), ((Movie) show).getDirector(),
                        ((Movie) show).getDuration(), show.getAgeCertification(), show.getReleaseDate(),
                        show.getMainGenre(), show.getCast());
            } else {
                System.out.printf(Constants.LIST_SHOW, show.getShowTitle(), ((Series) show).getCreator(),
                        ((Series) show).getNumberSeasons(), show.getAgeCertification(),
                        show.getReleaseDate(), show.getMainGenre(), show.getCast());
            }
        }
    }

    /**
     * Adds bio information about an artist.
     * The command receives the artist name, date and place of birth.
     * If the artist does not exist in the application, meaning that
     * the artist has not participated in any movie or series, they are added.
     * The command will show an error message if the artist with that name already has a bio.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeArtist(Scanner in, CineReviews cReviews) {
        String artistName = in.nextLine().trim();
        String birthDate = in.nextLine();
        String birthPlace = in.nextLine();
        try {
            if (cReviews.hasArtist(artistName)) {
                cReviews.addBioInformation(artistName, birthDate, birthPlace);
                System.out.printf(Constants.BIO_UPDATED, artistName);
            } else {
                cReviews.addBioInformation(artistName, birthDate, birthPlace);
                System.out.printf(Constants.BIO_CREATED, artistName);
            }
        } catch (ArtistAlreadyHasABioException e) {
            System.out.printf(Constants.BIO_ALREADY_AVAILABLE, artistName);
        }
    }

    /**
     * Lists the bio and credits of an artist. This command receives as an argument the artist
     * name and presents their bio, if available, and all their credits sorted by release year (more recent
     * first), and then by title. For each credit it will show the title, release year, type of credit (actor,
     * director, creator), and whether is a movie or series.
     * The command will show an error message if the artist name does not exist.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeCredits(Scanner in, CineReviews cReviews) {
        String artistsName = in.nextLine().trim();
        try {
            if (cReviews.artistHasBio(artistsName)) {
                printArtistBio(cReviews, artistsName);
            }
            if (cReviews.artistHasCredits(artistsName)) {
                printArtistCredits(cReviews, artistsName);
            }
        } catch (UnknownArtistException e) {
            System.out.printf(Constants.NO_INFORMATION, artistsName);
        }
    }

    /**
     * Lists the bio information (date and place of birth) of an artist.
     *
     * @param cReviews   the application.
     * @param artistName the name of the artist.
     * @pre cReviews != null && artistName != null
     */
    private static void printArtistBio(CineReviews cReviews, String artistName) {
        Iterator<String> it = cReviews.artistGetBio(artistName);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * List the credits of the artist.
     *
     * @param cReviews the application.
     * @param artist   the name of the artist.
     * @pre cReviews != null && artistName != null
     */
    private static void printArtistCredits(CineReviews cReviews, String artist) {
        Iterator<Credit> it = cReviews.getCredits(artist);
        while (it.hasNext()) {
            Credit credit = it.next();
            System.out.printf(Constants.LIST_CREDIT, credit.getShowTitle(), credit.getYearReleaseShow(),
                    credit.getParticipation(), credit.getShowType());
        }
    }

    /**
     * Adds a review to a show. The command receives as arguments the username of the critic
     * or audience member, the show title, a comment, and the classification, which can be
     * excellent, good, average, poor, or terrible.
     * The command will show an error message if the username does not exist, the username is associated to an admin,
     * the title is not associated with any show or the show with title was already reviewed by the username.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeReview(Scanner in, CineReviews cReviews) {
        String username = in.next();
        String showTitle = in.nextLine().trim();
        String comment = in.nextLine();
        String review = in.nextLine();
        try {
            int noReviews = cReviews.addNewReview(username, showTitle, comment, review);
            System.out.printf(Constants.REVIEW, showTitle, noReviews);
        } catch (UnknownUserException e) {
            System.out.printf(Constants.USER_NOT_EXIST, username);
        } catch (UserCannotReviewShowsException e) {
            System.out.printf(Constants.ADMIN_CANNOT_REVIEW, username);
        } catch (UnknownShowException e) {
            System.out.printf(Constants.SHOW_NOT_EXIST, showTitle);
        } catch (UserAlreadyReviewedShowException e) {
            System.out.printf(Constants.ALREADY_REVIEWED, username, showTitle);
        }
    }

    /**
     * Lists the reviews of a show. This command receives as an argument the show title and
     * presents all its reviews.
     * The command will show an error message if the title is not associated with any show.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeReviews(Scanner in, CineReviews cReviews) {
        String showTitle = in.nextLine().trim();
        try {
            printShowReviews(cReviews, showTitle);
        } catch (UnknownShowException e) {
            System.out.printf(Constants.SHOW_NOT_EXIST, showTitle);
        } catch (ShowHasNoReviewsException e) {
            System.out.printf(Constants.HAS_NO_REVIEWS, showTitle);
        }
    }

    /**
     * Prints the reviews of a show.
     *
     * @param cReviews the application.
     * @param show     the name of the show.
     * @throws UnknownShowException      the exception for when the show is unknown.
     * @throws ShowHasNoReviewsException the exception for when the show has no reviews yet.
     * @pre cReviews 1= null && show != null
     */
    private static void printShowReviews(CineReviews cReviews, String show)
            throws UnknownShowException, ShowHasNoReviewsException {
        System.out.printf(Constants.AVERAGE_REVIEWS, show, cReviews.getShowAverageReview(show));
        Iterator<Review> it = cReviews.getShowReviews(show);
        while (it.hasNext()) {
            Review r = it.next();
            System.out.printf(Constants.REVIEW_CLASSIFICATION, r.getReviewer().getUserName(), r.getReviewerType(),
                    r.getComment(), r.getClassification());
        }
    }

    /**
     * Lists shows of given genres. This command receives as argument an integer number >= 1
     * of genres (noGenre) and the show genres. The program will list the shows that cover all
     * the genres ordered by score (average of reviews), then by release date, and lastly by title
     * The command will show an error message if there are no shows within the criteria.
     *
     * @param in       the scanner.
     * @param cReviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeGenre(Scanner in, CineReviews cReviews) {
        int noGenre = in.nextInt();
        in.nextLine();
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < noGenre; i++) {
            genres.add(in.next().trim());
        }
        try {
            printShowsWithGenres(cReviews, genres);
        } catch (NoShowsWithTheGenresException e) {
            System.out.println(Constants.NO_SHOWS_FOUND);
        }
    }

    /**
     * Prints the shows with the given genres.
     *
     * @param cReviews the application.
     * @param genres   the genres.
     * @throws NoShowsWithTheGenresException the exception for when there are no shows within the criteria.
     * @pre cReviews != null && genres != null
     */
    private static void printShowsWithGenres(CineReviews cReviews, List<String> genres) throws NoShowsWithTheGenresException {
        Iterator<Show> it = cReviews.getShowsWithGenres(genres);
        System.out.println(Constants.SEARCH);
        while (it.hasNext()) {
            Show s = it.next();
            printShowByCriteria(s);
        }
    }

    /**
     * Lists shows released on a given year. This command receives as argument a (release
     * date). The program will list the shows with the same release date ordered by average score, and
     * then by title.
     * The command will show an error message if no show was found within the criteria.
     *
     * @param in       the scanner.
     * @param cRiviews the application.
     * @pre in != null && cReviews != null
     */
    private static void executeRelease(Scanner in, CineReviews cRiviews) {
        int year = in.nextInt();
        in.nextLine();
        try {
            printShowsInGivenDate(cRiviews, year);
        } catch (NoShowsInTheGivenDateException e) {
            System.out.println(Constants.NO_SHOWS_FOUND);
        }
    }

    /**
     * Prints the shows released in the given date.
     *
     * @param cReviews the application.
     * @param year     the year.
     * @throws NoShowsInTheGivenDateException the exception for when no shows were found in the criteria.
     * @pre cReviews != null && year != null
     */
    private static void printShowsInGivenDate(CineReviews cReviews, int year) throws NoShowsInTheGivenDateException {
        Iterator<Show> it = cReviews.getShowReleasedIn(year);
        System.out.printf(Constants.SHOWS_RELEASED_ON, year);
        while (it.hasNext()) {
            Show s = it.next();
            printShowByCriteria(s);
        }
    }

    /**
     * Prints the show within the criteria.
     *
     * @param show the show to print.
     * @pre show != null
     */
    private static void printShowByCriteria(Show show) {
        if (show instanceof Movie) {
            System.out.printf(Constants.GENRE_YEAR_MOVIE, show.getShowTitle(), ((Movie) show).getDirector(),
                    show.getReleaseDate(), show.getAverageReviews());
        }
        if (show instanceof Series) {
            System.out.printf(Constants.GENRE_YEAR_SERIES, show.getShowTitle(), ((Series) show).getCreator(),
                    show.getReleaseDate(), show.getAverageReviews());
        }
    }

    /**
     * Artists that have no common projects. This command list the largest group of artists
     * that have never participated in a common movie or series.
     * The command will show an error message if no artist exist in the application,
     * or if all the artits have worked together.
     *
     * @param cReviews the application.
     * @pre cReviews != null
     */
    private static void executeAvoiders(CineReviews cReviews) {
        try {
            printAvoiders(cReviews);
        } catch (NoArtistsExistsInApplicationException e) {
            System.out.println(Constants.NO_ARTISTS);
        } catch (AllArtistsWorkedTogetherException e) {
            System.out.println(Constants.SMALL_WORLD);
        }
    }

    /**
     * Prints the largest group of artists
     * that have never participated in a common movie or series.
     *
     * @param cReviews the application.
     * @throws NoArtistsExistsInApplicationException the exception for when there are no artist in the application.
     * @throws AllArtistsWorkedTogetherException     the exception for when all artists have worked together.
     * @pre cReviews != null
     */
    private static void printAvoiders(CineReviews cReviews)
            throws NoArtistsExistsInApplicationException, AllArtistsWorkedTogetherException {
        Iterator<SortedSet<Artist>> it = cReviews.getAvoidersGroups();
        SortedSet<Artist> group = it.next();
        System.out.printf(Constants.NUMBER_ARTISTS, group.size());
        printGroupWithComma(group);
        while (it.hasNext()) {
            printGroupWithComma(it.next());
        }
    }

    /**
     * Prints a group with a coma as delimiter.
     *
     * @param group the group to print.
     * @pre group != null
     */
    private static void printGroupWithComma(SortedSet<Artist> group) {
        printGroup(group.iterator(), ", ");
    }

    /**
     * Prints a group with "and" as delimiter.
     *
     * @param group the group to print.
     * @pre group != null
     */
    private static void printGroupWithAnd(SortedSet<Artist> group) {
        printGroup(group.iterator(), " and ");
    }

    /**
     * Prints a group with the delimiter given.
     *
     * @param group     the group to print.
     * @param delimiter the delimiter to use.
     * @pre group != null && delimiter != null
     */
    private static void printGroup(Iterator<Artist> group, String delimiter) {
        while (group.hasNext()) {
            System.out.print(group.next().getName());
            if (group.hasNext()) {
                System.out.print(delimiter);
            }
        }
        System.out.println();
    }

    /**
     * Artists that have more projects together. This command lists the pairs of artists that
     * have more projects (movies or series) together.
     * The command shows an error message when there are no artists in the application,
     * or there are no collaborations yet.
     *
     * @param cReviews the application.
     * @pre cReviews != null
     */
    private static void executeFriends(CineReviews cReviews) {
        try {
            printFriends(cReviews);
        } catch (NoArtistsExistsInApplicationException e) {
            System.out.println(Constants.NO_ARTISTS);
        } catch (NoCollaborationsYetException e) {
            System.out.println(Constants.NO_COLLAB);
        }
    }

    /**
     * Prints the pairs of artists that have more projects together.
     *
     * @param cReviews the application.
     * @throws NoArtistsExistsInApplicationException the exception for when there are no artists in the application.
     * @throws NoCollaborationsYetException          the exception for when there are no collaborations yet.
     * @pre cReviews != null
     */
    private static void printFriends(CineReviews cReviews)
            throws NoArtistsExistsInApplicationException, NoCollaborationsYetException {
        Iterator<SortedSet<Artist>> it = cReviews.friends();
        SortedSet<Artist> pair = it.next();
        System.out.printf(Constants.ARTISTS_WORKED_TOGETHER, cReviews.numberShowsTogether(pair));
        printGroupWithAnd(pair);
        while (it.hasNext()) {
            printGroupWithAnd(it.next());
        }
    }
}