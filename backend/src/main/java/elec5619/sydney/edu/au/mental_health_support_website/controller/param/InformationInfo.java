package elec5619.sydney.edu.au.mental_health_support_website.controller.param;


import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InformationInfo {
    private Long id;
    private String title;
    private String content;
    private String author;
}
