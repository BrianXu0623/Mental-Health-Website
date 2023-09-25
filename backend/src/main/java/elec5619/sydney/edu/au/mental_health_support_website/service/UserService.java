package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users registerUser(Users user) {
        // Add logic to validate and save the user
        return userRepository.save(user);
    }

    public Users loginUser(String username, String password) {
        // Add logic to authenticate the user
        return userRepository.findByUsername(username);
    }

    // Add other user-related methods
}
