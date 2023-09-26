package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.AppThreadRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class AppThreadService {
    @Resource
    private AppThreadRepository threadRepository;

    public AppThread createThread(AppThread thread) {
        return threadRepository.save(thread);
    }

    public AppThread removeThread(Long threadId) {
        Optional<AppThread> optThread = threadRepository.findById(threadId);
        threadRepository.deleteById(threadId);
        return optThread.orElse(null);
    }

    public void editThread(AppThread edittedThread) {
        threadRepository.save(edittedThread);
    }

    public AppThread getThread(Long threadId) {
        return threadRepository.findById(threadId).orElse(null);
    }

    public List<AppThread> getThreads(List<Long> threadIds) {
        return threadRepository.findAllById(threadIds);
    }
}
