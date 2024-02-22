package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.TutorDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorProfileController {
    /*
    @Autowired
    TutorProfileService tutorProfileService;
    */
    @GetMapping(value = "/tutorProfile")
    public String showPage(ModelMap model, HttpSession session) {
        TutorDTO tutor = (TutorDTO) session.getAttribute("user");

        model.addAttribute("tutor", tutor);
        model.addAttribute("role", (String) session.getAttribute("role"));
        return "tutorProfile";
    }

}
