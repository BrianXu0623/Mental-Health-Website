package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
    List<Users> findAllByIdIn(@Param("ids") List<Long> ids);
//    int updateEmailById(Long id, String newEmail);
//    int updateFollowedIdsById(Long id, String newFollowedIds);
//    int updateFollowerIdsById(Long id, String newFollowerIds);
//    int updateLikedThreadIdsById(Long id, String newLikedThreadIds);
//    int updateUsernameById(Long id, String newUsername);
//    int updatePasswordById(Long id, String newPassword);
    Users save(Users user);
}
