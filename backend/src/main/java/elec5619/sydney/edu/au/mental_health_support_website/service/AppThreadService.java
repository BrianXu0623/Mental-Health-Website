package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.AppThreadRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppThreadService {
    @Resource
    private AppThreadRepository threadRepository;

    /**
     * method for creating thread object and add it to the database
     * @param thread the object to be created
     * @return the newly created thread object
     */
    public AppThread createThread(AppThread thread) {
        return threadRepository.save(thread);
    }

    /**
     * method for removing threat object from the database
     * @param threadId - the id of the thread provided
     * @return the true if thread object exists, otherwise false
     */
    public boolean removeThread(Long threadId) {
        AppThread thr = threadRepository.findById(threadId).orElse(null);
        if (thr == null) {
            return false;
        }
        threadRepository.deleteById(threadId);
        return true;
    }

    /**
     * edit the content of existing thread
     * @param edittedThread the freshly editted thread object
     * @return true if the object exists, otherwise false
     */
    public boolean editThread(AppThread edittedThread) {
        AppThread thr = threadRepository.findById(edittedThread.getId()).orElse(null);
        threadRepository.save(edittedThread);
        return thr != null;
    }

    /**
     * get a single thread object from the database
     * @param threadId the id of the thread to be retrieved
     * @return the requested thread if found, otherwise null
     */
    public AppThread getThread(Long threadId) {
        return threadRepository.findById(threadId).orElse(null);
    }

    /**
     * get a list of threads based on a list of ids
     * @param threadIds the ids of thread requested
     * @return a list of threads if found, otherwise an empty list
     */
    public List<AppThread> getThreads(List<Long> threadIds) {
        return threadRepository.findAllById(threadIds);
    }

    public List<AppThread> getAllExistingThreads() {
        return threadRepository.findAll();
    }

    public List<AppThread> findByAuthorID(Long authorId) {
        return threadRepository.findByAuthorID(authorId);
    }
}
