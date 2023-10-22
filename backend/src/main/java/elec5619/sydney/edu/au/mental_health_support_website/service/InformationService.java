package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.controller.res.InfoRes;
import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Information;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.InformationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {
    @Resource
    private InformationRepository informationRepository;

    public List<Information> getAll() {
        return informationRepository.findAll();
    }

    public void insert(InfoRes infoRes) {
        id += 1;
        Information information = Information.builder().author(infoRes.getAuthor()).title(infoRes.getTitle()).
                content(infoRes.getContent()).build();
        informationRepository.save(information);
    }
}