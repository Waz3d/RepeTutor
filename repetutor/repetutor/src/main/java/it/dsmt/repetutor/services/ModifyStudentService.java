package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.repositories.StudentRepository;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ModifyStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void updateStudent(StudentDTO student) {
        studentRepository.findById(student.getId()).map(target -> {
            target.setName(student.getName());
            target.setSurname(student.getSurname());
            target.setEmail(student.getEmail());
            target.setPhoto(student.getPhoto());
            target.setPswHash(student.getPswHash());
            return target;
        });

    }
}
