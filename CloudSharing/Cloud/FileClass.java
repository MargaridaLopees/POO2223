/**
 * Classe FileClass.
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

import DataStructure.Iterator;

public class FileClass implements File{

    private final String name;

    private final String owner;
    private int size;

    public FileClass(String name, int size, String owner) {
        this.name = name;
        this.size = size;
        this.owner = owner;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOwnerName() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }
}
