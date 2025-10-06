/**
 * Interface Show.
 * Each show can be eather a movie or a series.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.Iterator;

public interface Show extends Comparable<Show> {

    /**
     * Returns the title of the show.
     *
     * @return the title of the show.
     */
    String getShowTitle();

    /**
     * Returns the age certification o the show.
     *
     * @return the age certification o the show.
     */
    String getAgeCertification();

    /**
     * Return the release date of the show.
     *
     * @return the release date of the show.
     */
    int getReleaseDate();

    /**
     * Returns the type of the show (movie or series).
     *
     * @return the type of the show (movie or series).
     */
    String getShowType();

    /**
     * Adds a new review to the show.
     *
     * @param review the review.
     * @pre review != null
     */
    void addReview(Review review);


    /**
     * Returns the main genre of the show.
     *
     * @return the main genre of the show.
     */
    String getMainGenre();

    /**
     * Returns a String with, at most, 3 names of show cast.
     *
     * @return a String with, at most, 3 names of show cast.
     */
    String getCast();

    /**
     * Verifys if the given user already reviewed the show.
     *
     * @param reviewer the user.
     * @return true if the user already has reviewed the show, and false if not.
     * @pre reviewer != null
     */
    boolean userAlreadyReviewed(User reviewer);


    /**
     * Returns the average reviews value of the show.
     *
     * @return the average reviews value of the show.
     */
    float getAverageReviews();


    /**
     * Verifys if a show has reviews.
     *
     * @return true if the show has reviews, and false if not.
     */
    boolean hasReviews();

    /**
     * Returns an iterator for the reviews of the show.
     *
     * @return an iterator for the reviews of the show.
     */
    Iterator<Review> getReviews();

    /**
     * Verifys if the show has the given genre.
     *
     * @param genre the genre.
     * @return true if the show has the genre, and false if does not.
     * @pre genre != null
     */
    boolean hasGenre(String genre);

    /**
     * Return the number of reviews that the show has.
     *
     * @return the number of reviews that the show has.
     */
    int getNumberReviews();

    /**
     * Verifys if a given artist participated in the show.
     *
     * @param name the name of the artist.
     * @return true if the artist participated in the show, and false if not.
     * @pre name != null
     */
    boolean hasParticipation(String name);
}
