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

    @PostMapping("newUser")
    public void addNewUser(
            @RequestBody Map<String, String> userInfo
    ) {
        return;
    }


}
