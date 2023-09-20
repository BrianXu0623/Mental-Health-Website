package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mhswa")
public class AppController {
    @Resource
    private UserService user;

    @GetMapping("viewall")
    public List<String> viewAllMapping() {
        return Arrays.asList("Hello", "World");
    }

    // Login/Sign up
//    @PostMapping("signup")
//    public void signupUser(
//            @RequestBody Map<String, String> userInfo
//    ) {
//    }
//
//    @GetMapping("login")
//    public void loginUser(
//            @RequestBody Map<String, String> userInfo
//    ) {
//        // Expected a map of email: ".." & password "..."
//    }
//
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

    // Following Others
    @PostMapping("user/{username}/following")
    public void userFollowAnotherUser(
            @RequestBody Integer userId,
            @RequestBody Integer followerId
    ) {

    }

    // Different Tier of User
    @PutMapping("userType/edit")
    public void editUserType(
            @RequestBody Integer editorId,
            @RequestBody Integer userId,
            @RequestBody String userType
    ) {

    }

    // Information Page
    @PostMapping("information_page/create")
    public void createInformationPage(
            @RequestBody List<Integer> threadIds
    ) {

    }

     @PutMapping("information_page/{informationPageId}/add")
     public void addThreadToInformationPage(
             @PathVariable Integer informationPageId,
             @RequestBody Integer threadId
     ) {

     }

     @PutMapping("information_page/{informationPageId}/remove")
     public void removeThreadToInformationPage(
             @PathVariable Integer informationPageId,
             @RequestBody Integer threadId
     ) {

     }

     @GetMapping("information_page/get/{informationPageId}")
     public Map<String, Object> getInformationPageById(
             @PathVariable Integer informationPageId
     ) {
       return null;
     }

     @GetMapping("information_page/get/{date}")
     public Map<String, Object> getInformationPageByDate(
             @PathVariable String date
     ) {
        return null;
     }

    // Search Function
    @GetMapping("thread/search/")
    public List<Map<Integer, Object>> getThreadsByTitle(
            @RequestBody String title
    ) {
        return null;
    }

    // Profile Management
    @GetMapping("user/{username}")
    public void editUserProfile(
            @RequestBody Integer userId,
            @RequestBody Map<String, String> values
    ) {
    }

    // Thread Management


    // Appointment Management

    // User Feedback on Professional


}
