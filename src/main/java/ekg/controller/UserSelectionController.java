package ekg.controller;

import ekg.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserSelectionController {

    @Autowired
    public UsersRepository repository;
    @GetMapping("/user_selection")
    public String showUserSelection(Model model){
        model.addAttribute("users",repository.findAll());
        return "/user_selection";
    }
}
