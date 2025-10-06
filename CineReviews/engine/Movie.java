/**
 * Class Movie - extends ShowClass.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.List;

public class Movie extends ShowClass {

    /**
     * The name of the director of the movie.
     */
    private final String director;

    /**
     * The duration, in minutes, of the movie.
     */
    private final int duration;

    /**
     * Movie constructor.
     * Creates a new movie.
     *
     * @param title            the title of the movie.
     * @param director         the name of the director of the movie.
     * @param duration         the duration, in minutes, of the movie.
     * @param ageCertification the age certification of the movie.
     * @param releaseDate      the year of release of the movie.
     * @param genres           the collection fo genres of the movie.
     * @param cast             the collection of actors involved in the movie.
     * @pre title != null && director != null && duration != null && ageCertification != null &&
     * releaseDate != null && genres != null && cast != null
     */
    public Movie(String title, String director, int duration, String ageCertification, int releaseDate,
                 List<String> genres, List<String> cast) {
        super(title, ageCertification, releaseDate, genres, cast);
        this.director = director;
        this.duration = duration;
    }

    /**
     * Returns the name of the director of the movie.
     *
     * @return the name of the director of the movie.
     */
    public String getDirector() {
        return director;
    }


    /**
     * Returns the duration, in minutes, of the movie.
     *
     * @return the duration, in minutes, of the movie.
     */
    public int getDuration() {
        return duration;
    }


    @Override
    boolean isCreatorDirector(String name) {
        return director.equals(name);
    }
}
