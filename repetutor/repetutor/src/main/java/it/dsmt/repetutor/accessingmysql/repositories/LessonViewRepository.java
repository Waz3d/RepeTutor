package it.dsmt.repetutor.accessingmysql.repositories;

import it.dsmt.repetutor.accessingmysql.entities.Lesson;
import it.dsmt.repetutor.accessingmysql.entities.LessonView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;

@Repository
@Transactional
public interface LessonViewRepository extends PagingAndSortingRepository<LessonView, Integer> {
    public ArrayList<LessonView> findByStudentAndDateGreaterThanEqualOrderByDate(Integer student, Date date);

    public ArrayList<LessonView> findByTutorAndDateGreaterThanEqualOrderByDate(Integer tutor, Date date);

    public ArrayList<LessonView> findByTutorAndDateLessThanOrderByDate(Integer tutor, Date date);

    public ArrayList<LessonView> findByStudentAndDateLessThanOrderByDate(Integer student, Date date);
}
