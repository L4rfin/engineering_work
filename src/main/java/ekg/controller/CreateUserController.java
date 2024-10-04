package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CreateUserController {
    private final UsersRepository repository;
    private final AppConfig appConfig;

    public CreateUserController(AppConfig appConfig, UsersRepository repository) {
        this.appConfig = appConfig;
        this.repository = repository;
    }

    @GetMapping("/create_user")
    public String showUserCreation() {

        return "/create_user";
    }

    @GetMapping("/create_user_input")
    public String createUser(
            @RequestParam("user_name") String name,
            @RequestParam("user_age") int age,
            @RequestParam("user_note") String note
    ) {
        UserEntity new_user = new UserEntity();
        new_user.setName(name);
        new_user.setAge(age);
        new_user.setNote(note);
        repository.save(new_user);
        appConfig.setUser(new_user);
        if (repository.findById(new_user.getId()).isPresent()) {
            return "redirect:/home";
        }
        return "redirect:/create_user";
    }
}
