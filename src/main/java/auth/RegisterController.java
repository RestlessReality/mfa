package auth;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * nur get und post verwenden?
 * prepared statements
 * gorm
 */
@RestController
public class RegisterController {

    @Autowired
    private UserDao userDao;

    /**
     * Creates a new user in the DB.
     * @param name Name of the user to create
     * @return todo: backup-code, seed as qr-code
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public User register(HttpServletRequest request, String name) { //@RequestParam(value="name") String name

        User existingUser = userDao.findByName(name);
        if (existingUser != null) {
            return existingUser;
        } else {

            User user = new User(name, request.getRemoteAddr());
            try {
                userDao.save(user);
            } catch (Exception ex) {
                System.out.println("Error creating the user: " + ex.toString());
            }
            return user;
        }
    }

//    /**
//     * Checks if user with this name is already existing.
//     * ToDo: Does not yet support/check for duplicates with different providerIDs
//     * @param providerIP not yet supported
//     * @param name name of the user which shall be searched in the database
//     * @return true, if user already exists in database,false otherwise
//     */
//    private boolean userAlreadyExisting(String providerIP, String name){
//        User user =userDao.findByName(name);
//        if ( user != null) {
//            System.out.println("Name alreadyExisting");
//            return true;
//        }
//        return false;
//    }

    /**
     * Method generates token for given user and compares it with the given token.
     * Checks currentTime for protection against replay-attacs.
     * @param token
     * @param currentTime
     * @return true, if both values are identical, false otherwise.
     */
    @RequestMapping(value="/verify", method = RequestMethod.GET)
    @ResponseBody
    public boolean verifyToken(HttpServletRequest request, String name, int token, int currentTime){

        // ask from ttop token for user


        // check if valid:
        // check if current token or + or - 30 seconds OR more or less? then ask for another token and check if same delay
        return false;
    }

    /**
     * Deletes a user if it is present
     * @return true, if user could be deleted, false otherwise
     */
    @RequestMapping(value="/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteUser(HttpServletRequest request, String name){

        // check if valid ip of requester
        // drop user

        return false;
    }

}
