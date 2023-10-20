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
public class AppThreadInfo {
    private AppThread thread;
    private List<String> tagNames;
    private String authorName;
    private String userToken;
    private List<ThreadComment> comments;
    private Long noComments;
}
