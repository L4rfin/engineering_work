package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserSelectionController {
    private final AppConfig appConfig;
    private final UsersRepository repository;

    public UserSelectionController(AppConfig appConfig, UsersRepository repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }

    @GetMapping("/user_selection")
    public String showUserSelection(Model model) {
        model.addAttribute("users", repository.findAll());
        return "/user_selection";
    }

    @GetMapping("/chose_user")
    public String goToHome(@RequestParam("userId") Long userId, Model model) {
        UserEntity user = repository.findById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            appConfig.setUser(user);
            return "/home";
        }
        model.addAttribute("users", repository.findAll());
        return "/user_selection";
    }
}
