package elec5619.sydney.edu.au.mental_health_support_website.controller.res;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class InfoRes {
    private String title;
    private String content;
    private String author;
}
