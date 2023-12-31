package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);

    List<Appointment> findByProfessionalUserId(Long professionalUserId);
}
