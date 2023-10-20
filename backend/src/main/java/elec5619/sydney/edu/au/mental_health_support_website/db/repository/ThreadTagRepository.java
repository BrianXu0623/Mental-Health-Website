package elec5619.sydney.edu.au.mental_health_support_website.db.repository;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ThreadTagRepository extends JpaRepository<ThreadTag, Long> {
    List<ThreadTag> findByNameIn(List<String> tagNames);
    List<ThreadTag> findAllByIdIn(List<Long> ids);

    ThreadTag findOneByName(String name);
}