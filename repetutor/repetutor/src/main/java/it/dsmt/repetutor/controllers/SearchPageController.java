package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.SearchPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class SearchPageController {
    @Autowired
    private SearchPageService searchPageService;

    @GetMapping(value = "/search")
    public String showPage(ModelMap model, HttpSession session) {
        model.addAttribute("role", (String) session.getAttribute("role"));
        return "search";
    }

    @PostMapping(value = "/search")
    public String postRequest(ModelMap model, HttpSession session, @RequestParam String subject,
                              @RequestParam String city) {
        ArrayList<TutorDTO> tutors = searchPageService.getTutorBySubjectsAndCity(subject, city);
        model.addAttribute("tutorList", tutors);
        model.addAttribute("set", true);
        model.addAttribute("role", (String) session.getAttribute("role"));
        return "search";
    }
}