package elec5619.sydney.edu.au.mental_health_support_website.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

enum ThreadTag {
    ANXIETY,
    DEPRESSION,
    GENERAL,
    DISCUSSION
}

@Entity
@Getter
@Setter
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String content;
    private List<ThreadTag> tags = new ArrayList<>();
    private Integer authorID;
    private LocalDateTime timestamp;
}
