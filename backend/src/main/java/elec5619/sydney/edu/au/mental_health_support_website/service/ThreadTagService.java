package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTag;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.ThreadTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadTagService {
    @Autowired
    private ThreadTagRepository repository;


    public ThreadTag createThreadTag(ThreadTag tag) {
        ThreadTag result = repository.findById(tag.getId()).orElse(null);
        if (result == null) {
            repository.save(tag);
        }
        return result;
    }

    public List<ThreadTag> getAllTags() {
        return repository.findAll();
    }

    public List<ThreadTag> getTagByNames(List<String> tagNames) {
        return repository.findAllByName(tagNames.get(0));
    }

     public ThreadTag getTag(Long tagId) {
        return repository.findById(tagId).orElse(null);
    }

    public void removeThreadTag(Long tagId) {
        Optional<ThreadTag> tag = repository.findById(tagId);
        tag.ifPresent(threadTag -> repository.delete(threadTag));
    }

    public void editThreadTag(Long tagId, ThreadTag tag) {
        if (repository.findById(tagId).isPresent())
            repository.save(tag);
    }

}
