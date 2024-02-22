package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.ModifyTutorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyTutorController {
    @Autowired
    private ModifyTutorService modifyTutorService;

    @GetMapping("/modifyTutor")
    public String showPage(ModelMap model, HttpSession session) {
        TutorDTO tutor = (TutorDTO) session.getAttribute("user");
        model.addAttribute("tutor", tutor);
        model.addAttribute("role", (String) session.getAttribute("role"));
        return "modifyTutor";
    }

    @PostMapping("/modifyTutor")
    public String changeData(ModelMap model, HttpSession session,
                             @RequestParam String passwordHash,
                             @RequestParam String check,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String email,
                             @RequestParam String photo,
                             @RequestParam String subjects,
                             @RequestParam Integer price,
                             @RequestParam String currency,
                             @RequestParam String location,
                             @RequestParam String description) {
        TutorDTO tutor = (TutorDTO) session.getAttribute("user");
        tutor.setDescription(description);
        tutor.setLocation(location);
        tutor.setCurrency(currency);
        tutor.setPrice(price);
        tutor.setSubjects(subjects);
        tutor.setPhoto(photo);
        tutor.setEmail(email);
        if (check.equals("si")) {
            tutor.setPswHash(passwordHash);
        }
        tutor.setName(name);
        tutor.setSurname(surname);

        modifyTutorService.updateTutor(tutor);

        session.setAttribute("user", tutor);
        return "redirect:/tutorProfile";
    }

}
