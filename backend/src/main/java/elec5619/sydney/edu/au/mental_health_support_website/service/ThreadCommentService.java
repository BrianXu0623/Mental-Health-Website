package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadComment;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.ThreadCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadCommentService {
    @Autowired
    private ThreadCommentRepository respository;

    public ThreadComment createThreadComment(ThreadComment threadComment) {
        return respository.save(threadComment);
    }

    public void removeThreadComment(ThreadComment threadComment) {
        respository.delete(threadComment);
    }

    public void editThreadComment(ThreadComment threadComment) {
        respository.save(threadComment);
    }

    public List<ThreadComment> getThreadComments(Long threadId) {
        return respository.findAllByThreadId(threadId);
    }

    public ThreadComment getThreadComment(Long commentId) {
        return respository.findById(commentId).orElse(null);
    }

    public List<ThreadComment> getAllComments() {
        return respository.findAll();
    }
}

