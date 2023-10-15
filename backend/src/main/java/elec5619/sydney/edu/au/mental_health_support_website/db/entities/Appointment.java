package elec5619.sydney.edu.au.mental_health_support_website.db.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String status;
    private LocalDate date;
    private LocalTime time;
    private Integer professionUserId;
    private Integer userId;
    private String appointmentTopic;

    public Appointment() {
    }

    /**
     * CREATE TABLE Appointment (
     * 	id BIGINT IDENTITY(1, 1) PRIMARY KEY,
     * 	status VARCHAR(500),
     * 	date DATE,
     * 	time TIME,
     * 	professional_user_id BIGINT,
     * 	user_id BIGINT,
     * 	appointment_topic TEXT,
     *
     * 	FOREIGN KEY (professional_user_id) REFERENCES [dbo].[Users](id),
     * 	FOREIGN KEY (user_id) REFERENCES [dbo].[Users](id)
     * );
     */

}
