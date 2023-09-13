package elec5619.sydney.edu.au.mental_health_support_website.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.ArrayList;

enum ThreadTag {
    ANXIETY,
    DEPRESSION,
    GENERAL,
    DISCUSSION
}

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private String content;
    private List<ThreadTag> tags = new ArrayList<>();
    private Integer authorID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ThreadTag> getTags() {
        return tags;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public void setTags(List<ThreadTag> tags) {
        this.tags = tags;
    }
}
