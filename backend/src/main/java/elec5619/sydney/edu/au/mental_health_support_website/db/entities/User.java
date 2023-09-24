package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday; 
    private String phoneNumber;
    private String userType; // (normal, professional, admin)
    private String followerIds;
    private String followedIds;
    private String likedThreadIds;
    private boolean isMuted = false;
    public User() {}
//    CREATE TABLE User (
//    id BIGINT AUTO_INCREMENT PRIMARY KEY,
//    username VARCHAR(255),
//    email VARCHAR(255),
//    password VARCHAR(255),
//    birthday DATE,
//    phone_number VARCHAR(20),
//    user_type VARCHAR(20),
//    follower_ids TEXT,
//    followed_ids TEXT,
//    liked_thread_ids TEXT,
//    isMuted BOOLEAN DEFAULT false);
//    CREATE UNIQUE INDEX username_index ON User(username);
}
