/**
 * Class SetComparatorClass.
 * Compares two sets of artists.
 * Margarida Lopes n64557, Diogo Castelos n66045
 */

package engine;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class SetComparatorClass implements Comparator<SortedSet<Artist>> {

    /**
     * Compares two set os Artist in alphabetic order.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     */
    @Override
    public int compare(SortedSet<Artist> o1, SortedSet<Artist> o2) {
        int comp = o2.size() - o1.size();
        if (comp == 0) {
            Iterator<Artist> it1 = o1.iterator();
            Iterator<Artist> it2 = o2.iterator();
            while (it1.hasNext() && it2.hasNext() && comp == 0)
                comp = it1.next().compareTo(it2.next());
        }
        return comp;
    }
}

