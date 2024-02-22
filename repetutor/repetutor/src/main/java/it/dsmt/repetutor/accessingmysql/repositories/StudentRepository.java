package it.dsmt.repetutor.accessingmysql.repositories;

import it.dsmt.repetutor.accessingmysql.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByUsername(String username);

    Student findByEmail(String email);
}
