package elec5619.sydney.edu.au.mental_health_support_website;

import elec5619.sydney.edu.au.mental_health_support_website.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mhswa")
public class AppController {
    @Autowired
    private User user;

    @GetMapping("viewall")
    public List<String> viewAllMapping() {
        return null;
    }

    // Login/Sign up
    @PostMapping("signup")
    public void signupUser(
            @RequestBody Map<String, String> userInfo
    ) {
        return;
    }

    @GetMapping("login")
    public void loginUser(
            @RequestBody Map<String, String> userInfo
    ) {
        // Expected a map of email: ".." & password "..."
    }

    // Administration
    @PutMapping("user/mute")
    public void muteUser(
        @RequestBody int userID
    ) {

    }

    @PutMapping("delete_thread")
    public void deleteThread(
            @RequestBody int userID,
            @RequestBody int threadID
    ) {

    }


}
