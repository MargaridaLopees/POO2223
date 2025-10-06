/**
 * Class Abstrata User.
 *
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

import DataStructure.Array;
import DataStructure.ArrayClass;
import DataStructure.Iterator;

public abstract class UserClass implements User {

    private final String name;
    private final String type;
    private int space;


    private Array<File> files;


    public UserClass(String email, String type, int space) {
        name = email;
        this.type = type;
        this.space = space;
        files = new ArrayClass<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSpace() {
        return space;
    }


    public abstract boolean canShare();

    public boolean hasOwnedFile(String file) {
        int i = 0;
        boolean found = true;
        while (i < files.size() && !files.get(i).getName().equals(file)) {
            i++;
        }
        if (i >= files.size()) {
            found = false;
        }
        return found;
    }

    public boolean hasSharedFile(String file, String owner) {
        int i = 0;
        boolean found = true;
        while (i < files.size() && !(files.get(i).getName().equals(file) && files.get(i).getOwnerName().equals(owner))) {
            i++;
        }
        if (i >= files.size()) {
            found = false;
        }
        return found;
    }

    public void addFile(String name, int size, String owner) {
        File file = new FileClass(name, size, owner);
        files.insertLast(file);
        setSpaceAddFile(size);
    }

    public void setSpaceAddFile(int size) {
        space = space - size;
    }

    public File getFile(String name) {
        int i = 0;
        while (i < files.size() && !files.get(i).getName().equals(name)) {
            i++;
        }
        return files.get(i);
    }

    public abstract boolean hasSharedCapacity(int size);

    public void addShareFile(File file) {
        files.insertLast(file);
        setSpaceSharedFile(file.getSize());
    }

    public abstract void setSpaceSharedFile(int size);

    public void setSpace(int newSpace) {
        space = newSpace;
    }

    public Iterator<File> iterator() {
        return files.iterator();
    }
}
