package elec5619.sydney.edu.au.mental_health_support_website;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MentalHealthSupportWebsiteApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegister() {
        Users user = new Users();
        user.setUsername("test_user");
        user.setEmail("test@outlook.com");
        user.setPassword("TestPassword.1234");
        Users retUser = userService.registerUser(user);
        assertEquals(retUser.getEmail(), "test@outlook.com");
        assertEquals(retUser.getUsername(), "test_user");
    }

    @Test
    void testLoginSuccess() {
        Users user = userService.loginUser("test@outlook.com", "TestPassword.1234");
        assertNotNull(user);
        assertEquals(user.getEmail(), "test@outlook.com");
    }

    @Test
    void testLoginFail() {
        Users user = userService.loginUser("test@outlook.com", "TestPassword.12345678");
        assertNull(user);
        user = userService.loginUser("notexisttest@outlook.com", "TestPassword.12345678");
        assertNull(user);
    }

    @Test
    void testPasswordEncryption() throws IOException, InterruptedException {
        String encrypted = EncryptionUtil.encrypt("abcd1234");
        System.out.println(encrypted);
        assertEquals(encrypted, "");
    }

    @Test
    void testFollow() {
        boolean ifSuccess = userService.follow("testRegister1", "testRegister2");
        assertTrue(ifSuccess);
        List<Users> followed = userService.getFollowed("testRegister2");
        boolean ifContains = false;
        for(Users user : followed) {
            if(user.getUsername().equals("testRegister1")) {
                ifContains = true;
                break;
            }
        }
        assertTrue(ifContains);
    }
}
