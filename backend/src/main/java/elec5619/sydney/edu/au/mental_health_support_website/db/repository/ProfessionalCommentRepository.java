package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ProfessionalComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessionalCommentRepository extends JpaRepository<ProfessionalComment, Long> {
    List<ProfessionalComment> findAllByProfessionalId(Long professionalId);
}
