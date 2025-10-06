/**
 * Interface Artist.
 * An artist can participate in shows as an actor, creator and director.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.Iterator;

public interface Artist extends Comparable<Artist> {

    /**
     * Verifys if the artists as the bio updated.
     *
     * @return returns true if the artists has the bio updated, and false if not.
     */
    boolean hasBio();

    /**
     * Updates the artist bio.
     *
     * @param bithdate   the date of birth of the artist.
     * @param birthplace the place of birth of the artist.
     */
    void setBio(String bithdate, String birthplace);

    /**
     * Adds a new credit to the artist.
     *
     * @param participation the type of participation of the artist in the show (actor, creator ou director).
     * @param show          the show.
     * @pre participation != null && show != null
     */
    void addCredit(String participation, Show show);

    /**
     * Returns an iterator for a LinkedList<String> that contains the bio of the artist.
     * The first element in the list is the date of birth, the second element is the place of birth.
     *
     * @return the bio of the artist.
     */
    Iterator<String> getBio();

    /**
     * Verifys if the artist has credits.
     *
     * @return true if the artist already has credits, and false if not.
     */
    boolean hasCredits();

    /**
     * Returns an iterator for the credis of the artist.
     *
     * @return an iterator for the credis of the artist.
     */
    Iterator<Credit> getCedits();

    /**
     * Returns the name of the artist.
     *
     * @return the name of the artist.
     */
    String getName();

    /**
     * Return the number of shows this artist has with the given artist.
     *
     * @param other the other artist.
     * @return the number of shows this artist has with the given artist.
     * @pre other != null
     */
    int howManyShowsTogether(Artist other);
}
