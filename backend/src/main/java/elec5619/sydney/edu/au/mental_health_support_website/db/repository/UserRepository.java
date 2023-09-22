package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    default User findByUsername(String username) {
        return null;
    }
}
