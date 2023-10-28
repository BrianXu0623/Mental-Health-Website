package elec5619.sydney.edu.au.mental_health_support_website.service;


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

    public List<ThreadTagRelationship> getTags(Long threadId) {
        return repository.findAllByThreadId(threadId);
    }

    public void removeThreadTagRelationship(ThreadTagRelationship obj) {
        repository.delete(obj);
    }

    public void deleteByThreadId(Long threadId) {
        repository.deleteByThreadId(threadId);
    }

    public ThreadTagRelationship findByThreadIdAndTagId(Long threadId, Long tagId) {
        return repository.findByThreadIdAndTagId(threadId, tagId);
    }

    public List<ThreadTagRelationship> getThread(Long tagId) {
        return repository.findAllByTagId(tagId);
    }

}
