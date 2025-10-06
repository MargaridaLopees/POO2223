/**
 * Interface Credit.
 * A credit contains has a show and the participation type in the show of a certain artist.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public interface Credit extends Comparable<Credit> {

    /**
     * Returns the participation type of the artist in the show of the credit.
     *
     * @return the participation type of the artist in the show of the credit.
     */
    String getParticipation();

    /**
     * Returns the title of the show of the credit.
     *
     * @return the title of the show of the credit.
     */
    String getShowTitle();

    /**
     * Returns the year of release of the show of the credit.
     *
     * @return the year of release of the show of the credit.
     */
    int getYearReleaseShow();

    /**
     * Returns the type of the show of the credit (movie or serie).
     *
     * @return the type of the show of the credit (movie or serie).
     */
    String getShowType();

    /**
     * Returns the show of the credit.
     *
     * @return the show of the credit.
     */
    Show getShow();


}
