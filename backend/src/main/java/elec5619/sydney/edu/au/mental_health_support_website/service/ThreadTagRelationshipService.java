package elec5619.sydney.edu.au.mental_health_support_website.service;


import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTag;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTagRelationship;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.ThreadTagRelationshipRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadTagRelationshipService {
    @Resource
    private ThreadTagRelationshipRepository repository;

    public void insertThreadTagRelationship(ThreadTagRelationship obj) {
        repository.save(obj);
    }
    public List<Long> getTags(Long threadId) {
        return repository.findAllByThreadId(threadId);
    }

    public List<Long> getThread(Long tagId) {
        return repository.findAllByTagId(tagId);
    }

}
