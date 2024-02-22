package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Student;
import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import it.dsmt.repetutor.accessingmysql.repositories.StudentRepository;
import it.dsmt.repetutor.accessingmysql.repositories.TutorRepository;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SignInService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public void addTutor(TutorDTO tutorDTO){
        Tutor tutor = Converter.DTOtoTutor(tutorDTO);
        tutorRepository.save(tutor);
    }

    public void addStudent(StudentDTO studentDTO){
        Student student = Converter.DTOtoStudent(studentDTO);
        studentRepository.save(student);
    }

    public boolean checkUniquenessStudent(String username, String email){
        if(studentRepository.findByUsername(username) != null || studentRepository.findByEmail(email) != null)
            return false;
        return true;
    }

    public boolean checkUniquenessTutor(String username, String email){
        if(tutorRepository.findByUsername(username) != null || tutorRepository.findByEmail(email) != null)
            return false;
        return true;
    }
}
