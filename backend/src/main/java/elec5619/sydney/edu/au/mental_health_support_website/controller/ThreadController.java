package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.service.AppThreadService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadCommentService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadTagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threads/")

public class ThreadController {
    @Resource
    private ThreadCommentService threadCommentService;

    @Resource
    private AppThreadService threadService;
    @Resource
    private ThreadTagService threadTagService;

    /**
     * Post method for creating new thread
     * @param thread a thread object with all required information for creating thread
     * @return a newly created thread if not exists
     */
    @PostMapping("/create")
    public AppThread createThread(
        @RequestBody AppThread thread
    ) {
        return threadService.createThread(thread);
    }

    // Requesting materials for thread

    /**
     * Get method for getting a single thread
     * @param threadId the id of the thread requested
     * @return the thread object associated with the requested id otherwise null
     */
    @GetMapping("/get/id")
    public AppThread getThread(
            @RequestBody Long threadId
    ) {
        return threadService.getThread(threadId);
    }

    /**
     * Get method for getting a list of threads provided by their ids
     * @param threadIds a list of thread ids requested
     * @return a list of thread objects associated with their ids, otherwise an empty list
     */
    @GetMapping("/get/ids")
    public List<AppThread> getThread(
            @RequestBody List<Long> threadIds
    ) {
        return threadService.getThreads(threadIds);
    }

    /**
     * Put method for requesting a change in thread content, this ranging from
     * thread content, a number of thread tags provided
     * @param threadId the id of the thread to be changed
     * @param thread the thread that the requested thread object to change to
     */
    @PutMapping("/update/{threadId}")
    public void editThread(
            @PathVariable Long threadId,
            @RequestBody AppThread thread
    ) {
        // TODO: make changes to the tags too
        threadService.editThread(threadId, thread);
    }
}
