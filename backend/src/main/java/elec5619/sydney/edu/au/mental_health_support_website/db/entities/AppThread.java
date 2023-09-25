package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class AppThread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String content;
    private String tags;
    private Long authorID;
    private Date timestamp;

}