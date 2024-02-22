package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.LessonDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.AddLessonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

@Controller
public class AddLessonController {

    @Autowired
    private AddLessonService addLessonService;

    @GetMapping(value= "/addLesson")
    public String showAddLesson(ModelMap model, HttpSession session, @RequestParam String id){
        String subjects = ((TutorDTO)session.getAttribute("user")).getSubjects();
        List<String> subjectList = Arrays.asList(subjects.split(", ", -1));
        model.addAttribute("tutor", ((TutorDTO)session.getAttribute("user")).getId());
        model.addAttribute("subjects", subjectList);
        model.addAttribute("idStudent", Integer.valueOf(id));
        return "addLesson";
    }

    @PostMapping(value="/addLesson")
    public String addLesson(ModelMap model, HttpSession session,
                            @RequestParam Date date,
                            @RequestParam Integer student,
                            @RequestParam String subject,
                            @RequestParam String start,
                            @RequestParam Integer duration,
                            @RequestParam Integer tutor){
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setTutor(tutor);
        lessonDTO.setStudent(student);
        lessonDTO.setStart(start);
        lessonDTO.setDuration(duration.toString());
        lessonDTO.setSubject(subject);
        lessonDTO.setDate(date);
        addLessonService.addLesson(lessonDTO);
        return "redirect:/calendar";
    }
}
