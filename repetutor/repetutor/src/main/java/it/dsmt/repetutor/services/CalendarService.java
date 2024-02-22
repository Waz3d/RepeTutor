package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Lesson;
import it.dsmt.repetutor.accessingmysql.entities.LessonView;
import it.dsmt.repetutor.accessingmysql.repositories.LessonRepository;
import it.dsmt.repetutor.accessingmysql.repositories.LessonViewRepository;
import it.dsmt.repetutor.dto.LessonDTO;
import it.dsmt.repetutor.dto.LessonViewDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class CalendarService {

    @Autowired
    private LessonViewRepository lessonViewRepository;

    public ArrayList<LessonViewDTO> findFutureLessons(Integer id, String role, Date date) {
        ArrayList<LessonView> list = new ArrayList<>();
        if (role.equals("student")) {
            list = lessonViewRepository.findByStudentAndDateGreaterThanEqualOrderByDate(id, date);
        }
        else if(role.equals("tutor")) {
            list = lessonViewRepository.findByTutorAndDateGreaterThanEqualOrderByDate(id, date);
        }

        ArrayList<LessonViewDTO> myList = new ArrayList<>();
        for (LessonView l : list)
            myList.add(Converter.lessonViewToDTO(l));
        return myList;
    }

    public ArrayList<LessonViewDTO> findPastLessons(Integer id, String role, Date date) {
        ArrayList<LessonView> list = new ArrayList<>();
        if (role.equals("student")) {
            list = lessonViewRepository.findByStudentAndDateLessThanOrderByDate(id, date);
        }
        else if(role.equals("tutor")) {
            list = lessonViewRepository.findByTutorAndDateLessThanOrderByDate(id, date);
        }

        ArrayList<LessonViewDTO> myList = new ArrayList<>();
        for (LessonView l : list)
            myList.add(Converter.lessonViewToDTO(l));
        return myList;
    }

}
