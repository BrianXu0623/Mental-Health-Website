package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

enum UserType {
    ADMIN,
    NORMAL,
    PROFESSIONAL
}

@Entity
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthday; 
    private String phonenumber;
    private UserType userType;
}