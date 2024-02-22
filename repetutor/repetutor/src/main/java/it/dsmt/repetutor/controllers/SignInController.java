package it.dsmt.repetutor.controllers;

import com.google.common.hash.Hashing;
import it.dsmt.repetutor.accessingmysql.entities.Comment;
import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.SignInService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;
    //private String mySalt;

    @GetMapping(value = "/signin")
    public String showPage(ModelMap model, HttpSession session) {
        byte[] bytes = new byte[16];
        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
            String mySalt = Base64.getEncoder().encodeToString(bytes);
            session.setAttribute("mySalt", mySalt);
            String hash = Hashing.sha256()
                    .hashString("tut" + mySalt, StandardCharsets.UTF_8)
                    .toString();
            model.put("salt", mySalt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            return "signin";
        }
    }
    
    @PostMapping(value = "/signin")
    public String registerUser(ModelMap model, HttpSession session,
                               @RequestParam String username,
                               @RequestParam String passwordHash,
                               @RequestParam String salt,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String email,
                               @RequestParam String photo,
                               @RequestParam String role,
                               @RequestParam String subjects,
                               @RequestParam String price,
                               @RequestParam String currency,
                               @RequestParam String location,
                               @RequestParam String description) {
        model.put("error", "Error during sign in!");
        String mySalt = session.getAttribute("mySalt").toString();
        if(!mySalt.equals(salt)) {
            return "redirect:/signin";
        }

        if(role.equals("student")) {
            StudentDTO user = new StudentDTO();
            user.setSurname(surname);
            user.setName(name);
            user.setPswHash(passwordHash);
            user.setSalt(salt);
            user.setUsername(username);
            user.setEmail(email);
            user.setPhoto(photo);
            user.setId(-1);

            //Check uniqueness of username and email!!
            if(!signInService.checkUniquenessStudent(username, email)) {
                return "redirect:/signin";
            }

            signInService.addStudent(user);
            session.setAttribute("user", user);
            session.setAttribute("role", role);
            model.remove("error");
            return "redirect:/search";
        }
        else if(role.equals("tutor")) {
            Integer intPrice = 0;
            if (price != null) {
                intPrice = Integer.valueOf(price);
            }
            
            TutorDTO tutor = new TutorDTO();
            tutor.setSurname(surname);
            tutor.setName(name);
            tutor.setPswHash(passwordHash);
            tutor.setSalt(salt);
            tutor.setUsername(username);
            tutor.setEmail(email);
            tutor.setPhoto(photo);
            tutor.setId(-1);
            tutor.setSubjects(subjects);
            tutor.setPrice(intPrice);
            tutor.setCurrency(currency);
            tutor.setLocation(location);
            tutor.setDescription(description);
            List<Comment> commentList = new ArrayList<>();
            tutor.setReceivedComments(commentList);

            if(!signInService.checkUniquenessTutor(username, email)) {
                return "redirect:/signin";
            }

            signInService.addTutor(tutor);
            session.setAttribute("user", tutor);
            session.setAttribute("role", role);
            model.remove("error");
            return "redirect:/tutorProfile";
        }

        return "redirect:/signin";
    }
}
