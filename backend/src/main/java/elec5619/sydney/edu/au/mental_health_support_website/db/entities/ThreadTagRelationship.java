package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ThreadTagRelationship {
    private Long threadId;
    private Long tagId;
}
