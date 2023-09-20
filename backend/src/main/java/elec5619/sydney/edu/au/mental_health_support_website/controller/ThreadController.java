package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.AppThread;
import elec5619.sydney.edu.au.mental_health_support_website.service.AppThreadService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadCommentService;
import elec5619.sydney.edu.au.mental_health_support_website.service.ThreadTagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/threads/")

public class ThreadController {
    @Resource
    private ThreadCommentService threadCommentService;

    @Resource
    private AppThreadService threadService;
    @Resource
    private ThreadTagService threadTagService;

    @PostMapping("/create")
    public AppThread createThread(
        @RequestBody AppThread thread
    ) {
        return threadService.createThread(thread);
    }

    @GetMapping("/get")
    public AppThread getThread(
            @RequestBody Long threadId
    ) {
        return threadService.getThread(threadId);
    }

    @PutMapping("/update/{threadId}")
    public void editThread(
            @PathVariable Long threadId,
            @RequestBody AppThread thread
    ) {
        // TODO: make changes to the tags too
        threadService.editThread(threadId, thread);
    }
}
