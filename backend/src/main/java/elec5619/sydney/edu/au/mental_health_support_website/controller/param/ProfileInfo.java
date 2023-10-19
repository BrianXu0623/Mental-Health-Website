package elec5619.sydney.edu.au.mental_health_support_website.controller.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProfileInfo {
    String newUsername;
    String newEmail;
    String newPhoneNumber;
    String newBirthday;
}
