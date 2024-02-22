package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private TutorRepository tutorRepository;

    public TutorDTO getTutorById(Integer id){
        if(id==null)
            return null;
        Tutor tutor = tutorRepository.findById(id).get();
        TutorDTO tutorDTO = Converter.tutorToDTO(tutor);
        return tutorDTO;
    }
}
