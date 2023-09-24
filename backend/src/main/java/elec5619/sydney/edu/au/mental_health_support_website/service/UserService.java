package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.User;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Add logic to validate and save the user
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        try {
            password = EncryptionUtil.encrypt(password);
        } catch (IOException | InterruptedException e) {
            return null;
        }
        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> getFollowers(String username) {
        User user = userRepository.findByUsername(username);
        String followers = user.getFollowerIds();
        if(followers == null || followers.length() == 0) {
            return new ArrayList<>();
        }
        String[] array = followers.split(",");
        Long[] longArray = new Long[array.length];

        for (int i = 0; i < array.length; i++) {
            longArray[i] = Long.parseLong(array[i]);
        }
        List<Long> list = Arrays.asList(longArray);
        return userRepository.findAllByIdIn(list);
    }

    public List<User> getFollowed(String username) {
        User user = userRepository.findByUsername(username);
        String followedIds = user.getFollowedIds();
        if(followedIds == null || followedIds.length() == 0) {
            return new ArrayList<>();
        }
        String[] array = followedIds.split(",");
        Long[] longArray = new Long[array.length];

        for (int i = 0; i < array.length; i++) {
            longArray[i] = Long.parseLong(array[i]);
        }
        List<Long> list = Arrays.asList(longArray);
        return userRepository.findAllByIdIn(list);
    }

    public boolean follow(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByUsername(fromUsername);
        User toUser = userRepository.findByUsername(toUsername);
        if(fromUser.getFollowedIds() == null) {
            fromUser.setFollowedIds("");
        }
        if(fromUser.getFollowedIds().length() > 0) {
            fromUser.setFollowedIds(fromUser.getFollowedIds() + ",");
        }
        fromUser.setFollowedIds(fromUser.getFollowedIds() + toUser.getId());
        userRepository.save(fromUser);

        if(toUser.getFollowerIds() == null) {
            toUser.setFollowerIds("");
        }
        if(toUser.getFollowerIds().length() > 0) {
            toUser.setFollowerIds(toUser.getFollowerIds() + ",");
        }
        toUser.setFollowerIds(toUser.getFollowerIds() + fromUser.getId());
        userRepository.save(toUser);
        return true;
    }

    public boolean mute(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByUsername(fromUsername);
        User toUser = userRepository.findByUsername(toUsername);
        if(fromUser.getUserType() != null && fromUser.getUserType().equals("admin")) {
            toUser.setMuted(true);
            userRepository.save(toUser);
        }
        else {
            return false;
        }
        return false;
    }

    public User updateProfile(String userName, String email, String phoneNumber, String birthday) {
        User user = userRepository.findByUsername(userName);
        if(StringUtils.isNotBlank(userName)) {
            user.setUsername(userName);
        }
        if(StringUtils.isNotBlank(email)) {
            user.setEmail(email);
        }
        if(StringUtils.isNotBlank(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }
        if(StringUtils.isNotBlank(birthday)) {
            user.setBirthday(LocalDate.parse(birthday));
        }
        return userRepository.save(user);
    }

    public boolean updatePassword(String userName, String oldPassword, String newPassword) throws IOException, InterruptedException {
        User user = userRepository.findByUsername(userName);
        try {
            oldPassword = EncryptionUtil.encrypt(oldPassword);
        } catch (IOException | InterruptedException e) {
            return false;
        }
        if(user.getPassword().equals(oldPassword)) {
            newPassword = EncryptionUtil.encrypt(newPassword);
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
