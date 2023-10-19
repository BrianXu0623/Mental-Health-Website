package elec5619.sydney.edu.au.mental_health_support_website.controller.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRes {
    boolean isSuccess;
    String token;
    String error;
}
