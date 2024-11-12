package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

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
            @RequestParam(value = "user_name", defaultValue = "null") String name,
            @RequestParam(value = "user_age", defaultValue = "0") int age,
            @RequestParam(value = "user_note", defaultValue = "null") String note,
            @RequestParam("method") int method
    ) {
        if (method == 1) {
            return "redirect:/user_option";
        }
        UserEntity new_user = new UserEntity();
        new_user.setName(name);
        new_user.setAge(age);
        new_user.setNote(note);
        repository.save(new_user);
        appConfig.setUser(new_user);
        if (repository.findById(new_user.getId()).isPresent()) {
            createUserTestFile(new_user);
            return "redirect:/home";
        }
        return "redirect:/create_user";
    }

    public void createUserTestFile(UserEntity user) {
        try {
            File file = new File("src/main/java/ekg/data/" + user.getId() + ".json");
            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append("{\"x\":[],"+"\"y\":[]}");
                fileWriter.close();
                System.out.println("new file created");
            }
            else System.out.println("file existed");
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
