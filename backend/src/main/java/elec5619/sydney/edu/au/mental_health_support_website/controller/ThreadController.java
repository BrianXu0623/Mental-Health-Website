package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadComment;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ThreadTag;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Users;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.service.AppThreadService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadCommentService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadTagService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threads/")

public class ThreadController {
    @Autowired
    private ThreadCommentService threadCommentService;

    @Autowired
    private AppThreadService threadService;

    @Autowired
    private ThreadTagService threadTagService;

    @Resource
    private UserRepository userRepository;

    // For testing purposes
    @GetMapping("/test-create")
    public AppThread testCreate() {
        AppThread aThread = AppThread.builder().title(
                "Body Dismorphic Disorder").content(
                "\uD83D\uDD25 Body Dysmorphic Disorder (BDD) - is another mental health condition included in the Obsessive-Compulsive & Related disorders category. Individuals with this disorder tend to have excessive concern over a body part because they believe that the body part appears abnormal, defective or embarrassing in some way.")
                .tags(
                "anxiety,disorder,obsessive-compulsive")
                .authorID(2L)
        .build();
        return threadService.createThread(aThread);
    }


    /**
     * Post method for creating new thread
     * @param thread a thread object with all required information for creating thread
     * @return a newly created thread if not exists
     */
    @PostMapping("/create")
    public AppThread createThread(
        @RequestBody AppThread thread
    ) {
        if (verifyTags(thread.getTags())) {
            return null;
        }
        return threadService.createThread(thread);
    }

    private boolean verifyTags(String tags) {
        List<ThreadTag> allTags = threadTagService.getAllTags();
        for (String tag : tags.split(",")) {
            if (!allTags.contains(tag)) {
                return false;
            }
        }
        return true;
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
    public List<AppThread> getThreads(
            @RequestBody List<Long> threadIds
    ) {
        return threadService.getThreads(threadIds);
    }

    /**
     * Put method for requesting a change in thread content, this ranging from
     * thread content, a number of thread tags provided
     * only the requested user, who created the thread, can edit the thread content
     * @param threadId the id of the thread to be changed
     * @param thread the thread that the requested thread object to change to
     */
    @PutMapping("/update/{threadId}")
    public boolean editThread(
            @PathVariable Long threadId,
            @RequestBody Long userId,
            @RequestBody AppThread thread
    ) {
        if (isUserEligibleToModifyThread(userId, threadId) && verifyTags(thread.getTags())) {
            threadService.editThread(thread);
           return true;
        }
        return false;
    }

    /**
     * Put method for removing a certain thread, only the user created the thread and the admin
     * can remove the thread
     * @param threadId the id of the thread requested to be deleted
     * @param userId the user id who requested the thread to be deleted
     */
    @PutMapping("/delete/{threadId}")
    public boolean removeThread(
            @PathVariable Long threadId,
            @RequestBody Long userId
    ) {
        if (isUserEligibleToModifyThread(userId, threadId)) {
            threadService.removeThread(threadId);
            return true;
        }
        return false;
    }

    private boolean isUserEligibleToModifyThread(Long userId, Long threadId) {
        Users user = userRepository.findById(userId).orElse(null);
        AppThread thr = threadService.getThread(threadId);
        if (user == null || thr == null) {
            // the user and thread has to exists
            return false;
        } else if (user.getUserType().equals("admin")) {
            // the user is an admin, force delete
            return true;
        } else if (!thr.getAuthorID().equals(userId)) {
            // if the user id is not the same as the author id, not allowed
            return false;
        }
        // otherwise delete
        return true;
    }

    // Request Methods for thread tags

    /**
     * Post method for adding new thread tag to the system
     * @param tag the tag to be added to the system
     * @return the newly created tag or not exists, otherwise return existing tag
     */
    @PostMapping("/tag/add")
    public ThreadTag addNewTag(
            @RequestBody ThreadTag tag
    ) {
        ThreadTag newTag = threadTagService.createThreadTag(tag);
        return newTag;
    }

    /**
     * Get method for getting all the tags in the database
     * @return a list of tags available in the system associated with thread
     */
    @GetMapping("/tag/getAll")
    public List<ThreadTag> getAllTags() {
       return threadTagService.getAllTags();
    }

    /**
     * Get method for getting a single thread tag from the database
     * @param tagId the id of the tag requested
     * @return a single tag associated with the provided id
     */
    @GetMapping("/tag/get")
    public ThreadTag getTag(
            @RequestBody Long tagId
    ) {
       return threadTagService.getTag(tagId);
    }

    /**
     * Put method for editing the existing thread tag
     * @param tag the edit thread tag
     */
    @PutMapping("/tag/{tagId}/edit")
    public void editTag(
            @PathVariable Long tagId,
            @RequestBody ThreadTag tag
    ) {
        threadTagService.editThreadTag(tagId, tag);
    }

    /**
     * Delete method for removing thread tag
     * @param tagId the id of thread tag to be removed
     */
    @DeleteMapping("/tag/{tagId}/remove")
    public void removeTag(
            @PathVariable Long tagId
    ) {
        threadTagService.removeThreadTag(tagId);
    }

    // Request methods for thread comment
    @GetMapping("/comment/getAll")
    public List<ThreadComment> getAllComments() {
        return threadCommentService.getAllComments();
    }


}
