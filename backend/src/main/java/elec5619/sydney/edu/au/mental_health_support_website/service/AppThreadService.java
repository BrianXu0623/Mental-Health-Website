package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.AppThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppThreadService {
    @Autowired
    private AppThreadRepository threadRepository;

    public AppThread createThread(AppThread thread) {
        return threadRepository.save(thread);
    }

    public AppThread removeThread(Long threadId) {
        Optional<AppThread> optThread = threadRepository.findById(threadId);
        threadRepository.deleteById(threadId);
        return optThread.orElse(null);
    }

    // Do we even need id
    public void editThread(Long threadId, AppThread edittedThread) {
        threadRepository.save(edittedThread);
    }

}
