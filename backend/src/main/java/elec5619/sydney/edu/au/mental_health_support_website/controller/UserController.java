package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.User;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
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
    public User testRegister1() throws IOException, InterruptedException {
        String testPassword = "testPassword1";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        User user = User.builder().username("testUserName1").password(encrypted).
                email("testEmail1@gmail.com").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegister2")
    public User testRegister2() throws IOException, InterruptedException {
        String testPassword = "testPassword2";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        User user = User.builder().username("testUserName2").password(encrypted).
                email("testEmail2@gmail.com").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegisterAdmin")
    public User testRegisterAdmin() throws IOException, InterruptedException {
        String testPassword = "testPasswordAdmin";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        User user = User.builder().username("testUserNameAdmin").password(encrypted).
                email("testEmailAdmin@gmail.com").userType("admin").build();
        return userService.registerUser(user);
    }

    @GetMapping("testRegisterProf")
    public User testRegisterProf() throws IOException, InterruptedException {
        String testPassword = "testPasswordProf";
        String encrypted = EncryptionUtil.encrypt(testPassword);
        User user = User.builder().username("testUserNameProf").password(encrypted).
                email("testEmailProf@gmail.com").userType("professional").build();
        return userService.registerUser(user);
    }

    @GetMapping("testLogin")
    public User testLogin() {
        return userService.loginUser("testEmail@gmail.com", "testPassword");
    }

    @PostMapping("register")
    public User register(@RequestParam String email,
                         @RequestParam String username,
                         @RequestParam String password) throws IOException, InterruptedException {
        String encrypted = EncryptionUtil.encrypt(password);
        User user = User.builder().username(username).password(encrypted).
                email(email).build();
        user.setToken(TokenUtil.generateToken(username));
        return userService.registerUser(user);
    }

    @PostMapping("login")
    public User login(
            @RequestParam String email,
            @RequestParam String password) {
        User user = userService.loginUser(email, password);
        user.setToken(TokenUtil.generateToken(user.getUsername()));
        return user;
    }

    @GetMapping("followers")
    private List<User> getFollowers(@RequestParam("token") String token) {
        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.length() == 0) {
            return new ArrayList<>();
        }
        return userService.getFollowers(username);
    }

    @GetMapping("followed")
    private List<User> getFollowed(@RequestParam("token") String token) {
        String username = TokenUtil.getUsernameFromToken(token);
        if(username == null || username.length() == 0) {
            return new ArrayList<>();
        }
        return userService.getFollowed(username);
    }

    @PostMapping("follow")
    private boolean follow(@RequestParam("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.follow(fromUsername, toUsername);
    }

    @PostMapping("mute")
    private boolean mute(@RequestParam("token") String token, String toUsername) {
        String fromUsername = TokenUtil.getUsernameFromToken(token);
        return userService.mute(fromUsername, toUsername);
    }

    @PostMapping("updateProfile")
    private User updateProfile(@RequestParam("token") String token,
                            @RequestParam String newUsername,
                           @RequestParam String newEmail,
                           @RequestParam String newPhoneNumber,
                           @RequestParam String newBirthday) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updateProfile(userName, newUsername, newEmail, newPhoneNumber, newBirthday);
    }

    @PostMapping("updatePassword")
    private boolean updatePassword(@RequestParam("token") String token,
                        @RequestParam String oldPassword,
                        @RequestParam String newPassword) throws IOException, InterruptedException {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.updatePassword(userName, oldPassword, newPassword);
    }

    @GetMapping("profile")
    private User getProfile(@RequestParam("token") String token) {
        String userName = TokenUtil.getUsernameFromToken(token);
        return userService.getUserByUsername(userName);
    }

    @GetMapping("searchUser")
    private User searchUser(@RequestParam String userName) {
        return userService.getUserByUsername(userName);
    }
}
