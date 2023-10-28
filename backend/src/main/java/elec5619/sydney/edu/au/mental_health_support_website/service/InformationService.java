package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.Information;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.InformationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {
    @Resource
    private InformationRepository informationRepository;

    public List<Information> getAll() {
        return informationRepository.findAll();
    }

    public Optional<Information> getById(Long id) {
        return informationRepository.findById(id);
    }
}
