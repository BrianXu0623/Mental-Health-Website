package elec5619.sydney.edu.au.mental_health_support_website.controller.res;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ProfessionalComment;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalRes {
    private Users user;
    private List<ProfessionalComment> professionalCommentList;
    private Long averageRating;
}
