/**
 * Classe UserBasic.
 *
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

public class UserBasic extends UserClass {

    private static final String TYPE = "Basic";


    public UserBasic(String email) {
        super(email, TYPE, 2048);
    }

    @Override
    public boolean canShare() {
        return false;
    }


    public boolean hasSharedCapacity(int size) {
        return ((size / 2) <= getSpace());
    }

    @Override
    public void setSpaceSharedFile(int size) {
        int newSpace = getSpace() - (size / 2);
        setSpace(newSpace);
    }


}
