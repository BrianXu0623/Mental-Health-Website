package elec5619.sydney.edu.au.mental_health_support_website.controller.param;


import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppThreadInfo {
    private AppThread thread;
    private List<String> tagNames;
}
