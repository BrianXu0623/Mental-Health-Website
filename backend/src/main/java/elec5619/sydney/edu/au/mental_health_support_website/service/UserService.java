package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public Users registerUser(Users user) {
        // Add logic to validate and save the user
        Users ret = userRepository.save(user);
        ret.setPassword("");
        ret.setToken("");
        return ret;
    }

    public Users loginUser(String email, String password) {
        Users user = userRepository.findByEmail(email);
        try {
            password = EncryptionUtil.encrypt(password);
        } catch (IOException | InterruptedException e) {
            return null;
        }
        if(user == null) {
            return null;
        }
        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<Users> getFollowers(String username) {
        Users user = userRepository.findByUsername(username);
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
        List<Users> users = userRepository.findAllByIdIn(list);
        for(Users cur : users) {
            cur.setPassword("");
            cur.setToken("");
        }
        return users;
    }

    public List<Users> getFollowed(String username) {
        Users user = userRepository.findByUsername(username);
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
        List<Users> users = userRepository.findAllByIdIn(list);
        for(Users cur : users) {
            cur.setPassword("");
            cur.setToken("");
        }
        return users;
    }

    public boolean follow(String fromUsername, String toUsername) {
        Users fromUser = userRepository.findByUsername(fromUsername);
        Users toUser = userRepository.findByUsername(toUsername);
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
        Users fromUser = userRepository.findByUsername(fromUsername);
        Users toUser = userRepository.findByUsername(toUsername);
        if(fromUser.getUserType() != null && fromUser.getUserType().equals("admin")) {
            toUser.setMuted(true);
            userRepository.save(toUser);
        }
        else {
            return false;
        }
        return false;
    }

    public Users updateProfile(String userName, String newUsername, String email, String phoneNumber, String birthday) {
        Users user = userRepository.findByUsername(userName);
        if(StringUtils.isNotBlank(newUsername)) {
            user.setUsername(newUsername);
        }
        if(StringUtils.isNotBlank(email)) {
            user.setEmail(email);
        }
        if(StringUtils.isNotBlank(phoneNumber)) {
            user.setPhonenumber(phoneNumber);
        }
        if(StringUtils.isNotBlank(birthday)) {
            user.setBirthday(LocalDate.parse(birthday));
        }
        Users ret = userRepository.save(user);
        ret.setPassword("");
        ret.setToken("");
        return ret;
    }

    public boolean updatePassword(String userName, String oldPassword, String newPassword) throws IOException, InterruptedException {
        Users user = userRepository.findByUsername(userName);
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

    public Users getUserByUsername(String userName) {
        Users user = userRepository.findByUsername(userName);
        user.setPassword("");
        user.setToken("");
        return user;
    }
}
