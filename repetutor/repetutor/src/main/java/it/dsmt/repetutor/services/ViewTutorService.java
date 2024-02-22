package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Comment;
import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewTutorService {
    @Autowired
    private TutorRepository tutorRepository;

    private double getAverage(Tutor tutor){
        if(tutor.getReceivedComments().size() == 0)
            return 0;
        double sum = 0;
        for(Comment comment : tutor.getReceivedComments()){
            sum += comment.getEvaluation();
        }
        double average = sum/(tutor.getReceivedComments().size()) * 100;
        double averageTruncated = Math.floor(average);
        return averageTruncated/100;
    }

    public TutorDTO getTutorById(Integer id) {
        Tutor tutor = tutorRepository.findById(id).get();
        TutorDTO tutorDTO = Converter.tutorToDTO(tutor);
        tutorDTO.setAverageEvaluation(getAverage(tutor));
        return tutorDTO;
    }
}
