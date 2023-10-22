package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.controller.res.InfoRes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class InformationController {
    @GetMapping("/information")
    public List<InfoRes> testCreate() {
        List<InfoRes> list = new ArrayList<>();
        InfoRes infoRes1 = InfoRes.builder().author("Dr. Jack")
                .title("The Hidden Struggles of University Students' Mental Health")
                .content("In a groundbreaking exploration into the often-overlooked realm of university students'" +
                        " mental health, our correspondent Emma Thompson delves into the challenges faced by today's " +
                        "young minds in the pursuit of higher education.\n" +
                        "\n" +
                        "Recent studies have unveiled a concerning rise in stress, anxiety, and depression among " +
                        "university students, shedding light on the pressing need for increased mental health support " +
                        "on campuses worldwide. With academic pressures, financial burdens, and the complexities of " +
                        "social life, students are grappling with a myriad of stressors that demand attention.\n" +
                        "\n")
        .build();

        InfoRes infoRes2 = InfoRes.builder().author("Dr. Thomas")
                .title("Coping Strategies and Resilience in University Students' Mental Health")
                .content("In this exclusive report, investigative journalist Alex Rodriguez explores the dynamic " +
                        "landscape of coping strategies and resilience among university students facing mental health challenges.\n" +
                        "\n" +
                        "Rodriguez delves into the personal stories of students who have not only confronted but" +
                        " triumphed over mental health hurdles. Through candid interviews and firsthand accounts, " +
                        "the article unveils the diverse array of coping mechanisms employed by students to navigate " +
                        "the demanding terrain of university life.")
                .build();

        InfoRes infoRes3 = InfoRes.builder().author("Dr. Emily")
                .title("The Untold Stories of University Students' Mental Health Divide")
                .content("In a thought-provoking exposé, feature writer Jordan Lee investigates the disparities in mental health experiences among university students, revealing a stark divide that often goes unnoticed.\n" +
                        "\n" +
                        "Lee's exploration unfolds through interviews with students from diverse backgrounds, " +
                        "shedding light on how factors such as socioeconomic status, cultural differences, and varying " +
                        "academic pressures can significantly impact mental health outcomes. The article confronts the " +
                        "uncomfortable truth that not all students have equal access to mental health resources and support.")
                .build();
        list.add(infoRes1);
        list.add(infoRes2);
        list.add(infoRes3);
        return list;
    }

}