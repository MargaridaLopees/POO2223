/**
 * Class Series - extends ShowClass.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.List;

public class Series extends ShowClass {

    /**
     * The name of the creator of the series.
     */
    private final String creator;

    /**
     * The number of seasons that the series have.
     */
    private final int numberSeasons;

    /**
     * Series constructor.
     * Creates a new show series.
     *
     * @param seriesTitle      the title of the series.
     * @param creator          the name of the creator of the series.
     * @param numberSeasons    the numberof seasons that the series have.
     * @param ageCertification the age certification of the series.
     * @param releaseDate      the release year of the series.
     * @param genres           the collection of genres of the series.
     * @param cast             the collection of actors hat participate in the series.
     * @pre seriesTitle != null && creator != null && numberSeason != null && ageGertification != null
     * && releaseDate != null && genres != null && cast != null
     */
    public Series(String seriesTitle, String creator, int numberSeasons, String ageCertification,
                  int releaseDate, List<String> genres, List<String> cast) {
        super(seriesTitle, ageCertification, releaseDate, genres, cast);
        this.creator = creator;
        this.numberSeasons = numberSeasons;
    }

    /**
     * Returns the name of the creator of the series.
     *
     * @return the name of the creator of the series.
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Returns the number of seasons of the series.
     *
     * @return the number of seasons of the series
     */
    public int getNumberSeasons() {
        return numberSeasons;
    }

    @Override
    boolean isCreatorDirector(String name) {
        return creator.equals(name);
    }
}
