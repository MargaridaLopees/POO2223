/**
 * Class ShowClass - implements Show.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ShowClass implements Show {


    /**
     * The title of the show.
     */
    private final String showTitle;


    /**
     * The age certification ofthe show.
     */
    private final String ageCertification;


    /**
     * The release date of the show.
     */
    private final int releaseDate;


    /**
     * The collection of genres of the show.
     */
    private final List<String> genres;


    /**
     * The collection of actors involved in the show.
     */
    private final List<String> cast;


    /**
     * The collection of all reviews made about the show.
     */
    private final List<Review> allReviews;


    /**
     * ShowClass constructor.
     *
     * @param showTitle        the title of the show.
     * @param ageCertification the age certification of the show.
     * @param releaseDate      the release date of the show.
     * @param genres           the collection of genres of the show.
     * @param cast             the collection of actors involved in the show.
     * @pre showTitle != null && ageCertification != null && releaseDate != null &&
     * genres != null && cast != null
     */
    public ShowClass(String showTitle, String ageCertification, int releaseDate,
                     List<String> genres, List<String> cast) {
        this.showTitle = showTitle;
        this.ageCertification = ageCertification;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.cast = cast;
        allReviews = new ArrayList<>();
    }


    @Override
    public String getShowTitle() {
        return showTitle;
    }


    @Override
    public String getAgeCertification() {
        return ageCertification;
    }


    @Override
    public int getReleaseDate() {
        return releaseDate;
    }


    public String getShowType() {
        String type = null;
        if (this instanceof Movie) {
            type = Constants.MOVIE;
        } else if (this instanceof Series) {
            type = Constants.SERIES;
        }
        return type;
    }

    public void addReview(Review review) {
        allReviews.add(review);
    }


    // The main genre of a how is the first genre in the collection.
    public String getMainGenre() {
        return genres.get(0);
    }

    // A string is concatenated with at least three actor names.
    public String getCast() {
        String castMembers = "";
        int i = 0;
        while (i < cast.size() && i < 3) {
            castMembers += cast.get(i);
            if (i + 1 < cast.size() && i + 1 < 3) {
                castMembers += "; ";
            }
            i++;
        }
        return castMembers;
    }

    public boolean userAlreadyReviewed(User reviewer) {
        boolean found = false;
        int i = 0;
        while (!found && i != allReviews.size()) {
            if (allReviews.get(i).getReviewer().getUserName().equals(reviewer.getUserName())) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public float getAverageReviews() {
        float dividend = 0;
        float divider = 0;
        float result = 0;
        for (Review r : allReviews) {
            dividend += (r.getScore() * ((Reviewer) r.getReviewer()).getReviewImportance());
            divider += ((Reviewer) r.getReviewer()).getReviewImportance();
            result = dividend / divider;
        }
        return result;
    }

    public boolean hasReviews() {
        return allReviews.size() > 0;
    }

    public Iterator<Review> getReviews() {
        Collections.sort(allReviews);
        return allReviews.iterator();
    }

    public boolean hasGenre(String genre) {
        return genres.contains(genre);
    }

    /**
     * Compares this show with the other show.
     * It will compare the shows by score, then by release date, and then by title.
     *
     * @param other the object to be compared.
     * @return a positive int if this average reviews is lower than the other show,
     * and a negative int if this average reviews is higher than the other show.
     * If this release date is higher, returns a negative int, and if is lower, return a positive int.
     * Lastly, compare by alphabetic order the show title.
     * @pre other != null
     */
    public int compareTo(Show other) {
        if (getAverageReviews() < other.getAverageReviews()) {
            return 1;
        }
        if (getAverageReviews() > other.getAverageReviews()) {
            return -1;
        }
        if (getReleaseDate() < other.getReleaseDate()) {
            return 1;
        }
        if (getReleaseDate() > other.getReleaseDate()) {
            return -1;
        }
        return getShowTitle().compareTo(other.getShowTitle());
    }

    public int getNumberReviews() {
        return allReviews.size();
    }

    public boolean hasParticipation(String name) {
        return (cast.contains(name) || isCreatorDirector(name));
    }

    /**
     * Verifys if the given name of the artist coresponds to the director or creator of the show.
     *
     * @param name the name of the artist.
     * @return true if correspondes and false if not.
     * @pre name != null
     */
    abstract boolean isCreatorDirector(String name);
}
