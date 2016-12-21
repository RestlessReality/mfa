package auth;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * nur get und post verwenden meint Tobias?
 * prepared statements
 * gorm
 *
 */
@RestController
public class RegisterController {

    @Autowired
    private UserDao userDao;

    // example:
    /**
     * GET /create  --> Create a new user and save it in the database.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String name) {
        String userId = "";
        try {
            User user = new User(name, "testProviderip");
            userDao.save(user);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }


    //  https://localhost:8443/register?username=a      //mehrere Parameter mit & verknüpfen
    @RequestMapping(value="/registeruser", method = RequestMethod.POST)
    public User register(HttpServletRequest request, @RequestParam(value="username") String username) throws Exception {

        final String callerIP = request.getRemoteAddr();
        System.out.println("Callers ip address: " + callerIP); // 8 blocks = ipv6 address

        // Open Database-Connection
//        Class.forName("org.sqlite.Driver"); //not working
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:~/mfa-db"); //we manually created a sqlite-db

//        Class.forName("org.h2.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:h2:~/test","sa","tacmot"); //todo this is a password in the code..!!!
//        Statement statement = connection.createStatement();

//        statement.execute("CREATE TABLE USERS ( id varchar(255) NOT NULL, name varchar(255) not null,  PRIMARY KEY (id) )");


//        PreparedStatement ps = connection.prepareStatement(
//              "SELECT user, password FROM tbl_user WHERE (user=?)"
//         ); // Statement wird erzeugt
//         ps.setString(1, username); // Parameter werden übergeben
//         ResultSet rs = ps.executeQuery(); //Statement wird ausgeführt.

//        String checkIp = "SELECT COUNT(*) FROM ( SELECT 1 FROM ALLOWEDIPS WHERE Ip = '"+callerIP+"')"; //this works
//        String checkIp = "SELECT 1 FROM ALLOWEDIPS WHERE Ip = '"+callerIP+"'";
//        ResultSet rs = statement.executeQuery(checkIp); //this works

//        rs.close();

//        statement.execute("" +
//                " IF NOT EXISTS (SELECT 1 FROM ALLOWEDIPS WHERE Ip = '"+callerIP+"')" +
//                " INSERT INTO ALLOWEDIPS VALUES ('"+callerIP+"')" ); // die zeile passt


        // verify provider

        if(!request.getRemoteAddr().equals( callerIP )){ // (later: from database table)
            throw new Exception("You are not registered");
        }

        // check if user not yet present
        // create user in database

//        connection.close();
        return new User(username, callerIP);
    }

    public boolean verifyToken(User user, int token, int currentTime){

        // ask from ttop token for user
        // check if valid:
        // check if current token or + or - 30 seconds OR more or less? then ask for another token and check if same delay
        return false;
    }

    @RequestMapping(value="/registerget", method = RequestMethod.GET)
    public Greeting registerget() {
        return new Greeting(3, "test register");

    }

}
