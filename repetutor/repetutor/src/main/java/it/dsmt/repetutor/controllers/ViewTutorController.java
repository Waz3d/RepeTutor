package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.SearchPageService;
import it.dsmt.repetutor.services.ViewTutorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ViewTutorController {
    @Autowired
    private ViewTutorService viewTutorService;

    @GetMapping(value="/viewTutor")
    public String getRequest(ModelMap model, HttpSession session, @RequestParam String id) {
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("tutorData", viewTutorService.getTutorById(Integer.valueOf(id)));
        return "viewTutor";
    }
}
