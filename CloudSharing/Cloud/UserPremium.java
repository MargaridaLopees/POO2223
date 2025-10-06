/**
 * Classe UserPremium.
 *
 * @author Margarida Lopes 64557 LEI
 */

package Cloud;

public class UserPremium extends UserClass {


    private static final String TYPE = "Premium";


    public UserPremium(String email) {
        super(email, TYPE, 5120);
    }


    @Override
    public boolean canShare() {
        return true;
    }


    @Override
    public boolean hasSharedCapacity(int size) {
        return true;
    }


    @Override
    public void setSpaceSharedFile(int size) {
    }

}
