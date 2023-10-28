package elec5619.sydney.edu.au.mental_health_support_website.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import elec5619.sydney.edu.au.mental_health_support_website.controller.param.AppThreadInfo;
import elec5619.sydney.edu.au.mental_health_support_website.controller.res.ThreadCommentRes;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.*;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.UserRepository;
import elec5619.sydney.edu.au.mental_health_support_website.service.*;
import elec5619.sydney.edu.au.mental_health_support_website.util.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/threads/")
@CrossOrigin(origins = "http://localhost:3000")
public class ThreadController {
    @Autowired
    private ThreadCommentService threadCommentService;
    @Autowired
    private AppThreadService threadService;
    @Autowired
    private ThreadTagService threadTagService;
    @Autowired
    private ThreadTagRelationshipService threadTagRelationshipService;
    @Resource
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // For testing purposes
    @GetMapping("/test-create")
    public AppThreadInfo testCreate() throws JsonProcessingException {
        AppThread aThread = AppThread.builder().title(
                        "Body Dismorphic Disorder").content(
                        "\uD83D\uDD25 Body Dysmorphic Disorder (BDD) - is another mental health condition included in " +
                                "the Obsessive-Compulsive & Related disorders category. Individuals with this disorder " +
                                "tend to have excessive concern over a body part because they believe that the body " +
                                "part appears abnormal, defective or embarrassing in some way.")
                .authorID(2L)
                .build();
        AppThread thr = threadService.createThread(aThread);
        String authorName = userService.getUserByUserId(thr.getAuthorID()).getUsername();
        return AppThreadInfo.builder().thread(thr).tagNames(Arrays.asList(new String[]{"depression", "anxiety"})).authorName(authorName).build();
    }


    /**
     * Post method for creating new thread
     *
     * @param context a thread object & a list of tags of type string, with all required information for creating thread
     * @return a newly created thread if not exists
     */
    @PostMapping("/create")
    public AppThreadInfo createThread(
            @RequestHeader("token") String token,
            @RequestBody AppThreadInfo context
    ) {
        AppThread thread = context.getThread();
        String username = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(username);

        thread.setAuthorID(user.getId());
        thread = threadService.createThread(thread);

        List<String> tagNames = Arrays.asList(context.getThread().getTags().split(","));
        List<ThreadTag> tags = threadTagService.getTagByNames(tagNames);
        insertThreadTagRelationship(thread, tags);
        return AppThreadInfo.builder()
                .thread(thread)
                .tagNames(getTagNamesFromThreadTags(tags))
                .authorName(username)
                .build();
    }

    /**
     * Method for getting a list of tagNames from a list of tag object
     *
     * @param tags a list of tag objects
     * @return a list of tag names
     */
    private List<String> getTagNamesFromThreadTags(List<ThreadTag> tags) {
        List<String> tagNames = new ArrayList<>();
        for (ThreadTag tag : tags) {
            tagNames.add(tag.getName());
        }
        return tagNames;
    }

    /**
     * Inserting (threadId, tagId) into ThreadTagRelationship table to keep track of what tags are linked to what threads
     *
     * @param thr  the thread object
     * @param tags a list of tags
     */
    private void insertThreadTagRelationship(AppThread thr, List<ThreadTag> tags) {
        for (ThreadTag tag : tags) {
            ThreadTagRelationship obj = ThreadTagRelationship.builder()
                    .threadId(thr.getId())
                    .tagId(tag.getId())
                    .build();
            threadTagRelationshipService.insertThreadTagRelationship(obj);
        }
    }

    // Requesting materials for thread

    /**
     * Get method for getting a single thread
     *
     * @param threadId the id of the thread requested
     * @return the thread object associated with the requested id otherwise null
     */
    @GetMapping("/get/id/{threadId}")
    public AppThreadInfo getThread(
            @PathVariable Long threadId
    ) {
        AppThread thread = threadService.getThread(threadId);
        List<ThreadTag> tags = getThreadTagsFromThreadId(threadId);
        List<String> tagNames = getTagNamesFromThreadTags(tags);
        List<ThreadComment> comments = threadCommentService.getThreadComments(threadId);
        for (ThreadComment comment : comments) {
            comment.setCommentAuthor(userService.getUserByUserId(comment.getUserId()).getUsername());
        }
        String authorName = userService.getUserByUserId(thread.getAuthorID()).getUsername();


        return AppThreadInfo.builder()
                .thread(thread)
                .tagNames(tagNames)
                .authorName(authorName)
                .comments(comments)
                .build();
    }

    /**
     * method for getting a list of ThreadTag objects associated with a threadId
     *
     * @param threadId the requested thread id
     * @return a list of ThreadTag objects
     */
    private List<ThreadTag> getThreadTagsFromThreadId(Long threadId) {
        List<ThreadTagRelationship> tagIds = threadTagRelationshipService.getTags(threadId);

        // Gathering a list of existing tags
        List<ThreadTag> currentTags = new ArrayList<>();
        for (ThreadTagRelationship tag : tagIds)
            currentTags.add(getTag(tag.getTagId()));
        return currentTags;
    }

    /**
     * Get method for getting a list of tags associated with a thread id
     *
     * @param threadId the requested thread id
     * @return a list of threads if exists, otherwise an empty list
     */
    @GetMapping("/get/tag/id/{threadId}")
    public List<ThreadTag> getThreadTags(
            @PathVariable Long threadId
    ) {
        List<ThreadTagRelationship> tagIds = threadTagRelationshipService.getTags(threadId);
        return threadTagService.findAllByIdIn(getTagIdFromTagRelationship(tagIds));
    }

    private List<Long> getTagIdFromTagRelationship(List<ThreadTagRelationship> lst) {
        List<Long> ids = new ArrayList<>();
        for (ThreadTagRelationship obj : lst) {
            ids.add(obj.getTagId());
        }
        return ids;
    }

    /**
     * Get method for getting a list of threads provided by their ids
     *
     * @param threadIds a list of thread ids requested
     * @return a list of thread objects associated with their ids, otherwise an empty list
     */
    @PostMapping("/get/ids")
    public List<AppThreadInfo> getThreads(
            @RequestBody List<Long> threadIds
    ) {
        List<AppThread> threads = threadService.getThreads(threadIds);
        List<AppThreadInfo> threadInfos = new ArrayList<>();
        for (AppThread thread : threads) {
            List<ThreadTag> tags = getThreadTagsFromThreadId(thread.getId());
            List<String> tagNames = getTagNamesFromThreadTags(tags);
            String authorName = userService.getUserByUserId(thread.getAuthorID()).getUsername();
            threadInfos.add(
                    AppThreadInfo.builder()
                            .thread(thread)
                            .tagNames(tagNames)
                            .authorName(authorName)
                            .build()
            );
        }
        return threadInfos;
    }

    /**
     * Get method for getting all existing threads from the systems
     *
     * @return a list of AppThreadInfo
     */
    @GetMapping("/get/all")
    public List<AppThreadInfo> getAllExistingThreads() {
        List<AppThread> threads = threadService.getAllExistingThreads();
        List<AppThreadInfo> threadInfos = new ArrayList<>();
        for (AppThread thread : threads) {
            List<ThreadTag> tags = getThreadTagsFromThreadId(thread.getId());
            List<String> tagNames = getTagNamesFromThreadTags(tags);
            String authorName = userService.getUserByUserId(thread.getAuthorID()).getUsername();
            threadInfos.add(
                    AppThreadInfo.builder()
                            .thread(thread)
                            .tagNames(tagNames)
                            .authorName(authorName)
                            .noComments(threadCommentService.countCommentsByThreadId(thread.getId()))
                            .build()
            );
        }
        return threadInfos;
    }

    /**
     * Post method for getting a list of thread given the author Id
     *
     * @param authorId the id of the author requested
     * @return a list of threads
     */
    @PostMapping("/get/byAuthor")
    public List<AppThreadInfo> getThreadsBasedOnAuthorID(
            @RequestBody Long authorId
    ) {
        List<AppThread> threads = threadService.findByAuthorID(authorId);
        List<AppThreadInfo> threadInfos = new ArrayList<>();
        for (AppThread thread : threads) {
            List<ThreadTag> tags = getThreadTagsFromThreadId(thread.getId());
            List<String> tagNames = getTagNamesFromThreadTags(tags);
            String authorName = userService.getUserByUserId(thread.getAuthorID()).getUsername();
            threadInfos.add(
                    AppThreadInfo.builder()
                            .thread(thread)
                            .tagNames(tagNames)
                            .authorName(authorName)
                            .noComments(threadCommentService.countCommentsByThreadId(thread.getId()))
                            .build()
            );
        }
        return threadInfos;
    }

    /**
     * Post method for getting a list of thread given a user token
     *
     * @param token the token of the author requested
     * @return a list of threads
     */
    @GetMapping("/get/byUserToken")
    public List<AppThreadInfo> getThreadsBasedOnUserToken(
            @RequestHeader("token") String token
    ) {
        try {
            Long authorId = userService.getUserByUsername(TokenUtil.getUsernameFromToken(token)).getId();
            if(authorId == null) {
                return new ArrayList<>();
            }
            List<AppThread> threads = threadService.findByAuthorID(authorId);
            if (threads == null || threads.isEmpty()) {
                return new ArrayList<>();
            }
            List<AppThreadInfo> threadInfos = new ArrayList<>();
            for (AppThread thread : threads) {
                List<ThreadTag> tags = getThreadTagsFromThreadId(thread.getId());
                List<String> tagNames = getTagNamesFromThreadTags(tags);
                String authorName = userService.getUserByUserId(thread.getAuthorID()).getUsername();
                threadInfos.add(
                        AppThreadInfo.builder()
                                .thread(thread)
                                .tagNames(tagNames)
                                .authorName(authorName)
                                .noComments(threadCommentService.countCommentsByThreadId(thread.getId()))
                                .build()
                );
            }
            return threadInfos;
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }


    /**
     * Put method for requesting a change in thread content, this ranging from
     * thread content, a number of thread tags provided
     * only the requested user, who created the thread, can edit the thread content
     *
     * @param threadId the id of the thread to be changed
     * @param context  the thread that the requested thread object to change to
     */
    @PutMapping("/update/{threadId}")
    public boolean editThread(
            @PathVariable Long threadId,
            @RequestHeader("token") String token,
            @RequestBody AppThreadInfo context
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && isUserEligibleToModifyThread(user.getId(), threadId)) {
            AppThread thread = context.getThread();
            List<String> tagNames = context.getTagNames();
            threadService.editThread(thread);
            updateThreadTagRelationship(threadId, tagNames);
            return true;
        }
        return false;
    }

    /**
     * method used to update all related information to thread, it also add & remove any relevant tags from the requested
     * thread
     *
     * @param threadId the id of the thread to be updated
     * @param tagNames a list of tag names to be updated
     */
    private void updateThreadTagRelationship(Long threadId, List<String> tagNames) {
        List<ThreadTag> currentTags = getThreadTagsFromThreadId(threadId);

        this.addNewTagsToThread(threadId, currentTags, tagNames);
        this.removeTagsFromThread(threadId, currentTags, tagNames);
    }

    /**
     * method used to add a relationship between a thread and a list of tags id
     *
     * @param threadId    the id the thread requested
     * @param currentTags the current list of tags that the thread has
     * @param tagNames    a list of tag names associated with the requested thread to be updated
     */
    private void addNewTagsToThread(Long threadId, List<ThreadTag> currentTags, List<String> tagNames) {
        List<ThreadTag> tagsToAdd = new ArrayList<>();
        // Filling out all the tag to adds
        for (String name : tagNames) {
            boolean nameFound = false;
            for (ThreadTag tag : currentTags) {
                if (tag.getName().equals(name)) {
                    nameFound = true;
                    break;
                }
            }

            if (!nameFound) {
                ThreadTag tmpTag = threadTagService.getTagByName(name);
                if (tmpTag != null) {
                    tagsToAdd.add(tmpTag);
                }
            }
        }
        for (ThreadTag tag : tagsToAdd) {
            threadTagRelationshipService.insertThreadTagRelationship(
                    ThreadTagRelationship.builder().threadId(threadId).tagId(tag.getId()).build()
            );
        }
    }

    /**
     * method used to remove a relationship between a thread and a list of tags id
     *
     * @param threadId    the id the thread requested
     * @param currentTags the current list of tags that the thread has
     * @param tagNames    a list of tag names associated with the requested thread to be updated
     */
    private void removeTagsFromThread(Long threadId, List<ThreadTag> currentTags, List<String> tagNames) {
        List<ThreadTag> tagsToRemove = new ArrayList<>();

        // Filling out all the tags to remove
        for (ThreadTag tag : currentTags) {
            boolean tagFound = false;
            for (String name : tagNames) {
                if (tag.getName().equals(name)) {
                    tagFound = true;
                    break;
                }
            }

            if (!tagFound) {
                tagsToRemove.add(tag);
            }
        }

        // Find all the ThreadTagRelationship objects to be removed
        for (ThreadTag tag : tagsToRemove) {
            ThreadTagRelationship obj = threadTagRelationshipService.findByThreadIdAndTagId(threadId, tag.getId());
            if (obj != null)
                threadTagRelationshipService.removeThreadTagRelationship(obj);
        }
    }

    /**
     * Put method for removing a certain thread, only the user created the thread and the admin
     * can remove the thread
     *
     * @param threadId the id of the thread requested to be deleted
     * @param token    the token of the user
     */
    @DeleteMapping("/delete/{threadId}")
    public boolean removeThread(
            @PathVariable Long threadId,
            @RequestHeader("token") String token
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && isUserEligibleToModifyThread(user.getId(), threadId)) {
            threadService.removeThread(threadId);
            threadTagRelationshipService.deleteByThreadId(threadId);
            return true;
        }
        return false;
    }

    /**
     * Get method for searching a list of thread given a tag name
     *
     * @param tagMap the name of the tag to be searched
     * @return a list of thread associated with the tag name
     */
    @PostMapping("/search/tag")
    public List<AppThreadInfo> searchThreadByTag(
            @RequestBody Map<String, String> tagMap
    ) {

        if (tagMap.get("tagName").equals("")) {
            return getAllExistingThreads();
        }
        ThreadTag tagObj = threadTagService.getTagByName(tagMap.get("tagName"));
        if (tagObj == null) {
            return new ArrayList<>();
        }
        // I need  to find all the relationships
        List<ThreadTagRelationship> tags = threadTagRelationshipService.getThread(tagObj.getId());

        // Getting the tag name
        List<String> tagNames = new ArrayList<>();
        tagNames.add(tagMap.get("tagName"));

        // Now getting all the AppThread
        List<AppThreadInfo> resultSet = new ArrayList<>();
        for (ThreadTagRelationship tag : tags) {
            AppThread thread = threadService.getThread(tag.getThreadId());
            resultSet.add(
                    AppThreadInfo.builder()
                            .thread(thread)
                            .tagNames(tagNames)
                            .noComments(threadCommentService.countCommentsByThreadId(thread.getId()))
                            .build()
            );
        }

        return resultSet;
    }


    /**
     * method used to check if the user can edit or remove thread
     * an eligible users are those either created the thread or has the admin user_type
     *
     * @param userId   the id of the user
     * @param threadId the id of the thread to be modified
     * @return TRUE if the user meets the requirement otherwise FALSE
     */
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
     *
     * @param tag the tag to be added to the system
     * @return the newly created tag or not exists, otherwise return existing tag
     */
    @PostMapping("/tag/add")
    public ThreadTag addNewTag(
            @RequestBody ThreadTag tag
    ) {
        return threadTagService.createThreadTag(tag);
    }

    /**
     * Get method for getting all the tags in the database
     *
     * @return a list of tags available in the system associated with thread
     */
    @GetMapping("/tag/getAll")
    public List<ThreadTag> getAllTags() {
        return threadTagService.getAllTags();
    }

    /**
     * Get method for getting a single thread tag from the database
     *
     * @param tagId the id of the tag requested
     * @return a single tag associated with the provided id
     */
    @GetMapping("/tag/get/{tagId}")
    public ThreadTag getTag(
            @PathVariable Long tagId
    ) {
        return threadTagService.getTag(tagId);
    }

    /**
     * Put method for editing the existing thread tag
     *
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
     * Put method for removing thread tag
     *
     * @param tagId the id of thread tag to be removed
     */
    @DeleteMapping("/tag/{tagId}/remove")
    public void removeTag(
            @PathVariable Long tagId
    ) {
        threadTagService.removeThreadTag(tagId);
    }

    // Request methods for thread comment


    /**
     * a post method for creating a thread comment associated with a specific thread
     *
     * @param comment the comment to be created
     * @return the newly created comment from the database
     */
    @PostMapping("/comment/create")
    public ThreadComment createThreadComment(
            @RequestHeader("token") String userToken,
            @RequestBody ThreadCommentRes comment
    ) {

        System.out.println(userToken);
        String username = TokenUtil.getUsernameFromToken(userToken);
        Long userId = userService.getUserByUsername(username).getId();
        Long threadId = Long.parseLong(comment.getThreadId());
        ThreadComment obj = ThreadComment.builder()
                .userId(userId)
                .comment(comment.getComment())
                .threadId(threadId)
                .timestamp(comment.getTimestamp())
                .build();
        return threadCommentService.createThreadComment(obj);
    }

    /**
     * a put method that allows eligible user to edit the thread comment
     *
     * @param commentId  the requested comment id
     * @param token      the token of the user
     * @param newComment the changed or altered comment
     * @return true if the operation is successful otherwise false
     */
    @PutMapping("/comment/{commentId}/edit")
    public boolean editThreadComment(
            @PathVariable Long commentId,
            @RequestHeader("token") String token,
            @RequestBody ThreadComment newComment
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && !isUserEligibleToModifyThreadComment(user.getId(), commentId)) {
            return false;
        }
        threadCommentService.editThreadComment(newComment);
        return true;
    }

    /**
     * a put method that allows eligible user to remove the thread comment
     *
     * @param commentId the requested comment id
     * @param token     the token of the user
     * @return true if the operation is successful otherwise false
     */
    @DeleteMapping("/comment/{commentId}/delete")
    public boolean removeThreadComment(
            @PathVariable Long commentId,
            @RequestHeader("token") String token
    ) {
        String userName = TokenUtil.getUsernameFromToken(token);
        Users user = userService.getUserByUsername(userName);
        if (user != null && !isUserEligibleToModifyThreadComment(user.getId(), commentId)) {
            return false;
        }
        threadCommentService.removeThreadComment(commentId);
        return true;
    }

    /**
     * used to check if the user are allowed to alter or delete a thread comment.
     * Eligible users must be either an admin or the original creator of the comment
     *
     * @param userId    the id of user requested for the changed
     * @param commentId the id of thread comment to be changed
     * @return true if the user is eligible, false otherwise
     */
    private boolean isUserEligibleToModifyThreadComment(Long userId, Long commentId) {
        Users user = userRepository.findById(userId).orElse(null);
        ThreadComment thrComment = threadCommentService.getThreadComment(commentId);
        if (user == null || thrComment == null) {
            // the user and thread has to exists
            return false;
        } else if (user.getUserType().equals("admin")) {
            // the user is an admin, force delete
            return true;
        } else if (!thrComment.getUserId().equals(userId)) {
            // if the user id is not the same as the author id, not allowed
            return false;
        }
        // otherwise delete
        return true;
    }


}
