package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Comment;
import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchPageService {
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

    public ArrayList<TutorDTO> getTutorBySubjectsAndCity(String subject, String city) {

        String myCity;
        String mySubject;
        if(city == null) {
            myCity = "";
        } else {
            myCity = city;
        }
        if(subject == null) {
            mySubject = "";
        } else {
            mySubject = subject;
        }
        ArrayList<Tutor> tutorDTOList = tutorRepository.findBySubjectsContainingAndLocationContaining(mySubject, myCity);
        ArrayList<TutorDTO> tutorList = new ArrayList<TutorDTO>();
        for (Tutor tutor : tutorDTOList) {
            TutorDTO tutorDTO = Converter.tutorToDTO(tutor);
            tutorDTO.setAverageEvaluation(getAverage(tutor));
            tutorList.add(tutorDTO);
            //previous:
            //tutorList.add(Converter.tutorToDTO(tutor));
        }
        return tutorList;

    }
}
