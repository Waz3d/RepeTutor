package it.dsmt.repetutor.controllers;

import it.dsmt.repetutor.dto.StudentDTO;
import it.dsmt.repetutor.dto.TutorDTO;
import it.dsmt.repetutor.services.ChatService;
import it.dsmt.repetutor.services.SearchPageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping(value="/chat")
    public String getChat(ModelMap model, HttpSession session, @RequestParam(required = false) String id){
        String role = (String) session.getAttribute("role");
        Integer myId = 0;
        String myName = new String();
        if(role.equals("student")) {
            myId = ((StudentDTO)session.getAttribute("user")).getId();
            myName = ((StudentDTO)session.getAttribute("user")).getName() + ' ' + ((StudentDTO)session.getAttribute("user")).getSurname();
        }
        else if(role.equals("tutor")) {
            myId = ((TutorDTO)session.getAttribute("user")).getId();
            myName = ((TutorDTO)session.getAttribute("user")).getName() + ' ' + ((TutorDTO)session.getAttribute("user")).getSurname();
        }
        model.addAttribute("id", myId);
        model.addAttribute("role", role);
        model.addAttribute("fullname", myName);
        if (id == null) {
            model.addAttribute("idDest", null);
            model.addAttribute("fullnameDest", null);
        }else{
            model.addAttribute("idDest", id);
            TutorDTO tutor = chatService.getTutorById(Integer.valueOf(id));
            if (tutor == null)
                System.out.println("error");
            model.addAttribute("fullnameDest", tutor.getName() + ' ' + tutor.getSurname());

        }
        return "chat";
    }
}
