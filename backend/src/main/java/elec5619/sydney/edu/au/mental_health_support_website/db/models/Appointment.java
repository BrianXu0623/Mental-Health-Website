package elec5619.sydney.edu.au.mental_health_support_website.db.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

enum AppointmentStatus {
    PENDING,
    COMPLETED,
    CANCELLED
};

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private AppointmentStatus status;
    private LocalDate date;
    private LocalTime time;
    private Integer professionUserId;
    private Integer userId;
    private String appointmentTopic;

}
