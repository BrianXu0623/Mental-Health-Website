package elec5619.sydney.edu.au.mental_health_support_website.models.repository;

import elec5619.sydney.edu.au.mental_health_support_website.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}