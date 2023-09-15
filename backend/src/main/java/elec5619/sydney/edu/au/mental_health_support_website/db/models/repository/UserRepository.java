package elec5619.sydney.edu.au.mental_health_support_website.db.models.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
