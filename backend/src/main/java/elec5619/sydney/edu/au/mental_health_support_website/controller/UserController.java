package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Users login(
            @RequestParam String username,
            @RequestParam String password) {
        Users user = userService.loginUser(username, password);
        getFollowers(user);
        return user;
    }

    @GetMapping("/test")
    public Users test() {
        Users user = new Users("Simon", "Yuan");
        userService.registerUser(user);
        return user;
    }

    // Add other user-related endpoints
    private void getFollowers(Users user) {

    }
}
