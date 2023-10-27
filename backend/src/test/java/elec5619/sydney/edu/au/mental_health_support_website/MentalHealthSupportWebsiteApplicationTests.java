package elec5619.sydney.edu.au.mental_health_support_website;

import elec5619.sydney.edu.au.mental_health_support_website.controller.ThreadController;
import elec5619.sydney.edu.au.mental_health_support_website.controller.UserController;
import elec5619.sydney.edu.au.mental_health_support_website.controller.res.ProfessionalRes;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.service.UserService;
import elec5619.sydney.edu.au.mental_health_support_website.util.EncryptionUtil;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MentalHealthSupportWebsiteApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    UserController userController;

    @Resource
    ThreadController threadController;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegister() {
//        Users user = new Users();
//        user.setUsername("test_user");
//        user.setEmail("test@outlook.com");
//        user.setPassword("97bbb3e3ef98422cac4b67ec6a3228bf");
//        Users retUser = userService.registerUser(user);
//        assertEquals(retUser.getEmail(), "test@outlook.com");
//        assertEquals(retUser.getUsername(), "test_user");
    }

    @Test
    void testLoginSuccess() throws InterruptedException {
        Thread.sleep(1000);
        Users user = userService.loginUser("test@outlook3.com", "TestPassword.1234");
        assertNotNull(user);
        assertEquals(user.getEmail(), "test@outlook3.com");
    }

    @Test
    void testLoginFail() throws InterruptedException {
        Thread.sleep(1000);
        Users user = userService.loginUser("test@outlook3.com", "TestPassword.12345678");
        assertNull(user);
    }

    @Test
    void testLoginNotExist() {
        Users user = userService.loginUser("notexisttest@outlook.com", "TestPassword.12345678");
        assertNull(user);
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

    @Test
    void testValidatePasswordFail() {
        boolean isValidate = EncryptionUtil.validatePassword("123456");
        assertFalse(isValidate);
    }
    @Test
    void testValidatePasswordSuccess() {
        boolean isValidate = EncryptionUtil.validatePassword("Xxx.1234.xxx");
        assertTrue(isValidate);
    }

    @Test
    void testGetProfessional() {
        ProfessionalRes professionalRes =userController.searchProfessional("Dr. David Collins");
        assertNotNull(professionalRes);
    }

    @Test
    void testToken() throws InterruptedException {
        String token = TokenUtil.generateToken("delay");
        System.out.println(token);
        System.out.println("asdasd");
        Thread.sleep(5000);
        System.out.println(TokenUtil.getUsernameFromToken(token));
    }

    @Test
    void testThread() {
        String username = TokenUtil.getUsernameFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLQyIsImV4cCI6MTY5ODI0NDA0Mn0.XzO_ihO7NuyQm94MYh3220KiyiCdkxqjt3nyYaeTAKg");
        System.out.println(username);
    }
}
