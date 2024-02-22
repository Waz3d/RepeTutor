package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.LessonDTO;
import it.dsmt.repetutor.dto.LessonViewDTO;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.CalendarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @GetMapping(value = "/calendar")
    public String showPage(ModelMap model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        Integer id = - 1;
        if(role.equals("student")) {
            id = ((StudentDTO)session.getAttribute("user")).getId();
        }
        else if(role.equals("tutor")) {
            id = ((TutorDTO)session.getAttribute("user")).getId();
        }
        ArrayList<LessonViewDTO> listFuture = new ArrayList<>();
        listFuture = calendarService.findFutureLessons(id, role, new Date(System.currentTimeMillis()));
        model.addAttribute("listFuture", listFuture);

        ArrayList<LessonViewDTO> listPast = new ArrayList<>();
        listPast = calendarService.findPastLessons(id, role, new Date(System.currentTimeMillis()));
        model.addAttribute("listPast", listPast);

        model.addAttribute("role", role);
        return "calendar";
    }

}

