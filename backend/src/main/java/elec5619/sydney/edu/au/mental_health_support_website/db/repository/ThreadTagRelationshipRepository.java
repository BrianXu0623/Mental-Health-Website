package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTagRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadTagRelationshipRepository extends JpaRepository<ThreadTagRelationship, Long> {
    List<ThreadTagRelationship> findAllByThreadId(Long threadId);

    List<ThreadTagRelationship> findAllByTagId(Long tagId);

    ThreadTagRelationship findByThreadIdAndTagId(Long threadId, Long tagId);

    void deleteByThreadId(Long threadId);
}

