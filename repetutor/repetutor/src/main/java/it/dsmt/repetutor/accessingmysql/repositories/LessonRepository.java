package it.dsmt.repetutor.accessingmysql.repositories;

import it.dsmt.repetutor.accessingmysql.entities.Lesson;
import it.dsmt.repetutor.accessingmysql.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.ArrayList;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    public ArrayList<Lesson> findByStudentAndDateGreaterThanEqualOrderByDate(Integer student, Date date);

    public ArrayList<Lesson> findByTutorAndDateGreaterThanEqualOrderByDate(Integer tutor, Date date);

    public ArrayList<Lesson> findByTutorAndDateLessThanOrderByDate(Integer tutor, Date date);

    public ArrayList<Lesson> findByStudentAndDateLessThanOrderByDate(Integer student, Date date);
}
