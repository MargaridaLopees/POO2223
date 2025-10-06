
/**
 * Class CloudSharing.
 *
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

import DataStructure.Array;
import DataStructure.ArrayClass;
import DataStructure.Iterator;

public class CloudSharingClass implements CloudSharing {

    private static final String BASIC_INPUT = "basic";
    private static final String BASIC_OUTPUT = "Basic";
    private static final String PREMIUM_INPUT = "premium";
    private static final String PREMIUM_OUTPUT = "Premium";

    private Array<User> users;


    public CloudSharingClass() {
        users = new ArrayClass<>();
    }

    @Override
    public boolean hasUser(String email) {
        int i = 0;
        boolean found = true;
        while (i < users.size() && !users.get(i).getName().equals(email)) {
            i++;
        }
        if (i >= users.size()) {
            found = false;
        }
        return found;
    }

    /**
     * Devolve um utilizador, dado um nome.
     *
     * @param name o nome do utilizador.
     * @return o utilizador com o nome dado.
     * @pre name != null && hasUser(name)
     */
    private User getUser(String name) {
        int i = 0;
        while (i < users.size() && !users.get(i).getName().equals(name)) {
            i++;
        }
        return users.get(i);
    }

    @Override
    public void addUser(String email, String type) {
        if (type.equals(BASIC_INPUT)) {
            addUserBasic(email);
        } else if (type.equals(PREMIUM_INPUT)) {
            addUserPremium(email);
        }
    }

    /**
     * Adiciona um novo utilizador com conta básica.
     *
     * @param email o email que identifica o utilizador.
     * @pre email != null && type != null
     */
    private void addUserBasic(String email) {
        User newUser = new UserBasic(email);
        users.insertLast(newUser);
    }

    /**
     * Adiciona um novo utilizador com conta premium.
     *
     * @param email o email que identifica o utilizador.
     * @pre email != null && type != null
     */
    private void addUserPremium(String email) {
        User newUser = new UserPremium(email);
        users.insertLast(newUser);
    }

    @Override
    public boolean hasOwnedFile(String user, String file) {
        return getUser(user).hasOwnedFile(file);
    }

    @Override
    public boolean hasCapacity(String user, int size) {
        return (getUser(user).getSpace() >= size);
    }

    @Override
    public void addFile(String user, String file, int size) {
        getUser(user).addFile(file, size, user);

    }

    @Override
    public boolean allowsSharing(String owner) {
        return getUser(owner).canShare();
    }

    @Override
    public boolean hasSharedFile(String owner, String other, String file) {
        return getUser(other).hasSharedFile(file, owner);
    }

    @Override
    public boolean hasSharingCapacity(String owner, String other, String file) {
        int size = getUser(owner).getFile(file).getSize();
        return getUser(other).hasSharedCapacity(size);
    }

    @Override
    public void shareFile(String owner, String other, String file) {
        getUser(other).addShareFile(getUser(owner).getFile(file));
    }

    @Override
    public Iterator<File> listFiles(String user) {
        return getUser(user).iterator();
    }

    @Override
    public Iterator<User> listAll() {
        return users.iterator();
    }
}
