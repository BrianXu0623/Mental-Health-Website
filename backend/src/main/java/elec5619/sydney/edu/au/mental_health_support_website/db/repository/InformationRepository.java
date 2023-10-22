package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Information;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InformationRepository extends JpaRepository<Information, Long> {

}
