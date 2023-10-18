package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * Post method that allows user to register his/her account to the system
     * @param email the email of the user
     * @param username the username that the user wished to be identified with
     * @param password the non-encrypted password
     * @return user token if the registration process success, otherwise descriptive error messages will be provided
     * @throws IOException if the encryption url is invalid
     * @throws InterruptedException if there is a connection interruption between the system and the encryption api server
     */
    @PostMapping("register")
    public String register(@RequestBody String email,
                         @RequestBody String username,
                         @RequestBody String password) throws IOException, InterruptedException {

            if(Strings.isEmpty(email) || Strings.isEmpty(username) || Strings.isEmpty(password)) {
                return ErrorsEnum.PARAMETER_ERROR.getErrorMessage();
            }

            if (!EncryptionUtil.validatePassword(password)) {
                return ErrorsEnum.PASSWORD_FORMAT_ERROR.getErrorMessage();
            }
            String encrypted = EncryptionUtil.encrypt(password);
            Users user = Users.builder().username(username).password(encrypted).
                    email(email).build();
            user.setToken(TokenUtil.generateToken(username));
            if (userService.registerUser(user) != null) {
                return user.getToken();
            }
            return ErrorsEnum.DATABASE_ERROR.getErrorMessage();
        }

    /**
     * Post method for verifying and log the user into the system
     * @param email the provided email for logging in
     * @param password the non-encrypted password that is used to verified
     * @return user token if the registration process success, otherwise descriptive error messages will be provided
     */
    @PostMapping("login")
    public String login(
            @RequestBody String email,
            @RequestBody String password) {
        if(Strings.isEmpty(email) || Strings.isEmpty(password)) {
            return ErrorsEnum.PARAMETER_ERROR.getErrorMessage();
        }
        Users user = userService.loginUser(email, password);
        if(user == null) {
            return ErrorsEnum.PASSWORD_WRONG_ERROR.getErrorMessage();
        }
        user.setToken(TokenUtil.generateToken(user.getUsername()));
        return user.getToken();
    }

    /**
     * Get method for getting a list of followers given a user token
     * @param token the requester user token
     * @return a list of users that followed the requested user, otherwise an empty list
     */
    @GetMapping("followers")
    private List<Users> getFollowers(@RequestHeader("token") String token) {

        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.isEmpty()) {
            return new ArrayList<>();
        }
        return userService.getFollowers(username);
    }

    /**
     * Get method for getting a list of users that the requester has followed
     * @param token the requester user token
     * @return a list of users that the requested user followed, otherwise an empty list
     */
    @GetMapping("followed")
    private List<Users> getFollowed(@RequestHeader("token") String token) {
        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.isEmpty()) {
            return new ArrayList<>();
        }
        return userService.getFollowed(username);
    }

    /**
     * Post method that allows a user to follow another user
     * @param token the requester user token
     * @param toUsername the username of the user to be followed
     * @return TRUE if the operation was successful, otherwise FALSE
     */
    @PostMapping("follow")
    private boolean follow(@RequestHeader("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.follow(fromUsername, toUsername);
    }

    /**
     * Post method that allows a user to mute another user
     * @param token the requester user token
     * @param toUsername the username of the user to be muted
     * @return TRUE if the operation was successful, otherwise FALSE
     */
    @PostMapping("mute")
    private boolean mute(@RequestHeader("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.mute(fromUsername, toUsername);
    }

    /**
     * Post method that allows the user to update his/her profile
     * @param token the requester user token
     * @param newUsername the new username to be changed into
     * @param newEmail the new email to be changed into
     * @param newPhoneNumber the new phone number to be changed into
     * @param newBirthday the new birthday to be changed into
     * @return a user object corresponded to all those changes
     */
    @PostMapping("updateProfile")
    private Users updateProfile(@RequestHeader("token") String token,
                            @RequestBody String newUsername,
                           @RequestBody String newEmail,
                           @RequestBody String newPhoneNumber,
                           @RequestBody String newBirthday) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updateProfile(userName, newUsername, newEmail, newPhoneNumber, newBirthday);
    }

    /**
     * Post method that allows a user to update his/her password
     * @param token the requester user token
     * @param oldPassword the non-encrypted current password
     * @param newPassword new non-encrypted password to be updated
     * @return TRUE if the operation was successful, otherwise FALSE
     * @throws IOException if the encryption url is invalid
     * @throws InterruptedException if there is a connection interruption between the system and the encryption api server
     */
    @PostMapping("updatePassword")
    private boolean updatePassword(@RequestHeader("token") String token,
                        @RequestBody String oldPassword,
                        @RequestBody String newPassword) throws IOException, InterruptedException {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updatePassword(userName, oldPassword, newPassword);
    }

    /**
     * Get method for getting the user profile
     * @param token the requester token
     * @return the user object requested if found, otherwise null
     */
    @GetMapping("profile")
    private Users getProfile(@RequestHeader("token") String token) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.getUserByUsername(userName);
    }

    /**
     * Get method for searching a specific user based on the username
     * @param userName the username of the user
     * @return the requested user object if found, otherwise null
     */
    @GetMapping("searchUser")
    private Users searchUser(@RequestBody String userName) {
        return userService.getUserByUsername(userName);
    }


    // Testing Endpoints
    @GetMapping("testRegister1")
    public Users testRegister1() throws IOException, InterruptedException {
        String testPassword = "testPassword1";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        Users user = Users.builder().username("testUserName1").password(encrypted).
                email("testEmail1@gmail.com").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegister2")
    public Users testRegister2() throws IOException, InterruptedException {
        String testPassword = "testPassword2";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        Users user = Users.builder().username("testUserName2").password(encrypted).
                email("testEmail2@gmail.com").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegisterAdmin")
    public Users testRegisterAdmin() throws IOException, InterruptedException {
        String testPassword = "testPasswordAdmin";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        Users user = Users.builder().username("testUserNameAdmin").password(encrypted).
                email("testEmailAdmin@gmail.com").userType("admin").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegisterProf")
    public Users testRegisterProf() throws IOException, InterruptedException {
        String testPassword = "testPasswordProf";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        Users user = Users.builder().username("testUserNameProf").password(encrypted).
                email("testEmailProf@gmail.com").userType("professional").build();
        return userService.registerUser(user);
    }

    @GetMapping("testLogin")
    public Users testLogin() {
        return userService.loginUser("testEmail@gmail.com", "testPassword");
    }
}

