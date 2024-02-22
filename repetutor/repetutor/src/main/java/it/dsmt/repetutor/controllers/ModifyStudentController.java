package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.services.ModifyStudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModifyStudentController {

    @Autowired
    private ModifyStudentService modifyStudentService;

    @GetMapping("/modifyStudent")
    public String showPage(ModelMap model, HttpSession session) {
        StudentDTO student = (StudentDTO) session.getAttribute("user");
        model.addAttribute("tutor", student);
        model.addAttribute("role", (String) session.getAttribute("role"));
        return "modifyStudent";
    }

    @PostMapping("/modifyStudent")
    public String changeData(ModelMap model, HttpSession session,
                             @RequestParam String passwordHash,
                             @RequestParam String check,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String email,
                             @RequestParam String photo) {
        StudentDTO student = (StudentDTO) session.getAttribute("user");
        student.setPhoto(photo);
        student.setEmail(email);
        if (check.equals("si")) {
            student.setPswHash(passwordHash);
        }
        student.setName(name);
        student.setSurname(surname);

        modifyStudentService.updateStudent(student);
        session.setAttribute("user", student);
        return "redirect:/modifyStudent";
    }

}
