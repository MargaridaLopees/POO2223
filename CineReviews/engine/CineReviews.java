/**
 * Interface CineReviews.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import exceptions.*;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

public interface CineReviews {

    /**
     * Registers a new user to the application.
     *
     * @param userType the type of the user.
     * @param userName the user name.
     * @param password the password (in case the userType is admin).
     * @throws UnknownUserTypeException       the exception for when the userType is unknown.
     * @throws UsernameAlreadyExistsException the exception for when already exists a user with that identifier.
     * @pre userType != null && userName != null && password != null
     */
    void registerNewUser(String userType, String userName, String password)
            throws UnknownUserTypeException, UsernameAlreadyExistsException;

    /**
     * Lists all registered users alphabetically ordered by identifier.
     *
     * @return an iterator that lists all registered users alphabetically ordered by identifier.
     * @throws NoRegisteredUsersException the exception for when there are no registered users in the application.
     */
    Iterator<User> listAllUsers() throws NoRegisteredUsersException;

    /**
     * Uploads a new movie.
     *
     * @param admin            the admin username.
     * @param password         the password for the admin account.
     * @param movieTitle       the title of the movie.
     * @param director         the director of the movie.
     * @param duration         the duration in minutes of the movie.
     * @param ageCertification the age certification of the movie.
     * @param year             the year of release of the movie.
     * @param genres           the genres of the movie.
     * @param cast             the cast of the movie.
     * @return the number of new artists added.
     * @throws UnknownUserException             the exception for when the user does not exist.
     * @throws UserIsNotAdminException          the exception for when the user is not an admin user.
     * @throws WrongPasswordException           the exception for when the password is not the correct one.
     * @throws MovieTitleAlreadyExistsException the exception for when the movie already exists in the application.
     * @pre admin != null && password != null && movieTitle != null && director != null && duration != null &&
     * agecertification != null && year != null && genres != null && cast != null
     */
    int uploadNewMovie(String admin, String password, String movieTitle, String director, int duration,
                       String ageCertification, int year, List<String> genres, List<String> cast)
            throws UnknownUserException, UserIsNotAdminException, WrongPasswordException, MovieTitleAlreadyExistsException;

    /**
     * Uploads a new series.
     *
     * @param admin            the admin username.
     * @param password         the password for the admin account.
     * @param seriesTitle      the title of the series.
     * @param creator          the creator of the series.
     * @param noSeasons        the number of seasons of the series.
     * @param ageCertification the age certification of the series.
     * @param year             the year of release of the series.
     * @param genres           the genres of the series.
     * @param cast             the cast of the series.
     * @return the number of new artits added.
     * @throws UnknownUserException              the exceotion for when the user does not exist.
     * @throws UserIsNotAdminException           the exception for when the user is not an admin user.
     * @throws WrongPasswordException            the exception for when the password is not the correct one.
     * @throws SeriesTitleAlreadyExistsException the exception for when the movie already exists in the application.
     * @pre admin != null && password != null && seriesTitle != null && creator != null && noSeasons != null
     * && ageCertification != null && year != null && genres != null && cast != null
     */
    int uploadNewSeries(String admin, String password, String seriesTitle, String creator, int noSeasons,
                        String ageCertification, int year, List<String> genres, List<String> cast)
            throws UnknownUserException, UserIsNotAdminException, WrongPasswordException, SeriesTitleAlreadyExistsException;

    /**
     * List all shows.
     *
     * @return an iterator that lists all shows uploaded in the application.
     * @throws NoShowsUploadedException the exception for when there are no shows uploaded in the application.
     */
    Iterator<Show> listAllShows() throws NoShowsUploadedException;

    /**
     * Adds bio information about an artist.
     * If the artist does not exist in the application, meaning that
     * the artist has not participated in any movie or series, they are added.
     *
     * @param artistName the artist name.
     * @param birthDate  the date of birth of the artist.
     * @param birthPlace the place of birth of the artist.
     * @throws ArtistAlreadyHasABioException the exception for when the artist already has a bio.
     * @pre artistName != null && birthDate != null && birthPlace != null
     */
    void addBioInformation(String artistName, String birthDate, String birthPlace) throws ArtistAlreadyHasABioException;

    /**
     * Verifys if the artist with the given name exists in the application.
     *
     * @param artistName the name of the artist.
     * @return true se o artista existir e false se nao existir.
     * @pre artistName != null
     */
    boolean hasArtist(String artistName);

    /**
     * Adds a review to a show.
     *
     * @param username  the name of the critic or audience member.
     * @param showTitle the title of the show.
     * @param comment   the comment of the review.
     * @param review    the classification of the review (excellent, good, average, poor or terrible).
     * @return the total number of reviews that the show currently have.
     * @throws UnknownUserException             the exception for when the user does not exist.
     * @throws UserCannotReviewShowsException   the exception for when the user with the name given is an admin user.
     * @throws UnknownShowException             the exception for when the show does not exist.
     * @throws UserAlreadyReviewedShowException the exception for when the user already reviewed the show.
     * @pre username != null && showTitle != null && comment != null && review != null
     */
    int addNewReview(String username, String showTitle, String comment, String review)
            throws UnknownUserException, UserCannotReviewShowsException,
            UnknownShowException, UserAlreadyReviewedShowException;

    /**
     * Verifys if the artist with the given name has bio information.
     *
     * @param artistsName the name of the artist
     * @return true if the artist has bio information, and false if not.
     * @throws UnknownArtistException the exception for when the artist is unknown.
     * @pre artistName != null
     */
    boolean artistHasBio(String artistsName) throws UnknownArtistException;

    /**
     * Returns an iterator for the bio of the artist.
     *
     * @param artistName the name of the artist.
     * @return an iterator for the bio of the artist.
     * @pre artistName != null
     */
    Iterator<String> artistGetBio(String artistName);

    /**
     * Verifys if an artist with the given name has credits.
     *
     * @param artistsName the name of the artits.
     * @return true if the artist has credits, and false if not.
     * @pre artistName != null
     */
    boolean artistHasCredits(String artistsName);

    /**
     * Returns an iterator for the credits of the artist with the name given.
     *
     * @param artist the name of the artist.
     * @return an iterator for the credits of the artist with the name given.
     * @pre artist != null
     */
    Iterator<Credit> getCredits(String artist);

    /**
     * Returns the average review value of a show.
     *
     * @param show the name of th show.
     * @return the average review value of a show.
     * @throws UnknownShowException      the exception for when the show is unknown.
     * @throws ShowHasNoReviewsException the exception for when the show has no reviews yet.
     * @pre show != null
     */
    float getShowAverageReview(String show) throws UnknownShowException, ShowHasNoReviewsException;

    /**
     * Returns an iterator for the reviews of a show.
     *
     * @param show the show.
     * @return an iterator for the reviews of a show.
     * @pre show != null
     */
    Iterator<Review> getShowReviews(String show);

    /**
     * Returns an iterator for the shows with the given genres.
     *
     * @param genres the genres.
     * @return an iterator for the shows with the given genres.
     * @throws NoShowsWithTheGenresException the exception for when there are no shows with the given genres.
     * @pre genres != null
     */
    Iterator<Show> getShowsWithGenres(List<String> genres) throws NoShowsWithTheGenresException;

    /**
     * Returns an iterator for the shows released in the given year.
     *
     * @param year the year.
     * @return an iterator for the shows released in the given year.
     * @throws NoShowsInTheGivenDateException the exception for when there are shows released in the given year.
     * @pre year != null
     */
    Iterator<Show> getShowReleasedIn(int year) throws NoShowsInTheGivenDateException;


    /**
     * Returns an iterator for the largest group of artists that have never worked together.
     *
     * @return an iterator for the largest group of artists that have never worked together.
     * @throws NoArtistsExistsInApplicationException the exception for when there are no artists in the application.
     * @throws AllArtistsWorkedTogetherException     the exception for when all artists have worked together.
     */
    Iterator<SortedSet<Artist>> getAvoidersGroups()
            throws NoArtistsExistsInApplicationException, AllArtistsWorkedTogetherException;


    /**
     * Returns an iterator for all grups have more shows together.
     *
     * @return an iterator for all grups have more shows together.
     * @throws NoArtistsExistsInApplicationException the exception for when teher are no artist in the application.
     * @throws NoCollaborationsYetException          the exception for when there are no colaborations yet.
     */
    Iterator<SortedSet<Artist>> friends()
            throws NoArtistsExistsInApplicationException, NoCollaborationsYetException;


    /**
     * Returns the number of shows the group worked together.
     *
     * @param group the group of artists.
     * @return the number of shows the group worked together.
     * @pre group != null
     */
    int numberShowsTogether(SortedSet<Artist> group);
}
