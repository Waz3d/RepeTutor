package it.dsmt.repetutor.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import it.dsmt.repetutor.services.LoginService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginUser(ModelMap model, HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        StudentDTO user = null;

        model.put("error", "There was an error during the login!");

        if(role.equals("student"))
            user = loginService.getStudent(username);
        else
            user = loginService.getTutor(username);

        if(user == null || (!role.equals("student") && !role.equals("tutor"))) {
            model.addAttribute("error", true);
            return "login";
        }


        String salt = user.getSalt();

        String newPassword = Hashing.sha256()
                .hashString(password + salt, StandardCharsets.UTF_8)
                .toString();

        if(!newPassword.equals(user.getPswHash())){
            System.out.println("Wrong password");
            model.addAttribute("error", true);
            return "login";
        }


        model.remove("error");
        String nextPage = "redirect:/search";
        if(role.equals("student")) {
            session.setAttribute("user", user);
        }
        else {
            TutorDTO tutor = (TutorDTO) user;

            session.setAttribute("user", tutor);
            nextPage = "redirect:/calendar";
        }

        session.setAttribute("role", role);

        return nextPage;
    }
}
