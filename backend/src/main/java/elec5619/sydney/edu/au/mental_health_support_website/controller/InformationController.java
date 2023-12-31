package elec5619.sydney.edu.au.mental_health_support_website.controller;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Information;
import elec5619.sydney.edu.au.mental_health_support_website.service.InformationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class InformationController {
    @Resource
    private InformationService informationService;

    /** Get method that retrieve all the information on the main page.
     *
     * @return All the information
     */
    @GetMapping("/information")
    public List<Information> getInfo() {
        List<Information> informationList = informationService.getAll();
        return informationList;
    }

    /**
     * Get method that retrieve a specific information based on its id.
     * @param infoId id of the information
     * @return The detail of the information
     */
    @GetMapping("/information/{infoId}")
    public Information getInfoById(@PathVariable Long infoId) {
        Optional<Information> informationOptional = informationService.getById(infoId);
        return informationOptional.orElse(null);
    }

}