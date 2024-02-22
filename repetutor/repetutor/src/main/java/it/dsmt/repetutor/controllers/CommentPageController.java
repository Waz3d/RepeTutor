package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.services.CommentPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentPageController {

    @Autowired
    private CommentPageService commentPageService;

    @GetMapping(value = "/addComment")
    public String getPage(@RequestParam String id, HttpSession session) {
        session.setAttribute("tutorIdComment", Integer.valueOf(id));
        return "addComment";
    }

    @PostMapping(value = "/addComment")
    public String postRequest(ModelMap model, HttpSession session, @RequestParam String comment,
                       @RequestParam String evaluation, @RequestParam String subject) {

        String studentName = ((StudentDTO)session.getAttribute("user")).getName();
        commentPageService.addComment(Integer.valueOf(session.getAttribute("tutorIdComment").toString()), studentName, comment, Integer.valueOf(evaluation), subject);

        return "redirect:/calendar";
    }
}
