package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModifyTutorService {
    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public void updateTutor(TutorDTO tutor) {
        tutorRepository.findById(tutor.getId()).map(target -> {
            target.setName(tutor.getName());
            target.setCurrency(tutor.getCurrency());
            target.setSurname(tutor.getSurname());
            target.setDescription(tutor.getDescription());
            target.setEmail(tutor.getEmail());
            target.setLocation(tutor.getLocation());
            target.setPhoto(tutor.getPhoto());
            target.setPrice(tutor.getPrice());
            target.setPswHash(tutor.getPswHash());
            target.setSubjects(tutor.getSubjects());
            return target;
        });

    }
}
