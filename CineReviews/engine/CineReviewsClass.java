/**
 * Class CineReviewsClass - implements CineReviews.
 * CineReviews in an online application that collects reviews from movie critics and audience
 * to determine a movie or series overall score.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import exceptions.*;

import java.util.*;

public class CineReviewsClass implements CineReviews {

    /**
     * Collection of all users added in the application.
     * The key of this Map is the name of the user,
     * the value is the object User correspondent to the name.
     */
    private final SortedMap<String, User> allUsers;

    /**
     * Collection of all shows added in the application.
     * The key of this Map is the title of the show,
     * the value is the object Show correspondent to the tittle.
     */
    private final SortedMap<String, Show> allShows;

    /**
     * Colection of all artits added in the application.
     * The key of this Map is the name of the artist,
     * the value is the object Artist correspondent to the name.
     */
    private final SortedMap<String, Artist> allArtists;

    /**
     * Collection of all the colaborations beteewn artists.
     * The key is the name of the artist,
     * the value is a List of the names the artist colaborated wth.
     */
    private final SortedMap<String, List<String>> cooperations;

    /**
     * CineReviewsClass constructor.
     * Creates a new application CineReviews.
     */
    public CineReviewsClass() {
        allUsers = new TreeMap<>();
        allShows = new TreeMap<>();
        allArtists = new TreeMap<>();
        cooperations = new TreeMap<>();
    }


    @Override
    public void registerNewUser(String userType, String username, String password)
            throws UnknownUserTypeException, UsernameAlreadyExistsException {
        if (!isValidUserType(userType)) {
            throw new UnknownUserTypeException();
        } else if (allUsers.containsKey(username)) {
            throw new UsernameAlreadyExistsException();
        } else {
            addUser(userType, username, password);
        }
    }

    @Override
    public Iterator<User> listAllUsers() throws NoRegisteredUsersException {
        if (allUsers.isEmpty()) {
            throw new NoRegisteredUsersException();
        } else {
            return allUsers.values().iterator();
        }
    }

    @Override
    public int uploadNewMovie(String admin, String password, String showTitle, String director, int duration,
                              String ageCertification, int year, List<String> genres, List<String> cast)
            throws UnknownUserException, UserIsNotAdminException,
            WrongPasswordException, MovieTitleAlreadyExistsException {
        int newArtists = 0;
        if (!allUsers.containsKey(admin)) {
            throw new UnknownUserException();
        } else if (!(allUsers.get(admin) instanceof Admin)) {
            throw new UserIsNotAdminException();
        } else if (!((Admin) allUsers.get(admin)).isPasswordCorrect(password)) {
            throw new WrongPasswordException();
        } else if (allShows.containsKey(showTitle)) {
            throw new MovieTitleAlreadyExistsException();
        } else {
            newArtists = addMovie(showTitle, director, duration, ageCertification, year, genres, cast);
            addShowToAdmin(allUsers.get(admin), showTitle);
            populateMatrixGivenArtists(cast, director);
        }
        return newArtists;
    }

    @Override
    public int uploadNewSeries(String admin, String password, String seriesTitle, String creator, int noSeasons,
                               String ageCertification, int year, List<String> genres, List<String> cast)
            throws UnknownUserException, UserIsNotAdminException,
            WrongPasswordException, SeriesTitleAlreadyExistsException {
        int newArtists = 0;
        if (!allUsers.containsKey(admin)) {
            throw new UnknownUserException();
        } else if (!(allUsers.get(admin) instanceof Admin)) {
            throw new UserIsNotAdminException();
        } else if (!((Admin) allUsers.get(admin)).isPasswordCorrect(password)) {
            throw new WrongPasswordException();
        } else if (allShows.containsKey(seriesTitle)) {
            throw new SeriesTitleAlreadyExistsException();
        } else {
            newArtists = addSeries(seriesTitle, creator, noSeasons, ageCertification, year, genres, cast);
            addShowToAdmin(allUsers.get(admin), seriesTitle);
            populateMatrixGivenArtists(cast, creator);
        }
        return newArtists;
    }

    /**
     * Uploads the show into the admin user account.
     *
     * @param admin     the admin user.
     * @param showTitle the title of the show.
     * @pre admin != null && showTitle != null
     */
    private void addShowToAdmin(User admin, String showTitle) {
        ((Admin) admin).uploadShow(allShows.get(showTitle));
    }

    @Override
    public Iterator<Show> listAllShows() throws NoShowsUploadedException {
        if (allShows.isEmpty()) {
            throw new NoShowsUploadedException();
        } else {
            return allShows.values().iterator();
        }
    }

    @Override
    public void addBioInformation(String artistName, String birthDate, String birthPlace)
            throws ArtistAlreadyHasABioException {
        if (!allArtists.containsKey(artistName)) {
            allArtists.put(artistName, new ArtistClass(artistName));
            cooperations.put(artistName, new ArrayList<>());
        }
        if (allArtists.get(artistName).hasBio()) {
            throw new ArtistAlreadyHasABioException();
        } else {
            allArtists.get(artistName).setBio(birthDate, birthPlace);
        }
    }

    @Override
    public boolean hasArtist(String artistName) {
        return allArtists.containsKey(artistName);
    }

    @Override
    public int addNewReview(String username, String showTitle, String comment, String review)
            throws UnknownUserException, UserCannotReviewShowsException,
            UnknownShowException, UserAlreadyReviewedShowException {
        if (!allUsers.containsKey(username)) {
            throw new UnknownUserException();
        } else if (allUsers.get(username) instanceof Admin) {
            throw new UserCannotReviewShowsException();
        } else if (!allShows.containsKey(showTitle)) {
            throw new UnknownShowException();
        }
        Show show = allShows.get(showTitle);
        User user = allUsers.get(username);
        if (show.userAlreadyReviewed(user)) {
            throw new UserAlreadyReviewedShowException();
        } else {
            show.addReview(new ReviewClass(user, showTitle, comment, review));
            ((Reviewer) user).addReview(show);
        }
        return show.getNumberReviews();
    }

    @Override
    public boolean artistHasBio(String artistsName) throws UnknownArtistException {
        if (!allArtists.containsKey(artistsName)) {
            throw new UnknownArtistException();
        } else {
            return allArtists.get(artistsName).hasBio();
        }
    }

    @Override
    public Iterator<String> artistGetBio(String artistName) {
        return allArtists.get(artistName).getBio();
    }

    @Override
    public boolean artistHasCredits(String artistsName) {
        return allArtists.get(artistsName).hasCredits();
    }

    @Override
    public Iterator<Credit> getCredits(String artist) {
        return allArtists.get(artist).getCedits();
    }

    @Override
    public float getShowAverageReview(String show) throws UnknownShowException, ShowHasNoReviewsException {
        if (!allShows.containsKey(show)) {
            throw new UnknownShowException();
        }
        Show s = allShows.get(show);
        if (!s.hasReviews()) {
            throw new ShowHasNoReviewsException();
        } else {
            return s.getAverageReviews();
        }
    }

    @Override
    public Iterator<Review> getShowReviews(String show) {
        return allShows.get(show).getReviews();
    }

    @Override
    public Iterator<Show> getShowsWithGenres(List<String> genres) throws NoShowsWithTheGenresException {
        List<Show> showsWithGenres = new ArrayList<>(allShows.values());
        for (String s : genres) {
            showsWithGenres = getShowByGenre(s, showsWithGenres);
        }
        if (showsWithGenres.isEmpty()) {
            throw new NoShowsWithTheGenresException();
        }
        Collections.sort(showsWithGenres);
        return showsWithGenres.iterator();
    }

    @Override
    public Iterator<Show> getShowReleasedIn(int year) throws NoShowsInTheGivenDateException {
        List<Show> showsInTheYear = new ArrayList<>();
        for (Show s : allShows.values()) {
            if (s.getReleaseDate() == year) {
                showsInTheYear.add(s);
            }
        }
        if (showsInTheYear.isEmpty()) {
            throw new NoShowsInTheGivenDateException();
        }
        Collections.sort(showsInTheYear);
        return showsInTheYear.iterator();
    }

    /**
     * Returns a list of shows with the given genre.
     *
     * @param genre the genre.
     * @param shows the list of shows to work with.
     * @return a list of shows with the given genre.
     * @pre genre != null && shows != null
     */
    private List<Show> getShowByGenre(String genre, List<Show> shows) {
        List<Show> showsWithGenre = new ArrayList<>();
        for (Show s : shows) {
            if (s.hasGenre(genre)) {
                showsWithGenre.add(s);
            }
        }
        return showsWithGenre;
    }

    /**
     * Verifys if the type of user given is a valid type.
     * A valid user type is 'admin', 'critic' or 'audience'.
     *
     * @param type the type to verify.
     * @return true if is a valid type, and false if it is not.
     * @pre type != null
     */
    private boolean isValidUserType(String type) {
        return (type.equals(Constants.ADMIN) || type.equals(Constants.CRITIC) ||
                type.equals(Constants.AUDIENCE));
    }

    /**
     * Adds a new user to the application.
     *
     * @param userType the type of user.
     * @param username the name of the user.
     * @param password the password, in case the user type is admin.
     * @pre userType != null && username != null && password != null &&
     * !hasUser(username) && isValidUserType(userType)
     */
    private void addUser(String userType, String username, String password) {
        switch (userType) {
            case Constants.ADMIN -> allUsers.put(username, new Admin(username, password));
            case Constants.CRITIC -> allUsers.put(username, new Critic(username));
            case Constants.AUDIENCE -> allUsers.put(username, new Audience(username));
        }
    }

    /**
     * Adds a new movie to the application.
     *
     * @param movieTitle       the title of the movie.
     * @param director         the director of the movie.
     * @param duration         the duration, in minutes, of the movie.
     * @param ageCertification the age certification of the movie.
     * @param year             the year of release of the movie.
     * @param genres           the genres of the movie.
     * @param cast             the cast of the movie.
     * @return the number of new artists added into the application.
     * @pre admin != null && movieTitle != null && director != null && duration != null && ageCertification != null
     * && year != null && genres != null && cast != null
     */
    private int addMovie(String movieTitle, String director, int duration,
                         String ageCertification, int year, List<String> genres, List<String> cast) {
        allShows.put(movieTitle, new Movie(movieTitle, director, duration, ageCertification, year, genres, cast));
        return processCast(cast, movieTitle, director) + processCreatorDirector(director, movieTitle, Constants.DIRECTOR);
    }

    /**
     * Adds a new series to the application.
     *
     * @param seriesTitle      the title of the series.
     * @param creator          the creator of the series.
     * @param noSeasons        the number of seasons of the series.
     * @param ageCertification the age certification of the series.
     * @param year             the year of release of the series.
     * @param genres           the genres of the series.
     * @param cast             the cast of the series.
     * @return the number of new artists added into the application.
     * @pre admin != null && seriesTitle != null && creator != null && noSeasons != null && ageCertification != null
     * && year != null && genres != null && cast != null
     */
    private int addSeries(String seriesTitle, String creator, int noSeasons,
                          String ageCertification, int year, List<String> genres, List<String> cast) {
        allShows.put(seriesTitle, new Series(seriesTitle, creator, noSeasons, ageCertification, year, genres, cast));
        return processCast(cast, seriesTitle, creator) + processCreatorDirector(creator, seriesTitle, Constants.CREATOR);
    }


    /**
     * Verifys if the creator/director with the name given in an existent artits.
     * If it is not, the artist is created.
     * Adds the show with the name given to the credits of the artits.
     *
     * @param name       the name of the artits.
     * @param showTitle  the title of the show.
     * @param artistType the type of participation in the show of the artits
     *                   (creator in case the show is a series and director in case the show is a movie).
     * @pre name != null && showTitle != null && artistType != null && allShows.containsKey(showTitle)
     */
    private int processCreatorDirector(String name, String showTitle, String artistType) {
        int newArtist = 0;
        if (!allArtists.containsKey(name)) {
            allArtists.put(name, new ArtistClass(name));
            newArtist++;
        }
        allArtists.get(name).addCredit(artistType, allShows.get(showTitle));
        return newArtist;
    }

    /**
     * Varifys if each cast member is an artist that is already added in the aplication.
     * If not, the artist is added.
     * The show is added to the credits of the artist with the participation 'actor'.
     *
     * @param cast      the collection of the actors names that participate in the show.
     * @param showTitle the title of the show.
     * @pre cast != null && showTitle != null && allShows.containsKey(showTitle)
     */
    private int processCast(List<String> cast, String showTitle, String directorCreator) {
        int newArtists = 0;
        for (String actor : cast) {
            if (!actor.equals(directorCreator)) {
                if (!allArtists.containsKey(actor)) {
                    allArtists.put(actor, new ArtistClass(actor));
                    newArtists++;
                }
                allArtists.get(actor).addCredit(Constants.ACTOR, allShows.get(showTitle));
            }
        }
        return newArtists;
    }

    /**
     * Complete the collaboration map taking into account the given artists who worked on the same show.
     *
     * @param cast            the name sof the actors.
     * @param creatorDirector the name of the director or creator.
     * @pre cast != null && creatorDirector != null
     */
    private void populateMatrixGivenArtists(List<String> cast, String creatorDirector) {
        cast.add(creatorDirector);
        for (int i = 0; i != cast.size(); i++) {
            List<String> restOfCast = new ArrayList<>(cast);
            restOfCast.remove(cast.get(i));
            populateArtistCooperation(cast.get(i), restOfCast);
        }
        cast.remove(cast.size() - 1);
    }

    /**
     * Verifys if a given artist is already on the collaboration map
     * and adds the remaining artists as a collaboration.
     *
     * @param artistName the name of the artist.
     * @param restOfCast the rest of the artists that participated in the show.
     * @pre artistName != null && restOfCast != null
     */
    private void populateArtistCooperation(String artistName, List<String> restOfCast) {
        if (!cooperations.containsKey(artistName)) {
            cooperations.put(artistName, restOfCast);
        } else {
            for (String artist : restOfCast) {
                if (!cooperations.get(artistName).contains(artist)) {
                    cooperations.get(artistName).add(artist);
                }
            }
        }
    }

    /**
     * Creates a SortedSet with all the possible combinations of artists.
     * Returns an iterator for all the possible combinations of artists.
     *
     * @return an iterator for all the possible combinations of artists.
     */
    private Iterator<SortedSet<Artist>> powerSet() {
        SortedSet<SortedSet<Artist>> subsets = new TreeSet<>(new SetComparatorClass());
        subsets.add(new TreeSet<>());
        for (Artist artist : allArtists.values()) {
            SortedSet<SortedSet<Artist>> current = new TreeSet<>(new SetComparatorClass());
            for (SortedSet<Artist> set : subsets) {
                SortedSet<Artist> newSet = new TreeSet<>(set);
                newSet.add(artist);
                current.add(newSet);
            }
            subsets.addAll(current);
        }
        return subsets.iterator();
    }

    @Override
    public Iterator<SortedSet<Artist>> getAvoidersGroups()
            throws NoArtistsExistsInApplicationException, AllArtistsWorkedTogetherException {
        List<SortedSet<Artist>> result = new ArrayList<>();
        if (allArtists.isEmpty()) {
            throw new NoArtistsExistsInApplicationException();
        } else {
            Iterator<SortedSet<Artist>> allCombinations = powerSet();
            int biggestCombinationSize = 0;
            while (allCombinations.hasNext()) {
                SortedSet<Artist> artistCombination = allCombinations.next();
                if (artistCombination.size() < biggestCombinationSize || artistCombination.size() <= 1) {
                    // Found the biggest combination possible of avoiders - early stop!
                    break;
                }
                if (!doesGroupWorkTogether(artistCombination)) {
                    result.add(artistCombination);
                    biggestCombinationSize = artistCombination.size();
                }
            }
        }
        if (result.isEmpty()) {
            throw new AllArtistsWorkedTogetherException();
        } else {
            return result.iterator();
        }
    }

    /**
     * Verifys if any artist of the gigen group have worked together.
     *
     * @param artistCombination the group.
     * @return true if any of the artists of the group have worked together, and false if not.
     */
    private boolean doesGroupWorkTogether(SortedSet<Artist> artistCombination) {
        List<Artist> artists = artistCombination.stream().toList();
        boolean workedTogether = false;
        int i = 0;
        while (!workedTogether && i != artists.size()) {
            workedTogether = hasWorkedTogether(artists.get(i), artists);
            i++;
        }
        return workedTogether;
    }

    /**
     * Verifys if the artist worked with any of the other artists of the group.
     *
     * @param artist  the artist.
     * @param artists the other artists in the group.
     * @return true if the artist have worked with any of the other artists, of false if not.
     * @pre artist != null && artists != null
     */
    private boolean hasWorkedTogether(Artist artist, List<Artist> artists) {
        boolean workedTogether = false;
        int i = 0;
        List<String> cooperationsOfArtist = cooperations.get(artist.getName());
        while (!workedTogether && i != artists.size()) {
            workedTogether = cooperationsOfArtist.contains(artists.get(i).getName());
            i++;
        }
        return workedTogether;
    }

    //TODO
    private List<SortedSet<Artist>> allPairs() {
        List<SortedSet<Artist>> pairs = new ArrayList<>();
        Iterator<SortedSet<Artist>> allCombinations = powerSet();
        while (allCombinations.hasNext()) {
            SortedSet<Artist> group = allCombinations.next();
            if (group.size() == 2) {
                pairs.add(group);
            }
        }
        return pairs;
    }


    public Iterator<SortedSet<Artist>> friends()
            throws NoArtistsExistsInApplicationException, NoCollaborationsYetException {
        List<SortedSet<Artist>> pairs = allPairs();
        List<SortedSet<Artist>> artistsWithMostShowsTogether = new ArrayList<>();
        if (allArtists.isEmpty()) {
            throw new NoArtistsExistsInApplicationException();
        }
        int maxNumberOfShowsTogether = 1;
        for (SortedSet<Artist> pair : pairs) {
            int current = numberShowsTogether(pair);
            if (maxNumberOfShowsTogether == current) {
                artistsWithMostShowsTogether.add(pair);
            } else if (maxNumberOfShowsTogether < current) {
                maxNumberOfShowsTogether = current;
                artistsWithMostShowsTogether.clear();
                artistsWithMostShowsTogether.add(pair);
            }
        }
        if (artistsWithMostShowsTogether.isEmpty()) {
            throw new NoCollaborationsYetException();
        }
        return artistsWithMostShowsTogether.iterator();
    }

    @Override
    public int numberShowsTogether(SortedSet<Artist> pair) {
        return pair.first().howManyShowsTogether(pair.last());
    }


}
