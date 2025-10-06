/**
 * Class CerditClass - implements Credit.
 * A credit contains has a show and the participation type in the show of a certain artist.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

public class CreditClass implements Credit {

    /**
     * The participation of the artist in the show (actor, director or creator).
     */
    private final String participation;

    /**
     * The show associated with the credit.
     */
    private final Show show;

    /**
     * CreditClass constructor.
     * Creates a new credit.
     *
     * @param participation the participation of the artist in the show (actor, director or creator).
     * @param show          the show associated with the credit.
     * @pre participation != null && show != null
     */
    public CreditClass(String participation, Show show) {
        this.participation = participation;
        this.show = show;
    }

    /**
     * Compares two credits by release year, and then by the title.
     *
     * @param other the object to be compared.
     * @return a negative number if this credit has a more recent year of release
     * or a positive number if this credit has a less recent year of release.
     * In case of a tie, compares both title, returning a positive number if this title comes first
     * and a negative number if this title comes after.
     * @pre other != null
     */
    @Override
    public int compareTo(Credit other) {
        if (show.getReleaseDate() < other.getShow().getReleaseDate()) {
            return 1;
        }
        if (show.getReleaseDate() > other.getShow().getReleaseDate()) {
            return -1;
        }
        return show.getShowTitle().compareTo(other.getShow().getShowTitle());
    }

    @Override
    public String getParticipation() {
        return participation;
    }

    @Override
    public String getShowTitle() {
        return show.getShowTitle();
    }

    @Override
    public int getYearReleaseShow() {
        return show.getReleaseDate();
    }

    @Override
    public String getShowType() {
        return show.getShowType();
    }

    @Override
    public Show getShow() {
        return show;
    }
}
