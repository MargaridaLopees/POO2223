/**
 * Class ArtistClass - implements Artist.
 *
 * @authors Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

public class ArtistClass implements Artist {

    /**
     * The artist name.
     */
    private final String name;

    /**
     * The date of birth of the artist.
     * Initially with default value until the bio is updated.
     */
    private String birthdate;

    /**
     * The place of birth of the artist.
     * Initially with default value until the bio is updated.
     */
    private String birthplace;

    /**
     * Collection of all shows where the artist participated (as an actor, director or creator).
     */
    private final SortedSet<Credit> credits;

    /**
     * ArtistClass constructor.
     * Creates a new artist.
     * Each artist is created without bio information.
     *
     * @param name the name of the artist.
     */
    public ArtistClass(String name) {
        this.name = name;
        birthdate = null;
        birthplace = null;
        credits = new TreeSet<>();
    }

    @Override
    public boolean hasBio() {
        return !(birthplace == null && birthdate == null);
    }

    @Override
    public void setBio(String bithdate, String birthplace) {
        this.birthdate = bithdate;
        this.birthplace = birthplace;
    }

    @Override
    public void addCredit(String participation, Show show) {
        credits.add(new CreditClass(participation, show));
    }

    @Override
    public Iterator<String> getBio() {
        LinkedList<String> bio = new LinkedList<>();
        bio.add(birthdate);
        bio.add(birthplace);
        return bio.iterator();
    }

    @Override
    public boolean hasCredits() {
        return !credits.isEmpty();
    }

    @Override
    public Iterator<Credit> getCedits() {
        return credits.iterator();
    }

    @Override
    public String getName() {
        return name;
    }

    public int compareTo(Artist other) {
        return name.compareTo(other.getName());
    }

    public int howManyShowsTogether(Artist other) {
        int numberShows = 0;
        for (Credit credit : credits) {
            if (credit.getShow().hasParticipation(other.getName())) {
                numberShows++;
            }
        }
        return numberShows;
    }
}
