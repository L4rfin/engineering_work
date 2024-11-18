package ekg.controller;

import ekg.AppConfig;
import ekg.UsersRepository;
import ekg.entity.UserEntity;
import ekg.services.UserRepositoryService;
import ekg.utility.DataFolderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileWriter;

@Controller
public class UserCreateController {
    private final UserRepositoryService repositoryService;

    public UserCreateController(UserRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/user_create")
    public String showUserCreation() {
        return "user_create";
    }

    @GetMapping("/create_user_input")
    public String createUser(
            @RequestParam(value = "user_name", defaultValue = "null") String name,
            @RequestParam(value = "user_age", defaultValue = "0") int age,
            @RequestParam(value = "user_note", defaultValue = "null") String note,
            @RequestParam("method") int method
    ) {
        if (method == 1) {
            return "redirect:user_option";
        }
        UserEntity new_user = new UserEntity();
        new_user.setName(name);
        new_user.setAge(age);
        new_user.setNote(note);
        if (repositoryService.addUserToDatabase(new_user)) {
            DataFolderService dataFolderService = new DataFolderService();
            dataFolderService.createFileStructure(new_user.getId());
            return "redirect:home";
        }
        return "redirect:create_user";
    }

}
