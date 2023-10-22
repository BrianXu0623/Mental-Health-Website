package elec5619.sydney.edu.au.mental_health_support_website.controller.res;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Information;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class InfoRes {
    private List<Information> informationList;
}
