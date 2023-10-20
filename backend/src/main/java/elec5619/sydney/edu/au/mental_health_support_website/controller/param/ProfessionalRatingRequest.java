package elec5619.sydney.edu.au.mental_health_support_website.controller.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfessionalRatingRequest {
    private Long rating;
    private String professionalUserName;
}
