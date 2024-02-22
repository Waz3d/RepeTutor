package it.dsmt.repetutor.controllers;

import com.google.common.hash.Hashing;
import it.dsmt.repetutor.accessingmysql.entities.Student;
import it.dsmt.repetutor.accessingmysql.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String getHomepage (ModelMap model) {
        return "homepage";
    }
}
