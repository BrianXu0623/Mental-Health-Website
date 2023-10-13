package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTagRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ThreadTagRelationshipRepository extends JpaRepository<ThreadTagRelationship, Long> {
    List<Long> findAllByThreadId(Long threadId);
    List<Long> findAllByTagId(Long tagId);

}
