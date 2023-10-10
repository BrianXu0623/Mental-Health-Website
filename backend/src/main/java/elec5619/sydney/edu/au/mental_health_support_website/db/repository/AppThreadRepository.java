package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppThreadRepository extends JpaRepository<AppThread, Long> {
}
