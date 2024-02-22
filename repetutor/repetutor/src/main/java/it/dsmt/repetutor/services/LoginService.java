package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Comment;
import it.dsmt.repetutor.accessingmysql.repositories.StudentRepository;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.entities.Student;
import it.dsmt.repetutor.utility.Converter;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TutorRepository tutorRepository;

    //gets a patient given a username
    public StudentDTO getStudent(String username) {
        Student student = studentRepository.findByUsername(username);
        return Converter.studentToDTO(student);
    }

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

    //gets a therapist given a username
    public TutorDTO getTutor(String username) {
        Tutor tutor = tutorRepository.findByUsername(username);
        TutorDTO tutorDTO = null;
        if(tutor!=null) {
            tutorDTO = Converter.tutorToDTO(tutor);
            tutorDTO.setAverageEvaluation(getAverage(tutor));
        }
        return tutorDTO;
    }

}
