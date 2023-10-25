package elec5619.sydney.edu.au.mental_health_support_website.controller.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ThreadCommentRes {
    private String threadId;
    private String comment;
    private Date timestamp;
}
