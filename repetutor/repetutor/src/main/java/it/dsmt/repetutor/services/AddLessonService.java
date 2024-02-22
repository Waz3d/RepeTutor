package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.entities.Lesson;
import it.dsmt.repetutor.accessingmysql.repositories.LessonRepository;
import it.dsmt.repetutor.dto.LessonDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddLessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public void addLesson(LessonDTO lessonDTO){
        Lesson lesson = Converter.DTOtoLesson(lessonDTO);
        lessonRepository.save(lesson);
    }
}
