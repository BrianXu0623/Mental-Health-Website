package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday;
    private String phonenumber;
    private String userType; // (normal, professional, admin)
    private String followerIds;
    private String followedIds;
    private String likedThreadIds;
    private boolean isMuted = false;
    @Transient
    private String token;
    private String experience;
    private String availableHours;
    private String clinic;
    private Long totalRating = 0L;
    private Long rateTimes = 0L;
    private String avatar;
    public Users() {}

    public Users copy() {
        return Users.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .password(this.password)
                .birthday(this.birthday)
                .phonenumber(this.phonenumber)
                .userType(this.userType)
                .followerIds(this.followerIds)
                .followedIds(this.followedIds)
                .likedThreadIds(this.likedThreadIds)
                .isMuted(this.isMuted)
                .token(this.token)
                .experience(this.experience)
                .availableHours(this.availableHours)
                .clinic(this.clinic)
                .totalRating(this.totalRating)
                .rateTimes(this.rateTimes)
                .avatar(this.avatar)
                .build();
    }
}
