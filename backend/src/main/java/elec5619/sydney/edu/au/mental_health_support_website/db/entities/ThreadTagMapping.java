package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class ThreadTagMapping {
    private Long id;
    private Long threadId;
    private Long tagId;
}
