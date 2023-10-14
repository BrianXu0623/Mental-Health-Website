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
public class UserController {
    @Autowired
    private UserService userService;

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

    @GetMapping("followers")
    private List<Users> getFollowers(@RequestHeader("token") String token) {

        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.length() == 0) {
            return new ArrayList<>();
        }
        return userService.getFollowers(username);
    }

    @GetMapping("followed")
    private List<Users> getFollowed(@RequestHeader("token") String token) {
        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.length() == 0) {
            return new ArrayList<>();
        }
        return userService.getFollowed(username);
    }

    @PostMapping("follow")
    private boolean follow(@RequestHeader("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.follow(fromUsername, toUsername);
    }

    @PostMapping("mute")
    private boolean mute(@RequestHeader("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.mute(fromUsername, toUsername);
    }

    @PostMapping("updateProfile")
    private Users updateProfile(@RequestHeader("token") String token,
                            @RequestBody String newUsername,
                           @RequestBody String newEmail,
                           @RequestBody String newPhoneNumber,
                           @RequestBody String newBirthday) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updateProfile(userName, newUsername, newEmail, newPhoneNumber, newBirthday);
    }

    @PostMapping("updatePassword")
    private boolean updatePassword(@RequestHeader("token") String token,
                        @RequestBody String oldPassword,
                        @RequestBody String newPassword) throws IOException, InterruptedException {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updatePassword(userName, oldPassword, newPassword);
    }

    @GetMapping("profile")
    private Users getProfile(@RequestHeader("token") String token) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.getUserByUsername(userName);
    }

    @GetMapping("searchUser")
    private Users searchUser(@RequestBody String userName) {
        return userService.getUserByUsername(userName);
    }
}
