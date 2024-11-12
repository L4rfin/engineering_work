package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserEditionController {

    final
    AppConfig appConfig;
    final
    UsersRepository repository;

    public UserEditionController(UsersRepository repository, AppConfig appConfig) {
        this.appConfig = appConfig;
        this.repository = repository;
    }

    @GetMapping("/user_edition")
    public String showUserEdition(Model model) {
        model.addAttribute("user", appConfig.getUser());
        return "/user_edition";
    }

    @PostMapping("/send_edit")
    public String saveEdit(
            @RequestParam(value = "username", defaultValue = "null") String name,
            @RequestParam(value = "age", defaultValue = "0") int age,
            @RequestParam(value = "note", defaultValue = "null") String note
    ) {
        UserEntity user = appConfig.getUser();
        user.setNote(note);
        user.setAge(age);
        user.setName(name);
        repository.save(user);
        return "redirect:/home";
    }
    @PostMapping("/delete_user")
    public String userDelete(){
        repository.delete(appConfig.getUser());
        appConfig.setUser(null);
        appConfig.setResults_1(null);
        appConfig.setResults_2(null);
        return "redirect:/login";
    }
}
