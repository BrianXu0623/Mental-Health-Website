package elec5619.sydney.edu.au.mental_health_support_website;

import elec5619.sydney.edu.au.mental_health_support_website.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    // Post Thread
    @PostMapping("new_thread")
    public void createThread(
            @RequestBody int userId,
            @RequestBody String title,
            @RequestBody String content,
            @RequestBody List<String> tags
    ) {

    }

    // Comment on thread
    @PostMapping("thread/{thread_id}/comment")
    public void commentOnThread(
            @PathVariable Integer threadId,
            @RequestBody Integer userId,
            @RequestBody String comment
    ) {

    }

    @PutMapping("thread/{thread_id}/comment")
    public void editThreadComment(
            @PathVariable Integer threadId,
            @RequestBody Integer userId,
            @RequestBody String comment
    ) {

    }

    @PutMapping("thread/{thread_id}/{comment_id}")
    public void deleteThreadComment(
            @PathVariable Integer threadId,
            @PathVariable Integer commentId
    ) {

    }

    @GetMapping("thread/{thread_id}/comment")
    public Map<String, String> getThreadComments(
            @PathVariable Integer threadID
    ) {
        return null;
    }

    // Linking Thread with tags
    @PutMapping("thread/{thread_id}/tag")
    public void addTagToThread(
            @PathVariable Integer threadId,
            @RequestBody String tag
    ) {

    }

    @PutMapping("thread/{thread_id}/tags")
    public void updateThreadTags(
            @PathVariable Integer threadId,
            @RequestBody List<String> tags
    ) {

    }

    



    // Profile Management
    @GetMapping("user/{username}")
    public void editUserProfile(
            @RequestBody Integer userId,
            @RequestBody Map<String, String> values
    ) {
    }


}
