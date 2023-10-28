package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Users findByUsername(String username);

    List<Users> findAllByIdIn(@Param("ids") List<Long> ids);

    List<Users> findAllByUserType(String userType);

    Users save(Users user);
}
