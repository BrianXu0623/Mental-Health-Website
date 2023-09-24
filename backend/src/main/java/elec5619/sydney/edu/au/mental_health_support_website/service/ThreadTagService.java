package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTag;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.ThreadTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadTagService {
    @Autowired
    private ThreadTagRepository repository;

    public void createThreadTag(ThreadTag tag) {
        repository.save(tag);
    }

    public void removeThreadTag(ThreadTag tag) {
        repository.delete(tag);
    }

    public void editThreadTag(ThreadTag tag) {
        repository.save(tag);
    }

}
