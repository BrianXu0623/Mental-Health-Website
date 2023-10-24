package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.controller.param.*;
import elec5619.sydney.edu.au.mental_health_support_website.controller.res.LoginRes;
import elec5619.sydney.edu.au.mental_health_support_website.controller.res.RegisterRes;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


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
     * @param registerInfo the register information of the user
     * @return user token if the registration process success, otherwise descriptive error messages will be provided
     * @throws IOException if the encryption url is invalid
     * @throws InterruptedException if there is a connection interruption between the system and the encryption api server
     */
    @PostMapping("register")
    public ResponseEntity<RegisterRes> register(@RequestBody RegisterInfo registerInfo) throws IOException,
            InterruptedException {

        String email = registerInfo.getEmail();
        String password = registerInfo.getPassword();
        String username = registerInfo.getUsername();
        RegisterRes registerRes = new RegisterRes(false, null, null, null);
        if(Strings.isEmpty(email) || Strings.isEmpty(username) || Strings.isEmpty(password)) {
            registerRes.setError(ErrorsEnum.PARAMETER_ERROR.getErrorMessage());
            return new ResponseEntity<>(registerRes, HttpStatus.METHOD_FAILURE);
        }

        if (!EncryptionUtil.validatePassword(password)) {
            registerRes.setError(ErrorsEnum.PASSWORD_FORMAT_ERROR.getErrorMessage());
            return new ResponseEntity<>(registerRes, HttpStatus.METHOD_FAILURE);
        }
        String encrypted = EncryptionUtil.encrypt(password);
        Users user = Users.builder().username(username).password(encrypted).
                email(email).userType("normal").build();
        user.setToken(TokenUtil.generateToken(username));
        if (userService.registerUser(user) != null) {
            registerRes.setSuccess(true);
            registerRes.setToken(user.getToken());
            registerRes.setUsername(user.getUsername());
            return new ResponseEntity<>(registerRes, HttpStatus.OK);
        }
        registerRes.setError(ErrorsEnum.DATABASE_ERROR.getErrorMessage());
        return new ResponseEntity<>(registerRes, HttpStatus.METHOD_FAILURE);
    }

    /**
     * Post method for verifying and log the user into the system
     * @param loginInfo the login information of the user for logging in
     * @return user token if the registration process success, otherwise descriptive error messages will be provided
     */
    @PostMapping("login")
    public ResponseEntity<LoginRes> login(
            @RequestBody LoginInfo loginInfo) {
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();
        LoginRes loginRes = new LoginRes(false, null, null, null);
        if(Strings.isEmpty(email) || Strings.isEmpty(password)) {
            loginRes.setError(ErrorsEnum.PARAMETER_ERROR.getErrorMessage());
            return new ResponseEntity<>(loginRes, HttpStatus.UNAUTHORIZED);
        }
        Users user = userService.loginUser(email, password);
        if(user == null) {
            loginRes.setError(ErrorsEnum.PASSWORD_WRONG_ERROR.getErrorMessage());
            return new ResponseEntity<>(loginRes, HttpStatus.UNAUTHORIZED);
        }

        user.setToken(TokenUtil.generateToken(user.getUsername()));
        loginRes.setSuccess(true);
        loginRes.setToken(user.getToken());
        loginRes.setUsername(user.getUsername());
        return new ResponseEntity<>(loginRes, HttpStatus.OK);
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
     * @param profileInfo the new profile information to be changed into
     * @return a user object corresponded to all those changes
     */
    @PostMapping("updateProfile")
    private Users updateProfile(@RequestHeader("token") String token,
                                @RequestBody ProfileInfo profileInfo) {
        String userName = TokenUtil.getUsernameFromToken(token);
        String newEmail = profileInfo.getNewEmail();
        String newPhoneNumber = profileInfo.getNewPhoneNumber();
        String newBirthday = profileInfo.getNewBirthday();
        String avatar = profileInfo.getAvatar();
        return userService.updateProfile(userName, newEmail, newPhoneNumber, newBirthday, avatar);
    }

    /**
     * Post method that allows a user to update his/her password
     * @param token the requester user token
     * @param passwordUpdateInfo current password and updated password
     * @return TRUE if the operation was successful, otherwise FALSE
     * @throws IOException if the encryption url is invalid
     * @throws InterruptedException if there is a connection interruption between the system and the encryption api server
     */
    @PostMapping("updatePassword")
    private boolean updatePassword(@RequestHeader("token") String token,
                                   @RequestBody PasswordUpdateInfo passwordUpdateInfo)
            throws IOException, InterruptedException {
        String userName = TokenUtil.getUsernameFromToken(token);
        String oldPassword = passwordUpdateInfo.getOldPassword();
        String newPassword = passwordUpdateInfo.getNewPassword();
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
    @GetMapping("searchUser/{userName}")
    private Users searchUser(@PathVariable String userName) {
        return userService.getUserByUsername(userName);
    }

    /**
     * Get method for all of the professional accounts
     * @return All of the professional accounts
     */
    @GetMapping("getAllProfessionals")
    public List<Users> getAllProfessionals() {
        return userService.getAllProfessionals();
    }

    /**
     * Post method for rating a professional
     * @param token the token of the current user
     * @param professionalRatingRequest information of professional rating request
     * @return The latest average rating of the professional;
     */
    @PostMapping("rateProfessional")
    public Long rateProfessional(@RequestHeader("token") String token,
                                 @RequestBody ProfessionalRatingRequest professionalRatingRequest) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users p = userService.getUserByUsername(professionalRatingRequest.getProfessionalUserName());
        p.setTotalRating(p.getTotalRating() + professionalRatingRequest.getRating());
        p.setRateTimes(p.getRateTimes() + 1);
        return p.getTotalRating() / p.getRateTimes();
    }

    /**
     * Get method for retrieving a professional's average rating
     * @param professionalUserName  username of the professional
     * @return The latest average rating of the professional;
     */
    @GetMapping("getProfessionalRating")
    public Long getProfessionalRating(@RequestBody String professionalUserName) {
        Users p = userService.getUserByUsername(professionalUserName);
        return p.getTotalRating() / p.getRateTimes();
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

//    @GetMapping("addSampleProfessionals")
//    public Users addSampleProfessionals() throws IOException, InterruptedException {
//        Users p1 = Users.builder().username("Dr. Emily Morgan").email("emilymorgan@outlook.com").userType("professional").password(EncryptionUtil.encrypt("Emily.12345")).phonenumber("0422748392").availableHours("1pm - 5pm Monday to Friday").clinic("Red Boundary Clinic").build();
//        Users ret = userService.registerUser(p1);
//        return ret;
//    }
}

