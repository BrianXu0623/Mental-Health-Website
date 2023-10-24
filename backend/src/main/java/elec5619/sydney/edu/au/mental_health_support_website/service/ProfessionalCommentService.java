package elec5619.sydney.edu.au.mental_health_support_website.service;

import elec5619.sydney.edu.au.mental_health_support_website.db.entities.ProfessionalComment;
import elec5619.sydney.edu.au.mental_health_support_website.db.repository.ProfessionalCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfessionalCommentService {
    @Autowired
    private ProfessionalCommentRepository repository;

    public ProfessionalComment createProfessionalComment(ProfessionalComment professionalComment) {
        return repository.save(professionalComment);
    }

    public List<ProfessionalComment> getProfessionalComments(Long professionalId) {
        return repository.findAllByProfessionalId(professionalId);
    }
}
