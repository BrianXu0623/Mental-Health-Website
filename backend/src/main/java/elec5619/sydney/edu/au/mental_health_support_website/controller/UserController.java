package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.User;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(
            @RequestParam String username,
            @RequestParam String password) {
        User user = userService.loginUser(username, password);
        getFollowers(user);
        return user;
    }

    // Add other user-related endpoints
    private void getFollowers(User user) {

    }
}
